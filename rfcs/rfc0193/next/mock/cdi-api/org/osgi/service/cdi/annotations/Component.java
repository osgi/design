package org.osgi.service.cdi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.util.Nonbinding;

/**
 * Added to a scope annotation to make it into a component
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.PACKAGE })
public @interface Component {
    /**
     * When applied to a scope annotation does nothing.
     *
     * When applied to java package retrofits the target scope.
     *
     * @return
     */
    Class<? extends Annotation> fromScope() default ApplicationScoped.class;

    /**
     *
     * @return
     */
    String configPid() default "";

    /**
     *
     * @return
     */
    ConfigurationCardinality configCardinality() default ConfigurationCardinality.NOT_SPECIFIED;

    /**
     * TODO Somehow map reference bean names to config key names Properties for this
     * Service.
     * <p>
     * Each property string is specified as {@code "name=value"}. The type of the
     * property value can be specified in the name as {@code name:type=value}. The
     * type must be one of the property types supported by the {@code type}
     * attribute of the {@code property} element of a Service Description.
     * <p>
     * To specify a property with multiple values, use multiple name, value pairs.
     * For example, {@code "foo=bar", "foo=baz"}.
     *
     * @see "The property element of a Service."
     */
    @Nonbinding
    String[] property() default {};
}
