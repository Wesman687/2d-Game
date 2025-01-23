package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {
        
        name = "Key";
        String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/objects/";

        try {
            
            image = ImageIO.read(new File(basePath + "key.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
