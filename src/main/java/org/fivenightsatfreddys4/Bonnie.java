package org.fivenightsatfreddys4;

public class Bonnie extends Animatronic {

    public Bonnie( int aggression) {
        super(aggression);
    }

    public void movementOpportunity() {
        // player is at the left door while Bonnie is in the hall or at the door he can't advance
        if (Main.player.getPos() == Position.LEFT_DOOR && (currentPos == Position.LEFT_HALLWAY || currentPos == Position.LEFT_DOOR)) {
            return;
        } else if (currentPos == Position.LIVING_ROOM_CENTER) {
            currentPos = Position.LIVING_ROOM_LEFT;
        } else if (currentPos == Position.LIVING_ROOM_LEFT) {
            currentPos = Math.random() < 0.5 ? Position.LEFT_HALLWAY: Position.LIVING_ROOM_CENTER;
        }
        else if (currentPos == Position.LEFT_HALLWAY) {
            currentPos = Position.LEFT_DOOR;
        }
        // if player shuts door on him while he's at the door, set pos to LIVING_ROOM_LEFT on the next interval, return true
    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.LEFT_DOOR;
    }
}