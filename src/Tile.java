import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends Drawable implements Draw_tile {
    protected BufferedImage image;
    private boolean parkingspot = false;

    void setParkingspot_ture(Tile tile) {
        tile.parkingspot = true;
    }

    void setParkingspot_false(Tile tile) {
        tile.parkingspot = false;
    }
    boolean getParkingspot(Tile tile){
        return tile.parkingspot;
    }

    @Override
    void draw(Graphics2D g2, GamePanel gp) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            int tileNUM = gp.TileM.mapTileNUM[col][row];
            g2.drawImage(gp.TileM.tile[tileNUM].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    @Override
    public void draw_light(Graphics2D g2, GamePanel gp) {
        for(int i = 0; i < 110; i++){
            int col = gp.parkingLights.parkingspot_position[i][0];
            int row = gp.parkingLights.parkingspot_position[i][1];
            int tileNUM = gp.parkingLights.parkingspot_position[i][3];
            if(gp.parkingLights.parkingspot_position[i][2] == 1){
                if(tileNUM == 5){ // 0 2
                    g2.drawImage(gp.parkingLights.lights[2].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNUM == 4){ // 1 3
                    g2.drawImage(gp.parkingLights.lights[3].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            } else {
                if(tileNUM == 5){ // 0 2
                    g2.drawImage(gp.parkingLights.lights[0].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNUM == 4){ // 1 3
                    g2.drawImage(gp.parkingLights.lights[1].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
