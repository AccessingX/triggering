package org.attomicron.event;

import org.bukkit.event.Cancellable;

public abstract class AbstractEventCancellable extends AbstractEvent implements Cancellable {

    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
