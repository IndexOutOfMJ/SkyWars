/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.listener;

import de.mj.skywars.SkyWars;
import de.mj.skywars.utils.Data;
import de.mj.skywars.utils.GameEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyClickEvent implements Listener {

    private final SkyWars skyWars;

    public LobbyClickEvent(SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    public void onInteractItem(PlayerInteractEvent interactEvent) {
        Player player = interactEvent.getPlayer();
        Data data = skyWars.getData();
        try {
            if (interactEvent.getItem().getType().equals(data.getStartItemType())) {
                if (skyWars.getStartScheduler().getLobbyCounter() > 10 && skyWars.getGame().getGameState().equals(GameEnum.START)) {
                    skyWars.getStartScheduler().setLobbyCounter(10);
                }
            }
            if (interactEvent.getItem().getType().equals(data.getKitItemType())) {
                player.openInventory(skyWars.getKitMenue().getKitMenue());
            }
            if (interactEvent.getItem().getType().equals(data.getExitItemType()))
                player.kickPlayer(" ");
        } catch (Exception ex) {
            //Do nothing here
        }
    }

    @EventHandler
    /*
        TODO
        LanguageFile
        SelectKit
     */
    public void onInvClick(InventoryClickEvent clickEvent) {
        Player player = (Player) clickEvent.getWhoClicked();
        if (skyWars.getGame().getGameState().equals(GameEnum.LOBBY) || skyWars.getGame().getGameState().equals(GameEnum.START)) {
            if (clickEvent.getClickedInventory().getTitle().equalsIgnoreCase(skyWars.getData().getKitItemName())) {
                skyWars.getKitMenue().setPlayerKit(player, clickEvent.getCurrentItem().getItemMeta().getDisplayName());
                player.sendMessage(skyWars.getData().getPrefix() + "You selected Kit " + clickEvent.getCurrentItem().getItemMeta().getDisplayName());
                if (skyWars.getData().isMySQLActive()) {

                } else
                    skyWars.getConfigUtil().addPlayerKit(player, clickEvent.getCurrentItem().getItemMeta().getDisplayName());
                player.closeInventory();
            }
        }
    }
}
