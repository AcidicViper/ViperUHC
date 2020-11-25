package me.acidviper.viperuhc.listener;

import me.acidviper.viperuhc.player.UHCPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class OnJoinListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        new UHCPlayer(e.getPlayer());
    }

}
