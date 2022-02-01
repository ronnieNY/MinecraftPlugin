package com.tbz.levelplugin.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ChickenCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && command.getName().equals("chickens")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                World world = player.getWorld();

                for (int i = 0; i < 20; i++) {
                    world.spawnEntity(player.getLocation(), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(0, 0, 1), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(1, 0, 0), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(0, 0, -1), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(-1, 0, 0), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(1, 0, 1), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(-1, 0, -1), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(-1, 0, 1), EntityType.CHICKEN);
                    world.spawnEntity(player.getLocation().add(1, 0, -1), EntityType.CHICKEN);
                }

                return true;
            }
            else {
                player.sendMessage("You're not allowed to use this command.");
            }
        }
        return false;
    }

}
