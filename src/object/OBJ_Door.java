package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    
    public OBJ_Door() {

        name = "Door";
        String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/objects/";

        try {
            
            image = ImageIO.read(new File(basePath + "door.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
