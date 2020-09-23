package net.almostmc.FiatMachine;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationBuilder {
    private final File configFile;

    public ConfigurationBuilder(String configName) {
        configFile = new File(FiatPlugin.singleton.getDataFolder(), configName);
    }

    public FileConfiguration Build() {
        FileConfiguration config = new YamlConfiguration();

        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }

            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return config;
    }
}
