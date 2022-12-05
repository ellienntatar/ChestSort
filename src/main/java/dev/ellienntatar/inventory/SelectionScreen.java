package dev.ellienntatar.inventory;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectionScreen implements InventoryHolder {
    
    public static final int VALUE_SLOT = 1;
    public static final int QUANTITY_SLOT = 2;
    public static final int TYPE_SLOT = 3;

    private Inventory inventory;

    public SelectionScreen() {
        inventory = Bukkit.createInventory(this, 9, "Chest Sorting");
        init();
    }

    private void init() {
        ItemStack value = createItem("Sort by value", Material.DIAMOND, List.of("Sorts chest inventory by value of items"));
        ItemStack quantity = createItem("Sort by quantity", Material.OAK_LOG, List.of("Sorts chest inventory by quantity of items"));
        ItemStack type = createItem("Sort by type", Material.DIAMOND_PICKAXE, List.of("Sorts chest inventory by type of item",
                                                                                            "Sorting order is: tools -> armor -> single items -> stackable items"));
        inventory.addItem(value);
        inventory.addItem(quantity);
        inventory.addItem(type);
    }

    public ItemStack createItem(String name, Material material, List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
