package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE3 {

	void observeFoos(@Observes @Reference(service = Foo.class) ReferenceEvent event) {
		event.adding((ServiceReference<Foo> s) -> new Holder<>(s));
	}

}
