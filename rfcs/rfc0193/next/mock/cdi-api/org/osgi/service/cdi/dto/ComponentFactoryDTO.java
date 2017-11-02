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

import org.osgi.dto.DTO;
import org.osgi.service.cdi.dto.model.ComponentModelDTO;
import org.osgi.service.cdi.dto.model.DependencyModelDTO;

/**
 * Description of a CDI configuration factory. This is a pure runtime structure
 * (i.e. has no formal model) that manages the instances of a given component as
 * described by it's {@link ComponentModelDTO model}. Handles the
 * {@link ComponentModelDTO#configurations}
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ComponentFactoryDTO extends DTO {
    /**
     * Model of the components this factory creates
     */
    public ComponentModelDTO model;

    /**
     * The configuration dependencies.
     * <p>
     * Must never be null.
     * <p>
     * May contain many {@link DependencyModelDTO.MaximumCardinality#ONE singleton}
     * configurations.
     * <p>
     * May contain at most one {@link DependencyModelDTO.MaximumCardinality#MANY
     * factory} configurations.
     */
    public ConfigurationDTO[] configurations;

    /**
     * All instances created by this factory
     */
    public ComponentDTO[] instances;
}
