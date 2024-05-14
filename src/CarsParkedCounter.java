import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarsParkedCounter extends JFrame {

    protected JLabel parkedCarsLabel;
    private JLabel free;
    private BackgroundCarsParked backgroundPanel;
    private JProgressBar progressBar;
    public int parkedCarsCount;
    public int freeSpots;

    private int parkedCarsX;
    private int parkedCarsY;
    private int freeSpotsX;
    private int freeSpotsY;

    public CarsParkedCounter(String title, GamePanel gp) {
        super(title);

        initializeBackgroundPanel();
        initializeLabels(gp);
        initializeProgressBar(gp);
        initializeFrameSettings(gp);
        startTimer(gp);
    }

    private void initializeBackgroundPanel() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("res/stopwatch/parking.png"));
            backgroundPanel = new BackgroundCarsParked(backgroundImage);
            backgroundPanel.setLayout(null); // To manually control positions
            getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeLabels(GamePanel gp) {
        parkedCarsCount = gp.cars_parked;
        freeSpots = 110 - parkedCarsCount;

        parkedCarsLabel = new JLabel("Parked Cars: " + parkedCarsCount);
        free = new JLabel("Free spots: " + freeSpots);

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 24);
        parkedCarsLabel.setFont(labelFont);
        free.setFont(labelFont);
        parkedCarsLabel.setForeground(Color.WHITE);
        free.setForeground(Color.WHITE);

        parkedCarsX = 63;
        parkedCarsY = 60;
        freeSpotsX = 63;
        freeSpotsY = parkedCarsY + 40; // Position of "free" label below "parkedCarsLabel"

        parkedCarsLabel.setBounds(parkedCarsX, parkedCarsY, 250, 30);
        free.setBounds(freeSpotsX, freeSpotsY, 250, 30);

        backgroundPanel.add(parkedCarsLabel);
        backgroundPanel.add(free);
    }

    private void initializeProgressBar(GamePanel gp) {
        progressBar = new JProgressBar(0, 110);
        progressBar.setValue(parkedCarsCount);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(115, 255, 110));
        Font font = new Font("SansSerif", Font.BOLD, 22); // Set new font for the progress bar text
        progressBar.setFont(font);

        BasicProgressBarUI ui = new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black; // string color over the background
            }
            protected Color getSelectionForeground() {
                return Color.black; // string color over the foreground
            }
        };
        progressBar.setUI(ui);

        int progressBarX = (330 - 300) / 2 - 8; // Center the progress bar, -8 for centering adjustment
        progressBar.setBounds(progressBarX, parkedCarsY + 80, 300, 50);

        backgroundPanel.add(progressBar);
    }

    private void initializeFrameSettings(GamePanel gp) {
        this.setSize(330, 250);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(30, 370);

        try {
            BufferedImage img = ImageIO.read(Main.class.getResource("/car-icon.png"));
            setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTimer(GamePanel gp) {
        Timer timer = new Timer(1000, e -> {
            parkedCarsCount = gp.cars_parked;
            freeSpots = 110 - parkedCarsCount;
            parkedCarsLabel.setText("Parked Cars: " + parkedCarsCount);
            free.setText("Free spots: " + freeSpots);
            progressBar.setValue(parkedCarsCount);
        });
        timer.start();
    }
}
