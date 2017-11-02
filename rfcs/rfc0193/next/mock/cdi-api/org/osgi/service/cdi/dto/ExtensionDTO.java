package org.osgi.service.cdi.dto;

import org.osgi.framework.dto.ServiceReferenceDTO;

public class ExtensionDTO extends DependencyDTO {
    /**
     *
     */
    public String target;

    /**
     * The service to which the extension dependency is resolved or
     * <code>null</code>.
     */
    public ServiceReferenceDTO service;
}
