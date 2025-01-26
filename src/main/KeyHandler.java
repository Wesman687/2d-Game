package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.Entity;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    // Debug
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Play State
        if (gp.gameState == gp.playState) {
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
            if (code == KeyEvent.VK_P) {
                if (gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                }                 
            }
            //Debug
            if (code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }

        }
        //Pause State
        if (gp.gameState == gp.pauseState){
            if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }

        }
        //dialog state
        if (gp.gameState == gp.dialogState){
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
                gp.currentNPC = null;
            }
            if(code == KeyEvent.VK_SPACE){
                gp.currentNPC.speak();
                
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("");
    }

    
}
