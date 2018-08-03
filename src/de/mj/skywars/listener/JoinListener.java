package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final SkyWars skyWars;

    public JoinListener (SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    public void onJoin (PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        if (Bukkit.getOnlinePlayers().size() == skyWars.getFileUtil().getDefaultConfig().getInt("PlayerCountToStart"))
            skyWars.getGameState().GameStart();
    }
}
