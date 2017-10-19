package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.ReferenceScope;

/*
 * This is a bean which defines a producer method.
 *
 * Part of the "Application Component" with PIDS = $
 *
 * Note that the parameters of @Producer methods are all injection points.
 *
 * Here we've used a @Reference to indicate the need for a service
 * of prototype scope.
 *
 * This construct will allow a distinct function instance visible per session.
 *
 * Finally, note that the target filter of the reference is configurable using
 * the property "session.function.target" derived again from the reference's
 * name.
 *
 * Graph:
 *
 *    @SessionScoped
 *    E6
 *      \
 *       @ApplicationScoped
 *       @Reference | name = session.function | session.function.target = "" | service.scope = prototype
 *       Function<String, Integer> function
 */

public class E6 {

	@Produces
	@SessionScoped
	public Function<String, Integer> getFunction(
		@Named("session.function")
		@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
		Function<String, Integer> function) {

		return function;
	}

}
