import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x = 0, y = 0;
    public int speed;
    public BufferedImage up, down, left, right;
    public String direction;
    public Rectangle solidArea;
    public boolean parking = false;
    public boolean leave_parkingspot = false;
    public int chose_turnLRS = 1;
    public int chose_turnLR = 0;
    public boolean turned = false;
}
