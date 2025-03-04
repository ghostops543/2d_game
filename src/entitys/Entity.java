package entitys;

import Main.PanelSettings;
import Main.Tools;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    PanelSettings gp;
    public int worldx, worldy;

    //item attributes
    public int attackDamage;
    public int fireRate;
    public int magazineSize;
    public int bulletCount;
    public int velocity = 2;
    public double reloadTime;
    public int fireDistance;

    //player attributes
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int coin;
    public boolean alive = true;
    public Entity currentWeapon;
    public Projectile projectile;

    public boolean attacking;


    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage holdItem;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultx, solidAreaDefaulty;
    public boolean collisionOn = false;
    public String[] inventory = new String[5];

    public Entity(PanelSettings gp) {
        this.gp = gp;
    }

    public int getScreenX() {

        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        return screenx;
    }

    public int getScreenY() {
        int screeny = worldy - gp.player.worldy + gp.player.screeny;
        return screeny;
    }

    public void update() {
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        Tools uTool = new Tools();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);   //it scales to tilesize , will fix for player attack(16px x 32px) by adding width and height
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = getScreenX();
        int tempScreenY = getScreenY();
        switch (direction) {
            case "up":
                if (!attacking) //Normal walking sprites
                {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking)  //Attacking sprites
                {
                    tempScreenY = getScreenY() - up1.getHeight();    //Adjusted the player's position one tile to up. Explained why I did it at where I call attacking() in update().
                    if (spriteNum == 1) {
                        //image = attackUp1;
                    }
                    if (spriteNum == 2) {
                       // image = attackUp2;
                    }
                }
                break;

            case "down":
                if (!attacking) //Normal walking sprites
                {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking)  //Attacking sprites
                {
                    if (spriteNum == 1) {
                        //image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        //image = attackDown2;
                    }
                }
                break;

            case "left":
                if (!attacking) //Normal walking sprites
                {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking)  //Attacking sprites
                {
                    tempScreenX = getScreenX() - up1.getWidth();    //Adjusted the player's position one tile left. Explained why I did it at where I call attacking() in update().
                    if (spriteNum == 1) {
                        //image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                       // image = attackLeft2;
                    }
                }
                break;

            case "right":
                if (!attacking) //Normal walking sprites
                {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking)  //Attacking sprites
                {
                    if (spriteNum == 1) {
                        //image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        //image = attackRight2;
                    }
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
    }
}
