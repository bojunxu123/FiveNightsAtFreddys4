package org.fivenightsatfreddys4;

public class Freddy extends Animatronic {


    public Freddy(int aggressionLevel) {
        super(aggressionLevel);
        currentPos = Position.BED;
    }

    public void movementOpportunity() {

    }

    @Override
    protected boolean isThreat() {
        return aggressionLevel > 0;
    }

}