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
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Annotation used in collaboration with {@link ComponentScoped} to specify
 * singleton configurations and their policy.
 *
 * @author $Id$
 */
@Documented
@Repeatable(PIDs.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
public @interface PID {

	/**
	 * Support inline instantiation of the {@link PID}
	 * annotation.
	 */
	public static final class Literal extends AnnotationLiteral<PID> implements PID {

		private static final long serialVersionUID = 1L;

		/**
		 * @param pid the configuration pid
		 * @param required true if the configuration is required
		 * @return an instance of {@link PID}
		 */
		public static final Literal of(String pid, boolean required) {
			return new Literal(pid, required);
		}

		private Literal(String pid, boolean required) {
			_pid = pid;
			_required = required;
		}

		@Override
		public String value() {
			return _pid;
		}

		@Override
		public boolean required() {
			return _required;
		}

		private final String _pid;
		private final boolean	_required;

	}

	/**
	 * The configuration PID for the configuration of this Component.
	 *
	 * <p>
	 * The value specifies a configuration PID who's configuration properties are
	 * available at injection points in the component.
	 *
	 * <p>
	 * A special string (<code>"$"</code>) can be used to specify the name of the
	 * component as a configuration PID. The {@link Component#NAME} constant holds
	 * this special string. For example:
	 *
	 * <pre>
	 * &#64;SingletonConfiguration(pid = Component.NAME)
	 * </pre>
	 */
	String value() default Component.NAME;

	/**
	 * The configuration policy associated with this PID.
	 *
	 * <p>
	 * Controls how the configuration must be satisfied depending on the presence
	 * and type of a corresponding Configuration object in the OSGi Configuration
	 * Admin service. Corresponding configuration is a Configuration object where
	 * the PID is equal to {@link PID#value() value}.
	 *
	 * <p>
	 * If not specified, the configuration is not required.
	 */
	boolean required() default false;

}
