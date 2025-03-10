package Main;

import entitys.Entity;

import java.sql.SQLOutput;

public class CollisionDetection {

    PanelSettings gp;

    public CollisionDetection(PanelSettings gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {

        int entityLeftWorldx= entity.worldx + entity.solidArea.x;
        int entityRightWorldx= entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldy= entity.worldy + entity.solidArea.y;
        int entityBottomWorldy= entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldx/gp.tileSize;
        int entityRightCol = entityRightWorldx/gp.tileSize;
        int entityTopRow = entityTopWorldy/gp.tileSize;
        int entityBottomRow = entityBottomWorldy/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){//finds tile that player is about to step in
            case "up":
                entityTopRow = (entityTopWorldy - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldy + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldx - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldx + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }

    }
    public int checkObject(Entity entity, boolean player) {//checks if it is a player
        int index = 999;
        if (gp.obj[0] != null) {
            for (int i = 0; i < gp.obj[0].length; i++) {
                if (gp.obj[0][i] != null) {
                    //get solid area position
                    entity.solidArea.x = entity.worldx + entity.solidArea.x;
                    entity.solidArea.y = entity.worldy + entity.solidArea.y;
                    //get objects postition
                    gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldx + gp.obj[gp.currentMap][i].solidArea.x;
                    gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldy + gp.obj[gp.currentMap][i].solidArea.y;

                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            break;
                    }
                    if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {// checks if entity is colliding with object
                        if (gp.obj[gp.currentMap][i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultx;
                    entity.solidArea.y = entity.solidAreaDefaulty;
                    gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultx;
                    gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaulty;
                }
            }
        }
            return index;
    }
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;
        for(int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                //get solid area position
                entity.solidArea.x = entity.worldx + entity.solidArea.x;
                entity.solidArea.y = entity.worldy + entity.solidArea.y;
                //get objects postition
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldx + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldy + target[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {// checks if entity is colliding with object
                    if (target[gp.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultx;
                entity.solidArea.y = entity.solidAreaDefaulty;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultx;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaulty;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;

        //get solid area position
        entity.solidArea.x = entity.worldx + entity.solidArea.x;
        entity.solidArea.y = entity.worldy + entity.solidArea.y;
        //get objects postition
        gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;

        switch(entity.direction){
            case "up": entity.solidArea.y -= entity.speed;break;
            case "down": entity.solidArea.y += entity.speed;break;
            case "left": entity.solidArea.x -= entity.speed;break;
            case "right": entity.solidArea.x += entity.speed;break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea)) {// checks if entity is colliding with object
                entity.collisionOn = true;
                contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultx;
        entity.solidArea.y = entity.solidAreaDefaulty;
        gp.player.solidArea.x = gp.player.solidAreaDefaultx;
        gp.player.solidArea.y = gp.player.solidAreaDefaulty;

        return contactPlayer;
    }
}
