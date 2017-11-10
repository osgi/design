package org.osgi.cdi.examples.component;

import java.util.Map;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.reference.AddingEvent;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE9 {

	void observeFoos(@Observes AddingEvent<Foo> event) {
		Map<String, ?> serviceProperties = event.getServiceProperties();
	}

}
