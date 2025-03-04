package entitys;

import Main.PanelSettings;

public class Projectile extends Entity{
    Entity user;

    public Projectile(PanelSettings gp){
        super(gp);
    }

    public void set(int worldx, int worldy, String direction, boolean alive, Entity user){

        this.worldx = worldx;
        this.worldy = worldy;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.fireDistance;

    }
    public void update(){
        switch(direction){
            case "up": worldy -= velocity; break;
            case "down": worldy += velocity; break;
            case "left": worldx -= velocity; break;
            case "right": worldx += velocity; break;
        }
        life--;
        if(life <= 0){
            alive = false;
        }
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
