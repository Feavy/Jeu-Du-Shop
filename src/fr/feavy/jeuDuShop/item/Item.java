package fr.feavy.jeuDuShop.item;

public class Item {
    private final ItemType type;
    protected int amount;

    public Item(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public ItemType getType() {
        return type;
    }

    public int getDefaultSellPrice() {
        return type.getSellPrice()*amount;
    }

    void add(Item item) throws DifferentItemsException {
        if(notSameTypeAs(item))
            throw new DifferentItemsException();
        this.amount += item.amount;
    }

    boolean remove(Item item) throws DifferentItemsException{
        if(notSameTypeAs(item))
            throw new DifferentItemsException();

        if(amount < item.amount)
            return false;

        this.amount -= item.amount;
        return true;
    }

    boolean isMoreOrEqualsThan(Item item) throws DifferentItemsException{
        if(notSameTypeAs(item))
            throw new DifferentItemsException();
        return amount >= item.amount;
    }

    private boolean notSameTypeAs(Item item) {
        return type != item.getType();
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        if(amount <= 1)
            return amount +" x "+getType().getName();
        return amount +" x "+getType().getPluralName();
    }
}
