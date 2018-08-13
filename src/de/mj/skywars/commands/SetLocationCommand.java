/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.commands;

import de.mj.skywars.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLocationCommand implements CommandExecutor {

    private final SkyWars skyWars;

    public SetLocationCommand(@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setCommand(this, "setlocation");
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("lobby")) {
                    if (skyWars.getLocationsUtil().setLocation(player.getLocation(), "lobby")) {
                        player.sendMessage(skyWars.getData().getPrefix() + "Location wurde erfolgreich gesetzt!");
                    } else {
                        player.sendMessage(skyWars.getData().getPrefix() + "Es ist ein Fehler aufgetreten!");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("center")) {
                    if (skyWars.getLocationsUtil().setLocation(player.getLocation(), "center")) {
                        player.sendMessage(skyWars.getData().getPrefix() + "Location wurde erfolgreich gesetzt!");
                    } else {
                        player.sendMessage(skyWars.getData().getPrefix() + "Es ist ein Fehler aufgetreten!");
                    }
                    return true;
                }
                if (isInteger(args[0])) {
                    int island = Integer.valueOf(args[0]);
                    if (skyWars.getLocationsUtil().setLocation(player.getLocation(), island)) {
                        player.sendMessage(skyWars.getData().getPrefix() + "Location wurde erfolgreich gesetzt!");
                    } else {
                        player.sendMessage(skyWars.getData().getPrefix() + "Es ist ein Fehler aufgetreten!");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInteger(Object object) {
        if (object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();
            try {
                Integer.parseInt(string);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
