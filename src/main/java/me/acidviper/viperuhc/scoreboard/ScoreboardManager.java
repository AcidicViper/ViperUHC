package me.acidviper.viperuhc.scoreboard;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.acidviper.viperuhc.ViperUHC;
import me.acidviper.viperuhc.game.UHCGame;
import me.acidviper.viperuhc.player.UHCPlayer;
import me.acidviper.viperuhc.util.DecimalUtil;
import me.acidviper.viperuhc.world.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardManager implements AssembleAdapter {
    public String getTitle(Player player) { return ChatColor.GREEN + "Viper UHC"; }

    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&0&m-------------------------");

        if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.SCATTERING) lines.add(ChatColor.GREEN + "Players are being scattered....");
        else if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.PLAYING) {
            if (UHCGame.getInstance().getGameTime() % 60 < 10) lines.add(UHCGame.getInstance().getGameTime() / 60 + ":0" + UHCGame.getInstance().getGameTime() % 60);
            else lines.add(UHCGame.getInstance().getGameTime() / 60 + ":" + UHCGame.getInstance().getGameTime() % 60);

            lines.add("");
            lines.add(ChatColor.GREEN + "Kills: " + UHCPlayer.getUHCPlayer(player).getCurrentKills());
            lines.add(ChatColor.GREEN + "Players left: " + UHCGame.getInstance().getGamePlayers().size());
            lines.add("");
            lines.add(ChatColor.GREEN + "Current Border Radius: " + UHCGame.getInstance().getCurrentBorder());
            lines.add("");
            lines.add("na1.viperuhc.com");
        } else if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.WHITELISTED) lines.add(ChatColor.GREEN + "Whitelisted....");
        else if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.UNWHITELISTED) {
            lines.add(ChatColor.GREEN + "Waiting for players!");
            lines.add("");
            lines.add(ChatColor.GREEN + "Players: " + ViperUHC.getInstance().getServer().getOnlinePlayers().size());
        }  else if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.ENDING) {
            lines.add("This game is over. Congrats to the winner!");
            lines.add("");
            lines.add(ChatColor.GREEN + "Kills: " + UHCPlayer.getUHCPlayer(player).getCurrentKills());
        } else if (UHCGame.getInstance().getCurrentState() == UHCGame.gameState.GENERATING) {
            lines.add("Currently generating world....");
            lines.add("Generating world: " + WorldManager.getInstance().getCurrentGenning().toString());
            lines.add("Progress: " + DecimalUtil.round(WorldManager.getInstance().getFillTask().getPercentageCompleted()) + "%");
        }

        lines.add("&0&m-------------------------");
        return lines;
    }
}
