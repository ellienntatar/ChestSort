package dev.ellienntatar.inventory;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import dev.ellienntatar.inventory.Sortables.InvalidSort;
import dev.ellienntatar.inventory.Sortables.QuantitySort;
import dev.ellienntatar.inventory.Sortables.Sortable;

public class InventoryUtil {

    public static enum SortType {
        QUANTITY
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
            default:
                return new InvalidSort(inventory); 
        }
    }
}
