package net.almostmc.FiatMachine;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;

public final class MoneyManager {
    // When MoneyManager is first called, initialize everything
    public static void Initialize() {
        // Load config into memory
        ConfigFile config = new ConfigFile("money.yml");

        // Initialize iron, gold, and diamond values to the values in the config
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

        // Assign the config loaded earlier to the config variable so it can be accessed later
        MoneyManager.config = config;

        //Load economy
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

    // Used to save iron, gold, and diamond data
    private static ConfigFile config;
    // Used to manage player money
    private static Economy economy;

    public static long Iron() {return iron;}
    public static long Gold() {return gold;}
    public static long Diamond() {return diamond;}

    // Convert mat to BankItemType and then call sellOre
    public static long sellOre(OfflinePlayer player, int amount, Material mat) {
        switch (mat) {
            case IRON_INGOT:
                return sellOre(player, amount, BankItemType.IRON);
            case GOLD_INGOT:
                return sellOre(player, amount, BankItemType.GOLD);
            case DIAMOND:
                return sellOre(player, amount, BankItemType.DIAMOND);

            // If the item is a block instead of an ingot, multiply the amount
            // by 9 because there are 9 ingots in a block
            case IRON_BLOCK:
                return sellOre(player, amount * 9, BankItemType.IRON);
            case GOLD_BLOCK:
                return sellOre(player, amount * 9, BankItemType.GOLD);
            case DIAMOND_BLOCK:
                return sellOre(player, amount * 9, BankItemType.DIAMOND);

            default:
                // If there is any other material inputted
                throw new IllegalStateException("Unexpected value: " + mat);
        }
    }

    // Sell (amount) amount of ore to the bank
    public static long sellOre(OfflinePlayer player, int amount, BankItemType ore) {
        if (!economy.hasAccount(player))
            economy.createPlayerAccount(player);

        long depositAmount = 0;
        for (int i = 0; i < amount; i++) {
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
            depositAmount += calculateOreWorth(ore);
        }
        economy.depositPlayer(player, depositAmount);
        saveConfig();
        return depositAmount;
    }

    // Save money.yml
    private static void saveConfig() {
        config.set("iron", iron);
        config.set("gold", gold);
        config.set("diamond", diamond);
        saveValues();
    }

    // Use quadratic equation to calculate how much an ore is worth
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

        if (amount <= 640)
            return ((((500L/136107L)*amount)*amount)-((640000L/136107L)*amount))+(272853500L/136107L);
        else if (amount <= 6400)
        // y = (-5/64)x + 550
            return ((-5L/64L)*amount) + 550;
        else
        // y = 50
            return 50L;
    }

    public static void saveValues() {
        config.save();
    }
}
