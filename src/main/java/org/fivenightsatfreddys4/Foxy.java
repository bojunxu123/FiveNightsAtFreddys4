package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public class Foxy extends Animatronic {

    // Creates a new foxy
    public Foxy(int aggressionLevel) {
        super(aggressionLevel);
        defaultJumpscare = new FrameSequence("foxy/jumpscare");
        killThreshold = 12;
    }


    @Override
    public void movementOpportunity() {
        // player is at the left door while Bonnie is in the hall or at the door he can't advance
        if (Main.player.getPos() == Position.LEFT_DOOR && (currentPos == Position.LEFT_HALLWAY || currentPos == Position.LEFT_DOOR)) {
            return;
        } else if (Main.player.getPos() == Position.RIGHT_DOOR && (currentPos == Position.RIGHT_HALLWAY || currentPos == Position.RIGHT_DOOR)) {
            return;
        } else if (currentPos == Position.LIVING_ROOM_CENTER) {
            currentPos = Math.random() < 0.5 ? Position.LIVING_ROOM_LEFT : Position.LIVING_ROOM_RIGHT;
        } else if (currentPos == Position.LIVING_ROOM_LEFT) {
            currentPos = Math.random() < 0.5 ? Position.LEFT_HALLWAY: Position.LIVING_ROOM_RIGHT;
        } else if (currentPos == Position.LIVING_ROOM_RIGHT) {
            currentPos = Math.random() < 0.5 ? Position.RIGHT_HALLWAY: Position.LIVING_ROOM_LEFT;
        } else if (currentPos == Position.LEFT_HALLWAY) {
            currentPos = Position.LEFT_DOOR;
        } else if (currentPos == Position.RIGHT_HALLWAY) {
            currentPos = Position.RIGHT_DOOR;
        } else if (currentPos == Position.LEFT_DOOR) {
            currentPos = Position.CLOSET;
        } else if (currentPos == Position.RIGHT_DOOR) {
            currentPos = Position.CLOSET;
        }

        if (currentPos == Position.CLOSET || currentPos == Position.BED) {
            Audios.foxyEnterCloset.play();
        }
        else if (currentPos == Position.LIVING_ROOM_LEFT || currentPos == Position.LIVING_ROOM_RIGHT) {
            Audios.foxyRun.play();
        }
    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.CLOSET;
    }
}
