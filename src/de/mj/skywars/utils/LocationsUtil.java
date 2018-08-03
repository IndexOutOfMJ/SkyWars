package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationsUtil {

    private final SkyWars skyWars;
    private Location spawn;

    public LocationsUtil (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void setLocation (Location location, String locationname) {
        if (locationname.equalsIgnoreCase("spawn")) {
            this.spawn = location;
            YamlConfiguration spawncfg = skyWars.getFileUtil().getLocationsConfig();

        }
    }

    public Location getSpawn() {
        return spawn;
    }
}
