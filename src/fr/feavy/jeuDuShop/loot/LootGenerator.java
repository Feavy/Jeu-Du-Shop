package fr.feavy.jeuDuShop.loot;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.utils.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LootGenerator implements EventListener {
    private final List<ItemSet> currentLoots = new ArrayList<>();
    private final Random random = new Random();

    private int counter = 0;

    public LootGenerator() {
        EventManager.addEventListener(this);
        generateLoots(5, 5);
    }

    public List<ItemSet> getCurrentLoots() {
        return Collections.unmodifiableList(currentLoots);
    }

    private void generateLoots(int lootAmount, int itemAmount) {
        currentLoots.clear();
        for(int i = 0; i < lootAmount; i++)
            currentLoots.add(generateLoot(itemAmount));
        EventManager.callEvent(new Event(EventID.LOOT_GENERATED));
    }

    private ItemSet generateLoot(int itemAmount) {
        ItemSet loot = new ItemSet();
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < itemAmount; i++)
            loot.addItem(getRandomItem());
        return loot;
    }

    private Item getRandomItem() {
        int randomNumber = random.nextInt(100)+1;
        int number = 1;
        for(ItemType itemType : ItemType.values()) {
            number += itemType.getRarity()*100;
            if(number >= randomNumber)
                return new Item(itemType, 1);
        }
        return new Item(ItemType.WOOD_LOG, 1);
    }

    public int getRemeaningTime() {
        return Settings.LOOT_GENERATION_COOLDOWN_SECONDS - counter;
    }

    @Override
    public void onEvent(Event event) {
        if(event.getId() == EventID.TICK) {
            counter ++;
            if(counter >= Settings.LOOT_GENERATION_COOLDOWN_SECONDS) {
                generateLoots(5, 5);
                counter = 0;
            }
        }
    }
}
