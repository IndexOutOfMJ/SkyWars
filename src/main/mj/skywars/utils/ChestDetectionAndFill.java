/*
 * @author MJ
 * Created in 02.08.2018
 * Copyright (c) 2018 by MJ. All rights reserved.
 */

package main.mj.skywars.utils;

import main.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChestDetectionAndFill {

    private final SkyWars skyWars;

    public ChestDetectionAndFill(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    synchronized void detectChets(String worldname) {
        List<Chunk> chunks = new ArrayList<>(Arrays.asList(Bukkit.getWorld(worldname).getLoadedChunks()));
        for (Chunk chunk : chunks) {
            for (BlockState blockState : chunk.getTileEntities()) {
                if (blockState instanceof Chest) {
                    fillChest((Chest) blockState);
                }
            }
        }
    }

    private synchronized void fillChest(Chest chest) {
        chest.getBlockInventory().clear();
        int chestslot = chest.getBlockInventory().getSize() - 1;
        while (chestslot >= 0) {
            Random random = new Random();
            int itemtype = random.nextInt(skyWars.getConfigUtil().getChestConfig().size() - 1) + 1;
            Material material = Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype));
            if (material != null) {
                ItemStack itemStack = new ItemStack(material);
                if (material != Material.AIR) {
                    if (itemStack.getMaxStackSize() >= 16) {
                        Random rand = new Random();
                        int moreblocks = rand.nextInt(64) + 1;
                        while (moreblocks > 0) {
                            //test if item is already in chest -> only add it, if not put it in to new slot
                            if (chest.getBlockInventory().contains(material))
                                chest.getBlockInventory().addItem(itemStack);
                            else
                                chest.getBlockInventory().setItem(chestslot, itemStack);
                            moreblocks--;
                        }
                    } else if (itemStack.getMaxStackSize() <= 16 && itemStack.getMaxStackSize() > 1) {
                        Random rand = new Random();
                        int moreblocks = rand.nextInt(16) + 1;
                        while (moreblocks > 0) {
                            //test if item is already in chest -> only add it, if not put it in to new slot
                            if (chest.getBlockInventory().contains(material))
                                chest.getBlockInventory().addItem(itemStack);
                            else
                                chest.getBlockInventory().setItem(chestslot, itemStack);
                            moreblocks--;
                        }
                    } else {
                        chest.getBlockInventory().setItem(chestslot, itemStack);
                    }
                } else
                    chest.getBlockInventory().setItem(chestslot, itemStack);
            } else
                chest.getBlockInventory().setItem(chestslot, new ItemStack(Material.AIR));
            chestslot--;
        }
    }
}
