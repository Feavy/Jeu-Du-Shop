package fr.feavy.jeuDuShop.event;

import fr.feavy.jeuDuShop.craft.Craft;

public class CraftRealizedEvent extends Event {
    private final Craft craft;

    public CraftRealizedEvent(Craft craft) {
        super(EventID.CRAFT_REALIZED);
        this.craft = craft;
    }

    public Craft getCraft() {
        return craft;
    }
}
