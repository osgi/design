package org.osgi.cdi.examples.app;

import javax.enterprise.context.ApplicationScoped;

/*
 * The OSGi CDI integration makes a distinction between all
 * traditional beans (including any beans belonging to custom
 * scopes) and beans which are scoped @ComponentScope. Traditional
 * beans are defined as being part of the "Application Component".
 * The implication being that any OSGi dependencies defined at the
 * "Application Component" level are shared across all such beans.
 * It also means that those dependencies affect the CDI container
 * in a transversal way such as a static greedy @Reference causing
 * the entire container to be destroyed and recreated when a better
 * service is bound or when a configuration change occurs.
 *
 * A typical @ApplicationScoped bean.
 *
 * Part of the Application Component, with PIDS = $
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
