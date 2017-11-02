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

package org.osgi.service.cdi.dto;

import java.util.Map;

import org.osgi.dto.DTO;
import org.osgi.framework.dto.ServiceReferenceDTO;

/**
 * Description of a CDI component.
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ComponentDTO extends DTO {

    /**
     * Define the possible values for {@link #scope}.
     */
    public enum Type {
        /**
         * The component is the <em>Application Component</em>.
         */
        APPLICATION,
        /**
         * The component is an <em>OSGi Component</em>.
         */
        COMPONENT
    }

    /**
     * Indicate whether the component is the <em>Application Component</em> or an
     * <em>OSGi Component<em>.
     */
    public Type componentType;

    /**
     * The bean class of the component.
     * <p>
     * In the case of the <em>Application Component</em>, the value is null.
     */
    public String beanClass;

    /**
     * The service dependencies of the component.
     * <p>
     * Value must not be null. The array will be empty if there are no service
     * dependencies.
     */
    public ReferenceDTO[] references;

    /**
     * The component properties.
     * <p>
     * These are the aggregated properties from all configuration sources. This
     * value must never be null.
     */
    public Map<String, Object> properties;

    /**
     * The services published from this component.
     * <p>
     * The value must not be null. The array will be empty if there are no services
     * published by this component.
     */
    public ServiceReferenceDTO[] services;
}
