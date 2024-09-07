package org.attomicron.event.trigger;

import org.attomicron.event.trigger.data.TriggerCondition;
import org.attomicron.event.trigger.data.TriggerMetaData;
import org.attomicron.event.trigger.interfaces.CanTriggered;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Trigger<T extends Event, C extends CanTriggered, X, M extends TriggerMetaData<C>> {

    protected final Class<T> eventClass;

    protected final Function<T, X> mappingObjectFunction;

    protected final List<TriggerCondition<T, C, X, M>> conditions = new ArrayList<>();

    protected final TriggerExecutor<X, C, M> executor;

    public Trigger(
            Class<T> eventClass,
            Function<T, X> mappingObjectFunction,
            TriggerExecutor<X, C, M> executor
    ) {
        this.eventClass = eventClass;
        this.mappingObjectFunction = mappingObjectFunction;
        this.executor = executor;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }

    public boolean canTriggered(Event event) {
        return eventClass.equals(event.getClass());
    }

    public TriggerExecutor<X, C, M> getExecutor() {
        return executor;
    }

    public abstract void activate(T event, String type, X object, C triggered, M metadata);

}
