package fr.feavy.jeuDuShop.loot;

import fr.feavy.jeuDuShop.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Loot {
    private List<Item> items = new ArrayList<>();

    public Loot(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
