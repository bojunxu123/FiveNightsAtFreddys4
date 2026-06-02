package org.fivenightsatfreddys4;

public abstract class Animatronic {

    protected int aggressionLevel;
    public int progress;;
    protected Position currentPos;
    protected FrameSequence doorJumpscare;
    protected FrameSequence roomJumpscare;

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
    }

    public Animatronic(int aggressionLevel) {
        System.out.println(getClass().getSimpleName().toLowerCase() + "/doorJumpscare");
        System.out.println(getClass().getSimpleName().toLowerCase() + "/roomJumpscare");
        this.doorJumpscare = new FrameSequence(getClass().getSimpleName().toLowerCase() + "/doorJumpscare");
        this.roomJumpscare = new FrameSequence(getClass().getSimpleName().toLowerCase() + "/roomJumpscare");
        this.aggressionLevel = aggressionLevel;
    }

    public abstract void movementOpportunity();

    protected abstract boolean isThreat();
}
