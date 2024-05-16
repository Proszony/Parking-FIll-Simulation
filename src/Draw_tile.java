import java.awt.*;

abstract class Draw_tile {
    abstract void draw(Graphics2D g2, GamePanel gp, int[][] tile_pos, Tile[] tiles);
    abstract void draw_light(Graphics2D g2, GamePanel gp, int[][] tile_pos, Tile[] tiles);

}
