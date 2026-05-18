package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.io.File;

public class Bonnie extends Animatronic {
    private static final String NAME = "Bonnie";
    private static final File JUMPSCARE_DOOR_FRAMES = new File("src/main/resources/Bonnie/Jumpscare/Door");
    private static final File JUMPSCARE_IN_ROOM_FRAMES = new File("src/main/resources/Bonnie/Jumpscare/InRoom");

    private FrameHandler frameHandler = new FrameHandler() {
        @Override
        public void onFrame(BufferedImage frame) {
            // Hook this to your renderer; default is a no-op.
        }
    };

    public Bonnie(Position startingPos) {
        this.name = NAME;
        this.aggressionLevel = 0;
        this.currentPos = startingPos;
    }

    public Bonnie(Position startingPos, int aggressionLevel) {
        this(startingPos);
        this.name = NAME;
        this.aggressionLevel = aggressionLevel;
    }

    public void setFrameHandler(FrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    public boolean movementOpportunity() {
        return true;
    }

    public void jumpscareDoor() {
        FramePlayer.playAtFps(JUMPSCARE_DOOR_FRAMES, 24, frameHandler);
    }

    public void jumpscareInRoom() {
        FramePlayer.playAtFps(JUMPSCARE_IN_ROOM_FRAMES, 24, frameHandler);
    }
}
