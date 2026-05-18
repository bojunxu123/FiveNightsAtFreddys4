package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.io.File;

public class Fredbear extends Animatronic {
    private static final String NAME = "Fredbear";
    private static final File JUMPSCARE_FRAMES = new File("src/main/resources/Fredbear/Jumpscare");
    private FrameHandler frameHandler = new FrameHandler() {
        @Override
        public void onFrame(BufferedImage frame) {
            // Hook this to your renderer; default is a no-op.
        }
    };

    public void setFrameHandler(FrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    public void jumpscare(){
        FramePlayer.playAtFps(JUMPSCARE_FRAMES, 24, frameHandler);
    }

    public boolean movementOpportunity() {
        return true;
    }
}
