package objects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class OBJ_revolver extends SuperObject{

    public OBJ_revolver(){
        name = "revolver";
        int firerate = 1;
        int bullets = 1;
        int capacity = 6;
        double reloadTime= .8;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/objects/revolver.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
