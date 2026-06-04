package org.fivenightsatfreddys4;

import java.util.EnumMap;

public class Player {
    private Position pos = Position.BEDROOM;
    private Position looking = Position.BEDROOM;
    private boolean flashlight = false;
    private boolean doorClosed;

    private static final EnumMap<Position,FrameSequence> check = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> close = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> pan = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> leave = new EnumMap<>(Position.class);

    public Player() {
        FramePlayer.playClip(new FrameSequence("room"));
        System.out.println("Player initialized");
    }

    public void pan(Position to) {
        if (to == Position.BEDROOM) {
            FramePlayer.playClip(pan.get(looking),true);
        }
        else {
               pan.get(to).play();
        }
        looking = to;
    }

    public void move(Position to) {
        looking = Position.BEDROOM;
        if (to == Position.BEDROOM) {
            leave.get(pos).play();
        }
        else {
            check.get(to).play();
        }
        pos = to;
    }

    public Position getPos() {
        return pos;
    }

    public void toggleDoor() {
        FramePlayer.playClip(close.get(pos),doorClosed);;
        doorClosed = !doorClosed;
    }

    public boolean isDoorClosed(Position door){
        return doorClosed && pos == door;
    }

    static {
        check.put(Position.BED, new FrameSequence("checkBed"));
        check.put(Position.CLOSET, new FrameSequence("checkCloset"));
        check.put(Position.LEFT_DOOR, new FrameSequence("checkLeft"));
        check.put(Position.RIGHT_DOOR, new FrameSequence("checkRight"));

        close.put(Position.LEFT_DOOR, new FrameSequence("closeLeft"));
        close.put(Position.RIGHT_DOOR, new FrameSequence("closeRight"));
        close.put(Position.CLOSET, new FrameSequence("closeCloset"));

        pan.put(Position.LEFT_HALLWAY, new FrameSequence("panLeft"));
        pan.put(Position.RIGHT_HALLWAY, new FrameSequence("panRight"));

        leave.put(Position.LEFT_DOOR, new FrameSequence("leaveLeft"));
        leave.put(Position.RIGHT_DOOR, new FrameSequence("leaveRight"));
        leave.put(Position.BED, new FrameSequence("checkBed",true));
        leave.put(Position.CLOSET, new FrameSequence("leaveCloset"));

    }

}
