package me.acidviper.viperuhc.command;

import me.acidviper.viperuhc.game.UHCGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScatterCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("scatter") && sender.hasPermission("UHC.scatter")) {
                sender.sendMessage(ChatColor.GREEN + "Attempting to begin scatter!");
                UHCGame.getInstance().start();
            }
        } else sender.sendMessage(ChatColor.RED + "Only a player can scatter!");

        return false;
    }
}
