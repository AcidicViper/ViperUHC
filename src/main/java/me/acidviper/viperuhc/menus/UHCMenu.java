package me.acidviper.viperuhc.menus;

import me.acidviper.viperuhc.config.options.*;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import me.acidviper.viperuhc.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class UHCMenu {
    public UHCMenu(Player p) {
        Inventory menu = Bukkit.createInventory(null, 27, "UHC Menu");

        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        item.setItemMeta(meta);

        for (int i = 0; i < 27; i++) menu.setItem(i, item);

        item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
        meta = item.getItemMeta();
        meta.setDisplayName("");
        item.setItemMeta(meta);

        for (int i = 0; i < 27; i += 2) menu.setItem(i, item);

        menu.setItem(0, ItemCreatorUtil.ItemCreator(Material.GOLDEN_APPLE, ChatColor.GOLD + "Healing Information", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Absorption: " + StringUtil.capitalize(Absorption.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Golden Heads: " + StringUtil.capitalize(GoldenHead.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.STRIKETHROUGH + "&8------------------------"
        )));

        menu.setItem(8, ItemCreatorUtil.ItemCreator(Material.POTION, ChatColor.DARK_PURPLE + "Potion Information", Arrays.asList(
               ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Speed: " + StringUtil.capitalize(Speed.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Strength: " + StringUtil.capitalize(Strength.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Splash: " + StringUtil.capitalize(Splash.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.STRIKETHROUGH + "&8------------------------"
                )));

        menu.setItem(10, ItemCreatorUtil.ItemCreator(Material.WATCH, ChatColor.GOLD + "Time Information", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Final Heal: " + FinalHeal.Option.valueOf(FinalHeal.getInstance().getOption()).getNumVal() + " minutes",
                ChatColor.WHITE + "Pvp Time: " + PVPTime.Option.valueOf(PVPTime.getInstance().getOption()).getNumVal() + " minutes",
                ChatColor.WHITE + "First Shrink: " + BorderShrink.Option.valueOf(BorderShrink.getInstance().getOption()).getNumVal() + " minutes",
                ChatColor.STRIKETHROUGH + "&8------------------------"
        )));

        menu.setItem(12, ItemCreatorUtil.ItemCreator(Material.NETHERRACK, ChatColor.RED + "Nether Information", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Nether: " + StringUtil.capitalize(Nether.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Portal Trapping: " + StringUtil.capitalize(PortalTrapping.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.STRIKETHROUGH + "&8------------------------"
        )));

        menu.setItem(14, ItemCreatorUtil.ItemCreator(Material.DIAMOND_PICKAXE, ChatColor.BLUE + "Mining Information", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Stripmining: " + StringUtil.capitalize(StripMining.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Pokeholing: " + StringUtil.capitalize(Pokeholing.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Staircasing: " + StringUtil.capitalize(StairCasing.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Blastmining: " + StringUtil.capitalize(BlastMining.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "F5Abuse: Not Allowed",
                ChatColor.WHITE + "C Counter: " + StringUtil.capitalize(CCounter.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Digging to sounds: " + StringUtil.capitalize(DigToSound.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.STRIKETHROUGH + "&8------------------------"
        )));

        menu.setItem(16, ItemCreatorUtil.ItemCreator(Material.STICK, ChatColor.YELLOW + "General Information", Arrays.asList(
                 ChatColor.STRIKETHROUGH + "&8------------------------",
                 ChatColor.WHITE + "Crossteaming: " + StringUtil.capitalize(CrossTeaming.getInstance().getOption().toLowerCase()).replace('_', ' '),
                 ChatColor.WHITE + "Toggle Sprint: Allowed",
                 ChatColor.WHITE +"Toggle Sneak: " + StringUtil.capitalize(ToggleSneak.getInstance().getOption().toLowerCase()).replace('_', ' '),
                 ChatColor.WHITE +"IPvP: Not Allowed",
                 ChatColor.WHITE +"Team Killing: " + StringUtil.capitalize(TeamKilling.getInstance().getOption().toLowerCase()).replace('_', ' '),
                 ChatColor.WHITE +"Digging down at Meetup: Not Allowed",
                 ChatColor.WHITE +"Stalking: As long as not excessive",
                 ChatColor.WHITE + "Spoiling: Only allowed when alive",
                 ChatColor.STRIKETHROUGH + "&8------------------------"
        )));

        menu.setItem(18, ItemCreatorUtil.ItemCreator(Material.SADDLE, ChatColor.DARK_GRAY + "Horse Information", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE + "Horses: " + StringUtil.capitalize(Horses.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Saddle Drops: " + StringUtil.capitalize(SaddleDrops.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.WHITE + "Horse Armor: " + StringUtil.capitalize(HorseArmor.getInstance().getOption().toLowerCase()).replace('_', ' '),
                ChatColor.STRIKETHROUGH + "&8------------------------"
                )));

        menu.setItem(26, ItemCreatorUtil.ItemCreator(Material.EXP_BOTTLE, ChatColor.GREEN + "Server Info", Arrays.asList(
                ChatColor.STRIKETHROUGH + "&8------------------------",
                ChatColor.WHITE +"Server Owners: AcidViper",
                ChatColor.WHITE + "Season: BETA (Test Games)",
                ChatColor.WHITE +"Developers: AcidViper",
                ChatColor.WHITE +"Welcome to Viper UHC! Glad to see you decided",
                ChatColor.WHITE + "to play one of our games, report any bugs",
                ChatColor.WHITE + "you experience to a staff member",
                ChatColor.STRIKETHROUGH + "&8------------------------"
                )));

        p.openInventory(menu);

    }
}
