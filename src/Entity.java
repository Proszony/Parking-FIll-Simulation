import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    protected int x = 0, y = 0;
    protected int speed;
    protected BufferedImage up, down, left, right;
    protected String direction;
    protected Rectangle solidArea;
    protected boolean parking = false;
    protected boolean leave_parkingspot = false;
    protected int chose_turnLRS = 1;
    protected int chose_turnLR = 0;
    protected boolean turned = false;
}
