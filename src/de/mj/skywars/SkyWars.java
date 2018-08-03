package de.mj.skywars;

import de.mj.skywars.listener.JoinListener;
import de.mj.skywars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {

    private ConsoleCommandSender sender;

    //Commands

    //LIstener
    private JoinListener joinListener;

    //Utils
    private ChestDetectionAndFill chestDetectionAndFill;
    private FileUtil fileUtil;
    private GameState gameState;
    private LocationsUtil locationsUtil;

    public void onEnable() {

        init();
        gameState.setGameState(GameEnum.LOBBY);
    }

    public void onDisable() {
        gameState.setGameState(GameEnum.RESTART);
    }

    public void init() {
        //Commands

        //Listener
        joinListener = new JoinListener(this);

        //Utils
        chestDetectionAndFill = new ChestDetectionAndFill(this);
        fileUtil = new FileUtil(this);
        gameState = new GameState();
        locationsUtil = new LocationsUtil(this);
    }

    public void setListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void setCommand (CommandExecutor commandExecutor, String command) {
        getCommand(command).setExecutor(commandExecutor);
    }

    public GameState getGameState () {
        return gameState;
    }

    public ConsoleCommandSender getSender() {
        return sender;
    }

    public JoinListener getJoinListener() {
        return joinListener;
    }

    public ChestDetectionAndFill getChestDetectionAndFill() {
        return chestDetectionAndFill;
    }

    public FileUtil getFileUtil() {
        return fileUtil;
    }

}
