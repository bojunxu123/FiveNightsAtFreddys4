package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public class Chica extends Animatronic {


    public Chica(int chicaAI) {
        super(chicaAI);
        defaultJumpscare = new FrameSequence("chica/roomJumpscare");
        specificJumpscare = new FrameSequence("chica/doorJumpscare");
    }

    @Override
    public void movementOpportunity() {
        // player is at the left door while Bonnie is in the hall or at the door he can't advance
        if (Main.player.getPos() == Position.LEFT_DOOR && (currentPos == Position.RIGHT_DOOR || currentPos == Position.RIGHT_DOOR)) {
            return;
        } else if (currentPos == Position.LIVING_ROOM_CENTER) {
            currentPos = Position.LIVING_ROOM_RIGHT;
        } else if (currentPos == Position.LIVING_ROOM_RIGHT) {
            currentPos = Math.random() < 0.35 ? Position.RIGHT_HALLWAY: Position.KITCHEN;
            Audios.softWalk.play();
        } else if (currentPos == Position.KITCHEN) {
            currentPos = Math.random() < 0.8 ? Position.LIVING_ROOM_RIGHT: Position.KITCHEN;
            Audios.softWalk.play();
        } else if (currentPos == Position.RIGHT_HALLWAY) {
            currentPos = Position.RIGHT_DOOR;
        }
        // if player shuts door on him while he's at the door, set pos to LIVING_ROOM_LEFT on the next interval, return true
    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.RIGHT_DOOR;
    }

    @Override
    protected boolean doSpecificJumpscare() {
        return Main.player.getPos() == Position.RIGHT_DOOR;
    }
}
