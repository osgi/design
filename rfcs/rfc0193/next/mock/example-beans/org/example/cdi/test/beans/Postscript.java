package org.example.cdi.test.beans;

import java.util.Map;
import java.util.function.Supplier;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Configuration;

@GreeterOneScoped
public class Postscript implements Supplier<String> {
    private final String str;

    @Inject
    public Postscript(@Configuration Map<String, Object> config) {
        this.str = (String) config.get("postscript");
    }

    @Override
    public String get() {
        return str;
    }
}
