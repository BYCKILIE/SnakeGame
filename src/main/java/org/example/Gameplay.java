package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Gameplay {
    private final LinkedList<SnakePiece> snake = new LinkedList<>();
    private final JLabel[][] labels;
    private final Icon imgSnake = resizeIcon(new ImageIcon("snake.png")),
            imgApple = resizeIcon(new ImageIcon("food.png"));
    private char direction;
    private final Random random = new Random();
    private int appleH, appleW;
    private final int height, width;
    private boolean blockMoves = false;

    public Gameplay(JLabel[][] labels, int height, int width) {
        this.labels = labels;
        this.height = height;
        this.width = width;

        int midHeight = height / 2;
        int midWidth = width / 2;
        for (int i = 0; i < 5; i++) {
            labels[midHeight][midWidth].setIcon(imgSnake);
            snake.addLast(new SnakePiece(midHeight, midWidth));
            midHeight++;
        }

        setApple();
    }

    private void incrementSnake(int h, int w, boolean grow) {
        snake.addFirst(new SnakePiece(h, w));
        labels[h][w].setIcon(imgSnake);
        if (grow) {
            setApple();
            return;
        }
        SnakePiece last = snake.removeLast();
        labels[last.h()][last.w()].setIcon(null);
    }

    private void moveStep() {
        SnakePiece first = snake.getFirst();
        int h = first.h(), w = first.w();
        boolean grow = false;

        if (direction == 'w') {
            h--;
        } else if (direction == 's') {
            h++;
        } else if (direction == 'a') {
            w--;
        } else if (direction == 'd') {
            w++;
        }

        if (h == height) {
            h = 0;
        } else if (h == -1) {
            h = height - 1;
        } else if (w == width) {
            w = 0;
        } else if (w == -1) {
            w = width - 1;
        }

        if (appleH == h & appleW == w) {
            grow = true;
        }

        incrementSnake(h, w, grow);
        blockMoves = false;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            try {
                while (!lose()) {
                    Thread.sleep(120);
                    moveStep();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    public void turn(char direction) {
        if (blockMoves)
            return;
        blockMoves = true;
        if ((this.direction == 'w' & direction == 's') | (this.direction == 's' & direction == 'w'))
            return;
        if ((this.direction == 'a' & direction == 'd') | (this.direction == 'd' & direction == 'a'))
            return;
        this.direction = direction;
    }

    private void setApple() {
        appleH = random.nextInt(0, height);
        appleW = random.nextInt(0, width);
        for (SnakePiece piece : snake) {
            if (appleH == piece.h() & appleW == piece.w()) {
                setApple();
                return;
            }
        }
        labels[appleH][appleW].setIcon(imgApple);
    }

    private boolean lose() {
        int stop = 0;
        SnakePiece first = snake.getFirst();
        for (SnakePiece piece : snake) {
            if (first.h() == piece.h() & first.w() == piece.w()) {
                stop++;
            }
        }
        return stop == 2;
    }

    private static Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}
