package com.tbz.levelplugin.listeners;

import com.tbz.levelplugin.level.LevelHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class LevelListener implements Listener {

    @EventHandler
    public void killedMob(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            Player player = e.getEntity().getKiller();

            int levelBefore = LevelHandler.getLevel(player);
            LevelHandler.addXP(player, 2);
            int levelAfter = LevelHandler.getLevel(player);

            if (levelBefore < levelAfter) {
                String messageFormat = ChatColor.YELLOW.toString() + ChatColor.BOLD;
                String message = "You reached level " + levelAfter + "!";
                player.sendMessage(messageFormat + message);

                if (levelAfter % 5 == 0) {
                    for (int i = 0; i < levelAfter / 5 + 1; i++) {
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                    }
                    player.sendMessage(messageFormat + "Congrats! Here are some diamonds.");
                }
            }
        }
    }

}
