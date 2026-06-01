package org.fivenightsatfreddys4;

public abstract class Animatronic {

    protected int aggressionLevel;
    protected Position currentPos;
    protected FrameSequence jumpscare;

    public void setAggressionLevel(int a){
        aggressionLevel=a;
    }
    public void addAggressionLevel(int a){
        aggressionLevel+=a;
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

    protected void tryMove() {
        if (Math.random() < aggressionLevel/20.0) {
            this.movementOpportunity();
        }
    }
    public abstract void movementOpportunity();
    
}
