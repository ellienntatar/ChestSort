package dev.ellienntatar.pojos;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemModel {
    private Material material;
    private ItemMeta itemMeta;

    public ItemModel(Material material, ItemMeta itemMeta) {
        this.material = material;
        this.itemMeta = itemMeta;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }
    
    @Override
    public int hashCode() {
        return material.hashCode() + itemMeta.hashCode();
    }

    @Override 
    public boolean equals(Object model) {
        if (model.getClass() != this.getClass())
            return false; 

        ItemModel itemModel = (ItemModel) model;
        return this.material.equals(itemModel.material) && this.itemMeta.equals(itemModel.itemMeta);
    }


}
