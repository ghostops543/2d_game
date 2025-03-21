package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/sound/pistol shrimp theme.wav");
        soundURL[1] = getClass().getResource("/res/sound/effects/gunshot.wav");

    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){}
    }

    public void playSound(){
        clip.start();
    }

    public void loopSound(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSound(){
        clip.stop();
    }
}
