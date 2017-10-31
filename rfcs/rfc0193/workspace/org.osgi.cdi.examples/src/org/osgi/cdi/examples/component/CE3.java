package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceEventCustomizer;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, reluctant references
 */

@Component
public class CE3 {

	void observeWithCustomizer(@Observes @Reference ReferenceEvent<ServiceReference<Foo>> event) {
		event.dispatch(new ReferenceEventCustomizer<ServiceReference<Foo>, Holder<ServiceReference<Foo>>>() {

			@Override
			public Holder<ServiceReference<Foo>> adding(ServiceReference<Foo> foo) {
				return new Holder<>(foo);
			}

			@Override
			public void modified(ServiceReference<Foo> foo, Holder<ServiceReference<Foo>> h) {
				// stub
			}

			@Override
			public void removed(ServiceReference<Foo> foo, Holder<ServiceReference<Foo>> h) {
				// stub
			}

		});
	}

	void observeWithCallbacks(@Observes @Reference ReferenceEvent<ServiceReference<Foo>> event) {
		event.dispatch(foo -> new Holder<>(foo), (foo,h) -> {}, (foo,h) -> {});
	}

}
