package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Consumer;

public class Bonnie extends Animatronic {
    private static final String NAME = "Bonnie";
    private static final File JUMPSCARE_DOOR_FRAMES = new File("src/main/resources/Bonnie/Jumpscare/Door");
    private static final File JUMPSCARE_IN_ROOM_FRAMES = new File("src/main/resources/Bonnie/Jumpscare/InRoom");

    private Consumer<BufferedImage> frameHandler = frame -> {};

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

    public void setFrameHandler(Consumer<BufferedImage> frameHandler) {
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
