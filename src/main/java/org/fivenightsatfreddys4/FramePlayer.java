package org.fivenightsatfreddys4;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

public class FramePlayer {
    private static final File JUMPSCARE_AUDIO = new File("src/main/resources/audio/scream2.wav");


    private FramePlayer() {
    }

    public static void playAtFps(File folder, int fps, FrameHandler onFrame) {
        ArrayList<File> frameFiles = loadFrames(folder);
        if (frameFiles.isEmpty()) {
            return;
        }

        ArrayList<BufferedImage> frames = loadImages(frameFiles);
        if (frames.isEmpty()) {
            return;
        }

        playJumpscareAudio();

        long frameDelayNanos = 1_000_000_000L / fps;
        long nextFrameTime = System.nanoTime();

        for (int i = 0; i < frames.size(); i++) {
            BufferedImage frame = frames.get(i);

            if (frame != null) {
                onFrame.onFrame(frame);
            }

            if (i < frames.size() - 1) {
                nextFrameTime = nextFrameTime + frameDelayNanos;
                while (System.nanoTime() < nextFrameTime) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    Thread.yield();
                }

                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }
        }
    }

    private static ArrayList<BufferedImage> loadImages(ArrayList<File> frameFiles) {
        ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();

        for (int i = 0; i < frameFiles.size(); i++) {
            File framePath = frameFiles.get(i);
            try {
                BufferedImage frame = ImageIO.read(framePath);
                if (frame != null) {
                    frames.add(frame);
                }
            } catch (Throwable problem) {
                System.out.println("Failed to read frame: " + framePath.getAbsolutePath());
            }
        }

        return frames;
    }

    private static void playJumpscareAudio() {
        try {
            if (!JUMPSCARE_AUDIO.isFile()) {
                throw new FileNotFoundException("Jumpscare audio not found: " + JUMPSCARE_AUDIO.getAbsolutePath());
            }

            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(JUMPSCARE_AUDIO)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Throwable problem) {
            System.out.println("Failed to play jumpscare audio: " + problem.getMessage());
        }
    }

    private static ArrayList<File> loadFrames(File folder) {
        ArrayList<File> sortedFrames = new ArrayList<File>();

        try {
            if (!folder.isDirectory()) {
                throw new FileNotFoundException("Frame folder does not exist: " + folder.getAbsolutePath());
            }

            File[] allFiles = folder.listFiles();
            if (allFiles == null) {
                throw new FileNotFoundException("Failed to scan frame folder: " + folder.getAbsolutePath());
            }

            for (int i = 0; i < allFiles.length; i++) {
                File file = allFiles[i];
                if (file.isFile() && file.getName().toLowerCase(Locale.ROOT).endsWith(".png")) {
                    sortedFrames.add(file);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        insertionSort(sortedFrames);
        return sortedFrames;
    }

    // Pure insertion sort for predictable frame ordering.
    private static void insertionSort(ArrayList<File> files) {
        for (int i = 1; i < files.size(); i++) {
            File current = files.get(i);
            int j = i - 1;

            while (j >= 0) {
                File left = files.get(j);
                int frameCompare = Integer.compare(frameNumber(left), frameNumber(current));
                int fileCompare;
                if (frameCompare != 0) {
                    fileCompare = frameCompare;
                } else {
                    fileCompare = left.getName().compareTo(current.getName());
                }

                if (fileCompare <= 0) {
                    break;
                }

                files.set(j + 1, files.get(j));
                j--;
            }

            files.set(j + 1, current);
        }
    }


    private static int frameNumber(File path) {
        String fileName = path.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String stem = dotIndex >= 0 ? fileName.substring(0, dotIndex) : fileName;

        if (stem.length() == 0) {
            return Integer.MAX_VALUE;
        }

        int value = 0;
        for (int i = 0; i < stem.length(); i++) {
            char character = stem.charAt(i);
            if (character < '0' || character > '9') {
                return Integer.MAX_VALUE;
            }

            int digit = character - '0';
            if (value > (Integer.MAX_VALUE - digit) / 10) {
                return Integer.MAX_VALUE;
            }

            value = value * 10 + digit;
        }

        return value;
    }
}

