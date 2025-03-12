package Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    PanelSettings gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shoot, enterPressed;// controls movement
    public boolean inv1, inv2, inv3, inv4, inv5;//lets players move to there 5 hotbar slots
    public boolean debug, pause;

    public KeyHandler(PanelSettings gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        else if (gp.gameState == gp.inventoryState) {
            inventoryState(code);
        }
    }
    public void titleState(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.commandNum > 0) {
                gp.ui.commandNum--;
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.commandNum < 2) {
                gp.ui.commandNum++;
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNum == 1) {
                // later
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);

            }
        }
    }
    public void playState(int code){
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
        if (code == KeyEvent.VK_Q) {
            gp.gameState = gp.inventoryState;
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
            if (!debug) {
                debug = true;
            } else if (debug) {
                debug = false;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void inventoryState(int code){
        if(code == KeyEvent.VK_Q || code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
            gp.ui.invIndex[0] = 0;
            gp.ui.invIndex[1] = 0;
        }
        if(code == KeyEvent.VK_W) {
            if (gp.ui.invIndex[0]> -5) {
                if (gp.ui.invIndex[0] <= -1) {
                    if (gp.ui.invIndex[1] == 1) {
                        gp.ui.invIndex[0]--;
                    }
                }
                if(gp.ui.invIndex[0] == 0) {
                    gp.ui.invIndex[1] = 0;
                }
                if (gp.ui.invIndex[0] > -1) {
                    gp.ui.invIndex[0]--;
                }

                System.out.println( gp.ui.invIndex[0]+" "+ gp.ui.invIndex[1]);

            }
        }
        if(code == KeyEvent.VK_S) {
            if (gp.ui.invIndex[0]< 3) {
                gp.ui.invIndex[0]++;
                System.out.println( gp.ui.invIndex[0]+" "+ gp.ui.invIndex[1]);
            }
        }
        if(code == KeyEvent.VK_A) {
            if (gp.ui.invIndex[1] > 0) {
                if (gp.ui.invIndex[0] >= -5 && gp.ui.invIndex[0] <= -1) {
                    if (gp.ui.invIndex[0] == -1) {
                        if (gp.ui.invIndex[1] > 0) {
                            gp.ui.invIndex[1]--;
                        }
                    }
                } else {
                    gp.ui.invIndex[1]--;
                }
            }
            System.out.println( gp.ui.invIndex[0]+" "+ gp.ui.invIndex[1]);
        }
        if(code == KeyEvent.VK_D) {
            if (gp.ui.invIndex[1]< 14) {
                if (gp.ui.invIndex[0] >= -5 && gp.ui.invIndex[0] <= -1) {
                    if (gp.ui.invIndex[0] == -1) {
                        if (gp.ui.invIndex[1] < 1) {
                            gp.ui.invIndex[1]++;
                        }
                    }
                }
                else {
                    gp.ui.invIndex[1]++;
                }
                System.out.println( gp.ui.invIndex[0]+" "+ gp.ui.invIndex[1]);

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
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
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
