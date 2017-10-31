package org.osgi.cdi.examples.app;

import java.util.Optional;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean which has a static, optional @Reference.
 *
 * Part of the Application Component, with PIDS = $
 *
 * Graph:
 *
 *    @ApplicationScoped
 *    E4
 *      \
 *       @ApplicationScoped
 *       @Reference | name = application.function | static | optional | reluctant
 *       Function<String, Integer>
 */
@ApplicationScoped
public class E9 {

	// constructor
	@Inject
	public E9(
		@Named("application.function")
		@Reference
		Optional<Function<String, Integer>> function) {}

	// OR

	// method
	@Inject
	public void set(
		@Named("application.function")
		@Reference
		Optional<Function<String, Integer>> function) {}

	// OR

	// field
	@Inject
	@Named("application.function")
	@Reference
	Optional<Function<String, Integer>> function;

}
