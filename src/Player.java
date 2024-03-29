import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    Graphics2D g2;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDeafultValues(-20, 0, 5, "right");
        getPImage();
    }

    public void setDeafultValues(int xi, int yi, int speedi, String directioni) {
        x = xi; //0;
        y = yi; //20 + 48*(0); // 24 - 4 -> ruch poziomy
        speed = speedi; //5;
        direction = directioni; //"right";
    }

    public void getPImage() { // RYSOWAC OBRAZ NA WSPOLRZEDNYCH x-24, y-24 !!!!!!!!!!!!!!!  // getClass().getResourceAsStream
        try {
            up = ImageIO.read(new File("res/Cars/JEEP/BLUE_JEEP/SEPARATEDN/Blue_JEEP_CLEAN_NORTH_000.png"));
            down = ImageIO.read(new File("res/Cars/JEEP/BLUE_JEEP/SEPARATEDS/Blue_JEEP_CLEAN_SOUTH_000.png"));
            left = ImageIO.read(new File("res/Cars/JEEP/BLUE_JEEP/SEPARATEDW/Blue_JEEP_CLEAN_WEST_000.png"));
            right = ImageIO.read(new File("res/Cars/JEEP/BLUE_JEEP/SEPARATEDE/Blue_JEEP_CLEAN_EAST_000.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void border() {
        if (y < -2) {
            y = -2;
        }
        if (y > 666) {
            y = 666;
        }
        if( x >= 1110){
            x = 1105;
        }
        if(x <= -15){
            x = -10;
        }
    }

    //60 rzedow na mapie (pierwszy = -2)
    public void update() {
        solidArea = new Rectangle(x+18, y+18, 8, 8);
//        System.out.println("x = " + x + ", y = " + y);
        if (keyH.upPress) {
            direction = "up";
        } else if (keyH.downPress) {
            direction = "down";
        } else if (keyH.leftPress) {
            direction = "left";
        } else if (keyH.rightPress) {
            direction = "right";
        } else if (keyH.Lpress) {
            leave_parkingspot = true;
        } else if (keyH.Ppress) {
            leave_parkingspot = false;
        }
        parking = false;
        gp.parkingCheck.checkPark(this);
        if (keyH.leftPress || keyH.rightPress || keyH.upPress || keyH.downPress) {
            if (parking == false) {
                switch (direction) {
                    case "up":
                        border();
                        y -= speed;
                        break;
                    case "down":
                        border();
                        y += speed;
                        break;
                    case "left":
                        border();
                        x -= speed;
                        break;
                    case "right":
                        border();
                        x += speed;
                        break;
                }
            }
            if (parking && !leave_parkingspot) {
                switch (direction) {
                    case "left":
                        direction = "right";
                        break;
                    case "right":
                        direction = "left";
                        break;
                }
            }
        }
    }
    public void solid(Graphics2D g2){
        g2.draw(solidArea);
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
        g2.drawImage(image, x - 24, y - 24, gp.PlayerSize * 2, gp.PlayerSize * 2, null);
    }
}
