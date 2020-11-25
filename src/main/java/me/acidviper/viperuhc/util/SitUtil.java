package me.acidviper.viperuhc.util;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SitUtil {
    public static void freezePlayer(Player p) {
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 255));
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 250));
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 255));
    }

    public static void unFreezePlayer(Player p) {
        p.getPlayer().removePotionEffect(PotionEffectType.SLOW);
        p.getPlayer().removePotionEffect(PotionEffectType.JUMP);
        p.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
    }
}
