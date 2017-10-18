# 0. Goals
- Map the CDI requirements to the CDI application model (Gen-1)
- Do not introduce new solutions to problems CDI already solves (Gen-2)
- Do not describe **how** the user will express the mapping in code (Gen-2)
    - State only **exactly what** the user **must** be able to describe

# 1. Entities
Levels of structure
- Model (similar to a class file)
- Runtime State (similar to a loaded class)
- Instance (similar to a class instance/object)

## 1.1. Application Structure
- Component Model
    - Root Component Model
- Component
    - Root Component
- Component Context

## 1.2. Consuming Services
- Reference Model
- Reference Bean (static)
- Reference Event (dynamic)
- Reference Instance

## 1.3. Providing Services
- Service Model
- Service Bean
- Service Instance

## 1.4. Configuration
- Configuration Model
- Configuration Bean
- Configuration Instance

# 2. Application Structure (Conf-2)
- *Conf-2* causes the OSGi-CDI integration to be a kind of mapping of Declarative Services onto a CDI container
- The differences are
    - The Component Instance internal object structure is created via dependency injection
    - The Components can have interceptors/decorators on their objects
    - The Components can provide more than one singleton service
    - The Components share a root environment through `@ApplicationScoped`
    - The root environment is sufficient to implement simple OSGi applications
    - The root environment can host non-OSGi CDI applications

Each CDI bundle has **one** CDI container hosting all application code

## 2.1. Component Context
A DS Component Instance
- Is mapped to a **CDI context**
- Called a _Component Context_

## 2.2. Component Model
The Component structure is described by a _Component Model_ 
- Which is an **immutable data structure**
- Which describes
    - CDI scope
    - Set of Services (entry application CDI beans)
    - Set of Configurations (synthetic CDI beans)
    - Set of References (synthetic CDI beans)
- Where the entry CDI beans are declared in the CDI scope
- Where a Component Context is created/updated by instantiating one or more of the entry CDI beans
- Where the CDI scope
    - Must be active during creation/reactivation of a Component Context
    - Has **normal scope** behavior with respect to bean instances
        - I.e. for every bean in the scope at most one bean instance exists in any context created in that scope

### 2.2.1. Root Component Model
- Has CDI scope `@ApplicationScoped`
- Has 0..n optional/mandatory Configurations

### 2.2.2. (Regular) Component Model
- Has CDI scope `@ComponentScoped`
- Has 0..n optional/mandatory Configurations
- Has 0..1 multiple Configurations

## 2.3. Component
A DS Component Configuration
- Is mapped to **runtime state**
- Called a _Component_
- With structure described by a _Component Model_
- With a lifecycle bound to a set of Configurations
- With a lifecycle bound to a set of References
- With a state machine
    - `created`->
        `waiting-for-configurations`->
        `waiting-for-references`->
        `satisfied`->
        `destroying`->
        `destroyed`
    - `failed`
- Where state `satisfied` means
    - All Configurations of cardinality `mandatory` are satisfied
    - All References of cardinality `mandatory` or `at-least-one` are satisfied 
    - It becomes possible to create Component Contexts in the Component Model scope
        - E.g. when OSGi requires a service object
- When the _Component Model_ has a `multiple` cardinality Configuration 
    - As soon as one Component enters `waiting-for-references`
    - another Component is created in `waiting-for-configurations`
    - to wait for the next Configuration. 

### 2.3.1 Root Component
- Controls the lifecycle of the CDI container
    - Creates it on entry in `satisfied`
    - Destroys it on entry in `destroyed`
- Has a special state `waiting-for-extensions`
    - `created`->`waiting-for-extensions`->...
    - In which `javax.enterprise.inject.spi.Extension` OSGi services are gathered
- Controls the lifecycles of other all Components
    - Which can enter `created` only after the root enters `satisfied`
    - Which must transition to `destroyed` when the root enters `destroying` and before the root enters `destroyed`

# 3. Consuming Services
Services consumed by the CDI container are described by _Reference Models_
- Where _Reference Model_ is part of a _Component Model_
    - Called the _host_ Component Model
- Where _Reference_ is part of a _Component_
    - Called the _host_ Component
- Where _Reference Instance_ is an OSGi service object added to the host _Component_

The Reference Model describes
- Reference **name** (Conf-7)
- Reference **cardinality** (Ref-2)
- Reference **policyOption** (Ref-3)
- Reference Instance type (Ref-1)
- Reference LDAP filter (Ref-1)

The supported Reference Instance types are:
- `T`
- `ServiceReference<T>` (Ref-5)
- `ServiceObjects<T>` (Ref-4)
- `Map.Entry<T, Map<String, Object>` (Ref-4)

Where `T` is the type of the consumed OSGi service.

## 3.1. Reference lifecycle (Ref-1, Ref-2, Ref-3)
Logically a Reference contains a list of services matching it's LDAP filter. The list is ordered by the natural
OSGi service order. The list is continuously updated as services matching the filter are registered/unregistered/modified.

A Reference can have state `satisfied` or `unsatisfied`.
When a Reference becomes `unsatisfied` the host Component must transition to state `waiting-for-services`.

A Reference starts in `unsatisfied` state and an empty service list.

**Case:** Reference is `unsatisfied`, service is added (Ref-2)
Reference checks if the size of the list matches the Reference *cardinality*:
- **mandatory**: one
- **optional**: at most one
- **at-least-one**: one or more
- **multiple**: zero or more

If this is the case the Reference **binds** all services currently in the list. I.e. it obtains objects of the
specified Reference Instance type from the OSGi service registry. The Reference then becomes `satisfied`.

**Case:** Reference `unsatisfied`, and service removed
Nothing is done

**Case:** Reference `satisfied`, and service added (Ref-2, Ref-3)
The Reference may bind the service depending on it's *policyOption*: 
- **greedy**: Bind.
- **reluctant**: Do not bind.

Depending on the Reference cardinality:
- **mandatory**: Re-bind to the new service if it is ahead in the list than the currently bound service
- **optional**: If there is a bound service re-bind to the new service if it is ahead in the list than the currently
 bound service. Otherwise bind the new service.
- **at-lest-one**: Bind the new service
- **multiple**: Bind the new service

**Case:** Reference `satisfied`, and service removed (Ref-2)
The Reference checks if the service is bound. If the service is bound the Reference unbinds the service. If the number
of bound services does not match the Reference cardinality the reference checks if the list contains enough unbound
services to match the cardinality again. If there are enough services the Reference binds services starting from the
beginning of the list until the cardinality becomes satisfied. If there are not enough services the Reference becomes
`unsatisfied` and unbinds all currently bound services.

**Case:** Reference `satisfied`, and service modified (Ref-2)
A service may change it's service properties, but still match the LDAP filter. If the service is bound the Reference
behaves as if it has a `policyOption=greedy` and the service is just added to the list. If the service is not bound the
Reference behaves according to it's policyOption as if the service is just added to the list.

The Reference `policy` determines if the bind/unbind operations of that happen while the Reference remains 
`satisfied` cause the host Component to be re-built or to be dynamically notified of the change (Ref-1).
- **static**: rebuild
- **dynamic**: notify

## 3.2. Static References
A Reference is _static_ if it has `policy=static`.

### 3.2.1. Static Reference Model
A static _Reference_
- Is represented within the CDI container by a **CDI bean** 
- Called a _Reference Bean_
- Which produces _Reference Instances_ from `Bean<T>.create()`
- Which is declared in the CDI scope of the host Component Model

Except the common properties of a Reference the static Reference additionally describes:
- Reference Bean **type** (Ref-2, Ref-4, Ref-5)

The Reference Bean type is determined by the Reference cardinality and Reference Instance type (Ref-2)
- **mandatory**
    - Same as the Reference Instance type
- **optional**
    - `Optional<R>`, equal to `Optional<R>.empty()` for missing service.
    - Where `R` is the Reference Instance type.
- **at-lest-one**
    - `List<R>`
    - Where `R` is the Reference Instance type.
- **multiple**
    - `List<T>`
    - Where `R` is the Reference Instance type.

### 3.2.2. Static Reference Policy (Ref-1)
When a static Reference is in state `satisfied` and binds a new service or re-binds to a new service the host Component
transitions to `waiting-for-reference` and immediately after that back to `satisfied`. In the process all Services
are unregistered, all Component Contexts are destroyed and all Services are registered again.

## 3.2. Dynamic References
A Reference is _dynamic_ if it has `policy=dynamic`.

### 3.2.1. Dynamic Reference Model
A dynamic _Reference_ 
- Is represented within the CDI container by patterns of **CDI event dispatches** (Ref-1)
- With _Reference Event_ payload that allows the receiver
    - To access the _Reference Instance_
    - To determine the service event `type=(added, modified, removed)`

### 3.2.3. Dynamic Reference Policy (Ref-1)
After the host Component creates a Component Context
1. While the new Component Context is active on the current thread
2. An `added` event is fired for all stored Reference Instances
3. The Component Context is deactivated

Before the host Component destroys a Component Context
1. The Component Context is activated on the current thread
2. A `removed` event is fired for all stored Reference Instances
3. The Component Context is deactivated

At all other times a service event is handled as follows
1. The respective change to the service list is made
2. For each Component Context managed by the host Component
3. The Component Context is activated on the current thread
4. An event of the respective type is fired
5. The Component Context is deactivated

# 4. Providing Services
## 4.1. Service Model
Services provided by the CDI container are described by _Service Models_
- Where _Service Model_ is part of a _Component Model_
    - Called the _host_ Component Model
- Where _Service_ is part of a _Component_
    - Called the _host_ Component
- Where _Service_ is represented within the CDI container by **CDI bean**
    - Called the _Service Bean_
    - Where _Service Instance_ is the object created by `Bean<T>.create()`  
 
The _Service Model_ describes
- OSGi **service scope** (Serv-2)
- OSGi **service type** (Serv-1)

## 4.2. Service Scopes (Serv-2)
The _Service Bean_ is always declared in the Component Model scope
    - E.g. it is not possible to provide services in `@RequestScoped` or `@Dependent`

The Service Model can declare OSGi scope 
- **bundle**
    - Registered as `org.osgi.framework.ServiceFactory`
    - The use of factory allows lazy-initialization of the service objects
    - But the objects are **effective singletons** within their Component Context.
        - I.e. the first call to `ServiceFactory.getService()` add the Service Instance to the Component Context
        - I.e. subsequent calls to `ServiceFactory.getService()` return the previously created Service Instance 
- **prototype**
    - Registered as `org.osgi.framework.PrototypeServiceFactory`

CDI scope vs. OSGi scope
- The Root Component Model can define
    - Many bundle Services and no prototype Services
- Any other Component Model can define
    - Many bundle Services and no prototype Services
    - Or one prototype Service

## 4.3. Service Lifecycle
Service registration/unregistration is driven by **state transitions** (Serv-2)
- Of the host Component
- Executed when entering/leaving state `satisfied`

Service get/unget is mapped to the **lifecycle of one or more Component Contexts** (Serv-2)
- Which are stored in the Service Runtime State under a key
    - Called the _context key_
    - Corresponding to the `org.osgi.framework.Bundle` for the many bundle Services case.
    - Corresponding to each produced service object for the one prototype Service case.

On call to `(Prototype)ServiceFactory.getService()`
1. The context key is resolved from the parameters of `getService()`
2a. A new Component Context is created+activated on the current thread
    - Stored under the context key in the Service Runtime State
    - Unless there is a Component Context under that key
        - Which can happen only in the case of a bundle Service
2b. An existing Component Context is retrieved+activated on the current thread
    - Retrieved by the context key from the Service Runtime State
        - Which can happen only in the case of a bundle Service
3. All Reference Beans and Configuration Beans in the host Component Model
    are bound to objects provided by the host Component
4. The Service Bean is called to create+inject a Service Instance
5. All additional bean instances in the Component Model scope created in the process are stored in the Component Context
6. The Component Context is deactivated
7. The created Service Instance is returned to OSGi

On call to `(Prototype)ServiceFactory.ungetService()`
1. The context key is resolved from the parameters of `ungetService()`
2. A Component Context is activated on the current thread
    - Retrieved by the context key from the _Service Runtime State_  
3. All Reference Beans and Configuration Beans in the host Component Model
    are bound to objects provided by the host Component
4a. All beans in the Component Context are destroyed immediately
    - In the case of one prototype service
    - The Component Context is removed from the _Service Runtime State_
4b. All beans in the Component Context are destroyed immediately
    - Only if there are no other exported Service Instances
    - Only if the host Component is not the Root Component  
    - The Component Context is removed from the _Service Runtime State_

# 5. Configuration
Configurations consumed by the CDI container are described by _Configuration Models_
- Where _Configuration Model_ is part of a _Component Model_
    - Called the _host_ Component Model
- Where _Configuration_ is part of a _Component_
    - Called the _host_ Component
- Where _Configuration Instance_ is an `org.osgi.service.cm.Configuration` object added to the host Component

The _Configuration Model_ describes
- Configuration **pid** (Conf-1, Conf-2)
- Configuration **cardinality** (Conf-1, Conf-2)
- Configuration **default properties** (Serv-3, Conf-6, Conf-7, Conf-8) 

## 5.1. Configuration Lifecycle
Logically a Configuration is a dynamic reference to a Configuration Instance with a set PID. The reference is
continuously updated as matching configurations are added/removed to the `org.osgi.service.cm.ConfigurationAdmin`.
A Configuration does not distinguish between factory and singleton configurations.

A Configuration can have state `satisfied` or `unsatisfied`.
When a Configuration becomes `unsatisfied` the host Component must transition to state `waiting-for-configurations`.

Each Component has a most one Configuration Instance per Configuration. Configurations with `multiple` cardinality
lead to the creation of new Components from the host Component Model.

**Case:** Configuration is `unsatisfied` and Configuration Instance is added
If this is the case the Configuration becomes `satisfied`.
If the Configuration has `multiple` cardinality a new Component is created from the host Component Model. The new
Component enters state `waiting-for-configurations`.

**Case:** Configuration `satisfied`, and Configuration Instance removed
If the Configuration 

## 5.2. Configuration Beans
A Configuration
- Is represented within the CDI container by **one or more CDI beans**
- Called the _Configuration Beans_
- Where the _Configuration Bean_ CDI scope is the scope of the host Component Model
- Where the _Configuration Bean_ type is converter-compatible with `Dictionary<String, Object>`

The Component Bean Model must provide
- Configuration Bean **type** (Conf-5)

## 5.3 Configuring Services (Conf-6)
- The Service Bean description must provide default properties
- These properties are merged 
    - With those of the host Component configuration
    - With those of the Root Component configuration

## 5.4. Configuring References (Conf-7)
- Both Reference Beans and Reference Event Dispatcher Descriptions have unique names
- Both Reference Beans and Reference Event Dispatcher Descriptions have Component Scopes
- It is therefore possible to resolve a Component
    - For every Reference Event Dispatcher
    - For every Reference Instance
- The Component can contain 
    - `<ref-name>.target`
    - `<ref-name>.cardinality`

## 5.6. Configuration defaults (Conf-8)
- The CDI extender must be able to compute a name
    - For every Reference Bean
    - For every Reference Event Dispatcher Description
- The CDI extender must be able to compute a PID
    - For the `@ApplicationScoped` configuration
    - For every `@<A-Component-Scope>` configuration
- Configuration override order (Conf-8)
    ?
