package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.reference.AddingEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, greedy references
 */

@Component
public class CE2 {

	void observeFoos(@Observes @Greedy @Reference AddingEvent<Foo> event) {
		Foo foo = event.getService();
	}

}
