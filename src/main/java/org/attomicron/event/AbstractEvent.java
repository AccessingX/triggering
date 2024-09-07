package org.attomicron.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("unchecked")
public abstract class AbstractEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public <T extends Event> T call() {
        Bukkit.getPluginManager().callEvent(this);
        return (T) this;
    }

}
