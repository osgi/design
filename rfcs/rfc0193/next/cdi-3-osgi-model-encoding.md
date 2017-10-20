# 0. Goals
- Define how the user will provide the metadata needed by the CDI-OSGi model
- Every part of an OSGi application must be defined unambiguously
    - The extender can provide defaults
    - The extender must raise definition errors on ambiguity
- It is desirable to avoid error prone solutions
    - E.g. it is easy for the user to inadvertently declare two reference rather than one by tweaking an injection point annotation.

# 1. Declaring OSGi integration in code
Beans in `@ComponentScoped` can be **shared** between Components.
E.g. if a `@ComponentScoped` bean `SB` can be reached from component bean `CB1` and from component bean `CB2`, then
any Component Context created for `CB1` or `CB2` will get a dedicated instance of `SB`.

A shared bean can have reference and configuration injection points. Each such injection point has to be satisfied
by **exactly one** synthetic bean. Otherwise the CDI container will raise an ambiguity definition error. It follows then
when from `Bean<T>.create()` is called on a synthetic reference/configuration beans it must search current 
Component Context for a suitable reference/configuration object. The synthetic beans are kind of selectors for
OSGi objects. 

The Reference/Configuration abstract members of a Component do not relate directly to the Reference/Configuration
synthetic beans. Instead they describe how to populate a Component Context with OSGi objects. Any number of 
Reference/Configuration synthetic beans can then search this context for matching objects. The OSGi Extender must provide
reasonable guarantees that the References/Configurations of a Component agree with the Reference/Configuration synthetic
Beans that can be reached from the Component Entry Beans. "Agree" means that when a synthetic bean searches a
Component Context it stands a reasonable chance to discover a matching object.

As dependency injection progresses it will hit synthetic beans that will turn to the current Component Context for
suitable objects. The Reference synthetic beans will always find a suitable object because at initialization time they
have caused each Component Model that reached them to include a Reference Model that will satisfy them.
Configuration Beans do not add Configuration Models to the currently built Component Model. Configuration Models are
only added by the Entry Beans of the Component. Configuration Beans must therefore be compatible with the
Configurations of all Components that can reach them. This may not always be verifiable at initialization time.
I.e. comparing a Configuration Model to a Configuration synthetic Bean may not always provide meaningful information
if the Configuration agrees with the Configuration Bean.

# 1.1 Declaring Components
Entry beans:
- A bean marked with `@Component` stereotype is the Entry Bean of a new Component
- A bean marked with `@Named` `@ComponentScoped` is the Entry Bean of a new Component
- A bean marked with `@Service` @ApplicationScoped is an Entry Bean in the Root Component

Configurations
- `@Configuration` on an entry bean
- `@FactoryConfiguration` on an entry bean  

Configuration Defaults
- `@Properties` on entry bean
- Property-like qualifiers on entry bean

# 1.2. Declaring Services
An Entry Bean marked with `@Service`

# 1.3. Declaring Reference injections
An injection point marked with `@Reference`

# 1.4. Declaring Configuration injections
An injection point marked with `@Configuration`

# 2. Building a CDI bundle Model
There are many Component Models
There is one Root Component Model, which is a special case of Component Model
There is one Synthetic Beans Model, which the set of synthetic Reference/Configuration beans

A (Root) Component Model describes:
- Set of entry beans
    - Many for the root
    - One for the others
- Set of References (an abstraction)
- Set of Configurations (an abstraction)

The Synthetic Beans Model describes:
- Set of Reference Bean Models
- Set of Configuration Bean Models

# 2.1. Initial Scan
1. For each entry bean
    - Lazy-create the current Component Model
        - The root has many entry beans, so it's model is reused
    - Add all Configuration Models to the current Component Model
    - Add Service Model to the current Component Model
2. For each Reference injection point in current bean
    - Add static Reference Model to the current Component Model
    - Add a Reference Bean Model to the Synthetic Beans Model 
3. For each Reference observer point in current bean
    - Add dynamic Reference Model to the current Component Model
4. For each Configuration injection point in current bean
    - Verify there is a compatible Configuration Model in the current Component Model
    - Add a Configuration Bean Model to the Synthetic Beans Model
5. For each regular injection point in current bean
    - Find matching bean
    - If bean is not visited
        - If bean is not an entry bean
            - Recursive jump to step 2 for the matching bean.
        - Otherwise
            - Definition Error

# 3. Building the CDI container
1. Create all beans in Synthetic Beans Model
