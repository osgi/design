package org.osgi.cdi.examples.app;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean which has a dynamic, mandatory @Reference.
 *
 * Part of the Application Component, with PIDS = $
 *
 * Graph:
 *
 *    @ApplicationScoped
 *    E4
 *      \
 *       @ApplicationScoped
 *       @Reference | name = application.function | dynamic | mandatory | reluctant
 *       Function<String, Integer>
 */
@ApplicationScoped
public class E10 {

	// constructor
	@Inject
	public E10(
		@Reference(name = "application.function")
		AtomicReference<Function<String, Integer>> function) {}

	// OR

	// method
	@Inject
	public void set(
		@Reference(name = "application.function")
		AtomicReference<Function<String, Integer>> function) {}

	// OR

	// field
	@Inject
	@Reference(name = "application.function")
	AtomicReference<Function<String, Integer>> function;

}
