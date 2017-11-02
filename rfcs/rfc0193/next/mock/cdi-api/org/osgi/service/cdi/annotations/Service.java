/*
 * Copyright (c) OSGi Alliance (2017). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.osgi.service.cdi.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.osgi.namespace.extender.ExtenderNamespace.EXTENDER_NAMESPACE;
import static org.osgi.service.cdi.CdiConstants.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.util.AnnotationLiteral;
import org.osgi.annotation.bundle.Requirement;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceFactory;

/**
 * Annotation used to specify that a CDI bean should be published as a service.
 * <p>
 * The behaviour of this annotation depends on it's usage:
 * <ul>
 * <li>on the bean type - publish the service using all implemented interfaces.
 * If there are no implemented interfaces use the bean class.</li>
 * <li>on the bean's type_use(s) - publish the service using the collected
 * interface(s).</li>
 * </ul>
 * Use of {@code @Service} on both type and type_use will result in a definition
 * error.
 * <p>
 * Where this annotation is used affects how service scopes are supported:
 * <li>{@link Component} or {@link Dependent} bean - The provided service can be
 * of any scope. The bean can either implement {@link ServiceFactory} or
 * {@link PrototypeServiceFactory} or use {@link Bundle} or {@link Prototype} to
 * set it's service scope. If none of those options are used the service is a
 * singleton scope service.</li>
 * <li>{@link ApplicationScoped} bean - The provided service is a singleton
 * scope service unless it implements {@link ServiceFactory} or
 * {@link PrototypeServiceFactory}. It cannot use {@link Bundle} or
 * {@link Prototype} to set it's service scope. Use of those annotations will
 * result in a definition error.</li>
 * </ul>
 *
 * @author $Id$
 */
@Documented
@Requirement(namespace = EXTENDER_NAMESPACE, name = CDI_CAPABILITY_NAME, version = CDI_SPECIFICATION_VERSION)
@Retention(RUNTIME)
@Target({TYPE, TYPE_USE})
public @interface Service {

	/**
	 * Support inline instantiation of the {@link Service} annotation.
	 */
	public static final class Literal extends AnnotationLiteral<Service> implements Service {

		/**
		 * Default instance
		 */
		public static final Service	INSTANCE			= new Literal();

		private static final long	serialVersionUID	= 1L;

	}

}
