import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ParkingLights extends Tile {
    GamePanel gp;
    protected Tile[] lights;
    protected int[][] parkingspot_position;
    private HashMap<Integer, String> filepathMap = new HashMap<>();

    ParkingLights(GamePanel gp) {
        this.gp = gp;
        lights = new Tile[4];
        parkingspot_position = new int[110][4];
//        load_img();
        load_Img();
        get_parking_Pos("mapfrfrsmol.csv");
    }

    private void get_parking_Pos(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            int i = 0;
            while (col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while (col < gp.maxCol) {
                    String[] numbers = line.split(",");
                    int num = Integer.parseInt(numbers[col]);
                    if (num == 4 || num == 5) {
                        parkingspot_position[i][0] = col;
                        parkingspot_position[i][1] = row;
                        parkingspot_position[i][2] = 0;
                        parkingspot_position[i][3] = num;
                        i++;
                    }
                    col++;
                }
                if (col == gp.maxCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    } // GET PARKINGSPOT POSITION (COLUMN, ROW)

    private void initFilepathMap() {
        filepathMap.put(0, "/lights/bulb_green_left.png");
        filepathMap.put(1, "/lights/bulb_green_right.png");
        filepathMap.put(2, "/lights/bulb_red_left.png");
        filepathMap.put(3, "/lights/bulb_red_right.png");
    } // INICJALIZACA MAPY SWIATEL FILEPATH

    private void load_Img(){
        initFilepathMap();
        for(int i = 0; i < lights.length; i++){
            try {
                lights[i] = new Tile();
                lights[i].image = ImageIO.read(getClass().getResourceAsStream(filepathMap.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } // ITERACYJNE LADOWANIE ZDJEC SWIATEL

    private void load_img() {
        try {
            lights[0] = new Tile();
            lights[0].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_left.png"));

            lights[1] = new Tile();
            lights[1].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_green_right.png"));

            lights[2] = new Tile();
            lights[2].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_left.png"));

            lights[3] = new Tile();
            lights[3].image = ImageIO.read(getClass().getResourceAsStream("/lights/bulb_red_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void lights_update(Entity entity) {
        if (entity.parking) {
            for (int i = 0; i < 110; i++) {
                if (Math.abs(entity.x - parkingspot_position[i][0] * gp.tileSize) < gp.tileSize - 10 && Math.abs(entity.y - parkingspot_position[i][1] * gp.tileSize) < gp.tileSize - 10) {
                    parkingspot_position[i][2] = 1;
                }
            }
        }
    } // AKTUALIZACJA STANU SWIATEL

}
