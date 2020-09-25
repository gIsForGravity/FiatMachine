package net.almostmc.FiatMachine;

import org.bukkit.configuration.file.FileConfiguration;

public final class MoneyManager {
    static {
        FileConfiguration config = new ConfigurationBuilder("money.yml").Build();

        // Probably not needed
        /*if (config.contains("ironValue"))
            //noinspection ConstantConditions
            ironValue = new BigDecimal(config.getString("ironValue"));
        else
            ironValue = new BigDecimal("00000000.0000");
        if (config.contains("goldValue"))
            //noinspection ConstantConditions
            goldValue = new BigDecimal(config.getString("goldValue"));
        else
            goldValue = new BigDecimal("00000000.0000");
        if (config.contains("diamondValue"))
            //noinspection ConstantConditions
            diamondValue = new BigDecimal(config.getString("ironValue"));
        else
            diamondValue = new BigDecimal("00000000.0000");*/

        if (config.contains("iron"))
            iron = config.getInt("iron");
        else
            iron = 0;
        if (config.contains("gold"))
            gold = config.getInt("gold");
        else
            gold = 0;
        if (config.contains("diamond"))
            diamond = config.getInt("diamond");
        else
            diamond = 0;

        MoneyManager.config = config;
    }

    private static int iron;
    private static int gold;
    private static int diamond;

    private static FileConfiguration config;

    public static int Iron() {return iron;}
    public static int Gold() {return gold;}
    public static int Diamond() {return diamond;}
}
