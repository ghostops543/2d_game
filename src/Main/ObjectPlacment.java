package Main;

import entitys.new_npc;
import monster.MON_Squib;
import objects.OBJ_revolver;

public class ObjectPlacment {
    PanelSettings gp;

    public ObjectPlacment(PanelSettings gp) {
        this.gp = gp;
    }

    public void setObject(){
        int mapNum= 0;
        int i = 0;
        gp.obj[mapNum][0] = new OBJ_revolver(gp);
        gp.obj[mapNum][0].worldx = 32 * gp.tileSize;
        gp.obj[mapNum][0].worldy = 24 * gp.tileSize;
    }
    public void setNPC(){
        int mapNum= 0;
        gp.npc[mapNum][0] = new new_npc(gp);
        gp.npc[mapNum][0].worldx = 32 * gp.tileSize;
        gp.npc[mapNum][0].worldy = 16 * gp.tileSize;
    }
    public void setMonster(){
        int mapNum= 0;
        gp.monster[mapNum][0] = new MON_Squib(gp);
        gp.monster[mapNum][0].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][0].worldy = 8 * gp.tileSize;
        gp.monster[mapNum][1] = new MON_Squib(gp);
        gp.monster[mapNum][1].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][1].worldy = 7 * gp.tileSize;
        gp.monster[mapNum][2] = new MON_Squib(gp);
        gp.monster[mapNum][2].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][2].worldy = 6 * gp.tileSize;
        gp.monster[mapNum][3] = new MON_Squib(gp);
        gp.monster[mapNum][3].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][3].worldy = 5 * gp.tileSize;
        gp.monster[mapNum][4] = new MON_Squib(gp);
        gp.monster[mapNum][4].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][4].worldy = 4 * gp.tileSize;
        gp.monster[mapNum][5] = new MON_Squib(gp);
        gp.monster[mapNum][5].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][5].worldy = 3 * gp.tileSize;
        gp.monster[mapNum][6] = new MON_Squib(gp);
        gp.monster[mapNum][6].worldx = 32 * gp.tileSize;
        gp.monster[mapNum][6].worldy = 2 * gp.tileSize;
    }

}
