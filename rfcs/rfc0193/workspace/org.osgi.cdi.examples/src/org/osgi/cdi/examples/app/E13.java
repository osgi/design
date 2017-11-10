package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean with a service dependency. The view is ReferenceServiceObjects.
 */

@ApplicationScoped
public class E13 {

	@Inject
	@Reference
	ReferenceServiceObjects<Function<String, Integer>> functions;

}
