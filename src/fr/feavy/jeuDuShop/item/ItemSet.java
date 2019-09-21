package fr.feavy.jeuDuShop.item;

import java.util.*;

public class ItemSet implements Iterable<Item>{
    private final Map<ItemType, Item> itemMap = new EnumMap<>(ItemType.class);

    public ItemSet() {
    }

    public ItemSet(Item[] items) {
        for(Item item : items)
            addItem(item);
    }

    public void addItems(ItemSet itemSet) {
        for(Item item : itemSet)
            addItem(item);
    }

    public void addItem(Item item) {
        if(!itemMap.containsKey(item.getType())) {
            itemMap.put(item.getType(), item);
            return;
        }

        itemMap.get(item.getType()).add(item);
    }

    public boolean removeItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            return false;

        boolean rep = itemMap.get(item.getType()).remove(item);
        if(rep && itemMap.get(item.getType()).isEmpty())
            itemMap.remove(item.getType());

        return rep;
    }

    public void removeItems(ItemSet items) {
        for(Item item : items)
            removeItem(item);
    }

    public boolean hasItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            return false;

        return itemMap.get(item.getType()).isMoreOrEqualsThan(item);
    }

    public boolean hasItems(ItemSet items) {
        for(Item item : items)
            if(!hasItem(item))
                return false;
        return true;
    }

    public Optional<Item> getItem(ItemType itemType) {
        return Optional.ofNullable(itemMap.get(itemType));
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        for(Item i : this)
            rep.append(i.toString()+" ");
        return rep.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return itemMap.values().iterator();
    }

    public Collection<Item> getItems() {
        return itemMap.values();
    }
}
