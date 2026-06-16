package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequence;
import org.fivenightsatfreddys4.animation.FrameTimedSequence;

/**
 * The abstract class that all the animatronics inhert from
 */
public abstract class Animatronic {

    // The frame sequence that plays when the play dies
    private static final FrameSequence deathScreen = new FrameTimedSequence("deathScreen",24);

    // How aggressive the animatronic is, max of 20
    protected int aggressionLevel;
    // the threshold for progess until its lethal
    protected int killThreshold = 5;
    // how close the animatronic is to killing the player
    public int progress;
    // its current position
    public Position currentPos = Position.LIVING_ROOM_CENTER;
    // the normal jumpscare
    protected FrameSequence defaultJumpscare;
    // the jumpscare specific to each animatronics attack position
    protected FrameSequence specificJumpscare;

    /**
     * Sets the aggresion level
     * @param a the new aggresion level
     */
    public void setAggressionLevel(int a){
        aggressionLevel=a;
    }

    /**
     * Adds to the aggresion level
     * @param a the added aggression
     */
    public void addAggressionLevel(int a){
        aggressionLevel+=a;
    }

    /**
     *
     * @return the animatronics aggression level
     */
    public int getAggressionLevel(){
        return aggressionLevel;
    }

    /**
     *
     * @param p the new animatronic position
     */
    public void setCurrentPos(Position p){
        currentPos=p;
    }

    /**
     *
     * @return the animatronics position
     */
    public Position getCurrentPos(){
        return currentPos;
    }

    /**
     * Ran at each movement opportunity to see what the animatronic should do
     */
    public void tryMove() {
        // if its a threat then it increases its progress
        if (isThreat()) {
            progress += 1;
            // Kills the player once it reaches its threshold
            if (progress > killThreshold) {
                kill();
            }
        }
        else {
            // otherwise reset progress
            progress = 0;
            // and check to see if it moves
            if (Math.random() < aggressionLevel/20.0) {
                this.movementOpportunity();
            }
        }
    }

    /**
     * Kills the player
     */
    public void kill() {
        // checks to see which jumpscare to play
        FrameSequence jumpscare = doSpecificJumpscare() ? specificJumpscare : defaultJumpscare;
        FramePlayer.playClips(jumpscare,deathScreen);
        Audios.jumpscare.play();
        Main.endGame();
    }

    // creates new animatronic of aggression level
    public Animatronic(int aggressionLevel) {
        this.aggressionLevel = aggressionLevel;
    }

    /**
     * ran to see how it should move
     */
    public abstract void movementOpportunity();

    /**
     *
     * @return if its a threat
     */
    protected abstract boolean isThreat();

    /**
     *
     * @return if the jumpscare it plays when killing it plays when killing the player is the specific jumpscare
     */
    protected boolean doSpecificJumpscare() {
        return false;
    }
}
