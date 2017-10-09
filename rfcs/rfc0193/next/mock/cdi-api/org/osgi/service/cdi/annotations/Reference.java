/*
 * Copyright (c) OSGi Alliance (2016, 2017). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.osgi.service.cdi.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Annotation used to annotate a CDI injection point informing the CDI container
 * that the injection should apply a service obtained from the OSGi registry.
 */
@Qualifier
@Target(value = { ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
// @Requirement(
// namespace = ExtenderNamespace.EXTENDER_NAMESPACE,
// name = CdiConstants.CDI_CAPABILITY_NAME,
// version = CdiConstants.CDI_SPECIFICATION_VERSION)
public @interface Reference {
    // /**
    // * Used @Named instead
    // *
    // * {@link #name()} and {@link #fromScope()} and uniquely identify the
    // synthetic
    // * bean within the CDI container. Can be used as configuration key for the
    // * target filter.
    // *
    // * @return
    // */
    // String name();

    /**
     * Scope where the synthetic bean will be created.
     *
     * @return
     */
    Class<? extends Annotation> fromScope() default ApplicationScoped.class;

    /**
     * Only needed for {@link ReferenceCardinality#AT_LEAST_ONE}
     *
     * @return
     */
    @Nonbinding
    ReferenceCardinality cardinality() default ReferenceCardinality.NOT_SPECIFIED;

    /**
     *
     * @return
     */
    @Nonbinding
    ReferencePolicyOption policyOption() default ReferencePolicyOption.NOT_SPECIFIED;
}
