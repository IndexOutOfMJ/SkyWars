/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtil {

    private final SkyWars skyWars;
    private final String parent = "plugins/SkyWars/";
    private List<String> def = new ArrayList<String>();

    private File chestFile = new File(parent, "Chest.yml");
    private File locationsFile = new File(parent, "Locations.yml");
    private File languageFile = new File(parent, "Language.yml");
    private YamlConfiguration chestConfig = YamlConfiguration.loadConfiguration(chestFile);
    private YamlConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationsFile);
    private YamlConfiguration languageConfig = YamlConfiguration.loadConfiguration(languageFile);

    public ConfigUtil(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void loadDefaultConfig() {
        skyWars.getConfig().options().copyDefaults(true);
        skyWars.getConfig().getDefaults();
        skyWars.getData().setSetup(skyWars.getConfig().getBoolean("Setup"));
        skyWars.getData().setPlayercount(skyWars.getConfig().getInt("PlayerCountToStart"));
        skyWars.getData().setPrefix(ColorFormatter(skyWars.getConfig().getString("Prefix")));
    }

    public void reloadAllConfigs() {
        //Main Config
        skyWars.reloadConfig();

        //Other Configs
        reloadOtherConf("Chest.yml", chestConfig);
        reloadOtherConf("Locations.yml", locationConfig);
        reloadOtherConf("Language.yml", languageConfig);
    }

    private void reloadOtherConf(String confyml, YamlConfiguration yamlConfiguration) {
        InputStream confStream = skyWars.getResource(confyml);
        if (confStream != null) {
            YamlConfiguration relConfig = YamlConfiguration.loadConfiguration(confStream);
            yamlConfiguration.setDefaults(relConfig);
        }
    }

    public Configuration getDefaultConfig() {
        return skyWars.getConfig();
    }

    List<String> getChestConfig() {
        def.add("WOOD");
        List<String> items = (List<String>) chestConfig.getList("chestFile.items", def);
        return items;
    }

    public YamlConfiguration getLocationsConfig() {
        return locationConfig;
    }

    public File getLocationsFile() {
        return locationsFile;
    }

    public void addDefaultChest() {
        List<String> defaults = new ArrayList<String>();
        defaults.add("DIAMOND_SWORD");
        defaults.add("LEATHER_CHESTPLATE");
        chestConfig.addDefault("chestFile.items", defaults);
        chestConfig.options().copyDefaults(true);
        try {
            chestConfig.save(chestFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String ColorFormatter(String text) {
        return text.replace("&", "§");
    }
}
