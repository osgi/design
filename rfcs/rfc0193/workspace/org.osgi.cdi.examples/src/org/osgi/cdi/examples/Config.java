package org.osgi.cdi.examples;

public @interface Config {
	String[] foo() default {};

	int factor() default 0;
}