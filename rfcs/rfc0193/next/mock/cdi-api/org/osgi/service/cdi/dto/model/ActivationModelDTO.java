package org.osgi.service.cdi.dto.model;

import org.osgi.service.cdi.dto.ComponentDTO;

/**
 * Models an action that is executed once the parent {@link ComponentDTO}
 * becomes satisfied.
 * <p>
 * Can be either an OSGi service publication or a creation of a bean or both in
 * case of {@link Scope#SINGLETON}.
 */
public class ActivationModelDTO {
    public enum Scope {
        SINGLETON, BUNDLE, PROTOTYPE
    }

    /**
     * The bean class which will be instantiated and dependency injected when this
     * activation is created and destroyed when this activation is disposed.
     */
    public String beanClass;

    /**
     *
     */
    public Scope scope;

    /**
     * Must never be null.
     * <p>
     * Can be empty if the {@link #beanClass} will not be published as an OSGi
     * service.
     */
    public String[] serviceClasses;
}
