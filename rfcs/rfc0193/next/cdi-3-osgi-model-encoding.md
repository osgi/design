# 0. Goals
- Define how the user will provide the metadata needed by the CDI-OSGi model
- Every part of an OSGi application must be defined unambiguously
    - The extender can provide defaults
    - The extender must raise definition errors on ambiguity
- It is desirable to avoid error prone solutions
    - E.g. it is easy for the user to inadvertently declare two reference rather than one by tweaking an injection point annotation.

# 1. Declaring OSGi integration in code
# 1.1 Declaring Components
Entry beans:
    - A bean marked with @Component stereotype is the Entry Bean of a new Component
    - A bean marked with @Named @ComponentScoped is the Entry Bean of a new Component
    - A bean marked with @Service @ApplicationScoped is an Entry Bean in the Root Component

Configurations
    - @Configuration on an entry bean
    - @FactoryConfiguration on an entry bean  

Configuration Defaults
    - @Properties on entry bean
    - Property-like qualifiers on entry bean

# 1.2. Declaring Services
An Entry Bean marked with @Service

# 1.3. Declaring Reference injections
An injection point marked with @Reference

# 1.4. Declaring Configuration injections
An injection point marked with @Configuration

# 2. Building a CDI bundle Model
There is one Root Component Model
There are many Component Models
There is one Beans model
    - Describes the set of synthetic Reference/Configuration beans

A Component Model describes:
- Set of entry beans
    - Many for the root
    - One for the others
- Set of References (an abstraction)
- Set of Configurations (an abstraction)

The Beans Model describes:
- Set of Reference Bean Models
- Set of Configuration Bean Models

Initial Scan:
1. For each entry bean
    - Create **current host** Component Model
    - Add all Configuration Models to current host Component Model
    - Add Service Model to current host Component Model
2. For each Reference injection point in current bean
    - Add static Reference Model to current host Component Model
    - Add a Reference Bean Model to global Beans Model 
3. For each Reference observer point in current bean
    - Add dynamic Reference Model to current host Component Model
4. For each Configuration injection point in current bean
    - Verify there is a compatible Configuration Model in the current host Component Model
    - Add a Configuration Bean Model to global Beans Model
5. For each regular injection point in current bean
    - Find matching bean
    - If bean is not visited
        - If bean is not an entry bean
            - Recursive jump to step 2 for the matching bean.
        - Otherwise
            - Definition Error

# 3. Building the CDI container
1. Create all beans in Beans Model
