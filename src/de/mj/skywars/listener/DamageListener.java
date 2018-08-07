/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import de.mj.skywars.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class DamageListener implements Listener {

    private final SkyWars skyWars;

    public DamageListener (@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent damageByEntityEvent) {
        GameState gameState = skyWars.getGameState();
        if (gameState.getGameState().equals(GameEnum.LOBBY) || gameState.getGameState().equals(GameEnum.END)
                || gameState.getGameState().equals(GameEnum.EQUIP))
            damageByEntityEvent.setCancelled(true);
        else damageByEntityEvent.setCancelled(false);
    }

    @EventHandler
    public void onBlockDamager(EntityDamageByBlockEvent damageByBlockEvent) {
        GameState gameState = skyWars.getGameState();
        if (gameState.getGameState().equals(GameEnum.LOBBY) || gameState.getGameState().equals(GameEnum.END))
            damageByBlockEvent.setCancelled(true);
        else damageByBlockEvent.setCancelled(false);
    }

}
