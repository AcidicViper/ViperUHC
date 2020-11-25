package me.acidviper.viperuhc.world;

import com.wimbli.WorldBorder.WorldFillTask;
import lombok.Getter;
import lombok.Setter;
import me.acidviper.viperuhc.ViperUHC;
import me.acidviper.viperuhc.game.UHCGame;
import me.acidviper.viperuhc.util.BorderUtil;
import org.bukkit.*;

import java.io.File;

public class WorldManager {
    @Getter private static WorldManager instance;

    @Getter private World uhcWorld;
    @Getter private World netherWorld;

    @Getter @Setter private Worlds currentGenning;
    @Getter @Setter private WorldFillTask fillTask;

    @Getter @Setter private boolean genned;

    public WorldManager() {
        instance = this;
        String path = ViperUHC.getInstance().getServer().getWorldContainer().getAbsolutePath();
        File file = new File(path, "uhc_world");
        if (file.exists()) {
            uhcWorld = new WorldCreator("uhc_world").createWorld();
            genned = true;
        } else genned = false;
    }

    public void generate() {
        // TODO: Set ore rates to whatever the input was.
        Bukkit.broadcastMessage(ChatColor.GREEN + "The world generation process has been started!");
        createUhcWorld();
    }

    public void createUhcWorld() {
        currentGenning = Worlds.UHC_WORLD;
        uhcWorld = Bukkit.createWorld(new WorldCreator("uhc_world").environment(World.Environment.NORMAL).type(WorldType.NORMAL));

        BorderUtil.bedrockBorder(1500, uhcWorld);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb shape square");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb uhc_world set 1500 1500 0 0");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb uhc_world fill 1000");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill confirm");

        UHCGame.getInstance().setCurrentState(UHCGame.gameState.GENERATING);
    }

    public void createNether() {
        currentGenning = Worlds.UHC_NETHER;
        netherWorld = Bukkit.createWorld(new WorldCreator("uhc_nether").environment(World.Environment.NETHER).type(WorldType.NORMAL));

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb shape square");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb uhc_nether set 400 400 0 0");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb uhc_nether fill 1000");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wb fill confirm");
    }

    public enum Worlds { UHC_WORLD, UHC_NETHER }
}
