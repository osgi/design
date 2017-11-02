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

package org.osgi.service.cdi.runtime;

import javax.enterprise.inject.spi.BeanManager;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.cdi.dto.CdiContainerDTO;
import org.osgi.util.promise.Promise;

/**
 * A {@code CdiContainerRuntime} object is registered by the CDI extender in the
 * OSGi registry for each managed CDI bundle.
 *
 * @author $Id$
 */
@ProviderType
public interface CdiContainerRuntime {

    /**
     * When the {@code CdiContainerRuntime}'s state is
     * {@link CdiContainerState#CREATED CREATED} the container's {@link BeanManager
     * BeanManager} will become available.
     *
     * @return the container's {@link BeanManager BeanManager}
     */
    public Promise<BeanManager> getBeanManager();

    /**
     * Obtain the CDI container dto.
     *
     * @return the CDI container dto
     */
    public CdiContainerDTO getCdiContainerDTO();
}
