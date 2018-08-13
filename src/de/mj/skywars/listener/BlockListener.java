/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.GameEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BlockListener implements Listener {

    private final SkyWars skyWars;

    public BlockListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent clickEvent) {
        if (!(skyWars.getGame().getGameState().equals(GameEnum.EQUIP) || skyWars.getGame().getGameState().equals(GameEnum.INGAME)))
            clickEvent.setCancelled(true);
        else
            clickEvent.setCancelled(false);
    }

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent foodLevelChangeEvent) {
        if (skyWars.getGame().getGameState().equals(GameEnum.LOBBY) || skyWars.getGame().getGameState().equals(GameEnum.START)) {
            foodLevelChangeEvent.setFoodLevel(20);
            foodLevelChangeEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent weatherChangeEvent) {
        if (weatherChangeEvent.toWeatherState())
            weatherChangeEvent.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent breakEvent) {
        if (!(skyWars.getGame().getGameState().equals(GameEnum.EQUIP) || skyWars.getGame().getGameState().equals(GameEnum.INGAME)))
            breakEvent.setCancelled(true);
        else
            breakEvent.setCancelled(false);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent placeEvent) {
        if (!(skyWars.getGame().getGameState().equals(GameEnum.EQUIP) || skyWars.getGame().getGameState().equals(GameEnum.INGAME)))
            placeEvent.setCancelled(true);
        else
            placeEvent.setCancelled(false);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent dropItemEvent) {
        if (!(skyWars.getGame().getGameState().equals(GameEnum.EQUIP) || skyWars.getGame().getGameState().equals(GameEnum.INGAME)))
            dropItemEvent.setCancelled(true);
        else
            dropItemEvent.setCancelled(false);
    }
}
