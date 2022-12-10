package dev.ellienntatar.pojos;

import org.bukkit.Material;

public class ItemAmount implements Comparable<ItemAmount> {
    private Material material;
    private int amount;

    public ItemAmount(Material material) {
        this.material = material;
        amount = 0;
    }

    public ItemAmount(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void add(int amount) {
        this.amount += amount;
    }

    @Override
    public int compareTo(ItemAmount item) {
        int otherValue = item.getAmount();
        if (amount > otherValue) 
            return 1;
        if (amount == otherValue)
            return 0;
        
        return -1;
    }
}
