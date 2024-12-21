/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckerGame extends JFrame {
    private final int SIZE = 8;
    private final JPanel[][] squares = new JPanel[SIZE][SIZE];
    private String selectedPiece = null;
    private int selectedX = -1;
    private int selectedY = -1;

    public CheckerGame() {
        setTitle("Permainan Checker");
        setSize(600, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if ((i + j) % 2 == 0) {
                    panel.setBackground(Color.WHITE);
                } else {
                    panel.setBackground(Color.BLACK);
                }
                final int x = i;
                final int y = j;

                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(x, y);
                    }
                });

                squares[i][j] = panel;
                add(panel);
            }
        }

        placePieces();
    }

    private void placePieces() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 != 0) {
                    squares[i][j].add(new JLabel("⚫"));
                }
            }
        }
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 != 0) {
                    squares[i][j].add(new JLabel("⚪"));
                }
            }
        }
    }

    private void handleSquareClick(int x, int y) {
        if (selectedPiece == null) {
            if (squares[x][y].getComponentCount() > 0) {
                JLabel piece = (JLabel) squares[x][y].getComponent(0);
                selectedPiece = piece.getText();
                selectedX = x;
                selectedY = y;
                squares[x][y].remove(piece);
                squares[x][y].revalidate();
                squares[x][y].repaint();
            }
        } else {
            // Jika ada bidak yang dipilih, pindahkan ke kotak yang diklik
            if (isValidMove(selectedX, selectedY, x, y)) {
                squares[x][y].add(new JLabel(selectedPiece));
                squares[x][y].revalidate();
                squares[x][y].repaint();
                selectedPiece = null; // Reset pilihan
            } else {
                squares[selectedX][selectedY].add(new JLabel(selectedPiece));
                squares[selectedX][selectedY].revalidate();
                squares[selectedX][selectedY].repaint();
                selectedPiece = null; // Reset pilihan
            }
        }
    }

    private boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (toX < 0 || toX >= SIZE || toY < 0 || toY >= SIZE) {
            return false;
        }

        if (squares[toX][toY].getComponentCount() > 0) {
            return false;
        }

        if (Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 1) {
            return true;
        }

  if (Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY) == 2) {
            int midX = (fromX + toX) / 2;
            int midY = (fromY + toY) / 2;

            if (squares[midX][midY].getComponentCount() > 0) {
                JLabel middlePiece = (JLabel) squares[midX][midY].getComponent(0);

                if (!middlePiece.getText().equals(selectedPiece)) {
                    squares[midX][midY].remove(middlePiece);
                    squares[midX][midY].revalidate();
                    squares[midX][midY].repaint();
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckerGame game = new CheckerGame();
            game.setVisible(true);
        });
    }
}