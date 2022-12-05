package dev.ellienntatar.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;

import dev.ellienntatar.inventory.SelectionScreen;

public class PlayerExchange implements Listener {
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }

        // selection screen click event
        if (e.getClickedInventory().getHolder() instanceof SelectionScreen) {
            if (e.isLeftClick()) {
                    e.setCancelled(true);
                    Player player = (Player) e.getWhoClicked();
                    if (e.getCurrentItem() == null) { 
                    return;
                }

                if (e.getSlot() == SelectionScreen.VALUE_SLOT) {
                    player.sendMessage("Value sort selected. Right click on the chest you would like to sort.");
                }
                if (e.getSlot() == SelectionScreen.QUANTITY_SLOT) {
                    player.sendMessage("Quantity sort selected. Right click on the chest you would like to sort.");
                }
                if (e.getSlot() == SelectionScreen.TYPE_SLOT) {
                    player.sendMessage("Type sort selected. Right click on the chest you would like to sort.");
                }
            }
        }
    }
}
