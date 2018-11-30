/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.playerobject;

import main.mj.skywars.SkyWars;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamManager {

    private final SkyWars skyWars;
    private Set<Team> teams = new HashSet<>();

    public TeamManager(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void createTeams() {
        for (int i = skyWars.getConfigUtil().getDefaultConfig().getInt("teamSize"); i > 0; i--) {
            int teamid = i;
            teams.add(new Team() {
                @Override
                public Integer getTeamID() {
                    return teamid;
                }

                @Override
                public List<Player> getPlayers() {
                    return null;
                }

                @Override
                public void addPlayer(Player player) {

                }
            });
        }
    }

    public void setTeam(Player player) {
    }

    public Set<Team> getTeams() {
        return teams;
    }
}
