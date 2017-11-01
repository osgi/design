package org.osgi.cdi.examples.component;

import java.util.Map;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE11 {

	void observeFoos(@Observes @Reference ReferenceEvent<Foo> event) {
		Foo foo = event.getService();
		Map<String, ?> serviceProperties = event.getServiceProperties();
	}

}
