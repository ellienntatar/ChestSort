package dev.ellienntatar.pojos;

public class ItemGroup implements Comparable<ItemGroup> {
    private ItemModel item;
    private int amount;

    public ItemGroup(ItemModel item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public ItemModel getItemModel() {
        return item;
    }

    public void add(int amount) {
        this.amount += amount;
    }

    @Override
    public int compareTo(ItemGroup item) {
        int otherValue = item.getAmount();
        if (amount > otherValue) 
            return 1;
        if (amount == otherValue)
            return 0;
        
        return -1;
    }
}
