package fr.feavy.jeuDuShop.player;

import fr.feavy.jeuDuShop.craft.Craft;
import fr.feavy.jeuDuShop.craft.CraftManager;
import fr.feavy.jeuDuShop.event.*;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.item.SellingItem;
import fr.feavy.jeuDuShop.listener.EventListener;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Player implements EventListener {
    private final ItemSet<Item> inventory = new ItemSet();
    private final ItemSet<SellingItem> sellingItemSet = new ItemSet();

    public Player() {
        EventManager.addEventListener(this);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void addItems(ItemSet items) {
        inventory.addItems(items);
    }

    public void removeItems(ItemSet items) {
        inventory.removeItems(items);
    }

    public void addSellingItem(SellingItem sellingItem) {
        sellingItemSet.addItem(sellingItem);
    }

    public void removeSellingItem(SellingItem sellingItem) {
        sellingItemSet.removeItem(sellingItem);
    }

    public Collection<Item> getItems() {
        return inventory.getItems();
    }

    public Collection<SellingItem> getSellingItems() {
        return sellingItemSet.getItems();
    }

    public List<Craft> getRealizableCrafts() {
        return CraftManager.getRealisableCrafts(inventory);
    }

    public boolean canRealize(Craft craft) {
        return craft.isRealizable(inventory);
    }

    @Override
    public void onEvent(Event event) {
        if(event.getId() == EventID.TICK) {
            Iterator<SellingItem> iterator = sellingItemSet.iterator();
            while(iterator.hasNext()) {
                SellingItem item = iterator.next();
                if(item.sell())
                    iterator.remove();
            }
        } if(event instanceof LootCollectedEvent) {
            System.out.println("Items collected : "+((LootCollectedEvent) event).getLoot());
            addItems(((LootCollectedEvent) event).getLoot());
        }else if(event instanceof CraftRealizedEvent) {
            Craft craft = ((CraftRealizedEvent) event).getCraft();
            removeItems(craft.getComponents());
            addItem(craft.getResultItem());
        }
    }
}
