package main;
import javax.swing.JPanel;
import object.SuperObject;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class GamePanel extends JPanel implements Runnable {

    final int orginalTileSize = 16;
    final int scale = 3;

    public final int tileSize = orginalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public boolean developerMode = true;
    public boolean developmentMode = true;
    public boolean treasureHunting = false;
    public boolean rpg = true;
    
    public Entity currentNPC;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);    
    Thread gameThread;

    //Entity and object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void setupGame(){

        aSetter.setObject();
        if (rpg) {
            aSetter.setNPC();
        }
        playMusic(0);
        stopMusic();
        gameState = playState;

    }

    public void startGameThread() {
        gameThread = new  Thread(this);
        gameThread.start();
    }

    public void stopGameThread() {
        gameThread = null;
    }
    
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }         
        }
    }
    public void update(){
        if (gameState == playState){
            player.update();

            for(int i =0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState){
            // nothing for now
        }
        

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();

        }

        tileM.draw(g2);
        
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        if (rpg){
            for (int i = 0;i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
        }

        player.draw(g2);

        ui.draw(g2);

        // Debug
        if (keyH.checkDrawTime == true) {
            developerMode = true;
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time"+ passed, 10, 400);
            System.out.println("Draw Time:" + passed);
        } else {
            developerMode = false;
        }

        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){

        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }

}
