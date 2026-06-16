package org.fivenightsatfreddys4.animation;

import java.awt.image.BufferedImage;

/**
 * A special frame sequence that holds each frame for a set amount of time
 */
public class FrameTimedSequence extends FrameSequence {

    // the number of render intervals per frame
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
}
