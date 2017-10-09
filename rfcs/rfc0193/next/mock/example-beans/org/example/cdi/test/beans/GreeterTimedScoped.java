package org.example.cdi.test.beans;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.example.cdi.test.beans.GreeterTimedScoped.CONF_KEY_SALUTE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Scope;

import org.osgi.service.cdi.annotations.Component;

@Retention(RUNTIME)
@Target({ FIELD, METHOD, TYPE })
@Scope
@Component(
        configPid = "org.example.greeter.timed",
        property = {
                CONF_KEY_SALUTE + "=Hello from default CDI"
        })
public @interface GreeterTimedScoped {
    String CONF_KEY_SALUTE = "salute";
}
