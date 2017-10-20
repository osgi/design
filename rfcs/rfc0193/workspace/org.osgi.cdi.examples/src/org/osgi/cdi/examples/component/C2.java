package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.SingletonConfiguration;

/*
 * A component with specified configuration PID.
 *
 * Part of Component "c2", with PIDS = com.foo
 *
 * Since there's a producer method for Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in context "c2".
 *
 * Graph:
 *
 *    @ComponentScoped
 *    @Component | name = c2
 *    C1
 *      \
 *       @ComponentScoped
 *       CF
 *         \
 *          @ComponentScoped
 *          @Configuration
 *          Config
 *
 * Note that the configuration injected in the instance of CF is based
 * on those available in the context "c1" (a.k.a. PIDS = c1)
 */

@Component
@SingletonConfiguration(pid = "com.foo")
public class C2 {

	@Inject
	Function<String, Integer> function;

}
