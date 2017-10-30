package org.osgi.cdi.examples.component;

import java.util.function.Function;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.FactoryPID;
import org.osgi.service.cdi.annotations.PID;
import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.Service;

/*
 * A factory component with specified configuration PIDs and Factory PID which
 * produces a singleton scoped service for each configuration instance
 *
 * Part of the Component c5, with PIDS = [$, com.factory]
 *
 * The component specifies a factory pid "com.factory" which means
 * that for every configuration factory instance of "com.factory" a
 * new context c5[x] is created where x is the factory pid.
 *
 * Since there's a bean of type Function<String, Integer> in
 * @ComponentScoped (i.e. CF) an instance is created in each context "c5[x]".
 *
 * Note that the configuration injected in the instance of CF is based
 * on those available in the context c5[x] (a.k.a. PIDS = [$, com.factory-x]).
 * The resulting configuration properties are available as service properties.
 *
 * Graph:
 *
 *    @ComponentScoped
 *    @Component | name = c5 | pids [$, com.factory-x]
 *    C1
 *      \
 *       @ComponentScoped
 *       @Reference | name = "${FQCN}.function" | static | mandatory | reluctant
 *       Function<String, Integer> function
 */

@Component
@PID
@FactoryPID("com.factory")
@Service
public class C5 {

	@Inject
	@Reference
	Function<String, Integer> function;

}
