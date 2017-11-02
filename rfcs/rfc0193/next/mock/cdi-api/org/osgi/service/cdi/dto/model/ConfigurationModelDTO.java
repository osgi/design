package org.osgi.service.cdi.dto.model;

/**
 * Singleton configuration:
 * <ul>
 * <li>{@link DependencyModelDTO#maximumCardinality maximumCardinality} =
 * {@link DependencyModelDTO.MaximumCardinality#ONE ONE}</li>
 * <li>{@link DependencyModelDTO#minimumCardinality minimumCardinality} = 1</li>
 * <li>{@link DependencyModelDTO#dynamic dynamic} = false</li>
 * <li>{@link DependencyModelDTO#greedy greedy} = true</li>
 * </ul>
 *
 * Factory configuration:
 * <ul>
 * <li>{@link DependencyModelDTO#maximumCardinality maximumCardinality} =
 * {@link DependencyModelDTO.MaximumCardinality#MANY MANY}</li>
 * <li>{@link DependencyModelDTO#minimumCardinality minimumCardinality} = 0</li>
 * <li>{@link DependencyModelDTO#dynamic dynamic} = true</li>
 * <li>{@link DependencyModelDTO#greedy greedy} = true</li>
 * </ul>
 */
public class ConfigurationModelDTO extends DependencyModelDTO {
    /**
     * The pid of the Configuration object.
     * <p>
     * The value must not be null.
     */
    public String pid;
}
