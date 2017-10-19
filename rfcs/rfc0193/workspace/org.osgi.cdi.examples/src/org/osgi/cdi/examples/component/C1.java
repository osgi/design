package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Component;

/*
 * A simple component bean.
 *
 * Part of the "OSGi Component", context name = c1, with PIDS = c1
 *
 * Since there's a producer method for Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in context "c1".
 *
 * Graph:
 *
 *    @ComponentScoped
 *    @Component | name = c1
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
public class C1 {

	@Inject
	Function<String, Integer> function;

}
