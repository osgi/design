package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a prototype scoped service using all directly
 * implemented interfaces.
 */

@Component
@Service
@Prototype
public class C9 implements Comparable<C9>, Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

	@Override
	public int compareTo(C9 o) {
		return 0;
	}

}
