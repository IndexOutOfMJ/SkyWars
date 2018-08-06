package de.mj.skywars;

import de.mj.skywars.commands.SetLocationCommand;
import de.mj.skywars.commands.SkyWarsMainCommand;
import de.mj.skywars.commands.StartCommand;
import de.mj.skywars.listener.DamageListener;
import de.mj.skywars.listener.JoinListener;
import de.mj.skywars.listener.PlayerDeathListener;
import de.mj.skywars.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SkyWars extends JavaPlugin {

    private ConsoleCommandSender sender;

    //Commands
    private SetLocationCommand setLocationCommand;
    private SkyWarsMainCommand skyWarsMainCommand;
    private StartCommand startCommand;

    //Listener
    private DamageListener damageListener;
    private JoinListener joinListener;
    private PlayerDeathListener playerDeathListener;

    //Utils
    private ChestDetectionAndFill chestDetectionAndFill;
    private Data data;
    private ConfigUtil configUtil;
    private GameState gameState;
    private LocationsUtil locationsUtil;
    private SchedulerSaver schedulerSaver;
    private StartScheduler startScheduler;

    public void onEnable() {
        this.saveDefaultConfig();
        init();
        configUtil.loadDefaultConfig();
        configUtil.addDefaultChest();
        gameState.setGameState(GameEnum.LOBBY);
        sender.sendMessage(data.getPrefix() + "§awas successfully enabled!");
    }

    public void onDisable() {
        gameState.setGameState(GameEnum.RESTART);
        schedulerSaver.cancelScheduler();
    }

    private void init() {
        sender = Bukkit.getConsoleSender();

        //Commands
        setLocationCommand = new SetLocationCommand(this);
        skyWarsMainCommand = new SkyWarsMainCommand(this);
        startCommand = new StartCommand(this);

        //Listener
        damageListener = new DamageListener(this);
        joinListener = new JoinListener(this);
        playerDeathListener = new PlayerDeathListener(this);

        //Utils
        chestDetectionAndFill = new ChestDetectionAndFill(this);
        data = new Data();
        configUtil = new ConfigUtil(this);
        gameState = new GameState(this);
        locationsUtil = new LocationsUtil(this);
        schedulerSaver = new SchedulerSaver();
        startScheduler = new StartScheduler(this);
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

    public ConfigUtil getConfigUtil() {
        return configUtil;
    }

    public LocationsUtil getLocationsUtil() {
        return locationsUtil;
    }

    public SchedulerSaver getSchedulerSaver() {
        return schedulerSaver;
    }

    public StartScheduler getStartScheduler() {
        return startScheduler;
    }

    public Data getData() {
        return data;
    }

    public SetLocationCommand getSetLocationCommand() {
        return setLocationCommand;
    }

    public DamageListener getDamageListener() {
        return damageListener;
    }

    public PlayerDeathListener getPlayerDeathListener() {
        return playerDeathListener;
    }

    public StartCommand getStartCommand() {
        return startCommand;
    }

    public SkyWarsMainCommand getSkyWarsMainCommand() {
        return skyWarsMainCommand;
    }
}
