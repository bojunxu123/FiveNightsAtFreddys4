package org.fivenightsatfreddys4;

import java.util.Random;

/*
Holds multiple audios it randomly selects when played
 */
public class Sound implements Playable {

    private Audio[] audios;
    private Random random = new Random();

    public Sound(Audio... audios) {
        this.audios = audios;
    }

    /*
    Plays a random audio from the sound
     */
    public void play() {
        int index = random.nextInt(audios.length);
        audios[index].play();
    }
}
