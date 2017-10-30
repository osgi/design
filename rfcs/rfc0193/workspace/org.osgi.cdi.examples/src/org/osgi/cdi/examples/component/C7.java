package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a prototype scoped service using only the
 * interface Function<String, Integer>
 */

@Component
public class C7 implements @Service @Prototype Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

}
