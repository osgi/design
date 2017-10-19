package org.osgi.cdi.examples.app;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Service;

/*
 * An application scoped bean which provides a service.
 *
 * Part of the "Application Component" with PIDS = $
 *
 * Note that only @ApplicationScoped and @Singleton beans can provide
 * services from the Application Component.
 *
 * Based on the proposed OSGi/CDI model, such beans can only provide
 * singleton scoped services. Any other selected scope value on
 * @Service will result in a definition error.
 *
 * Graph:
 *
 *    @ApplicationScoped
 *    E2
 *      \
 *       @ApplicationScoped
 *       E1
 */

@ApplicationScoped
@Service // Effectively the same as @Service(scope = ServiceScope.SINGLETON)
public class E2 {

	@Inject
	E1 e1;

}
