package Main;

import java.awt.*;

public class EventHandler {
    PanelSettings gp;
    Rectangle eventRect;
    int eventRecDefaultX, eventRecDefaultY;
    public EventHandler(PanelSettings gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 15;
        eventRect.height = 15;
        eventRecDefaultX = eventRect.x;
        eventRecDefaultY = eventRect.y;
    }
    public void checkEvent() {
        if(hit(2, 2, "any")){
            damagePit();
        }
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultx;
        gp.player.solidArea.y = gp.player.solidAreaDefaulty;
        eventRect.x = eventRecDefaultX;
        eventRect.y = eventRecDefaultY;

        return hit;
    }
    public void damagePit(){
//        gp.gameState = gameState;
        gp.player.life -= 1;
        System.out.println("dmg");
    }
}

