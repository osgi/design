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
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Annotation used in conjunction with {@link ComponentScoped} in order to
 * associate configurations with the component bean.
 *
 * @author $Id$
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
public @interface PIDs {

	/**
	 * Support inline instantiation of the {@link PIDs} annotation.
	 */
	public static final class Literal extends AnnotationLiteral<PIDs> implements PIDs {

		private static final long serialVersionUID = 1L;

		/**
		 * @param pids array of {@link PID}
		 * @return an instance of {@link PIDs}
		 */
		public static PIDs of(PID[] pids) {
			return new Literal(pids);
		}

		private Literal(PID[] pids) {
			_pids = pids;
		}

		@Override
		public PID[] value() {
			return _pids;
		}

		private final PID[] _pids;

	}

	/**
	 * The set of ordered configurations available to the component.
	 */
	PID[] value();

}
