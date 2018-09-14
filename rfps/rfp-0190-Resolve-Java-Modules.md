# Resolve Java modules

# Abstract
Use the OSGi Resolver to assemble a self-contained OSGi application:  JRE + Framework + Bundles
- A build/agent can call `jlink` on the resolved modules to make the JRE
- A build/agent can setup an OSGi framework to boot with the resolved bundles

# Use Case

## Build a compact docker image

## Build a compact self-contained command line tool
- The users don't have to know it's java and maintain a compatible JRE 

## Build a compact JRE

- JPMS specifies that an external party has to select a consistent set of modules before calling it.
- The OSGi Resolver can handle multiple versions and runtime requirements similar to a build.

# Requirements

## Req-1
**Must** resolve OSGi bundles against Java modules without using the Java module identity
- At least the Java module packages must be published under the `osgi.wiring.package` namespace

## Req-2

**Must** resolve Java modules against other Java modules and **May** produce a valid set according to the JPMS restrictions

- Module requires other module by name
- Modules may expose packages to a select set of friend modules
- No two modules contain (export or not) the same package
- No cycles

**Or at least as many of these as possible**

## Req-3

**Should** support version ranges on Java module requirements

- Publish the module version set at compile time
- Read the compile time versions of the module dependencies
- Use heuristics to build a version range (possibly externally configurable)
  - E.g. requirements for JDK modules have an open-ended version range: `[9, infinity)`
  - E.g. requirements for use 

## Req-4

**Should** use as many of the existing namespaces as possible

- Service Loader specification namespaces for the Java module services
- `osgi.wiring.bundle` for Java module requirements
- Prefer additional attributes on existing capabilities/requirements rather than new namespaces

## Req-5
**May** use bundle annotations on Java modules to handle dependencies that can not be captured by `module-info.java`

 `@Target(ElementType.MODULE)`

```
@Requirement("(framework=jpa)(jpa.version >= 1)(!(jpa.version < 2))")
module org.example.foo {
    uses org.example.hello.Hello;
}

@Capability("framework=jpa", "version=1.0")
module com.bar.jpa {
}
```
## Req-6

**Must** be able to resolve third party Java modules and expose their packages within the OSGi framework

- At lest specify how `org.osgi.framework.packages.extra` should work

## Req-7

**May** specify runtime representation for the Java modules