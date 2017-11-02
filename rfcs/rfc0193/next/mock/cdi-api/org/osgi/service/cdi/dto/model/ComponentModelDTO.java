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

package org.osgi.service.cdi.dto.model;

import java.util.Map;

import org.osgi.dto.DTO;
import org.osgi.service.cdi.dto.ComponentDTO;
import org.osgi.service.cdi.dto.ComponentFactoryDTO;

/**
 * Description of a CDI component.
 *
 * At runtime it is spit between a {@link ComponentFactoryDTO} which handles the
 * resolution of the {@link #configurations} and the creation of
 * {@link ComponentDTO} instances and one or more {@link ComponentDTO}
 * instances, which handle the resolution of {@link #references} and the
 * creation of {@link #activations}.
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ComponentModelDTO extends DTO {

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
    public Type type;

    /**
     * The default component properties statically resolved from the metadata.
     * <p>
     * May be empty if no defaults were specified.
     */
    public Map<String, Object> properties;

    /**
     * The configuration dependencies of this component.
     * <p>
     * Value must not be null, since there is always at least one default singleton
     * configuration.
     * <p>
     * May contain at most one factory configuration.
     * <p>
     * May contain one or more singleton configurations.
     */
    public ConfigurationModelDTO[] configurations;

    /**
     * The service dependencies of the component.
     * <p>
     * Value must not be null. The array will be empty if there are no service
     * dependencies.
     */
    public ReferenceModelDTO[] references;

    /**
     * The activation beans of the component.
     * <p>
     * Value must not be null.
     * <p>
     * For {@link #type} = {@link Type#APPLICATION} the array may be empty.
     * <p>
     * For {@link #type} = {@link Type#APPLICATION} the array may contain multiple
     * entries where {@link ActivationModelDTO#serviceClasses} is not empty and
     * {@link ActivationModelDTO#scope} is {@link ActivationModelDTO.Scope#SINGLETON
     * SINGLETON}.
     * <p>
     * For {@link #type} = {@link Type#COMPONENT} the array must contain exactly one
     * entry.
     */
    public ActivationModelDTO[] activations;
}
