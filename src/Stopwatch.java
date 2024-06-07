import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Stopwatch {
    private JFrame frame;
    private JLabel timeLabel;
    private JLabel timeLabel2;

    private int elapsedTime;
    private int seconds_100;
    private int seconds1;
    private int minutes1;
    private String seconds_100_string;
    private String seconds_string;
    private String minutes_string;

    private Timer timer;
    private GamePanel gamePanel;
    private CarsParkedCounter carsParkedCounter;

    public Stopwatch(GamePanel gamePanel, CarsParkedCounter carsParkedCounter) {
        this.gamePanel = gamePanel;
        this.carsParkedCounter = carsParkedCounter;
        initializeFields();
        initializeLabels();
        initializeFrame();
        initializeTimer();
        start();
    }

    private void initializeFields() {
        frame = new JFrame();
        timeLabel = new JLabel();
        timeLabel2 = new JLabel();
        elapsedTime = 0;
        seconds_100 = 0;
        seconds1 = 0;
        minutes1 = 0;
        seconds_100_string = String.format("%02d", seconds_100);
        seconds_string = String.format("%02d", seconds1);
        minutes_string = String.format("%02d", minutes1);
    }

    private void initializeLabels() {
        timeLabel.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
        timeLabel.setBounds(-25, -31, 270, 270); // Centering the stopwatch display
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(false);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        timeLabel2.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
        timeLabel2.setBounds(-22, -28, 270, 270); // Same as above
        timeLabel2.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel2.setForeground(Color.WHITE);
        timeLabel2.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel2.setOpaque(false);
        timeLabel2.setHorizontalAlignment(JTextField.CENTER);
    }

    private void initializeFrame() {
        BufferedImage background = null;
        try {
            background = ImageIO.read(Main.class.getResource("car-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(240, 270);
        frame.setLayout(null);
        frame.setIconImage(background);
        frame.setResizable(false);
        frame.setTitle("Parking Test Simulation Timer");
        frame.setLocationRelativeTo(null);
        frame.setLocation(65, 50);
        frame.setVisible(true);

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/stopwatch/stopwatch_background.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(timeLabel);
        frame.add(timeLabel2);
    }

    private void initializeTimer() {
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 10;
                minutes1 = (elapsedTime / 60000) % 60;
                seconds1 = (elapsedTime / 1000) % 60;
                seconds_100 = (elapsedTime / 10) % 100;
                seconds_100_string = String.format("%02d", seconds_100);
                seconds_string = String.format("%02d", seconds1);
                minutes_string = String.format("%02d", minutes1);
                timeLabel.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
                timeLabel2.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);

                if (gamePanel.cars_parked == 110) { //zmienic na 110! mozna testowac dka innej wartosci ale wtedy tez zmiana z GamePanel!
                    stop();

                }
            }
        });
    }

    private void start() {
        timer.start();
    }

    private void stop() {
        timer.stop();
    }

    public String getElapsedTime() {
        return minutes_string + ":" + seconds_string + ":" + seconds_100_string;
    }

    public void dispose() {
        frame.dispose();
    }
}
