package dev.ellienntatar.inventory.Sortables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.Inventory;

import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.pojos.ItemGroup;
import dev.ellienntatar.pojos.ItemModel;

public class CategorySort implements Sortable {
    
    private Inventory inv;
    private Map<CreativeCategory, List<ItemGroup>> categoriesToItems = new LinkedHashMap<>();

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
        List<ItemGroup> itemGroupList = InventoryUtil.getItemGroupList(inv);

        // get all items into respective category lists
        for (ItemGroup itemGroup : itemGroupList) {
            ItemModel itemModel = itemGroup.getItemModel();
            CreativeCategory itemCategory = itemModel.getMaterial().getCreativeCategory();
            // should never happen
            if (!categoriesToItems.containsKey(itemCategory))
                continue;

            categoriesToItems.get(itemCategory).add(itemGroup);
        }
        // sort each list by quantity
        List<ItemGroup> sortedCategoryList = new ArrayList<>();
        for (var list : categoriesToItems.values()) {
            // Sort the list
            Collections.sort(list, Collections.reverseOrder());
            sortedCategoryList.addAll(list);
        }

        return InventoryUtil.outputContents(inv, sortedCategoryList);
    }
}
