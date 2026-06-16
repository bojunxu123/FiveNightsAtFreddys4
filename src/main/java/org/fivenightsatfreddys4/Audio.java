package org.fivenightsatfreddys4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
/*
* Holds one sound that it can play
 */
public class Audio implements Playable {

    private Clip clip;

    /*
    automatically loads and preps the audio file based on its name
     */
    public Audio(String name) {
        URL audioFile = getClass().getResource("/audio/" + name + ".wav");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            this.clip = clip;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
    plays the audio
     */
    public void play() {
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }

}
