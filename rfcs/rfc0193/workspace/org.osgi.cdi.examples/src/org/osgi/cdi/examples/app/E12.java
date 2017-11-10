package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.framework.ServiceReference;
import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean with a service dependency. The view is ServiceReference.
 */

@ApplicationScoped
public class E12 {

	@Inject
	@Reference
	ServiceReference<Function<String, Integer>> function;

}
