package org.osgi.cdi.examples.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE1 {

	void observeFoos(@Observes @Reference ReferenceEvent<Foo> event) {
		Foo foo = event.getService();
		Map<String, ?> serviceProperties = event.getServiceProperties();

		foos.put(foo, serviceProperties);

		event.onUpdate(() -> foos.replace(foo, event.getServiceProperties()));
		event.onRemove(() -> foos.remove(foo));
	}

	private final Map<Foo, Map<String, ?>> foos = new ConcurrentHashMap<>();

}
