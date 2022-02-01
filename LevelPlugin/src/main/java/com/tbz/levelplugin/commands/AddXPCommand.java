package com.tbz.levelplugin.commands;

import com.tbz.levelplugin.level.LevelHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddXPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && command.getName().equals("addxp") && args.length == 2) {
            Player player = (Player) sender;
            try {
                Player receiver = Bukkit.getPlayer(args[0]);
                LevelHandler.addXP(receiver, Integer.parseInt(args[1]));

                String messageFormat = ChatColor.YELLOW.toString() + ChatColor.BOLD;
                player.sendMessage(messageFormat + "Gave " + args[0] + " " + args[1] + " XP.");
                receiver.sendMessage(messageFormat + "Someone gave you " + args[1] + " XP.");

                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
