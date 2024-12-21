package TheMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pacman extends JFrame {
    private final PacChar pacman;
    private final PacGhost ghost1, ghost2;
    private final Timer timer;

    public pacman() {
        setTitle("Pac-Man Game");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pacman = new PacChar(50, 50, "/assets/pacman-open.png", "/assets/pacman-close.png");
        ghost1 = new PacGhost(200, 100, "/assets/ghost1.gif");
        ghost2 = new PacGhost(300, 150, "/assets/ghost2.gif");

        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        timer = new Timer(100, e -> {
            ghost1.moveRandomly(getWidth(), getHeight());
            ghost2.moveRandomly(getWidth(), getHeight());
            gamePanel.repaint();
        });

        timer.start();
    }

    private class GamePanel extends JPanel implements KeyListener {
        public GamePanel() {
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            pacman.draw(g);
            ghost1.draw(g);
            ghost2.draw(g);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> pacman.setDirection(0, -5);
                case KeyEvent.VK_DOWN -> pacman.setDirection(0, 5);
                case KeyEvent.VK_LEFT -> pacman.setDirection(-5, 0);
                case KeyEvent.VK_RIGHT -> pacman.setDirection(5, 0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pacman.setDirection(0, 0);
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    }
}
