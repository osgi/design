package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Bundle;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Property;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a bundle scoped service using all directly
 * implemented interfaces with properties.
 */

@Component
@Service
@Bundle
@Property("foo=bar")
public class C12 implements Comparable<C12>, Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

	@Override
	public int compareTo(C12 o) {
		return 0;
	}

}
