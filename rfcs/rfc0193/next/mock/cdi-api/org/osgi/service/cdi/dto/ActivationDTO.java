package org.osgi.service.cdi.dto;

import org.osgi.framework.dto.ServiceReferenceDTO;
import org.osgi.service.cdi.dto.model.ActivationModelDTO;
import org.osgi.service.cdi.dto.model.ComponentModelDTO;

/**
 * Runtime representation of a component activation
 */
public class ActivationDTO {
    /**
     *
     */
    public ActivationModelDTO model;

    /**
     * Must not be null if {@link ActivationModelDTO#serviceClasses
     * model.serviceClasses} is not empty.
     */
    public ServiceReferenceDTO service;

    /**
     * The number of contexts created by this activation.
     * <p>
     * If the owner component is of type {@link ComponentModelDTO.Type#APPLICATION
     * APPLICATION} the value is -1 to reflect that all application activations
     * share the @ApplicationScoped scope, which they did not create.
     * <p>
     * If the owner component is of type {@link ComponentModelDTO.Type#COMPONENT
     * COMPONENT} the value is 0 or more depending on the
     * {@link ActivationModelDTO#scope model.scope}.
     */
    public int contexts;
}
