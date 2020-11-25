package me.acidviper.viperuhc.listener;

import me.acidviper.viperuhc.ViperUHC;
import me.acidviper.viperuhc.config.MatchOptions;
import me.acidviper.viperuhc.config.options.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.ArrayList;

public class InteractListener implements Listener {

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        if (HorseArmor.getInstance().getOption().equalsIgnoreCase("off")) {
            if (e.getItem().getType().toString().contains("BARDING")) {
                e.getPlayer().sendMessage(ChatColor.RED + "Horse armor is disabled!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        if (HorseArmor.getInstance().getOption().equalsIgnoreCase("off")) {
            if (e.getCurrentItem().getType().toString().contains("BARDING")) {
                e.getWhoClicked().sendMessage(ChatColor.RED + "Horse armor is disabled!");
                e.setCancelled(true);
            }
        }

        if (e.getInventory().getTitle().contains("Config Editor")) {
            for (MatchOptions option : MatchOptions.getOptions()) {
                if (e.getCurrentItem().getType() == option.item.getType()) {
                        int next = option.getOptionOptions().indexOf(option.getOption()) + 1;
                        if (next >= option.getOptionOptions().size()) next = 0;
                        option.setOption(option.getOptionOptions().get(next));
                        ItemStack item = option.item;
                        ItemMeta meta = item.getItemMeta();
                        ArrayList<String> lore = new ArrayList<>();
                        for (String name : option.getOptionOptions()) {
                            if (name.equalsIgnoreCase(option.getOption())) {
                                lore.add(ChatColor.GREEN + name);
                            } else {
                                lore.add(ChatColor.RED + name);
                            }
                        }
                        meta.setLore(lore);
                        option.item.setItemMeta(meta);

                        e.getCurrentItem().setItemMeta(meta);
                }
            }
            e.setCancelled(true);
        }
        if (e.getInventory().getTitle().contains("UHC Menu")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSplash(PotionSplashEvent e) {
        if (Splash.getInstance().getOption().equalsIgnoreCase("off")) {
            if (e.getEntity().getShooter() instanceof Player) {
                ((Player) e.getEntity().getShooter()).sendMessage(ChatColor.YELLOW + "Splash potions are off!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Golden Head")) {
            if (Absorption.getInstance().getOption().equalsIgnoreCase("off")) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(ViperUHC.getInstance(), () -> {
                    e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
                }, 5);
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(ViperUHC.getInstance(), () -> {
                e.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);

            if (GoldenHead.getInstance().getOption().equalsIgnoreCase("Four_Heart")) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10, 1));
            } else if (GoldenHead.getInstance().getOption().equalsIgnoreCase("Three_Heart")) {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 7, 1));
            }

            }, 5);

        } else if (e.getItem().getType() == Material.GOLDEN_APPLE) {
            if (Absorption.getInstance().getOption().equalsIgnoreCase("off")) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(ViperUHC.getInstance(), () -> {
                    e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
                }, 5);
            }
        }
    }

    @EventHandler
    public void onMount(EntityMountEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getMount().getType() == EntityType.HORSE) {
                if (Horses.getInstance().getOption().equalsIgnoreCase("off")) {
                    e.getEntity().sendMessage(ChatColor.RED + "Horses are disabled!");
                    e.setCancelled(true);
                }
            }
        }
    }
}
