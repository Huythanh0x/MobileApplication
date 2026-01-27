/*    */ package dm;public class Battle implements Constants_H {
/*    */   public Monster[] mon;
/*    */   public byte now_id;
/*    */   public byte action;
/*    */   public byte skill;
/*  6 */   public byte throw_state = -1; public byte dead; public byte act_num; public byte fs_level; public byte rate_off; public byte[] countS = new byte[10]; public byte[] cThrow = new byte[4]; public byte bg_id; public byte[] ceff = new byte[6];
/*    */ 
/*    */ 
/*    */   
/*    */   public byte baoji;
/*    */ 
/*    */ 
/*    */   
/*    */   public short chp;
/*    */ 
/*    */ 
/*    */   
/*    */   public short cexp;
/*    */ 
/*    */ 
/*    */   
/* 22 */   public short[][] hit = new short[3][5];
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean b_renascence = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public Battle(Monster[] _mon) {
/* 31 */     this.act_num = 1;
/* 32 */     this.mon = _mon;
/*    */   } public Monster getMon() {
/* 34 */     return this.mon[this.now_id];
/*    */   } public void initHit() {
/* 36 */     for (byte i = 0; i < this.hit.length; i = (byte)(i + 1))
/* 37 */       this.hit[i][1] = 0; 
/*    */   }
/*    */   
/*    */   public void addHit(int h, int type, int i) {
/* 41 */     this.hit[i][0] = (short)type;
/* 42 */     this.hit[i][1] = (short)(this.hit[i][1] + h);
/* 43 */     this.hit[i][2] = 0;
/* 44 */     this.hit[i][3] = 0;
/* 45 */     this.hit[i][4] = 0;
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Battle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */