package dev.ellienntatar.events;

import org.bukkit.event.*;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

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
            BlockState clickedBlockState = event.getClickedBlock().getState();
            SortType sortType = playerSortType.get(playerName);
            // chest click
            if (clickedBlockState instanceof Chest) {
                Chest clickedChest = (Chest) clickedBlockState;
                Inventory chestInventory = clickedChest.getInventory();
                Sortable sorter = InventoryUtil.getSorter(sortType, chestInventory);
                chestInventory.setContents((sorter.sort().getContents()));

                event.getPlayer().sendMessage(ChatColor.AQUA + "Chest contents have been sorted!");

            // shulker box click
            } else if (clickedBlockState instanceof ShulkerBox) { 
                ShulkerBox clickedShulkerBox = (ShulkerBox) clickedBlockState;
                Inventory shulkerBoxInventory = clickedShulkerBox.getInventory();
                Sortable sorter = InventoryUtil.getSorter(sortType, shulkerBoxInventory);
                shulkerBoxInventory.setContents((sorter.sort().getContents()));

                event.getPlayer().sendMessage(ChatColor.AQUA + "ShulkerBox contents have been sorted!");

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
