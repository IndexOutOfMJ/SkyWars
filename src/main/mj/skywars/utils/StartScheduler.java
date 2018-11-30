/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import main.mj.skywars.playerobject.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;

public class StartScheduler {

    private final SkyWars skyWars;

    private int lobbyCounter = 60;
    private int timer = 20;

    public StartScheduler(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    private static <K, V> K getFirstKey(HashMap<K, V> map) {
        Iterator<K> i = map.keySet().iterator();
        return i.hasNext() ? i.next() : null;
    }

    void gameStartScheduler() {
        skyWars.getSchedulerSaver().createScheduler(new BukkitRunnable() {
            public void run() {
                if (lobbyCounter == 0) {
                    skyWars.getGame().setGameState(GameEnum.EQUIP);
                    int playerSize = Bukkit.getOnlinePlayers().size();
                    int playerPerTeam = playerSize / skyWars.getTeamManager().getTeams().size();
                    int islandSize = skyWars.getConfigUtil().getLocationsConfig().getConfigurationSection("islands").getKeys(false).size() / skyWars.getTeamManager().getTeams().size();
                    for (Team team : skyWars.getTeamManager().getTeams()) {
                        int b = playerPerTeam;
                        while (b > 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                team.addPlayer(all);
                                all.teleport(new Location(Bukkit.getWorld(skyWars.getConfigUtil().getLocationsConfig().getString("islands." + islandSize + ".world")),
                                        skyWars.getConfigUtil().getLocationsConfig().getDouble("islands." + islandSize + ".x"),
                                        skyWars.getConfigUtil().getLocationsConfig().getDouble("islands." + islandSize + ".y"),
                                        skyWars.getConfigUtil().getLocationsConfig().getDouble("islands." + islandSize + ".z"),
                                        (float) skyWars.getConfigUtil().getLocationsConfig().getDouble("islands." + islandSize + ".yaw"),
                                        (float) skyWars.getConfigUtil().getLocationsConfig().getDouble("islands." + islandSize + ".pitch")));
                                islandSize--;
                            }
                            b--;
                        }
                    }
                    setPlayerKits();
                    equipTimer();
                    cancel();
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setLevel(lobbyCounter);
                }
                lobbyCounter--;
            }
        }.runTaskTimer(skyWars, 0L, 20L));
    }

    private void equipTimer() {
        timer = 20;
        skyWars.getSchedulerSaver().createScheduler(new BukkitRunnable() {
            public void run() {
                if (timer == 0) {
                    skyWars.getGame().setGameState(GameEnum.INGAME);
                    skyWars.getGameScheduler().gameScheduler();
                    cancel();
                }
                timer--;
            }
        }.runTaskTimer(skyWars, 0L, 20L));
    }

    public int getLobbyCounter() {
        return lobbyCounter;
    }

    public void setLobbyCounter(int lobbyCounter) {
        this.lobbyCounter = lobbyCounter;
    }

    private void setPlayerKits() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.getInventory().clear();
            if (skyWars.getData().isMySQLActive())
                skyWars.getSqlSkyWars().getPlayerKit(all);
            if (skyWars.getKitMenue().getPlayerKit().get(all) == null)
                skyWars.getKitMenue().setPlayerKit(all, getFirstKey(skyWars.getConfigUtil().getKits()));
            for (String material : skyWars.getConfigUtil().getKits().get(skyWars.getKitMenue().getPlayerKit().get(all))) {
                all.getInventory().addItem(new ItemStack(Material.getMaterial(material)));
            }
        }
    }
}

