import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundCarsParked extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundCarsParked(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}