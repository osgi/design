package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/*
 * A simple request scoped bean.
 *
 * Part of the "Application Component" with PIDS = $
 *
 * Note that since the only producer for Function<String, Integer>
 * beans is E6, which produces session scoped instances, every E7 instance
 * resulting from a request during a given session should see the same
 * instance of function, as would E5.
 *
 * Graph:
 *
 *    @RequestScoped
 *    E7
 *      \
 *       @SessionScoped
 *       E6
 *         \
 *          @ApplicationScoped
 *          @Reference | name = session.function | session.function.target = "" | service.scope = prototype
 *          Function<String, Integer> function
 */

@RequestScoped
public class E7 {

	@Inject
	Function<String, Integer> function;

}
