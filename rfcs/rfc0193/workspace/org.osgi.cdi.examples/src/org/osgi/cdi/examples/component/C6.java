package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a singleton scoped service using only the
 * interface Function<String, Integer>
 */

@Component
public class C6 implements Comparable<C6>, @Service Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

	@Override
	public int compareTo(C6 o) {
		return 0;
	}

}
