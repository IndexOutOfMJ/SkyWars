/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.mysql;

import de.mj.skywars.SkyWars;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

public class SQLSkyWars {

    private final SkyWars skyWars;
    private AsyncMySQL asyncMySQL;

    public SQLSkyWars(SkyWars skyWars) {
        this.skyWars = skyWars;
        asyncMySQL = skyWars.getAsyncMySQL();
    }

    void createTables() {
        AsyncMySQL.update("CREATE TABLE IF NOT EXIST SkyWars (player_uuid VARCHAR(50), kit VARCHAR(50), kills INTEGER, deaths INTEGER)");
    }

    public void createPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        AsyncMySQL.update("INSERT INTO SkyWars (UUID, KIT) SELECT '" + uuid + "', '" + skyWars.getKitMenue().getPlayerKit().get(player) + "' FROM DUAL WHERE NOT EXISTS (SELECT '*' FROM SkyWars WHERE UUID = '" + uuid + "');");
    }

    public void getPlayerKit(Player player) {
        UUID uuid = player.getUniqueId();
        asyncMySQL.query("SELECT * FROM SkyWars WHERE UUID='" + uuid + "'", resultSet -> {
            try {
                if (resultSet.next()) {
                    String kit = resultSet.getString("kit");
                    skyWars.getKitMenue().setPlayerKit(player, kit);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setPlayerKit(Player player, String kit) {
        UUID uuid = player.getUniqueId();
        AsyncMySQL.update("UPDATE SkyWars SET kit='" + kit + "' WHERE UUID='" + uuid + "'");
    }

    public void getPlayerKills(Player player) {
        UUID uuid = player.getUniqueId();
        asyncMySQL.query("SELECT * FROM SkyWars WHERE UUID='" + uuid + "'", resultSet -> {
            try {
                if (resultSet.next()) {
                    int kills = resultSet.getInt("kills");

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void addPlayerKills(Player player, Integer kills) {
        UUID uuid = player.getUniqueId();
        AsyncMySQL.update("UPDATE SkyWars SET kit='" + kills + "' WHERE UUID='" + uuid + "'");
    }

    public void getPlayerDeaths(Player player) {
        UUID uuid = player.getUniqueId();
        asyncMySQL.query("SELECT * FROM SkyWars WHERE UUID='" + uuid + "'", resultSet -> {
            try {
                if (resultSet.next()) {
                    int deaths = resultSet.getInt("deaths");
                    skyWars.getKdManager().setDeaths(player, deaths);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void addPlayerDeaths(Player player, Integer deaths) {
        UUID uuid = player.getUniqueId();
        AsyncMySQL.update("UPDATE SkyWars SET kit='" + deaths + "' WHERE UUID='" + uuid + "'");
    }

}
