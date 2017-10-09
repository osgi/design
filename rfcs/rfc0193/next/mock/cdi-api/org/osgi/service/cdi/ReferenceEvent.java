package org.osgi.service.cdi;

/**
 * @author tobo
 *
 * @param <T>
 *            The service type, or ServiceReference&lt;T&gt, or Map.Entry&lt;T&gt;, ServiceReference&lt;T&gt;&gt;
 */
public class ReferenceEvent<T> {
    public enum Type {
        ADDED, MODIFIED, REMOVED;
    }

    public final Type type;
    public final T instance;

    public ReferenceEvent(Type type, T instance) {
        this.type = type;
        this.instance = instance;
    }

    @Override
    public String toString() {
        return ReferenceEvent.class.getSimpleName() + "[ type: " + type + ", instance: " + instance + "]";
    }
}
