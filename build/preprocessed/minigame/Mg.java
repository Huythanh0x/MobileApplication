/*    */ package minigame;
/*    */ 
/*    */ public class Mg implements Constants_H {
/*    */   private static Mg mgListener;
/*    */   
/*    */   public Mg() {
/*  7 */     mgListener = this;
/*    */   } private MiniGame_H mg; public static Mg i() {
/*  9 */     if (null == mgListener) mgListener = new Mg(); 
/* 10 */     return mgListener;
/*    */   }
/*    */   
/*    */   private void setGame(GameRun gr, int i) {
/* 14 */     this.mg = null;
/* 15 */     switch (i) { case 0:
/* 16 */         this.mg = new Racing(gr); break;
/* 17 */       case 1: this.mg = new Bearer(gr); break;
/* 18 */       case 2: this.mg = new Cards(gr); break;
/* 19 */       case 3: this.mg = new Guess(gr);
/*    */         break; }
/*    */   
/*    */   } public void go(GameRun gr, int i, int mode, int lv) {
/* 23 */     GameRun.run_state = 69;
/* 24 */     setGame(gr, i);
/* 25 */     this.mg.go(mode, lv);
/*    */   }
/*    */   
/* 28 */   public void paint() { this.mg.patin(); } public void run() {
/* 29 */     this.mg.run();
/*    */   } public void key(GameRun gr) {
/* 31 */     if (this.mg.key()) {
/* 32 */       GameRun.run_state = -10;
/* 33 */       this.mg = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/minigame/Mg.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */