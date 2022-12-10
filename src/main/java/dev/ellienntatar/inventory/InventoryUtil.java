package dev.ellienntatar.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import dev.ellienntatar.inventory.Sortables.CategorySort;
import dev.ellienntatar.inventory.Sortables.InvalidSort;
import dev.ellienntatar.inventory.Sortables.QuantitySort;
import dev.ellienntatar.inventory.Sortables.Sortable;
import dev.ellienntatar.pojos.ItemGroup;
import dev.ellienntatar.pojos.ItemModel;

public class InventoryUtil {

    public static enum SortType {
        QUANTITY,
        QUALITY,
        CATEGORY
    }
    
    private InventoryUtil() {
        // prevents instantiation
    }

    public static Inventory outputContents(Inventory holderInventory, List<ItemGroup> list) {
        Inventory sortedInventory = Bukkit.createInventory(holderInventory.getHolder(), holderInventory.getSize());
        
        for (ItemGroup item : list) {
            int numItems = item.getAmount();
            while (numItems > 0) {
                ItemModel itemModel = item.getItemModel();
                int maxStackSize = new ItemStack(itemModel.getMaterial()).getMaxStackSize();

                int currNum = numItems > maxStackSize ? maxStackSize : numItems;
                ItemStack itemStack = new ItemStack(itemModel.getMaterial(), currNum);
                itemStack.setItemMeta(itemModel.getItemMeta());
                sortedInventory.addItem(itemStack);
                numItems -= currNum;
            }
        }
        return sortedInventory;
    }

    public static List<ItemGroup> getItemGroupList(Inventory inv) {
        ItemStack[] contents = inv.getContents();
    
        Map<ItemModel, Integer> itemModels = new HashMap<>();
        for (ItemStack item : contents) {
            if (item == null)
                continue;
            int itemAmount = item.getAmount();
            ItemModel itemModel = new ItemModel(item.getType(), item.getItemMeta());
            if (itemModels.containsKey(itemModel)) {
                itemModels.put(itemModel, itemModels.get(itemModel) + itemAmount);
            } else {
                itemModels.put(itemModel, itemAmount);
            }
        }

        List<ItemGroup> groupList = new ArrayList<>();
        for (var entry : itemModels.entrySet()) {
            ItemGroup itemGroup = new ItemGroup(entry.getKey(), entry.getValue());
            groupList.add(itemGroup);
        }

        return groupList;
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
