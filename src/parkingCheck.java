public class parkingCheck {
    GamePanel gp;

    public parkingCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkPark(Entity entity) {
        int entitySolidX = entity.solidArea.x + entity.solidArea.width;
        int entitySolidY = entity.y + entity.solidArea.height + 5;
        int col = entitySolidX / gp.tileSize;
        int row = entitySolidY / gp.tileSize;
//        System.out.println("col = " + col + " row = " + row + " tile = " + gp.TileM.mapTileNUM[col][row]);
        if (!entity.get_leaveparkingstop(entity)) {
            switch (entity.direction) {
                case "left":
                    col = (entitySolidX + 8) / gp.tileSize;
                    row = entitySolidY / gp.tileSize;
                    if (gp.TileM.mapTileNUM[col][row] == 5 || gp.TileM.mapTileNUM[col][row] == 4) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                    }
                    break;
                case "right":
                    col = (entitySolidX - 15) / gp.tileSize;
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
