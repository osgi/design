package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Bundle;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references, forcing bundle scoped service
 */

@Component
public class CE7 {

	void observeFoos(@Observes @Reference(service = Foo.class) @Bundle ReferenceEvent event) {
		event.adding((ReferenceServiceObjects<Foo> foo) -> new Holder<>(foo));
	}

}
