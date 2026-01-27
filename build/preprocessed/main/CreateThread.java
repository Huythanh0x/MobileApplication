/*    */ package main;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreateThread
/*    */   extends Thread
/*    */ {
/*    */   GameRun gr;
/*    */   
/*    */   public CreateThread(GameRun gr_, int type) {
/* 19 */     this.gr = gr_;
/* 20 */     this.gr.threadType = (byte)type;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     (new Thread(this)).start();
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 30 */       this.gr.createOver = 0;
/* 31 */       if (this.gr.threadType == 0) {
/* 32 */         this.gr.map.initMap();
/* 33 */         this.gr.loadItem();
/*    */ 
/*    */         
/* 36 */         this.gr.loadMon(0, this.gr.monster_pro);
/* 37 */         this.gr.loadMon(1, this.gr.monster_pro);
/*    */ 
/*    */         
/* 40 */         this.gr.loadInfoList();
/*    */ 
/*    */       
/*    */       }
/* 44 */       else if (this.gr.threadType == 1) {
/* 45 */         this.gr.initBattle();
/*    */       }
/* 47 */       else if (this.gr.threadType != 2) {
/*    */ 
/*    */         
/* 50 */         if (this.gr.threadType != 3)
/*    */         {
/*    */           
/* 53 */           if (this.gr.threadType != 4)
/*    */           {
/*    */             
/* 56 */             if (this.gr.threadType == 5); } 
/*    */         }
/*    */       } 
/* 59 */       this.gr.createOver = -1;
/*    */     }
/* 61 */     catch (Exception e) {
/* 62 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/main/CreateThread.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */