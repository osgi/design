package org.osgi.service.cdi.dto;

import java.util.Map;

import org.osgi.service.cdi.dto.model.ConfigurationModelDTO;
import org.osgi.service.cdi.dto.model.DependencyModelDTO;

public class ConfigurationDTO extends DependencyDTO {
    /**
     *
     */
    ConfigurationModelDTO model;

    /**
     * The properties to which this configuration dependency is currently resolved.
     * <p>
     * Must never be null.
     * <p>
     * Can contain multiple values when {@link DependencyModelDTO#maximumCardinality
     * model.maximumCardinality} is
     * {@link DependencyModelDTO.MaximumCardinality#MANY MANY}.
     */
    public Map<String, Object>[] properties;
}
