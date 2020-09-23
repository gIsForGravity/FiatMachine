package net.almostmc.FiatMachine;

import org.bukkit.configuration.file.FileConfiguration;

import java.math.BigDecimal;

public class MoneyManager {
    static {
        FileConfiguration config = new ConfigurationBuilder("money.yml").Build();

        if (config.contains("ironValue"))
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
            diamondValue = new BigDecimal("00000000.0000");

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

    private static BigDecimal ironValue;
    private static BigDecimal goldValue;
    private static BigDecimal diamondValue;

    private static int iron;
    private static int gold;
    private static int diamond;

    private static FileConfiguration config;

    public static BigDecimal getIronValue() {return ironValue;}
    public static BigDecimal getGoldValue() {return goldValue;}
}
