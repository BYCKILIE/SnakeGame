package org.example;

import javax.swing.*;
import java.awt.*;

public class Board {
    private static final int height = 20, width = 20;
    private final JPanel mainPanel = new JPanel();

    public Board(int lines, int columns, JFrame mainFrame) {
        mainPanel.setPreferredSize(new Dimension(columns * width + 15, lines * height + 10));
        mainPanel.setBackground(new Color(0x75EA45));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel[][] labels = new JLabel[lines][columns];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                labels[i][j].setPreferredSize(new Dimension(width, height));
                labels[i][j].setBackground(giveColor(i, j));
                mainPanel.add(labels[i][j]);
            }

        }

        new Control(mainFrame, labels, lines, columns);
    }

    private Color giveColor(int i, int j) {
        if (i % 2 == 0) {
            if (j % 2 == 0) {
                return new Color(0x329F05);
            } else {
                return new Color(0x1E6401);
            }
        } else {
            if (j % 2 != 0) {
                return new Color(0x329F05);
            } else {
                return new Color(0x1E6401);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
