package org.osgi.cdi.examples.app;

import java.util.function.Function;

import javax.enterprise.context.ApplicationScoped;

import org.osgi.service.cdi.annotations.Service;

/*
 * An application scoped bean which provides a service with the
 * interface {@code Function<String, Long>}.
 */

@ApplicationScoped
public class E11 implements @Service Function<String, Long> {

	@Override
	public Long apply(String t) {
		return new Long(t.length());
	}


}
