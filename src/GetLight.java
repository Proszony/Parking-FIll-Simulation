import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetLight {
    GamePanel gp;
    public int[][] ParkingSpots;
    private int Light_x,Light_y;
    private final LightTile[] LightTiles;
    public GetLight(GamePanel gp){
        this.gp = gp;
        LightTiles = new LightTile[4];
        ParkingSpots = new int[110][2];
        GetLightIMG();
        LoadParking("mapfrfrsmol.csv");

    }
    public void LoadParking(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            int i = 0;
            while (col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while (col < gp.maxCol) {
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    if(num == 4 || num == 5){
                        ParkingSpots[i][0] = col;
                        ParkingSpots[i][1] = row;
                        i++;
                    }
                    col++;
                }
                if (col == gp.maxCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }
    private void GetLightIMG(){
        try {
            LightTiles[0] = new LightTile();
            LightTiles[0].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_left.png"));

            LightTiles[1] = new LightTile();
            LightTiles[1].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_right.png"));

            LightTiles[2] = new LightTile();
            LightTiles[2].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_left.png"));

            LightTiles[3] = new LightTile();
            LightTiles[3].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GetTile(Entity entity){
        if(entity.parking){
            for(int i = 0;i<ParkingSpots.length;i++){
                int col = ParkingSpots[i][0];
                int row = ParkingSpots[i][1];
                int tileX = col*gp.tileSize;
                int tileY = row*gp.tileSize;
                if(Math.abs(entity.x - tileX) < gp.tileSize && Math.abs(entity.y - tileY) < gp.tileSize){
                    Light_y = tileY;
                    Light_x = tileX;
                }
            }
        }
    }

    public void drawLight(Graphics2D g2) {
        if(gp.player.parking ){
            int tilenum = gp.TileM.mapTileNUM[Light_x/gp.tileSize][Light_y/gp.tileSize];
            if(tilenum == 4){
                g2.drawImage(LightTiles[3].image, Light_x, Light_y, gp.tileSize, gp.tileSize, null);
            }
            if(tilenum == 5){
                g2.drawImage(LightTiles[2].image, Light_x, Light_y, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
