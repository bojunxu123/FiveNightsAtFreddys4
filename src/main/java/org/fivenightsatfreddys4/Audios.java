package org.fivenightsatfreddys4;

/*
* A class that holds all the audios in memory so they can be easily accessed and reused.
 */
public class Audios {

    public static final Sound fredbearLaugh = new Sound(new Audio("fredlaugh1"),new Audio("fredlaugh2"),new Audio("fredlaugh3"),new Audio("fredlaugh4"),new Audio("fredlaugh5"));

    public static final Audio fredbearRL = new Audio("fredbearRightToLeft");

    public static final Audio fredbearLR = new Audio("fredbearLeftToRight");

    public static final Audio jumpscare = new Audio("jumpscare");

    public static final Sound foxyEnterCloset = new Sound(new Audio("foxyGetsIn"),new Audio("foxyGetsIn2"));

    public static final Sound foxyRun = new Sound(new Audio("foxyRun1"),new Audio("foxyRun2"),new Audio("foxyRun3"),new Audio("foxyRun4"));

    public static final Audio foxySpook = new Audio("foxySpook");

    public static final Sound softWalk = new Sound(new Audio("softwalk1b"),new Audio("softwalk2b"),new Audio("softwalk3b"));

    public static final Sound walk = new Sound(new Audio("walk1"),new Audio("walk2"));

    public static final Sound doorOpen = new Sound(new Audio("doorcreak1"),new Audio("doorcreak2"),new Audio("doorcreak3"),new Audio("doorcreak4"),new Audio("doorcreak5"));

    public static final Sound doorClose = new Sound(new Audio("doorOpen1"),new Audio("doorOpen2"));
}
