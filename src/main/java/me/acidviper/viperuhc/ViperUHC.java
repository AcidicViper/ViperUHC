package me.acidviper.viperuhc;

import io.github.thatkawaiisam.assemble.Assemble;
import lombok.Getter;
import me.acidviper.viperuhc.command.ConfigEditorCommand;
import me.acidviper.viperuhc.command.GenerateCommand;
import me.acidviper.viperuhc.command.ScatterCommand;
import me.acidviper.viperuhc.command.UHCCommand;
import me.acidviper.viperuhc.config.options.*;
import me.acidviper.viperuhc.game.UHCGame;
import me.acidviper.viperuhc.listener.*;
import me.acidviper.viperuhc.scoreboard.ScoreboardManager;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import me.acidviper.viperuhc.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class ViperUHC extends JavaPlugin {
    @Getter private static ViperUHC instance;

    @Getter private ShapedRecipe goldenHead;
    public void onEnable() {
        instance = this;
        new Assemble(this, new ScoreboardManager());

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aViperUHC&8] &fEnabling plugin..."));

        registerCommands();
        registerListeners();
        registerOptions();

        new WorldManager();
        new UHCGame();

        createRecipes();
    }

    public void createRecipes() {
        ShapedRecipe goldenHead = new ShapedRecipe(ItemCreatorUtil.ItemCreator(Material.GOLDEN_APPLE, ChatColor.GOLD + "Golden Head"));
        goldenHead.shape("!!!","!@!","!!!");
        goldenHead.setIngredient('!', Material.GOLD_INGOT);
        goldenHead.setIngredient('@', Material.SKULL_ITEM, 3);
        this.goldenHead = goldenHead;
        Bukkit.getServer().addRecipe(goldenHead);

        ShapelessRecipe bookCraft = new ShapelessRecipe(new ItemStack(Material.BOOK));
        bookCraft.addIngredient(2, Material.ENCHANTED_BOOK);
        Bukkit.getServer().addRecipe(bookCraft);

        ShapelessRecipe stringCraft = new ShapelessRecipe(new ItemStack(Material.STRING));
        stringCraft.addIngredient(4, Material.WOOL);
        Bukkit.getServer().addRecipe(stringCraft);
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&aViperUHC&8] &fDisabling plugin..."));
    }

    public void registerCommands() {
        Bukkit.getServer().getPluginCommand("scatter").setExecutor(new ScatterCommand());
        Bukkit.getServer().getPluginCommand("generate").setExecutor(new GenerateCommand());
        Bukkit.getServer().getPluginCommand("uhc").setExecutor(new UHCCommand());
        Bukkit.getServer().getPluginCommand("editconfig").setExecutor(new ConfigEditorCommand());
    }

    public void registerOptions() {
       new BlastMining();
       new BorderShrink();
       new BorderTrapping();
       new Camping();
       new CCounter();
       new CrossTeaming();
       new DeathLightning();
       new DiamondRate();
       new FinalHeal();
       new Nether();
       new PearlDamage();
       new PortalTrapping();
       new PVPTime();
       new Absorption();
       new GoldenHead();
       new Speed();
       new Strength();
       new Splash();
       new StripMining();
       new Pokeholing();
       new StairCasing();
       new DigToSound();
       new ToggleSneak();
       new TeamKilling();
       new Horses();
       new HorseArmor();
       new SaddleDrops();
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new OnJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
    }
}
