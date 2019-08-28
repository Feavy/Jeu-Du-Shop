package fr.feavy.jeuDuShop.ui.scene;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventID;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.event.LootCollectedEvent;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.ui.LootItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class LootScene extends Scene implements EventListener, Consumer<LootItem> {
    private JPanel centerPanel = new JPanel();
    private JLabel remainingTimeLabel = new JLabel("...");

    public LootScene() {
        super("Loots", new BorderLayout());

        centerPanel.setLayout(new GridLayout(0,1));
        add(centerPanel, BorderLayout.CENTER);

        remainingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(remainingTimeLabel, BorderLayout.SOUTH);

        EventManager.addEventListener(this);
        if(!JeuDuShop.get().isLooted())
            updateLoots();
        updateTimer();
    }

    private void updateLoots() {
        centerPanel.removeAll();
        for(ItemSet loot : JeuDuShop.get().getLootGenerator().getCurrentLoots()) {
            centerPanel.add(new LootItem(loot, this));
        }
        centerPanel.repaint();
        centerPanel.revalidate();
    }

    private void updateTimer() {
        remainingTimeLabel.setText(JeuDuShop.get().getLootGenerator().getRemeaningTime()+" secondes restantes avant les prochains loots.");
    }

    @Override
    public void onCreate() {
        EventManager.addEventListener(this);
    }

    @Override
    public void onDestroy() {
        EventManager.removeEventListener(this);
    }

    @Override
    public void onEvent(Event event) {
        switch(event.getId()) {
            case TICK:
                updateTimer();
                break;
            case LOOT_GENERATED:
                updateLoots();
                break;
            default:
                break;
        }
    }

    @Override
    public void accept(LootItem lootItem) {
        centerPanel.removeAll();
        centerPanel.repaint();
        centerPanel.revalidate();
        EventManager.callEvent(new LootCollectedEvent(lootItem.getLoot()));
        JOptionPane.showMessageDialog(this, "Les ressources ont été collectées !", "Ressources collectées", JOptionPane.INFORMATION_MESSAGE);
    }
}
