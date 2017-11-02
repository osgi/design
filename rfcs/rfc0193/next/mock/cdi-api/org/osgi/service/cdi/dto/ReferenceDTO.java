package org.osgi.service.cdi.dto;

import org.osgi.framework.dto.ServiceReferenceDTO;
import org.osgi.service.cdi.dto.model.ReferenceModelDTO;

public class ReferenceDTO extends DependencyDTO {
    /**
     *
     */
    ReferenceModelDTO model;

    /**
     * Indicates the runtime target filter used in addition to the
     * {@link ReferenceModelDTO#serviceType model.serviceType} to match services.
     */
    public String target;

    /**
     * The set of services that match this reference.
     * <p>
     * The value must not be null. An empty array indicates no matching services.
     */
    public ServiceReferenceDTO[] matches;
}
