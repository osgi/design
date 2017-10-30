package org.osgi.cdi.examples.component;

import javax.inject.Inject;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with mandatory, static, reluctant reference.
 */

@Component
public class CR1 {

	@Inject
	public CR1(@Reference Foo foo) {
		// stub
	}

	@Inject
	void foo(@Reference Foo foo) {
		// stub
	}

	@Inject
	@Reference
	Foo foo;

}
