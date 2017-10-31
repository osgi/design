package org.osgi.cdi.examples;

public class Holder<T> implements AutoCloseable {

	public Holder(T t) {
		this.t = t;
	}

	@Override
	public void close() throws Exception {
		// stub
	}

	private final T t;

}