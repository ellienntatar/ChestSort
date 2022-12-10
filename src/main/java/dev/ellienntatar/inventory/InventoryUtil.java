package dev.ellienntatar.inventory;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil {

    public static enum SortType {
        QUANTITY,
        INVALID
    }
    
    private InventoryUtil() {
        // prevents instantiation
    }

    public static Inventory outputContents(Inventory holderInventory, List<Entry<Material, Integer>> list) {
        Inventory sortedInventory = Bukkit.createInventory(holderInventory.getHolder(), holderInventory.getSize());
        
        for (Entry<Material, Integer> entry : list) {
            int numItems = entry.getValue();
            while (numItems > 0) {
                int maxStackSize = new ItemStack(entry.getKey()).getMaxStackSize();

                int currNum = numItems > maxStackSize ? maxStackSize : numItems;
                sortedInventory.addItem(new ItemStack(entry.getKey(), currNum));
                numItems -= currNum;
            }
        }
        return sortedInventory;
    }
    
    public static Sortable getSorter(SortType type, Inventory inventory) {
        switch (type) {
            case QUANTITY:
                return new QuantitySort(inventory);
            default:
                return null; 
        }
    }

    // checks if specified type is part of enum
    public static boolean isImplementedSortType(String type) {
        for (SortType enumType : SortType.values()) {
            if (enumType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
    
        return false;
    }
}
