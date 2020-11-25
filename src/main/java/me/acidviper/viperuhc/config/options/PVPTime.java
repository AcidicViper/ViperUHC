package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class PVPTime extends MatchOptions{
    public Option option = Option.TWENTY;
    @Getter
    private static MatchOptions instance;

    public PVPTime() {
        super("When pvp enables", ItemCreatorUtil.ItemCreator(Material.DIAMOND_AXE, ChatColor.BLUE + "PvP Time"));
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
        TWENTY(20), FIFTEEN(15), TEN(10);
        private int numVal;
        Option(int numVal) {
            this.numVal = numVal;
        }
        public int getNumVal() {
            return numVal;
        }
    }
}
