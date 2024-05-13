import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends A_draw {

    protected int x = 0, y = 0;
    protected int speed;
    protected BufferedImage up, down, left, right;
    protected String direction;
    protected Rectangle solidArea;
    protected boolean parking = false;
    private boolean leave_parkingspot = false;
    protected int chose_turnLRS = 1;
    protected int chose_turnLR = 1;
    protected boolean chose_turn = false;
    protected boolean turned = false;
    protected Rectangle bounding_box;
    private Color box_color = new Color(Color.green.getRGB());

    // GET / SET leave_parkingstop
    boolean get_leaveparkingstop(Entity entity) {
        return entity.leave_parkingspot;
    }

    void set_leaveparkingstop_ture(Entity entity) {
        entity.leave_parkingspot = true;
    }

    void set_leaveparkingstop_false(Entity entity) {
        entity.leave_parkingspot = false;
    }

    // GET / SET
    void setBox_color_green(Entity entity){entity.box_color = new Color(Color.green.getRGB());}
    void setBox_color_red(Entity entity){entity.box_color = new Color(Color.red.getRGB());}

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
        g2.setColor(entity.box_color);
        if (entity.bounding_box != null) {
            g2.draw(entity.bounding_box);
        }
    }
}// Hermetyzacje dodac
