package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Reference;

/*
 * This is a bean which defines a producer method.
 *
 * Part of the Application Component, with PIDS = $
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
 *    @Dependent
 *    E6
 *      \
 *       @Produces
 *       @SessionScoped
 *       Function<String, Integer>
 *          \
 *           @ApplicationScoped
 *           @Reference | name = session.function | static | mandatory | reluctant | prototype
 *           ReferenceServiceObjects<Function<String, Integer>>
 */

public class E6 {

	@Produces
	@SessionScoped
	public Function<String, Integer> getFunction(
		@Reference(name = "session.function")
		@Prototype
		ReferenceServiceObjects<Function<String, Integer>> function) {

		return function.getService();
	}

}
