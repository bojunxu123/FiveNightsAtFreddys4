package org.fivenightsatfreddys4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrameSequence {

    public BufferedImage[] frames;
    public int size;
    public boolean reversed;

    public FrameSequence(String path, boolean reversed) {

        URL url = getClass().getResource("/" + path);
        File root = new File(url.getPath());
        File[] frameFiles = root.listFiles();



        size = frameFiles.length;
        frames = new BufferedImage[size];
        this.reversed = reversed;

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

}
