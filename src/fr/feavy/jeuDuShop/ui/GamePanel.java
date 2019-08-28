package fr.feavy.jeuDuShop.ui;

import fr.feavy.jeuDuShop.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Scene activeScene;

    public GamePanel(Scene currentScene) {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        StyledButton recolteButton = new StyledButton("RECOLTE").setPadding(20, 0, 20, 0);

        southPanel.add(recolteButton);
        southPanel.add(new StyledButton("CRAFT"));
        southPanel.add(new StyledButton("SHOP"));
        add(southPanel, BorderLayout.SOUTH);

        this.activeScene = currentScene;
        add(activeScene, BorderLayout.CENTER);
    }

    public void setNewScene(Scene scene) {
        if(activeScene != null) {
            activeScene.onDestroy();
            remove(activeScene);
        }
        this.activeScene = scene;
        activeScene.onCreate();
        add(activeScene, BorderLayout.CENTER);
    }

    public Scene getActiveScene() {
        return activeScene;
    }
}
