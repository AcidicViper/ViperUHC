package me.acidviper.viperuhc.menus;

import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.SafeInventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ConfigEditorMenu {
    public ConfigEditorMenu(Player p) {
        Inventory inventory = Bukkit.createInventory(null, SafeInventoryUtil.safestInventorySize(MatchOptions.getOptions().size()), "Config Editor");

        int i = 0;
        for (MatchOptions options : MatchOptions.getOptions()) {
            ItemStack item = options.item;
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            for (String name : options.getOptionOptions()) {
                if (name.equalsIgnoreCase(options.getOption())) lore.add(ChatColor.GREEN + name);
                else lore.add(ChatColor.RED + name);
            }

            meta.setLore(lore);
            options.item.setItemMeta(meta);
            inventory.setItem(i, options.item);

            i++;
        }

        p.openInventory(inventory);
    }
}
