package de.mj.skywars.commands;

import de.mj.skywars.SkyWars;
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
        if (skyWars.getStartScheduler().getLobbycounter() > 10) {
            skyWars.getStartScheduler().setLobbycounter(10);
            return true;
        }
        return false;
    }
}
