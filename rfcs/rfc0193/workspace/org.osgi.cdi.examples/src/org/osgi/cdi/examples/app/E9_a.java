package org.osgi.cdi.examples.app;

import java.util.Optional;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
public class E9_a {

	@Inject
	@Reference(name = "application.function")
	Optional<Function<String, Integer>> function;

}
