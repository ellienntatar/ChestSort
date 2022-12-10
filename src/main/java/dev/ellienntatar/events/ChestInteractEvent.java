package dev.ellienntatar.events;

import org.bukkit.event.*;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.inventory.InventoryUtil.SortType;
import dev.ellienntatar.inventory.Sortables.Sortable;

public class ChestInteractEvent implements Listener {

    Map<String, Boolean> hasToggledSort = new HashMap<>();
    Map<String, SortType> playerSortType = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        if (hasToggledSort.containsKey(playerName)) {
            if (event.getClickedBlock().getState() instanceof Chest) {
                SortType sortType = playerSortType.get(playerName);
                Chest clickedChest = (Chest) event.getClickedBlock().getState();
                Sortable sorter = InventoryUtil.getSorter(sortType, clickedChest.getInventory());
                clickedChest.getInventory().setContents((sorter.sort().getContents()));

                event.getPlayer().sendMessage(ChatColor.AQUA + "Chest contents have been sorted!");

            } else {
                // player clicked non chest block
                event.getPlayer().sendMessage(ChatColor.RED + "Non-chest block clicked, sort mode disabled.");
            }

            hasToggledSort.remove(playerName);
        }
    }

    public void addPlayer(String name, SortType type) {
        hasToggledSort.put(name, true);
        playerSortType.put(name, type);
    }
}
