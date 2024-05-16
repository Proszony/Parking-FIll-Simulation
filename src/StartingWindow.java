import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow extends JFrame {

    private StartListener startListener;

    StartingWindow(StartListener startListener) {
        this.startListener = startListener;
        initializeUI();
    }

    private void initializeUI() {
        // Tworzenie etykiety dla tła
        JLabel background = new JLabel(new ImageIcon("res/start.jpg"));
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.insets = new Insets(8, 75, 8, 75); // Dodanie odstępu między przyciskami

        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("MAX CARS: " + (i + 1));
            final int value = i + 1;
            button.setFocusable(false);
            button.setFont(new Font("Comic Sans", Font.BOLD, 14));
            button.setForeground(Color.black);
            button.setBackground(new Color(255, 229, 204)); // Kolor pastelowy (jasny różowy)
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setBorder(BorderFactory.createLineBorder(new Color(165, 169, 163), 2)); // Granica przycisku w kolorze pastelowym
            button.setPreferredSize(new Dimension(70, 65)); // Zmniejszenie szerokości przycisku
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startListener != null) {
                        startListener.onStart();
                    }
                    dispose();
                    GamePanel.setMaxCarsOnScreen(value);
                }
            });
            gbc.gridy = i; // Ustawienie wiersza w siatce
            background.add(button, gbc);
        }

        // Ustawienie tła okna na etykietę z obrazem
        setContentPane(background);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 665);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        StartingWindow startingWindow = new StartingWindow(null);
    }
}
