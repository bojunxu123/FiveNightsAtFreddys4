package org.fivenightsatfreddys4;

public class Foxy extends Animatronic {
    private static final String NAME = "Foxy";
    private boolean inCloset;


    @Override
    public boolean movementOpportunity(Player p) {
        return false;
    }
}
