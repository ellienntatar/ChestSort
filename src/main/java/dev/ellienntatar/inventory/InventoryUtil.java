package dev.ellienntatar.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import dev.ellienntatar.inventory.Sortables.CategorySort;
import dev.ellienntatar.inventory.Sortables.InvalidSort;
import dev.ellienntatar.inventory.Sortables.QuantitySort;
import dev.ellienntatar.inventory.Sortables.Sortable;
import dev.ellienntatar.pojos.ItemAmount;

public class InventoryUtil {

    public static enum SortType {
        QUANTITY,
        QUALITY,
        CATEGORY
    }
    
    private InventoryUtil() {
        // prevents instantiation
    }

    public static Inventory outputContents(Inventory holderInventory, List<ItemAmount> list) {
        Inventory sortedInventory = Bukkit.createInventory(holderInventory.getHolder(), holderInventory.getSize());
        
        for (ItemAmount item : list) {
            int numItems = item.getAmount();
            while (numItems > 0) {
                int maxStackSize = new ItemStack(item.getMaterial()).getMaxStackSize();

                int currNum = numItems > maxStackSize ? maxStackSize : numItems;
                sortedInventory.addItem(new ItemStack(item.getMaterial(), currNum));
                numItems -= currNum;
            }
        }
        return sortedInventory;
    }

    public static Map<Material, Integer> getMaterialCountMap(Inventory inv) {
        ItemStack[] contents = inv.getContents();

        Map<Material, Integer> materialAmount = new HashMap<>();
        for (ItemStack item : contents) {
            if (item == null)
                continue;

            Material currItemType = item.getType();
            int itemAmount = item.getAmount();
            if (materialAmount.containsKey(currItemType)) {
                materialAmount.put(currItemType, materialAmount.get(currItemType) + itemAmount);
            } else {
                materialAmount.put(currItemType, itemAmount);
            }
        }

        return materialAmount;
    }

    public static Sortable getSorter(SortType type, Inventory inventory) {
        switch (type) {
            case QUANTITY:
                return new QuantitySort(inventory);
            case CATEGORY:
                return new CategorySort(inventory);
            default:
                return new InvalidSort(inventory); 
        }
    }
}
