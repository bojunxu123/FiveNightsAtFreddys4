package org.fivenightsatfreddys4;

public abstract class Animatronic {

    protected int aggressionLevel;
    protected Position currentPos;
    protected FrameSequence jumpscare;

    public void setAggressionLevel(int a){
        aggressionLevel=a;
    }
    public int getAggressionLevel(){
        return aggressionLevel;
    }
    public void setCurrentPos(Position p){
        currentPos=p;
    }
    public Position getCurrentPos(){
        return currentPos;
    }

    public abstract boolean movementOpportunity(Player p);
    
}
