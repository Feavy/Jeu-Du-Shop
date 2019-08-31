package fr.feavy.jeuDuShop.ui.scene.craft;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.craft.Craft;
import fr.feavy.jeuDuShop.craft.CraftManager;
import fr.feavy.jeuDuShop.event.CraftRealizedEvent;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CraftScene extends Scene implements DocumentListener, ActionListener, Consumer<Craft> {
    private final JTextField nameField;
    private final JCheckBox realizableCheckbox;
    private final JPanel craftsPanel;

    private Collection<Craft> crafts = CraftManager.getAllCrafts();
    private String currentNameQuery = "";

    public CraftScene() {
        super("Crafts", new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("Objet :"));

        nameField = new JTextField(20);
        nameField.getDocument().addDocumentListener(this);
        northPanel.add(nameField);

        realizableCheckbox = new JCheckBox("Réalisables ?");
        realizableCheckbox.addActionListener(this);
        northPanel.add(realizableCheckbox);

        add(northPanel, BorderLayout.NORTH);

        craftsPanel = new JPanel(new GridLayout(crafts.size() < 4 ? 4 : crafts.size(), 0));
        craftsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        updateCrafts();

        JScrollPane craftScrollPane = new JScrollPane(craftsPanel);

        add(craftScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    private void updateCrafts() {
        craftsPanel.removeAll();
        int preferredHeight = 0;
        for(Craft craft : crafts) {
            CraftItem currentItem = new CraftItem(craft, this);
            if(!JeuDuShop.get().getPlayer().canRealize(craft))
                currentItem.setEnabled(false);
            craftsPanel.add(currentItem);
            preferredHeight += currentItem.getPreferredSize().height;
        }
        craftsPanel.setPreferredSize(new Dimension(craftsPanel.getPreferredSize().width, preferredHeight));
        craftsPanel.repaint();
        craftsPanel.revalidate();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        onTextChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onTextChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        onTextChange();
    }

    private void onTextChange() {
        currentNameQuery = nameField.getText();
        applyCurrentSelectedCraftsFilter();
        applyCurrentNameFilter();
        updateCrafts();
    }

    private void applyCurrentNameFilter() {
        crafts = crafts.stream().filter(craft -> craft.getResultItem().getType().getName().contains(currentNameQuery)).collect(Collectors.toList());
    }

    private void applyCurrentSelectedCraftsFilter() {
        if(realizableCheckbox.isSelected()) {
            crafts = JeuDuShop.get().getPlayer().getRealizableCrafts();
        }else{
            crafts = CraftManager.getAllCrafts();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        applyCurrentSelectedCraftsFilter();
        applyCurrentNameFilter();
        updateCrafts();
    }

    @Override
    public void accept(Craft craft) {
        EventManager.callEvent(new CraftRealizedEvent(craft));
        updateCrafts();
    }
}
