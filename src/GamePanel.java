import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    static final int defultTileSize = 16; // 16x16 tile
    static final int scale = 3;
    final int p_scale = 3;
    public static final int tileSize = defultTileSize * scale; // zrobic static czy w klasie entity zrobic Gamepanel gp; i dac super reszcie dziedziczacych klas
    public final int PlayerSize = defultTileSize * p_scale;
    public final int maxCol = 24; //32
    public final int maxRow = 15; //21
    public final int screenHeight = maxRow * tileSize;
    public final int screenWidth = maxCol * tileSize;
    public int cars_parked = 0; // indicates how many cars have parked
    public int parked_blackCivic = 0;
    public int parked_greenCivic = 0;
    public int parked_blueCivic = 0;
    public int parked_magentaCivic = 0;
    public int parked_yellowCivic = 0;
    public int parked_blueJeep = 0;
    public int parked_greenJeep = 0;
    public int parked_blackJeep = 0;
    public int parked_redJeep = 0;
    public int parked_yellowJeep = 0;
    public int parked_micro = 0;
    public static int max_cars_onscreen; // indicates max number of cars drawn on the screan

    // FPS
    int FPS = 60;
    TileManager TileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public parkingCheck parkingCheck = new parkingCheck(this);
    Player player = new Player(this, keyH);
    GetLight getLight = new GetLight(this);
    Cars cars = new Cars(this);
    CarMovement carM = new CarMovement(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        CarsParkedCounter parkedCarsWindow = new CarsParkedCounter("Parking Status Update", this); // DODAJ FUNKCJE NIE WSZYSTKO W KOSTRUKTORZE
        Stopwatch stopwatch = new Stopwatch(this);
        Histogram histogram = new Histogram("Types of cars", this);
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
        //player.update();
//        parked_blackCivic = 0;
//        parked_greenCivic = 0;
//        parked_blueCivic = 0;
//        parked_magentaCivic = 0;
//        parked_yellowCivic = 0;
//        parked_blueJeep = 0;
//        parked_greenJeep = 0;
//        parked_blackJeep = 0;
//        parked_redJeep = 0;
//        parked_yellowJeep = 0;
//        parked_micro = 0;

        for (int i = 0; i < max_cars_onscreen + cars_parked; i++) {
            if (i >= 110) {
                continue;
            } else {
                carM.update(i);
//                getLight.GetTile(cars.cars[i]);
                if(!cars.getCounted(cars.cars[i])){  // PARKING CHECKKKKK NAPRAWWWWW!!!!!!!!!!!!!!!!!!!!!!
                    switch (cars.cars[i].type) {
                        case 0: parked_blackCivic++; cars.setCounted_true(cars.cars[i]); break;
                        case 1: parked_greenCivic++; cars.setCounted_true(cars.cars[i]); break;
                        case 2: parked_blueCivic++; cars.setCounted_true(cars.cars[i]); break;
                        case 3: parked_magentaCivic++; cars.setCounted_true(cars.cars[i]); break;
                        case 4: parked_yellowCivic++; cars.setCounted_true(cars.cars[i]); break;
                        case 5: parked_blueJeep++; cars.setCounted_true(cars.cars[i]); break;
                        case 6: parked_greenJeep++; cars.setCounted_true(cars.cars[i]); break;
                        case 7: parked_blackJeep++; cars.setCounted_true(cars.cars[i]); break;
                        case 8: parked_redJeep++; cars.setCounted_true(cars.cars[i]); break;
                        case 9: parked_yellowJeep++; cars.setCounted_true(cars.cars[i]); break;
                        case 10: parked_micro++; cars.setCounted_true(cars.cars[i]); break;
                    }
                }
            }
        }
        //getLight.GetTile(player);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        TileM.draw(g2);
        //  player.draw(g2, player, this);
        for(int i = 0; i < max_cars_onscreen + cars_parked; i++){
            if(i >= 110){
                continue;
            }else {
                //carM.draw(g2,i);
                cars.draw(g2, carM.cars[i], this);
            }
        }
        getLight.drawLight(g2);
        g2.dispose();
    }


}