package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.osgi.cdi.examples.ApplicationFunctionTarget;
import org.osgi.service.cdi.annotations.Filter;
import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean which has a static, mandatory, reluctant @Reference.
 *
 * Part of the Application Component, with PIDS = $
 *
 * It also declares properties which define the target filter of the reference.
 *
 * The precedence of the target filter property is:
 * 1. @Reference annotation's "target" member                                                   (foo=bar)
 * 2. @ApplicationFunctionTarget qualifier, which matches once converted using converter rules  (foo=baz)
 * 3. because this bean is considered part of the "Application Component", the
 *    configuration admin objects available match the PIDs, and their order, as
 *    defined in the "osgi.cdi" requirement, all of which are optional.
 *
 * Note that the "name" of the target property must match the Reference's name as
 * specified by the @Named annotation, which is required.
 *
 * Graph:
 *
 *    @ApplicationScoped
 *    E4
 *      \
 *       @ApplicationScoped
 *       @Reference | name = application.function | static | mandatory | reluctant
 *       Function<String, Integer>
 */
@ApplicationScoped
@ApplicationFunctionTarget("(foo=baz)")
public class E4 {

	// constructor
	@Inject
	public E4(
		@Named("application.function")
		@Filter("(foo=bar)")
		@Reference
		Function<String, Integer> function) {}

	// OR

	// method
	@Inject
	public void set(
		@Named("application.function")
		@Filter("(foo=bar)")
		@Reference
		Function<String, Integer> function) {}

	// OR

	// field
	@Inject
	@Named("application.function")
	@Filter("(foo=bar)")
	@Reference
	Function<String, Integer> function;

}
