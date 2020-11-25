package me.acidviper.viperuhc.listener;

import me.acidviper.viperuhc.player.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.Random;

public class FoodListener implements Listener {

    @EventHandler
    public void foodChange(FoodLevelChangeEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            UHCPlayer up = UHCPlayer.getUHCPlayer(p);

            if(up.currentState != UHCPlayer.PlayerState.PLAYING) e.setFoodLevel(20);

            if (e.getFoodLevel() < ((Player)e.getEntity()).getFoodLevel() && new Random().nextInt(100) > 15) e.setCancelled(true);
        }
    }
}
