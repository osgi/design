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

/**
 * Description of a CDI configuration factory dependency.
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ComponentFactoryDTO extends DTO {
    /**
     * The component name
     */
    public String name;

    /**
     * A most one configuration with multiple cardinality.
     * Many configurations with singleton cardinality.
     * 
     * There is always at lest one (default) singleton configuration.
     */
    public ConfigurationDTO[] configurations;

    /**
     *
     */
    public ComponentDTO[] instances;
}
