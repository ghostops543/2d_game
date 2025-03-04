package objects;

import Main.PanelSettings;
import entitys.Projectile;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_bullet extends Projectile {
    PanelSettings gp;
    public OBJ_bullet(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        name = "bullet";
        velocity = 10;
        fireDistance = 100;
        life = maxLife;
        attackDamage = 2;
        alive = false;
        getImage();
    }
    public void getImage() {
        left1 = setup("/res/projectiles/bullet left 1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/projectiles/bullet left 2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/projectiles/bullet right 1",gp.tileSize,gp.tileSize);
        right2 =setup("/res/projectiles/bullet right 2",gp.tileSize,gp.tileSize);
        up1 = setup("/res/projectiles/bullet up 1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/projectiles/bullet up 2",gp.tileSize,gp.tileSize);
        down1= setup("/res/projectiles/bullet down 1",gp.tileSize,gp.tileSize);
        down2 =setup("/res/projectiles/bullet down 2",gp.tileSize,gp.tileSize);
    }

}
