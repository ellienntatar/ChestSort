package dev.ellienntatar.inventory;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

public class ChestUtil {
    
    private ChestUtil() {
        // prevents instantiation
    }

    public static Inventory outputContents(Inventory holderInventory, List<Entry<Material, Integer>> list) {
        Inventory sortedInventory = Bukkit.createInventory(holderInventory.getHolder(), InventoryType.CHEST);
        
        for (Entry<Material, Integer> entry : list) {
            int numItems = entry.getValue();
            while (numItems > 0) {
                ItemStack singleStack = new ItemStack(entry.getKey(), 1);
                if (singleStack.getMaxStackSize() == 1) {
                    sortedInventory.addItem(singleStack);    
                    numItems -= 1;
                } else {
                    int currNum = numItems > 64 ? 64 : numItems;
                    sortedInventory.addItem(new ItemStack(entry.getKey(), currNum));
                    numItems -= currNum;
                }
            }
        }
        return sortedInventory;
    }
}
