package fr.feavy.jeuDuShop.scene;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {
    public Scene() {
    }

    public Scene(LayoutManager layout) {
        super(layout);
    }

    public abstract void onCreate();
    public abstract void onDestroy();
}
