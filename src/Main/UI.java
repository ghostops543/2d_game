package Main;

import entitys.Entity;
import objects.OBJ_Heart;
import objects.OBJ_revolver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    PanelSettings gp;
    Graphics2D g2;
    Font serif_20;
    BufferedImage revolver1;
    BufferedImage heart0, heart1, heart2,heart3, heart4, heart5;
    BufferedImage inv_frame;
    BufferedImage inv_frame_hover;
    public int invHov;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    int messagecount = 0;
    public String currentDialogue = "";
    public int commandNum = 0;
    public Entity npc;

    public UI(PanelSettings gp) {
        this.gp = gp;
        InputStream is = getClass().getResourceAsStream("/font/cambriab.ttf");
        serif_20 = new Font("Cambria", Font.PLAIN, 20);
        Entity revolver = new OBJ_revolver(gp);
        revolver1 = revolver.down1;
        Entity heart = new OBJ_Heart(gp);
        heart5=heart.image5;
        heart4=heart.image4;
        heart3=heart.image3;
        heart2=heart.image2;
        heart1=heart.image1;
        heart0=heart.image;

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
           drawHud();
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.titleState) {
            drawTitle();
        }
        if (gp.gameState == gp.inventoryState) {
            drawInventory();
        }
    }
    public void drawHud(){
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
        //creates hotbar frame
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
        if (gp.player.hotbar != null) {
            for (int i = 0; i < gp.player.hotbar.length; i++) {
                if (Objects.equals(gp.player.hotbar[i], "revolver")) {
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
    }
    public void drawTitle() {

        //g2.setColor(new Color(50, 151, 173, 100));
        //g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
        String text = "Pistol Shrimp";
        int x = getX(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        Color c;

        text = "New Game";
        x = getX(text);
        y += gp.tileSize*4;
        if(commandNum == 0) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(gp.screenWidth/2-200, y+12-gp.tileSize, 400, 40, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.screenWidth/2-195, y+14-gp.tileSize, 390, 35, 25 , 25);
        g2.drawString(text, x, y);




        text = "Continue Game";
        x = getX(text);
        y += gp.tileSize;
        if(commandNum == 1) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(gp.screenWidth/2-200, y+12-gp.tileSize, 400, 40, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.screenWidth/2-195, y+14-gp.tileSize, 390, 35, 25 , 25);

        g2.drawString(text, x, y);
        text = "Exit";
        x = getX(text);
        y += gp.tileSize;
        if(commandNum == 2) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(gp.screenWidth/2-200, y+12-gp.tileSize, 400, 40, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.screenWidth/2-195, y+14-gp.tileSize, 390, 35, 25 , 25);

        g2.drawString(text, x, y);


    }
    public void drawInventory() {
        final int framey = gp.tileSize;
        final int framex = gp.tileSize;
        final int frameWidth = gp.tileSize *14;
        final int frameHeight = gp.tileSize *10;
        drawSubWindow(framex, framey, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32f));
        int textx = framex + 20;
        int hotbarx = (gp.tileSize*5)+50;
        int squareSize = 36;
        int texty = framey + 20;
        final int lineHeight = 32;
        //hotbar squares
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        //stats
        int statsx = hotbarx + squareSize + 20;
        int statWidth = 350;
        int statHeight = texty-30;
        texty = framey + 20;
        drawInvWindow(statsx, texty, statWidth, statHeight);
        //storage


//        g2.setFont(g2.getFont().deriveFont(48f));
//        g2.drawString("Inventory", getX("Inventory"), texty);
//        texty += lineHeight+ 10;
//        g2.setFont(g2.getFont().deriveFont(32f));
//        g2.drawString("level: " + gp.player.level, textx, texty);
//        texty += lineHeight;
//        g2.drawString("armour: ", textx, texty);
//        g2.setFont(g2.getFont().deriveFont(48f));
//        g2.drawString(gp.player.currentArmor.name, getX(gp.player.currentArmor.name), texty);
//        texty += lineHeight+ 10 ;
//        g2.setFont(g2.getFont().deriveFont(32f));
//        g2.drawString("defense: " + gp.player.currentArmor.defense, textx, texty);
//        texty += lineHeight;
//        g2.drawString("weapon: ", textx, texty);
//        g2.setFont(g2.getFont().deriveFont(48f));
//        g2.drawString(gp.player.currentWeapon.name, getX(gp.player.currentWeapon.name), texty);
//        texty += lineHeight + 10;
//        g2.setFont(g2.getFont().deriveFont(32f));
//        g2.drawString("Damage: " + gp.player.currentWeapon.attackDamage, textx, texty);
//        texty += lineHeight;




    }
    public void drawDialogueScreen(){
        //window
        int x=gp.tileSize * 2;
        int y=gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(g2.getFont().getSize()-2));
        x+=gp.tileSize;
        y+=gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x , y);
            y+= 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25 , 25);

    }
    public void drawInvWindow(int x, int y, int width, int height) {
        Color c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x+2, y+2, width, height, 15 , 15);

    }
    public void drawPauseScreen() {
        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96f));
        int x = getX(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        }
    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }
    public int getX (String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
        }
}
