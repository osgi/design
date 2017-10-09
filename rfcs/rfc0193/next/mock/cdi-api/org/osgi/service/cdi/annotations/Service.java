package org.osgi.service.cdi.annotations;

import javax.enterprise.util.Nonbinding;

public @interface Service {
    /**
     *
     * @return
     */
    Class<?> type() default Object.class;

    /**
     *
     * @return
     */
    ServiceScope scope() default ServiceScope.NOT_SPECIFIED;

    /**
     * Properties for this Service.
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
