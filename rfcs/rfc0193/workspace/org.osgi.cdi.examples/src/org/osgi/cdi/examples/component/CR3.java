package org.osgi.cdi.examples.component;

import javax.inject.Inject;
import javax.inject.Provider;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with mandatory, dynamic, reluctant reference.
 */

@Component
public class CR3 {

	@Inject
	@Reference
	Provider<Foo> foo;

}
