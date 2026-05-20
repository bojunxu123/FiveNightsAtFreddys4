package org.fivenightsatfreddys4;

public class Freddy extends Animatronic {
    private static final String NAME = "Freddy";
    private int currentProgress;

    public Freddy(int aggressionLevel) {
        this.name = NAME;
        currentPos = Position.BED;
        this.aggressionLevel = aggressionLevel;
    }

    public boolean movementOpportunity() {
        currentProgress += aggressionLevel;
        return true;
    }

    public void jumpscareInRoom() {
        // TODO: implement jumpscare animation
    }

    public void jumpscareBed() {
        // TODO: implement jumpscare animation
    }
}
