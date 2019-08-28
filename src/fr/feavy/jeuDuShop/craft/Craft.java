package fr.feavy.jeuDuShop.craft;

import com.sun.istack.internal.NotNull;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemSet;

public class Craft {
    private final Item[] components;
    private final Item resultItem;

    Craft(Item[] components, Item resultItem) {
        this.components = components;
        this.resultItem = resultItem;
    }

    public Item[] getComponents() {
        return components;
    }

    public Item getResultItem() {
        return resultItem;
    }

    boolean isRealizable(@NotNull ItemSet inventory) {
        for(Item component : components) {
            if(!inventory.hasItem(component))
                return false;
        }
        return true;
    }
}
