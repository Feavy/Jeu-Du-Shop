package fr.feavy.jeuDuShop.event;

import fr.feavy.jeuDuShop.item.ItemSet;

public class LootCollectedEvent extends Event {
    private final ItemSet loot;

    public LootCollectedEvent(ItemSet loot) {
        super(EventID.LOOT_COLLECTED);
        this.loot = loot;
    }

    public ItemSet getLoot() {
        return loot;
    }
}
