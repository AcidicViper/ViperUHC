package me.acidviper.viperuhc.game;

import lombok.Getter;
import lombok.Setter;
import me.acidviper.viperuhc.ViperUHC;
import me.acidviper.viperuhc.player.UHCPlayer;
import me.acidviper.viperuhc.runnable.GameRunnable;
import me.acidviper.viperuhc.util.SitUtil;
import me.acidviper.viperuhc.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class UHCGame {
    @Getter private static UHCGame instance;

    @Getter private GameRunnable runnable;

    @Getter @Setter private gameState currentState;
    @Getter @Setter private Player winner;

    @Getter @Setter private int gameTime = 0;
    @Getter @Setter private int currentBorder = 1500;

    @Getter private final ArrayList<UHCPlayer> gamePlayers = new ArrayList<>();

    public UHCGame() {
        instance = this;
        currentState = gameState.WHITELISTED;
    }

    public void start() {
        if (currentState == gameState.PLAYING || currentState == gameState.ENDING || currentState == gameState.GENERATING) return;
        if (!WorldManager.getInstance().isGenned()) ViperUHC.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "World not generated!");
        else {
            currentState = gameState.SCATTERING;
            for (UHCPlayer player : UHCPlayer.getUhcPlayers()) {
                player.setCurrentState(UHCPlayer.PlayerState.PLAYING);
                gamePlayers.add(player);

                Location loc = new Location(Bukkit.getWorld("uhc_world"), new Random().nextInt(1500), 1, new Random().nextInt(1500));
                loc.setY(Bukkit.getWorld("uhc_world").getHighestBlockYAt(loc));

                loc.getChunk().load();
                player.getPlayerObject().teleport(loc);

                SitUtil.freezePlayer(player.getPlayerObject());
            }

            // Waiting for tps to stabilize..
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (UHCPlayer player : gamePlayers) {
                        SitUtil.unFreezePlayer(player.getPlayerObject());
                        player.startGame();
                        currentState = gameState.PLAYING;
                    }

                    runnable = new GameRunnable();
                    runnable.runTaskTimerAsynchronously(ViperUHC.getInstance(), 0, 20);

                    for (UHCPlayer player : gamePlayers) {
                        player.setCurrentState(UHCPlayer.PlayerState.PLAYING);
                        player.setCurrentKills(0);
                    }
                }
            }.runTaskLater(ViperUHC.getInstance(), 20 * 15);

        }
    }

    public void endGame() {
        for (UHCPlayer player : gamePlayers) {
            winner = player.getPlayerObject();
            currentState = gameState.ENDING;

            // TODO: Do celebration then restart after 30 seconds
        }
    }

    public enum gameState { WHITELISTED, GENERATING, UNWHITELISTED, SCATTERING, PLAYING, ENDING }
}
