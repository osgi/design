package org.osgi.cdi.examples.component;

import java.util.Optional;

import javax.inject.Inject;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with optional, static, reluctant reference.
 */

@Component
public class CR5 {

	@Inject
	@Reference
	Optional<Foo> foo;

}
