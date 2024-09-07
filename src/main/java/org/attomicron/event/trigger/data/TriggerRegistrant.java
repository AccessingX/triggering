package org.attomicron.event.trigger.data;

import org.attomicron.Triggering;
import org.attomicron.event.trigger.Trigger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TriggerRegistrant {

    private static final Map<String, List<Class<? extends Event>>> registered
            = new HashMap<>();

    public static void register(String type, Trigger<?, ?, ?, ?> trigger) {
        if (!registered.containsKey(type)) {
            registered.put(type, new ArrayList<>());
        }

        List<Class<? extends Event>> events = registered.get(type);
        if (events.contains(trigger.getEventClass())) {
            return;
        }

        events.add(trigger.getEventClass());
        Bukkit.getPluginManager().registerEvent(
                trigger.getEventClass(),
                new Listener() {},
                EventPriority.LOWEST,
                (listener, event) -> {
                    trigger.getExecutor().execute(event);
                },
                Triggering.plugin()
        );
    }

}
