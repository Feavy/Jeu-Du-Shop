package fr.feavy.jeuDuShop.ui.scene.loot;

import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.ui.StyledButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.List;

public class LootItem extends JPanel {
    private final ItemSet loot;
    private final StyledButton collectButton = new StyledButton(">");

    private final JPanel centerPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();

    public LootItem(ItemSet loot, Consumer<LootItem> collectCallback) {
        super(new BorderLayout());
        this.loot = loot;
        setBorder(new EmptyBorder(20, 0, 20, 0));
        add(rightPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new GridLayout(2, 3));
        for(Item item : loot)
            centerPanel.add(new JLabel(item.toString()));

        collectButton.addActionListener(e -> collectCallback.accept(this));
        collectButton.setPadding(5, 10, 5, 10);
        rightPanel.add(collectButton);
    }

    public ItemSet getLoot() {
        return loot;
    }
}
