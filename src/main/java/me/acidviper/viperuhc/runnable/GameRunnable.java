package me.acidviper.viperuhc.runnable;

import com.wimbli.WorldBorder.WorldBorder;
import me.acidviper.viperuhc.ViperUHC;
import me.acidviper.viperuhc.config.options.BorderShrink;
import me.acidviper.viperuhc.config.options.FinalHeal;
import me.acidviper.viperuhc.config.options.GoldenHead;
import me.acidviper.viperuhc.config.options.PVPTime;
import me.acidviper.viperuhc.game.UHCGame;
import me.acidviper.viperuhc.player.UHCPlayer;
import me.acidviper.viperuhc.util.BorderUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Recipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;

public class GameRunnable extends BukkitRunnable {
    int nextBorder;
    int finalHealTime;
    int pvpTime;

    boolean pvpEnabled = false;

    boolean oneMinuteWarning = false;
    boolean tenSecondTimer = false;

    boolean oneMinutePvpWarning = false;
    boolean tenSecondPvpTimer = false;
    boolean fiveMinutePvpWarning = false;

    boolean oneMinuteHealWarning = false;

    boolean finalHealed = false;

    int taskID = 0;

    public GameRunnable() {
        Bukkit.getWorld("uhc_world").setPVP(false);
        Bukkit.getWorld("uhc_world").setGameRuleValue("naturalRegeneration", "false");

        nextBorder = BorderShrink.Option.valueOf(BorderShrink.getInstance().getOption()).getNumVal() * 60;
        finalHealTime = FinalHeal.Option.valueOf(FinalHeal.getInstance().getOption()).getNumVal() * 60;
        pvpTime = PVPTime.Option.valueOf(PVPTime.getInstance().getOption()).getNumVal() * 60;

        if (GoldenHead.getInstance().getOption().equalsIgnoreCase("off")) {
            Iterator<Recipe> iter = getServer().recipeIterator();
            while (iter.hasNext()) {
                Recipe r = iter.next();
                if (r == ViperUHC.getInstance().getGoldenHead()) iter.remove();
            }
        }
    }

    public void run() {
        UHCGame.getInstance().setGameTime(UHCGame.getInstance().getGameTime() + 1);

        // GAME WIN LOGIC
        if (UHCGame.getInstance().getGamePlayers().size() < 2) { UHCGame.getInstance().endGame(); }

        // FINAL HEAL LOGIC
        if (UHCGame.getInstance().getGameTime() >= (finalHealTime) - 60 && !oneMinuteHealWarning) {
            oneMinuteHealWarning = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + "One minute until final heal!");
        }

        if (UHCGame.getInstance().getGameTime() >= (finalHealTime) - 10 && !finalHealed) {
            finalHealed = true;

            new BukkitRunnable() {
                public void run () {
                    int i = (finalHealTime) - UHCGame.getInstance().getGameTime();
                    if (i <= 0) {
                        Bukkit.broadcastMessage(ChatColor.GREEN + "All players have been given final heal. Please don't ask for another one.");
                        for (UHCPlayer player : UHCPlayer.getUhcPlayers()) if (player.getCurrentState() == UHCPlayer.PlayerState.PLAYING) player.getPlayerObject().setHealth(20);
                    } else Bukkit.broadcastMessage(ChatColor.GREEN + "" + i + "seconds until final heal");
                }
            }.runTaskTimerAsynchronously(ViperUHC.getInstance(), 0, 20);
        }

        // PVP LOGIC
        if (UHCGame.getInstance().getGameTime() >= (pvpTime) - 60 * 5 && !fiveMinutePvpWarning) {
            fiveMinutePvpWarning = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + "5 minutes until pvp enables!");
        }

        if (UHCGame.getInstance().getGameTime() >= (pvpTime) - 60 && !oneMinutePvpWarning) {
            oneMinutePvpWarning = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + "One minute until pvp enables!");
        }

        if (UHCGame.getInstance().getGameTime() >= (pvpTime) - 10 && !tenSecondPvpTimer) {
            tenSecondPvpTimer = true;
            new BukkitRunnable() {
                @Override
                public void run () {
                    int i = (pvpTime) - UHCGame.getInstance().getGameTime();
                    if (i <= 0) {
                        pvpEnabled = true;

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Pvp has been enabled! Players can now attack each others!");
                        Bukkit.getWorld("uhc_world").setPVP(true);
                    } else Bukkit.broadcastMessage(ChatColor.GREEN + "" + i + "seconds until pvp is enabled");
                }
            }.runTaskTimerAsynchronously(ViperUHC.getInstance(), 0, 20);
        }

        // ALL BORDER LOGIC
        if (UHCGame.getInstance().getGameTime() >= nextBorder - 60 && !oneMinuteWarning) {
            oneMinuteWarning = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + "One minute until border shrinks to " + nextBorder(UHCGame.getInstance().getCurrentBorder()) + " radius!");
        }

        if (UHCGame.getInstance().getGameTime() >= nextBorder - 10 && !tenSecondTimer) {
            tenSecondTimer = true;
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(ViperUHC.getInstance(), () -> {
                    int i = nextBorder - UHCGame.getInstance().getGameTime();
                    if (i <= 0) {
                        BorderUtil.bedrockBorder(nextBorder(UHCGame.getInstance().getCurrentBorder()), Bukkit.getWorld("uhc_world"));
                        WorldBorder.plugin.getWorldBorder("uhc_world").setData(0, 0, nextBorder(UHCGame.getInstance().getCurrentBorder()), false);
                        UHCGame.getInstance().setCurrentBorder(nextBorder(UHCGame.getInstance().getCurrentBorder()));

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Border has shrunk to " + nextBorder(UHCGame.getInstance().getCurrentBorder()) + " radius!");

                        if (nextBorder(UHCGame.getInstance().getCurrentBorder()) != UHCGame.getInstance().getCurrentBorder()) {
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Border will shrink to " + nextBorder(UHCGame.getInstance().getCurrentBorder()) + " radius in five minutes!");

                            nextBorder += 60 * 5;
                            oneMinuteWarning = false;
                            tenSecondTimer = false;

                            endTask(taskID);
                        }
                    } else Bukkit.broadcastMessage(ChatColor.GREEN + "" + i + " seconds until border shrinks to " + nextBorder(UHCGame.getInstance().getCurrentBorder()) + " radius!");
            }, 0, 20);
         }
    }

    public int nextBorder(int radius) {
        if (radius - 500 >= 500) return radius - 500;
        else if (radius == 500) return 250;
        else if (radius == 250) return 100;
        else if (radius == 100) return 50;

        return radius;
    }

    public void endTask(int task) { Bukkit.getScheduler().cancelTask(task); }
}
