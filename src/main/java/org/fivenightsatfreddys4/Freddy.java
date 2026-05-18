package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.io.File;

public class Freddy extends Animatronic {
    private static final String NAME = "Freddy";
    private static final File JUMPSCARE_IN_ROOM_FRAMES = new File("src/main/resources/Freddy/Jumpscare/InRoom");
    private static final File JUMPSCARE_BED_FRAMES = new File("src/main/resources/Freddy/Jumpscare/Bed");
    private int currentProgress;

    public Freddy(int aggressionLevel){
        this.name = NAME;
        currentPos = Position.BED;
        this.aggressionLevel = aggressionLevel;
    }

    private FrameHandler frameHandler = new FrameHandler() {
        @Override
        public void onFrame(BufferedImage frame) {
            // Hook this to your renderer; default is a no-op.
        }
    };


    public void setFrameHandler(FrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    public boolean movementOpportunity(){
        currentProgress+= aggressionLevel;
        return true;
    }

    public void jumpscareInRoom(){
        FramePlayer.playAtFps(JUMPSCARE_IN_ROOM_FRAMES, 24, frameHandler);
    }

    public void jumpscareBed(){
        FramePlayer.playAtFps(JUMPSCARE_BED_FRAMES, 24, frameHandler);
    }
}
