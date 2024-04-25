import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends A_draw {

    public int x = 0, y = 0;
    public int speed;
    public BufferedImage up, down, left, right;
    public String direction;
    public Rectangle solidArea;
    public boolean parking = false;
    public boolean leave_parkingspot = false;
    public int chose_turnLRS = 1;
    public int chose_turnLR = 1;
    public boolean turned = false;
    
    @Override
    void draw(Graphics2D g2, Entity entity, GamePanel gp) {
        BufferedImage img = switch (entity.direction) {
            case "up" -> entity.up;
            case "down" -> entity.down;
            case "left" -> entity.left;
            case "right" -> entity.right;
            default -> null;
        };
        g2.drawImage(img, entity.x - 24, entity.y - 24, gp.PlayerSize * 2, gp.PlayerSize * 2, null);   
    }
}// Hermetyzacje dodac
