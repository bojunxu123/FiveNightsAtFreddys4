package org.fivenightsatfreddys4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Audio implements Playable {

    private Clip clip;

    public Audio(String name) {
        URL audioFile = getClass().getResource("/audio/" + name);
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

    public void play() {
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }

}
