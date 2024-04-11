import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StartingWindow extends JFrame {
    public StartingWindow() {

        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource("/car-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("Parking Test Simulation");
        setIconImage(img);
        setSize(920, 665);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        BufferedImage icon = null;

        JButton programStartingButton = new JButton();
        //getting random number
        Random random = new Random();
        int n = random.nextInt(3);
        String path = "";
        try {
            if(n == 2){
                path = "res/TIPSY_DRIVING_SIM_V026.png";
            }
            else {
                path = "res/start.jpg";
            }
            BufferedImage playImage = ImageIO.read(new File(path));
            icon = ImageIO.read(new File("res/car-icon.png"));
            Image scaledImage = playImage.getScaledInstance(920, 665, Image.SCALE_SMOOTH);
            ImageIcon playIcon = new ImageIcon(scaledImage);
            programStartingButton = new JButton(playIcon);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(icon); //Icon display
        programStartingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMainApplication();
                dispose(); // Zamyka to okno po uruchomieniu
            }
        });

        add(programStartingButton);
        setVisible(true);
    }

    private void startMainApplication() {
        Main.startApplication();
    }
}