package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.cdi.examples.SomeQualifer;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.FactoryPID;
import org.osgi.service.cdi.annotations.PID;

/*
 * A component with specified configuration PIDs and Factory PID.
 *
 * Part of the Component c2, with PIDS = [com.foo, $, com.factory]
 *
 * The component specifies a factory pid "com.factory" which means
 * that for every configuration factory instance of "com.factory" a
 * new context c2[x] is created where x is the factory pid.
 *
 * Since there's a producer method for Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in each context c2[x].
 *
 * Note that the configuration injected in the instance of CF is based
 * on those available in the context c2[x] (a.k.a. PIDS = [com.foo, $, com.factory-x])
 *
 * Graph:
 *
 *    @ComponentScoped
 *    @Component | name = c2 | pids [com.foo, $, com.factory-x]
 *    C1
 *      \
 *       @ComponentScoped
 *       @SomeQualifer("one")
 *       CFR
 *          \
 *           @ComponentScoped
 *           @Configuration
 *           Config
 */

@Component
@PID("com.foo")
@PID // same as @PID(Component.NAME)
@FactoryPID("com.factory")
public class C3 {

	@Inject
	@SomeQualifer("one")
	Function<String, Integer> function;

}
