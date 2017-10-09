package org.osgi.service.cdi.annotations;

public enum ServiceScope {
    /**
     * A single service object is used for all references to the service in this
     * bundle.
     */
    BUNDLE("bundle"),

    /**
     * Bound services must have prototype service scope. Each instance of the bean
     * with this reference can receive a unique instance of the service.
     */
    PROTOTYPE("prototype"),

    /**
     * Bound services must have singleton service scope. Each instance of the bean
     * with this reference will receive the same instance of the service.
     */
    SINGLETON("singleton"),

    /**
     *
     */
    NOT_SPECIFIED("not-specified");

    private final String value;

    ServiceScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
