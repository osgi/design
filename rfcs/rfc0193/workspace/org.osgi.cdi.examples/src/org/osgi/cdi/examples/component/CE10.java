package org.osgi.cdi.examples.component;

import java.util.Map;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Holder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceEventCustomizer;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, greedy references
 */

@Component
public class CE10 {

	void observeWithCustomizer(@Observes @Greedy @Reference ReferenceEvent<Map<String, ?>> event) {
		event.dispatch(new ReferenceEventCustomizer<Map<String, ?>, Holder<Map<String, ?>>>() {

			@Override
			public Holder<Map<String, ?>> adding(Map<String, ?> foo) {
				return new Holder<>(foo);
			}

			@Override
			public void modified(Map<String, ?> foo, Holder<Map<String, ?>> h) {
				// stub
			}

			@Override
			public void removed(Map<String, ?> foo, Holder<Map<String, ?>> h) {
				// stub
			}

		});
	}

	void observeWithCallbacks(@Observes @Greedy @Reference ReferenceEvent<Map<String, ?>> event) {
		event.dispatch(foo -> new Holder<>(foo), (foo,h) -> {}, (foo,h) -> {});
	}

}
