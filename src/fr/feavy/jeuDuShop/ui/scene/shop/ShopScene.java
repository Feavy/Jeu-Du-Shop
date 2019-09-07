package fr.feavy.jeuDuShop.ui.scene.shop;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.item.SellingItem;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class ShopScene extends Scene implements EventListener {

    public ShopScene() {
        super("Boutique", new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        JPanel sellingItemsPanel = new JPanel();
        add(scrollPane, BorderLayout.CENTER);
        
        for(SellingItem item : JeuDuShop.get().getPlayer().getSellingItems())
            sellingItemsPanel.add(new ShopItem(item));
        JeuDuShop.get().getPlayer().getSellingItems();

        JPanel southPanel = new JPanel(new BorderLayout());
        add(southPanel, BorderLayout.SOUTH);

        southPanel.add(new JLabel("<html><h3>Vendre :</h3></html>"), BorderLayout.NORTH);
        JPanel sellPanel = new JPanel();
        southPanel.add(sellPanel, BorderLayout.CENTER);

        sellPanel.add(new JLabel("Objet : "));
        sellPanel.add(new JComboBox<ItemType>(ItemType.values()));
        sellPanel.add(new JLabel("Prix : "));
        sellPanel.add(new JSpinner(new SpinnerNumberModel(1, 0, 10000, 1)));

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onEvent(Event event) {
        if(event.getId() == EventID.TICK) {

        }
    }
}
