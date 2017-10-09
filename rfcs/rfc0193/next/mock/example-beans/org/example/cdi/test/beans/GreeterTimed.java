package org.example.cdi.test.beans;

import static org.example.cdi.test.beans.GreeterTimedScoped.CONF_KEY_SALUTE;

import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.cdi.test.services.FormattedTime;
import org.example.cdi.test.services.Greeter;
import org.osgi.service.cdi.annotations.Configuration;
import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.Service;

@GreeterTimedScoped
@Service
public class GreeterTimed implements Greeter {
    private final FormattedTime now;
    private final String prefix;

    @Inject
    public GreeterTimed(
            @Named("timed.now") @Reference FormattedTime now,
            @Named("timed.prefix") String prefix) {
        this.now = now;
        this.prefix = prefix;
    }

    @Override
    public String greet(String who) {
        return String.format("[%s] %s %s", now.now(), prefix, who);
    }

    @Produces
    @GreeterTimedScoped
    @Named("timed.prefix")
    public static String getSalute(@Configuration Map<String, Object> config) {
        return config.get(CONF_KEY_SALUTE).toString();
    }
}
