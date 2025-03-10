package objects;

import Main.PanelSettings;
import entitys.Entity;

public class OBJ_start_armour extends Entity {
    PanelSettings gp;
    public OBJ_start_armour(PanelSettings gp) {
        super(gp);
        this.gp = gp;
        name = "start_armour";
        defense = 0;

    }
}
