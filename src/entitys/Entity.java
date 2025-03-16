package entitys;

import Main.PanelSettings;
import Main.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    PanelSettings gp;
    public double worldx, worldy;
    public boolean invincibility= false;


    //item attributes
    public int attackDamage;
    public int fireRate;
    public int magazineSize;
    public int bulletCount;
    public int velocity = 2;
    public double reloadTime;
    public int fireDistance;
    public int ammoCount = magazineSize;
    public int ammoCost;

    //player attributes
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int defense= 0;
    public int coin;
    public int exp;
    public int nextLevelExp;
    public int level;
    public boolean alive = true;
    public Entity currentWeapon;
    public Entity currentArmor;
    public Projectile projectile;
    public boolean canShoot;
    public boolean dying = false;
    public boolean attacking;
    public boolean collision = false;
    public String[] hotbar = new String[5];
    public ArrayList<String> inventory = new ArrayList<String>();
    public String direction ="down";


    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public BufferedImage image, image1, image2, image3, image4, image5;


    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, invPic;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
            attackRight1, attackRight2, attackLeft1, attackLeft2;
    public BufferedImage holdItem;

    public int spriteNum = 1;
    boolean hpBarOn=false;

    public Rectangle solidArea= new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultx, solidAreaDefaulty;
    public boolean collisionOn = false;
    //counters
    public int antiFidgetSpin;
    public int invincibleCount = 0;
    public int spriteCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int fireBuffer = 0;

    //type
    public int type;
    public final int type_monster = 2;

    public Entity(PanelSettings gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String ImageName, int width, int height){
        Tools tool = new Tools();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + ImageName + ".png")));
            image = tool.scaleImage(image, width, height);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;

    }

    public int getScreenX() {

        int screenx = (int) (worldx - gp.player.worldx + gp.player.screenx);
        return screenx;
    }
    public int getScreenY() {
        int screeny = (int) (worldy - gp.player.worldy + gp.player.screeny);
        return screeny;
    }
    public void move(){}
    public void damageReaction(){}
    public void speak(){}
    public void checkCollision(){
        collisionOn = false;
        gp.cDetection.checkTile(this);
        gp.cDetection.checkObject(this, false);
        gp.cDetection.checkEntity(this, gp.npc);
        gp.cDetection.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cDetection.checkPlayer(this);
        if(this.type == type_monster && contactPlayer)
        {
            damagePlayer(attackDamage);
        }
    }
    public void damagePlayer(int attackDamage){
        if(!gp.player.invincibility) {
            int damage = attackDamage - gp.player.defense;;
            System.out.println(damage);
            System.out.println(attackDamage);
            if ((gp.player.life - damage)<= 0) {
                gp.player.life = 0;
            }
            else{
                gp.player.life -= damage;
            }
            gp.player.invincibility = true;
        }
    }
    public boolean use(Entity entity) {
        return true;
        //return "true" if you used the item and "false" if you failed to use it.
    }
    public void update() {
        move();
        checkCollision();

        if (!collisionOn) {

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
        spriteCounter++;//sprite updater
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (invincibility) {
            invincibleCount++;
            if (invincibleCount > 40){
                invincibility = false;
                invincibleCount = 0;
            }
        }
        if (fireBuffer <=20){
            fireBuffer++;
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;


        int tempScreenX = getScreenX();
        int tempScreenY = getScreenY();

        int screenx = (int) (worldx - gp.player.worldx + gp.player.screenx);
        int screeny = (int) (worldy - gp.player.worldy + gp.player.screeny);

        if(worldx > gp.player.worldx - gp.player.screenx//only renders tile in player screen
                && worldx < gp.player.worldx + gp.player.screenx
                && worldy  > gp.player.worldy - gp.player.screeny
                && worldy  < gp.player.worldy + gp.player.screeny){
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
                    break;

                case "left":
                    if (!attacking) //Normal walking sprites
                    {
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
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
                    break;
            }
            //monster hp
            if (type == type_monster && hpBarOn) {
                double oneScale = (double)gp.tileSize/maxLife;
                double hpBarValue = oneScale * life;
                g2.setColor(new Color(35,35,35));
                g2.fillRoundRect(screenx-1, screeny-16, gp.tileSize+2,12, 25, 10 );
                g2.setColor(new Color(255, 0, 80));
                g2.fillRoundRect(screenx, screeny - 15, (int)hpBarValue, 10,25 ,10);
                hpBarCounter++;
                if (hpBarCounter >600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(invincibility){
                hpBarOn = true;
                hpBarCounter = 0;
                if (invincibleCount % 5 == 0 || invincibleCount % 6 == 0|| invincibleCount % 7 == 0){
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                }
                else{
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                }
                if (invincibleCount < 20){
                    damageReaction();
                }

            }
            if(dying){
                dyingAnimation(g2);

            }
            //g2.drawImage(image, screenx, screeny, null);
            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if(dyingCounter <= 5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 5 && dyingCounter <= 10) {

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > 10){
            dying = false;
            alive = false;
            gp.player.exp += exp;
            dyingCounter = 0;
        }
    }

}
