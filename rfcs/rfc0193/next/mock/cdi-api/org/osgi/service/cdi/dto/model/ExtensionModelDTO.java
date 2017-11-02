package org.osgi.service.cdi.dto.model;

import org.osgi.service.cdi.dto.CdiContainerDTO;

/**
 * Models an extension dependency of the {@link CdiContainerDTO}
 *
 * <ul>
 * <li>{@link DependencyModelDTO#maximumCardinality maximumCardinality} =
 * {@link DependencyModelDTO.MaximumCardinality#ONE ONE}</li>
 * <li>{@link DependencyModelDTO#minimumCardinality minimumCardinality} = 1</li>
 * <li>{@link DependencyModelDTO#dynamic dynamic} = false</li>
 * <li>{@link DependencyModelDTO#greedy greedy} = true</li>
 * </ul>
 */
public class ExtensionModelDTO extends DependencyModelDTO {
    /**
     * Target filter for the extension service.
     * <p>
     * The value must not be null.
     */
    public String target;
}
