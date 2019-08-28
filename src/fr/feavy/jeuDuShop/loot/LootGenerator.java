package fr.feavy.jeuDuShop.loot;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.utils.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LootGenerator implements EventListener {
    private final List<Loot> currentLoots = new ArrayList<>();
    private final Random random = new Random();

    private int counter = 0;

    public LootGenerator() {
        EventManager.addEventListener(this);
    }

    public List<Loot> getCurrentLoots() {
        return Collections.unmodifiableList(currentLoots);
    }

    private void generateLoots(int lootAmount, int itemAmount) {
        currentLoots.clear();
        for(int i = 0; i < lootAmount; i++)
            currentLoots.add(generateLoot(itemAmount));
        EventManager.callEvent(new Event(EventID.LOOT_GENERATED));
    }

    private Loot generateLoot(int itemAmount) {
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < itemAmount; i++)
            items.add(getRandomItem());
        return new Loot(items);
    }

    private Item getRandomItem() {
        int randomNumber = random.nextInt(100)+1;
        int number = 1;
        for(ItemType itemType : ItemType.values()) {
            number += itemType.getRarity()*100;
            if(number >= randomNumber)
                return new Item(itemType, 1);
        }
        return null;
    }

    @Override
    public void onEvent(Event event) {
        if(event.getId() == EventID.TICK) {
            counter ++;
            if(counter >= Settings.LOOT_GENERATION_COOLDOWN_SECONDS) {
                generateLoots(3, 5);
                counter = 0;
            }
        }
    }
}
