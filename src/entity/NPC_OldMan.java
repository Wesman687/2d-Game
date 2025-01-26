package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogue();
    }
    
    public void getImage() {        

        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
        
    }
    public void setDialogue(){
        dialoges[0] = "Hello, lad.";
        dialoges[1] = "So you've come to my beautiful island, \n are you here for the treasure?";
        dialoges[2] = "Many have tried and failed, \n i once to tried when I was a younger wizard.";
        dialoges[3] = "I have a few maps left over from my previous adventures, \n I could be talked into finding them for you.";
        dialoges[4] = "Find me after you've proven yourself..";

    }
    public void setAction(){

        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100)+1; 
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <=75){
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;

        }
       

    }
    public void speak(){
        if (dialoges[dialogIndex] == null) {
            dialogIndex=0;
        }
        gp.ui.currentDialog = dialoges[dialogIndex];
        dialogIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;

        }


    }
    
}
