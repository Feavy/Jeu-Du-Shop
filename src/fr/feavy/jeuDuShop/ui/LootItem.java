package fr.feavy.jeuDuShop.ui;

import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class LootItem extends JPanel {
    private final ItemSet loot;
    private final StyledButton collectButton = new StyledButton(">");

    private final JPanel centerPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();

    public LootItem(ItemSet loot, Consumer<LootItem> collectCallback) {
        super(new BorderLayout());
        this.loot = loot;
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setBorder(new EmptyBorder(20, 0, 20, 0));

        centerPanel.setLayout(new GridLayout(2, 3));
        for(Item item : loot.getItems())
            centerPanel.add(new JLabel(item.toString()));

        collectButton.addActionListener(e -> collectCallback.accept(LootItem.this));
        collectButton.setPadding(5, 10, 5, 10);
        rightPanel.add(collectButton);
    }

    public ItemSet getLoot() {
        return loot;
    }
}
