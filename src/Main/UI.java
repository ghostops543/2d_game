package Main;

import entitys.Entity;
import entitys.Player;
import objects.OBJ_Heart;
import objects.OBJ_revolver;
import objects.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    PanelSettings gp;
    Graphics2D g2;
    Font serif_20;
    BufferedImage revolver1;
    BufferedImage heart0, heart1, heart2,heart3, heart4, heart5;
    BufferedImage inv_frame;
    BufferedImage inv_frame_hover;
    public UI(PanelSettings gp) {
        this.gp = gp;
        serif_20 = new Font("Serif", Font.BOLD, 20);
        SuperObject revolver = new OBJ_revolver(gp);
        revolver1 = revolver.image;
        SuperObject heart = new OBJ_Heart(gp);
        heart5=heart.image5;
        heart4=heart.image4;
        heart3=heart.image3;
        heart2=heart.image2;
        heart1=heart.image1;
        heart0=heart.image;
//        OBJ_revolver revolver = new OBJ_revolver(gp);
//        revolverImage = revolver.image;
        try {

            inv_frame = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/inventory frame.png")));
            inv_frame_hover = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/UI/inventory frame hover.png")));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(serif_20);
        g2.setColor(Color.black);
        if (gp.gameState == gp.playState) {
            switch (gp.player.life) {
                case 0:
                    g2.drawImage(heart0, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;
                case 1:
                    g2.drawImage(heart1, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;
                case 2:
                    g2.drawImage(heart2, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;
                case 3:
                    g2.drawImage(heart3, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;
                case 4:
                    g2.drawImage(heart4, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;
                case 5:
                    g2.drawImage(heart5, 15, 5, gp.tileSize * 5, gp.tileSize, null);
                    break;

            }
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
                for (int i = 0; i < gp.player.inventory.length; i++) {
                    if (Objects.equals(gp.player.inventory[i], "revolver")) {
                        switch (i) {
                            case 0:
                                g2.drawImage(revolver1, 15, 25, gp.tileSize, gp.tileSize, null);
                                break;
                            case 1:
                                g2.drawImage(revolver1, 65, 25, gp.tileSize, gp.tileSize, null);
                                break;
                            case 2:
                                g2.drawImage(revolver1, 115, 25, gp.tileSize, gp.tileSize, null);
                                break;
                            case 3:
                                g2.drawImage(revolver1, 165, 25, gp.tileSize, gp.tileSize, null);
                                break;
                            case 4:
                                g2.drawImage(revolver1, 215, 25, gp.tileSize, gp.tileSize, null);
                                break;
                        }
                    }
                }
            }
            if (gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
        }
    }
    public void drawPauseScreen () {
        String text = "PAUSED";
        int x = getX(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        }
    public int getX (String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
        }
}
