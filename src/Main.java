import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartingWindow(); // Wyświetla te takie pierwsze startowe
            }
        });
    }

    public static void startApplication() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource("/car-icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stopwatch stopwatch = new Stopwatch(); //okienko stopera
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("Parking Test Simulation");
 
        window.setIconImage(img);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setLocation(300, 0);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
