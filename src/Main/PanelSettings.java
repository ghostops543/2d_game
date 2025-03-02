package Main;
import entitys.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
public class PanelSettings extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16;// 16x16 tile
    final int scale = 3;//3 x
    public final int tileSize = originalTileSize * scale;//3x16 = 48x48 tiles
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    //world settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;
    public final int WorldWidth = tileSize * maxWorldCol;
    public final int WorldHeight = tileSize * maxWorldRow;

    //fps
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    TileManager tileM = new TileManager(this);


    public PanelSettings() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);// better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps; // 60 fps
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
                timer  += (currentTime - lastTime);
                lastTime = currentTime;

                delta = delta / 1000000;

                if (delta < 0){
                    delta = 0;
                }

                Thread.sleep((long) delta);
                drawcount ++;
                nextDrawTime += drawInterval;
                if (timer >= 1000000000) {
                    System.out.println("FPS: " + fps);
                    drawcount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void update() {
        player.update();
        tileM.update();


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();//saves memory
    }
}
