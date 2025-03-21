package monster;

import Main.PanelSettings;
import entitys.Entity;

import java.util.Random;

public class MON_Squib extends Entity {
        PanelSettings gp;
    public MON_Squib(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        name = "Squib";
        type = type_monster;
        speed = 1;
        maxLife = 6;
        life = maxLife;
        attackDamage=1;
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
        up1 = setup("/monster/squib up1",gp.tileSize, gp.tileSize);
        up2 = setup("/monster/squib up2",gp.tileSize, gp.tileSize);
        left1 = setup("/monster/squib left1",gp.tileSize, gp.tileSize);
        left2 = setup("/monster/squib left2",gp.tileSize, gp.tileSize);
        right1 = setup("/monster/squib right1",gp.tileSize, gp.tileSize);
        right2 = setup("/monster/squib right2",gp.tileSize, gp.tileSize);
        down1 = setup("/monster/squib down1",gp.tileSize, gp.tileSize);
        down2 = setup("/monster/squib down2",gp.tileSize, gp.tileSize);
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
