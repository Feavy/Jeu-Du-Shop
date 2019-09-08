package fr.feavy.jeuDuShop.ui.scene.shop;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.item.SellingItem;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.ui.StyledButton;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShopScene extends Scene {

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

        //sellPanel.add(new JLabel("Objet : "));
        JSpinner jSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        jSpinner.setMaximumSize(new Dimension(20, 20));
        jSpinner.setPreferredSize(new Dimension(40, 20));
        sellPanel.add(jSpinner);
        sellPanel.add(new JLabel(" x "));
        List<ItemType> itemTypes = JeuDuShop.get().getPlayer().getItems().stream().map(item -> item.getType()).collect(Collectors.toList());
        sellPanel.add(new JComboBox<ItemType>(itemTypes.toArray(new ItemType[0])));
        sellPanel.add(new JLabel("Prix : "));

        JSpinner jSpinner2 = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        jSpinner2.setMaximumSize(new Dimension(20, 20));
        jSpinner2.setPreferredSize(new Dimension(60, 20));

        sellPanel.add(jSpinner2);
        sellPanel.add(new StyledButton("Vendre"));
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
