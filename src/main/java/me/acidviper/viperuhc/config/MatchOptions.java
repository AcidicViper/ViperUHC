package me.acidviper.viperuhc.config;

import lombok.Getter;
import me.acidviper.viperuhc.config.options.CrossTeaming;
import me.acidviper.viperuhc.config.options.PortalTrapping;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class MatchOptions {
    @Getter public static ArrayList<MatchOptions> options = new ArrayList<>();

    public String desc;
    public ItemStack item;

    public MatchOptions(String description, ItemStack symbol) {
        desc = description;
        item = symbol;

        options.add(this);
    }

    public abstract String getOption();
    public abstract void setOption(String name);
    public abstract ArrayList<String> getOptionOptions();
}
