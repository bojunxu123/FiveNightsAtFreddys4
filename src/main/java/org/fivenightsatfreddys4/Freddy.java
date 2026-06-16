package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public class Freddy extends Animatronic {


    public Freddy(int aggressionLevel) {
        super(aggressionLevel);
        currentPos = Position.BED;
        defaultJumpscare = new FrameSequence("freddy/roomJumpscare");
        specificJumpscare = new FrameSequence("freddy/bedJumpscare");
        killThreshold = 60;
    }


    /**
     * increase its progress at each movement opportunity
     */
    public void movementOpportunity() {
        progress += (int) Math.ceil(aggressionLevel / 2.0f);
        if (progress > killThreshold) {
            kill();
        }
    }

    @Override
    protected boolean isThreat() {
        return true;
    }

    @Override
    public void tryMove() {
        progress += aggressionLevel;
    }

    @Override
    protected boolean doSpecificJumpscare() {
        return Main.player.getPos() == Position.BED;
    }
}