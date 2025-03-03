package objects;

import Main.PanelSettings;
import entitys.Entity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class OBJ_revolver extends SuperObject{
    PanelSettings gp;
    public OBJ_revolver(PanelSettings gp) {
        this.gp = gp;
        name = "revolver";
        int fireRate = 1;
        int bulletCount = 1;
        int magazineSize = 6;
        double reloadTime= .8;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/objects/revolver.png")));
            tool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
