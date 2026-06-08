package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequence;

import java.util.EnumMap;

public class Player {
    private Position pos = Position.BEDROOM;
    private Position looking = Position.BEDROOM;
    private boolean flashlight = false;
    private boolean doorClosed;

    private static final EnumMap<Position, FrameSequence> checkEnd = new EnumMap<>(Position.class);

    private static final EnumMap<Position, FrameSequence> checkStart = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> close = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> pan = new EnumMap<>(Position.class);

    private static final EnumMap<Position,FrameSequence> leave = new EnumMap<>(Position.class);

    private static final FrameSequence run = new FrameSequence("run");

    private static final FrameSequence home = new FrameSequence("checkBedroom");

    public Player() {
        FramePlayer.playClip(home);
        System.out.println("Player initialized");
    }

    public void pan(Position to) {
        if (to == looking && pos == Position.BEDROOM) return;
        if (to == Position.BEDROOM) {
            FramePlayer.playClip(pan.get(looking),true);
        }
        else {
               pan.get(to).play();
        }
        looking = to;
    }

    public void move(Position to) {
        if (to == pos) return;
        looking = Position.BEDROOM;
        if (to == Position.BEDROOM) {
            if (pos == Position.BED) {
                FramePlayer.playClip(checkEnd.get(Position.BED),true);
            }
            else {
                FramePlayer.playClips(leave.get(pos),run,home);
            }
        }
        else if (to == Position.BED) {
            checkEnd.get(Position.BED).play();
        }
        else {
            FramePlayer.playClips(checkStart.get(to),run,checkEnd.get(to));
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
        checkEnd.put(Position.BED, new FrameSequence("checkBed"));
        checkEnd.put(Position.CLOSET, new FrameSequence("checkClosetEnd"));
        checkEnd.put(Position.LEFT_DOOR, new FrameSequence("checkLeftEnd"));
        checkEnd.put(Position.RIGHT_DOOR, new FrameSequence("checkRightEnd"));


        checkStart.put(Position.CLOSET, new FrameSequence("checkClosetStart"));
        checkStart.put(Position.LEFT_DOOR, new FrameSequence("checkLeftStart"));
        checkStart.put(Position.RIGHT_DOOR, new FrameSequence("checkRightStart"));

        close.put(Position.LEFT_DOOR, new FrameSequence("closeLeft"));
        close.put(Position.RIGHT_DOOR, new FrameSequence("closeRight"));
        close.put(Position.CLOSET, new FrameSequence("closeCloset"));

        pan.put(Position.LEFT_DOOR, new FrameSequence("panLeft"));
        pan.put(Position.RIGHT_DOOR, new FrameSequence("panRight"));

        leave.put(Position.LEFT_DOOR, new FrameSequence("leaveLeft"));
        leave.put(Position.RIGHT_DOOR, new FrameSequence("leaveRight"));
        leave.put(Position.CLOSET, new FrameSequence("leaveCloset"));

    }

}
