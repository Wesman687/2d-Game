package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest  extends SuperObject {
    
    public OBJ_Chest(){

        name = "Chest";
        String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/objects/";

        try {
            
            image = ImageIO.read(new File(basePath + "chest.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
