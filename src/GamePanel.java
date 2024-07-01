import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS

    private GameCompletionListener completionListener;

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
    public static int max_cars_onscreen; // indicates max number of cars drawn on the screen

    // FPS
    int FPS = 60;
    TileManager TileM = new TileManager(this);
    Thread gameThread;
    Cars cars = new Cars(this);
    CarMovement carM = new CarMovement(this);
    ParkingLights parkingLights = new ParkingLights(this);
    Music music = new Music();


    private CarsParkedCounter parkedCarsWindow;
    private Stopwatch stopwatch;

    public GamePanel(GameCompletionListener completionListener) {
        this.completionListener = completionListener;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        parkedCarsWindow = new CarsParkedCounter("Parking Status Update", this);
        stopwatch = new Stopwatch(this, parkedCarsWindow);

        music.playMusic("res/music/Tokyo Emergency.wav");

    }

    public static void setMaxCarsOnScreen(int value) {
        max_cars_onscreen = value;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                // Update position
                try {
                    update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Draw
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void update() throws InterruptedException {

        for (int i = 0; i < max_cars_onscreen + cars_parked; i++) {
            if (i >= 110) {
                continue;
            } else {
                carM.update(i);
                parkingLights.lights_update(carM.cars[i]);
            }
        }

        if (cars_parked >= 110) { //zmienic na 110!!! mozna testowac dka innej wartosci ale wtedy tez zmiana z stopwatch!
            gameThread = null; // Stop the game loop
            Thread.sleep(600);
            showCompletionWindow();
            if (completionListener != null) {
                completionListener.onGameCompleted();
            }
        }

    }

    public void showCompletionWindow() {
        String elapsedTime = stopwatch.getElapsedTime();
        new CompletionWindow(cars_parked, elapsedTime);
        parkedCarsWindow.dispose();
        stopwatch.dispose();
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


