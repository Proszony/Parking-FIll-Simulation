import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame();
    JButton startButton = new JButton();
    JButton stopButton = new JButton();
    JButton resetButton = new JButton();
    JLabel timeLabel = new JLabel();
    JLabel timeLabel2 = new JLabel();


    int elapsedTime = 0;
    int seconds_100 = 0;
    int seconds1 = 0;
    int minutes1 = 0;
    boolean started = false;
    boolean stopped = true;
    String seconds_100_string = String.format("%02d", seconds_100);
    String seconds_string = String.format("%02d", seconds1);
    String minutes_string = String.format("%02d", minutes1);

    Timer timer = new Timer(10, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime + 10;
            minutes1 = (elapsedTime / 60000) % 60;
            seconds1 = (elapsedTime / 1000) % 60;
            seconds_100 = (elapsedTime / 10) % 100;
            seconds_100_string = String.format("%02d", seconds_100);
            seconds_string = String.format("%02d", seconds1);
            minutes_string = String.format("%02d", minutes1);
            timeLabel.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
            timeLabel2.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);

            if (gamePanel.cars_parked == 110) {
                stopped = true;
                started = false;
                stop();
            }

        }
    });

    private GamePanel gamePanel;

    public Stopwatch(GamePanel gamePanel) {
        this.gamePanel=gamePanel;

        timeLabel.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
        timeLabel.setBounds(-25, -31, 270, 270); //centrowanie wyswietlacza stopera
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(false);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        timeLabel2.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
        timeLabel2.setBounds(-22,-28, 270, 270); //jak wyzej ^
        timeLabel2.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel2.setForeground(Color.WHITE);
        timeLabel2.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel2.setOpaque(false);
        timeLabel2.setHorizontalAlignment(JTextField.CENTER);

//        try {
//            BufferedImage playImage = ImageIO.read(new File("res/stopwatch/play_button.jpg"));
//            Image scaledImage = playImage.getScaledInstance(112, 68, Image.SCALE_SMOOTH);
//            ImageIcon playIcon = new ImageIcon(scaledImage);
//            startButton = new JButton(playIcon);
//            startButton.setBounds(0, 112, 112, 68);
//            startButton.setFocusable(false);
//            startButton.addActionListener(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            BufferedImage playImage = ImageIO.read(new File("res/stopwatch/stop_button.jpg"));
//            Image scaledImage2 = playImage.getScaledInstance(115, 68, Image.SCALE_SMOOTH);
//            ImageIcon playIcon2 = new ImageIcon(scaledImage2);
//            stopButton = new JButton(playIcon2);
//            stopButton.setBounds(112, 112, 115, 68);
//            stopButton.setFocusable(false);
//            stopButton.addActionListener(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            BufferedImage playImage3 = ImageIO.read(new File("res/stopwatch/reset_button.jpg"));
//            Image scaledImage3 = playImage3.getScaledInstance(225, 50, Image.SCALE_SMOOTH);
//            ImageIcon playIcon3 = new ImageIcon(scaledImage3);
//            resetButton = new JButton(playIcon3);
//            resetButton.setBounds(0, 180, 224, 50);
//            resetButton.setFocusable(false);
//            resetButton.addActionListener(this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        frame.add(startButton);
//        frame.add(stopButton);
//        frame.add(resetButton);

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
//        frame.add(startButton);
//        frame.add(stopButton);
//        frame.add(resetButton);
        frame.add(timeLabel);
        frame.add(timeLabel2);
        start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            if (started == false) {
                started = true;
                stopped = false;
                start();
            }
        }}
//        if (e.getSource() == resetButton) {
//            started = false;
//            reset();
//        }
//        if (e.getSource() == stopButton) {
//            stopped = true;
//            started = false;
//            stop();
//        }
//
//    }
//
    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

//    void reset() {
//        timer.stop();
//        elapsedTime = 0;
//        seconds_100 = 0;
//        seconds1 = 0;
//        minutes1 = 0;
//        seconds_100_string = String.format("%02d", seconds_100);
//        seconds_string = String.format("%02d", seconds1);
//        minutes_string = String.format("%02d", minutes1);
//        timeLabel.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
//        timeLabel2.setText(minutes_string + ":" + seconds_string + ":" + seconds_100_string);
//    }
}


