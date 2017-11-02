package org.osgi.service.cdi.dto;

import org.osgi.dto.DTO;

public abstract class DependencyDTO extends DTO {
    /**
     * Defines the possible values for {@link #maximumCardinality}
     */
    public enum MaximumCardinality {
        /**
         * Defines a unary reference.
         */
        ONE,
        /**
         * Defines a plural reference.
         */
        MANY
    }

    /**
     * The maximum cardinality of the reference.
     */
    public MaximumCardinality maximumCardinality;

    /**
     * The minimum cardinality of the reference.
     * <p>
     * <ul>
     * <li>If {@link #maximumCardinality} is {@link MaximumCardinality#ONE ONE} the
     * value must be either 0 or 1.</li>
     * <li>If {@link #maximumCardinality} is {@link MaximumCardinality#MANY MANY}
     * the value must be from 0 to {@link Integer#MAX_VALUE}.
     */
    public int minimumCardinality;

    /**
     * Indicates if the reference is greedy or reluctant in nature.
     */
    public boolean greedy;

    /**
     * Indicates if the reference is dynamic or static in nature.
     */
    public boolean dynamic;

    /**
     *
     */
    public boolean satisfied;
}
