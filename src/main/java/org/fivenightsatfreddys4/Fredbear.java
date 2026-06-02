package org.fivenightsatfreddys4;

public class Fredbear extends Animatronic {

    public Fredbear(int aggressionLevel) {
        super(aggressionLevel);
    }

    @Override
    public void movementOpportunity() {

    }

    @Override
    protected boolean isThreat() {
        return false;
    }
}