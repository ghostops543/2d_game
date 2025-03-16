package Main;

import entitys.new_npc;
import monster.MON_Crab;
import monster.MON_Squib;
import objects.OBJ_machine_gun;
import objects.OBJ_revolver;

import java.util.Random;

public class ObjectPlacment {
    PanelSettings gp;
    int randint;
    int randint2;

    public ObjectPlacment(PanelSettings gp) {
        this.gp = gp;
    }

    public void setObject(){
        int mapNum= 0;
        int i = 0;
        gp.obj[mapNum][0] = new OBJ_revolver(gp);
        gp.obj[mapNum][0].worldx = 4 * gp.tileSize;
        gp.obj[mapNum][0].worldy = 4 * gp.tileSize;

        gp.obj[mapNum][1] = new OBJ_machine_gun(gp);
        gp.obj[mapNum][1].worldx = 6 * gp.tileSize;
        gp.obj[mapNum][1].worldy = 6 * gp.tileSize;
    }
    public void setNPC(){
        int mapNum= 0;
        gp.npc[mapNum][0] = new new_npc(gp);
        gp.npc[mapNum][0].worldx = 10 * gp.tileSize;
        gp.npc[mapNum][0].worldy = 3 * gp.tileSize;
    }
    public void setMonster(){
        int mapNum= 0;
        getRandCords();
        gp.monster[mapNum][0] = new MON_Squib(gp);
        gp.monster[mapNum][0].worldx = randint * gp.tileSize;
        gp.monster[mapNum][0].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][1] = new MON_Squib(gp);
        gp.monster[mapNum][1].worldx = randint * gp.tileSize;
        gp.monster[mapNum][1].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][2] = new MON_Squib(gp);
        gp.monster[mapNum][2].worldx = randint * gp.tileSize;
        gp.monster[mapNum][2].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][3] = new MON_Squib(gp);
        gp.monster[mapNum][3].worldx = randint * gp.tileSize;
        gp.monster[mapNum][3].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][4] = new MON_Squib(gp);
        gp.monster[mapNum][4].worldx = randint * gp.tileSize;
        gp.monster[mapNum][4].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][5] = new MON_Squib(gp);
        gp.monster[mapNum][5].worldx = randint * gp.tileSize;
        gp.monster[mapNum][5].worldy = randint2 * gp.tileSize;
        getRandCords();
        gp.monster[mapNum][6] = new MON_Squib(gp);
        gp.monster[mapNum][6].worldx = randint * gp.tileSize;
        gp.monster[mapNum][6].worldy = randint2 * gp.tileSize;
    }
    public void getRandCords(){
        Random rand = new Random();
         randint = rand.nextInt(64);
         randint2 = rand.nextInt(48);

    }

}
