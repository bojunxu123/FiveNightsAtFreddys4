package org.fivenightsatfreddys4;

public class Bonnie extends Animatronic {
    private static final String NAME = "Bonnie";
    private static final Position[] locations = {
            Position.LIVING_ROOM_CENTER,
            Position.LIVING_ROOM_LEFT,
            Position.LEFT_HALLWAY,
            Position.LEFT_DOOR
    };

    public Bonnie() {
        this.name = NAME;
        this.aggressionLevel = 0;
        this.currentPos = locations[0];
    }

    public Bonnie(int a) {
        this();
        this.aggressionLevel = a;
    }

    public boolean movementOpportunity(Player p) {
        // player is at the left door while Bonnie is in the hall or at the door he can't advance
        if (p.getPos() == Position.LEFT_DOOR && (currentPos == Position.LEFT_HALLWAY || currentPos == Position.LEFT_DOOR)) {
            return false;
        } else if (p.isLeftDoorClosed() && currentPos == Position.LEFT_DOOR) {
            currentPos = Position.LIVING_ROOM_LEFT;
            return true;
        } else if (p.isLeftDoorClosed() && currentPos == Position.LEFT_HALLWAY) {
            currentPos = Position.LEFT_DOOR;
            return true;
        } else {
            return true;
        }
        // if player shuts door on him while he's at the door, set pos to LIVING_ROOM_LEFT on the next interval, return true
    }

    public void jumpscareDoor() {
        // TODO: implement jumpscare animation
    }

    public void jumpscareInRoom() {
        // TODO: implement jumpscare animation
    }
}