package org.fivenightsatfreddys4;

import java.util.TimerTask;

/**
 * The class that is responsible for the nights
 */
public class Nights {

    // the current night
    private static int night = 0;

    /**
     * Loads a night
     * @param i the night to be loaded
     */
    public static void loadNight(int i) {
        // sets the night
        night = i;
        i--;

        // creates all the required objects
        Main.player = new Player();

        Main.bonnie = new Bonnie(nights[i].bonnieAI);
        Main.chica = new Chica(nights[i].chicaAI);
        Main.freddy = new Freddy(nights[i].freddyAI);
        Main.foxy = new Foxy(nights[i].foxyAI);
        Main.fredbear = new Fredbear(nights[i].fredbearAI);

        // Schedules the movement opportunities and hours tasks
        Main.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Main.bonnie.tryMove();
                    Main.chica.tryMove();
                    Main.freddy.tryMove();
                    Main.foxy.tryMove();
                    Main.fredbear.tryMove();
                } catch (NullPointerException e) {

                }
            }
        }, 3000, 5000);
        Main.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hour");
                Main.hour++;
                Main.amLabel.setText(Main.hour + " am");
                switch (Main.hour) {
                    case 1:
                        Main.freddy.addAggressionLevel(1);
                        break;
                    case 2:
                        Main.bonnie.addAggressionLevel(1);
                        Main.chica.addAggressionLevel(1);
                        break;
                    case 3:
                        Main.bonnie.addAggressionLevel(2);
                        Main.chica.addAggressionLevel(1);
                        if (night == 1) {
                            Main.freddy.addAggressionLevel(1);
                        }
                        else if (night == 2 ) {
                            Main.foxy.addAggressionLevel(3);
                            Main.freddy.addAggressionLevel(1);
                        }
                        else if (night == 4 ) {
                            Main.foxy.addAggressionLevel(5);
                        }
                        break;
                    case 4:
                        Main.chica.addAggressionLevel(1);
                        Main.freddy.addAggressionLevel(2);
                        Main.bonnie.addAggressionLevel(1);
                        break;
                    case 5:
                        Main.bonnie.addAggressionLevel(night);
                        Main.freddy.addAggressionLevel(1);
                        break;
                    case 6:
                        break;
                }
            }
        }, 45000, 45000);
    }

    // Hold the aggression levels for each foe per night
    private static final Night[] nights = new Night[] {
        new Night(0,2,0,0,0),
        new Night(5,5,2,1,0),
        new Night(7,7,3,10,0),
        new Night(10,10,4,5,0),
        new Night(0,0,0,0,12)
    };

    /**
     * Holds all the aggression values of a night
     */
    private static class Night {

        int bonnieAI;

        int chicaAI;

        int freddyAI;

        int foxyAI;

        int fredbearAI;

        private Night(int bonnieAI, int chicaAI, int freddyAI, int foxyAI, int fredbearAI) {
            this.bonnieAI = bonnieAI;
            this.chicaAI = chicaAI;
            this.freddyAI = freddyAI;
            this.foxyAI = foxyAI;
            this.fredbearAI = fredbearAI;
        }

    }

}
