package fr.feavy.jeuDuShop.event;

import fr.feavy.jeuDuShop.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final static List<EventListener> listeners = new ArrayList<>();

    public static void addEventListener(EventListener listener) {
        listeners.add(listener);
    }

    public static void removeEventListener(EventListener listener) {
        listeners.remove(listener);
    }

    public static void callEvent(Event event) {
        for(EventListener listener : listeners)
            listener.onEvent(event);
    }
}
