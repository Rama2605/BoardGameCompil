package TheMain;

import javax.swing.*;
import java.awt.*;

public class main extends JFrame {

    public main() {
        setTitle("Menu Utama Game");
        setSize(400, 500); // Perbesar ukuran frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        JLabel mainTitleLabel = new JLabel("Board Game Compilation", JLabel.CENTER);
        mainTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(mainTitleLabel, BorderLayout.NORTH);

        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/images/boardgame.gif"));
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(gifLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Pilih Game Anda", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton checkerGameButton = new JButton("Permainan Checker");
        checkerGameButton.addActionListener(e -> {
            new CheckerGame().setVisible(true);
            dispose();
        });

        JButton snakeLadderGameButton = new JButton("Permainan Ular Tangga");
        snakeLadderGameButton.addActionListener(e -> {
            new UlarTanggaGame().setVisible(true);
            dispose();
        });
        
        JButton pacmanGameButton = new JButton("Permainan Pac-Man");
        pacmanGameButton.addActionListener(e -> {
            new pacman().setVisible(true);
            dispose();
        });

        buttonPanel.add(checkerGameButton);
        buttonPanel.add(snakeLadderGameButton);
        buttonPanel.add(pacmanGameButton);

        add(buttonPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Dibuat dengan Java Swing", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        add(footerLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            main mainMenu = new main();
            mainMenu.setVisible(true);
        });
    }
}
