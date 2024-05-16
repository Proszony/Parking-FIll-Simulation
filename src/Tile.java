import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends Draw_tile {
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
    void draw(Graphics2D g2, GamePanel gp, int[][] tile_pos, Tile[] tiles) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxCol && row < gp.maxRow) {
            int tileNUM = tile_pos[col][row];
            g2.drawImage(tiles[tileNUM].image, x, y, gp.tileSize, gp.tileSize, null);
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
    void draw_light(Graphics2D g2, GamePanel gp, int[][] tile_pos, Tile[] tiles) {
        for(int i = 0; i < 110; i++){
            int col = tile_pos[i][0];
            int row = tile_pos[i][1];
            int tileNUM = tile_pos[i][3];
            if(tile_pos[i][2] == 1){
                if(tileNUM == 5){ // 0 2
                    g2.drawImage(tiles[2].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNUM == 4){ // 1 3
                    g2.drawImage(tiles[3].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            } else {
                if(tileNUM == 5){ // 0 2
                    g2.drawImage(tiles[0].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                if(tileNUM == 4){ // 1 3
                    g2.drawImage(tiles[1].image, col*gp.tileSize, row*gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
