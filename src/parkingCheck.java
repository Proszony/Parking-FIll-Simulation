public class parkingCheck {
    GamePanel gp;

    public parkingCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLX = entity.x + entity.solidArea.x;
        int entityRX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTY = entity.y + entity.solidArea.y;
        int entityBY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLCol = entityLX / gp.tileSize;
        int entityRCol = entityRX / gp.tileSize;
        int entityTRow = entityTY / gp.tileSize;
        int entityBRow = entityBY / gp.tileSize;
        int TileNum1, TileNum2;

        if (entity.leave_parkingspot == false) {
            switch (entity.direction) {
                case "up":
                    entityTRow = (entityTY - entity.speed) / gp.tileSize;
                    TileNum1 = gp.TileM.mapTileNUM[entityLCol][entityTRow];
                    TileNum2 = gp.TileM.mapTileNUM[entityRCol][entityTRow];
                    if (gp.TileM.tile[TileNum2].parkingspot == true) {
                        entity.parking = true;
                        gp.TileM.tile[TileNum2].taken = true;

                    } else {
                        entity.parking = false;
                        gp.TileM.tile[TileNum2].taken = false;
                    }
                    break;
                case "down":
                    entityBRow = (entityBY + entity.speed) / gp.tileSize;
                    TileNum1 = gp.TileM.mapTileNUM[entityLCol][entityBRow];
                    TileNum2 = gp.TileM.mapTileNUM[entityRCol][entityBRow];
                    if (gp.TileM.tile[TileNum2].parkingspot == true) {
                        entity.parking = true;
                    } else {
                        entity.parking = false;
                        gp.TileM.tile[TileNum2].taken = false;
                    }
                    break;
                case "left":
                    entityLCol = (entityLX - entity.speed + 15) / gp.tileSize;
                    TileNum1 = gp.TileM.mapTileNUM[entityLCol][entityTRow];
                    TileNum2 = gp.TileM.mapTileNUM[entityLCol][entityBRow];
                    if (gp.TileM.tile[TileNum2].parkingspot == true) {
                        entity.parking = true;
                        gp.TileM.tile[TileNum2].taken = true;
                    } else {
                        entity.parking = false;
                        gp.TileM.tile[TileNum2].taken = false;
                    }
                    break;
                case "right":
                    entityRCol = (entityRX + entity.speed - 30) / gp.tileSize;
                    TileNum1 = gp.TileM.mapTileNUM[entityRCol][entityTRow];
                    TileNum2 = gp.TileM.mapTileNUM[entityRCol][entityBRow];
                    if (gp.TileM.tile[TileNum2].parkingspot == true) {
                        entity.parking = true;
                        gp.TileM.tile[TileNum2].taken = true;
                    } else {
                        entity.parking = false;
                        gp.TileM.tile[TileNum2].taken = false;
                    }
                    break;
            }
        }
    }
}
