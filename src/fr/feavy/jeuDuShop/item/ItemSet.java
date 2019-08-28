package fr.feavy.jeuDuShop.item;

import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;

import java.util.*;

public class ItemSet {
    private final Map<ItemType, Item> itemMap = new EnumMap<>(ItemType.class);

    public void addItems(ItemSet itemSet) {
        for(Item item : itemSet.getItems())
            addItem(item);
    }

    public void addItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            itemMap.put(item.getType(), new Item(item.getType(), 0));

        itemMap.get(item.getType()).add(item);
    }

    public boolean removeItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            return false;

        return itemMap.get(item.getType()).remove(item);
    }

    public boolean hasItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            return false;

        return itemMap.get(item.getType()).isMoreOrEqualsThan(item);
    }

    public Collection<Item> getItems() {
        return itemMap.values();
    }
}
