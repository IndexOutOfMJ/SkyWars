package de.mj.skywars.utils;

import de.mj.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
        chest.getBlockInventory().clear();
        int chestslot = 26;
        while (chestslot >= 0) {
            Random random = new Random();
            int itemtype = random.nextInt(skyWars.getConfigUtil().getChestConfig().size()-1) + 1;
            if (new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))) instanceof Block) {
                Random rand = new Random();
                int moreblocks = rand.nextInt(64) + 1;
                while (moreblocks > 0) {
                    chest.getBlockInventory().addItem(new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
                    moreblocks--;
                }
            } else if (chest.getBlockInventory().contains(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))) && (Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))) != Material.AIR)
                chest.getBlockInventory().addItem(new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
            else
                chest.getBlockInventory().setItem(chestslot, new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
            chestslot--;
        }
    }
}
