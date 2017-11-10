package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Bundle;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.reference.AddingEvent;

/*
 * Examples of multiple, dynamic, reluctant references, forcing bundle scoped service
 */

@Component
public class CE7 {

	void observeFoos(@Observes @Bundle AddingEvent<Foo> event) {
		ReferenceServiceObjects<Foo> serviceObjects = event.getServiceObjects();
	}

}
