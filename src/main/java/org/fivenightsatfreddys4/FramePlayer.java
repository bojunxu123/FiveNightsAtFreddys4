package org.fivenightsatfreddys4;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Consumer;

public class FramePlayer {
    private static final File JUMPSCARE_AUDIO = new File("src/main/resources/audio/scream2.wav");

    private FramePlayer() {
    }

    /** Fire-and-forget overload. Returns immediately; frames play on the Swing EDT. */
    public static void playAtFps(File folder, int fps, Consumer<BufferedImage> onFrame) {
        playAtFps(folder, fps, onFrame, null);
    }

    /**
     * Plays frames from {@code folder} at {@code fps} using a Swing Timer.
     * Returns immediately. {@code onComplete} (if non-null) is called on the EDT
     * after the last frame has been shown.
     */
    public static void playAtFps(File folder, int fps, Consumer<BufferedImage> onFrame, Runnable onComplete) {
        ArrayList<BufferedImage> frames = loadImages(loadFrames(folder));
        if (frames.isEmpty()) {
            return;
        }

        playJumpscareAudio();

        int delayMs = 1000 / fps;
        int[] index = {0};

        Timer timer = new Timer(delayMs, null);
        timer.addActionListener(e -> {
            onFrame.accept(frames.get(index[0]));
            index[0]++;
            if (index[0] >= frames.size()) {
                timer.stop();
                if (onComplete != null) {
                    onComplete.run();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
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

