package org.fivenightsatfreddys4;

public class Player {
    private Position pos;
    private boolean flashlightLeft;
    private boolean flashlightRight;
    private boolean flashlightTop;
    private boolean flashlightBottom;
    private boolean leftDoorClosed;
    private boolean rightDoorClosed;

    public Player() {
        pos = Position.BEDROOM;
        flashlightLeft = false;
        flashlightRight = false;
        flashlightTop = false;
        flashlightBottom = false;
        leftDoorClosed = false;
        rightDoorClosed = false;
    }

    public void move(Position p, Position pos) {

    }

    public Position getPos() {
        return pos;
    }

    public boolean isFlashlightLeft() {
        return flashlightLeft;
    }

    public boolean isLeftDoorClosed(){
        return leftDoorClosed;
    }

    public boolean isRightDoorClosed(){
        return rightDoorClosed;
    }
}
