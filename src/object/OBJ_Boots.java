package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {

    public OBJ_Boots(){

        name = "Boots";
        String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/objects/";

        try {
            
            image = ImageIO.read(new File(basePath + "boots.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
