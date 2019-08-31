package fr.feavy.jeuDuShop.ui.scene.craft;

import fr.feavy.jeuDuShop.craft.Craft;
import fr.feavy.jeuDuShop.item.Item;
import fr.feavy.jeuDuShop.ui.StyledButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class CraftItem extends JPanel {
    private final Craft craft;

    private final StyledButton createButton = new StyledButton("Créer").setPadding(5);;

    public CraftItem(Craft craft, Consumer<Craft> craftCallback) {
        super(new BorderLayout());
        this.craft = craft;

        JLabel itemNameLbl = new JLabel("<html><h3>"+craft.getResultItem().toString()+"</h3></html>");
        itemNameLbl.setBorder(new EmptyBorder(0, 0, -10, 0));
        add(itemNameLbl, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(new JLabel("<html><h4 style='text-decoration: underline;'> Objets nécessités : </h4></html>"), BorderLayout.NORTH);

        JPanel componentsPanel = new JPanel();
        centerPanel.add(componentsPanel, BorderLayout.CENTER);

        componentsPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        componentsPanel.setLayout(new GridLayout(2, 3));
        for(Item item : craft.getComponents())
            componentsPanel.add(new JLabel(item.toString()));

        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.EAST);

        createButton.addActionListener(e -> craftCallback.accept(craft));
        rightPanel.add(createButton);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        createButton.setEnabled(enabled);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0xA0EEEEEE, true));
        if(!isEnabled())
            g.fillRect(0, 0, getWidth(), getHeight());
    }
}
