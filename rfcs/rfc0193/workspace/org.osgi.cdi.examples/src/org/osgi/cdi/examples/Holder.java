package org.osgi.cdi.examples;

public class Holder<T> {

	public Holder(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	private final T t;

}