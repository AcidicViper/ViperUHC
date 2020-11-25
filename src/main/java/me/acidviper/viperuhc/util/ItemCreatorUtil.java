package me.acidviper.viperuhc.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemCreatorUtil {
    public static ItemStack ItemCreator(Material mat, String name, List<String> list) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(list);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack ItemCreator(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }
}
