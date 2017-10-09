# 1. Goals
- Describe the typical/least-surprising way CDI works 

# 2. Scopes and Contexts
- A Bean is
    - A factory for objects
    - Called Bean Instances
- A Scope is
    - A class of Contexts
    - A set of Beans
- A Context is
    - An instance of a Scope
    - A graph of bean instances
    - Created and destroyed together

# 3. Beans and Instances
- It is expected that a call to `Bean<T>.create()` will
    - return a new object `T`
    - or preserve referential transparency
        - E.g. return the same stateless singleton
- It is expected that Contexts
    - Are a layer on top of `Bean<T>.create()` that provides instance caching policy
    - I.e. `Bean<T>.create()` should not make caching decisions
- It is expected that extensions
    - Make requests for bean instances to `Context.get()`, rather than to `Bean<T>.create()`
    - Manage the storage of dependent instances

    // Example for Bean<T> instance management
    class ContextualFactory<T> {
        private final Map<T, CreationalContext<T>> dependents;
        private final Bean<T> bean;
        private final BeanManager manager;
        
        public ContextualFactory(Bean<T> bean, BeanManager manager) {
            this.bean = bean;
            this.manager = manager;
            this.dependents = new ConcurrentHashMap<>();
        }
        
        public T create() {
            CreationalContext<T> cctx = manager.createCreationalContext(bean);
            Context ctx = manager.getContext(bean.getScope());
            T instance = ctx.get(bean, cctx);
            dependents.put(instance, cctx);
            return instance;
        }
        
        public void destroy(T instance) {
            dependents.computeIfPresent(instance, (inst, cctx) -> {
                bean.destroy(inst, cctx);
                return null;
            });
        }
    }
    
# 4. Execution of operations
- The CDI container is a provider of business objects rather than an active entity
- Extensions mediate between the CDI container and the external world
- Execution sequence
    1. The extension intercepts a request from the external environment
    2a. The extension activates+binds contexts to the current thread
    2b. The extension creates+binds contexts to the current thread
        - Can seed some of the beans with instances from the external environment
        - E.g. during the creation of a context a `Bean<HttpServletRequest>` is setup to return the current `HttpServletRequest`.
    3. The extension asks CDI for instances from some contexts
    4. CDI lazy-creates and dependency-injects the instances
        - Reuses bean instances from the contexts bound to the current thread
    5. The extension calls the instances to perform the function
    6a. The extension unbinds+deactivates contexts from the current thread
    6b. The extension unbinds+destroys contexts from the current thread
        - There is no direct SPI support for the destruction of a context
        - The concrete implementation of Context must provide a method for the extension to call
