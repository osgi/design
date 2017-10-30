package org.osgi.cdi.examples.component;

import java.util.function.Function;

import org.osgi.service.cdi.annotations.Bundle;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a bundle scoped service using all directly
 * implemented interfaces.
 */

@Component
@Service
@Bundle
public class C10 implements Comparable<C10>, Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length();
	}

	@Override
	public int compareTo(C10 o) {
		return 0;
	}

}
