package org.osgi.service.cdi.annotations;

/**
 * Policy option for the {@link Reference} annotation.
 *
 * @author $Id$
 * @since 1.2
 */
public enum ReferencePolicyOption {
    /**
     * The reluctant policy option is the default policy option for both
     * {@link ReferencePolicy#STATIC static} and {@link ReferencePolicy#DYNAMIC
     * dynamic} reference policies. When a new target service for a reference
     * becomes available, references having the reluctant policy option for the
     * static policy or the dynamic policy with a unary cardinality will ignore the
     * new target service. References having the dynamic policy with a multiple
     * cardinality will bind the new target service.
     */
    RELUCTANT("reluctant"),

    /**
     * The greedy policy option is a valid policy option for both
     * {@link ReferencePolicy#STATIC static} and {@link ReferencePolicy#DYNAMIC
     * dynamic} reference policies. When a new target service for a reference
     * becomes available, references having the greedy policy option will bind the
     * new target service.
     */
    GREEDY("greedy"),

    /**
     *
     */
    NOT_SPECIFIED("not-specified");

    private final String value;

    private ReferencePolicyOption(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
