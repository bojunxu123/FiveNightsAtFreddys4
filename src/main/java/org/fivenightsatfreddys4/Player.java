package org.fivenightsatfreddys4;

public class Player {
    private Position pos;
    private boolean flashlight = false;
    private boolean doorClosed;

    private FrameSequence checkBed = new FrameSequence("checkBed");
    private FrameSequence checkLeft = new FrameSequence("checkLeft");
    private FrameSequence checkRight = new FrameSequence("checkRight");

    private FrameSequence closeLeft = new FrameSequence("closeLeft");
    private FrameSequence closeRight = new FrameSequence("closeRight");
    private FrameSequence closeCloset = new FrameSequence("closeCloset");

    private FrameSequence panLeft = new FrameSequence("panLeft");
    private FrameSequence panRight = new FrameSequence("panRight");

    private FrameSequence leaveLeft = new FrameSequence("leaveLeft");
    private FrameSequence leaveRight = new FrameSequence("leaveRight");

    public Player() {
        pos = Position.BEDROOM;
    }

    public void move(Position p, Position pos) {

    }

    public Position getPos() {
        return pos;
    }


    public boolean isLeftDoorClosed(){
        return doorClosed && pos == Position.LEFT_DOOR;
    }

    public boolean isRightDoorClosed(){
        return doorClosed && pos == Position.RIGHT_DOOR;
    }
}
