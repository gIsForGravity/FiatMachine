package net.almostmc.FiatMachine;

import net.md_5.bungee.chat.SelectorComponentSerializer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;

public final class MoneyManager {
    static {
        FileConfiguration config = new ConfigurationBuilder("money.yml").Build();

        if (config.contains("iron"))
            iron = config.getLong("iron");
        else
            iron = 0;
        if (config.contains("gold"))
            gold = config.getLong("gold");
        else
            gold = 0;
        if (config.contains("diamond"))
            diamond = config.getLong("diamond");
        else
            diamond = 0;

        MoneyManager.config = config;

        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            FiatPlugin.singleton.getLogger().log(Level.SEVERE, "There was an error retrieving the vault economy service. This is fatal. Shutting down");
            Bukkit.getPluginManager().disablePlugin(FiatPlugin.singleton);
        } else
            economy = rsp.getProvider();
    }

    private static long iron;
    private static long gold;
    private static long diamond;

    private static FileConfiguration config;
    private static Economy economy;

    public static long Iron() {return iron;}
    public static long Gold() {return gold;}
    public static long Diamond() {return diamond;}

    public static void sellOre(OfflinePlayer player, int amount, BankItemType ore) {
        if (!economy.hasAccount(player))
            economy.createPlayerAccount(player);

        for (int i = 0; i < amount; i++) {
            economy.depositPlayer(player, calculateOreWorth(ore));
            switch (ore) {
                case IRON:
                    iron += 1;
                    break;
                case GOLD:
                    gold += 1;
                    break;
                case DIAMOND:
                    diamond += 1;
                    break;
            }
        }
    }

    private static long calculateOreWorth(BankItemType ore) {
        long amount;
        switch (ore) {
            case IRON:
                amount = iron;
                break;
            case GOLD:
                amount = gold;
                break;
            case DIAMOND:
                amount = diamond;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ore);
        }

        return Math.round(((double) 50/(double) 16129)*(double) amount*2-((double)12800/(double)16129)*amount+((double) 1625650/(double) 16129));
    }
}
