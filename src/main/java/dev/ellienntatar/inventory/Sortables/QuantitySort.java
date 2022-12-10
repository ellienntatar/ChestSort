package dev.ellienntatar.inventory.Sortables;

import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.ellienntatar.inventory.InventoryUtil;

public class QuantitySort implements Sortable {
    private Inventory inv;

    public QuantitySort(Inventory inv) {
        this.inv = inv;
    }

    public Inventory sort() {
        Map<Material, Integer> materialAmount = InventoryUtil.getMaterialCountMap(inv);

        List<Entry<Material, Integer>> sortedList = new LinkedList<>(materialAmount.entrySet());
        // Sort the list
        Collections.sort(sortedList, new Comparator<Map.Entry<Material, Integer> >() {
            public int compare(Map.Entry<Material, Integer> o1,
                               Map.Entry<Material, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        inv = InventoryUtil.outputContents(inv, sortedList);

        return inv;
    }
}
