package org.osgi.cdi.examples.component;

import javax.inject.Inject;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * A component with mandatory, static, greedy reference.
 */

@Component
public class CR2 {

	@Inject
	@Reference
	@Greedy
	Foo foo;

}
