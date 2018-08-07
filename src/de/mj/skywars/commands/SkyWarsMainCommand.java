/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.commands;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SkyWarsMainCommand implements CommandExecutor {

    private final SkyWars skyWars;

    public SkyWarsMainCommand(SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setCommand(this, "skywars");
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            skyWars.getConfigUtil().reloadAllConfigs();
            commandSender.sendMessage(skyWars.getData().getPrefix() + "§aAll configurationfile where successfully reloaded!");
            return true;
        }
        if (args[0].equalsIgnoreCase("setlobby")) {
            skyWars.getGameState().setGameState(GameEnum.LOBBY);
            skyWars.getStartScheduler().setLobbycounter(60);
            commandSender.sendMessage(skyWars.getData().getPrefix() + "§aGameState was set to lobby");
            return true;
        }
        return false;
    }
}
