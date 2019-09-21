package fr.feavy.jeuDuShop.player;

public class Money {
    public final static String currency = "$";
    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return amount+" "+currency;
    }
}
