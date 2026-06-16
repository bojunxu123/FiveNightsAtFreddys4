package org.fivenightsatfreddys4.animation;

import org.fivenightsatfreddys4.Audio;
import org.fivenightsatfreddys4.Audios;
import org.fivenightsatfreddys4.Main;
import org.fivenightsatfreddys4.Position;

public class FrameSequences{

    // all the frame sequences for the left hallway
    private static final FrameSequence leftEmpty = new FrameSequence("emptyLeft");
    private static final FrameSequence leftFoxy = new FrameSequence("foxy/left");
    private static final FrameSequence leftBonnie = new FrameSequence("bonnie/left");
    private static final FrameTimedSequence leftFredbear = new FrameTimedSequence("fredbear/left",8);

    // all the frame sequences for the right hallway
    private static final FrameSequence rightEmpty = new FrameSequence("emptyRight");
    private static final FrameSequence rightFoxy = new FrameSequence("foxy/right");
    private static final FrameSequence rightChica = new FrameSequence("chica/right");
    private static final FrameTimedSequence rightFredbear = new FrameTimedSequence("fredbear/right",8);

    // all the frame sequences for the closet
    private static final FrameSequence closetEmpty = new FrameSequence("emptyCloset");
    private static final FrameSequence closetFoxy1 = new FrameSequence("foxy/stage1");
    private static final FrameSequence closetFoxy2 = new FrameSequence("foxy/stage2");
    private static final FrameSequence closetFoxy3 = new FrameSequence("foxy/stage3");
    private static final FrameSequence closetFoxy4 = new FrameSequence("foxy/stage4");
    private static final FrameSequence closetFredbear = new FrameSequence("fredbear/closet");

    // all the frame sequences for the bed
    private static final FrameSequence bedEmpty = new FrameSequence("emptyBed");
    private static final FrameSequence bedFredbear = new FrameTimedSequence("fredbear/bed", 8);
    private static final FrameSequence bedFreddle1 = new FrameSequence("freddy/1/idle");
    private static final FrameSequence bedFreddle2 = new FrameSequence("freddy/2/idle");
    private static final FrameSequence bedFreddle3 = new FrameSequence("freddy/3/idle");

    // the intro warning
    public static FrameSequence introWarning = new FrameTimedSequence("mainMenu/warning", 16);

    /**
     * Shows the correct frame sequence for the left door
     */
    public static void showLeftDoor() {
        if (Main.foxy.currentPos == Position.LEFT_HALLWAY) {
            leftFoxy.play();
            Main.foxy.currentPos = Position.LIVING_ROOM_CENTER;
        }
        else if (Main.bonnie.currentPos == Position.LEFT_HALLWAY) {
            leftBonnie.play();
            Main.bonnie.currentPos = Position.LIVING_ROOM_CENTER;
        }
        else if (Main.fredbear.currentPos == Position.LEFT_HALLWAY) {
            FramePlayer.playClips(leftFredbear,leftEmpty);
            Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
        } else if (Main.bonnie.currentPos == Position.LEFT_DOOR) {
            Main.bonnie.kill();
        } else if (Main.fredbear.currentPos == Position.LEFT_DOOR) {
            Main.fredbear.kill();
        } else {
            leftEmpty.play();
        }
    }

    /**
     * Shows the correct frame sequence for the right door
     */
    public static void showRightDoor() {
        if (Main.foxy.currentPos == Position.RIGHT_HALLWAY) {
            rightFoxy.play();
            Main.foxy.currentPos = Position.LIVING_ROOM_CENTER;
        }
        else if (Main.chica.currentPos == Position.RIGHT_HALLWAY) {
            rightChica.play();
            Main.chica.currentPos = Position.LIVING_ROOM_CENTER;
        }
        else if (Main.fredbear.currentPos == Position.RIGHT_HALLWAY) {
            FramePlayer.playClips(rightFredbear,rightEmpty);
            Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
        } else if (Main.chica.currentPos == Position.RIGHT_DOOR) {
            Main.chica.kill();
        } else if (Main.fredbear.currentPos == Position.RIGHT_DOOR) {
            Main.fredbear.kill();
        } else {
            rightEmpty.play();
        }
    }

    /**
     * Shows the correct frame sequence for the closet
     */
    public static void showCloset() {
        if (Main.fredbear.currentPos == Position.CLOSET) {
            closetFredbear.play();
        }
        else if (Main.foxy.currentPos == Position.CLOSET) {
            if (Main.foxy.progress < 2) {
                closetFoxy1.play();
            }
            else if (Main.foxy.progress < 4) {
                closetFoxy2.play();
            }
            else if (Main.foxy.progress < 6) {
                closetFoxy3.play();
            }
            else {
                closetFoxy4.play();
                Audios.foxySpook.play();
            }
        }
        else {
            closetEmpty.play();
        }
    }

    /**
     * Shows the correct frame sequence for the bed
     */
    public static void showBed() {
        if (Main.fredbear.currentPos == Position.BED) {
            FramePlayer.playClips(bedFredbear,bedEmpty);
            Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
        }
        else if (Main.freddy.progress < 10) {
            bedEmpty.play();
        }
        else if (Main.freddy.progress < 20) {
            bedFreddle1.play();
        }
        else if (Main.freddy.progress < 30) {
            bedFreddle2.play();
        }
        else if (Main.freddy.progress < 60) {
            bedFreddle3.play();
        }
        else {
            bedEmpty.play();
        }
    }
}
