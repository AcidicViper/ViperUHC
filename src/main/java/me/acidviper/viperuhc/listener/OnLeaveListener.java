package me.acidviper.viperuhc.listener;

import me.acidviper.viperuhc.player.UHCPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveListener implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        UHCPlayer.getUHCPlayer(e.getPlayer()).remove();
    }
}
