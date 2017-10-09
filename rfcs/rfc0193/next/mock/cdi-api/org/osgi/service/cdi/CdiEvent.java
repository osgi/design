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

import org.osgi.framework.Bundle;

/**
 * {@link CdiEvent}s are sent by the CDI extender and received by registered @{link CdiListener} services.
 *
 * The event is fired when the {@link CdiContainer} enters a new state.
 */
public class CdiEvent {
    private final Bundle bundle;
    private final Throwable cause;
    private final Bundle extenderBundle;
    private final String dependencies;
    private final long timestamp;
    private final CdiContainerState type;
    private final String string;

    /**
     * @param type
     * @param bundle
     * @param extenderBundle
     */
    public CdiEvent(CdiContainerState type, Bundle bundle, Bundle extenderBundle) {
        this(type, bundle, extenderBundle, null, null);
    }

    /**
     * @param type
     * @param bundle
     * @param extenderBundle
     * @param dependencies
     */
    public CdiEvent(CdiContainerState type, Bundle bundle, Bundle extenderBundle, String dependencies) {
        this(type, bundle, extenderBundle, dependencies, null);
    }

    /**
     * @param type
     * @param bundle
     * @param extenderBundle
     * @param dependencies
     * @param cause
     */
    public CdiEvent(
            CdiContainerState type, Bundle bundle, Bundle extenderBundle, String dependencies, Throwable cause) {

        this.type = type;
        this.bundle = bundle;
        this.extenderBundle = extenderBundle;
        this.dependencies = dependencies;
        this.cause = cause;
        this.timestamp = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append("{type:'").append(this.type);
        sb.append("', timestamp:").append(this.timestamp);
        sb.append(", bundle:'").append(this.bundle).append("'");
        sb.append(", extenderBundle:'").append(this.extenderBundle).append("'");
        if (this.dependencies != null) {
            sb.append(", payload:'").append(this.dependencies).append("'");
        }
        if (this.cause != null) {
            sb.append(", cause:'").append(this.cause.getMessage()).append("'");
        }
        sb.append("}");

        string = sb.toString();
    }

    /**
     * @return the bundle who's CDI container triggered this event
     */
    public Bundle getBundle() {
        return bundle;
    }

    /**
     * @return the cause of the event if there was one
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * @return the bundle of the CDI extender
     */
    public Bundle getExtenderBundle() {
        return extenderBundle;
    }

    /**
     * @return the payload associated with this event
     */
    public String getDependencies() {
        return dependencies;
    }

    /**
     * @return the timestamp of the event
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return the state of this event
     */
    public CdiContainerState getType() {
        return type;
    }

    @Override
    public String toString() {
        return string;
    }
}
