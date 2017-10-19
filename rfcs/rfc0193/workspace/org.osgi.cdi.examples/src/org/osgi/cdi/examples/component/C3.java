package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.cdi.examples.SomeQualifer;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.FactoryConfiguration;
import org.osgi.service.cdi.annotations.SingletonConfiguration;

/*
 * A component with specified configuration PIDs and Factory PID.
 *
 * Part of the "OSGi Component", context name = c2[x], with PIDS = [com.foo, $, com.factory]
 *
 * Since there's a producer method for Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in context "c2[x]".
 *
 * The name c2[x] implies that this is a factory scenario. This means
 * that for every factory configuration instance of "com.factory" a new
 * context of c2 is created based on the factory pid.
 *
 * Graph:
 *
 *    @ComponentScoped
 *    @Component | name = c2[x]
 *    C1
 *      \
 *       @ComponentScoped
 *       @SomeQualifer("one")
 *       CFR
 *          \
 *           @ComponentScoped
 *           @Configuration
 *           Config
 *
 * Note that the configuration injected in the instance of CF is based
 * on those available in the context "c2[x]" (a.k.a. PIDS = [com.foo, $, com.factory-x])
 */

@Component
@SingletonConfiguration(pid = "com.foo")
@SingletonConfiguration // same as @SingletonConfiguration(pid = Component.NAME)
@FactoryConfiguration(pid = "com.factory")
public class C3 {

	@Inject
	@SomeQualifer("one")
	Function<String, Integer> function;

}
