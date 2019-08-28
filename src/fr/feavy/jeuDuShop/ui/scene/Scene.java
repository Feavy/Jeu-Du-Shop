package fr.feavy.jeuDuShop.ui.scene;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Scene extends JPanel {
    private final String title;

    public Scene(String title) {
        this(title, new FlowLayout());
    }

    Scene(String title, LayoutManager layout) {
        super(layout);
        this.title = title;
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    public abstract void onCreate();
    public abstract void onDestroy();

    public String getTitle() {
        return title;
    }
}
