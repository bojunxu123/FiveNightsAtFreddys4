package org.fivenightsatfreddys4;

public abstract class Animatronic {
    protected String name;
    protected int aggressionLevel;
    protected Position currentPos;

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

    public void increaseAggression() {
        aggressionLevel++;
    }


    public abstract boolean movementOpportunity(Player p);
    
}
