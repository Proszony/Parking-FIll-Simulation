import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity extends Drawable implements Collisions, ParkingCheck {
    GamePanel gp;
    protected int x = 0, y = 0;
    protected int speed;
    protected BufferedImage up, down, left, right;
    protected String direction;
    protected Rectangle solidArea;
    protected boolean parking = false;
    private boolean leave_parkingspot = false;
    protected int chose_turnLRS = 1;
    protected int chose_turnLR = 1;
    private boolean chose_turn = false;
    private boolean turned = false;
    private boolean no_right_truns = false;
    private int type;
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

    // GET / SET box_color
    void setBox_color_green(Entity entity) {
        entity.box_color = new Color(Color.green.getRGB());
    }

    void setBox_color_red(Entity entity) {
        entity.box_color = new Color(Color.red.getRGB());
    }

    // GET / SET if able to trun right
    boolean getNo_right_truns(Entity entity) {
        return entity.no_right_truns;
    }

    void setNo_right_truns_false(Entity entity) {
        entity.no_right_truns = false;
    }

    void setNo_right_truns_true(Entity entity) {
        entity.no_right_truns = true;
    }

    // GET / SET type
    void setType(Entity entity, int type) {
        if (type >= 0 && type <= 11) {
            entity.type = type;
        } else {
            entity.type = -1;
        }
    }

    // GET / SET Chose_turn
    void setChose_turn_true(Entity entity) {
        entity.chose_turn = true;
    }

    void setChose_turn_false(Entity entity) {
        entity.chose_turn = false;
    }

    boolean getChose_turn(Entity entity) {
        return entity.chose_turn;
    }

    // GET / SET turned
    void setTurned_true(Entity entity) {
        entity.turned = true;
    }

    void setTurned_false(Entity entity) {
        entity.turned = false;
    }

    boolean getTurned(Entity entity) {
        return entity.turned;
    }

    // DRAWING
    @Override
    void draw(Graphics2D g2, GamePanel gp) {
        for (int i = 0; i < gp.cars_parked + gp.max_cars_onscreen; i++) {
            if(i < 110){
                BufferedImage img = switch (gp.carM.cars[i].direction) {
                    case "up" -> gp.carM.cars[i].up;
                    case "down" -> gp.carM.cars[i].down;
                    case "left" -> gp.carM.cars[i].left;
                    case "right" -> gp.carM.cars[i].right;
                    default -> null;
                };
                g2.drawImage(img, gp.carM.cars[i].x - 24, gp.carM.cars[i].y - 24, gp.PlayerSize * 2, gp.PlayerSize * 2, null);
                g2.setColor(gp.carM.cars[i].box_color);
                if (gp.carM.cars[i].bounding_box != null) {
                    g2.draw(gp.carM.cars[i].bounding_box);
                }
            }
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
        if (entity1.bounding_box.intersects(entity2.bounding_box) && ((Math.abs(entity1.y - entity2.y) < 5) || (Math.abs(entity2.x - entity1.x) < 5))) {
            return true;
        } else {
            return false;
        }
    }

    // Parking Checks
    @Override
    public void checkPark(Entity entity, GamePanel gp) {
        int entitySolidX = entity.x + entity.solidArea.width + 5;
        int entitySolidY = entity.y + entity.solidArea.height + 5;
        int col = entitySolidX / gp.tileSize;
        int row = entitySolidY / gp.tileSize;
//        System.out.println("col = " + col + " row = " + row + " tile = " + gp.TileM.mapTileNUM[col][row]);
        if (!entity.get_leaveparkingstop(entity)) {
            switch (entity.direction) {
                case "left":
                    col = (entitySolidX + 30) / gp.tileSize;
                    row = entitySolidY / gp.tileSize;
                    if (gp.TileM.mapTileNUM[col][row] == 5 || gp.TileM.mapTileNUM[col][row] == 4) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                    }
                    break;
                case "right":
                    col = (entitySolidX - 8) / gp.tileSize;
                    row = entitySolidY / gp.tileSize;
                    if (gp.TileM.mapTileNUM[col][row] == 4 || gp.TileM.mapTileNUM[col][row] == 5) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                    }
                    break;
                case "up":
                    if (gp.TileM.mapTileNUM[col][row] == 4 || gp.TileM.mapTileNUM[col][row] == 5) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                    }
                    break;
                case "down":
                    col = (entitySolidX) / gp.tileSize;
                    row = (entitySolidY + 30) / gp.tileSize;
                    if (gp.TileM.mapTileNUM[col][row] == 4 || gp.TileM.mapTileNUM[col][row] == 5) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                    }
                    break;
            }
        }
    }

}
