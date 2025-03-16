package objects;

import Main.PanelSettings;
import entitys.Projectile;

public class OBJ_Squib_bullet extends Projectile {
    PanelSettings gp;
    public OBJ_Squib_bullet(PanelSettings gp) {
        super(gp);

        this.gp = gp;
        name = "Squib Bullet";
        alive = false;
        attackDamage = 2;
        getImage();

    }
    public void getImage() {
        up1 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        up2 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        down1 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        down2 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        left1 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        left2 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        right1 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
        right2 =setup("projectiles/squib bullet",gp.tileSize,gp.tileSize);
    }
}
