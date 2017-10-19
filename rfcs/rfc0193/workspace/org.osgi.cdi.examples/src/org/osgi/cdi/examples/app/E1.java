package org.osgi.cdi.examples.app;

import javax.enterprise.context.ApplicationScoped;

/*
 * A typical @ApplicationScoped bean, visible throughout the application.
 *
 * Part of the "Application Component" with PIDS = $
 *
 * Graph:
 *
 *     @ApplicationScoped
 *     E1
 */

@ApplicationScoped
public class E1 {

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	private volatile String foo;

}
