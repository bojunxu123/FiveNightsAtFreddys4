package org.fivenightsatfreddys4;

public class Foxy extends Animatronic {

    public Foxy(int aggressionLevel) {
        super(aggressionLevel);

    }


    @Override
    public void movementOpportunity() {

    }

    @Override
    protected boolean isThreat() {
        return currentPos == Position.CLOSET;
    }
}
