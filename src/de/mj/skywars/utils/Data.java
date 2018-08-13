/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

import org.bukkit.Material;

import java.util.ArrayList;

public class Data {

    private String prefix;

    private String startItemName;
    private ArrayList<String> startItemLore;
    private Material startItemType;

    private String kitItemName;
    private ArrayList<String> kitItemLore;
    private Material kitItemType;

    private String exitItemName;
    private ArrayList<String> exitItemLore;
    private Material exitItemType;

    private int playerCount;

    private boolean setup;

    private boolean mySQLActive;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public boolean isSetup() {
        return setup;
    }

    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    public String getStartItemName() {
        return startItemName;
    }

    public void setStartItemName(String startItemName) {
        this.startItemName = startItemName;
    }

    public ArrayList<String> getStartItemLore() {
        return startItemLore;
    }

    public void setStartItemLore(ArrayList<String> startItemLore) {
        this.startItemLore = startItemLore;
    }

    public Material getStartItemType() {
        return startItemType;
    }

    public void setStartItemType(Material startItemType) {
        this.startItemType = startItemType;
    }

    public String getKitItemName() {
        return kitItemName;
    }

    public void setKitItemName(String kitItemName) {
        this.kitItemName = kitItemName;
    }

    public ArrayList<String> getKitItemLore() {
        return kitItemLore;
    }

    public void setKitItemLore(ArrayList<String> kitItemLore) {
        this.kitItemLore = kitItemLore;
    }

    public Material getKitItemType() {
        return kitItemType;
    }

    public void setKitItemType(Material kitItemType) {
        this.kitItemType = kitItemType;
    }

    public String getExitItemName() {
        return exitItemName;
    }

    public void setExitItemName(String exitItemName) {
        this.exitItemName = exitItemName;
    }

    public ArrayList<String> getExitItemLore() {
        return exitItemLore;
    }

    public void setExitItemLore(ArrayList<String> exitItemLore) {
        this.exitItemLore = exitItemLore;
    }

    public Material getExitItemType() {
        return exitItemType;
    }

    public void setExitItemType(Material exitItemType) {
        this.exitItemType = exitItemType;
    }

    public boolean isMySQLActive() {
        return mySQLActive;
    }

    public void setMySQLActive(boolean mySQLActive) {
        this.mySQLActive = mySQLActive;
    }
}
