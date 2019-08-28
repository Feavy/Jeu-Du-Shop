package fr.feavy.jeuDuShop.event;

public class Event {
    private final EventID id;

    public Event(EventID id) {
        this.id = id;
    }

    public EventID getId() {
        return id;
    }
}
