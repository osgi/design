package org.osgi.cdi.examples.app;

import java.util.Map;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean with a service dependency. The view is a Map.Entry tuple of the properties and the service.
 */

@ApplicationScoped
public class E15 {

	@Inject
	@Reference
	Map.Entry<Map<String, Object>, Function<String, Integer>> function;

}
