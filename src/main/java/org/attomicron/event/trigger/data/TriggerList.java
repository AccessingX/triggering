package org.attomicron.event.trigger.data;

import org.attomicron.event.trigger.Trigger;

import java.util.ArrayList;

public final class TriggerList extends ArrayList<Trigger<?, ?, ?, ?>> {

    private final String type;

    public TriggerList(String type) {
        this.type = type;
    }

    @Override
    public boolean add(Trigger<?, ?, ?, ?> trigger) {
        TriggerRegistrant.register(type, trigger);
        return super.add(trigger);
    }

}
