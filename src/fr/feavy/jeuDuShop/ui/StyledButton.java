package fr.feavy.jeuDuShop.ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StyledButton extends JButton {
    private LineBorder lineBorder = new LineBorder(Color.DARK_GRAY);

    public StyledButton(String text) {
        super(text);
        setBackground(Color.LIGHT_GRAY);
        setBorder(lineBorder);
    }

    public StyledButton setPadding(int top, int right, int bottom, int left) {
        setBorder(new CompoundBorder(lineBorder, new EmptyBorder(top, left, bottom, right)));
        return this;
    }

    public StyledButton setPadding(int padding) {
        return setPadding(padding, padding, padding, padding);
    }
}
