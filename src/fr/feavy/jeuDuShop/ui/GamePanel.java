package fr.feavy.jeuDuShop.ui;

import fr.feavy.jeuDuShop.ui.scene.LootScene;
import fr.feavy.jeuDuShop.ui.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Scene activeScene;
    private JLabel activeTitle;

    private JButton collectButton = new StyledButton("RECOLTE").setPadding(20, 0, 20, 0),
                    craftButton = new StyledButton("CRAFT").setPadding(20, 0, 20, 0),
                    shopButton = new StyledButton("SHOP").setPadding(20, 0, 20, 0);

    public GamePanel(Scene currentScene) {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel southPanel = new JPanel(new GridLayout(1, 3));

        collectButton.addActionListener(e -> setNewScene(new LootScene()));
        southPanel.add(collectButton);


        southPanel.add(craftButton);


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
            remove(activeTitle);
        }
        this.activeScene = scene;
        activeScene.onCreate();
        add(activeScene, BorderLayout.CENTER);
        this.activeTitle = new JLabel("<html><h1>"+scene.getTitle()+"</h1></html>");
        this.activeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(activeTitle, BorderLayout.NORTH);
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
