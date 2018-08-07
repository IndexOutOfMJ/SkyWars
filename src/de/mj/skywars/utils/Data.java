/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

public class Data {

    private String prefix;
    private int playercount;
    private boolean setup;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPlayercount() {
        return playercount;
    }

    public void setPlayercount(int playercount) {
        this.playercount = playercount;
    }

    public boolean isSetup() {
        return setup;
    }

    public void setSetup(boolean setup) {
        this.setup = setup;
    }
}
