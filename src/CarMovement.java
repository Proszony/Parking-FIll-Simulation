import java.awt.*;

public class CarMovement extends Cars {
    public CarMovement(GamePanel gp) {
        super(gp);
    }

    public int[][] car2x2 = new int[3][3];

    public void getRoad(int i) {
        int col = (cars[i].x + cars[i].solidArea.width + 5) / gp.tileSize;
        int row = (cars[i].y + cars[i].solidArea.height + 5) / gp.tileSize;
        System.out.println("col = " + col + " row = " + row);
        car2x2[1][1] = gp.TileM.mapTileNUM[col][row];
        if (row == 0) {
        } else if (row == gp.maxRow-1) {
        } else {
            for (int r = -1; r < 2; r++) {
                if (col == 0) {
                    for (int c = -1; c < 2; c++) {
                        if (c == -1) {
                            car2x2[1 + c][1 + r] = -1;
                        } else {
                            car2x2[1 + c][1 + r] = gp.TileM.mapTileNUM[col + c][row + r];
                        }
                    }
                } else if (col == gp.maxCol-1) {
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
    }
}
