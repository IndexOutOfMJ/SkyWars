/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ChestDetectionAndFill {

    private final SkyWars skyWars;

    public ChestDetectionAndFill(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    synchronized void DetectChets(String worldname) {
        ArrayList<Chunk> chunks = new ArrayList<Chunk>(Arrays.asList(Bukkit.getWorld(worldname).getLoadedChunks()));
        for (Chunk chunk : chunks) {
            for (BlockState blockState : chunk.getTileEntities()) {
                if (blockState instanceof Chest) {
                    FillChest((Chest) blockState);
                }
            }
        }
    }

    private synchronized void FillChest(Chest chest) {
        //clear chest
        chest.getBlockInventory().clear();
        //get size of chest
        int chestslot = chest.getBlockInventory().getSize();
        //fill items in chest
        while (chestslot >= 0) {
            Random random = new Random();
            //get item list from config
            int itemtype = random.nextInt(skyWars.getConfigUtil().getChestConfig().size() - 1) + 1;
            //test max stack size of item is bigger than 16 and test if it is AIR
            if (new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))).getMaxStackSize() > 16 && (Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))) != Material.AIR) {
                Random rand = new Random();
                int moreblocks = rand.nextInt(64) + 1;
                while (moreblocks > 0) {
                    //test if item is already in chest -> only add it, if not put it in to new slot
                    if (chest.getBlockInventory().contains(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))))
                        chest.getBlockInventory().addItem(new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
                    else
                        chest.getBlockInventory().setItem(chestslot, new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
                    moreblocks--;
                }
                //test max stack size of item is between than 1 - 17 and test if it is AIR
            } else if (new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))).getMaxStackSize() <= 16 && new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))).getMaxStackSize() > 1 && (Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))) != Material.AIR) {
                Random rand = new Random();
                int moreblocks = rand.nextInt(16) + 1;
                while (moreblocks > 0) {
                    //test if item is already in chest -> only add it, if not put it in to new slot
                    if (chest.getBlockInventory().contains(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))))
                        chest.getBlockInventory().addItem(new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
                    else
                        chest.getBlockInventory().setItem(chestslot, new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
                    moreblocks--;
                }
                //if item is air or can not be stacked put it into new slot
            } else
                chest.getBlockInventory().setItem(chestslot, new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
            chestslot--;
        }
    }
}
