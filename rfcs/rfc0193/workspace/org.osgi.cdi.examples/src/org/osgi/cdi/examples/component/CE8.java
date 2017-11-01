package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references, forcing prototype scoped service
 */

@Component
public class CE8 {

	void observeFoos(@Observes @Reference @Prototype ReferenceEvent<Foo> event) {
		ReferenceServiceObjects<Foo> serviceObjects = event.getServiceObjects();
	}

}
