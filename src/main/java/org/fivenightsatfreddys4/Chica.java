package org.fivenightsatfreddys4;

public class Chica extends Animatronic {


    public Chica(int chicaAI) {
        super(chicaAI);
    }

    @Override
    public void movementOpportunity() {

    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.RIGHT_DOOR;
    }
}
