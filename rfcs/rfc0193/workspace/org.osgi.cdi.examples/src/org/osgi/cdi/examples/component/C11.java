package org.osgi.cdi.examples.component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Function;

import javax.inject.Qualifier;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Prototype;
import org.osgi.service.cdi.annotations.Service;

/*
 * A component which publishes a prototype scoped service using all directly
 * implemented interfaces with properties.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface Foo {
	String value();
}

@Component
@Service
@Prototype
@Foo("bar")
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
