import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        //x+48,y+48,16,16
        solidArea = new Rectangle(24,24,8,8);
        setDeafultValues(-24,20,5,"right");
        getPImage();
    }

    public void setDeafultValues(int xi, int yi, int speedi, String directioni) {
        x = xi; //-24;
        y = yi; //20 + 48*(0); // 24 - 4 -> ruch poziomy
        speed = speedi; //5;
        direction = directioni; //"right";
    }


    public void getPImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/BLACK_CIVIC/SEPARATEDN/Black_CIVIC_CLEAN_NORTH_000.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/BLACK_CIVIC/SEPARATEDS/Black_CIVIC_CLEAN_SOUTH_000.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/BLACK_CIVIC/SEPARATEDW/Black_CIVIC_CLEAN_WEST_000.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/BLACK_CIVIC/SEPARATEDE/Black_CIVIC_CLEAN_EAST_000.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//60 rzedow na mapie (pierwszy = -2)
    public void update() {
//        System.out.println("x = " + x + ", y = " + y);
        if (keyH.upPress) {
            direction = "up";
        } else if (keyH.downPress) {
            direction = "down";
        } else if (keyH.leftPress) {
            direction = "left";
        } else if (keyH.rightPress) {
            direction = "right";
        }else if (keyH.Lpress){
            leave_parkingspot = true;
        }else if (keyH.Ppress){
            leave_parkingspot = false;
        }
        parking = false;
        gp.parkingCheck.checkTile(this);
        if(keyH.leftPress || keyH.rightPress || keyH.upPress || keyH.downPress){
        if(parking == false){
            switch (direction){
                case "up":
                    if(y < -24){y = -24;}
                    y -= speed;
                    break;
                case "down":
                    if(y > 938){y = 938;}
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }
        if(parking && !leave_parkingspot){
            switch (direction){
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
        g2.drawImage(image, x, y, gp.PlayerSize*2, gp.PlayerSize*2, null);
    }
}
