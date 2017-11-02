package org.osgi.service.cdi.dto;

import java.util.Map;

public class ConfigurationDTO extends DependencyDTO {
    /**
     * The pid of the Configuration object.
     * <p>
     * The value must not be null.
     */
    public String pid;

    /**
     * The properties as found in the configuration dictionary.
     * <p>
     * A null value indicates no configuration object is available.
     */
    public Map<String, Object> properties;
}
