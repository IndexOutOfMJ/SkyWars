package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import de.mj.skywars.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private final SkyWars skyWars;

    public JoinListener (@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        GameState gameState = skyWars.getGameState();
        System.out.println(gameState.getGameState());
        if (Bukkit.getOnlinePlayers().size() == skyWars.getConfigUtil().getDefaultConfig().getInt("PlayerCountToStart"))
            skyWars.getGameState().GameStart();
        if (skyWars.getLocationsUtil().getLobby() == null) {

        } else if (gameState.getGameState().equals(GameEnum.LOBBY))
            player.teleport(skyWars.getLocationsUtil().getLobby());
    }
}
