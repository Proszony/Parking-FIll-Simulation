import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends A_draw implements Collisions {

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
    private boolean no_right_truns = false;
    protected Rectangle bounding_box;
    private Color box_color = new Color(Color.green.getRGB());
    public int type;
    private boolean counted;

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

    // GET / SET box_color
    void setBox_color_green(Entity entity) {
        entity.box_color = new Color(Color.green.getRGB());
    }

    void setBox_color_red(Entity entity) {
        entity.box_color = new Color(Color.red.getRGB());
    }

    // GET / SET if able to trun right
    boolean getNo_right_truns(Entity entity){
        return entity.no_right_truns;
    }
//    void setNo_right_truns_false(Entity entity){ entity.no_right_truns = false; }
    void setNo_right_truns_true(Entity entity){
        entity.no_right_truns = true;
    }

    // GET / SET counted
    void setCounted_true(Entity entity){ entity.counted = true;}
    boolean getCounted(Entity entity){ return entity.counted;}

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

    // COLLISIONS

    @Override
    public boolean collision_check(Entity entity1, Entity entity2) {
//        switch (entity1.direction) {
//            case "up":
////                if (entity1.y - entity2.y < (3 * GamePanel.tileSize) && entity1.y - entity2.y >= 1 && Math.abs(entity1.x - entity2.x) < 5 && !entity1.parking && !entity2.parking) {
////                    entity1.speed = 1;
////                    return true;
////                }
//                if (entity1.bounding_box.intersects(entity2.bounding_box) && Math.abs(entity2.x - entity1.x) < 5 && !entity1.parking && !entity2.parking) {
//                    entity1.speed = 1;
//                    return true;
//                }
//                break;
//            case "down":
////                if (entity2.y - entity1.y < (3 * GamePanel.tileSize) && entity2.y - entity1.y >= 1 && Math.abs(entity2.x - entity1.x) < 5 && !entity1.parking && !entity2.parking) {
////                    entity1.speed =1;
////                    return true;
////                }
//                if (entity2.bounding_box.intersects(entity1.bounding_box) && Math.abs(entity2.x - entity1.x) < 5 && !entity1.parking && !entity2.parking) {
//                    entity1.speed = 1;
//                    return true;
//                }
//                break;
//            case "left":
////                if (entity1.x - entity2.x < (3 * GamePanel.tileSize) && entity1.x - entity2.x >= 1 && Math.abs(entity1.y - entity2.y) < 5 && !entity1.parking && !entity2.parking){
////                    entity1.speed = 1;
////                    return true;
////                }
//                if (entity1.bounding_box.intersects(entity2.bounding_box) && Math.abs(entity1.y - entity2.y) < 5 && !entity1.parking && !entity2.parking) {
//                    entity1.speed = 1;
//                    return true;
//                }
//                break;
//            case "right":
////                if (entity2.x - entity1.x < (3 * GamePanel.tileSize) && entity2.x - entity1.x >= 1 && Math.abs(entity2.y - entity1.y) < 5 && !entity1.parking && !entity2.parking) {
////                    entity1.speed = 1;
////                    return true;
////                }
//                if (entity2.bounding_box.intersects(entity1.bounding_box) && Math.abs(entity1.y - entity2.y) < 5 && !entity1.parking && !entity2.parking) {
//                    entity1.speed = 1;
//                    return true;
//                }
//                break;
//
//        }
        if(entity1.bounding_box.intersects(entity2.bounding_box) && ((Math.abs(entity1.y - entity2.y) < 5) || (Math.abs(entity2.x - entity1.x) < 5))){
            return true;
        } else{
            return false;
        }
    }

}// Hermetyzacje dodac
