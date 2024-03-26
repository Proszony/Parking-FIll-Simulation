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

    // FPS
    int FPS = 60;
    TileManager TileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public parkingCheck parkingCheck = new parkingCheck(this);
    Player player = new Player(this, keyH);
    GetLight getLight = new GetLight(this);
    Cars cars = new Cars(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
            }
        }
    }

    public void update() {
        player.update();
        getLight.GetTile(player);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        TileM.draw(g2);
        player.draw(g2);
        for(int i = 0; i < 5; i++){
            cars.draw(g2,i);
        }
        getLight.drawLight(g2);
        g2.dispose();
    }
}
