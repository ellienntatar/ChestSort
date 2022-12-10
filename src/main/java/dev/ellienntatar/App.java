package dev.ellienntatar;
import org.bukkit.plugin.java.JavaPlugin;

import dev.ellienntatar.commands.SortCommandCompleter;
import dev.ellienntatar.commands.SortCommand;
import dev.ellienntatar.events.ChestInteractEvent;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        // implemented commands
        // saveDefaultConfig();
        ChestInteractEvent chestInteractEvent = new ChestInteractEvent();
        getCommand("sort").setExecutor(new SortCommand(chestInteractEvent));
        getCommand("sort").setTabCompleter(new SortCommandCompleter());
        getServer().getPluginManager().registerEvents(chestInteractEvent, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}