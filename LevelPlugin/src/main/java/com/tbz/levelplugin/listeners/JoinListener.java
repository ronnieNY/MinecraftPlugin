package com.tbz.levelplugin.listeners;

import com.tbz.levelplugin.level.LevelHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LevelHandler.registerPlayer(player);

        String joinMessageFormat = ChatColor.BLUE.toString() + ChatColor.BOLD;
        String joinMessage = "Hallo " + player.getName() + "!";
        event.setJoinMessage(joinMessageFormat + joinMessage);
    }

}
