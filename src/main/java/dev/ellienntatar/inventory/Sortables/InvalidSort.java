package dev.ellienntatar.inventory.Sortables;

import org.bukkit.inventory.Inventory;

public class InvalidSort implements Sortable {

    private Inventory inv;

    public InvalidSort(Inventory inv) {
        this.inv = inv;
    }

    public Inventory sort() {
        return inv;
    }
}
