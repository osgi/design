# RFP-191: OSGi's future role in distributed systems 

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
			- I.e. OSGi is part of the control plane
		- Extend with bottom-up API for OSGi to CaaS to pull/push performance/discovery data.
			- I.e. OSGi is part of the distributed application
- Adapt to "brute force" approach (without RSA?)
	- Explore if/how this reduces the value of the service layer
		- E.g. service dynamics do not matter: containers are static, remote endpoints too.
		- E.g. but service dynamics still free you from doing the start/stop order of modules manually
		- E.g. but service layer is still a good abstraction to hook modules together
- Adapt to https://opentracing.io/
- Integrate with modern distributed ecosystem
	- *Impl:* Not clear if there is anything to specify
		- These are moving targets rather than long-lived standards, we may specify SPIs to plug them into RSA...
	- RSA discovery for Consul, Eureka, .. K8S API server?
		- **Question** Is it worth doing this or just give up since sidecar loadbalancing (e.g. Envoy) seems to take over?
			- Sidecar load balancing does not propagate cluster topology changes back to app - no distributed lifecycle.
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
