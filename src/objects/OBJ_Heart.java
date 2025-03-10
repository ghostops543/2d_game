package objects;

import Main.PanelSettings;
import entitys.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {
    public OBJ_Heart(PanelSettings gp) {
        super(gp);
        name = "Heart";
        image5 = setup("UI/health full",gp.tileSize, gp.tileSize);
        image4 = setup("UI/health 4",gp.tileSize, gp.tileSize);
        image3 = setup("UI/health 3",gp.tileSize, gp.tileSize);
        image2 = setup("UI/health 2",gp.tileSize, gp.tileSize);
        image1 = setup("UI/health 1",gp.tileSize, gp.tileSize);
        image = setup("UI/health 0",gp.tileSize, gp.tileSize);

    }
}

