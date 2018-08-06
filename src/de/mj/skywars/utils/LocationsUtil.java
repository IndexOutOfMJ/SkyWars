package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;

public class LocationsUtil {

    private final SkyWars skyWars;
    private Location lobby;
    private HashMap<Integer, Location> island = new HashMap<Integer, Location>();

    public LocationsUtil (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public boolean setLocation (Location location, String locationname) {
        if (locationname.equalsIgnoreCase("lobby")) {
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".world", location.getWorld().getName());
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".x", location.getX());
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".y", location.getY());
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".z", location.getZ());
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".yaw", location.getY());
            skyWars.getConfigUtil().getLocationsConfig().set(locationname.toLowerCase() + ".pitch", location.getPitch());
            try {
                skyWars.getConfigUtil().getLocationsConfig().save(skyWars.getConfigUtil().getLocationsFile());
                loadLocation();
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public void loadLocation() {
        this.lobby = new Location(Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("lobby.world")),
                skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.x"), skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.y"),
                skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.z"), skyWars.getConfigUtil().getLocationsConfig().getLong("lobby.yaw"),
                skyWars.getConfigUtil().getLocationsConfig().getLong("lobby.pitch"));
    }

    public Location getLobby() {
        return lobby;
    }

    public HashMap<Integer, Location> getIsland() {
        return island;
    }
}
