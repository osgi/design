package org.osgi.cdi.examples.component;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with optional, dynamic, greedy reference.
 */

@Component
public class CR8 {

	@Inject
	public CR8(@Greedy @Reference Provider<Optional<Foo>> foo) {
		// stub
	}

	@Inject
	void foo(@Greedy @Reference Provider<Optional<Foo>> foo) {
		// stub
	}

	@Inject
	@Reference
	@Greedy
	Provider<Optional<Foo>> foo;

}
