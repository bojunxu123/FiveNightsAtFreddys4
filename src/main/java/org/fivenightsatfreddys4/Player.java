package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequence;

import java.util.EnumMap;

public class Player {
    // The position of the player
    private Position pos = Position.BEDROOM;

    // Where the player is looking, used for the panning
    private Position looking = Position.BEDROOM;

    // if a door is being closed by the player
    private boolean doorClosed;

    // the cooldown between actions when moving
    private int moveCooldown;

    // prevents the light from being used when the door is closed or closing
    private int doorCooldown;

    // The end of the check sequences
    private static final EnumMap<Position, FrameSequence> checkEnd = new EnumMap<>(Position.class);

    // The start of the check sequences
    private static final EnumMap<Position, FrameSequence> checkStart = new EnumMap<>(Position.class);

    // The closing of the doors
    private static final EnumMap<Position,FrameSequence> close = new EnumMap<>(Position.class);

    // The animations for panning
    private static final EnumMap<Position,FrameSequence> pan = new EnumMap<>(Position.class);

    // Leaving a door
    private static final EnumMap<Position,FrameSequence> leave = new EnumMap<>(Position.class);

    // the running animation
    private static final FrameSequence run = new FrameSequence("run");

    // the animation of the player looking back in the bedroom
    private static final FrameSequence home = new FrameSequence("checkBedroom");

    public Player() {
        // plays the starting animation
        FramePlayer.playClip(home);
    }

    /**
     * plays the animations of the player looking around with the flashlight
     * @param to where the player is looking to
     */
    public void pan(Position to) {
        // if the player is currently moving then they cant pan
        if (moveCooldown > 0) return;
        // if already looking there
        if (to == looking && pos == Position.BEDROOM) return;
        // reverse the clip
        if (to == Position.BEDROOM) {
            FramePlayer.playClip(pan.get(looking),true);
        }
        else {
            // play the correct clip
            pan.get(to).play();
        }
        // update where the player is looking
        looking = to;
    }

    /**
     * Move the player and plays the corresponding animations
     * @param to where the player is moving too
     */
    public void move(Position to) {
        // if alreading moving then return
        if (moveCooldown > 0) return;
        // if already there then ignore
        if (to == pos) return;
        // play the walking sound
        Audios.walk.play();
        // makes the player look forward
        looking = Position.BEDROOM;
        // Plays the animations for moving to the bedroom
        if (to == Position.BEDROOM) {
            if (pos == Position.BED) {
                FramePlayer.playClip(checkEnd.get(Position.BED),true);
            }
            else {
                FramePlayer.playClips(leave.get(pos),run,home);
            }
        } // if the player is moving to the bed then play the specific animation
        else if (to == Position.BED) {
            checkEnd.get(Position.BED).play();
        } // Other wise play the animations for moving to the doors
        else {
            FramePlayer.playClips(checkStart.get(to),run,checkEnd.get(to));
        }
        // updates the player position
        pos = to;
        // sets the player on move cooldown
        moveCooldown = FramePlayer.getDuration();
    }

    /**
     *
     * @return the players current position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Toggles the door the player is currently looking at
     */
    public void toggleDoor() {
        // if moving then cant use door
        if (moveCooldown > 0) {return;}
        // if mid door usage then just reverse the clip
        if (FramePlayer.isPlaying()) {
            FramePlayer.reverse();
        }
        else { // Otherwise play the clip
            FramePlayer.playClip(close.get(pos),doorClosed);
        }
        // sets the cooldown
        doorCooldown = FramePlayer.getDuration();
        // updates the state of the door
        doorClosed = !doorClosed;
        if (doorClosed) {
            // Plays the audio for closing door
            Audios.doorClose.play();
            // moves left door threats accordingly
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
            } else if (pos == Position.RIGHT_DOOR) { // moves right door threats accordingly
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
            } // banishes closet threats
            else if (pos == Position.CLOSET) {
                if (Main.fredbear.currentPos == Position.CLOSET) {
                    Main.fredbear.currentPos = Position.LIVING_ROOM_CENTER;
                } else if (Main.foxy.currentPos == Position.CLOSET) {
                    Main.foxy.progress = 0;
                }
            }
        } else {
            // Play door open sounmd
            Audios.doorOpen.play();
        }
    }

    /**
     * returns if a door is closed
     * @param door the door being checked
     * @return if that door is closed
     */
    public boolean isDoorClosed(Position door){
        return doorClosed && pos == door;
    }

    // automatically loads each frame sequence
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
