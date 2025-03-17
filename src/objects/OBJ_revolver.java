package objects;

import Main.PanelSettings;
import entitys.Entity;
import entitys.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class OBJ_revolver extends Entity {
    PanelSettings gp;
    public OBJ_revolver(PanelSettings gp) {
        super(gp);
        this.gp = gp;
        name = "revolver";
        fireRate = 4;
        bulletCount = 10;
        magazineSize = 6;
        ammoCost =1;
        reloadTime= .8;
        attackDamage = 10;
        velocity = 10;
        down1 = setup("/objects/revolver",gp.tileSize, gp.tileSize);
        collisionOn = true;
        fireDistance=100;
    }
    public boolean use(Player player) {

        player.hotbar[player.invNum-1] = "revolver";
        gp.ui.hotbarImg[player.invNum-1] = down1;
        return true;
    }
}
