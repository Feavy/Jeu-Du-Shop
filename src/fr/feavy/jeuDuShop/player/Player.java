package fr.feavy.jeuDuShop.player;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.event.LootCollectedEvent;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.listener.EventListener;

public class Player implements EventListener {
    private final ItemSet inventory = new ItemSet();

    public ItemSet getInventory() {
        return inventory;
    }

    public Player() {
        EventManager.addEventListener(this);
    }


    @Override
    public void onEvent(Event event) {
        if(event instanceof LootCollectedEvent) {
            System.out.println("Items collected : "+((LootCollectedEvent) event).getLoot());
            getInventory().addItems(((LootCollectedEvent) event).getLoot());
        }
    }
}
