package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    public MainFrame(int lines, int columns) {
        JFrame frame = new JFrame("Snake");

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0x75EA45));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel title = new JLabel();
        title.setIcon(new ImageIcon("title.gif"));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(title, BorderLayout.NORTH);
        frame.add(new Board(lines, columns, frame).getMainPanel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

}
