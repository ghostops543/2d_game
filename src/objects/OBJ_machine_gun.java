package objects;

import Main.PanelSettings;
import entitys.Entity;
import entitys.Player;

public class OBJ_machine_gun extends Entity {
    public OBJ_machine_gun(PanelSettings gp) {
        super(gp);
        name = "machine gun";
        fireRate = 2;
        bulletCount = fireRate * 10;
        magazineSize = 30;
        ammoCost=1;
        reloadTime= .8;
        attackDamage = 1;
        velocity = 10;
        down1 = setup("/objects/revolver",gp.tileSize, gp.tileSize);
        collisionOn = true;
        fireDistance=100;
    }
    public boolean use(Player player) {

        player.hotbar[player.invNum-1] = "machine gun";
        return true;
    }
}


