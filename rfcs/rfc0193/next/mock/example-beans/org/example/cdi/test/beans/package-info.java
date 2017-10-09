@Component(
        fromScope = ApplicationScoped.class,
        configPid = "org.example.test.pid",
        configCardinality = ConfigurationCardinality.MANDATORY,
        property = {})
package org.example.cdi.test.beans;

import javax.enterprise.context.ApplicationScoped;

import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.ConfigurationCardinality;
