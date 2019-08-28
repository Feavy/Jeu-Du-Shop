package fr.feavy.jeuDuShop.scene;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.listener.EventListener;

public class LootScene extends Scene implements EventListener {
    public LootScene() {

    }

    @Override
    public void onCreate() {
        EventManager.addEventListener(this);
    }

    @Override
    public void onDestroy() {
        EventManager.removeEventListener(this);
    }

    @Override
    public void onEvent(Event event) {
        if(event.getId() == EventID.LOOT_GENERATED) {

        }
    }
}
