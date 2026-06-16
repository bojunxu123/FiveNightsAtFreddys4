package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public class Fredbear extends Animatronic {

    public Fredbear(int aggressionLevel) {
        super(aggressionLevel);
        killThreshold = 2;
        if (aggressionLevel > 0) {
            defaultJumpscare = new FrameSequence("fredbear/jumpscare");
        }
    }

    @Override
    public void movementOpportunity() {
        if (currentPos == Position.LIVING_ROOM_CENTER) {
            currentPos = Math.random() < 0.2 ? (Math.random() < 0.5 ? Position.BED : Position.CLOSET) : (Math.random() < 0.5 ? Position.LIVING_ROOM_RIGHT : Position.LIVING_ROOM_LEFT);
        }
        else if (currentPos == Position.LIVING_ROOM_LEFT && Main.player.getPos() != Position.LEFT_DOOR) {
            currentPos = Math.random() < 0.2 ? (Math.random() < 0.5 ? Position.BED : Position.CLOSET) : (Math.random() < 0.6 ? Position.LEFT_HALLWAY : Position.LIVING_ROOM_RIGHT);
        }
        else if (currentPos == Position.LIVING_ROOM_RIGHT && Main.player.getPos() != Position.RIGHT_DOOR) {
            currentPos = Math.random() < 0.2 ? (Math.random() < 0.5 ? Position.BED : Position.CLOSET) : (Math.random() < 0.6 ? Position.RIGHT_HALLWAY : Position.LIVING_ROOM_LEFT);
        }
        else if (currentPos == Position.LEFT_HALLWAY && Main.player.getPos() != Position.LEFT_DOOR) {
            currentPos =  Position.LEFT_DOOR;
        }
        else if (currentPos == Position.RIGHT_HALLWAY && Main.player.getPos() != Position.RIGHT_DOOR) {
            currentPos =  Position.RIGHT_DOOR;
        }
        if (currentPos == Position.CLOSET || currentPos == Position.BED) {
            Audios.fredbearLaugh.play();
        }
        else if (currentPos == Position.LIVING_ROOM_LEFT) {
            Audios.fredbearRL.play();
        }
        else if (currentPos == Position.LIVING_ROOM_RIGHT) {
            Audios.fredbearLR.play();
        }
    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.LEFT_DOOR || currentPos == Position.RIGHT_DOOR || currentPos == Position.BED || currentPos == Position.CLOSET;
    }
}