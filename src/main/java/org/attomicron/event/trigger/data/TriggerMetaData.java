package org.attomicron.event.trigger.data;

import org.attomicron.event.trigger.interfaces.CanTriggered;

public abstract class TriggerMetaData<T extends CanTriggered> {

    private final int index;

    private final T triggered;

    public TriggerMetaData(int index, T triggered) {
        this.index = index;
        this.triggered = triggered;
    }

    public int getIndex() {
        return index;
    }

    public T getTriggered() {
        return triggered;
    }

}
