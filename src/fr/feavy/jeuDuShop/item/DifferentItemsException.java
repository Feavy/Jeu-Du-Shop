package fr.feavy.jeuDuShop.item;

public class DifferentItemsException extends RuntimeException {
    public DifferentItemsException() {
        super("Operations between different items are not allowed.");
    }
}
