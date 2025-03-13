package entitys;


import Main.KeyHandler;
import Main.PanelSettings;
import objects.OBJ_bullet;
import objects.OBJ_revolver;
import objects.OBJ_start_armour;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    PanelSettings gp;
    KeyHandler keyH;



    public final int screenx;
    public final int screeny;
    public int itemx, itemy;
    public int invNum;
    boolean debug = false;
    int stationary = 0;

    int fireRateTimer=0;




    public Player(PanelSettings gp, KeyHandler keyH){
        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenx = gp.screenWidth/2-(gp.tileSize/2);
        screeny = gp.screenHeight/2-(gp.tileSize/2);

        solidArea = new Rectangle(8, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultx = solidArea.x;
        solidAreaDefaulty = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getAttackImage();

    }
    public void setDefaultValues() {
        worldx = gp.tileSize * 5;
        worldy = gp.tileSize * 2;
        speed = 8;
        direction = "right";
        invNum = 1;
        gp.currentMap = 0;


        maxLife = 5;
        life = maxLife;
        coin = 0;
        level = 1;
        exp = 0;
        nextLevelExp = 5;
        currentWeapon = new OBJ_revolver(gp);
        currentArmor = new OBJ_start_armour(gp);

        attackDamage = currentWeapon.attackDamage;
        fireRate = currentWeapon.fireRate;
        velocity = currentWeapon.velocity;

        defense = currentArmor.defense;


    }

    public void getPlayerImage(){//gets sprite info
        up1 = setup("player/pixel shrimp u-1",gp.tileSize, gp.tileSize);
        up2 = setup("player/pixel shrimp u-2",gp.tileSize, gp.tileSize);
        down1 = setup("player/pixel shrimp d-1",gp.tileSize, gp.tileSize);
        down2 = setup("player/pixel shrimp d-2",gp.tileSize, gp.tileSize);
        left1 = setup("player/pixel shrimp l-1",gp.tileSize, gp.tileSize);
        left2 = setup("player/pixel shrimp l-2",gp.tileSize, gp.tileSize);
        right1 = setup("player/pixel shrimp r-1",gp.tileSize, gp.tileSize);
        right2 = setup("player/pixel shrimp r-2",gp.tileSize, gp.tileSize);
        invPic = setup("player/pixel shrimp r-1",gp.tileSize* 2, gp.tileSize*2);
    }
    public void getAttackImage(){
        attackUp1 = setup("player/weapon/shrimp_revolver_up1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("player/weapon/shrimp_revolver_up2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("player/weapon/shrimp_revolver_down1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("player/weapon/shrimp_revolver_down2",gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("player/weapon/shrimp_revolver_right1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("player/weapon/shrimp_revolver_right2",gp.tileSize*2, gp.tileSize);
        attackLeft1 = setup("player/weapon/shrimp_revolver_left1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("player/weapon/shrimp_revolver_left2",gp.tileSize*2, gp.tileSize);
    }

    public void update() {

        if (attacking) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || keyH.shoot) {// stops sprite moving when stationary
            if (keyH.upPressed) {
                direction = "up";
                solidArea.width = 30;
                solidArea.height = 48;
            } else if (keyH.downPressed) {
                direction = "down";
                solidArea.width = 30;
                solidArea.height = 48;
            } else if (keyH.leftPressed) {
                direction = "left";
                solidArea.width = 36;
                solidArea.height = 36;
            } else if (keyH.rightPressed) {
                direction = "right";
                solidArea.width = 36;
                solidArea.height = 36;
            }
            //check object collision
            collisionOn = false;

            gp.cDetection.checkTile(this);
            int objIndex = gp.cDetection.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cDetection.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cDetection.checkEntity(this, gp.monster);
            damaged(monsterIndex);

            //check event
            gp.eHandler.checkEvent();


            if (!collisionOn) {
                if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                    switch (direction) {
                        case "up":
                            if (keyH.upPressed) {
                                worldy -= speed;
                                break;
                            }
                        case "down":
                            if (keyH.downPressed) {
                                worldy += speed;
                                break;
                            }
                        case "left":
                            if (keyH.leftPressed) {
                                worldx -= speed;
                                break;
                            }
                        case "right":
                            if (keyH.rightPressed) {
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
                    } else {
                        stationary++;
                        if (stationary == 20) {
                            spriteNum = 1;
                            stationary = 0;
                        }
                    }
                }
            }
            //check tile collision

            //if collision is false player can move


            gp.keyH.enterPressed = false;

            if (keyH.inv1) {
                invNum = 1;
            } else if (keyH.inv2) {
                invNum = 2;
            } else if (keyH.inv3) {
                invNum = 3;
            } else if (keyH.inv4) {
                invNum = 4;
            } else if (keyH.inv5) {
                invNum = 5;
            }


        }
        if (invincibility) {
            invincibleCount++;
            if (invincibleCount >= 60) {
                invincibility = false;
                invincibleCount = 0;
            }
        }
        if (!canShoot) {
            fireBuffer++;
            if (fireBuffer > fireRate) {
                canShoot = true;
                fireBuffer = 0;
            }
        }
    }

    public void attacking(){
        spriteCounter ++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter>5 && spriteCounter <=25){
            spriteNum = 2;

            int currentWorldx = (int) worldx;
            int currentWorldy = (int) worldy;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up": worldy -=attackArea.height; break;
                case "down": worldy += attackArea.height; break;
                case "left": worldx -= attackArea.width; break;
                case "right": worldx += attackArea.width; break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.cDetection.checkEntity(this, gp.monster);
            worldx = currentWorldx;
            worldy = currentWorldy;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter>25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int index) {

        if (index != 999) {

            String objectName = gp.obj[gp.currentMap][index].name;
            gp.obj[gp.currentMap][index].use(this);
            hotbar[invNum-1] = objectName;
            gp.obj[gp.currentMap][index] = null;

        }
    }
    public void interactNPC(int i){
        if (i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }

        }
        else{
            if(gp.keyH.shoot|| gp.shoot){
                if(hotbar[invNum-1] != null && canShoot) {
                    canShoot = false;
                    attacking = true;
                    gp.playSoundEffect(1);
                    projectile = new OBJ_bullet(gp);
                    projectile.set((int) worldx, (int) worldy, direction, true, this);
                    gp.projectileList.add(projectile);


                }
            }
        }
        gp.keyH.enterPressed = false;
    }
    public void damaged(int i){
        if (i != 999) {
            if(!invincibility) {
                life -= gp.monster[gp.currentMap][i].attackDamage ;
                invincibility = true;
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attackDamage) {
        if(i != 999)
        {
            if(!gp.monster[gp.currentMap][i].invincibility)
            {
                int damage = attackDamage;
                if(damage <= 0 )
                {
                    damage = 1;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[gp.currentMap][i].invincibility = true;
                gp.monster[gp.currentMap][i].damageReaction();  //run away from player

                if(gp.monster[gp.currentMap][i].life <= 0)
                {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp()
    {
        while(exp >= nextLevelExp)
        {
            level++;
            exp = exp - nextLevelExp;          //Example: Your exp is 4 and nextLevelExp is 5. You killed a monster and receive 2exp. So, your exp is now 6. Your 1 extra xp will be recovered for the next level.
            if(level <= 4)
            {
                nextLevelExp = nextLevelExp + 4;   //Level 2 to 6: 4xp- 8xp- 12xp- 16xp- 20xp
            }
            else
            {
                nextLevelExp = nextLevelExp + 8;  //After Level 6: 28xp- 36xp- 44xp- 52xp- 60xp
            }
            maxLife += 2;

        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        int tempscreenx = screenx;
        int tempscreeny = screeny;

        switch(direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackUp1;
                        tempscreeny -= gp.tileSize;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                        tempscreeny -= gp.tileSize;}
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {
                        image = attackLeft1;
                        tempscreenx -= gp.tileSize;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                        tempscreenx -= gp.tileSize;

                    }
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        if(invincibility){
            if (invincibleCount % 5 == 0 || invincibleCount % 6 == 0|| invincibleCount % 7 == 0){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            else{
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
        g2.drawImage(image, tempscreenx, tempscreeny, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2.drawImage(holdItem, screenx, screeny, null);
        if(keyH.debug) {
            g2.setColor(Color.red);
            g2.drawRect(screenx + solidArea.x, screeny + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
