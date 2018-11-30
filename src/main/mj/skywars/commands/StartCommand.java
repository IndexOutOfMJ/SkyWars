/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.commands;

import main.mj.skywars.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private final SkyWars skyWars;

    public StartCommand(SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setCommand(this, "start");
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (skyWars.getStartScheduler().getLobbyCounter() > 10) {
            skyWars.getStartScheduler().setLobbyCounter(10);
            return true;
        }
        return false;
    }
}
