/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.mysql;

import main.mj.skywars.SkyWars;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MySQLManager {

    private final SkyWars skyWars;
    private File f = new File("plugins/SkyWars/", "MySQL.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
    private String host;
    private int port;
    private String user;
    private String pw;
    private String db;

    public MySQLManager(SkyWars skyWars) {
        this.skyWars = skyWars;
        loadMySQL();
    }

    private boolean loadConf() {
        skyWars.getSender().sendMessage(skyWars.getData().getPrefix() + "§dload config.yml");
        if (f.exists()) {
            host = cfg.getString("host");
            port = cfg.getInt("port");
            user = cfg.getString("username");
            pw = cfg.getString("password");
            db = cfg.getString("database");
            return true;
        } else {
            skyWars.getSender().sendMessage(skyWars.getData().getPrefix() + "§cMySQL-File created! Please setup the MySQL.yml!");
            cfg.set("host", "localhost");
            cfg.set("port", 3306);
            cfg.set("username", "user");
            cfg.set("password", "yourpw");
            cfg.set("database", "db");
            try {
                cfg.save(f);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    private void loadMySQL() {
        if (loadConf()) {
            skyWars.getSender().sendMessage(skyWars.getData().getPrefix() + "§dtry to connect to MySQL");
            new AsyncMySQL(skyWars, host, port, user, pw, db);
            skyWars.getSqlSkyWars().createTables();
        }
    }
}
