import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartingWindow extends JFrame {
    public StartingWindow() {
        setTitle("Parking Test Simulation");
        setSize(920, 665);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton programStartingButton = new JButton();

        try {
            BufferedImage playImage = ImageIO.read(new File("res/start.jpg"));
            Image scaledImage = playImage.getScaledInstance(920, 665, Image.SCALE_SMOOTH);
            ImageIcon playIcon = new ImageIcon(scaledImage);
            programStartingButton = new JButton(playIcon);
            //programStartingButton.setBounds(200, 180, 224, 50);
           // programStartingButton.setFocusable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        programStartingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMainApplication();
                dispose(); // Zamyka to oknopo uruchomieniu
            }
        });

        add(programStartingButton);
        setVisible(true);
    }

    private void startMainApplication() {
        Main.startApplication();
    }
}