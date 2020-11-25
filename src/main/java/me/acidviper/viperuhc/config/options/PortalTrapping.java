package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class PortalTrapping extends MatchOptions {
    //DONE
    public Option option = Option.OFF;
    @Getter
    private static MatchOptions instance;

    public PortalTrapping() {
        super("If Portal trapping is on or off.", ItemCreatorUtil.ItemCreator(Material.OBSIDIAN, ChatColor.BLUE + "Portal trapping"));
        instance = this;
    }

    public String getOption() {
        return option.name();
    }

    public void setOption(String name) {
        option = Option.valueOf(name);
    }

    public ArrayList<String> getOptionOptions() {
        ArrayList<String> array = new ArrayList<>();
        for (Option op : Option.values()) {
            array.add(op.name());
        }
        return array;
    }

    public enum Option {
        ON, OFF
    }
}
