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
    public int velocity;
    public double reloadTime;
    public int fireDistance;

    //player attributes
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int coin;
    public boolean alive;
    public Entity currentWeapon;
    public Projectile projectile;




    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultx, solidAreaDefaulty;
    public boolean collisionOn = false;
    public String[] inventory = new String[5];

    public Entity(PanelSettings gp){

    }

    public void update() {
    }
    public BufferedImage setup(String imagePath, int width, int height)
    {
        Tools uTool = new Tools();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image,width,height);   //it scales to tilesize , will fix for player attack(16px x 32px) by adding width and height
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
}}
