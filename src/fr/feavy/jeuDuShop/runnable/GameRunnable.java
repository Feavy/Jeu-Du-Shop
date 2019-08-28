package fr.feavy.jeuDuShop.runnable;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;

class GameRunnable implements Runnable {
    private static final GameRunnable INSTANCE = new GameRunnable();

    static {
        new Thread(INSTANCE).start();
    }

    private int timer = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EventManager.callEvent(new Event(EventID.TICK));
        }
    }

}
