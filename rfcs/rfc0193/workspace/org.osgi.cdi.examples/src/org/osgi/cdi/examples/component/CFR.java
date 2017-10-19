package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.cdi.examples.Config;
import org.osgi.cdi.examples.SomeQualifer;
import org.osgi.service.cdi.annotations.ComponentScoped;
import org.osgi.service.cdi.annotations.Configuration;

@ComponentScoped
@SomeQualifer("one")
public class CFR implements Function<String, Integer> {

	@Override
	public Integer apply(String t) {
		return t.length() * config.factor();
	}

	@Inject
	@Configuration
	Config config;

}
