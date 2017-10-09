package org.example.cdi.test.beans;

import static java.util.stream.Collectors.joining;
import static org.osgi.service.cdi.annotations.ReferenceCardinality.AT_LEAST_ONE;
import static org.osgi.service.cdi.annotations.ReferencePolicyOption.GREEDY;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.example.cdi.test.services.Greeter;
import org.example.cdi.test.services.MessageBuilder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.annotations.Reference;

@ApplicationScoped
public class GreeterAll implements Greeter {
    List<MessageBuilder> messages = new CopyOnWriteArrayList<>();

    @Override
    public synchronized String greet(String who) {
        return messages.stream().map(mb -> mb.message(who)).collect(joining("\n"));
    }

    @SuppressWarnings("incomplete-switch")
    public synchronized void addBuilder(
            @Observes //
            @Named("all.mb") //
            @Reference(cardinality = AT_LEAST_ONE, policyOption = GREEDY) //
            ReferenceEvent<MessageBuilder> e) {

        switch (e.type) {
        case ADDED:
            messages.add(e.instance);
            break;
        case REMOVED:
            messages.remove(e.instance);
        }
    }
}
