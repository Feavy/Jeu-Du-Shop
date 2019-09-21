package fr.feavy.jeuDuShop;

import fr.feavy.jeuDuShop.event.Event;
import fr.feavy.jeuDuShop.event.EventManager;
import fr.feavy.jeuDuShop.listener.EventListener;
import fr.feavy.jeuDuShop.loot.LootGenerator;
import fr.feavy.jeuDuShop.player.Player;
import fr.feavy.jeuDuShop.runnable.GameRunnable;
import fr.feavy.jeuDuShop.ui.scene.TitleScreenScene;
import fr.feavy.jeuDuShop.ui.GamePanel;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class JeuDuShop implements EventListener {
    private final static JeuDuShop INSTANCE = new JeuDuShop();

    public static JeuDuShop get() {
        return INSTANCE;
    }


    private Player player;
    private LootGenerator lootGenerator;
    private GamePanel gamePanel;
    private boolean looted;

    private JeuDuShop() {
        UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        UIManager.put("CheckBox.background", new ColorUIResource(255, 255, 255));

        this.lootGenerator = new LootGenerator();
        this.player = new Player();
        this.gamePanel = new GamePanel(new TitleScreenScene());
        new Thread(new GameRunnable()).start();
        EventManager.addEventListener(this);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public LootGenerator getLootGenerator() {
        return lootGenerator;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isLooted() {
        return looted;
    }

    @Override
    public void onEvent(Event event) {
        switch (event.getId()) {
            case LOOT_GENERATED:
                looted = false;
                break;
            case LOOT_COLLECTED:
                looted = true;
                break;
            default:
                break;
        }
    }
}
