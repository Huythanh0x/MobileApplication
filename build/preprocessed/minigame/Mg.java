package minigame;

import main.Constants_H;
import main.GameRun;

public class Mg implements Constants_H {
   private static Mg mgListener;
   private MiniGame_H mg;

   public Mg() {
      mgListener = this;
   }

   public static Mg i() {
      if (null == mgListener) {
         mgListener = new Mg();
      }

      return mgListener;
   }

   private void setGame(GameRun gr, int i) {
      this.mg = null;
      switch (i) {
         case 0:
            this.mg = new Racing(gr);
            break;
         case 1:
            this.mg = new Bearer(gr);
            break;
         case 2:
            this.mg = new Cards(gr);
            break;
         case 3:
            this.mg = new Guess(gr);
      }

   }

   public void go(GameRun gr, int i, int mode, int lv) {
      GameRun.run_state = 69;
      this.setGame(gr, i);
      this.mg.go(mode, lv);
   }

   public void paint() {
      this.mg.patin();
   }

   public void run() {
      this.mg.run();
   }

   public void key(GameRun gr) {
      if (this.mg.key()) {
         GameRun.run_state = -10;
         this.mg = null;
      }

   }
}
