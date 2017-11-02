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
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Qualifier;
import org.osgi.annotation.bundle.Requirement;

/**
 * Annotation used with {@link Inject} in order to have configuration properties
 * injected into CDI beans. Properties are a combination of CDI qualifiers,
 * component property types and properties provided through Configuration Admin
 * in association with the configuration PIDs defined by {@link PID} and
 * {@link FactoryPID} or in the case of non-components which were specified in
 * the requirement (which defaults to a PID named after the generated CDI
 * container id).
 *
 * @author $Id$
 */
@Documented
@Qualifier
@Requirement(namespace = EXTENDER_NAMESPACE, name = CDI_CAPABILITY_NAME, version = CDI_SPECIFICATION_VERSION)
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
public @interface Configuration {

	/**
	 * Support inline instantiation of the {@link Configuration} annotation.
	 */
	public static final class Literal extends AnnotationLiteral<Configuration> implements Configuration {

		/**
		 * Default instance.
		 */
		public static final Configuration	INSTANCE			= new Literal();

		private static final long			serialVersionUID	= 1L;

	}

}
