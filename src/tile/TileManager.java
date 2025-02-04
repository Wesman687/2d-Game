package tile;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    public int mapVersion = 1;
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        if (mapVersion == 0) {
            loadMap("world01.txt");
        } else if (mapVersion == 1){
            loadMap("worldv2.txt");
        }
    }
    public void getTileImage(){
        
        if (mapVersion == 0){            

            setup(0, "grass", false);
            setup(1, "wall", true);
            setup(2, "water", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "sand", false);

        } else if (mapVersion == 1) {

            setup(0, "grass00", false);
            setup(1, "grass00", false);
            setup(2, "grass00", false);
            setup(3, "grass00", false);
            setup(4, "grass00", false);
            setup(5, "grass00", false);
            setup(6, "grass00", false);
            setup(7, "grass00", false);
            setup(8, "grass00", false);
            setup(9, "grass00", false);

        // Real tiles
            setup(10, "grass00", false);
            setup(11, "grass01", false);
            setup(12, "water00", true);
            setup(13, "water01", true);
            setup(14, "water02", true);
            setup(15, "water03", true);
            setup(16, "water04", true);
            setup(17, "water05", true);
            setup(18, "water06", true);
            setup(19, "water07", true);
            setup(20, "water08", true);
            setup(21, "water09", true);
            setup(22, "water10", true);
            setup(23, "water11", true);
            setup(24, "water12", true);
            setup(25, "water13", true);
            setup(26, "road00", false);
            setup(27, "road01", false);
            setup(28, "road02", false);
            setup(29, "road03", false);
            setup(30, "road04", false);
            setup(31, "road05", false);
            setup(32, "road06", false);
            setup(33, "road07", false);
            setup(34, "road08", false);
            setup(35, "road09", false);
            setup(36, "road10", false);
            setup(37, "road11", false);
            setup(38, "road12", false);
            setup(39, "earth", false);
            setup(40, "wall", true);
            setup(41, "tree", true);


        }

        
    }
    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();
        tile[index] = new Tile();

        try {
            if (gp.developmentMode) {

                String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res";            
                tile[index].image = ImageIO.read(new File(basePath + "/tiles/" + imageName + ".png"));
            } else {
                String resourcePath = "/res/tiles/" + imageName + ".png";
                InputStream is = getClass().getResourceAsStream(resourcePath);
                
                if (is == null) {
                    throw new FileNotFoundException("Resource not found: " + resourcePath);
                }
                tile[index].image = ImageIO.read(is);
            }           
            
        } catch (Exception e) {
            e.printStackTrace();            
        }

        tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
        tile[index].collision = collision;
        

    }
    public void loadMap(String filePath){
        try {
            String basePath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/maps/";        
            
            FileInputStream is = new FileInputStream(basePath + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){                
                
                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldCol) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (gp.player.screenX > gp.player.worldX){
                screenX = worldX;
            }
            if (gp.player.screenY > gp.player.worldY) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if (rightOffset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffSet = gp.screenHeight - gp.player.screenY;
            if (bottomOffSet > gp.worldHeight - gp.player.worldY) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX  && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX  && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY  && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            else if (gp.player.screenX > gp.player.worldX || 
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    bottomOffSet > gp.worldHeight - gp.player.worldY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }

            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
