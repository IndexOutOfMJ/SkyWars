/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class LocationsUtil {

    private final SkyWars skyWars;
    private Map<String, Location> locations = new HashMap<String, Location>();
    private Map<Integer, Location> islands = new HashMap<Integer, Location>();

    public LocationsUtil(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public boolean setLocation(Location location, Object locationType) {
        if (isString(locationType)) {
            String locationName = locationType.toString();
            if (locationName.equalsIgnoreCase("lobby")) {
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".world", location.getWorld().getName());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".x", location.getX());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".y", location.getY());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".z", location.getZ());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".yaw", location.getY());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".pitch", location.getPitch());
                try {
                    skyWars.getConfigUtil().getLocationsConfig().save(skyWars.getConfigUtil().getLocationsFile());
                    loadLocation();
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            } else if (locationName.equalsIgnoreCase("center")) {
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".world", location.getWorld().getName());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".x", location.getX());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".y", location.getY());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".z", location.getZ());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".yaw", location.getY());
                skyWars.getConfigUtil().getLocationsConfig().set(locationName.toLowerCase() + ".pitch", location.getPitch());
                try {
                    skyWars.getConfigUtil().getLocationsConfig().save(skyWars.getConfigUtil().getLocationsFile());
                    loadLocation();
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
        } else if (isInteger(locationType)) {
            String locationName = "island." + locationType.toString();
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".world", location.getWorld().getName());
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".x", location.getX());
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".y", location.getY());
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".z", location.getZ());
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".yaw", location.getY());
            skyWars.getConfigUtil().getLocationsConfig().set(locationName + ".pitch", location.getPitch());
            int islands = skyWars.getConfigUtil().getLocationsConfig().getInt("islands");
            islands++;
            skyWars.getConfigUtil().getLocationsConfig().set("islands", islands);
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
        //Lobby loc
        if (Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("lobby.world")) != null) {
            locations.put("lobby", new Location(Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("lobby.world")),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.x"), skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.y"),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble("lobby.z"), skyWars.getConfigUtil().getLocationsConfig().getLong("lobby.yaw"),
                    skyWars.getConfigUtil().getLocationsConfig().getLong("lobby.pitch")));
        }
        //ArenaCenter Location
        /*if (Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("center.world")) != null) {
            locations.put("center", new Location(Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("center.world")),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble("center.x"), skyWars.getConfigUtil().getLocationsConfig().getDouble("center.y"),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble("center.z"), skyWars.getConfigUtil().getLocationsConfig().getLong("center.yaw"),
                    skyWars.getConfigUtil().getLocationsConfig().getLong("center.pitch")));
        }
        */
        //loadIslands();
    }

    private void loadIslands() {
        int islandSize = skyWars.getConfigUtil().getLocationsConfig().getInt("islands");
        while (islandSize > 0) {
            String island = "island." + islandSize;
            locations.put("center", new Location(Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString(island + ".world")),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble(island + ".x"), skyWars.getConfigUtil().getLocationsConfig().getDouble(island + ".y"),
                    skyWars.getConfigUtil().getLocationsConfig().getDouble(island + ".z"), skyWars.getConfigUtil().getLocationsConfig().getLong(island + ".yaw"),
                    skyWars.getConfigUtil().getLocationsConfig().getLong(island + ".pitch")));
            islandSize--;
        }
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    Map<Integer, Location> getIsland() {
        return islands;
    }

    private boolean isInteger(Object object) {
        if (object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();
            try {
                Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean isString(Object object) {
        return object instanceof String;
    }
}
