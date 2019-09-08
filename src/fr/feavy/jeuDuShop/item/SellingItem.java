package fr.feavy.jeuDuShop.item;

public class SellingItem {
    private final Item item;
    private final int price;
    private int remainingTimeBeforeSell;

    public SellingItem(Item item, int price) {
        this.item = item;
        this.price = price;
        this.remainingTimeBeforeSell = item.getDefaultSellPrice()/5 + price-item.getDefaultSellPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public boolean sell() {
        if(remainingTimeBeforeSell > 0)
            return false;

        return true;
    }
}
