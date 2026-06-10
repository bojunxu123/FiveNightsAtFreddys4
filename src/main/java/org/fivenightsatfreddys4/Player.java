package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequence;

import java.util.EnumMap;

public class Player {
    private Position pos = Position.BEDROOM;
    private Position looking = Position.BEDROOM;
    private boolean doorClosed;

    private int moveCooldown;

    private int doorCooldown;

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
        if (moveCooldown > 0) return;
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
        if (moveCooldown > 0) return;
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
        moveCooldown = FramePlayer.getDuration();
    }

    public Position getPos() {
        return pos;
    }

    public void toggleDoor() {
        if (moveCooldown > 0) {return;}
        if (FramePlayer.isPlaying()) {
            FramePlayer.reverse();
        }
        else {
            FramePlayer.playClip(close.get(pos),doorClosed);
        }
        doorCooldown = FramePlayer.getDuration();
        doorClosed = !doorClosed;
        if (doorClosed) {
            if (pos == Position.LEFT_DOOR) {
                if (Main.bonnie.currentPos == Position.LEFT_DOOR) {
                    Main.bonnie.currentPos = Position.LIVING_ROOM_CENTER;
                }
                else {
                    Main.bonnie.currentPos = Position.LEFT_DOOR;
                }
                if (Main.fredbear.currentPos == Position.LEFT_DOOR) {
                    Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
                }
                if (Main.fredbear.currentPos == Position.LEFT_DOOR) {
                    Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
                }
            } else if (pos == Position.RIGHT_DOOR) {
                if (Main.chica.currentPos == Position.RIGHT_DOOR) {
                    Main.chica.currentPos = Position.LIVING_ROOM_CENTER;
                } else {
                    Main.chica.currentPos = Position.RIGHT_DOOR;
                }
                if (Main.fredbear.currentPos == Position.RIGHT_DOOR) {
                    Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
                }
                if (Main.foxy.currentPos == Position.RIGHT_DOOR) {
                    Main.foxy.currentPos = Position.LIVING_ROOM_CENTER;
                }
            }
        }
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

    public void tick() {
        moveCooldown--;
        doorCooldown--;
    }

    public int getMoveCooldown() {
        return moveCooldown;
    }

    public int getDoorCooldown() {
        return doorCooldown;
    }
}
