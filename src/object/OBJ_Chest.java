package object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest  extends SuperObject {
    GamePanel gp;
    
    public OBJ_Chest(GamePanel gp){
        this.gp = gp;        

        name = "Chest";

        try {
            if (gp.developmentMode) {
                String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/objects/";
                image = ImageIO.read(new File(basePath + "chest.png"));
                
            } else {

                String resourcePath = "/res/objects/" + "chest.png";
                InputStream is = getClass().getResourceAsStream(resourcePath);
                
                if (is == null) {
                    throw new FileNotFoundException("Resource not found: " + resourcePath);
                }
                image = ImageIO.read(is);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
