package dev.ellienntatar.inventory.Sortables;

import java.util.*;

import org.bukkit.inventory.Inventory;

import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.pojos.ItemGroup;

public class QuantitySort implements Sortable {
    private Inventory inv;

    public QuantitySort(Inventory inv) {
        this.inv = inv;
    }

    public Inventory sort() {
        List<ItemGroup> itemGroupList = InventoryUtil.getItemGroupList(inv);

        // Sort the list
        Collections.sort(itemGroupList, Collections.reverseOrder());

        inv = InventoryUtil.outputContents(inv, itemGroupList);

        return inv;
    }
}
