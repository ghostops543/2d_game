package Main;

import entitys.Entity;
import entitys.Player;
import objects.HUD_crossHair;
import objects.OBJ_Heart;
import objects.OBJ_revolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    PanelSettings gp;
    Graphics2D g2;
    Font serif_20;
    BufferedImage item;
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
    public int[] invIndex= {0,0};
    public int crossHairCounter = 0;
    public int crossHairNum;
    int spin=0;



    public UI(PanelSettings gp) {
        this.gp = gp;
        InputStream is = getClass().getResourceAsStream("/font/cambriab.ttf");
        serif_20 = new Font("Cambria", Font.PLAIN, 20);


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
        crossHairCounter++;
        if (crossHairCounter < 60) {
            crossHairNum = 0;
        }
        else if (crossHairCounter < 120) {
            crossHairNum = 1;
        }
        else if (crossHairCounter < 180) {
            crossHairNum = 2;
        }
        else {
            crossHairCounter = 0;
        }

        g2.setFont(serif_20);
        g2.setColor(Color.black);
        if (gp.gameState == gp.playState) {
           drawHud();
           drawCrosshair();
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
            overlayInventory();
        }



    }
    public void drawHud(){
        Entity revolver = new OBJ_revolver(gp);
        item = revolver.down1;
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
                            g2.drawImage(item, 15, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 1:
                            g2.drawImage(item, 65, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 2:
                            g2.drawImage(item, 115, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 3:
                            g2.drawImage(item, 165, 25, gp.tileSize, gp.tileSize, null);
                            break;
                        case 4:
                            g2.drawImage(item, 215, 25, gp.tileSize, gp.tileSize, null);
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
        int rectX =gp.screenWidth/2-200;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        Color c;

        text = "New Game";
        x = getX(text);
        y += gp.tileSize*4;

        if(gp.mouseX >= rectX && gp.mouseX <= rectX+400){
            if(gp.mouseY >= y+12-gp.tileSize && gp.mouseY <= y+12-gp.tileSize+40) {
                commandNum = 0;
            }
        }
        if(commandNum == 0) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(rectX, y+12-gp.tileSize, 400, 40, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.screenWidth/2-195, y+14-gp.tileSize, 390, 35, 25 , 25);
        g2.drawString(text, x, y);




        text = "Continue Game";
        x = getX(text);
        y += gp.tileSize;
        if(gp.mouseX >= rectX && gp.mouseX <= rectX+400){
            if(gp.mouseY >= y+12-gp.tileSize && gp.mouseY <= y+12-gp.tileSize+40) {
                commandNum = 1;
            }
        }
        if(commandNum == 1) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(rectX, y+12-gp.tileSize, 400, 40, 35 , 35);
        c = new Color(255,255,255, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.screenWidth/2-195, y+14-gp.tileSize, 390, 35, 25 , 25);

        g2.drawString(text, x, y);
        text = "Exit";
        x = getX(text);
        y += gp.tileSize;
        if(gp.mouseX >= rectX && gp.mouseX <= rectX+400){
            if(gp.mouseY >= y+12-gp.tileSize && gp.mouseY <= y+12-gp.tileSize+40) {
                commandNum = 2;
            }
        }
        if(commandNum == 2) {
            c = new Color(105, 105, 105, 150);
        }
        else{
            c = new Color(0, 0, 0, 200);
        }
        g2.setColor(c);
        g2.fillRoundRect(rectX, y+12-gp.tileSize, 400, 40, 35 , 35);
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
        int startx = framex + 20;
        int hotbarx = (gp.tileSize*5)+60;
        int squareSize = 36;
        int texty = framey + 20;
        final int lineHeight = 32;
        //hotbar squares could be a loop but i didnt want to rewrite it ugh
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        if (gp.mouseX >= hotbarx && gp.mouseX <= hotbarx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -5;
                invIndex[1] = 1;
            }
        }
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        if (gp.mouseX >= hotbarx && gp.mouseX <= hotbarx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -4;
                invIndex[1] = 1;
            }
        }
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        if (gp.mouseX >= hotbarx && gp.mouseX <= hotbarx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -3;
                invIndex[1] = 1;
            }
        }
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        if (gp.mouseX >= hotbarx && gp.mouseX <= hotbarx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -2;
                invIndex[1] = 1;
            }
        }
        texty += 10+squareSize;
        drawInvWindow(hotbarx, texty, squareSize, squareSize);
        if (gp.mouseX >= hotbarx && gp.mouseX <= hotbarx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -1;
                invIndex[1] = 1;
            }
        }
        //armour slot
        drawInvWindow(startx , texty, squareSize, squareSize);
        if (gp.mouseX >= startx && gp.mouseX <= startx + squareSize) {
            if(gp.mouseY >= texty && gp.mouseY <= texty + squareSize) {
                invIndex[0] = -1;
                invIndex[1] = 0;
            }
        }
        //stats
        int statsx = hotbarx + squareSize + 10;
        int statWidth = 350;
        int statHeight = texty-30;
        texty = framey + 20;
        drawInvWindow(statsx, texty, statWidth, statHeight);
        // stats words
        texty += lineHeight;
        statsx += 20;
        g2.setFont(g2.getFont().deriveFont(32f));
        g2.drawString("level: " + gp.player.level, statsx, texty);
        texty += lineHeight;
        g2.drawString("Defense: " + gp.player.currentArmor.defense, statsx, texty);
        texty += lineHeight;
        g2.drawString("Coins: " + gp.player.coin, statsx, texty);
        texty += lineHeight;
        g2.drawString("Exp: " + gp.player.exp, statsx, texty);
        //player view
        int playerWidth = gp.tileSize * 4;
        texty = framey + 20;
        drawInvWindow(startx, texty, statHeight, statHeight);
        g2.drawImage(gp.player.invPic,(statHeight/2)+ 20,(statHeight/2)+ 20, null);
        //storage
        //displays grid
        squareSize += 5;
        int storagey = 252 + 20 + squareSize;
        int storagex = startx;
        drawStorageWindow(storagex, storagey, squareSize, squareSize);
        for(int i = 0; i < 4; i++){
            storagex = startx;
            for(int j = 0; j < 15; j++) {
                drawStorageWindow(storagex, storagey, squareSize, squareSize);
                if (gp.mouseX >= storagex && gp.mouseX <= storagex + squareSize) {
                    if(gp.mouseY >= storagey && gp.mouseY <= storagey + squareSize) {
                        invIndex[0] = i;
                        invIndex[1] = j;
                    }
                }
                storagex += squareSize;
            }
            storagey += squareSize;
        }






    }
    public void overlayInventory(){
        //going to insert inventory items and hotbar over the inventory hud
        int xAdd = gp.tileSize ;
        int yAdd = 41;
        int y =315;
        int x = gp.tileSize + 22;
        Color c = new Color(105, 105, 105, 150);
        if (invIndex[0] >=0) {
            x += yAdd * invIndex[1];
            y += yAdd * invIndex[0];
            g2.setColor(c);
            g2.fillRect(x, y, yAdd, yAdd);// yAdd is the same at storage rect size
        } else if (invIndex[0] == -1 && invIndex[1] == 0) {
            x = gp.tileSize+23;
            y = gp.tileSize+24+(46*4);
            g2.fillRoundRect(x, y, 34, 34, 15, 15);
        } else if (invIndex[0] == -1 && invIndex[1] == 1) {
            x = (gp.tileSize*5)+63;
            y = (gp.tileSize * 5)+16;
            g2.fillRoundRect(x,y,34,34,15,15);
        } else if (invIndex[0] == -2 && invIndex[1] == 1) {
            x = (gp.tileSize*5)+63;
            y = (gp.tileSize * 4)+18;
            g2.fillRoundRect(x,y,34,34,15,15);
        } else if (invIndex[0] == -3 && invIndex[1] == 1) {
            x = (gp.tileSize*5)+63;
            y = (gp.tileSize * 3)+19;
            g2.fillRoundRect(x,y,34,34,15,15);
        } else if (invIndex[0] == -4 && invIndex[1] == 1) {
            x = (gp.tileSize*5)+63;
            y = (gp.tileSize * 2)+21;
            g2.fillRoundRect(x,y,34,34,15,15);
        } else if (invIndex[0] == -5 && invIndex[1] == 1) {
            x = (gp.tileSize*5)+63;
            y = gp.tileSize+23;
            g2.fillRoundRect(x,y,34,34,15,15);
        }

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
    public void drawCrosshair(){
        Entity crossHair = new HUD_crossHair(gp);

        if (crossHairNum ==0){
            item = crossHair.image1;
        } else if (crossHairNum ==1) {
            item = crossHair.image2;
        } else if (crossHairNum == 2) {
            item = crossHair.image3;
        }
//        if (gp.mouseMoving) {
//            spin -= 3;
//            if (spin == -360) {
//                spin = 0;
//            }
//            g2.rotate(Math.toRadians(spin), gp.mouseX, gp.mouseY);
//
//            Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(item,new Point(0,0),"crosshair");
//            gp.setCursor(c);
//            //g2.drawImage(item, gp.mouseX - (gp.tileSize / 2), gp.mouseY - (gp.tileSize / 2), gp.tileSize, gp.tileSize, null);
//        }
//        else{
            if (spin < 0){
                if (spin % 12 == 0) {
                    spin += 12;
                }else if(spin % 9 == 0){
                    spin += 9;
                } else if (spin % 6 == 0) {
                    spin += 6;
                } else if (spin % 3 == 0) {
                    spin += 3;
                }
            }
            g2.rotate(Math.toRadians(spin), gp.mouseX, gp.mouseY);
            Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(item,new Point(0,0),"crosshair");
            gp.setCursor(c);
            //g2.drawImage(item, gp.mouseX - (gp.tileSize / 2), gp.mouseY - (gp.tileSize / 2), gp.tileSize, gp.tileSize, null);


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
    public void drawStorageWindow(int x, int y, int width, int height) {
        Color c = new Color(100,100,100, 220);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x+2, y+2, width, height);
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
