package org.osgi.service.cdi;

/**
 * An enum defining the states of a CDI container.
 */
public enum CdiContainerState {
    /**
     * The CDI container has started being created.
     */
    CREATING,

    /**
     * The CDI container is created and should be fully usable.
     */
    CREATED,

    /**
     * The CDI container has started being destroyed.
     */
    DESTROYING,

    /**
     * The CDI container is completely destroyed.
     */
    DESTROYED,

    /**
     * The CDI container is waiting for dependent configurations.
     */
    WAITING_FOR_CONFIGURATIONS,

    /**
     * The CDI container is waiting for dependent extensions.
     */
    WAITING_FOR_EXTENSIONS,

    /**
     * The CDI container is waiting for dependent services.
     */
    WAITING_FOR_SERVICES,

    /**
     * The CDI container is satisfied and resuming construction.
     */
    SATISFIED,

    /**
     * The CDI container has suffered a failure and will be destroyed.
     */
    FAILED
}