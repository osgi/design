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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Scope;
import org.osgi.annotation.bundle.Requirement;

/**
 * This scope is used to define a bean as a CDI component. To trigger this it
 * must be used in conjunction with the {@link javax.inject.Named} annotation
 * which must specify a value.
 *
 * @author $Id$
 */
@Documented
@Inherited
@Requirement(namespace = EXTENDER_NAMESPACE, name = CDI_CAPABILITY_NAME, version = CDI_SPECIFICATION_VERSION)
@Retention(RUNTIME)
@Scope
@Target({FIELD, METHOD, TYPE})
public @interface ComponentScoped {

	/**
	 * Support inline instantiation of the {@link ComponentScoped} annotation.
	 */
	public static final class Literal extends AnnotationLiteral<ComponentScoped> implements ComponentScoped {

		/**
		 * Default instance.
		 */
		public static final ComponentScoped	INSTANCE			= new Literal();

		private static final long			serialVersionUID	= 1L;

	}

}
