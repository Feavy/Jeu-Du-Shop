package fr.feavy.jeuDuShop.player;

import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<ItemType, Item> itemMap = new EnumMap<ItemType, Item>(ItemType.class);

    public void addItems(List<Item> items) {
        for(Item item : items)
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
}
