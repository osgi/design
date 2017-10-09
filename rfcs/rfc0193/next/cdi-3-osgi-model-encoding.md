# 1. Goals
- Define how the user will provide the metadata needed by the CDI-OSGi model
- Every part of an OSGi application must be defined unambiguously
    - The extender can provide defaults
    - The extender must raise definition errors on ambiguity
- It is desirable to avoid error prone solutions
    - E.g. it is easy for the user to inadvertently declare two reference rather than one by tweaking an injection point annotation.

# 2. Issues
## 2.1. How to describe _References_
    - Description must contain: name, scope, type, qualifiers?
    - But there is no class on which to add annotations, only injection points for Reference Instances.
## 2.2. How to attach metadata to _Root Component Environment_
    - For `@<A-Component-Scope>` it can be attached to the scope annotation since it is provided by the user
    - For `@ApplicationScoped` it is not possible to add meta annotations after the fact
