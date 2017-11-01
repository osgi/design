package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Bundle;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a bundle scoped service using only the
 * interface Function<String, Integer>
 */

@Bundle
@Component
public class C8 implements @Service Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

}
