package fr.feavy.jeuDuShop.ui;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.player.Money;
import fr.feavy.jeuDuShop.ui.scene.InventoryScene;
import fr.feavy.jeuDuShop.ui.scene.craft.CraftScene;
import fr.feavy.jeuDuShop.ui.scene.loot.LootScene;
import fr.feavy.jeuDuShop.ui.scene.Scene;
import fr.feavy.jeuDuShop.ui.scene.shop.ShopScene;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Scene activeScene;
    private JLabel activeTitle;

    private JButton collectButton = new StyledButton("RECOLTE").setPadding(20, 0, 20, 0),
                    craftButton = new StyledButton("CRAFT").setPadding(20, 0, 20, 0),
                    shopButton = new StyledButton("SHOP").setPadding(20, 0, 20, 0),
                    inventoryButton = new StyledButton("Inventaire").setPadding(5);

    private JPanel northPanel = new JPanel();

    public GamePanel(Scene currentScene) {
        super(new BorderLayout());

        setBackground(Color.WHITE);

        northPanel.setLayout(new BorderLayout());

        inventoryButton.addActionListener(e -> setNewScene(new InventoryScene()));
        northPanel.add(inventoryButton, BorderLayout.EAST);

        northPanel.add(new JLabel(" Argent : "+new Money(0).toString()), BorderLayout.WEST);
        add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new GridLayout(1, 3));

        collectButton.addActionListener(e -> setNewScene(new LootScene()));
        southPanel.add(collectButton);

        craftButton.addActionListener(e -> setNewScene(new CraftScene()));
        southPanel.add(craftButton);

        shopButton.addActionListener(e -> setNewScene(new ShopScene()));
        southPanel.add(shopButton);

        add(southPanel, BorderLayout.SOUTH);

        setNewScene(currentScene);
    }

    public void setNewScene(Scene scene) {
        if(activeScene != null) {
            activeScene.onDestroy();
            remove(activeScene);
        }
        if(activeTitle != null) {
            northPanel.remove(activeTitle);
        }
        this.activeScene = scene;
        activeScene.onCreate();
        add(activeScene, BorderLayout.CENTER);
        this.activeTitle = new JLabel("<html><h1>"+scene.getTitle()+"</h1></html>");
        this.activeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(activeTitle, BorderLayout.SOUTH);
        repaint();
        revalidate();
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public JButton getCollectButton() {
        return collectButton;
    }

    public JButton getCraftButton() {
        return craftButton;
    }

    public JButton getShopButton() {
        return shopButton;
    }
}
