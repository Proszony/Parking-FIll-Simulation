import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CompletionWindow extends JFrame {
    public CompletionWindow(int parkedCars, String elapsedTime) {
        setTitle("Parking Summary");
        setSize(440, 275);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            BufferedImage img = ImageIO.read(Main.class.getResource("/car-icon.png"));
            setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel background = new JLabel(new ImageIcon("res/math.jpg"));
        background.setLayout(null);


        JLabel completionLabel = new JLabel("ALL CARS PARKED!!", SwingConstants.CENTER);
        completionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        completionLabel.setForeground(Color.WHITE); // Set text color to white for better visibility
        completionLabel.setBounds(64, 70, 300, 30); // Set position and size of the label

        JLabel detailsLabel = new JLabel("Total parked cars: " + parkedCars + " in time: " + elapsedTime, SwingConstants.CENTER);
        detailsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        detailsLabel.setForeground(Color.WHITE); // Set text color to white for better visibility
        detailsLabel.setBounds(20, 120, 380, 30); // Set position and size of the label

        completionLabel.setForeground(Color.BLACK);
        detailsLabel.setForeground(Color.BLACK);

        background.add(completionLabel);
        background.add(detailsLabel);

        add(background);

        setVisible(true);
    }
}