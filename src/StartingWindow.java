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

        // Panel dla przycisków plus i minus oraz licznika
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setOpaque(true);
        controlPanel.setBackground(new Color(255, 229, 204)); // Background color for the control panel
        controlPanel.setBounds(600, 350, 230, 180); // Setting position and size

        // Creating rounded buttons
        RoundedButton plusButton = new RoundedButton("ADD A CAR");
        plusButton.setBounds(2, 1, 226, 58); // Setting position and size within control panel
        plusButton.setFont(new Font("Comic Sans", Font.BOLD, 18)); // Zwiększenie rozmiaru czcionki
        plusButton.setBackground(new Color(255, 160, 140)); // Button background color
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

        RoundedButton minusButton = new RoundedButton("REMOVE A CAR");
        minusButton.setBounds(2, 121, 226, 58); // Setting position and size within control panel
        minusButton.setFont(new Font("Comic Sans", Font.BOLD, 18)); // Zwiększenie rozmiaru czcionki
        minusButton.setBackground(new Color(255, 160, 140)); // Button background color
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
        countLabel = new JLabel("CARS: " + count, SwingConstants.CENTER);
        countLabel.setFont(new Font("Comic Sans", Font.BOLD, 18)); // Zmniejszenie rozmiaru czcionki
        countLabel.setOpaque(true);
        countLabel.setBackground(new Color(255, 229, 204)); // Background color for the label
        countLabel.setBounds(0, 60, 230, 60); // Setting position and size within control panel

        // Adding components to the control panel
        controlPanel.add(plusButton);
        controlPanel.add(minusButton);
        controlPanel.add(countLabel);

        // Panel dla przycisku start
        RoundedButton startButton = new RoundedButton("START");
        startButton.setFont(new Font("Comic Sans", Font.BOLD, 24)); // Zwiększenie rozmiaru czcionki
        startButton.setBackground(new Color(255, 229, 204)); // Kolory tła w postaci pastelowego różowego
        startButton.setFocusPainted(false); // Remove the focus border
        startButton.setBounds(150, 340, 300, 90); // Centering the start button

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

        // Adding components to the background
        background.add(controlPanel);
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
        countLabel.setText("CARS: " + count);
    }

    public static void main(String[] args) {
        StartingWindow startingWindow = new StartingWindow(null);
    }
}

// Custom rounded button class
class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setMargin(new Insets(10, 10, 10, 10)); // Add padding to avoid content cutting off
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        // No content area to fill
    }
}
