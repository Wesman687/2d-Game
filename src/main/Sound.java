package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        String soundPath = "C:/Users/Wesma/OneDrive/Desktop/revature/2dGame/game/res/sound/";

        try {

            soundURL[0] = new File(soundPath + "BlueBoyAdventure.wav").toURI().toURL();
            soundURL[1] = new File(soundPath + "coin.wav").toURI().toURL();
            soundURL[2] = new File(soundPath + "powerup.wav").toURI().toURL();
            soundURL[3] = new File(soundPath + "unlock.wav").toURI().toURL();
            soundURL[4] = new File(soundPath + "fanfare.wav").toURI().toURL();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void play() {

        clip.start();

    }
    
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop() {

        clip.stop();

    }
}
