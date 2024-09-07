package org.attomicron;

import org.bukkit.plugin.PluginBase;

public final class Triggering {

    private static PluginBase plugin;

    public static void boot(PluginBase plugin) {
        if (Triggering.plugin != null) {
            throw new RuntimeException("Triggering already booted!");
        }
        Triggering.plugin = plugin;
    }

    public static PluginBase plugin() {
        if (Triggering.plugin == null) {
            throw new RuntimeException("Triggering not yet booted!");
        }
        return plugin;
    }

}
