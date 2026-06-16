package org.fivenightsatfreddys4.animation;

import org.fivenightsatfreddys4.Main;
import org.fivenightsatfreddys4.Playable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Loads and holds a sequence of frames that can be played
 */
public class FrameSequence implements Playable {

    // an array of all the frames
    public BufferedImage[] frames;
    // how many frames are in the array
    public int size;

    public FrameSequence(String path, boolean reversed) {
        // loads the parent directory
        URL url = getClass().getResource("/" + path);
        File root = new File(url.getPath());
        // makes a list of all the files
        File[] frameFiles = root.listFiles();
        size = frameFiles.length;
        frames = new BufferedImage[size];

        // attempts to load the frames
        try {
            if (reversed) {
                for (int i = 0; i < size; i++) {
                    frames[i] = ImageIO.read(frameFiles[size - i - 1]);
                }
            }
            else {
                for (int i = 0; i < size; i++) {
                    frames[i] = ImageIO.read(frameFiles[i]);

                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FrameSequence(String path) {
        this(path,false);
    }

    /**
     *
     * @param i the frame being returned
     * @return the requested frame
     */
    public BufferedImage getFrame(int i) {
        return frames[i];
    }

    @Override
    public void play() {
        FramePlayer.playClip(this);
    }

    /**
     *
     * @return The length in milliseconds
     */
    public int getLength() {
        return Main.renderInterval * size;
    }
}
