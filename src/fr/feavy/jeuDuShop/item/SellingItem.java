package fr.feavy.jeuDuShop.item;

public class SellingItem extends Item{
    private final int price;
    private int remainingTimeBeforeSell;

    public SellingItem(Item item, int price) {
        super(item.getType(), item.amount);
        this.price = price;
        this.remainingTimeBeforeSell = item.getDefaultSellPrice()/5 + price-item.getDefaultSellPrice();
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
