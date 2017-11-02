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

/**
 * Description of a CDI component instance
 *
 * @NotThreadSafe
 * @author $Id$
 */
public class ComponentDTO extends DTO {
    /**
     * The service dependencies of the component.
     * <p>
     * Value must not be null. The array will be empty if there are no service
     * dependencies.
     */
    public ReferenceDTO[] references;

    /**
     * The service dependencies of the component.
     * <p>
     * Value must not be null. The array may be empty for the application component.
     */
    public ActivationDTO[] activations;

    /**
     * The values of all configurations consumed by this component.
     * <p>
     * Each map contains a <code>service.pid<code>.
     * <p>
     * At most one map also contains <code>service.factoryPid</code>
     * <p>
     * The merged properties are in a map where <code>service.pid=$</code>.
     */
    public Map<String, Object>[] configurations;
}
