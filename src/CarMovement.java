import java.awt.*;
import java.awt.image.BufferedImage;

public class CarMovement extends Cars {
    public CarMovement(GamePanel gp) {
        super(gp);
    }

    public int[][] car2x2 = new int[3][3];

    public void getRoad(int i) { // gets the 3x3 grid of tiles (-1 for out of border)
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        System.out.println("col = " + col + " row = " + row);
        car2x2[1][1] = gp.TileM.mapTileNUM[col][row];
        if (row == 0) {
            for (int r = -1; r < 2; r++) {
                if (r == -1) {
                    for (int c = -1; c < 2; c++) {
                        car2x2[1 + c][1 + r] = -1;
                    }
                } else {
                    checkingRxC(col, row, r);
                }
            }
        } else if (row == gp.maxRow - 1) {
            for (int r = -1; r < 2; r++) {
                if (r == 1) {
                    for (int c = -1; c < 2; c++) {
                        car2x2[1 + c][1 + r] = -1;
                    }
                } else {
                    checkingRxC(col, row, r);
                }
            }
        } else {
            for (int r = -1; r < 2; r++) {
                checkingRxC(col, row, r);
            }
        }
    }

    public void checkingRxC(int col, int row, int r) { // Checks tiles for columns in a row
        if (col == 0) {
            for (int c = -1; c < 2; c++) {
                if (c == -1) {
                    car2x2[1 + c][1 + r] = -1;
                } else {
                    car2x2[1 + c][1 + r] = gp.TileM.mapTileNUM[col + c][row + r];
                }
            }
        } else if (col == gp.maxCol - 1) {
            for (int c = -1; c < 2; c++) {
                if (c == 1) {
                    car2x2[1 + c][1 + r] = -1;
                } else {
                    car2x2[1 + c][1 + r] = gp.TileM.mapTileNUM[col + c][row + r];
                }
            }
        } else {
            for (int c = -1; c < 2; c++) {
                car2x2[1 + c][1 + r] = gp.TileM.mapTileNUM[col + c][row + r];
            }
        }
    }

    public void check2x2(int i) {
        getRoad(i);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(car2x2[c][r] + " ");
            }
            System.out.println();
        }
    }

    public void update(int i) {
        cars[i].solidArea = new Rectangle(cars[i].x + 18, cars[i].y + 18, 8, 8);
        check2x2(i);
        move(i);
    }

    public void move(int i){
        switch (car2x2[1][1]){
            case 8:
                cars[i].direction = "right";
                cars[i].x += cars[i].speed;
                break;
            case 9:
                if (car2x2[0][2] == 0) {
                    cars[i].speed = 0;
                }
                cars[i].direction = "left";
                cars[i].x -= cars[i].speed;
                break;
        }
    }

    public void draw(Graphics2D g2, int i) {
        BufferedImage img = null;
        switch (cars[i].direction) {
            case "up":
                img = cars[i].up;
                break;
            case "down":
                img = cars[i].down;
                break;
            case "left":
                img = cars[i].left;
                break;
            case "right":
                img = cars[i].right;
                break;
        }
        g2.drawImage(img, cars[i].x - 24, cars[i].y - 24, gp.PlayerSize * 2, gp.PlayerSize * 2, null);
    }
}
