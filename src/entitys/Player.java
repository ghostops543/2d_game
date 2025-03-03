package entitys;


import Main.KeyHandler;
import Main.PanelSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    PanelSettings gp;
    KeyHandler keyH;



    public final int screenx;
    public final int screeny;
    public int invNum;




    public Player(PanelSettings gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenx = gp.screenWidth/2-(gp.tileSize/2);
        screeny = gp.screenHeight/2-(gp.tileSize/2);

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultx = solidArea.x;
        solidAreaDefaulty = solidArea.y;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){
        worldx = gp.tileSize * 24;
        worldy = gp.tileSize * 32;
        speed = 8;
        direction= "right";
        invNum = 1;

    }
    public void getPlayerImage(){//gets sprite info

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp u-1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp u-2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp d-1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp d-2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp l-1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp l-2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp r-1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pixel shrimp r-2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {// stops sprite moving when stationary
            spriteCounter++;//sprite updater
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }


            if (keyH.upPressed) {
                direction = "up";
                solidArea.x = 4 * 3;//4 pixles x the scale
                solidArea.y = 0;
                solidArea.width = 32;
                solidArea.height = 48;

            } else if (keyH.downPressed) {
                direction = "down";
                solidArea.x = 4 * 3;//4 pixles x the scale
                solidArea.y = 0;
                solidArea.width = 24;
                solidArea.height = 48;

            } else if (keyH.leftPressed) {
                direction = "left";
                solidArea.x = 2 * 3;
                solidArea.y = 2 * 3;
                solidArea.width = 36;
                solidArea.height = 36;

            } else if (keyH.rightPressed) {
                direction = "right";
                solidArea.x = 2 * 3;
                solidArea.y = 2 * 3;
                solidArea.width = 36;
                solidArea.height = 36;

            }


            //check tile collision
            collisionOn = false;
            gp.cDetection.checkTile(this);

            //check object collision
            int objIndex = gp.cDetection.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false player can move
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldy -= speed;
                        break;
                    case "down":
                        worldy += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;
                    case "right":
                        worldx += speed;
                        break;
                }
            }
        }
        if(keyH.inv1){
            invNum = 1;
        }
        else if(keyH.inv2){
            invNum = 2;
        }
        else if(keyH.inv3){
            invNum = 3;
        }
        else if(keyH.inv4){
            invNum = 4;
        }
        else if(keyH.inv5){
            invNum = 5;
        }


        }

    public void pickUpObject(int index) {

        if (index != 999) {

            String objectName = gp.obj[index].name;

            switch(objectName) {
                case "revolver":
                    switch(invNum) {
                        case 1:
                            inventory[0] = ("revolver");
                            break;
                        case 2:
                            inventory[1] = ("revolver");
                            break;
                        case 3:
                            inventory[2] = ("revolver");
                            break;
                        case 4:
                            inventory[3] = ("revolver");
                            break;
                        case 5:
                            inventory[4] = ("revolver");
                            break;
                    }
                    gp.obj[index] = null;
                    break;
                case "chest":
            }

        }

    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;

            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;

            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;

            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenx, screeny,gp.tileSize, gp.tileSize, null);
    }
}
