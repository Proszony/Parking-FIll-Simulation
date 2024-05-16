import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Histogram extends JFrame {

    protected JLabel parkedCarsLabel;
    protected JLabel parkedCarsLabel1;
    private BackgroundCarsParked backgroundPanel;
    private JProgressBar[] progressBars;
    private GamePanel gamePanel;

    public Histogram(String title, GamePanel gp) {
        super(title);
        this.gamePanel = gp;
        initializeBackgroundPanel();
        initializeLabels();
        initializeProgressBars();
        initializeFrameSettings();
        startTimer();
    }

    private void initializeBackgroundPanel() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("res/math.jpg"));
            backgroundPanel = new BackgroundCarsParked(backgroundImage);
            backgroundPanel.setLayout(null); // To manually control positions
            getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeLabels() {
        parkedCarsLabel = new JLabel("CAR TYPES");

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 44);
        parkedCarsLabel.setFont(labelFont);
        parkedCarsLabel.setForeground(Color.BLACK);

        int parkedCarsX = 44;
        int parkedCarsY = 53;

        parkedCarsLabel.setBounds(parkedCarsX, parkedCarsY, 250, 50);
        backgroundPanel.add(parkedCarsLabel);

        parkedCarsLabel1 = new JLabel("CAR TYPES");
        parkedCarsLabel1.setFont(labelFont);
        parkedCarsLabel1.setForeground(Color.WHITE);

        parkedCarsLabel1.setBounds(parkedCarsX+3, parkedCarsY+3, 250, 50);
        backgroundPanel.add(parkedCarsLabel1);
    }

    private void initializeProgressBars() {
        int progressBarX = (330 - 300) / 2 + 5; // Center the progress bar, +5 for centering adjustment
        int[] progressBarYs = {140, 210, 280, 350, 420, 490, 560, 630, 700, 770, 840};
        progressBars = new JProgressBar[11];

        Font font = new Font("SansSerif", Font.BOLD, 22); // Set new font for the progress bar text
        BasicProgressBarUI ui = new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black; // string color over the background
            }
            protected Color getSelectionForeground() {
                return Color.black; // string color over the foreground
            }
        };

        for (int i = 0; i < progressBars.length; i++) {
            progressBars[i] = createProgressBar(0, progressBarX, progressBarYs[i], ui, font);
            backgroundPanel.add(progressBars[i]);
        }
    }

    private JProgressBar createProgressBar(int value, int x, int y, BasicProgressBarUI ui, Font font) {
        JProgressBar progressBar = new JProgressBar(0, 110);
        progressBar.setValue(value);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(115, 255, 110));
        progressBar.setFont(font);
        progressBar.setUI(ui);
        progressBar.setBounds(x, y, 300, 50);
        return progressBar;
    }

    private void initializeFrameSettings() {
        this.setSize(350, 940);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(1550, 20);

        try {
            BufferedImage img = ImageIO.read(Main.class.getResource("/car-icon.png"));
            setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        Timer timer = new Timer(100, e -> {
            updateProgressBars();
        });
        timer.start();
    }

    private void updateProgressBars() {
        String[] carTypes = {
                "Black Civic",
                "Green Civic",
                "Blue Civic",
                "Magenta Civic",
                "Yellow Civic",
                "Blue Jeep",
                "Green Jeep",
                "Black Jeep",
                "Red Jeep",
                "Yellow Jeep",
                "Magenta Micro"
        };

        int[] carCounts = {
                gamePanel.parked_blackCivic,
                gamePanel.parked_greenCivic,
                gamePanel.parked_blueCivic,
                gamePanel.parked_magentaCivic,
                gamePanel.parked_yellowCivic,
                gamePanel.parked_blueJeep,
                gamePanel.parked_greenJeep,
                gamePanel.parked_blackJeep,
                gamePanel.parked_redJeep,
                gamePanel.parked_yellowJeep,
                gamePanel.parked_micro,
                // Additional car counts if needed
        };

        for (int i = 0; i < carCounts.length; i++) {
            try {
                progressBars[i].setValue(carCounts[i]);

                progressBars[i].setString(carTypes[i] + ": " + carCounts[i]);

            } catch (IndexOutOfBoundsException e) {
                // Handle the exception, for example:
                System.err.println("Warning: Car type data exceeds available car types. Skipping element: " + i);
            }
        }
    }

}