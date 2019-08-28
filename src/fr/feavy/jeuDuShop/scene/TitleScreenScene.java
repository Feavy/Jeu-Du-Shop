package fr.feavy.jeuDuShop.scene;

import fr.feavy.jeuDuShop.JeuDuShop;
import fr.feavy.jeuDuShop.ui.StyledButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TitleScreenScene extends Scene {
    public TitleScreenScene() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("<html><h1>Jeu du Shop</h1></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(new EmptyBorder(200, 100, 200,  100));
        StyledButton startButton = new StyledButton("Jouer").setPadding(10, 10, 10, 10);
        startButton.addActionListener(e -> {
            JeuDuShop.get().getGamePanel().setNewScene(new LootScene());
        });
        center.add(startButton);
        add(center, BorderLayout.CENTER);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {

    }
}
