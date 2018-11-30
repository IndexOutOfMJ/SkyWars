/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.playerobject;

import main.mj.skywars.SkyWars;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class KDManager {

    private final SkyWars skyWars;
    private Map<Player, Integer> kills = new HashMap<>();
    private Map<Player, Integer> deaths = new HashMap<>();

    public KDManager(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void addKills(Player player) {
        int kills = getKills(player) + 1;
        if (skyWars.getData().isMySQLActive()) {
            skyWars.getSqlSkyWars().getPlayerKills(player);
            skyWars.getSqlSkyWars().addPlayerKills(player, kills);
        } else
            skyWars.getConfigUtil().getPlayerConfig().set(player.getName() + ".Kills", kills);
    }

    public Integer getKills(Player player) {
        return kills.get(player);
    }

    public void setKills(Player player, Integer kill) {
        if (skyWars.getData().isMySQLActive()) {
            deaths.put(player, kill);
        } else
            deaths.put(player, skyWars.getConfigUtil().getPlayerConfig().getInt(player.getName() + ".Kills"));
    }

    public void addDeaths(Player player) {
        int deaths = getKills(player) + 1;
        if (skyWars.getData().isMySQLActive()) {
            skyWars.getSqlSkyWars().getPlayerDeaths(player);
            skyWars.getSqlSkyWars().addPlayerKills(player, deaths);
        } else
            skyWars.getConfigUtil().getPlayerConfig().set(player.getName() + ".Kills", deaths);
    }

    public Integer getDeaths(Player player) {
        return deaths.get(player);
    }

    public void setDeaths(Player player, Integer death) {
        if (skyWars.getData().isMySQLActive()) {
            deaths.put(player, death);
        } else
            deaths.put(player, death);
    }

}
