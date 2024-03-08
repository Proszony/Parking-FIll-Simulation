import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource("car-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GIGA NIGGA");
        window.setIconImage(img);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JFrame Second_window = new JFrame();
        Second_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Second_window.setResizable(false);
        Second_window.setTitle("Sub_WIN");
        Second_window.setIconImage(img);
        Second_window.setLocationRelativeTo(null);
        Second_window.setLocation(30,30);
        Second_window.setVisible(true);
        Second_window.setSize(250,700);

        gamePanel.startGameThread();

    }
}