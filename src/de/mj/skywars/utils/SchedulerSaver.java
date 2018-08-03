package de.mj.skywars.utils;

import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

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
