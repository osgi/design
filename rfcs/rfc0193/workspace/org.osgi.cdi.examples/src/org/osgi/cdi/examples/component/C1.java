package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Component;

/*
 * An OSGi Component bean (henceforth simply called a "component") uses the
 * @Component stereotype annotation to declare itself. This stereotype
 * defines a CDI scope of @ComponentScoped and @Named to ensure that
 * the bean has a name. The name should be unique across all OSGi components
 * in the bundle. Ideally it is globally unique because the name is used as
 * the default PID of the component.
 *
 * Part of Component "c1", with PIDS = c1
 *
 * Since there's a producer method for type Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in context "c1".
 *
 * Note that the configuration injected in the instance of CF is based
 * on those available in the context "c1" (a.k.a. PIDS = c1)
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
 */

@Component
public class C1 {

	@Inject
	Function<String, Integer> function;

}
