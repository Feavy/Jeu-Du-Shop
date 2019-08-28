package fr.feavy.jeuDuShop.item;

public class Item {
    private final ItemType type;
    private int quantity;

    public Item(ItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public ItemType getType() {
        return type;
    }

    void add(Item item) throws DifferentItemsException {
        if(notSameTypeAs(item))
            throw new DifferentItemsException();
        this.quantity += item.quantity;
    }

    boolean remove(Item item) throws DifferentItemsException{
        if(notSameTypeAs(item))
            throw new DifferentItemsException();

        if(quantity < item.quantity)
            return false;

        this.quantity -= item.quantity;
        return true;
    }

    boolean isMoreOrEqualsThan(Item item) throws DifferentItemsException{
        if(notSameTypeAs(item))
            throw new DifferentItemsException();
        return quantity >= item.quantity;
    }

    private boolean notSameTypeAs(Item item) {
        return type == item.getType();
    }

    @Override
    public String toString() {
        if(quantity <= 1)
            return quantity+" x "+getType().getName();
        return quantity+" x "+getType().getPluralName();
    }
}
