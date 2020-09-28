package net.almostmc.FiatMachine;

import net.almostmc.FiatMachine.Commands.BankCommand;
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
    }
}
