package fr.feavy.jeuDuShop.ui.scene.shop;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemType;
import fr.feavy.jeuDuShop.item.SellingItem;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.player.Player;
import fr.feavy.jeuDuShop.ui.StyledButton;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShopScene extends Scene {
    private ComboBoxModel<ItemType> itemTypesModel;
    private JComboBox itemTypesComboBox;
    private JSpinner amountSpinner;
    private JSpinner priceSpinner;

    private JPanel sellingItemsPanel;

    public ShopScene() {
        super("Boutique", new BorderLayout());

        sellingItemsPanel = new JPanel();
        sellingItemsPanel.setLayout(new GridLayout(5, 1));
        JScrollPane scrollPane = new JScrollPane(sellingItemsPanel);

        for(SellingItem item : JeuDuShop.get().getPlayer().getSellingItems())
            sellingItemsPanel.add(new ShopItem(item));

        add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        add(southPanel, BorderLayout.SOUTH);

        southPanel.add(new JLabel("<html><h3>Vendre :</h3></html>"), BorderLayout.NORTH);
        JPanel sellPanel = new JPanel();
        southPanel.add(sellPanel, BorderLayout.CENTER);

        amountSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
        amountSpinner.setPreferredSize(new Dimension(40, 20));

        sellPanel.add(amountSpinner);
        sellPanel.add(new JLabel(" x "));
        List<ItemType> itemTypes = JeuDuShop.get().getPlayer().getItems().stream().map(item -> item.getType()).collect(Collectors.toList());
        itemTypesModel = new DefaultComboBoxModel<>(itemTypes.toArray(new ItemType[0]));
        itemTypesComboBox = new JComboBox<ItemType>(itemTypesModel);
        itemTypesComboBox.setPrototypeDisplayValue(ItemType.WOOD_PLANKS);
        itemTypesComboBox.addActionListener(e -> onNewItemTypeSelected((ItemType) itemTypesComboBox.getSelectedItem()));
        sellPanel.add(itemTypesComboBox);
        sellPanel.add(new JLabel("Prix : "));

        priceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        priceSpinner.setPreferredSize(new Dimension(60, 20));

        sellPanel.add(priceSpinner);

        StyledButton buyButton = new StyledButton(">").setPadding(3, 7, 3, 7);
        buyButton.addActionListener(e -> onBuyButtonClicked());
        sellPanel.add(buyButton);

        if(itemTypes.size() > 0)
            onNewItemTypeSelected(itemTypes.get(0));
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    public void updateItems() {
        List<ItemType> itemTypes = JeuDuShop.get().getPlayer().getItems().stream().map(item -> item.getType()).collect(Collectors.toList());
        itemTypesModel = new DefaultComboBoxModel<>(itemTypes.toArray(new ItemType[0]));
        itemTypesComboBox.setModel(itemTypesModel);

        itemTypesComboBox.repaint();
        itemTypesComboBox.revalidate();

        if(itemTypes.size() > 0)
            onNewItemTypeSelected(itemTypes.get(0));

        sellingItemsPanel.removeAll();
        for(SellingItem item : JeuDuShop.get().getPlayer().getSellingItems())
            sellingItemsPanel.add(new ShopItem(item));

        sellingItemsPanel.repaint();
        sellingItemsPanel.revalidate();
    }

    public void onNewItemTypeSelected(ItemType itemType) {
        int maxQuantity = JeuDuShop.get().getPlayer().getItemAmount(itemType);

        amountSpinner.setModel(new SpinnerNumberModel(1, 1, maxQuantity, 1));
    }

    public void onBuyButtonClicked() {
        if(itemTypesComboBox.getSelectedItem() == null)
            return;

        SellingItem sellingItem = new SellingItem((ItemType) itemTypesComboBox.getSelectedItem(),
                                                  (int)amountSpinner.getValue(),
                                                  (int)priceSpinner.getValue());

        Player player = JeuDuShop.get().getPlayer();
        player.removeItem(sellingItem.getItem());
        player.addSellingItem(sellingItem);

        updateItems();
    }
}
