package fr.feavy.jeuDuShop;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jeu du Shop");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(JeuDuShop.get().getGamePanel());
        frame.setVisible(true);
    }
}
