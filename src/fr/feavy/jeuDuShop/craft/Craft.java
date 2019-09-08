package fr.feavy.jeuDuShop.craft;

import com.sun.istack.internal.NotNull;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemSet;

public class Craft {
    private final Item resultItem;
    private final ItemSet components;

    Craft(Item[] components, Item resultItem) {
        this.resultItem = resultItem;
        this.components = new ItemSet(components);
    }

    public ItemSet getComponents() {
        return components;
    }

    public Item getResultItem() {
        return resultItem;
    }

    public boolean isRealizable(@NotNull ItemSet inventory) {
        return inventory.hasItems(components);
    }
}
