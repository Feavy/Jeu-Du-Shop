package fr.feavy.jeuDuShop.ui.scene.loot;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.event.LootCollectedEvent;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.item.ItemSet;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import java.awt.*;
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

        updateLoots();
        updateTimer();
    }

    private void updateLoots() {
        if(JeuDuShop.get().isLooted()) {
            JLabel feedback = new JLabel("<html><h3>Les loots ont déjà été collectés.</h3></html>");
            feedback.setHorizontalAlignment(SwingConstants.CENTER);
            centerPanel.add(feedback);
            return;
        }
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
        updateLoots();
        JOptionPane.showMessageDialog(this, "Les ressources ont été collectées !", "Ressources collectées", JOptionPane.INFORMATION_MESSAGE);
    }
}
