package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class DeathLightning extends  MatchOptions {
    //DONE

    public Option option = Option.OFF;
    @Getter
    private static MatchOptions instance;

    public DeathLightning() {
        super("If Death Lightning is on or off.", ItemCreatorUtil.ItemCreator(Material.DAYLIGHT_DETECTOR, ChatColor.BLUE + "Death Lightning"));
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
