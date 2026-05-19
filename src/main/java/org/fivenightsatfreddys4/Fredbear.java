package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Consumer;

public class Fredbear extends Animatronic {
    private static final String NAME = "Fredbear";
    private static final File JUMPSCARE_FRAMES = new File("src/main/resources/Fredbear/Jumpscare");

    private Consumer<BufferedImage> frameHandler = frame -> {};

    public void setFrameHandler(Consumer<BufferedImage> frameHandler) {
        this.frameHandler = frameHandler;
    }

    public void jumpscare(){
        FramePlayer.playAtFps(JUMPSCARE_FRAMES, 24, frameHandler);
    }

    public boolean movementOpportunity() {
        return true;
    }
}
