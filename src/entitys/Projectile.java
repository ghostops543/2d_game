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
        this.life = fireDistance;

    }
    public void update() {

        if (user == gp.player) {
            int monsterIndex = gp.cDetection.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, gp.player.currentWeapon.attackDamage);
                alive = false;
            }
        }
        if (user != gp.player) {

        }
        worldy += gp.yDif* gp.player.currentWeapon.velocity;
        worldx += gp.xDif* gp.player.currentWeapon.velocity;
//        System.out.println(worldx + " " + worldy);


        life--;
        if (life <= 0) {
            alive = false;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
