/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.listener;

import main.mj.skywars.SkyWars;
import main.mj.skywars.utils.Game;
import main.mj.skywars.utils.GameEnum;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private final SkyWars skyWars;

    public JoinListener (@NotNull SkyWars skyWars) {
        this.skyWars = skyWars;
        skyWars.setListener(this);
    }

    @EventHandler
    /*
    TODO
    LanguageFile
    JoinMessage
     */
    public void onJoin (PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        Game game = skyWars.getGame();
        System.out.println(game.getGameState());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        if (Bukkit.getOnlinePlayers().size() == skyWars.getData().getPlayerCount())
            skyWars.getGame().gameStart();
        if (game.getGameState().equals(GameEnum.LOBBY) && skyWars.getLocationsUtil().getLocation("lobby") != null)
            player.teleport(skyWars.getLocationsUtil().getLocation("lobby"));
        if (game.getGameState().equals(GameEnum.EQUIP) || game.getGameState().equals(GameEnum.INGAME)) {
            player.teleport(skyWars.getLocationsUtil().getLocation("center"));
            player.setGameMode(GameMode.SPECTATOR);
        }
        if (game.getGameState().equals(GameEnum.END) || game.getGameState().equals(GameEnum.RESTART))
            player.kickPlayer(" ");
        if (skyWars.getData().isMySQLActive())
            skyWars.getSqlSkyWars().createPlayer(player);
        else {
            if (skyWars.getConfigUtil().getPlayerConfig().get(player.getName() + "Kills") == null) {
                skyWars.getConfigUtil().getPlayerConfig().set(player.getName() + ".Kills", 0);
                skyWars.getConfigUtil().getPlayerConfig().set(player.getName() + ".Deaths", 0);
            }
        }
        setItems(player);
    }

    private void setItems(Player player) {
        Inventory inventory = player.getInventory();
        inventory.clear();
        if (player.hasPermission("skywars.start"))
            inventory.setItem(1, skyWars.getItemCreator().createItem(skyWars.getData().getStartItemName(), skyWars.getData().getStartItemType(), skyWars.getData().getStartItemLore()));
        inventory.setItem(2, skyWars.getItemCreator().createItem(skyWars.getData().getKitItemName(), skyWars.getData().getKitItemType(), skyWars.getData().getKitItemLore()));
        inventory.setItem(8, skyWars.getItemCreator().createItem(skyWars.getData().getExitItemName(), skyWars.getData().getExitItemType(), skyWars.getData().getExitItemLore()));
    }
}
