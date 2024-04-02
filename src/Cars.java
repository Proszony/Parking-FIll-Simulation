import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.util.List;

public class Cars extends Entity {
    GamePanel gp;
    public Entity[] cars;
    public Random random = new Random();
    public int[][] entry = new int[5][2];
    public int last_entry = 0;

    public Cars(GamePanel gp) {
        this.gp = gp;
        cars = new Entity[110];
        for (int i = 0; i < 110; i++) {
            getStartPos(i);
        }
    }


    public void getStartPos(int i) {

        String dir;

        //    entry 1 (left up) (x,y) = (-15,38) // x - 20
        entry[0][0] = 0;
        entry[0][1] = 38;
        //    entry 2 (right up) (x,y) = (1110,-2) // x + 20
        entry[1][0] = 1100;
        entry[1][1] = -2;
        //    entry 3 (right down) (x,y) = (1110,618) // x + 20
        entry[2][0] = 1100;
        entry[2][1] = 618;
        //    entry 4 (left down) (x,y) = (-15, 663) // x - 20
        entry[3][0] = 0;
        entry[3][1] = 663;
        //    entry 5 (left middle) (x,y) = (-15, 373) // x - 20
        entry[4][0] = 0;
        entry[4][1] = 373;
        int start_entry = random.nextInt(5);
        while (start_entry == last_entry){
            start_entry = random.nextInt(5);
        }
        last_entry = start_entry;
        if (start_entry == 2 || start_entry == 1) {
            dir = "left";
        } else {
            dir = "right";
        }
//        System.out.println(Arrays.toString(entry[start_entry]));
        setDeafultValues(i, entry[start_entry][0], entry[start_entry][1], 5, dir);
        setDeafultValues(0, entry[0][0] + 970 -(8*48), entry[0][1] - 40 + (7*48), 5, "left");
    }

    public void setDeafultValues(int i, int xi, int yi, int speedi, String directioni) {
        cars[i] = new Entity();
        cars[i].x = xi;
        cars[i].y = yi;
        cars[i].speed = speedi;
        cars[i].direction = directioni;
        cars[i].solidArea = new Rectangle(cars[i].x + 18, cars[i].y + 18, 8, 8);
        getCarImage(i);
    }

    public void getCarImage(int i) {
        int png_num = random.nextInt(10);
        String[][] path_diretion = new String[][]{{"SEPARATEDE", "EAST"}, {"SEPARATEDN", "NORTH"}, {"SEPARATEDS", "SOUTH"}, {"SEPARATEDW", "WEST"}};
        String path_carbrand;
        String path_color;
        String path_color_lower;
        String end_path = "_000.png";
        String path = "res/Cars/";
        String[] full_path = new String[4];
        switch (png_num) {
            case 0:
                path_carbrand = "CIVIC";
                path_color_lower = "Black";
                path_color = "BLACK";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 1:
                path_carbrand = "CIVIC";
                path_color_lower = "Green";
                path_color = "GREEN";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 2:
                path_carbrand = "CIVIC";
                path_color_lower = "Blue";
                path_color = "BLUE";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 3:
                path_carbrand = "CIVIC";
                path_color_lower = "Magenta";
                path_color = "MAGENTA";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 4:
                path_carbrand = "CIVIC";
                path_color_lower = "Yellow";
                path_color = "YELLOW";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 5:
                path_carbrand = "JEEP";
                path_color_lower = "Blue";
                path_color = "BLUE";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 6:
                path_carbrand = "JEEP";
                path_color_lower = "Green";
                path_color = "GREEN";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 7:
                path_carbrand = "JEEP";
                path_color_lower = "Black";
                path_color = "BLACK";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 8:
                path_carbrand = "JEEP";
                path_color_lower = "Red";
                path_color = "RED";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
            case 9:
                path_carbrand = "JEEP";
                path_color_lower = "Yellow";
                path_color = "YELLOW";
                full_path[0] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[0][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[0][1] + end_path;
                full_path[1] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[1][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[1][1] + end_path;
                full_path[2] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[2][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[2][1] + end_path;
                full_path[3] = path + path_carbrand + "/" + path_color + "_" + path_carbrand + "/" + path_diretion[3][0] + "/" + path_color_lower + "_" + path_carbrand + "_CLEAN_" + path_diretion[3][1] + end_path;
                break;
        }
        try {
            cars[i].up = ImageIO.read(new File(full_path[1]));
            cars[i].down = ImageIO.read(new File(full_path[2]));
            cars[i].left = ImageIO.read(new File(full_path[3]));
            cars[i].right = ImageIO.read(new File(full_path[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
