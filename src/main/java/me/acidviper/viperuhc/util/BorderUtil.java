package me.acidviper.viperuhc.util;

import me.acidviper.viperuhc.ViperUHC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BorderUtil {
    public static void bedrockBorder(int radius, World world) {
        ArrayList<Block> block1 = new ArrayList<>();
        for (int i = radius; i > -radius; i--) {
            Location loc = new Location(world, radius, 0, i);
            loc.setY(world.getHighestBlockYAt(loc) + 1);
            block1.add(loc.getBlock());
        }

        ArrayList<Block> block2 = new ArrayList<>();
        for (int i = radius; i > -radius; i--) {
            Location loc = new Location(world, -radius, 0, i);
            loc.setY(world.getHighestBlockYAt(loc) + 1);
            block2.add(loc.getBlock());
        }

        ArrayList<Block> block3 = new ArrayList<>();
        for (int i = radius; i > -radius; i--) {
            Location loc = new Location(world, i, 0, radius);
            loc.setY(world.getHighestBlockYAt(loc) + 1);
            block3.add(loc.getBlock());
        }

        ArrayList<Block> block4 = new ArrayList<>();
        for (int i = radius; i > -radius; i--) {
            Location loc = new Location(world, i, 0, -radius);
            loc.setY(world.getHighestBlockYAt(loc) + 1);
            block4.add(loc.getBlock());
        }

        new BukkitRunnable() { public void run() {
            for (Block block : block1) block.setType(Material.BEDROCK);
        } }.runTaskLater(ViperUHC.getInstance(), 2);

        new BukkitRunnable() { public void run() {
            for (Block block : block2) block.setType(Material.BEDROCK);
        } }.runTaskLater(ViperUHC.getInstance(), 4);

        new BukkitRunnable() { public void run() {
            for (Block block : block3) block.setType(Material.BEDROCK);
        } }.runTaskLater(ViperUHC.getInstance(), 6);

        new BukkitRunnable() { public void run() {
            for (Block block : block4) block.setType(Material.BEDROCK);
        } }.runTaskLater(ViperUHC.getInstance(), 8);
    }

}
