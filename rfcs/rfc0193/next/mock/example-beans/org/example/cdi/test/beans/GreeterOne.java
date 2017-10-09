package org.example.cdi.test.beans;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.cdi.test.services.FormattedTime;
import org.example.cdi.test.services.Greeter;
import org.example.cdi.test.services.MessageBuilder;
import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.annotations.Service;

@GreeterOneScoped
@Service
public class GreeterOne implements Greeter {
    @Inject
    @Named("one.mb")
    @Reference
    @ServiceName("one")
    MessageBuilder mb;

    @Inject
    @Named("one.time")
    @Reference
    FormattedTime time;

    @Inject
    Postscript postscript;

    @Override
    public String greet(String who) {
        return String.format("[%s] %s %s", time.now(), mb.message(who), postscript.get());
    }
}
