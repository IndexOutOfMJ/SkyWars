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

    public void DetectChets(String worldname) {
        ArrayList<Chunk> chunks = new ArrayList<Chunk>(Arrays.asList(Bukkit.getWorld(worldname).getLoadedChunks()));
        for (Chunk chunk : chunks) {
            for (BlockState blockState : chunk.getTileEntities()) {
                if (blockState instanceof Chest) {
                    FillChest((Chest) blockState);
                }
            }
        }
    }

    public void FillChest(Chest chest) {
        int chestslot = 26;
        while (chestslot >= 0) {
            Random random = new Random();
            int itemtype = random.nextInt(skyWars.getConfigUtil().getChestConfig().size()-1) + 1;
            chest.getBlockInventory().addItem(new ItemStack(Material.getMaterial(skyWars.getConfigUtil().getChestConfig().get(itemtype))));
            chestslot--;
        }
    }
}
