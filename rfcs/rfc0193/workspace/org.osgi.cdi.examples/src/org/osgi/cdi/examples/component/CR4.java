package org.osgi.cdi.examples.component;

import javax.inject.Inject;
import javax.inject.Provider;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with mandatory, dynamic, greedy reference.
 */

@Component
public class CR4 {

	@Inject
	public CR4(@Greedy @Reference Provider<Foo> foo) {
		// stub
	}

	@Inject
	void foo(@Greedy @Reference Provider<Foo> foo) {
		// stub
	}

	@Inject
	@Greedy
	@Reference
	Provider<Foo> foo;

}
