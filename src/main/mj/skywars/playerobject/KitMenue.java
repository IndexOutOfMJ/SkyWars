/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.playerobject;

import main.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitMenue {

    private final SkyWars skyWars;
    private Inventory kitMenue;
    private Map<Player, String> playerKit = new HashMap<>();

    public KitMenue(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public synchronized void createKitInv() {
        kitMenue = Bukkit.createInventory(null, getInventorySize(skyWars.getConfigUtil().getKitConfig().getList("KitNames").size()), skyWars.getData().getKitItemName());
        List<String> material = new ArrayList<String>();
        for (String name : skyWars.getConfigUtil().getKits().keySet()) {
            material.clear();
            material.addAll((List<String>) skyWars.getConfigUtil().getKitConfig().getList(name));
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

    public Map<Player, String> getPlayerKit() {
        return playerKit;
    }

    public void setPlayerKit(Player player, String kitName) {
        playerKit.put(player, kitName);
    }

}
