package objects;

import Main.PanelSettings;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends SuperObject{
    PanelSettings gp;
    public OBJ_Heart(PanelSettings gp) {
        this.gp = gp;
        name = "Heart";
        try{
            image5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health full.png")));
            image4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health 4.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health 3.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health 2.png")));
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health 1.png")));
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health 0.png")));
            tool.scaleImage(image5, gp.tileSize, gp.tileSize);
            tool.scaleImage(image4, gp.tileSize, gp.tileSize);
            tool.scaleImage(image3, gp.tileSize, gp.tileSize);
            tool.scaleImage(image2, gp.tileSize, gp.tileSize);
            tool.scaleImage(image1, gp.tileSize, gp.tileSize);
            tool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}

