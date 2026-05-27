package org.fivenightsatfreddys4;

import java.util.Random;

public class Sound implements Playable {

    private Audio[] audios;
    private Random random = new Random();

    public Sound(Audio[] audios) {
        this.audios = audios;
    }


    @Override
    public void play() {
        int index = random.nextInt(audios.length);
        audios[index].play();
    }
}
