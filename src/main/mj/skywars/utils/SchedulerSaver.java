/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class SchedulerSaver {

    private List<BukkitTask> schedulers = new ArrayList<BukkitTask>();

    public void createScheduler(BukkitTask task) {
        schedulers.add(task);
    }

    public void cancelScheduler() {
        for (BukkitTask task : schedulers) {
            task.cancel();
        }
    }
}
