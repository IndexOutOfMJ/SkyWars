package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import de.mj.skywars.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private final SkyWars skyWars;

    public DamageListener (SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent damageEvent) {
        GameState gameState = skyWars.getGameState();
        if (gameState.getGameState().equals(GameEnum.LOBBY) || gameState.getGameState().equals(GameEnum.END))
            damageEvent.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent damageByEntityEvent) {
        GameState gameState = skyWars.getGameState();
        if (gameState.getGameState().equals(GameEnum.LOBBY) || gameState.getGameState().equals(GameEnum.END))
            damageByEntityEvent.setCancelled(true);
    }

}
