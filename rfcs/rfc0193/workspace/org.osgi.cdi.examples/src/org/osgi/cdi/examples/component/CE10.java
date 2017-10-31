package org.osgi.cdi.examples.component;

import java.util.Map;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, greedy references
 */

@Component
public class CE10 {

	void observeFoos(@Observes @Reference(service = Foo.class) @Greedy ReferenceEvent event) {
		event.adding((Map<String, ?> foo) -> new Holder<>(foo));
	}

}
