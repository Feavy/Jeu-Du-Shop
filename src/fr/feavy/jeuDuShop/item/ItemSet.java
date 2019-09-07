package fr.feavy.jeuDuShop.item;

import java.util.*;

public class ItemSet<T extends Item> implements Iterable<T>{
    private final Map<ItemType, T> itemMap = new EnumMap<>(ItemType.class);

    public ItemSet() {
    }

    public ItemSet(T[] items) {
        for(T item : items)
            addItem(item);
    }

    public void addItems(ItemSet<T> itemSet) {
        for(T item : itemSet)
            addItem(item);
    }

    public void addItem(T item) {
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

    public void removeItems(ItemSet<T> items) {
        for(Item item : items)
            removeItem(item);
    }

    public boolean hasItem(Item item) {
        if(!itemMap.containsKey(item.getType()))
            return false;

        return itemMap.get(item.getType()).isMoreOrEqualsThan(item);
    }

    public boolean hasItems(ItemSet<T> items) {
        for(Item item : items)
            if(!hasItem(item))
                return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        for(Item i : this)
            rep.append(i.toString()+" ");
        return rep.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return itemMap.values().iterator();
    }

    public Collection<T> getItems() {
        return itemMap.values();
    }
}
