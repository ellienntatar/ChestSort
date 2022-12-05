package dev.ellienntatar.events;

import org.bukkit.event.*;

import java.util.*;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.ellienntatar.inventory.ChestSorter;

public class ChestInteractEvent implements Listener {

    Map<String, Boolean> hasToggledSort = new HashMap<>();
    Map<String, String> playerSortType = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        String playerName = e.getPlayer().getName();
        if (hasToggledSort.containsKey(playerName)) {

            if (e.getClickedBlock().getState() instanceof Chest) {
                String sortType = playerSortType.get(playerName);
                Chest clickedChest = (Chest) e.getClickedBlock().getState();
                ChestSorter sorter = new ChestSorter(clickedChest.getInventory());


                clickedChest.getBlockInventory().setContents((sorter.sort(sortType).getContents()));

                e.getPlayer().sendMessage("Chest contents have been sorted!");
            } else if (e.getClickedBlock().getState() instanceof DoubleChest) {
                // TODO: Figure out double chest sorting (hard!)
            } else {
                // player clicked non chest block
                e.getPlayer().sendMessage("Non-chest block clicked, sort mode disabled.");
            }

            hasToggledSort.remove(playerName);
        }
    }

    public void addPlayer(String name, String type) {
        hasToggledSort.put(name, true);
        playerSortType.put(name, type);
    }
}
