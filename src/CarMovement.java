import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
public class CarMovement extends Cars {
    public CarMovement(GamePanel gp) {
        super(gp);

    }
    public int[][] car2x2 = new int[3][3];
    private final Random random = new Random();

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
        if (!cars[i].turned) {
            cars[i].chose_turnLRS = random.nextInt(3);
            cars[i].chose_turnLR = random.nextInt(100) % 2;
            cars[i].turned = true;
        }
        check2x2(i);
        if (checkpark(i)) {
            park(i);
            if (cars[i].direction == "up") {
                System.out.println("SKDAOKDOWA");
            }
        } else if (!checkpark(i)) {
            if (!cars[i].parking) {
                move(i);
            }
        }
        gp.parkingCheck.checkPark(cars[i]);

        int counter = 0;
        for (int c = 0; c < 110; c++) {
            if (cars[c].parking) {
                counter++;
            }
        }
        gp.cars_parked = counter;

    }

    public void move(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        // poprawic by losowalo czy skreca tylko raz
        // skrecianie 3 strony niech szuka nr tile 0 1 2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        switch (car2x2[1][1]) {
            case 0:
                switch (cars[i].direction) {
                    case "down":
                        if ((car2x2[1][0] == 1 || car2x2[0][0] == 1) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLRS == 2) {
                            cars[i].direction = "right";
                            cars[i].x += cars[i].speed;
                        } else {
                            cars[i].y += cars[i].speed;
                        }
                        break;
                    case "up":
                        cars[i].y -= cars[i].speed;
                        break;
                    case "left":
                        cars[i].x -= cars[i].speed;
                        break;
                    case "right":
                        cars[i].x += cars[i].speed;
                        break;
                }
                break;
            case 1:
                if (car2x2[1][2] == 0 && car2x2[2][1] == 2 && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLRS == 0) {
                    cars[i].direction = "left";
                    cars[i].x -= cars[i].speed;
                } else if (car2x2[1][2] == 0 && car2x2[2][1] == 2 && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLRS == 1) {
                    cars[i].direction = "down";
                    cars[i].y += cars[i].speed;
                } else if (car2x2[1][2] == 0 && car2x2[2][1] == 2 && car2x2[0][1] != 17 && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLRS == 2) {
                    cars[i].chose_turnLRS = random.nextInt(3);
                } else if (car2x2[1][2] == 0 && car2x2[2][1] == 2 && car2x2[0][1] != 17 && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLRS == 2) {
                    cars[i].direction = "up";
                    cars[i].y -= cars[i].speed;
                } else if ((car2x2[1][2] == 8 || car2x2[1][0] == 17) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 0) {
                    cars[i].direction = "down";
                    cars[i].y += cars[i].speed;
                } else if ((car2x2[1][2] == 0 || car2x2[1][0] == 0) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 0) {
                    cars[i].direction = "down";
                    cars[i].y += cars[i].speed;
                } else {
                    cars[i].direction = "left";
                    cars[i].x -= cars[i].speed;
                }
                break;
            case 2:
                if ((car2x2[0][1] == 1 || car2x2[0][0] == 1) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 1) {
                    cars[i].direction = "up";
                    cars[i].y -= cars[i].speed;
                } else if ((car2x2[1][2] == 0 || car2x2[1][0] == 0) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 0) {
                    cars[i].direction = "down";
                    cars[i].y += cars[i].speed;
                } else {
                    cars[i].direction = "left";
                    cars[i].x -= cars[i].speed;
                }
                break;
            case 3:
                switch (cars[i].direction) {
                    case "down":
                        cars[i].y += cars[i].speed;
                        break;
                    case "up":
                        cars[i].y -= cars[i].speed;
                        break;
                    case "left":
                        cars[i].x -= cars[i].speed;
                        break;
                    case "right":
                        cars[i].x += cars[i].speed;
                        break;
                }
                break;
            case 4:
                cars[i].direction = "right";
                cars[i].x += cars[i].speed;
                break;
            case 5:
                cars[i].direction = "left";
                cars[i].x -= cars[i].speed;
                break;
            case 8:
                if ((car2x2[0][1] == 1 || car2x2[0][0] == 1) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 1) {
                    cars[i].direction = "up";
                    cars[i].y -= cars[i].speed;
                } else {
                    cars[i].direction = "right";
                    cars[i].x += cars[i].speed;
                    cars[i].turned = false;
                }
                break;
            case 9:
                if ((car2x2[1][2] == 0 || car2x2[1][0] == 0) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].chose_turnLR == 0) { // I TU
                    cars[i].direction = "down";
                    cars[i].y += cars[i].speed;
                } else {
                    cars[i].direction = "left";
                    cars[i].x -= cars[i].speed;
                    cars[i].turned = false;
                }
                break;
            case 17:
                cars[i].direction = "down";
                cars[i].y += cars[i].speed;
                cars[i].turned = false;
                break;
            case 19:
                cars[i].direction = "up";
                cars[i].y -= cars[i].speed;
                cars[i].turned = false;
                break;
        }
    }

    private boolean checkpark(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        boolean parked = false;

        switch (cars[i].direction) {
            case "up":
                for (int j = 0; j < 110; j++) {
                    int col2 = (cars[j].x + cars[j].solidArea.width + 5) / gp.tileSize;
                    int row2 = (cars[j].y + cars[j].solidArea.height + 5) / gp.tileSize;
                    if (col - col2 == -1 && row2 == row && cars[j].parking) {
                        parked = true;
                        break;
                    }
                }
                if ((car2x2[2][1] == 4 || car2x2[1][1] == 4) && cars[i].y - (row * gp.tileSize) < -2 && cars[i].x - (col * gp.tileSize) > -15  && !parked) {
                    return true;
                }
                break;
            case "right":
                if ((car2x2[2][1] == 4 || car2x2[1][1] == 4) && cars[i].y - (row * gp.tileSize) < -2 && cars[i].x - (col * gp.tileSize) > -1) {
                    return true;
                }
            case "down":
                for (int j = 0; j < 110; j++) {
                    int col2 = (cars[j].x + cars[j].solidArea.width + 5) / gp.tileSize;
                    int row2 = (cars[j].y + cars[j].solidArea.height + 5) / gp.tileSize;
                    if (col - col2 == 1 && row2 == row && cars[j].parking) {
                        parked = true;
                        break;
                    }
                }
                if ((car2x2[0][1] == 5 || car2x2[1][1] == 5) &&  cars[i].x - (col * gp.tileSize) < 1 && cars[i].y - (row * gp.tileSize) < -2 && !parked) {
                    return true;
                }
                break;
            case "left":
                if ((car2x2[0][1] == 5 || car2x2[1][1] == 5) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].y - (row * gp.tileSize) < -2) {
                    return true;
                }
        }
        return false;
    }

    public void park(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;

        if (cars[i].y - ((row) * gp.tileSize) < -2) {
            if (cars[i].direction == "down") {
                cars[i].direction = "left";
                cars[i].x -= cars[i].speed;
            }
            if (cars[i].direction == "left") {
                cars[i].x -= cars[i].speed;
            }
            if (cars[i].direction == "up") {
                cars[i].direction = "right";
                cars[i].x += cars[i].speed;
            }
            if (cars[i].direction == "right") {
                cars[i].x += cars[i].speed;
            }
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
