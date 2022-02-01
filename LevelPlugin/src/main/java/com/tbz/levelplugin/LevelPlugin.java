package com.tbz.levelplugin;

import com.tbz.levelplugin.commands.ChickenCommand;
import com.tbz.levelplugin.commands.LevelCommand;
import com.tbz.levelplugin.commands.AddXPCommand;
import com.tbz.levelplugin.level.LevelHandler;
import com.tbz.levelplugin.listeners.LevelListener;
import com.tbz.levelplugin.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LevelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new LevelListener(), this);

        getCommand("level").setExecutor(new LevelCommand());
        getCommand("chickens").setExecutor(new ChickenCommand());
        getCommand("addxp").setExecutor(new AddXPCommand());

        LevelHandler.initPlayerLevels();
        LevelHandler.pluginDir = getDataFolder().getAbsolutePath();
    }

    @Override
    public void onDisable() {
        LevelHandler.savePlayerLevels();
    }
}
