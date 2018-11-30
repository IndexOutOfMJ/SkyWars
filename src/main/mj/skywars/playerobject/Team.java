/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.playerobject;

import org.bukkit.entity.Player;

import java.util.List;

public interface Team {
    Integer getTeamID();

    List<Player> getPlayers();

    void addPlayer(Player player);
}
