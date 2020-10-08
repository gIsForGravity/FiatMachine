package net.almostmc.FiatMachine;

import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FiatPlugin extends JavaPlugin {
    public static FiatPlugin singleton;

    @Override
    public void onLoad() {
        singleton = this;
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(getServer().getPluginCommand("bank")).setExecutor(new BankCommand());
        getServer().getPluginManager().registerEvents(new WorldSaveEventListener(), this);
        MoneyManager.Initialize();
    }

    @Override
    public void onDisable() {
        MoneyManager.saveValues();
    }
}
