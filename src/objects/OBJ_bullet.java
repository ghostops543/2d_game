package objects;

import Main.PanelSettings;
import entitys.Entity;
import entitys.Projectile;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_bullet extends Projectile {
    PanelSettings gp;
    public OBJ_bullet(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        name = "bullet";
        alive = false;

        getImage();
    }
    public void getImage() {
//        left1 = setup("projectiles/bullet left 1",gp.tileSize,gp.tileSize);
//        left2 = setup("projectiles/bullet left 2",gp.tileSize,gp.tileSize);
//        right1 = setup("projectiles/bullet right 1",gp.tileSize,gp.tileSize);
//        right2 =setup("projectiles/bullet right 2",gp.tileSize,gp.tileSize);
//        up1 = setup("projectiles/bullet up 1",gp.tileSize,gp.tileSize);
//        up2 = setup("projectiles/bullet up 2",gp.tileSize,gp.tileSize);
//        down1= setup("projectiles/bullet down 1",gp.tileSize,gp.tileSize);
//        down2 =setup("projectiles/bullet down 2",gp.tileSize,gp.tileSize);
        up1 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        up2 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        down1 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        down2 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        left1 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        left2 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        right1 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
        right2 =setup("projectiles/bullet tier 1",gp.tileSize,gp.tileSize);
    }
    public boolean haveAmmo(Entity user){
        boolean haveAmmo = false;
        if (user.ammoCount>=user.ammoCost){
            haveAmmo = true;
        }
        return haveAmmo;
    }
    public void subtractAmmo(Entity user){
        user.ammoCount -= user.ammoCost;
    }

}
