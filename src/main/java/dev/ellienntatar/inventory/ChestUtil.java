package dev.ellienntatar.inventory;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

public class ChestUtil {

    // TODO: Overkill?
    public static enum SortType {
        QUANTITY
    }
    
    private ChestUtil() {
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
