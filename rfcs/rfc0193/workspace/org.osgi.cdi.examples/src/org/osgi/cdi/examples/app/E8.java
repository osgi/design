package org.osgi.cdi.examples.app;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.osgi.cdi.examples.Config;
import org.osgi.service.cdi.annotations.Configuration;
import org.osgi.service.cdi.annotations.Property;

/*
 * A simple request scoped bean with configuration.
 *
 * Part of the Application Component, with PIDS = $
 *
 * Note that a default for the property "foo" is set here via
 * the @Properties annotation. This could also have been done using
 * a custom @Qualifier annotation which follows the converter spec
 * rules.
 *
 * If no available configuration object for $, the value
 * returned from `config.foo()` should be "blurp".
 *
 * Graph:
 *
 *    @RequestScoped
 *    E8
 *      \
 *       @ApplicationScoped
 *       @Configuration | foo=blurp
 *       Config
 */

@RequestScoped
@Property("foo=blurp")
public class E8 {

	@Inject
	@Configuration
	Config config;

}
