package org.osgi.service.cdi.dto;

import org.osgi.framework.dto.ServiceReferenceDTO;
import org.osgi.service.cdi.dto.model.ExtensionModelDTO;

/**
 * TODO Add a BundleDTO? Remove the service?
 */
public class ExtensionDTO extends DependencyDTO {
    /**
     *
     */
    ExtensionModelDTO model;

    /**
     * The service to which the extension dependency is resolved.
     * <p>
     * Must never be null.
     */
    public ServiceReferenceDTO service;
}
