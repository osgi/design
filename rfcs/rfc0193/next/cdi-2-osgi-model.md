# 1. Goals
- Map the CDI requirements to the CDI application model (Gen-1)
- Do not introduce new solutions to problems CDI already solves (Gen-2)
- Do not describe **how** the user will express the mapping in code (Gen-2)
    - State only **exactly what** the user **must** be able to describe

# 2. Components (Conf-2)
- *Conf-2* causes the OSGi-CDI integration to be a kind of mapping of Declarative Services onto a CDI container
- The differences are
    - The Component internal object structure is created via dependency injection
    - The Components can have interceptors/decorators on their objects
    - The Components can provide more than one singleton service
    - The Components share a root environment through `@ApplicationScoped`
    - The root environment is sufficient to implement simple OSGi bundles
    - The root environment can host non-OSGi CDI applications

Each CDI bundle has **one** CDI container hosting all application code

## 2.1. Component Context
A DS Component Instance
- Is mapped to a **CDI context**
- Called a _Component Context_

## 2.2. Component Model
A DS Component Description
- Is mapped to an **immutable object structure**
- Called a _Component Model_
- Which describes
    - CDI scope
    - Set of Services (entry CDI beans)
    - Set of Configurations
    - Set of References
- Where a Component Context is created/updated by instantiating one or more of the entry CDI beans
- Where the CDI scope
    - Must be active during creation/reactivation of a Component Context
    - Has **normal scope** behavior with respect to bean instances
        - I.e. for every bean in the scope at most one bean instance exists in any context created in that scope
    - Has to be a scope dedicated to components or `@ApplicationScoped`   
- Where the set of Configurations can contain at most one Configuration with multiple cardinality

_Root (Component) Model_
- Has CDI scope `@ApplicationScoped`
- Has only optional/mandatory configurations

## 2.3. Component Environment
A DS Component Configuration
- Is mapped to **runtime state**
- Called a _Component Environment_
- With structure described by a _Component Model_
- With a lifecycle bound to a set of Configurations (I.e. `org.osgi.service.cm.Configuration` objects)
- With a lifecycle bound to a set of References (I.e. OSGi service objects)
- With a state machine
    - `created`->
        `waiting-for-configurations`->
        `waiting-for-references`->
        `satisfied`->
        `destroying`->
        `destroyed`
    - `failed`
- Where state `satisfied` means
    - All Configurations are satisfied
    - All References of cardinality `mandatory` or `at-least-one` are satisfied 
    - It is now possible to create Component Contexts in the Component Model scope
        - E.g. when OSGi requires a service object
- When the _Component Model_ has a multiple cardinality Configuration 
    - As soon as one Component Environment enters `waiting-for-references`
    - another empty Component Environment is created in `waiting-for-configurations`
    - to wait for the next configuration instance. 

_Root (Component) Environment_
- Controls the lifecycle of the CDI container
    - Creates it on entry in `satisfied`
    - Destroys it on entry in `destroyed`
- Has a special state `waiting-for-extensions`
    - `created`->`waiting-for-extensions`->...
    - In which `javax.enterprise.inject.spi.Extension` OSGi services are gathered
- Controls the lifecycles of other all Component Environments
    - Which can enter `created` only after the root enters `satisfied`
    - Which must transition to `destroyed` when the root enters `destroying` and before the root enters `destroyed`

# 3. Consuming Services
Services consumed by the CDI container are described by _References_
- Where _Reference_ is part of the _Component Model_
- Where _Reference Runtime State_ is stored in a _Component Environment_
    - Called the _host_ Component Environment
    - E.g. a `ServiceTracker`
- _Reference Instance_ is an OSGi service object added to a _Component Context_
    
## 3.1. Static policy References
### 3.1.1. Model
A static _Reference_
- Is mapped to a **CDI bean** 
- Called a _Reference Bean_
- Which produces _Reference Instances_ from `Bean<T>.create()`
- Which can be declared in any scope (Mig-1, Ref-6)
    - Where the Component Model scope must be active when the Reference Bean scope is active
    - E.g. references defined in the Root Component Model can be `@RequestScoped`, because `@ApplicationScoped` is
        always active when `@RequestScoped` is active 

The static Reference describes
- Reference **name** (Conf-7)
- Reference **cardinality** (Ref-2)
- Reference **policyOption** (Ref-3)
- Reference Bean **type** (Ref-2, Ref-4, Ref-5)
- Reference Bean **scope** (Mig-1, Ref-6, Ref-7, Conf-7)

### 3.1.2. Type and Cardinality
Reference Bean cardinality vs Reference Instance type (Ref-2)
- **mandatory**
    - `Bean<T>`
    - `Bean<ServiceReference<T>>` (Ref-5)
    - `Bean<ServiceObjects<T>>` (Ref-4)
    - `Bean<Map.Entry<T, Map<String, Object>>` (Ref-4)
- **optional**
    - `Bean<Optional<T>>`, equal to `Optional<T>.empty()` for missing service.
    - The other representations are defined accordingly
- **at-lest-one**
    - `Bean<List<T>>`, sorted by natural service order, where the list is never empty.
    - The other representations are defined accordingly
- **multiple**
    - `Bean<List<T>>`, sorted by natural service order, where the list can be empty.
    - The other representations are defined accordingly

### 3.1.3. Service Dynamics
Service events are mapped to **state transitions**
- Of the host Component Environment
- Following the DS rules for `cardinality` and `policyOption` for static references (Ref-2, Ref-3)

## 3.2. Dynamic policy References
### 3.2.1. Model
A dynamic _Reference_ 
- Is mapped to a model of an **event dispatcher** (Ref-1)
- Called a _Reference Event Dispatcher_
    - Which is the _Reference Runtime State_ of a dynamic Reference
- Which dispatches _Reference Events_
    - To CDI observer methods
    - Of `type=(added, modified, removed)`
    - With a _Reference Instance_ payload

The dynamic Reference describes
- Reference **name** (Conf-7)
- Reference **cardinality** (Ref-2)
- Reference **policyOption** (Ref-3)
- Reference Instance **type** (Ref-4, Ref-5)

### 3.2.2. Type and Cardinality
Supported Reference Instance types
- `T`,
- `ServiceReference<T>` (Ref-4, Ref-5)
- `ServiceObjects<T>>` (Ref-4)
- `Map.Entry<T, Map<String, Object>>` (Ref-5)

A Reference Event Dispatcher is created for every Component Environment (Ref-1)

The dispatcher stores Reference Instances respective to it's cardinality (Ref-2)
- **mandatory**: one
- **optional**: at most one
- **at-least-one**: one or more
- **multiple**: zero or more

### 3.2.3. Service Dynamics
Service events are mapped to **CDI event dispatches** (Ref-1)

After the host Component Environment creates a Component Context
0. There is already Component Context active on the current thread
1. An `added` event is fired for all stored Reference Instances
2. The Component Context is deactivated

Before the host Component Environment destroys a Component Context
1. The Component Context is activated on the current thread
2. A `removed` event is fired for all stored Reference Instances
3. The Component Context is deactivated

At all other times a service event is handled as follows
1. The respective change to the set of Reference Instances is made
    - Register: create + add instance
    - Unregister: remove instance
    - Modify: recreate/modify instance as needed
2. For each Component Context managed by the host Component Environment
3. The Component Context is activated on the current thread
4. An event of the respective type is fired
5. The Component Context is deactivated

Service events are also mapped **state transitions**
- Of the host Component Environment
- Following the DS rules for `cardinality` and `policyOption` for dynamic references (Ref-2, Ref-3)

# 4. Providing Services
## 4.1. Model
A provided OSGi _Service_ is mapped to a **CDI bean** 
- Called the _Service Bean_
- Where _Service_ is part of the Component Model
- Where _Service Runtime State_ is stored in a _Component Environment_
    - Called the _host_ Component Environment
    - E.g. the `org.osgi.framework.ServiceRegistration`
- Where _Service Instance_ is the object created by `Bean<T>.create()`

The _Service_ describes
- OSGi **service scope** (Serv-2)
- OSGi **service properties** (Serv-3)
- Service Bean **type** (Serv-1)

## 4.2. Scopes (Serv-2)
The _Service Bean_ is always declared in the Component Model scope
    - E.g. it is not possible to provide services in `@RequestScoped` or `@Dependent`

A Service can have OSGi scope 
- **bundle**
    - Registered as `org.osgi.framework.ServiceFactory`
    - The use of factory allows lazy-initialization of the service objects
    - But the objects are **effective singletons** within their Component Context.
        - I.e. once created the same instance is always returned.
- **prototype**
    - Registered as `org.osgi.framework.PrototypeServiceFactory`

CDI scope vs. OSGi scope
- The Root Component Model can define
    - Many bundle Services and no prototype Services
- Any other Component Model can define
    - Many bundle Services and no prototype Services
    - Or one prototype Service

## 4.3. Lifecycle
Service registration/unregistration is driven by **state transitions** (Serv-2)
- Of the host Component Environment
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
    are bound to objects provided by the host Component Environment
4. The Service Bean is called to create+inject a Service Instance
5. All additional bean instances in the Component Model scope created in the process are stored in the Component Context
6. The Component Context is deactivated
7. The created Service Instance is returned to OSGi

On call to `(Prototype)ServiceFactory.ungetService()`
1. The context key is resolved from the parameters of `ungetService()`
2. A Component Context is activated on the current thread
    - Retrieved by the context key from the _Service Runtime State_  
3. All Reference Beans and Configuration Beans in the host Component Model
    are bound to objects provided by the host Component Environment
4a. All beans in the Component Context are destroyed immediately
    - In the case of one prototype service
    - The Component Context is removed from the _Service Runtime State_
4b. All beans in the Component Context are destroyed immediately
    - Only if there are no other exported Service Instances
    - Only if the host Component Environment is not the Root Component Environment  
    - The Component Context is removed from the _Service Runtime State_

# 5. Configuration
A _Configuration_ provided by the `ConfigurationAdmin` is mapped to a **CDI Bean**
- Called the _Configuration Bean_
- Where _Configuration_ is part of the _Component Model_
- Where _Configuration Instance_ is produced from `Bean<T>.create()`

The _Configuration_ describes
- Configuration **pid**
- Bean **type** (Conf-5)
- Bean **scope** (Conf-4)

## 5.1. Cardinality
Configurations can have cardinality relative to the CDI container
- **mandatory** Exactly one Configuration Instance is consumed
- **optional** At most one Configuration Instance is consumed
- **multiple** Many Configuration Instances are consumed

Each Component Environment can consume at most one `org.osgi.sevice.cm.Configuration` object per Configuration 
regardless of the Configuration cardinality.

Component Models that declare on a multiple cardinality Configuration produce one Component Environment for every
consumed `org.osgi.service.cm.Configuration` object.  
 
## 5.2. Configuration Beans
A _Configuration_  

- A `org.osgi.service.cm.Configuration` is mapped to a **Component Environment**
    - Each Configuration with the PID of Component Scope creates a new Component Environment
- A `org.osgi.service.cm.Configuration` is mapped to **multiple beans** (Conf-5)
    - Called _Configuration Beans_
    - Where
        - _Configuration Bean_ is a static definition obtained from the _Component Scope_
            - E.g. via annotations
        - _Configuration Instance_ is a bean instance added to a _Component Context_
    - Where the _Configuration Bean_ CDI scope is _Component Scope_
    - Where the _Configuration Bean_ type is converter-compatible with `Dictionary<Stirng, Object>`
- The Configuration Bean description must provide
    - Bean **type** (Conf-5)
    - Bean **name**
    - Bean **scope** (Conf-4)
    - Bean **qualifiers**
- Configuring provided service properties (Conf-6)
    - The Service Bean description must provide default properties
    - These properties are merged 
        - With those of the host Component Environment configuration
        - With those of the Root Component Environment configuration
- Configuring consumed service target filter and cardinality (Conf-7)
    - Both Reference Beans and Reference Event Dispatcher Descriptions have unique names
    - Both Reference Beans and Reference Event Dispatcher Descriptions have Component Scopes
    - It is therefore possible to resolve a Component Environment
        - For every Reference Event Dispatcher
        - For every Reference Instance
    - The Component Environment can contain 
        - `<ref-name>.target`
        - `<ref-name>.cardinality`
- Default PIDS/Names (Conf-8)
    - The CDI extender must be able to compute a name
        - For every Reference Bean
        - For every Reference Event Dispatcher Description
    - The CDI extender must be able to compute a PID
        - For the `@ApplicationScoped` configuration
        - For every `@<A-Component-Scope>` configuration
- Configuration override order (Conf-8)
    ?

# 6. Glossary
## 6.1. Configuration/Application Structure
- Component Model (compile time)
- Component Environment (runtime)
    - Root Component Environment
- Component Context (runtime)
- Configuration Bean (compile time)
- Configuration Instance (runtime)

## 6.2. Consuming Services
- Reference (compile time)
    - static: Reference Bean
    - dynamic: Reference Event Dispatcher Model
- Reference Runtime State (runtime)
    - static: No special name
    - dynamic: Reference Event Dispatcher
- Reference Instance (runtime)

## 6.3. Providing Services
- Service Bean (compile time)
- Service Runtime State (runtime)
- Service Instance (runtime)