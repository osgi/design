package org.osgi.service.cdi.dto;

import org.osgi.framework.dto.ServiceReferenceDTO;

public class ReferenceDTO extends DependencyDTO {
    /**
     * The name of the reference.
     */
    public String name;

    /**
     * Indicates a target filter used in addition to the {@link #serviceType} to
     * match services.
     */
    public String target;

    /**
     * Indicates the type of service matched by the reference.
     */
    public String serviceType;

    /**
     * The set of services that match this reference.
     * <p>
     * The value must not be null. An empty array indicates no matching services.
     */
    public ServiceReferenceDTO[] matches;
}
