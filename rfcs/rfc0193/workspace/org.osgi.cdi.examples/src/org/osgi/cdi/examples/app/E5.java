package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/*
 * A plain session scoped bean.
 *
 * Part of the Application Component, with PIDS = $
 *
 * Note that the only provider of
 * Function<String, Integer> is the ABeanWithReference bean, which
 * defines a producer method for that type.
 *
 * Graph:
 *
 *    @SessionScoped
 *    E5
 *     |\
 *     | @SessionScoped
 *     | HttpSession
 *     |
 *     |\
 *     | @ApplicationScoped
 *     | E1
 *     |
 *      \
 *       @SessionScoped
 *       E6
 *         \
 *          @ApplicationScoped
 *          @Reference | name = session.function | static | mandatory | reluctant | prototype
 *          Function<String, Integer> function
 */

@SessionScoped
public class E5 {

	@Inject
	HttpSession httpSession;

	@Inject
	E1 e1;

	@Inject
	Function<String, Integer> function;

}
