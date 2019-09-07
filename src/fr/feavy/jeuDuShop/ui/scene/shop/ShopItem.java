package fr.feavy.jeuDuShop.ui.scene.shop;

import fr.feavy.jeuDuShop.item.SellingItem;

import javax.swing.*;

public class ShopItem extends JPanel {
    private final SellingItem sellingItem;

    public ShopItem(SellingItem item) {
        this.sellingItem = item;
        add(new JLabel(item.toString()));
    }

    public SellingItem getSellingItem() {
        return sellingItem;
    }
}
