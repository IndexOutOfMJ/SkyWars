/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.listener;

import main.mj.skywars.SkyWars;
import main.mj.skywars.utils.Game;
import main.mj.skywars.utils.GameEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;

public class DamageListener implements Listener {

    private final SkyWars skyWars;

    public DamageListener (@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent damageEvent) {
        if (!(damageEvent.getEntity() instanceof Player))
            return;
        Game game = skyWars.getGame();
        if (game.getGameState().equals(GameEnum.LOBBY) || game.getGameState().equals(GameEnum.END) || game.getGameState().equals(GameEnum.START)) {
            damageEvent.setCancelled(true);
        } else if (game.getGameState().equals(GameEnum.EQUIP)) {
            if (damageEvent.getCause().equals(DamageCause.ENTITY_ATTACK) || damageEvent.getCause().equals(DamageCause.CONTACT))
                damageEvent.setCancelled(true);
            else damageEvent.setCancelled(false);
        } else
            damageEvent.setCancelled(false);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent damageByEntityEvent) {
        Game game = skyWars.getGame();
        if (game.getGameState().equals(GameEnum.LOBBY) || game.getGameState().equals(GameEnum.END)
                || game.getGameState().equals(GameEnum.EQUIP))
            damageByEntityEvent.setCancelled(true);
        else damageByEntityEvent.setCancelled(false);
    }

    @EventHandler
    public void onBlockDamager(EntityDamageByBlockEvent damageByBlockEvent) {
        Game game = skyWars.getGame();
        if (game.getGameState().equals(GameEnum.LOBBY) || game.getGameState().equals(GameEnum.END))
            damageByBlockEvent.setCancelled(true);
        else damageByBlockEvent.setCancelled(false);
    }

}
