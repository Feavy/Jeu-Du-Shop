package fr.feavy.jeuDuShop;

import fr.feavy.jeuDuShop.loot.LootGenerator;
import fr.feavy.jeuDuShop.player.Player;
import fr.feavy.jeuDuShop.scene.Scene;
import fr.feavy.jeuDuShop.scene.TitleScreenScene;
import fr.feavy.jeuDuShop.ui.GamePanel;

public class JeuDuShop {
    private final static JeuDuShop INSTANCE = new JeuDuShop();

    public static JeuDuShop get() {
        return INSTANCE;
    }


    private Player player;
    private LootGenerator lootGenerator;
    private GamePanel gamePanel;

    private JeuDuShop() {
        this.lootGenerator = new LootGenerator();
        this.gamePanel = new GamePanel(new TitleScreenScene());
        this.player = new Player();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public LootGenerator getLootGenerator() {
        return lootGenerator;
    }
}
