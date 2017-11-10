package org.osgi.cdi.examples.app;

import java.util.Map;
import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;

/*
 * An application scoped bean with a service dependency. The view is a Map of service properties.
 */

@ApplicationScoped
public class E14 {

	@Inject
	@Reference(Function.class)
	Map<String, Object> properties;

}
