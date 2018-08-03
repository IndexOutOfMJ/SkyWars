package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LocationsUtil {

    private final SkyWars skyWars;
    private Location lobby;
    private HashMap<Integer, Location> island = new HashMap<Integer, Location>();

    public LocationsUtil (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void setLocation (Location location, String locationname) {
        if (locationname.equalsIgnoreCase("lobby")) {
            YamlConfiguration spawncfg = skyWars.getFileUtil().getLocationsConfig();
            spawncfg.set(locationname.toLowerCase() + ".world", location.getWorld().getName());
            spawncfg.set(locationname.toLowerCase() + ".x", location.getX());
            spawncfg.set(locationname.toLowerCase() + ".y", location.getY());
            spawncfg.set(locationname.toLowerCase() + ".z", location.getZ());
            spawncfg.set(locationname.toLowerCase() + ".yaw", location.getY());
            spawncfg.set(locationname.toLowerCase() + ".pitch", location.getPitch());
            try {
                spawncfg.save(skyWars.getFileUtil().getLocationsFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadLocation() {
        YamlConfiguration locationcfg = skyWars.getFileUtil().getLocationsConfig();
        this.lobby = new Location(Bukkit.getWorld(locationcfg.getString("lobby.world")),
                locationcfg.getDouble("lobby.x"), locationcfg.getDouble("lobby.y"),
                locationcfg.getDouble("lobby.z"), locationcfg.getLong("lobby.yaw"),
                locationcfg.getLong("lobby.pitch"));
    }

    public Location getLobby() {
        return lobby;
    }

    public HashMap<Integer, Location> getIsland() {
        return island;
    }
}
