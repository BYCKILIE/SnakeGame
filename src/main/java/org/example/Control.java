package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    private final Gameplay gameplay;
    private boolean start = false;

    public Control(JFrame board, JLabel[][] labels, int height, int width) {
        board.addKeyListener(this);

        gameplay = new Gameplay(labels, height, width);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getKeyCode();
        if (source.equals(KeyEvent.VK_W)) {
            gameplay.turn('w');
        } else if (source.equals(KeyEvent.VK_S)) {
            if (!start)
                return;
            gameplay.turn('s');
        } else if (source.equals(KeyEvent.VK_A)) {
            gameplay.turn('a');
        } else if (source.equals(KeyEvent.VK_D)) {
            gameplay.turn('d');
        }

        if (!start) {
            gameplay.start();
            start = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
