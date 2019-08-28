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

    public void add(Item item) throws DifferentItemsException {
        if(!sameTypeAs(item))
            throw new DifferentItemsException();
        this.quantity += item.quantity;
    }

    public boolean remove(Item item) throws DifferentItemsException{
        if(!sameTypeAs(item))
            throw new DifferentItemsException();

        if(quantity < item.quantity)
            return false;

        this.quantity -= item.quantity;
        return true;
    }

    public boolean isMoreOrEqualsThan(Item item) throws DifferentItemsException{
        if(!sameTypeAs(item))
            throw new DifferentItemsException();
        return quantity >= item.quantity;
    }

    public boolean sameTypeAs(Item item) {
        return type == item.getType();
    }
}
