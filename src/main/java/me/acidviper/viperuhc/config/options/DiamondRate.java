package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class DiamondRate extends MatchOptions {
    //DONE
    public Option option = Option.VANILLA;
    @Getter
    private static MatchOptions instance;

    public DiamondRate() {
        super("Diamond rate", ItemCreatorUtil.ItemCreator(Material.DIAMOND_ORE, ChatColor.BLUE + "Diamond Rate"));
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
        VANILLA;
    }
}
