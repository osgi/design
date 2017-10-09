package org.osgi.service.cdi.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 * Annotation used with {@link Inject} in order to have Configuration Admin
 * configurations injected into CDI beans.
 * <p>
 * The values are PIDs of required configurations. The special value {@code $}
 * is used to refer to the host bean class name.
 */
@Qualifier
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface Configuration {
    // Allows us to avid synthetic qualifiers
    Class<? extends Annotation> fromScope() default ApplicationScoped.class;
}
