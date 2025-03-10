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
    public OBJ_revolver(PanelSettings gp) {
        super(gp);
        name = "revolver";
        fireRate = 1;
        bulletCount = 1;
        magazineSize = 6;
        reloadTime= .8;
        attackDamage = 1;
        velocity = 3;
        down1 = setup("/objects/revolver",gp.tileSize, gp.tileSize);
        collisionOn = true;
    }
    public boolean use( Player player) {

        player.hotbar[player.invNum-1] = "revolver";
        return true;
    }
}
