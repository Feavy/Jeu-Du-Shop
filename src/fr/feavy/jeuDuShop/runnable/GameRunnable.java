package fr.feavy.jeuDuShop.runnable;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;

public class GameRunnable implements Runnable {
    public GameRunnable() {}

    private int timer = 0;

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                EventManager.callEvent(new Event(EventID.TICK));
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
