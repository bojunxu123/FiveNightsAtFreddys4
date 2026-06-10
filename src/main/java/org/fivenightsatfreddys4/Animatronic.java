package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FrameSequence;

public abstract class Animatronic {

    protected int aggressionLevel;
    public int progress;;
    public Position currentPos = Position.LIVING_ROOM_CENTER;
    protected FrameSequence doorJumpscare;

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

    public void tryMove() {
        if (isThreat()) {
            progress += 1;
        }
        if (Math.random() < aggressionLevel/20.0) {
            this.movementOpportunity();
        }
        else {
            if (aggressionLevel > 0) System.out.println(getClass().getSimpleName() + " did not move at AI " + aggressionLevel);
        }
    }

    public Animatronic(int aggressionLevel) {
        this.doorJumpscare = new FrameSequence(getClass().getSimpleName().toLowerCase() + "/doorJumpscare");
        this.aggressionLevel = aggressionLevel;
    }

    public abstract void movementOpportunity();

    protected abstract boolean isThreat();
}
