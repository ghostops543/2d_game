package Main;

import entitys.Entity;
import entitys.Player;
import objects.OBJ_revolver;
import objects.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    PanelSettings gp;

    Font serif_20;
    BufferedImage revolverImage;
    BufferedImage health;
    BufferedImage inv_frame;
    BufferedImage inv_frame_hover;
    public UI(PanelSettings gp) {
        this.gp = gp;
        serif_20 = new Font("Serif", Font.BOLD, 20);
        OBJ_revolver revolver = new OBJ_revolver();
        revolverImage = revolver.image;
        try {
            health = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/health full.png")));
            inv_frame = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/inventory frame.png")));
            inv_frame_hover = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/inventory frame hover.png")));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {



        g2.setFont(serif_20);
        g2.setColor(Color.white);
        //g2.drawString("inventory",50 ,50);
        g2.drawImage(health, 15, 5, gp.tileSize * 2, gp.tileSize, null);
        //creates inventory frame
        g2.drawImage(inv_frame, 15, 25, gp.tileSize, gp.tileSize, null);
        g2.drawImage(inv_frame, 65, 25, gp.tileSize, gp.tileSize, null);
        g2.drawImage(inv_frame, 115, 25, gp.tileSize, gp.tileSize, null);
        g2.drawImage(inv_frame, 165, 25, gp.tileSize, gp.tileSize, null);
        g2.drawImage(inv_frame, 215, 25, gp.tileSize, gp.tileSize, null);
        switch (gp.player.invNum) {
            case 1:
                g2.drawImage(inv_frame_hover, 15, 25, gp.tileSize, gp.tileSize, null);
                break;
            case 2:
                g2.drawImage(inv_frame_hover, 65, 25, gp.tileSize, gp.tileSize, null);
                break;
            case 3:
                g2.drawImage(inv_frame_hover, 115, 25, gp.tileSize, gp.tileSize, null);
                break;
            case 4:
                g2.drawImage(inv_frame_hover, 165, 25, gp.tileSize, gp.tileSize, null);
                break;
            case 5:
                g2.drawImage(inv_frame_hover, 215, 25, gp.tileSize, gp.tileSize, null);
                break;
        }
        if (gp.player.inventory != null) {
            for (int i = 0; i < gp.player.inventory.length ; i++) {
                if (Objects.equals(gp.player.inventory[i], "revolver")) {
                    switch (i) {
                        case 0:
                            g2.drawImage(revolverImage, 15, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 1:
                            g2.drawImage(revolverImage, 65, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 2:
                            g2.drawImage(revolverImage, 115, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 3:
                            g2.drawImage(revolverImage, 165, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 4:
                            g2.drawImage(revolverImage, 215, 25, gp.tileSize, gp.tileSize, null);
                            break;
                    }
                }
            }
        }
    }
}
