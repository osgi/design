package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Property;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a prototype scoped service using all directly
 * implemented interfaces with properties.
 */

@Component
@Service
@Prototype
@Property("foo=bar")
public class C11 implements Comparable<C11>, Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

	@Override
	public int compareTo(C11 o) {
		return 0;
	}

}
