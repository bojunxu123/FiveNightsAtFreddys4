package org.fivenightsatfreddys4;

import java.util.TimerTask;

public class Nights {

    private static int night = 0;

    public static final TimerTask nextHour = new TimerTask() {
        @Override
        public void run() {
            Main.hour++;
            switch (Main.hour) {
                case 1:
                    if (night == 1) {
                        Main.freddy.addAggressionLevel(1);
                    }
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
                    break;
                case 5:
                    break;
                case 6:
                    nextHour.cancel();
                    break;
            }
        }
    };

    public static final TimerTask movementOpportunity = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Movement opportunity");
            Main.bonnie.tryMove();
            Main.chica.tryMove();
            Main.freddy.tryMove();
            Main.foxy.tryMove();
            Main.fredbear.tryMove();
        }
    };

    public static void loadNight(int i) {
        night = i;
        i--;

        Main.player = new Player();

        Main.bonnie = new Bonnie(nights[i].bonnieAI);
        Main.chica = new Chica(nights[i].chicaAI);
        Main.freddy = new Freddy(nights[i].freddyAI);
        Main.foxy = new Foxy(nights[i].foxyAI);
        Main.fredbear = new Fredbear(nights[i].fredbearAI);

        Main.timer.purge();
        Main.timer.scheduleAtFixedRate(nextHour, 0, 90000);
        Main.timer.scheduleAtFixedRate(movementOpportunity, 0, 5000);
    }

    private static final Night[] nights = new Night[] {
        new Night(20,1,0,0,0),
        new Night(5,5,2,1,0),
        new Night(7,7,3,10,0),
        new Night(10,10,4,5,0),
        new Night(0,0,0,0,12)
    };

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
