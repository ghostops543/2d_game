package monster;

import Main.PanelSettings;
import entitys.Entity;

import java.util.Random;

public class MON_Crab extends Entity {
    PanelSettings gp;
    public MON_Crab(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        name = "crab";
        type = type_monster;
        speed = 10;
        maxLife = 10;
        life = maxLife;
        attackDamage=2;
        exp = 2;



        solidArea.x=3;
        solidArea.y=3;
        solidArea.width=42;
        solidArea.height=42;
        solidAreaDefaultx = solidArea.x;
        solidAreaDefaulty = solidArea.y;
        getImage();
    }
    public void getImage(){
        Random rand = new Random();
        if (rand.nextInt(1, 100 ) < 50) {
            up1 = setup("/monster/crab up down 1", gp.tileSize, gp.tileSize);
            up2 = setup("/monster/crab up down 2", gp.tileSize, gp.tileSize);
            left1 = setup("/monster/crab left right 1", gp.tileSize, gp.tileSize);
            left2 = setup("/monster/crab left right 2", gp.tileSize, gp.tileSize);
            right1 = setup("/monster/crab left right 1", gp.tileSize, gp.tileSize);
            right2 = setup("/monster/crab left right 2", gp.tileSize, gp.tileSize);
            down1 = setup("/monster/crab up down 1", gp.tileSize, gp.tileSize);
            down2 = setup("/monster/crab up down 2", gp.tileSize, gp.tileSize);
        }
        else if(rand.nextInt(1, 100 ) > 50){
            up1 = setup("/monster/crab up down 3", gp.tileSize, gp.tileSize);
            up2 = setup("/monster/crab up down 4", gp.tileSize, gp.tileSize);
            left1 = setup("/monster/crab left right 3", gp.tileSize, gp.tileSize);
            left2 = setup("/monster/crab left right 4", gp.tileSize, gp.tileSize);
            right1 = setup("/monster/crab left right 3", gp.tileSize, gp.tileSize);
            right2 = setup("/monster/crab left right 4", gp.tileSize, gp.tileSize);
            down1 = setup("/monster/crab up down 3", gp.tileSize, gp.tileSize);
            down2 = setup("/monster/crab up down 4", gp.tileSize, gp.tileSize);
        }
    }
    public void move(){
        Random rand = new Random();
        antiFidgetSpin ++;

        if (antiFidgetSpin >= rand.nextInt(50, 750 )){

            int i = rand.nextInt(100)+1;

            if (i <= 25){
                direction = "up";
            }
            else if (i <= 50){
                direction = "down";
            }
            else if (i <= 75){
                direction = "left";
            }
            else if (i <= 100){
                direction = "right";
            }
            antiFidgetSpin = 0;
        }
    }
    public void damageReaction() {
        antiFidgetSpin = 0;
        switch (gp.player.direction){
            case "up": direction = "down"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
            case "down": direction = "up"; break;
        }


        //direction = gp.player.direction;
        //onPath = true; // gets aggro
    }
}
