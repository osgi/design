package org.osgi.cdi.examples.component;

import java.util.Map;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceEventCustomizer;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE11 {

	void observeWithCustomizer(@Observes @Reference ReferenceEvent<Map.Entry<Map<String, ?>, Foo>> event) {
		event.dispatch(new ReferenceEventCustomizer<Map.Entry<Map<String, ?>, Foo>, Holder<Map.Entry<Map<String, ?>, Foo>>>() {

			@Override
			public Holder<Map.Entry<Map<String, ?>, Foo>> adding(Map.Entry<Map<String, ?>, Foo> foo) {
				return new Holder<>(foo);
			}

			@Override
			public void modified(Map.Entry<Map<String, ?>, Foo> foo, Holder<Map.Entry<Map<String, ?>, Foo>> h) {
				// stub
			}

			@Override
			public void removed(Map.Entry<Map<String, ?>, Foo> foo, Holder<Map.Entry<Map<String, ?>, Foo>> h) {
				// stub
			}

		});
	}

	void observeWithCallbacks(@Observes @Reference ReferenceEvent<Map.Entry<Map<String, ?>, Foo>> event) {
		event.dispatch(foo -> new Holder<>(foo), (foo,h) -> {}, (foo,h) -> {});
	}

}
