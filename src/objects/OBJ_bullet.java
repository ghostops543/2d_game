package objects;

import Main.PanelSettings;
import entitys.Projectile;

public class OBJ_bullet extends Projectile {
    PanelSettings gp;
    public OBJ_bullet(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        name = "fireball";
        velocity = 5;
        maxLife = 80;
        Life = maxLife;
        attack = 2;
    }

}
