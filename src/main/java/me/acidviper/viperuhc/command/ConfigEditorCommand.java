package me.acidviper.viperuhc.command;

import me.acidviper.viperuhc.menus.ConfigEditorMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigEditorCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("uhc.editconfig")) {
                sender.sendMessage(ChatColor.GREEN + "Opening edit menu...");
                new ConfigEditorMenu((Player) sender);
            } else sender.sendMessage(ChatColor.RED + "You don't have permission to use that command!");
        } else sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");

        return false;
    }
}
