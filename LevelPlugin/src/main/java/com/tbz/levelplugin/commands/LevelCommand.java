package com.tbz.levelplugin.commands;

import com.tbz.levelplugin.level.LevelHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof  Player && command.getName().equals("level")) {
            Player player = (Player)sender;
            if (args.length == 0) {
                String messageFormat = ChatColor.YELLOW.toString() + ChatColor.BOLD;
                String message = "Your current level: " + LevelHandler.getLevel(player) +
                        "\nYour XP: " + LevelHandler.getXP(player);
                player.sendMessage(messageFormat + message);

                return true;
            }
            else if (args.length == 1) {
                if (player.isOp()) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        String messageFormat = ChatColor.YELLOW.toString() + ChatColor.BOLD;
                        String message = args[0] + "'s current level: " + LevelHandler.getLevel(args[0]) +
                                "\n" + args[0] + "'s XP: " + LevelHandler.getXP(args[0]);
                        player.sendMessage(messageFormat + message);

                        return true;
                    }
                    else {
                        player.sendMessage("The player " + args[0] + " doesn't exist.");
                    }
                }
                else {
                    player.sendMessage("You're not allowed to use this command.");
                }
            }
        }
        return false;
    }

}
