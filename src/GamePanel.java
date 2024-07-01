import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int defultTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int p_scale = 3;
    public final int tileSize = defultTileSize * scale;
    public final int PlayerSize = defultTileSize * p_scale;
    public final int maxCol = 24; //32
    public final int maxRow = 15; //21
    public final int screenHeight = maxRow * tileSize;
    public final int screenWidth = maxCol * tileSize;
    public int cars_parked = 0; // indicates how many cars have parked
    public final int max_cars_onscreen = 5; // indicates max number of cars drawn on the screan

    // FPS
    int FPS = 60;
    TileManager TileM = new TileManager(this);
    Thread gameThread;
    Cars cars = new Cars(this);
    CarMovement carM = new CarMovement(this);
    ParkingLights parkingLights = new ParkingLights(this);
    Music music = new Music();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        CarsParkedCounter parkedCarsWindow = new CarsParkedCounter("Parking Status Update", this);
        Stopwatch stopwatch = new Stopwatch(this);
        music.playMusic("res/music/Tokyo Emergency.wav");
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterwal = 1000000000 / FPS;
        double delta = 0;
        long LastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - LastTime) / drawInterwal;
            timer += (currentTime - LastTime);
            LastTime = currentTime;
            if (delta >= 1) {
                // Update position
                update();
                // Draw
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            } // Sleep ustawic
            // uniezależnic predkosc od fps (tylko od czasu)
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        for (int i = 0; i < max_cars_onscreen + cars_parked; i++) {
            if (i >= 110) {
                continue;
            } else {
                carM.update(i);
                parkingLights.lights_update(carM.cars[i]);

            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        TileM.draw(g2, this);
        cars.draw(g2, this);
        TileM.draw_light(g2, this);
        g2.dispose();
    }
}