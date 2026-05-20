package org.fivenightsatfreddys4;

public class Bonnie extends Animatronic {
    private static final String NAME = "Bonnie";

    public Bonnie(Position startingPos) {
        this.name = NAME;
        this.aggressionLevel = 0;
        this.currentPos = startingPos;
    }

    public Bonnie(Position startingPos, int aggressionLevel) {
        this(startingPos);
        this.aggressionLevel = aggressionLevel;
    }

    public boolean movementOpportunity() {
        return true;
    }

    public void jumpscareDoor() {
        // TODO: implement jumpscare animation
    }

    public void jumpscareInRoom() {
        // TODO: implement jumpscare animation
    }
}
