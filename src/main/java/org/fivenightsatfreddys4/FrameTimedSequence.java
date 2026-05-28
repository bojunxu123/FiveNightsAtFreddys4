package org.fivenightsatfreddys4;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class FrameTimedSequence extends FrameSequence {

    private int framesPerFrame;

    public FrameTimedSequence(String path, int fpf) {
        super(path);
        framesPerFrame = fpf;
        size *= framesPerFrame;
    }

    @Override
    public BufferedImage getFrame(int i) {
        return frames[i/framesPerFrame];
    }

    @Override
    public int getLength() {
        return Main.renderInterval * size * framesPerFrame;
    }
}
