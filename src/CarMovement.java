import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class CarMovement extends Cars {
    public CarMovement(GamePanel gp) {
        super(gp);
    }

    private int[][] car2x2 = new int[3][3];
    private final Random random = new Random();

    private void getRoad(int i) { // gets the 3x3 grid of tiles (-1 for out of border)
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;

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

    private void checkingRxC(int col, int row, int r) { // Checks tiles for columns in a row
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

    private void check2x2(int i) {
        getRoad(i);
    }

    public void update(int i) {
        setBox_color_green(cars[i]);
        cars[i].speed = 5;
        cars[i].solidArea = new Rectangle(cars[i].x + 18, cars[i].y + 18, 8, 8);
        if (!cars[i].parking) {
            cars[i].bounding_box = new Rectangle(cars[i].x - 48, cars[i].y - 48, gp.tileSize * 3, gp.tileSize * 3);
        } else {
            cars[i].bounding_box = new Rectangle(0, 0, 0, 0);
        }
        if (!getChose_turn(cars[i])) {
            chose_LRS(i);
        }
        check2x2(i);
        if (checkpark(i)) {
            park(i);
        } else if (!checkpark(i)) {
            if (!cars[i].parking) {
                border(i);
                for (int j = 0; j < gp.max_cars_onscreen + gp.cars_parked; j++) {
                    if (j >= 110) continue;
                    else {
                        if(cars[i].speed < 5){
                            cars[i].speed = 5;
                        }
                        if(cars[j].speed < 5){
                            cars[j].speed = 5;
                        }
                        if (j != i) {
                            if (collision_check(cars[i], cars[j])) {
                                //System.out.println("Car[" + i + "] x:" + cars[i].x + " y:" + cars[i].y + " collides with Car[" + j + "] x:" + cars[j].x + " y:" + cars[j].y);
                                setBox_color_red(cars[i]);
                                setBox_color_red(cars[j]);
                                break;
                            }
                        }
                    }
                }
                move(i);
            }
            else {
                if (car2x2[1][1] == 4 && Objects.equals(cars[i].direction, "right")) {
                    cars[i].direction = "left";
                }
                if (car2x2[1][1] == 5 && Objects.equals(cars[i].direction, "left")) {
                    cars[i].direction = "right";
                }
            }


        }
        checkPark(cars[i], gp);

        int counter = 0;
        for (int c = 0; c < 110; c++) {
            if (cars[c].parking) {
                counter++;
            }
        }
        gp.cars_parked = counter;
    }

    private void move(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        switch (car2x2[1][1]) {
            case 19: // up
                cars[i].x = col * gp.tileSize;
                cars[i].direction = "up";
                cars[i].y -= cars[i].speed;
                setChose_turn_false(cars[i]);
                setTurned_false(cars[i]);
                break;
            case 17: // down
                cars[i].x = col * gp.tileSize;
                cars[i].direction = "down";
                cars[i].y += cars[i].speed;
                setChose_turn_false(cars[i]);
                setTurned_false(cars[i]);
                break;
            case 9: // left // 3 - way left turn (left -> down)
                if (car2x2[1][2] == 0 && cars[i].x - (col * gp.tileSize) < 1) {
                    if (cars[i].chose_turnLR == 0) {
                        turnLEFT(i);
                        switch (cars[i].direction) {
                            case "down":
                                cars[i].x = col * gp.tileSize;
                                cars[i].y += cars[i].speed;
                                break;
                            case "left":
                                cars[i].x -= cars[i].speed;
                                break;
                        }
                    } else {
                        cars[i].direction = "left";
                        cars[i].x -= cars[i].speed;
                    }
                } else {
                    if (cars[i].y - (row * gp.tileSize) > 5) {
                        cars[i].direction = "up";
                        cars[i].y -= cars[i].speed;
                        setChose_turn_false(cars[i]);
                        setTurned_false(cars[i]);
                    } else if ((row * gp.tileSize) - cars[i].y > 5) {
                        cars[i].direction = "down";
                        cars[i].y += cars[i].speed;
                        setChose_turn_false(cars[i]);
                        setTurned_false(cars[i]);
                    } else {
                        cars[i].direction = "left";
                        cars[i].x -= cars[i].speed;
                        setChose_turn_false(cars[i]);
                        setTurned_false(cars[i]);
                    }
                }
                break;
            case 8: // right // 3 - way left turn (right -> up)
                if (car2x2[1][0] == 2 && (col * gp.tileSize) - cars[i].x < 1) {
                    if (cars[i].chose_turnLR == 0) {
                        turnLEFT(i);
                        switch (cars[i].direction) {
                            case "up":
                                cars[i].x = col * gp.tileSize;
                                cars[i].y -= cars[i].speed;
                                break;
                            case "right":
                                cars[i].x += cars[i].speed;
                                break;
                        }
                    } else {
                        cars[i].direction = "right";
                        cars[i].x += cars[i].speed;
                    }
                } else {
                    cars[i].direction = "right";
                    cars[i].x += cars[i].speed;
                    setChose_turn_false(cars[i]);
                    setTurned_false(cars[i]);
                }
                break;
            default:
                // 4 - way left turn (down -> right)  LEFT
                if (car2x2[1][1] == 0 && car2x2[1][0] == 1 && (row * gp.tileSize) - cars[i].y < 1 && Objects.equals(cars[i].direction, "down") && !getTurned(cars[i])) {
                    if (cars[i].chose_turnLRS == 0) {
                        turnLEFT(i);
                        if (Objects.equals(cars[i].direction, "right")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x += cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "down")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y += cars[i].speed;
                        }
                    } // 4 - way left turn (up -> left)  LEFT
                } else if (car2x2[1][1] == 2 && car2x2[1][2] == 3 && cars[i].y - (row * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "up") && !getTurned(cars[i])) {
                    if (cars[i].chose_turnLRS == 0) {
                        turnLEFT(i);
                        if (Objects.equals(cars[i].direction, "left")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x -= cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "up")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y -= cars[i].speed;
                        }
                    } // 4 - way left turn (left -> down)  LEFT
                } else if (car2x2[1][1] == 1 && car2x2[1][2] == 0 && cars[i].x - (col * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "left") && !getTurned(cars[i])) {
                    if (cars[i].chose_turnLRS == 0) {
                        turnLEFT(i);
                        if (Objects.equals(cars[i].direction, "down")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y += cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "left")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x -= cars[i].speed;
                        }
                    } // 4 - way left turn (right -> up)  LEFT
                } else if (car2x2[1][1] == 3 && car2x2[1][0] == 2 && (col * gp.tileSize) - cars[i].x < 1 && Objects.equals((cars[i].direction), "right") && !getTurned(cars[i])) {
                    if (cars[i].chose_turnLRS == 0) {
                        turnLEFT(i);
                        if (Objects.equals(cars[i].direction, "up")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y -= cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "right")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x += cars[i].speed;
                        }
                    } // 4 - way right turn (right -> down)
                } else if (car2x2[1][1] == 0 && car2x2[2][1] == 3 && (col * gp.tileSize) - cars[i].x < 1 && Objects.equals(cars[i].direction, "right")
                        && !getTurned(cars[i]) && !getNo_right_truns(cars[i])) {
                    if (cars[i].chose_turnLRS == 1) {
                        turnRIGHT(i);
                        if (Objects.equals(cars[i].direction, "down")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y += cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "right")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x += cars[i].speed;
                        }
                    } // 4 - way right turn (left -> up)
                } else if (car2x2[1][1] == 2 && car2x2[0][1] == 1 && cars[i].x - (col * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "left")
                        && !getTurned(cars[i]) && !getNo_right_truns(cars[i])) {
                    if (cars[i].chose_turnLRS == 1) {
                        turnRIGHT(i);
                        if (Objects.equals(cars[i].direction, "up")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y -= cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "left")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x -= cars[i].speed;
                        }
                    } // 4 - way right turn (up -> right) car2x2[1][0] == 2
                } else if (car2x2[1][1] == 3 && car2x2[0][1] == 0 && cars[i].y - (row * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "up")
                        && !getTurned(cars[i]) && !getNo_right_truns(cars[i])) {
                    if (cars[i].chose_turnLRS == 1) {
                        turnRIGHT(i);
                        if (Objects.equals(cars[i].direction, "right")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x += cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "up")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y -= cars[i].speed;
                        }
                    } // 4 - way right turn (down -> left) car2x2[1][2] == 0
                } else if (car2x2[1][1] == 1 && car2x2[2][1] == 2 && (row * gp.tileSize) - cars[i].y < 1 && Objects.equals(cars[i].direction, "down")
                        && !getTurned(cars[i]) && !getNo_right_truns(cars[i])) {
                    if (cars[i].chose_turnLRS == 1) {
                        turnRIGHT(i);
                        if (Objects.equals(cars[i].direction, "left")) {
                            cars[i].y = row * gp.tileSize - 10;
                            cars[i].x -= cars[i].speed;
                        }
                    } else {
                        if (Objects.equals(cars[i].direction, "down")) {
                            cars[i].x = col * gp.tileSize;
                            cars[i].y += cars[i].speed;
                        }
                    } // skret w prawo na brzegach
                } else {
                    switch (cars[i].direction) {
                        case "up":
                            cars[i].y -= cars[i].speed;
                            break;
                        case "down":
                            cars[i].y += cars[i].speed;
                            break;
                        case "left":
                            cars[i].x -= cars[i].speed;
                            break;
                        case "right":
                            cars[i].x += cars[i].speed;
                            break;
                    }
                }
        }
    }

    private void chose_LRS(int i) {
        setChose_turn_true(cars[i]);
        cars[i].chose_turnLRS = random.nextInt(99) % 3;
        cars[i].chose_turnLR = random.nextInt(100) % 2;

        //System.out.println("CARS["+(gp.max_cars_onscreen + gp.cars_parked -1)+"] LOSUJEEEE => " + cars[gp.max_cars_onscreen + gp.cars_parked -1].chose_turnLR);

    }

    private void turnLEFT(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        boolean turns_left = false;
        setTurned_true(cars[i]);
        switch (cars[i].direction) {
            case "right": //roadsideD
                if (car2x2[1][0] == 2 && (col * gp.tileSize) - cars[i].x < 1 && Objects.equals(cars[i].direction, "right")) {
                    turns_left = true;
                }
                if (turns_left) {
                    cars[i].direction = "up";
                }
                break;
            case "left": //roadsideU
                if (car2x2[1][2] == 0 && cars[i].x - (col * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "left")) {
                    turns_left = true;
                }
                if (turns_left) {
                    cars[i].direction = "down";
                }
                break;
            case "up": //roadupL2
                if (car2x2[0][1] == 1 && cars[i].y - (row * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "up")) {
                    turns_left = true;
                }
                if (turns_left) {
                    cars[i].direction = "left";
                }
                break;
            case "down": //roadupR2
                if (car2x2[1][0] == 1 && (row * gp.tileSize) - cars[i].y < 1 && Objects.equals(cars[i].direction, "down")) {
                    turns_left = true;
                }
                if (turns_left) {
                    cars[i].direction = "right";
                }
                break;
        }
    }

    private void turnRIGHT(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        boolean turns_right = false;
        setTurned_true(cars[i]);
        switch (cars[i].direction) {
            case "right": // roadsideD
                if (car2x2[2][1] == 3 && (col * gp.tileSize) - cars[i].x < 1 && Objects.equals(cars[i].direction, "right")) {
                    turns_right = true;
                }
                if (turns_right) {
                    cars[i].direction = "down";
                }
                break;
            case "left": // roadsideU
                if (car2x2[0][1] == 1 && cars[i].x - (col * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "left")) {
                    turns_right = true;
                }
                if (turns_right) {
                    cars[i].direction = "up";
                }
                break;
            case "up": // roadupL2
                if (car2x2[0][1] == 0 && cars[i].y - (row * gp.tileSize) < 1 && Objects.equals(cars[i].direction, "up")) {
                    turns_right = true;
                }
                if (turns_right) {
                    cars[i].direction = "right";
                }
                break;
            case "down": // roadupR2
                if (car2x2[2][1] == 2 && (row * gp.tileSize) - cars[i].y < 1 && Objects.equals(cars[i].direction, "down")) {
                    turns_right = true;
                }
                if (turns_right) {
                    cars[i].direction = "left";
                }
                break;
        }
    }

    private boolean checkpark(int i) {
        // sprawdzac dla 4 (okrąg)
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
                if ((car2x2[2][1] == 4 || car2x2[1][1] == 4) && cars[i].y - (row * gp.tileSize) < -2 && cars[i].x - (col * gp.tileSize) > -15 && !parked) {
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
                if ((car2x2[0][1] == 5 || car2x2[1][1] == 5) && cars[i].x - (col * gp.tileSize) < 1 && cars[i].y - (row * gp.tileSize) < -2 && !parked) {
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

    private void border(int i) {
        switch (car2x2[1][1]) {
            case 9:
                if (cars[i].x < 5) {
                    cars[i].y += gp.tileSize - 8;
                }
                break;
            case 8:
                if (cars[i].x > 1095) {
                    cars[i].y -= gp.tileSize - 8;
                }
        }
    }

    private void park(int i) {
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;

        if (cars[i].y - ((row) * gp.tileSize) < -2) {
            if (Objects.equals(cars[i].direction, "down")) {
                cars[i].direction = "left";
                cars[i].x -= cars[i].speed;
            }
            if (Objects.equals(cars[i].direction, "left")) {
                cars[i].x -= cars[i].speed;
            }
            if (Objects.equals(cars[i].direction, "up")) {
                cars[i].direction = "right";
                cars[i].x += cars[i].speed;
            }
            if (Objects.equals(cars[i].direction, "right")) {
                cars[i].x += cars[i].speed;
            }
        }
    }
//    void draw(Graphics2D g2, int i) { // dodac interfejs do rysowania wszystkiego
//        BufferedImage img = switch (cars[i].direction) {
//            case "up" -> cars[i].up;
//            case "down" -> cars[i].down;
//            case "left" -> cars[i].left;
//            case "right" -> cars[i].right;
//            default -> null;
//        };
//        g2.drawImage(img, cars[i].x - 24, cars[i].y - 24, gp.PlayerSize * 2, gp.PlayerSize * 2, null);
//    }
}
