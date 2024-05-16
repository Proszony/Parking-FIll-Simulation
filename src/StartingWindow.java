import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartingWindow extends JFrame {

    private JLabel countLabel;
    private int count = 0;
    private StartListener startListener;

    StartingWindow(StartListener startListener) {
        this.startListener = startListener;
        initializeUI();
    }

    private void initializeUI() {
        // Tworzenie etykiety dla tła
        JLabel background = new JLabel(new ImageIcon("res/start.jpg"));
        background.setLayout(null); // Using null layout for absolute positioning

        // Panel dla przycisków plus i minus
        JButton plusButton = new JButton("ADD A CAR");
        plusButton.setBounds(75, 250, 300, 90); // Setting position and size
        plusButton.setFont(new Font("Comic Sans", Font.BOLD, 24)); // Zwiększenie rozmiaru czcionki
        plusButton.setBackground(new Color(255, 229, 204)); // Kolory tła w postaci pastelowego różowego
        plusButton.setFocusPainted(false); // Remove the focus border
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 5) { // Dodanie warunku, aby liczba nie przekraczała 5
                    count++;
                    updateCountLabel();
                }
            }
        });

        JButton minusButton = new JButton("REMOVE A CAR");
        minusButton.setBounds(525, 250, 300, 90); // Setting position and size
        minusButton.setFont(new Font("Comic Sans", Font.BOLD, 24)); // Zwiększenie rozmiaru czcionki
        minusButton.setBackground(new Color(255, 229, 204)); // Kolory tła w postaci pastelowego różowego
        minusButton.setFocusPainted(false); // Remove the focus border
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count > 0) {
                    count--;
                    updateCountLabel();
                }
            }
        });

        // Label for count with background
        countLabel = new JLabel(String.valueOf("CARS: " + count), SwingConstants.CENTER);
        countLabel.setFont(new Font("Comic Sans", Font.BOLD, 24)); // Zmniejszenie rozmiaru czcionki
        countLabel.setOpaque(true);
        countLabel.setBackground(new Color(255, 160, 140)); // Background color
        countLabel.setBounds(375, 250, 150, 90); // Setting position and size

        // Panel dla przycisku start
        JButton startButton = new JButton("START");
        startButton.setBounds(310, 450, 300, 90); // Setting position and size
        startButton.setFont(new Font("Comic Sans", Font.BOLD, 24)); // Zwiększenie rozmiaru czcionki
        startButton.setBackground(new Color(255, 229, 204)); // Kolory tła w postaci pastelowego różowego
        startButton.setFocusPainted(false); // Remove the focus border
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startListener != null) {
                    startListener.onStart();
                }
                dispose();
                GamePanel.setMaxCarsOnScreen(count);
            }
        });

        // Adding components directly to the background
        background.add(plusButton);
        background.add(minusButton);
        background.add(countLabel);
        background.add(startButton);

        // Setting the content pane to the background label
        setContentPane(background);

        setTitle("Starting Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 665); // Ustawienie rozmiaru okna
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource("/car-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(img);
    }

    private void updateCountLabel() {
        countLabel.setText(String.valueOf("CARS: " + count));
    }

    public static void main(String[] args) {
        StartingWindow startingWindow = new StartingWindow(null);
    }
}
