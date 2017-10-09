package org.osgi.service.cdi.annotations;

public enum ConfigurationCardinality {
    /**
     * The reference is optional and unary. That is, the reference has a cardinality
     * of {@code 0..1}.
     */
    OPTIONAL("0..1"),

    /**
     * The reference is mandatory and unary. That is, the reference has a
     * cardinality of {@code 1..1}.
     */
    MANDATORY("1..1"),

    /**
     * The reference is optional and multiple. That is, the reference has a
     * cardinality of {@code 0..n}.
     */
    MULTIPLE("0..n"),

    // /**
    // * The reference is mandatory and multiple. That is, the reference has a
    // * cardinality of {@code 1..n}.
    // */
    // AT_LEAST_ONE("1..n"),

    /**
     *
     */
    NOT_SPECIFIED("not-specified");

    private final String value;

    private ConfigurationCardinality(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
