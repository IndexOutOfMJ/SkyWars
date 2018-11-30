/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScheduler {

    private final SkyWars skyWars;

    public GameScheduler(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void gameScheduler() {
        skyWars.getSchedulerSaver().createScheduler(
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int alife = 0;
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.getGameMode().equals(GameMode.SURVIVAL)) {
                                alife++;
                            }
                        }
                        if (alife == 1) {
                            new StopGame(skyWars);
                            cancel();
                        }
                    }
                }.runTaskTimer(skyWars, 0L, 20L)
        );
    }
}
