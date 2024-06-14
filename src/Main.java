import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    private static JFrame window;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartingWindow startingWindow = new StartingWindow(new StartListener() {
                    @Override
                    public void onStart() {
                        startApplication();
                    }
                });
                startingWindow.setVisible(true); // Display the starting window
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

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Parking Test Simulation");
        window.setIconImage(img);

        GameCompletionListener completionListener = new GameCompletionListener() {
            @Override
            public void onGameCompleted() {
                window.dispose();
            }
        };

        GamePanel gamePanel = new GamePanel(completionListener);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setLocation(365, 125);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
