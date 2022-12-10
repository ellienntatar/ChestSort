package dev.ellienntatar.inventory.Sortables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.Inventory;

import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.pojos.ItemAmount;

public class CategorySort implements Sortable {
    
    private Inventory inv;
    private Map<CreativeCategory, List<ItemAmount>> categoriesToItems = new LinkedHashMap<>();

    public CategorySort(Inventory inv) {
        this.inv = inv;
        // Order of categories:
        // BUILDING_BLOCKS
        // DECORATIONS
        // REDSTONE
        // TRANSPORTATION
        // FOOD
        // TOOLS
        // COMBAT
        // BREWING
        // MISC
        for (CreativeCategory category : CreativeCategory.values()) {
            categoriesToItems.put(category, new ArrayList<>());
        }
    }
    
    public Inventory sort() {
        Map<Material, Integer> materialAmount = InventoryUtil.getMaterialCountMap(inv);

        // get all items into respective category lists
        for (var entry : materialAmount.entrySet()) {
            CreativeCategory itemCategory = entry.getKey().getCreativeCategory();
            // should never happen
            if (!categoriesToItems.containsKey(itemCategory))
                continue;

            ItemAmount item = new ItemAmount(entry.getKey(), entry.getValue());
            categoriesToItems.get(itemCategory).add(item);
        }
        // sort each list by quantity
        List<ItemAmount> sortedCategoryList = new ArrayList<>();
        for (var list : categoriesToItems.values()) {
            // Sort the list
            Collections.sort(list, Collections.reverseOrder());
            sortedCategoryList.addAll(list);
        }

        return InventoryUtil.outputContents(inv, sortedCategoryList);
    }
}
