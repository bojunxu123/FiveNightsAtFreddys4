package org.fivenightsatfreddys4.animation;

import javax.swing.*;
import java.util.ArrayDeque;

/*
The class responsible for rendering the whole game
 */
public class FramePlayer {

    // Whether its currently playing a sequence
    private static boolean playing;
    // the current sequence being played
    private static FrameSequence current;
    // the frame of the sequence its currently on
    private static int frame;
    // the swing object that is drawn to the screen
    private static JLabel viewport;
    // whether its playing in reverse
    private static boolean reverse;

    // A queue of all sequences to be played
    private static ArrayDeque<FrameSequence> queue = new ArrayDeque<>();

    /*
    Links the frame player to the screen object
     */
    public static void link(JLabel screen) {
        viewport = screen;
    }

    /*
    Runs each render interval and draws the new frame to the screen while advancing its progress
     */
    public static void tick() {
        // if theres a clip to play
        if (current == null) return;
        // when clip ends
        if (frame == current.size) {
            // if there is more in the queue then begin playing the next one
            if (queue.size() > 0) {
                current = queue.poll();
                frame = 0;
                viewport.setIcon(new ImageIcon(current.getFrame( reverse ? current.size - 1 - frame : frame)));
                return;
            }
            // otherwise stop playing
            playing = false;
            current = null;
            frame = 0;
            return;
        }
        else {
            // display the current frame
            viewport.setIcon(new ImageIcon(current.getFrame( reverse ? current.size - 1 - frame : frame)));
        }
        // advances to next frame
        frame++;
    }

    /*
    Plays a clip forwards
     */
    public static void playClip(FrameSequence toPlay) {
        playClip(toPlay,false);
    }

    /*
    Plays a clip reversably
     */
    public static void playClip(FrameSequence toPlay, boolean reversed) {
        frame = 0;
        playing = true;
        current = toPlay;
        reverse = reversed;
        queue.clear();
    }

    /*
    Plays multiple clips forwards
     */
    public static void playClips(FrameSequence... clips) {
        current = clips[0];
        reverse = false;
        playing = true;
        frame = 0;
        for (int i = 1; i < clips.length; i++) {
            queue.add(clips[i]);
        }
    }


    /**
     * @return the number of frames left until its finished
     */
    public static int getDuration() {
        int duration = current.size - frame;
        for (FrameSequence clip : queue) {
            duration += clip.size;
        }
        return duration;
    }

    /**
     * if its playing
     * @return if its playing
     */
    public static boolean isPlaying() {
        return playing;
    }

    /**
     * reverses the current clip being played
     */
    public static void reverse() {
        reverse = !reverse;
        frame = current.size - frame;
    }
}
