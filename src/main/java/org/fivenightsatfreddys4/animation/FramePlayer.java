package org.fivenightsatfreddys4.animation;

import javax.swing.*;
import java.util.ArrayDeque;

public class FramePlayer {

    private static boolean playing;
    private static FrameSequence current;
    private static int frame;
    private static JLabel viewport;
    private static boolean reverse;

    private static ArrayDeque<FrameSequence> queue = new ArrayDeque<>();

    public static void link(JLabel screen) {
        viewport = screen;
    }

    public static void tick() {
        if (current == null) return;
        // when clip ends
        if (frame == current.size) {
            if (queue.size() > 0) {
                current = queue.poll();
                frame = 0;
                viewport.setIcon(new ImageIcon(current.getFrame( reverse ? current.size - 1 - frame : frame)));
                return;
            }
            playing = false;
            current = null;
            frame = 0;
            return;
        }
        else {
            viewport.setIcon(new ImageIcon(current.getFrame( reverse ? current.size - 1 - frame : frame)));
        }
        frame++;
    }

    public static void playClip(FrameSequence toPlay) {
        playClip(toPlay,false);
    }

    public static void playClip(FrameSequence toPlay, boolean reversed) {
        frame = 0;
        playing = true;
        current = toPlay;
        reverse = reversed;
        queue.clear();
    }

    public static void playClips(FrameSequence... clips) {
        current = clips[0];
        reverse = false;
        playing = true;
        frame = 0;
        for (int i = 1; i < clips.length; i++) {
            queue.add(clips[i]);
        }
    }

}
