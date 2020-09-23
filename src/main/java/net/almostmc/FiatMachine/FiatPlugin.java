package net.almostmc.FiatMachine;

import org.bukkit.plugin.java.JavaPlugin;

public class FiatPlugin extends JavaPlugin {
    public static FiatPlugin singleton;

    @Override
    public void onLoad() {
        singleton = this;
    }
}
