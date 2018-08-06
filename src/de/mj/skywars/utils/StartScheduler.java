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
                    skyWars.getGameState().setGameState(GameEnum.EQUIP);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        Integer islands = skyWars.getLocationsUtil().getIsland().size();
                        if (skyWars.getLocationsUtil().getIsland().get(islands) !=null)
                            all.teleport(skyWars.getLocationsUtil().getIsland().get(islands));
                        skyWars.getChestDetectionAndFill().DetectChets(all.getWorld().getName());
                        islands--;
                    }
                    EquipTimer();
                    cancel();
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setLevel(startcounter);
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
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.setLevel(timer);
                }
                timer--;
            }
        }.runTaskTimer(skyWars, 0L, 20L));
    }
}

