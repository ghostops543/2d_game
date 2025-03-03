package Main;

import objects.OBJ_revolver;

public class ObjectPlacment {
    PanelSettings gp;

    public ObjectPlacment(PanelSettings gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_revolver(gp);
        gp.obj[0].worldx = 2 * gp.tileSize;
        gp.obj[0].worldy = 2 * gp.tileSize;
    }

}
