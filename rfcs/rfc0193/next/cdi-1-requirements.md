# General
- *Gen-1* Map requirements to the CDI execution model
    - E.g. control the number of instances of some bean via scopes/contexts.
    - E.g. control the validity of injection points via bean scopes.
    - E.g. map dynamics to container events or context creation/destruction.
- *Gen-2* Use as few constructs/concepts/rules as possible
    - Possibly at the expense of granularity
    - E.g. it may be simpler to not support configuration of individual service injection points.

# Migration
- *Mig-1* Replace beans with static consumed services
    - Replace a local bean of **any scope** with a consumed service with **static** policy
    - Without code changes to the other local beans.
- *Mig-2* Provide services from beans
    - Declare an `@ApplicationScoped` bean as a provided service of OSGi scope *singleton*.
    - Without code changes to any bean
- *Mig-3* Replace local beans with transformed configuration objects 
    - Without code changes to the local beans.
- *Mig-4* Consume singleton configuration
    - Configure consumed service target filters and cardinality
    - Configure provided service properties

# Consuming Services
- *Ref-1* Support `policy=(static | dynamic)`
    - Synchronous notification for dynamic service events
- *Ref-2* Support `cardinality=(optional | mandatory | at-least-one | multiple)`
    - Natural service ordering for `cardinality=(at-least-one | multiple)`
- *Ref-3* Support `policyOption=(reluctant | greedy)`
- *Ref-4* Provide access to the properties of consumed services
    - E.g. inject `Map<String, Object>`.
- *Ref-5* Support late service binding
    - E.g. inject `ServiceReference<T>` and `BundleContext` 
- *Ref-6* Inject services with static policy into beans of any scope
    - E.g. `@ApplicationScoped`, `@RequestScoped`
    - Also required by *Mig-1*
- *Ref-7* Handle stateful services correctly
    - E.g. the user controls how many instances of a "prototype" scoped OSGi service are created and where are they injected.
    - Also required by *Mig-1*
- *Ref-8* Support Interceptors/Decorators on consumed service objects 

# Providing Services
- *Serv-1* Support service `objectClass`
- *Serv-2* Support service registration `scope=(singleton | bundle | prototype)`
- *Serv-3* Support service registration properties
- *Serv-4* Support `immediate=(true | false)`
- *Serv-5* Support Interceptors/Decorators on provided service objects

# Configuration
- *Conf-1* Support singleton cardinality configurations
- *Conf-2* Support multiple cardinality configurations
    - A configuration instance creates a component (in the abstract sense)
    - A component can consume services
    - A component can provide at least one service
    - The configuration instance can modify
        - Consumed services target filters
        - Consumed services cardinality
        - Provided service properties
        - Injected configuration values
- *Conf-3* Support configuration `policy=(mandatory | optional)`
    - E.g. for optional there must be a way do declare configuration defaults
- *Conf-4* Inject singleton configuration into beans of any scope.
    - E.g. `@ApplicationScoped`, `@RequestScoped`
    - Supports *Mig-3*
- *Conf-5* Inject configuration transformations via the converter
- *Conf-6* Configure the properties of provided services
- *Conf-7* Configure the target filters and cardinalities of consumed services
- *Conf-8* Support override at runtime, without explicit application opt-in
    - Of Conf-6 and Conf-7
    - E.g. the user does not have to declare in code where configuration applies
