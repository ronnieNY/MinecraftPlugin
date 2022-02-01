package com.tbz.levelplugin.level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import java.io.*;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class LevelHandler {

    private static HashMap<String, Integer> playerLevels;
    public static String pluginDir;

    public static void initPlayerLevels() {
        playerLevels = new HashMap<>();

        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream("/storage/players.ser")));
            playerLevels = (HashMap<String, Integer>) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean savePlayerLevels() {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream("/storage/players.ser")));
            out.writeObject(playerLevels);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void registerPlayer(String username) {
        if (!playerIsRegistered(username)) {
            playerLevels.put(username, 0);
        }
    }

    public static void registerPlayer(Player player) {
        registerPlayer(player.getName());
    }

    private static boolean playerIsRegistered(String username) {
        if (playerLevels.containsKey(username)) {
            return true;
        }
        return false;
    }

    public static int getLevel(String username) {
        if (!playerIsRegistered(username)) {
            return 0;
        }
        else {
            int xp = playerLevels.get(username);

            return (int)(((Math.sqrt(5 * (4 * xp + 5))) + 5) / 10);
        }
    }

    public static int getLevel(Player player) {
        return getLevel(player.getName());
    }

    public static int getXP(String username) {
        if (!playerIsRegistered(username)) {
            return 0;
        }
        else {
            return playerLevels.get(username);
        }
    }

    public static int getXP(Player player) {
        return getXP(player.getName());
    }

    public static void addXP(String username, int xp) {
        if (playerIsRegistered(username)) {
            int levelBefore = getLevel(username);
            playerLevels.put(username, playerLevels.get(username) + xp);
            int levelAfter = getLevel(username);

            int gifts = 0;
            for (int i = levelBefore + 1; i < levelAfter + 1; i++) {
                if (i % 5 == 0) {
                    gifts++;
                }
            }
            for (int i = 0; i < gifts; i++) {
                for (int j = 0; j < levelAfter / 5 - i + 1; j++) {
                    Bukkit.getPlayer(username).getInventory().addItem(new ItemStack(Material.DIAMOND));
                }
            }
            savePlayerLevels();
        }
    }

    public static void addXP(Player player, int xp) {
        addXP(player.getName(), xp);
    }

}
