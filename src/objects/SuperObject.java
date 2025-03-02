package objects;

import Main.PanelSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldx, worldy;

    public void draw(Graphics2D g2, PanelSettings gp) {
        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        int screeny = worldy - gp.player.worldy + gp.player.screeny;

        if(worldx > gp.player.worldx - gp.player.screenx//only renders tile in player screen
                && worldx < gp.player.worldx + gp.player.screenx
                && worldy  > gp.player.worldy - gp.player.screeny
                && worldy  < gp.player.worldy + gp.player.screeny){
            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
        }

    }

}
