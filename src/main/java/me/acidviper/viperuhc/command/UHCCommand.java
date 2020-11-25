package me.acidviper.viperuhc.command;

import me.acidviper.viperuhc.menus.UHCMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UHCCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "Opening UHC menu...");
            new UHCMenu((Player) sender);
        } else sender.sendMessage(ChatColor.RED + "You must be a player to use that command!");

        return false;
    }
}
