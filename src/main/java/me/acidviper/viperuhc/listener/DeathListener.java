package me.acidviper.viperuhc.listener;

import me.acidviper.viperuhc.config.options.DeathLightning;
import me.acidviper.viperuhc.game.UHCGame;
import me.acidviper.viperuhc.player.UHCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Iterator;
import java.util.Objects;

public class DeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(e.getEntity().getName());
        item.setItemMeta(meta);
        e.getDrops().add(item);

        if (DeathLightning.getInstance().getOption().equalsIgnoreCase("on")) {
            Bukkit.broadcastMessage("test");
            Bukkit.getWorld("uhc_world").strikeLightning(e.getEntity().getLocation());
        }

        if (Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity())).getCurrentState() == UHCPlayer.PlayerState.PLAYING) {
            Iterator<UHCPlayer> iter = UHCGame.getInstance().getGamePlayers().iterator();
            while (iter.hasNext()) {
                Player p = iter.next().getPlayerObject();
                if (p == e.getEntity()) iter.remove();
            }

            if (e.getEntity().getKiller() != null) Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity().getKiller())).setCurrentKills(Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity().getKiller())).getCurrentKills() + 1);

            if (e.getEntity().hasPermission("uhc.spectate")) {
                e.getEntity().teleport(new Location(Bukkit.getWorld("uhc_world"), 0, 100, 0));
                e.getEntity().setGameMode(GameMode.SPECTATOR);

                Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity())).setCurrentState(UHCPlayer.PlayerState.SPECTATING);
            } else {
                Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity())).reset();
                Objects.requireNonNull(UHCPlayer.getUHCPlayer(e.getEntity())).setCurrentState(UHCPlayer.PlayerState.LOBBY);
            }
        }
    }
}
