package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class BorderShrink extends MatchOptions {
    public Option option = Option.THIRTYFIVE;
    @Getter
    private static MatchOptions instance;

    public BorderShrink() {
        super("When border first shrinks", ItemCreatorUtil.ItemCreator(Material.COBBLE_WALL, ChatColor.BLUE + "First Shrink"));
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
        TWENTYFIVE (25), THIRTY (30), THIRTYFIVE (35), FOURTY (40), FOURTYFIVE(45);
        private int numVal;
        Option(int numVal) {
            this.numVal = numVal;
        }
        public int getNumVal() {
            return numVal;
        }
    }
}
