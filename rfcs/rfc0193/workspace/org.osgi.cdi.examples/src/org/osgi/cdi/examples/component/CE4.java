package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, greedy references
 */

@Component
public class CE4 {

	void observeFoos(@Observes @Greedy @Reference(service = Foo.class) ReferenceEvent event) {
		event.adding((ServiceReference<Foo> foo) -> new Holder<>(foo));
	}

}
