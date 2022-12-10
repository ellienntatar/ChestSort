package dev.ellienntatar.inventory.Sortables;

import java.util.*;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.pojos.ItemAmount;

public class QuantitySort implements Sortable {
    private Inventory inv;

    public QuantitySort(Inventory inv) {
        this.inv = inv;
    }

    public Inventory sort() {
        Map<Material, Integer> materialAmount = InventoryUtil.getMaterialCountMap(inv);

        List<ItemAmount> itemsList = new ArrayList<>();
        for (Entry<Material, Integer> entry : materialAmount.entrySet()) {
            ItemAmount currItem = new ItemAmount(entry.getKey(), entry.getValue());
            itemsList.add(currItem);
        }
        // Sort the list
        Collections.sort(itemsList, new Comparator<ItemAmount>() {
            public int compare(ItemAmount o1, ItemAmount o2) { return o2.compareTo(o1); }
        });

        inv = InventoryUtil.outputContents(inv, itemsList);

        return inv;
    }
}
