package org.osgi.cdi.examples.component;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with optional, dynamic, reluctant reference.
 */

@Component
public class CR7 {

	@Inject
	public CR7(@Reference Provider<Optional<Foo>> foo) {
		// stub
	}

	@Inject
	void foo(@Reference Provider<Optional<Foo>> foo) {
		// stub
	}

	@Inject
	@Reference
	Provider<Optional<Foo>> foo;

}
