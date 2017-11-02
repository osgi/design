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

package org.osgi.service.cdi;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Defines CDI constants.
 *
 * @author $Id$
 */
@ProviderType
public class CdiConstants {
	private CdiConstants() {
		// non-instantiable
	}

	/**
	 * Capability name for CDI Integration.
	 * <p>
	 * Used in {@code Provide-Capability} and {@code Require-Capability}
	 * manifest headers with the {@code osgi.extender} namespace. For example:
	 *
	 * <pre>
	 * Require-Capability: osgi.extender;
	 *  filter:="(&amp;(osgi.extender=osgi.cdi)(version&gt;=1.0)(!(version&gt;=2.0)))"
	 * </pre>
	 */
	public static final String	CDI_CAPABILITY_NAME					= "osgi.cdi";

	/**
	 * The property of the CdiContainer service holding it's state.
	 * <p>
	 * The property will be updated every time the CdiContainer's state changes. The
	 * possible values are defined by
	 * {@link org.osgi.service.cdi.runtime.CdiContainerState}.
	 */
	public static final String	CDI_CONTAINER_STATE		= "osgi.cdi.container.state";

	/**
	 * Namespace name for CDI extension capabilities and requirements.
	 * <p>
	 * Used in {@code Provide-Capability} and {@code Require-Capability}
	 * manifest headers. For example:
	 *
	 * <pre>
	 * Require-Capability: osgi.cdi.extension;
	 *  filter:="(&amp;(osgi.cdi.extension=foo)(version&gt;=1.0)(!(version&gt;=2.0)))"
	 * </pre>
	 */
	public static final String	CDI_EXTENSION_NAMESPACE				= "osgi.cdi.extension";

	/**
	 * Compile time constant for the Specification Version of CDI Integration.
	 * <p>
	 * Used in {@code Version} and {@code Requirement} annotations. The value of
	 * this compile time constant will change when the specification version of
	 * CDI Integration is updated.
	 */
	public static final String	CDI_SPECIFICATION_VERSION			= "1.0.0";

	/**
	 * The 'beans' attribute on the CDI extender requirement.
	 * <p>
	 * The value of this attribute is a comma delimited list of bean CDI bean
	 * descriptor files to be searched on the {@code Bundle-ClassPath}. For
	 * example:
	 *
	 * <pre>
	 * Require-Capability: osgi.extender;\
	 *  filter:="(&amp;(osgi.extender=osgi.cdi)(version&gt;=1.0)(!(version&gt;=2.0)))";\
	 *  beans:List&lt;String&gt;="META-INF/beans.xml"
	 * </pre>
	 */
	public static final String	REQUIREMENT_BEANS_ATTRIBUTE			= "beans";

	/**
	 * The 'osgi.beans' attribute on the CDI extender requirement.
	 * <p>
	 * The value of this attribute is the name of the OSGi Beans Description
	 * file. The default value when unspecified is
	 * {@code OSGI-INF/cdi/osgi-beans.xml}. For example:
	 *
	 * <pre>
	 * Require-Capability: osgi.extender;\
	 *  filter:="(&amp;(osgi.extender=osgi.cdi)(version&gt;=1.0)(!(version&gt;=2.0)))";\
	 *  osgi.beans="OSGI-INF/cdi/osgi-beans.xml"
	 * </pre>
	 */
	public static final String	REQUIREMENT_OSGI_BEANS_ATTRIBUTE	= "osgi.beans";

}
