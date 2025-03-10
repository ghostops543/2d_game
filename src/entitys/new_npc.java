package entitys;

import Main.PanelSettings;

import java.util.Random;

public class new_npc extends Entity{
    PanelSettings gp;
    public new_npc(PanelSettings gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){//gets sprite info
        up1 = setup("npc/roly up1",gp.tileSize, gp.tileSize);
        up2 = setup("npc/roly up2",gp.tileSize, gp.tileSize);
        down1 = setup("npc/roly down1",gp.tileSize, gp.tileSize);
        down2 = setup("npc/roly down2",gp.tileSize, gp.tileSize);
        left1 = setup("npc/roly left1",gp.tileSize, gp.tileSize);
        left2 = setup("npc/roly left2",gp.tileSize, gp.tileSize);
        right1 = setup("npc/roly right1",gp.tileSize, gp.tileSize);
        right2 = setup("npc/roly right2",gp.tileSize, gp.tileSize);
    }
    public void move(){
        Random rand = new Random();
        antiFidgetSpin ++;

        if (antiFidgetSpin >= rand.nextInt(50, 750 )){

        int i = rand.nextInt(100)+1;

        if (i <= 25){
            direction = "up";
        }
        else if (i <= 50){
            direction = "down";
        }
        else if (i <= 75){
            direction = "left";
        }
        else if (i <= 100){
            direction = "right";
        }
        antiFidgetSpin = 0;
        }
    }
    public void setDialogue(){
        dialogues[0]= "hello my name is rollie.";
        dialogues[1]= "I think i have heard of you.";
        dialogues[2]= "Have you seen my shop";
        dialogues[3]= "have you met my sister?.";
        dialogues[4]= "I know what you did... \n just kidding how are you?";
        dialogues[5]= "have you seen the squibs,\n they give me the heebie jeebies.";

    }
    public void speak(){
        if (dialogues[dialogueIndex]==null){
            dialogueIndex= 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
            case "down":
                direction = "up";
                break;
        }

    }

}
