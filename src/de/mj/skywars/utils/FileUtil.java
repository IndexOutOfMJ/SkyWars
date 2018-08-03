package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private final SkyWars skyWars;
    private List<String> def = new ArrayList<String>();

    private File chest = new File("plugins/SkyWars/", "Chest.yml");
    private File locations = new File("plugin/SkyWars", "Locations.yml");
    private YamlConfiguration chestConfig = YamlConfiguration.loadConfiguration(chest);
    private YamlConfiguration locationConfig = YamlConfiguration.loadConfiguration(locations);

    public FileUtil(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void loadDefaultConfig() {
        skyWars.getConfig().options().copyDefaults(true);
        skyWars.getConfig().getDefaults();
    }

    public Configuration getDefaultConfig() {
        return skyWars.getConfig();
    }

    public List<String> getChestConfig() {
        def.add("WOOD");
        List<String> items = (List<String>) chestConfig.getList("chest.items", def);
        return items;
    }

    public YamlConfiguration getLocationsConfig() {
        return locationConfig;
    }

    public File getLocationsFile() {
        return locations;
    }
}
