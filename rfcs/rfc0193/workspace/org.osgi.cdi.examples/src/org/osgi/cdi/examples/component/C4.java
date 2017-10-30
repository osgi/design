package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Service;

/*
 * Component which provides a singleton scoped service using interface Function<String, Integer>
 */

@Component
public class C4 implements @Service Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

}
