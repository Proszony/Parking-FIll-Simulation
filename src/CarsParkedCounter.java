import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarsParkedCounter extends JFrame {

    private JLabel parkedCarsLabel;
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

        try {
            BufferedImage backgroundImage = ImageIO.read(new File("res/stopwatch/parking.png"));
            backgroundPanel = new BackgroundCarsParked(backgroundImage); // Utwórz niestandardowy panel tła
            backgroundPanel.setLayout(null); // Ustawienie na null, aby móc ręcznie kontrolować położenie komponentów
            getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }


        parkedCarsCount = gp.cars_parked;
        freeSpots = 110 - parkedCarsCount;

        parkedCarsLabel = new JLabel("Parked Cars: " + parkedCarsCount);
        free = new JLabel("Free spots: " + freeSpots);

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 24);
        parkedCarsLabel.setFont(labelFont);
        free.setFont(labelFont);
        parkedCarsLabel.setForeground(Color.WHITE);
        free.setForeground(Color.WHITE);

        parkedCarsX = 65;
        parkedCarsY = 55;
        freeSpotsX = 65;
        freeSpotsY = parkedCarsY + 40; // Pozycja etykiety "free" pod etykietą "parkedCarsLabel"

        parkedCarsLabel.setBounds(parkedCarsX, parkedCarsY, 250, 30);
        free.setBounds(freeSpotsX, freeSpotsY, 250, 30);

        // Dodanie paska postępu
        progressBar = new JProgressBar(0, 110);
        progressBar.setValue(parkedCarsCount);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(115, 255, 110));
        Font font = new Font("SansSerif", Font.BOLD, 22); // Ustawienie nowej czcionki dla tekstu na pasku postępu
        progressBar.setFont(font);


        int progressBarX = (330 - 300) / 2 - 8; // Obliczamy położenie X tak, aby pasek postępu był wycentrowany a te -8 centruje
        progressBar.setBounds(progressBarX, parkedCarsY + 80, 300, 50);

        // Add labels first, then progress bar
        backgroundPanel.add(parkedCarsLabel);
        backgroundPanel.add(free);
        backgroundPanel.add(progressBar);

        this.setSize(330, 250); // Zwiększenie wysokości okna aby zmieścić etykietę "free" i pasek postępu
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(30, 370);

        Timer timer = new Timer(0, e -> {
            parkedCarsCount = gp.cars_parked;
            freeSpots = 110 - parkedCarsCount;
            parkedCarsLabel.setText("Parked Cars: " + parkedCarsCount);
            free.setText("Free spots: " + freeSpots);
            progressBar.setValue(parkedCarsCount);
        });
        timer.start();

        BufferedImage img;
        try {
            img = ImageIO.read(Main.class.getResource("/car-icon.png"));
            setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
