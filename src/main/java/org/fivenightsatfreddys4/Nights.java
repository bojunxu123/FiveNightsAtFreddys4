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

    public static void loadNight(int i) {
        night = i;

        Main.bonnie.setAggressionLevel(nights[i].bonnieAI);
        Main.chica.setAggressionLevel(nights[i].chicaAI);
        Main.freddy.setAggressionLevel(nights[i].freddyAI);
        Main.foxy.setAggressionLevel(nights[i].foxyAI);
        Main.fredbear.setAggressionLevel(nights[i].fredbearAI);

        Main.timer.scheduleAtFixedRate(nextHour, 0, 90000);
    }

    private static final Night[] nights = new Night[] {
        new Night(0,1,0,0,0),
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
