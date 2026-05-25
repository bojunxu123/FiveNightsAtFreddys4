package org.fivenightsatfreddys4;

import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sound {

    Clip clip;

    public Sound(String name) {
        URL audioFile = getClass().getResource("/audio/" + name);
        this.clip = clip;
    }

}
