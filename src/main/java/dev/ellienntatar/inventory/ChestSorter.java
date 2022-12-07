package dev.ellienntatar.inventory;

import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;

public class ChestSorter {
    
    // TODO: Overkill?
    public static enum SortType {
        QUANTITY
    }

    private Inventory inv;

    public ChestSorter(Inventory inv) {
        this.inv = inv;
    }


    public Inventory sort(String sortType) {
        switch (sortType) {
            case "quantity":
                quantitySort();
            
            default:
                break;
        }
        return inv;
    }

    // TODO: Review efficiency of this... Evan?
    private void quantitySort() {
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

        List<Entry<Material, Integer>> sortedList = new LinkedList<>(materialAmount.entrySet());
        // Sort the list
        Collections.sort(sortedList, new Comparator<Map.Entry<Material, Integer> >() {
            public int compare(Map.Entry<Material, Integer> o1,
                               Map.Entry<Material, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        inv = ChestUtil.outputContents(inv, sortedList);
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
