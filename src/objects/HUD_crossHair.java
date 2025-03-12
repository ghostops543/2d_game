package objects;

import Main.PanelSettings;
import entitys.Entity;

import javax.swing.text.Position;
import java.awt.image.BufferedImage;

public class HUD_crossHair extends Entity {
    public HUD_crossHair(PanelSettings gp) {
        super(gp);
        image1 = setup("/objects/crossHair 1", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/crossHair 2", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/crossHair 3", gp.tileSize, gp.tileSize);

    }
    public void move(){
        antiFidgetSpin++;
    }
}
