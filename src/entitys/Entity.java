package entitys;

import Main.PanelSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldx, worldy;

    //item attributes
    public int weaponDamage;
    public int fireRate;
    public int magazineSize;
    public int bulletCount;
    public int bulletSpeed;

    //player attributes
    public String name;
    public int speed;
    public int maxLife = 5;
    public int life;
    public int coin;
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
}
