/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.playerobject;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class KitMenue {

    private final SkyWars skyWars;
    private Inventory kitMenue;
    private HashMap<Player, String> playerKit = new HashMap<>();

    public KitMenue(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public synchronized void createKitInv() {
        kitMenue = Bukkit.createInventory(null, getInventorySize(skyWars.getConfigUtil().getKitConfig().getList("KitNames").size()), skyWars.getData().getKitItemName());
        ArrayList<String> material = new ArrayList<String>();
        for (String name : skyWars.getConfigUtil().getKits().keySet()) {
            material.clear();
            material.addAll((ArrayList<String>) skyWars.getConfigUtil().getKitConfig().getList(name));
            ItemStack kitStack = new ItemStack(Material.getMaterial(material.get(0)));
            ItemMeta kitMeta = kitStack.getItemMeta();
            kitMeta.setLore(material);
            kitMeta.setDisplayName(name);
            kitStack.setItemMeta(kitMeta);
            kitMenue.addItem(kitStack);
        }
    }

    private int getInventorySize(int n) {
        int rows = n / 9;
        if (rows * 9 < n) {
            rows++;
        }
        return rows * 9;
    }

    public Inventory getKitMenue() {
        return kitMenue;
    }

    public HashMap<Player, String> getPlayerKit() {
        return playerKit;
    }

    public void setPlayerKit(Player player, String kitName) {
        playerKit.put(player, kitName);
    }

}
