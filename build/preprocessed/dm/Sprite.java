/*    */ package dm;
/*    */ 
/*    */ public class Sprite {
/*    */   public Image img;
/*    */   private boolean type = true;
/*  6 */   private byte[][] module_byte = (byte[][])null; private byte[][][] frame_byte = (byte[][][])null; private byte[][][] action_byte = (byte[][][])null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 11 */   private short[][] module_short = (short[][])null; private short[][][] frame_short = (short[][][])null, action_short = (short[][][])null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Sprite() {
/* 17 */     nullIMFA();
/*    */   } public void nullIMFA() {
/* 19 */     this.img = null;
/* 20 */     this.module_byte = (byte[][])null;
/* 21 */     this.frame_byte = (byte[][][])null;
/* 22 */     this.action_byte = (byte[][][])null;
/* 23 */     this.module_short = (short[][])null;
/* 24 */     this.frame_short = (short[][][])null;
/* 25 */     this.action_short = (short[][][])null;
/*    */   }
/*    */   public static Sprite Create(Image _img, byte[][] _module, byte[][][] _frame, byte[][][] _action) {
/* 28 */     Sprite sp = new Sprite();
/* 29 */     sp.type = true;
/* 30 */     sp.img = _img;
/* 31 */     sp.module_byte = _module;
/* 32 */     sp.frame_byte = _frame;
/* 33 */     sp.action_byte = _action;
/* 34 */     return sp;
/*    */   }
/*    */   public void Set(Image _img, byte[][] _module, byte[][][] _frame, byte[][][] _action) {
/* 37 */     nullIMFA();
/* 38 */     this.type = true;
/* 39 */     this.img = _img;
/* 40 */     this.module_byte = _module;
/* 41 */     this.frame_byte = _frame;
/* 42 */     this.action_byte = _action;
/*    */   }
/*    */   public static Sprite Create(Image _img, short[][] _module, short[][][] _frame, short[][][] _action) {
/* 45 */     Sprite sp = new Sprite();
/* 46 */     sp.type = false;
/* 47 */     sp.img = _img;
/* 48 */     sp.module_short = _module;
/* 49 */     sp.frame_short = _frame;
/* 50 */     sp.action_short = _action;
/* 51 */     return sp;
/*    */   }
/*    */   public void Set(Image _img, short[][] _module, short[][][] _frame, short[][][] _action) {
/* 54 */     nullIMFA();
/* 55 */     this.type = false;
/* 56 */     this.img = _img;
/* 57 */     this.module_short = _module;
/* 58 */     this.frame_short = _frame;
/* 59 */     this.action_short = _action;
/*    */   }
/* 61 */   public int module(int i, int j) { return this.type ? this.module_byte[i][j] : this.module_short[i][j]; }
/* 62 */   public int frame(int i, int j, int k) { return this.type ? this.frame_byte[i][j][k] : this.frame_short[i][j][k]; }
/* 63 */   public int action(int i, int j, int k) { return this.type ? this.action_byte[i][j][k] : this.action_short[i][j][k]; }
/* 64 */   public int aLength(int i) { return this.type ? (this.action_byte[i]).length : (this.action_short[i]).length; }
/* 65 */   public int aLength() { return this.type ? this.action_byte.length : this.action_short.length; } public int fLength(int i) {
/* 66 */     return this.type ? (this.frame_byte[i]).length : (this.frame_short[i]).length;
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Sprite.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */