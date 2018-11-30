/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigUtil {

    private final SkyWars skyWars;
    private final String parent = "plugins/SkyWars/";
    private List<String> def = new ArrayList<String>();
    private HashMap<String, List<String>> kits = new HashMap<>();

    private File chestFile = new File(parent, "Chest.yml");
    private File locationsFile = new File(parent, "Locations.yml");
    private File languageFile = new File(parent, "Language.yml");
    private File kitFile = new File(parent, "Kits.yml");
    private File playerFile = new File(parent, "Player.yml");
    private YamlConfiguration chestConfig = YamlConfiguration.loadConfiguration(chestFile);
    private YamlConfiguration locationConfig = YamlConfiguration.loadConfiguration(locationsFile);
    private YamlConfiguration languageConfig = YamlConfiguration.loadConfiguration(languageFile);
    private YamlConfiguration kitConfig = YamlConfiguration.loadConfiguration(kitFile);
    private YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

    /*
    TODO
    LanguageFile
     */

    public ConfigUtil(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void loadDefaultConfig() {
        skyWars.getConfig().options().copyDefaults(true);
        skyWars.getConfig().getDefaults();

        skyWars.getData().setPrefix(colorFormatter(skyWars.getConfig().getString("Prefix")));

        skyWars.getData().setSetup(skyWars.getConfig().getBoolean("Setup"));

        skyWars.getData().setPlayerCount(skyWars.getConfig().getInt("PlayerCountToStart"));

        skyWars.getData().setMySQLActive(skyWars.getConfig().getBoolean("MySQL"));

        skyWars.getData().setStartItemName(colorFormatter(skyWars.getConfig().getString("StartItemName")));
        skyWars.getData().setStartItemType(Material.getMaterial(skyWars.getConfig().getString("StartItemType")));

        skyWars.getData().setKitItemName(colorFormatter(skyWars.getConfig().getString("KitItemName")));
        skyWars.getData().setKitItemType(Material.getMaterial(skyWars.getConfig().getString("KitItemType")));

        skyWars.getData().setExitItemName(colorFormatter(skyWars.getConfig().getString("ExitItemName")));
        skyWars.getData().setExitItemType(Material.getMaterial(skyWars.getConfig().getString("ExitItemType")));

        //load lore's
        ArrayList<String> startItemLore = (ArrayList<String>) skyWars.getConfig().getList("StartItemLore");
        ArrayList<String> kitItemLore = (ArrayList<String>) skyWars.getConfig().getList("KitItemLore");
        ArrayList<String> exitItemLore = (ArrayList<String>) skyWars.getConfig().getList("ExitItemLore");
        skyWars.getData().setStartItemLore(startItemLore);
        skyWars.getData().setKitItemLore(kitItemLore);
        skyWars.getData().setExitItemLore(exitItemLore);
    }

    public void reloadAllConfigs() {
        //main config
        skyWars.reloadConfig();

        //Other config's
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

    public void setLocIslandDefault() {
        locationConfig.addDefault("islands", 0);
        locationConfig.options().copyDefaults(true);
        try {
            locationConfig.save(locationsFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public YamlConfiguration getLocationsConfig() {
        return locationConfig;
    }

    public File getLocationsFile() {
        return locationsFile;
    }

    public HashMap<String, List<String>> getKits() {
        return kits;
    }

    public YamlConfiguration getKitConfig() {
        return kitConfig;
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

    private String colorFormatter(String text) {
        return text.replace("&", "§");
    }

    public void kitFileDefault() {
        List<String> kitNames = new ArrayList<String>();
        kitNames.add("Starter");
        kitNames.add("Crafter");
        kitConfig.addDefault("KitNames", kitNames);

        List<String> starterItems = new ArrayList<String>();
        List<String> crafterItems = new ArrayList<String>();
        starterItems.add("STONE_PICKAXE");
        starterItems.add("STONE_SWORD");
        starterItems.add("STONE_AXE");
        crafterItems.add("WORKBENCH");
        crafterItems.add("ANVIL");
        crafterItems.add("IRON_INGOT");
        kitConfig.addDefault("Starter", starterItems);
        kitConfig.addDefault("Crafter", crafterItems);
        kitConfig.options().copyDefaults(true);
        try {
            kitConfig.save(kitFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void kitFileLoader() {
        for (String name : (ArrayList<String>) kitConfig.getList("KitNames")) {
            kits.put(name, (ArrayList<String>) kitConfig.getList(name));
        }
    }

    public YamlConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public void addPlayerKit(Player player, String kit) {
        playerConfig.set(player.getName(), kit);
        try {
            playerConfig.save(playerFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
