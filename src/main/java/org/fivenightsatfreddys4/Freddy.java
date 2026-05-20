package org.fivenightsatfreddys4;

public class Freddy extends Animatronic {
    private static final String NAME = "Freddy";
    private int currentProgress;

    public Freddy(int aggressionLevel) {
        this.name = NAME;
        currentPos = Position.BED;
        this.aggressionLevel = aggressionLevel;
    }

    public boolean movementOpportunity(Player p) {
        if (!p.getPos().equals(Position.BED)) { //if player isn't watching the bed
            currentProgress += aggressionLevel; //get more aggressive
            return true;
        } else {
            return false; //if the player is watching do nothing
        }
    }

    public void jumpscareInRoom() {
        // TODO: implement jumpscare animation
    }

    public void jumpscareBed() {
        // TODO: implement jumpscare animation
    }
}