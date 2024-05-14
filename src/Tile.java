import java.awt.image.BufferedImage;

public class Tile {
    protected BufferedImage image;
    private boolean parkingspot = false;

    void setParkingspot_ture(Tile tile){
        tile.parkingspot = true;
    }
    void setParkingspot_false(Tile tile){
        tile.parkingspot = false;
    }
}
