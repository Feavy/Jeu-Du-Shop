package fr.feavy.jeuDuShop.ui.scene;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.ui.StyledButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TitleScreenScene extends Scene {
    public TitleScreenScene() {
        super("Jeu du Shop", new BorderLayout());

        setBorder(new EmptyBorder(200, 100, 200,  100));
        StyledButton startButton = new StyledButton("Jouer").setPadding(10, 10, 10, 10);
        startButton.addActionListener(e -> JeuDuShop.get().getGamePanel().setNewScene(new LootScene()));
        add(startButton);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {

    }
}
