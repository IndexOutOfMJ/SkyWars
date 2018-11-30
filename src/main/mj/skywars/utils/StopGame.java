/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StopGame {

    private final SkyWars skyWars;

    public StopGame(SkyWars skyWars) {
        this.skyWars = skyWars;
        stopGame();
    }

    private void stopGame() {
        skyWars.getSchedulerSaver().cancelScheduler();
        skyWars.getSchedulerSaver().createScheduler(
                new BukkitRunnable() {
                    int stopTimer = 10;

                    @Override
                    public void run() {
                        Bukkit.getOnlinePlayers().forEach(all -> all.sendMessage(skyWars.getData().getPrefix() + "Game stops in " + stopTimer + " Seconds"));
                        if (stopTimer == 0) {
                            cancel();
                            Bukkit.shutdown();
                        }
                        stopTimer--;
                    }
                }.runTaskTimer(skyWars, 0L, 20L)
        );
    }
}
