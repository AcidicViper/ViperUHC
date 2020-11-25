package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class GoldenHead extends MatchOptions {
    //DONE
    public Option option = Option.FOUR_HEART;
    @Getter
    private static MatchOptions instance;

    public GoldenHead() {
        super("Golden Heads Settings", ItemCreatorUtil.ItemCreator(Material.SKULL, ChatColor.BLUE + "Golden Heads"));
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
        OFF, THREE_HEART, FOUR_HEART
    }
}
