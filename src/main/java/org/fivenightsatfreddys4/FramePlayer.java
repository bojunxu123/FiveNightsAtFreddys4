package org.fivenightsatfreddys4;

import javax.swing.*;

public class FramePlayer {

    private static boolean playing;
    private static FrameSequence current;
    private static int frame;
    private static JLabel viewport;
    private static boolean reverse;

    public static void link(JLabel screen) {
        viewport = screen;
    }

    public static void tick() {
        if (current == null) return;
        // when clip ends
        if (frame == current.size) {
            playing = false;
            current = null;
            frame = 0;
        }
        else {
            viewport.setIcon(new ImageIcon(current.getFrame( reverse ? current.size - 1 - frame : frame)));
        }
        frame++;
    }

    public static void playClip(FrameSequence toPlay) {
        frame = 0;
        playing = true;
        current = toPlay;
    }

    public static void playClip(FrameSequence toPlay, boolean reversed) {
        frame = 0;
        playing = true;
        current = toPlay;
        reverse = reversed;
    }


}
