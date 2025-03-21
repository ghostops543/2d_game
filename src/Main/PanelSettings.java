package Main;
import entitys.Entity;
import entitys.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class PanelSettings extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16;// 16x16 tile
    final int scale = 3;//3 x
    public final int tileSize = originalTileSize * scale;//3x16 = 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //world settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;
    public final int maxMap = 10;
    public int currentMap = 0;


    //fps
    int fps = 60;
    //system
    Sound music = new Sound();
    Sound SE = new Sound();
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    public ObjectPlacment oPlacer = new ObjectPlacment(this);
    public CollisionDetection cDetection = new CollisionDetection(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    //Map map = new Map(this);
    //entity
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][10];// can display up to 10 objects
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][10];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    //gamestate
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;


    public PanelSettings() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);// better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        oPlacer.setObject();
        oPlacer.setNPC();
        oPlacer.setMonster();
        //playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps; // 60 fps
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawcount = 0;

        while (gameThread != null) {
            //System.out.println("running gameThread");


            // 1 UPDATE: update information such as character positions
            update();
            //2 DRAW: draw the screen with the updated information
            repaint();

            try {//sets 60 fps
                double delta = nextDrawTime - System.nanoTime();
                currentTime = System.nanoTime();
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                delta = delta / 1000000;

                if (delta < 0) {
                    delta = 0;
                }

                Thread.sleep((long) delta);
                drawcount++;
                nextDrawTime += drawInterval;
                if (timer >= 1000000000) {
                    //System.out.println("FPS: " + fps);
                    drawcount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if (!monster[currentMap][i].alive ) {
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        if (gameState == pauseState) {

        }
        tileM.update();




    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //debug
        long drawStart = 0;
        if (keyH.debug) {

            drawStart = System.nanoTime();
        }

        if (gameState == titleState) {
            tileM.draw(g2);
            ui.draw(g2);
        }
        else{
            tileM.draw(g2);

            //adds player and other entitires to list
            entityList.add(player);

            for(int i = 0; i < npc.length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i =0; i< obj.length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i =0; i< monster.length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            //sorts entity list
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldy,e2.worldy);
                    return result;
                }
            });
            //draws entity list
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTY ENTITY LIST
            entityList.clear();
            //ui
            ui.draw(g2);

            //debug
            if (keyH.debug) {
                long drawend = System.nanoTime();
                long passed = drawend - drawStart;
                g2.setColor(Color.BLACK);
                g2.drawString("Draw time: " + passed, 10, 400);
                System.out.println("Draw time: " + passed);
            }

            g2.dispose();//saves memory
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.playSound();
        music.loopSound();
    }

    public void stopMusic() {
        music.stopSound();
    }

    public void playSoundEffect(int i) {
        SE.setFile(i);
        SE.playSound();
    }
}

