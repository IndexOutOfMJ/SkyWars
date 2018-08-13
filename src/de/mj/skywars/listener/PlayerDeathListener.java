/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.Game;
import de.mj.skywars.utils.GameEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeathListener implements Listener {

    private final SkyWars skyWars;

    public PlayerDeathListener (@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getEntity();
        Game game = skyWars.getGame();
        if (game.getGameState().equals(GameEnum.LOBBY)) {
            player.teleport(skyWars.getLocationsUtil().getLocation("lobby"));
            return;
        }
    }
}
