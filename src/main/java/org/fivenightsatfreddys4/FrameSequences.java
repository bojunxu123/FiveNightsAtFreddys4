package org.fivenightsatfreddys4;

public class FrameSequences{

    private static final FrameSequence leftEmpty = new FrameSequence("emptyLeft");
    private static final FrameSequence leftFoxy = new FrameSequence("foxy/left");
    private static final FrameSequence leftBonnie = new FrameSequence("bonnie/left");
    private static final FrameTimedSequence leftFredbear = new FrameTimedSequence("fredbear/left",20);

    private static final FrameSequence rightEmpty = new FrameSequence("emptyRight");
    private static final FrameSequence rightFoxy = new FrameSequence("foxy/right");
    private static final FrameSequence rightChica = new FrameSequence("chica/right");
    private static final FrameTimedSequence rightFredbear = new FrameTimedSequence("fredbear/right",20);

    private static final FrameSequence closetEmpty = new FrameSequence("emptyCloset");
    private static final FrameSequence closetFoxy1 = new FrameSequence("foxy/stage1");
    private static final FrameSequence closetFoxy2 = new FrameSequence("foxy/stage2");
    private static final FrameSequence closetFoxy3 = new FrameSequence("foxy/stage3");
    private static final FrameSequence closetFoxy4 = new FrameSequence("foxy/stage4");
    private static final FrameSequence closetFredbear = new FrameSequence("fredbear/closet");

    public static FrameSequence introWarning = new FrameTimedSequence("mainMenu/warning", 16);
    public static void showLeftDoor() {
        if (Main.foxy.currentPos == Position.LEFT_HALLWAY) {
            leftFoxy.play();
        }
        else if (Main.bonnie.currentPos == Position.LEFT_HALLWAY) {
            leftBonnie.play();
        }
        else if (Main.freddy.currentPos == Position.LEFT_HALLWAY) {
            leftFredbear.play();
        }
        else {
            leftEmpty.play();
        }
    }

    public static void showRightDoor() {
        if (Main.foxy.currentPos == Position.RIGHT_HALLWAY) {
            rightFoxy.play();
        }
        else if (Main.chica.currentPos == Position.RIGHT_HALLWAY) {
            rightChica.play();
        }
        else if (Main.freddy.currentPos == Position.RIGHT_HALLWAY) {
            rightFredbear.play();
        }
        else {
            rightEmpty.play();
        }
    }

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
            }
        }
        else {
            closetEmpty.play();
        }
    }
}
