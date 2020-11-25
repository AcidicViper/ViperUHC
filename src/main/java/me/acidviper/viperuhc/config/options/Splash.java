package me.acidviper.viperuhc.config.options;

import lombok.Getter;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.util.ItemCreatorUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

public class Splash extends MatchOptions {
    public Option option = Option.OFF;
    @Getter
    private static MatchOptions instance;

    public Splash() {
        super("Whether or not splash potions are on", ItemCreatorUtil.ItemCreator(Material.SULPHUR, ChatColor.BLUE + "Splash Potions"));
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
        OFF, ON;
    }
}
