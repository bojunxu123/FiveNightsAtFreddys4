package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public class Bonnie extends Animatronic {

    public Bonnie( int aggression) {
        super(aggression);
        defaultJumpscare = new FrameSequence("bonnie/roomJumpscare");
        specificJumpscare = new FrameSequence("bonnie/doorJumpscare");
    }

    /**
     * Moves bonnie
     */
    public void movementOpportunity() {
        // player is at the left door while Bonnie is in the hall or at the door he can't advance
        if (Main.player.getPos() == Position.LEFT_DOOR && (currentPos == Position.LEFT_HALLWAY || currentPos == Position.LEFT_DOOR)) {
            return;
        } else if (currentPos == Position.LIVING_ROOM_CENTER) {
            currentPos = Position.LIVING_ROOM_LEFT;
        } else if (currentPos == Position.LIVING_ROOM_LEFT) {
            currentPos = Math.random() < (0.5 + aggressionLevel * 0.025) ? Position.LEFT_HALLWAY: Position.LIVING_ROOM_CENTER;
            Audios.softWalk.play();
        }
        else if (currentPos == Position.LEFT_HALLWAY) {
            currentPos = Position.LEFT_DOOR;
        }
    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.LEFT_DOOR;
    }

    @Override
    protected boolean doSpecificJumpscare() {
        return Main.player.getPos() == Position.LEFT_DOOR;
    }
}