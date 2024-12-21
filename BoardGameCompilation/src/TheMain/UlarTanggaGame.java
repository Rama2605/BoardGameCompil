package TheMain;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UlarTanggaGame extends JFrame {
    private final int SIZE = 10;
    private final JButton[][] squares = new JButton[SIZE][SIZE];
    private int playerPosition = 1;
    private int previousPosition = 1;
    private final JLabel statusLabel = new JLabel("Pemain berada di kotak 1");
    private final JLabel diceLabel = new JLabel();
    private final Random random = new Random();
    private final String diceImagePath = "src/images/dice-";
    private final String pawnImagePath = "src/images/pawn";
    private final JLabel playerPawn = new JLabel(); 

    public UlarTanggaGame() {
        setTitle("Permainan Ular Tangga");
        setSize(800, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setEnabled(false);
                button.setFont(new Font("Arial", Font.BOLD, 15));
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                int num = (SIZE - i) * SIZE - j;
                if (i % 2 != 0) {
                    num = (SIZE - i) * SIZE - (SIZE - 1 - j);
                }
                button.setText(String.valueOf(num));
                squares[i][j] = button;
                boardPanel.add(button);
            }
        }

        addLaddersAndSnakes();

        squares[9][0].setIcon(new ImageIcon(pawnImagePath + "1.png"));

        JButton rollButton = new JButton("Lempar Dadu");
        rollButton.setFont(new Font("Arial", Font.BOLD, 18));
        rollButton.addActionListener(e -> rollDice());

        diceLabel.setHorizontalAlignment(JLabel.CENTER);
        diceLabel.setIcon(new ImageIcon(diceImagePath + "1.jpg"));

        add(boardPanel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
        add(diceLabel, BorderLayout.EAST);
    }

    private void addLaddersAndSnakes() {
        squares[8][2].setText("T↑");
        squares[6][5].setText("T↑");
        squares[4][1].setText("T↑");

        squares[2][4].setText("U↓");
        squares[5][6].setText("U↓");
        squares[7][1].setText("U↓");
    }

    private void rollDice() {
        Timer diceAnimation = new Timer(100, null);
        diceAnimation.addActionListener(e -> {
            int tempValue = random.nextInt(6) + 1;
            diceLabel.setIcon(new ImageIcon(diceImagePath + tempValue + ".jpg"));
        });
        diceAnimation.setRepeats(true);
        diceAnimation.start();

        Timer stopTimer = new Timer(1000, e -> {
            diceAnimation.stop();
            int diceValue = random.nextInt(6) + 1;
            diceLabel.setIcon(new ImageIcon(diceImagePath + diceValue + ".jpg"));
            movePlayer(diceValue);
        });
        stopTimer.setRepeats(false);
        stopTimer.start();
    }

    private void movePlayer(int diceValue) {
    playerPosition += diceValue;

    if (playerPosition > 100) {
        playerPosition = 100 - (playerPosition - 100);
    }

    statusLabel.setText("Dadu: " + diceValue + ". Pemain berada di kotak " + playerPosition);
    updateBoard();

    if (playerPosition == 100) {
        JOptionPane.showMessageDialog(this, "Selamat! Anda menang!");
    }
}



private void updateBoard() {
    int[] prevPosition = getRowColFromPosition(previousPosition);
    squares[prevPosition[0]][prevPosition[1]].setIcon(null);

    int[] newPosition = getRowColFromPosition(playerPosition);
    ImageIcon icon = new ImageIcon(pawnImagePath + "1.png");
    Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    squares[newPosition[0]][newPosition[1]].setIcon(new ImageIcon(scaledImage));

    previousPosition = playerPosition;

    squares[newPosition[0]][newPosition[1]].revalidate();
    squares[newPosition[0]][newPosition[1]].repaint();
}



    private int[] getRowColFromPosition(int position) {
    int row = (position - 1) / SIZE;
    int col = (position - 1) % SIZE;

    if ((row % 2) == 1) {
        col = SIZE - 1 - col;
    }

    row = SIZE - 1 - row;

    return new int[]{row, col};
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UlarTanggaGame().setVisible(true));
    }
}


