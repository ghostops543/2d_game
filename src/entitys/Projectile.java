package entitys;

import Main.PanelSettings;

public class Projectile extends Entity{
    Entity user;
    private double Xdif;
    private double Ydif;

    public Projectile(PanelSettings gp){
        super(gp);
    }

    public void set(int worldx, int worldy, boolean alive, Entity user){

        this.worldx = worldx;
        this.worldy = worldy;
        this.Xdif = gp.xDif;
        this.Ydif = gp.yDif;
        this.alive = alive;
        this.user = user;
        this.life = gp.player.currentWeapon.fireDistance;

    }
    public void update() {

        if (user == gp.player) {
            int monsterIndex = gp.cDetection.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, gp.player.currentWeapon.attackDamage);
                alive = false;

            }
            worldy += Ydif* gp.player.currentWeapon.velocity;
            worldx += Xdif* gp.player.currentWeapon.velocity;
        }
        if (user != gp.player) {
            boolean contactPlayer = gp.cDetection.checkPlayer(this);
            if (contactPlayer && !gp.player.invincibility) {
                damagePlayer(attackDamage);
                alive = false;
            }
            worldy += Ydif * user.velocity;
            worldx += Xdif * user.velocity;
        }

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
    public boolean haveAmmo(Entity user) {
        boolean haveAmmo = false;
        return haveAmmo;
    }
    public void subtractAmmo(Entity user){
    }
}
