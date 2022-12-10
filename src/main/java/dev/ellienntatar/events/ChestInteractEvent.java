package dev.ellienntatar.events;

import org.bukkit.event.*;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.ellienntatar.inventory.ChestSorter;

public class ChestInteractEvent implements Listener {

    Map<String, Boolean> hasToggledSort = new HashMap<>();
    Map<String, String> playerSortType = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        if (hasToggledSort.containsKey(playerName)) {
            String sortType = playerSortType.get(playerName);
            if (event.getClickedBlock().getState() instanceof Chest) {
                Chest clickedChest = (Chest) event.getClickedBlock().getState();
                ChestSorter sorter = new ChestSorter(clickedChest.getInventory());
                clickedChest.getInventory().setContents((sorter.sort(sortType).getContents()));

                event.getPlayer().sendMessage(ChatColor.AQUA + "Chest contents have been sorted!");
            } else {
                // player clicked non chest block
                event.getPlayer().sendMessage(ChatColor.RED + "Non-chest block clicked, sort mode disabled.");
            }

            hasToggledSort.remove(playerName);
        }
    }

    public void addPlayer(String name, String type) {
        hasToggledSort.put(name, true);
        playerSortType.put(name, type);
    }
}
