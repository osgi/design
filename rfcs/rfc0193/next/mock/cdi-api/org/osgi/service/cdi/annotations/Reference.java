/*
 * Copyright (c) OSGi Alliance (2016, 2017). All Rights Reserved.
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
import javax.inject.Qualifier;
import org.osgi.annotation.bundle.Requirement;

/**
 * Annotation used to annotate a CDI injection point informing the CDI container
 * that the injection should apply a service obtained from the OSGi registry.
 * <p>
 * This annotation must be used in conjunction with {@link javax.inject.Named}
 * which must specify a value.
 *
 * @author $Id$
 */
@Documented
@Qualifier
@Requirement(namespace = EXTENDER_NAMESPACE, name = CDI_CAPABILITY_NAME, version = CDI_SPECIFICATION_VERSION)
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
public @interface Reference {

	/**
	 * Support inline instantiation of the {@link Reference} annotation.
	 */
	public static final class Literal extends AnnotationLiteral<Reference> implements Reference {

		private static final long serialVersionUID = 1L;

		/**
		 * @param service
		 * @param target
		 * @return instance of {@link Reference}
		 */
		public static final Literal of(
				Class<?> service,
				String target) {

			return new Literal(service, target);
		}

		private Literal(
				Class<?> service,
				String target) {
			_service = service;
			_target = target;
		}

		@Override
		public Class<?> service() {
			return _service;
		}

		@Override
		public String target() {
			return _target;
		}

		private final Class<?>				_service;
		private final String				_target;

	}

	/**
	 * The type of the service for this reference.
	 * <p>
	 * If not specified, the type of the service for this reference is based upon
	 * how this annotation is used:
	 * <ul>
	 * <li>Annotated field - TODO The type of the service is based upon the type of
	 * the field being annotated. The type of the field must be one of
	 * {@code java.util.Collection}, {@code java.util.List}, or a subtype of
	 * {@code java.util.Collection} so the type of the service is the generic type
	 * of the collection. Otherwise, the type of the service is the type of the
	 * field.</li>
	 * <li>Annotated constructor or method parameter - TODO The type of the service
	 * is based upon the type of the parameter being annotated. The type of the
	 * parameter must be one of {@code java.util.Collection},
	 * {@code java.util.List}, or a subtype of {@code java.util.Collection} so the
	 * type of the service is the generic type of the collection. Otherwise, the
	 * type of the service is the type of the parameter.</li>
	 * </ul>
	 */
	Class<?> service() default Object.class;

	/**
	 * The target property for this reference.
	 *
	 * <p>
	 * If not specified, no target property is set.
	 */
	String target() default "";

}
