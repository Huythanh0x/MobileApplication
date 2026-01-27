/*    */ package dm;
/*    */ 
/*    */ import main.Constants_H;
/*    */ import main.Key_H;
/*    */ 
/*    */ public class Npc
/*    */   implements Constants_H, Key_H {
/*    */   public short x;
/*    */   public short y;
/*    */   public boolean b_check = true;
/*    */   public boolean b_auto = false;
/*    */   public boolean bdir = false;
/* 13 */   public byte npc_num = 0; public byte frame_c = -1; public byte frame_num = 1; public byte state; public byte lastAction = Byte.MAX_VALUE; public byte speed_x; public byte speed_y; public byte speed = 5; public byte brow_action; public byte brow_time; public byte brow_type = -1; public byte ix = -1; public byte iy = -1; public byte timeMove; public byte dir = 3; public byte[] other; public byte now_time = 0, now_action = 0;
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
/*    */   public Npc() {}
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
/*    */   public Npc(int len) {
/* 38 */     this.other = new byte[len];
/*    */   }
/*    */   public void setXY_npc() {
/* 41 */     setXY(this.other[0], this.other[1], 0, 0);
/*    */   } public void setIxIy_npc() {
/* 43 */     this.other[0] = getIx();
/* 44 */     this.other[1] = getIy();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setActionNo(boolean mode) {
/* 49 */     if (this.bdir)
/* 50 */       return;  if (this.dir == 2) { this.other[7] = mode ? 3 : 0; }
/* 51 */     else if (this.dir == 1) { this.other[7] = mode ? 4 : 1; }
/* 52 */     else { this.other[7] = mode ? 5 : 2; }
/* 53 */      this.other[7] = (byte)(this.other[7] + this.npc_num * 6);
/*    */   }
/*    */   
/*    */   public void setNpcNum(int length) {
/* 57 */     this.npc_num = (byte)(this.other[7] / 6);
/* 58 */     if ((this.npc_num + 1) * 6 > length) this.npc_num = 0; 
/*    */   }
/*    */   
/*    */   public void setSpeedXY(int _speed_x, int _speed_y) {
/* 62 */     this.speed_x = (byte)_speed_x;
/* 63 */     this.speed_y = (byte)_speed_y;
/*    */   }
/*    */   public void setIXY(int _ix, int _iy) {
/* 66 */     this.ix = (byte)_ix;
/* 67 */     this.iy = (byte)_iy;
/*    */   }
/*    */   public void setLastAction(boolean _bdir, int _lastAction) {
/* 70 */     this.bdir = _bdir;
/* 71 */     this.lastAction = (byte)_lastAction;
/*    */   }
/*    */   public void setXY(int ix, int iy, int offx, int offy) {
/* 74 */     this.x = (short)(ix * 20 + offx);
/* 75 */     this.y = (short)(iy * 20 + offy);
/*    */   }
/* 77 */   public byte getIx() { return (byte)(this.x / 20); }
/* 78 */   public byte getIy() { return (byte)(this.y / 20); }
/* 79 */   public byte getIx_off() { return (byte)(this.x % 20); } public byte getIy_off() {
/* 80 */     return (byte)(this.y % 20);
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Npc.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */