package Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    PanelSettings gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shoot;// controls movement
    public boolean inv1, inv2, inv3, inv4, inv5;//lets players move to there 5 inventory slots
    public boolean debug, pause;

    public KeyHandler(PanelSettings gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_1) {
            inv1 = true;
        }
        if (code == KeyEvent.VK_2) {
            inv2 = true;
        }
        if (code == KeyEvent.VK_3) {
            inv3 = true;
        }
        if (code == KeyEvent.VK_4) {
            inv4 = true;
        }
        if (code == KeyEvent.VK_5) {
            inv5 = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            shoot = true;
        }
        if (code == KeyEvent.VK_P) {
            if (!debug){
                debug = true;
            }
            else if(debug){
                debug = false;
            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_1) {
            inv1 = false;
        }
        if (code == KeyEvent.VK_2) {
            inv2 = false;
        }
        if (code == KeyEvent.VK_3) {
            inv3 = false;
        }
        if (code == KeyEvent.VK_4) {
            inv4 = false;
        }
        if (code == KeyEvent.VK_5) {
            inv5 = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }
}
