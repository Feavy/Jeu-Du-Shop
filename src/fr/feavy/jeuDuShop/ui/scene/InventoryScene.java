package fr.feavy.jeuDuShop.ui.scene;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.item.Item;

import javax.swing.*;
import java.awt.*;

public class InventoryScene extends Scene {
    public InventoryScene() {
        super("Inventaire", new BorderLayout());

        JList<Item> items = new JList<Item>(JeuDuShop.get().getPlayer().getItems().toArray(new Item[0]));
        add(items, BorderLayout.CENTER);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
