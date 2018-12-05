# RFP-191: OSGi's future role in distributed systems 

## Abstract

Due to the prevalence of distributed systems in computing, it is essential to reflect which aspects, if any, are missing from the OSGi ecosystem. This document is a broad review of the state of the art and is meant as a summary of idioms, terminology and pre-existing technologies. It is not a normative document and does not declare any position or opinions by the OSGi Alliance or any members in any way.

## License

**DISTRIBUTION AND FEEDBACK LICENSE, Version 2.0**

The OSGi Alliance hereby grants you a limited copyright license to copy and display this document (the “Distribution”) in any medium without fee or royalty. This Distribution license is exclusively for the purpose of reviewing and providing feedback to the OSGi Alliance.  You agree not to modify the Distribution in any way and further agree to not participate in any way in the making of derivative works thereof, other than as a necessary result of reviewing and providing feedback to the Distribution.  You also agree to cause this notice, along with the accompanying consent, to be included on all copies (or portions thereof) of the Distribution. The OSGi Alliance also grants you a perpetual, non-exclusive, worldwide, fully paid-up, royalty free, limited license (without the right to sublicense) under any applicable copyrights, to create and/or distribute an implementation of the Distribution that: (i) fully implements the Distribution including all its required interfaces and functionality; (ii) does not modify, subset, superset or otherwise extend the OSGi Name Space, or include any public or protected packages, classes, Java interfaces, fields or methods within the OSGi Name Space other than those required and authorized by the Distribution. An implementation that does not satisfy limitations (i)-(ii) is not considered an implementation of the Distribution, does not receive the benefits of this license, and must not be described as an implementation of the Distribution. "OSGi Name Space" shall mean the public class or interface declarations whose names begin with "org.osgi" or any recognized successors or replacements thereof. The OSGi Alliance expressly reserves all rights not granted pursuant to these limited copyright licenses including termination of the license at will at any time.



EXCEPT FOR THE LIMITED COPYRIGHT LICENSES GRANTED ABOVE, THE OSGi ALLIANCE DOES NOT GRANT, EITHER EXPRESSLY OR IMPLIEDLY, A LICENSE TO ANY INTELLECTUAL PROPERTY IT, OR ANY THIRD PARTIES, OWN OR CONTROL.  Title to the copyright in the Distribution will at all times remain with the OSGi Alliance.  The example companies, organizations, products, domain names, e-mail addresses, logos, people, places, and events depicted therein are fictitious.  No association with any real company, organization, product, domain name, email address, logo, person, place, or event is intended or should be inferred.   



THE DISTRIBUTION IS PROVIDED "AS IS," AND THE OSGi ALLIANCE (INCLUDING ANY THIRD PARTIES THAT HAVE CONTRIBUTED TO THE DISTRIBUTION) MAKES NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT, OR TITLE; THAT THE CONTENTS OF THE DISTRIBUTION ARE SUITABLE FOR ANY PURPOSE; NOR THAT THE IMPLEMENTATION OF SUCH CONTENTS WILL NOT INFRINGE ANY THIRD PARTY PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.  

NEITHER THE OSGi ALLIANCE NOR ANY THIRD PARTY WILL BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF OR RELATING TO ANY USE OR DISTRIBUTION OF THE DISTRIBUTION.  



Implementation of certain elements of this Distribution may be subject to third party intellectual property rights, including without limitation, patent rights (such a third party may or may not be a member of the OSGi Alliance). The OSGi Alliance is not responsible and shall not be held responsible in any manner for identifying or failing to identify any or all such third party intellectual property rights.



The Distribution is a draft. As a result, the final product may change substantially by the time of final publication, and you are cautioned against relying on the content of this Distribution.  You are encouraged to update any implementation of the Distribution if and when such Distribution becomes a final specification.   



The OSGi Alliance is willing to receive input, suggestions and other feedback (“Feedback”) on the Distribution.  By providing such Feedback to the OSGi Alliance, you grant to the OSGi Alliance and all its Members a non-exclusive, non-transferable, worldwide, perpetual, irrevocable, royalty-free copyright license to copy, publish, license, modify, sublicense or otherwise distribute and exploit your Feedback for any purpose.  Likewise, if incorporation of your Feedback would cause an implementation of the Distribution, including as it may be modified, amended, or published at any point in the future (“Future Specification”),  to necessarily infringe a patent or patent application that you own or control, you hereby commit to grant to all implementers of such Distribution or Future Specification an irrevocable, worldwide, sublicenseable, royalty free license under such patent or patent application to make, have made, use, sell, offer for sale, import and export products or services that implement such Distribution or Future Specification.  You warrant that (a) to the best of your knowledge you have the right to provide this Feedback, and if you are providing Feedback on behalf of a company, you have the rights to provide Feedback on behalf of your company; (b) the Feedback is not confidential to you and does not violate the copyright or trade secret interests of another; and (c) to the best of your knowledge, use of the Feedback would not cause an implementation of the Distribution or a Future Specification to necessarily infringe any third-party patent or patent application known to you.  You also acknowledge that the OSGi Alliance is not required to incorporate your Feedback into any version of the Distribution or a Future Specification.



**I HEREBY ACKNOWLEDGE AND AGREE TO THE TERMS AND CONDITIONS DELINEATED ABOVE.**

## Trademarks

OSGi™ is a trademark, registered trademark, or service mark of the OSGi Alliance in the US and other countries. Java is a trademark, registered trademark, or service mark of Oracle Corporation in the US and other countries. All other trademarks, registered trademarks, or service marks used in this document are the property of their respective owners and are hereby recognized.

## Feedback

This document can be downloaded from the OSGi Alliance design repository at <https://github.com/osgi/design> The public can provide feedback about this document by opening a bug at <https://www.osgi.org/bugzilla/>.

## Terminology and Document Conventions

The key words "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL NOT", "SHOULD", "SHOULD NOT", "RECOMMENDED", "NOT RECOMMENDED", "MAY" and "OPTIONAL" in this document are to be interpreted as described in 6.1.

`Source code is shown in this typeface.`

## Revision History

The last named individual in this history is currently responsible for this document.

| **Revision** | **Date**    | **Comments**                                                 |
| ------------ | ----------- | ------------------------------------------------------------ |
| 0.1          | Oct 08 2018 | Initial version based on rough notes and discussions within CPEG <br />Todor Boev, Software AG, <todor.boev@softwareag.com> |
| 0.2          | Nov 19 2018 | Add front matter<br />Raymond Auge, Liferay Inc., <raymond.auge@liferay.com> |


  

# Application Domain

## Applications are becoming...
- **Distributed:** From an architecture point of view
	- System is always in flux and partial failure
- **Multilingual:** From a development point of view
	- System designed as protocols, data formats, communication patterns.
	- Not as language APIs
- **Containerized:** From an operations point of view
	- Generic, but restricted interface: posix

## How it works
- **Leverage containers**
	- They allow multilingual apps
	- They allow resource control
- **To move as much function as possible...**
	- Away from the application code
	- Into "Orchestrators"
		- Shield the app form the "fallacies of distributed computing"
	- Ultimately we get to FaaS
- **Orchestrator enforces particular...**
	- Shape of the app: what it can do
	- Assumptions about what it needs
- **CaaS (K8S, Swarm):**
	- Apps
		- Stateful
		- Long running
		- Weak at handling changes in environment
	- Orchestrator 
		- Tries to make the environment look as static as possible
		- Tries to keep at least one instance running
- **FaaS (OpenWhisk, Fn):**
	- Apps
		- Stateless
		- Short running
		- Unaware of environment
	- Orchestrator
		- Wraps application code into an executor container
			- E.g. a container with `node.js`
		- Container exposes a strictly defined call interface to Orchestrator
			- E.g. `/init` to inject the application code and `/call` to make a function call.

## Problem Description
- **Problem: Where does OSGi fit?**
- **OSGi app shape:**
	- App code in simple bundles: annotation driven 
		- Especially singe OSGi R7: DS, Bundle annotations, ...
	- Managing infrastructure on board: `RSA` + `DS` + `ConfigAdmin` + ...
		- Rather than in the Orchestrator
- **OSGi app traits:**
	- Can be deployed on a wide array of operational infrastructure: `os` -> `vm` -> `container`
	- Module layer provides: building low footprint apps (for a Java app)
	- Service layer provides: adaptive apps (killer feature?)
	- Merge/Split depending on scaling needs:
		- E.g. run my components on the same JVM for dev and in container-per-subsystem in prod.
		- E.g. redistribute components between JVMs to improve performance/locality.
- **Solution: approach**
	- Find OSGI's natural place in today's distributed systems as a peer to the other technologies
	- **Do not try to match feature-for-feature...**
		- Other Java platforms: Spring(Boot), Microprofile
		- Other languages: node.js, Go
	- **Try to find the problems OSGi solves in a unique way**
		- If someone solves them better - then see if competition make sense.

# Requirements

- An open discussion on OSGi's future yielded the lines of inquiry described below.
- These are not final, we hope to get as much additional suggestions, questions and corrections as possible.

## CaaS, FaaS deployment:
- **Not strictly "distribution", but relevant**

- **Pressure on Java to...**
	- Reduce footprint: disk, (more important) memory 
	- Reduce startup time

- **JVM solutions**
	- JPMS: reduces JRE, but not clear if it helps in general (some day `jlink` produces faster images?)
	- Substrate VM: reduces everything at a high cost
	- Java frameworks and libraries have to adapt first (especially to Substrate VM)

### OSGi now
- Reduce footprint with the requirement/capability based component assembly.

### OSGi future
- Adapt to JPMS
	- [RFP-190: Resource encoding for Java Modules](https://github.com/osgi/design/blob/master/rfps/rfp-0190-Resource%20encoding%20for%20Java%20Modules.odt)
	- [RFP-143: OSGi connect: remove modular layer](https://osgi.org/svn/documents/trunk/rfps/rfp-0143-OSGiConnect.pdf)
		- Diverges into a different topic: what to do if Java moves to JPMS and abandons OSGi.
		- Must make sure these still work: extender pattern, whiteboard pattern, requirement/capability/wiring/resource runtime model
- Adapt to Substrate VM
	- *New RFP:* Make sure the OSGi core can compile: remove modular layer?
- Push for light weight components
	- **Conjecture:** Go, node.js, etc. are efficient because they don't have the Java heavyweight libraries.
	- Programming model: (lambda/async over)? DS over CDI
	- Communication:
		- ? over JAX-RS
		- Do not use REST within a distributed cluster
	- Persistence:
		- ?
		- Persistent Actors
	- Tracing
		- ?
	- ... what else?

## CaaS communication model
- CaaS (K8S, Swarm) tend to use a "brute force" approach to shield the application 
	- Cluster every distributed component
	- Play with network to keep every component accessible through a static name
	
### OSGi now
- RSA enables a app to handle the true distributed environment dynamics
- Client-Side loadbalancing: 
	- RSA publishes as local services **all** suitable remote endpoints
	- App can load balance
- Circuit-breaker: 
	- When all remote endpoints go down DS stops local consumer component until an endpoint comes back up
- Errors handling:
	- Rather than just stop or retry app can see event and take alternative action
- Distributed lifecycle/Continuous recovery:
	- RSA publishes local services that represent the lifecycle of remote resources
	- App can tie it's lifecycle to the remote resources
	- For example:
		1. Database is published as a service of arbitrary type, but with a property for the DB URL
		2. A component will start when a service with a DB URL property it can use appears
		3. A component will stop when the DB goes away and restart/recover when it comes back
	- *NOTE:*  Seems to enable actor-like behavior where components can reset to initial state when remote calls fail
- **Question:** Can/Should the app react to changes to global system state?
	- E.g. start processing requests when the entire system is ready?
		- Rather than just the immediately visible remote endpoints
	- E.g. user publishes remote endpoints as global signals?
- *NOTE:* Tie ourselves to "reactive" as in "reactive manifesto" 
	- But it's more about async message passing, than our type of "reactive"
	
### OSGi future
- Adapt RSA to CaaS
	- How does OSGi communicate to CaaS it should scale it up/down?
		- CaaS monitors cpu/memory and potentially custom metrics
			[K8S horizontal pod autoscale](https://kubernetes.io/docs/tasks/run-application/horizontal-pod-autoscale/#support-for-metrics-APIs)
		- Enhance RSA to collect metrics and push to CaaS?
	- How does an OSGi instance load balance between all possible candidates it can call?
		- Talk to CaaS to get performance data?
		- RSA distributes the Cluster Information Service to everyone?
	- How does OSGi integrate with services that do not use RSA?
		- RSA talks to CaaS to get instance information?
		- RSA talks to CaaS to get endpoint information?
	- *Impl or RFP:* Load Balancing Topology Manager
		- *NOTE:* Compare with what others are doing: Eureka, Envoy, ...
		- The distribution provider only distributes
		- Load balancing decisions should be made by the topology manager: controls the service hook.
		- Specify at least a name other than `promiscuous` for the topology manager
		- Every subset of remote endpoints may have different loadbalancing needs
		- ...
	- [RFC-240: Compute Management Service](https://github.com/osgi/design/blob/master/rfcs/rfc0240/rfc-0240-Compute-Management-Service.pdf)
		- Prototype is about top-down calls from OSGi to CaaS to create/destroy containers etc.
			- I.e. OSGi is part of the *control plane*
		- Extend with bottom-up API for OSGi to CaaS to pull/push performance/discovery data.
			- I.e. OSGi participates in the *data plane*
- Adapt to "brute force" approach (without RSA?)
	- Explore if/how this reduces the value of the service layer
		- E.g. service dynamics do not matter: containers are static, remote endpoints too.
		- E.g. but service dynamics still free you from doing the start/stop order of modules manually
		- E.g. but service layer is still a good abstraction to hook modules together
- Integrate with modern distributed ecosystem
	- **Question:** Should we target the CNCF ecosystem as something of near-standard stability?
	- *Impl:* Not clear if there is anything to specify
		- These are moving targets rather than long-lived standards, we may specify SPIs to plug them into RSA...
	- RSA adoption of https://opentracing.io/
	- RSA discovery for Consul, Eureka, .. K8S API server?
		- **Question:** Is it worth doing this or just give up since sidecar loadbalancing (e.g. Envoy) seems to take over?
			- Sidecar load balancing does not propagate cluster topology changes back to app - no distributed lifecycle.
		- [Ribbon](https://medium.com/netflix-techblog/announcing-ribbon-tying-the-netflix-mid-tier-services-together-a89346910a62)
	- RSA distribution providers for gRPC, thrift, etc.
	- RSA/PuthStreams distribution providers for AMQP, MQTT, etc.
	- RSA topology manager with...? K8S API server?

## Application distribution
- **Conjecture**: The world demands agile horizontal scalability more than vertical scalability (in-process efficiency)
	- Some: expand at unlimited scale (not everyone is google)
	- Most: keep it small, but expand and shrink rapidly

### OSGi now
- Decouples the decision to distribute from the decision to reuse logic
	- Makes it cheaper to re-distribute code when tuning the distribution layout.
	- [Monoliths, Cookie-Cutter or Microservices?](https://paulhammant.com/2014/12/07/moniliths-cookiecutters-or-microservices/)
	- [Microservices, bounded context, cohesion. What do they have in common?](https://hackernoon.com/microservices-bounded-context-cohesion-what-do-they-have-in-common-1107b70342b3)
	- [Design Patterns for Microservices -> Decomposition patterns](https://dzone.com/articles/design-patterns-for-microservices?edition=408215&utm_source=Weekly%20Digest&utm_medium=email&utm_campaign=Weekly%20Digest%202018-10-24)
- Using containers as the *universal* unit of modularity/reuse is wrong
	- Gluing components via the limited container interface incurs heavy performance penalties
	- Distribution is also a fundamental architectural change
		- Failure handling changes fundamentally
		- Opens new attack surfaces to secure
- It is (very) hard to decide on a distribution layout

### OSGi future
- Automate the distribution decisions.
- Discover cut lines using the requirements/capability model
	- E.g. at the remotable services
- Apply OSGi Resolver to distribute bundles between runtimes.
- *NOTE:* Related to [RFP-188: Features](https://github.com/osgi/design/blob/master/rfps/rfp-0188-Features.pdf)

## Asynchronous programming
- **Conjecture**: 
	- Not used in enterprise, because container Orchestrators promise to solve it through brute force scaling.
	- Used in IoT where the operator has no control over the network.

- **Helps to express concurrency:**
	- Improve quality: 
		- Responsiveness/liveness
			- E.g. a thread that handles an incoming request will not block waiting for a downstream response.
		- Remain available even in the face of partial failures 
			- E.g. at external integration points - db, peer process, etc.
			- [GOTO 2016 • Stability Patterns & Antipatterns • Michael T. Nygard](https://www.youtube.com/watch?v=VZePNGQojfA)
	- Improve performance: 
		- Run more on less CPU/Memory
	- Improve diagnostics: 
		- Async infrastructure enables access to the fine grained tasks
	- **Question:** Do OSGi services provide benefits to the async programming model?
		- No: Promises and PushStreams are libraries
		- Yes: AsyncService together with RSA

### OSGi now
- AsyncService, Promises, PushStreams
	
### OSGi future
- *New RFC:* General purpose messaging service (probably based on PushStreams)
- *New RFC:* Actors

# Appendix

## Hystrix
Hystrix is a library that adds certain measure of resilience to distributed applications where the prevailing 
communication mechanism is point-to-point synchronous calls (e.g. HTTP requests).

At present it is succeeded by [ressilience4j](https://github.com/resilience4j/resilience4j), which is inspired
by it's design and offers the same core patterns.

Here we explore the design of Hystrix to find analogies with the current OSGi specifications and pinpoint potential
improvements to OSGi for R8.

[Hystrix](https://github.com/Netflix/Hystrix/wiki/How-it-Works)
[Hystrix Diagram](https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/hystrix-command-flow-chart.png)

- **Question**: What is it for?
	- Distributed application resilience to calls to remote dependencies
	- It is purely a client-side library
	- Tuned for the Netflix use case:
		- E.g. each node has a large number of remote dependency nodes to load balance
	- *NOTE:* Our conditions are probably different
		- Not a humongous scale
		- But saturation of a limited compute pool that may change rapidly
			- E.g. add/remove new nodes to CaaS to handle spikes in load
			- E.g. CaaS shuffles containers aggressively around the current nodes to saturate them better
		- **Question:** Does Hystrix help there?
		
- **Question**: How does Hystrix relate to the other Netflix projects (Ribbon, Eureka, Archieus)?
	- It only implements the circuit breaking behavior
	- It uses Ribbon for client side load balancing
	- Ribbon uses Eureka for discovery
	- Ribbon/Hystrix use Archius for configuration.
	- ...
	
### Design assumption: Most network access is performed synchronously
- **Question**: Do all the patterns built around this work for other communication patterns?
	- Event driven?
	- Note that event driven may be built on top of sync calls to queues for example.

### Design assumption: Failure and latency can occur in the client-side code as well, not just in the network call
- Client side degradation is managed through a local thread pool that can reject incoming calls once full: *shedding load*
- Server side failures are managed through *circuit breaker*, *health tracking*, *timeouts*. 
- Server side degradation is managed indirectly through *shedding load* too.
	- It doesn't matter if a remote call is slow to respond or the local client is overloaded.

### Design implementation: convert sync remote calls to async
- Built around making async "command" wrappers on top of arbitrary sync call
- It is assumed that within the "command" block the user makes a remote call via an arbitrary client library. E.,g.: http, grpc, soap
- *NOTE:* Similar to the `AsyncService` wrapping an RSA endpoint
- *NOTE:* This can be used to gain resilience in other parts of the system. E.g. local disk access. 

### Design implementation: programming model
- Each "command" provides an `rx.Observlable` back to the caller
- `rx.Observable`: [HystrinxCommand.observe()](http://netflix.github.io/Hystrix/javadoc/com/netflix/hystrix/HystrixCommand.html#observe--)
	- *NOTE:* Similar to a `PushStream`
- `java.util.concurrent.Future`: [HystrixCommand.queue()](http://netflix.github.io/Hystrix/javadoc/com/netflix/hystrix/HystrixCommand.html#queue--)
	- In reality backed by the `rx.Observable`
	- *NOTE:* Similar to a `Promise`. Can convert back to sync, via `Promise.getValue()`
- **Question:** When does a command return multiple results to the `rx.Observable`?
	- It seems the user can design a command that either emits one value or many

### Design implementation: hook alternative behavior
- User overrides methods on `HystrixCommand`
- `HystrixCommand.getFallback()`: returns one value immediately
	- *NOTE:* Similar to `Promise.fallbackTo(Promise fallback)`
- `HystrixCommand.resumeWithFallback()`: returns an `rx.Observable` which emits one or more values.
	- *NOTE:* Similar to `Promise.recoverWith(Function<Promise, Promise> recovery)`
- *NOTE:* Uniquely OSGi can also hook lifecycle changes as a fallback behavior
	- E.g. RSA detects all endpoints are unhealthy or missing: take down service rather than `circuit breaker`

### Design implementation: discovery
- Ribbon has pluggable discovery
- *NOTE:* RSA has pluggable discovery too: e.g. call K8S master for the pods, rather than zookeeper
	
### Design implementation: load balancing
- Hidden inside the Hystrix impl is Ribbon that does the load balancing, maybe health monitoring, etc.
- *NOTE:* OSGi invisible to app: RSA can do internal load balancing behind one service endpoint
- *NOTE:* OSGi visible to app: RSA can provide all endpoints and app can create a load balancing wrapper
	- Similarly to how `AsyncService` is applied to an existing service.
	- Maybe even an extension to `AsyncService`
	- Maybe extensions to RSA to decorate endpoints with metrics so the extended `AsyncService` can make decisions.

### Design implementation: threading
- Each command has it's own thread pool
- If thread pool is exhausted "load shedding" is applied: commands are failed immediately.
- *NOTE:* Similar to a `PushStream` impl or a `Promise` impl with it's own executor
- **Question:** A command is a one-shot thing while the thread pool must remain. How are they hooked?

### Design implementation: status/monitoring
- `HystrixCommand` is stuffed with informative methods
	- E.g. why a certain result happened: circuit breaking, load shedding, timeout, local cache hit, called through to remote side
	- E.g. log of the circuit breaker
- *NOTE:* The best OSGi has is `Promise.isDone()`, `Promise.getFailure()` (with special exceptions?)
- *NOTE:* It is not clear how much of that API is for the application and how much for other tools.
