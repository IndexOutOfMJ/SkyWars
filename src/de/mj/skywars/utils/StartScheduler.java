package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartScheduler {

    private final SkyWars skyWars;

    public StartScheduler (SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void GameStartScheduler() {
        skyWars.getSchedulerSaver().createScheduler(new BukkitRunnable() {
            int startcounter = 60;
            public void run() {
                if (startcounter == 0) {
                    skyWars.getGameState().setGameState(GameEnum.INGAME);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        Integer islands = skyWars.getLocationsUtil().getIsland().size();
                        all.teleport(skyWars.getLocationsUtil().getIsland().get(islands));
                        islands--;
                    }
                    EquipTimer();
                    cancel();
                }
                startcounter--;
            }
        }.runTaskTimer(skyWars, 0L, 20L));
    }

    public void EquipTimer() {
        skyWars.getSchedulerSaver().createScheduler(new BukkitRunnable() {
            int timer = 20;
            public void run() {
                if (timer == 0) {
                    skyWars.getGameState().setGameState(GameEnum.INGAME);
                    cancel();
                }
                timer--;
            }
        }.runTaskTimer(skyWars, 0L, 20L));
    }
}

