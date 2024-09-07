package org.attomicron.event.trigger.data;

import org.attomicron.event.trigger.interfaces.CanTriggered;
import org.bukkit.event.Event;

public abstract class TriggerCondition<E extends Event, C extends CanTriggered, X, M extends TriggerMetaData<C>> {

    public abstract boolean test(E event, String type, C triggered, X object, M metadata);

}
