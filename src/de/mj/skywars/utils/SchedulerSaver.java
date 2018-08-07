/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class SchedulerSaver {

    private ArrayList<BukkitTask> schedulers = new ArrayList<BukkitTask>();

    public void createScheduler(BukkitTask task) {
        schedulers.add(task);
    }

    public void cancelScheduler() {
        for (BukkitTask task : schedulers) {
            task.cancel();
        }
    }
}
