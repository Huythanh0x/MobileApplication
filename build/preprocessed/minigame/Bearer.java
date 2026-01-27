/*     */ package minigame;
/*     */ 
/*     */ import dm.Ms;
/*     */ import dm.Sprite;
/*     */ import dm.Ui;
/*     */ import javax.microedition.lcdui.Image;
/*     */ import main.Constants_H;
/*     */ import main.GameRun;
/*     */ 
/*     */ public class Bearer
/*     */   implements MiniGame_H, Constants_H {
/*     */   GameRun gr;
/*     */   private byte state;
/*     */   private byte length;
/*     */   private byte cur;
/*     */   private byte lv;
/*     */   private short ballC0;
/*     */   private short ballC1;
/*     */   private short time0;
/*     */   private short time1;
/*     */   private short WIDTH_B;
/*  22 */   private short[][] gDate = new short[][] { { 9, 1, 10, 2, 11, 4, 27, 8, 2, -10 }, { 50, 100, 150, 200 }, { 60, 40, 30, 10 }, { 8, 6, 13, 3, 20, 5, 30, 10 } }; private short[][] ballDate = new short[][] { { 10, 20, 60, 10, 220 }, { 13, 40, 60, 12, 170 }, { 10, 40, 60, 10, 120 } }; private short[] money = new short[] { 150, 200, 300, 450 }; private short[][] xy = new short[40][7]; private short[] myxy = new short[] { 0, 275, 67, 10, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short count;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Sprite[] sp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Image img;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Sprite bsp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private byte[] ball_time = new byte[] { 20, 15, 10, 5 };
/*     */ 
/*     */ 
/*     */   
/*     */   private final byte SPEED;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nullGame() {
/*  63 */     this.gDate = (short[][])null;
/*  64 */     this.money = null;
/*  65 */     this.sp = null;
/*  66 */     this.img = null;
/*  67 */     this.ballDate = (short[][])null;
/*     */   } public void setLv(int lv_) {
/*  69 */     this.lv = (byte)lv_;
/*     */   }
/*     */   public void go(int mode, int lv_) {
/*  72 */     this.lv = (byte)lv_;
/*  73 */     this.sp = new Sprite[2];
/*  74 */     this.sp[0] = Ms.i().createSprite("data/npc2/38", true);
/*  75 */     this.img = Ms.i().createImage("data/brow/m0");
/*     */     
/*  77 */     byte[] date = Ms.i().getStream("data/gamee.data", -1);
/*  78 */     Ms.i(); Ms.skip = 0;
/*  79 */     this.bsp = Sprite.Create(Ms.i().createImage("data/map/5"), Ms.i().createShort2Array(date, 2), Ms.i().createShort3Array(date, 2), Ms.i().createShort3Array(date, 2));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     go(mode);
/*     */   }
/*     */   
/*     */   public void go(int mode) {
/*  88 */     if (mode < 1) {
/*  89 */       this.state = 0;
/*  90 */       this.length = 10;
/*  91 */       this.gr.setStringB("在限定时间里接尽量多的球到框里，最后根据框里球的分数来换取金钱。", 200, 0);
/*  92 */     } else if (mode == 1) {
/*  93 */       this.state = 1;
/*  94 */       this.count = 0;
/*  95 */       this.cur = 0;
/*  96 */       this.ballC0 = this.gDate[1][this.lv];
/*  97 */       initABall(true);
/*  98 */       for (byte i = 0; i < this.xy.length; ) { this.xy[i][5] = -1; i = (byte)(i + 1); }
/*     */     
/*     */     } 
/* 101 */   } public Bearer(GameRun gr_) { this.SPEED = 10;
/*     */     this.gr = gr_;
/* 103 */     this.WIDTH_B = 240; } public boolean key() { if (this.state == 0) {
/* 104 */       if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.money[this.lv], true)) { go(1); }
/* 105 */       else if (Ms.i().key_S2())
/* 106 */       { nullGame();
/* 107 */         return true; }
/*     */     
/* 109 */     } else if (this.state == 1) {
/* 110 */       if (Ms.i().key_Left()) {
/* 111 */         this.myxy[0] = (short)(this.myxy[0] - 10);
/* 112 */         if (this.myxy[0] < 0) this.myxy[0] = 0; 
/* 113 */       } else if (Ms.i().key_Right()) {
/* 114 */         this.myxy[0] = (short)(this.myxy[0] + 10);
/* 115 */         if (this.myxy[0] + this.myxy[2] > this.WIDTH_B - 20) this.myxy[0] = (short)(this.WIDTH_B - this.myxy[2] - 20); 
/*     */       } 
/*     */     } 
/* 118 */     return false; }
/*     */ 
/*     */   
/*     */   public void patin() {
/* 122 */     if (this.state == 0) { draw0(10, 2, 20 * this.length); }
/* 123 */     else { drawGame(); }
/*     */   
/*     */   } private void draw0(int x, int y, int h) {
/* 126 */     Ui.i().fillRectB();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     Ui.i().drawString("游戏规则", 120, y + 20, 33, 3, 1);
/* 168 */     Ui.i().drawUi(7, 82, y + 20, 40, 0);
/* 169 */     Ui.i().drawUi(7, 158, y + 20, 36, 4);
/* 170 */     Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
/* 171 */     Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 60, 4);
/* 172 */     Ui.i().drawStringY(this.gr.about_a, 19, y + 20 + 10, 20, 0, 0);
/* 173 */     int ty = y + 40 + this.gr.about_a.length * 20;
/* 174 */     Ui.i().drawString("不同种类的球的分数：", 19, ty, 0, 0, 0);
/*     */ 
/*     */ 
/*     */     
/* 178 */     ty += 40;
/*     */     
/* 180 */     for (int i = 0; i < (this.gDate[0]).length; i += 2) {
/* 181 */       this.gr.drawItem(this.gDate[0][i], 38 + ((i % 4 == 2) ? 76 : 0), ty + (i >> 2) * 25, 0);
/*     */       
/* 183 */       Ui.i().drawString(((this.gDate[0][i + 1] < 0) ? "" : "+") + this.gDate[0][i + 1], 38 + ((i % 4 == 2) ? 76 : 0) + 16, ty + (i >> 2) * 25 - 4, 0, 0, 1);
/*     */     } 
/*     */ 
/*     */     
/* 187 */     Ui.i().drawString("需要的参加费：" + this.money[this.lv] + "金", 19, y + 40 + 10 + h, 0, 0, 1);
/*     */     
/* 189 */     this.gr.drawMoney(120, 320, 3, false);
/* 190 */     Ui.i().drawYesNo(true, true);
/*     */   }
/*     */   private void drawGame() {
/* 193 */     Ui.i().fillRect(5423359, 0, 0, 240, 320);
/* 194 */     Ui.i().drawFrameOne(this.bsp, 0, this.WIDTH_B / 2, 160, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     Ui.i().drawString("分数：" + this.count, 0, 0, 20, 3, 1);
/* 205 */     Ui.i().drawString("球总数：" + this.ballC0, this.WIDTH_B, 0, 24, 3, 1);
/*     */     
/* 207 */     Ui.i().drawImage(this.img, this.WIDTH_B, 305, 40);
/*     */     byte i;
/* 209 */     for (i = 0; i < this.xy.length; i = (byte)(i + 1)) {
/* 210 */       if (this.xy[i][5] != -1) {
/* 211 */         this.gr.drawItem(this.gDate[0][this.xy[i][6] << 1], this.xy[i][0], this.xy[i][1], 0);
/*     */       }
/*     */     } 
/* 214 */     for (i = 0; i < 2; i = (byte)(i + 1)) {
/* 215 */       Ui.i().drawFrameOne(this.sp[0], (this.myxy[4] == 0) ? 3 : 4, this.myxy[0] + i * 30 + 20, this.myxy[1] + 25, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addBall(int id) {
/* 222 */     this.xy[this.cur][0] = -20;
/* 223 */     this.xy[this.cur][1] = this.ballDate[id][4];
/* 224 */     this.xy[this.cur][2] = (short)(this.ballDate[id][1] / this.ballDate[id][0]);
/* 225 */     this.xy[this.cur][4] = this.ballDate[id][0];
/* 226 */     this.xy[this.cur][5] = (short)id;
/* 227 */     this.xy[this.cur][3] = this.ballDate[id][3];
/* 228 */     Ms.i(); this.xy[this.cur][6] = (short)Ms.getRandom((this.gDate[0]).length >> 1);
/* 229 */     if ((this.cur = (byte)(this.cur + 1)) >= this.xy.length) this.cur = 0; 
/*     */   }
/*     */   private void initABall(boolean mode) {
/* 232 */     this.time0 = mode ? 10 : this.gDate[2][this.lv];
/* 233 */     this.time1 = (short)this.ball_time[this.lv];
/* 234 */     Ms.i(); this.ballC1 = (short)(Ms.getRandom(this.gDate[3][this.lv << 1]) + this.gDate[3][(this.lv << 1) + 1]);
/*     */   }
/*     */   private void getAY(int i) {
/* 237 */     if (this.xy[i][4] > 0 && this.xy[i][3] > 1) { this.xy[i][3] = (short)(this.xy[i][3] - 1); }
/* 238 */     else if (this.xy[i][4] == 0) { this.xy[i][3] = 0; }
/* 239 */     else if (this.xy[i][4] < 0 && this.xy[i][3] > -this.ballDate[this.xy[i][5]][3]) { this.xy[i][3] = (short)(this.xy[i][3] - 1); }
/*     */   
/*     */   } private boolean isSrc(int i) {
/* 242 */     boolean bb = (this.xy[i][1] > 330);
/* 243 */     if (Ms.i().isRect(this.WIDTH_B - 10, 280, 20, 10, this.xy[i][0], this.xy[i][1], 16, 16) || (this.xy[i][0] > this.WIDTH_B && this.xy[i][1] < this.myxy[1])) {
/*     */ 
/*     */       
/* 246 */       bb = true;
/* 247 */       this.count = (short)(this.count + this.gDate[0][this.xy[i][6] * 2 + 1]);
/*     */     } 
/* 249 */     return bb;
/*     */   }
/*     */   
/*     */   private boolean isCollision(int i) {
/* 253 */     boolean bb = Ms.i().isRect(this.myxy[0], this.myxy[1], this.myxy[2], this.myxy[3], this.xy[i][0], this.xy[i][1], 16, 16);
/*     */     
/* 255 */     return bb;
/*     */   }
/*     */   public void run() {
/* 258 */     if (this.state == 1)
/* 259 */     { if (this.ballC0 > 0)
/* 260 */         if (this.time0 > 0) { this.time0 = (short)(this.time0 - 1); }
/* 261 */         else if (this.time1 > 0) { this.time1 = (short)(this.time1 - 1); }
/* 262 */         else if (this.ballC1 > 0)
/* 263 */         { this.time1 = (short)this.ball_time[this.lv];
/* 264 */           Ms.i(); addBall(Ms.getRandom(this.ballDate.length));
/* 265 */           this.ballC1 = (short)(this.ballC1 - 1);
/* 266 */           this.ballC0 = (short)(this.ballC0 - 1); }
/* 267 */         else if (this.ballC1 == 0) { initABall(false); }
/*     */          
/* 269 */       if (this.myxy[4] > 0) this.myxy[4] = (short)(this.myxy[4] - 1);
/*     */       
/* 271 */       boolean bb = true;
/* 272 */       for (byte i = 0; i < this.xy.length; i = (byte)(i + 1)) {
/* 273 */         if (this.xy[i][5] != -1) {
/* 274 */           bb = false;
/* 275 */           this.xy[i][0] = (short)(this.xy[i][0] + this.xy[i][2]);
/* 276 */           this.xy[i][1] = (short)(this.xy[i][1] - this.xy[i][3]);
/* 277 */           getAY(i);
/* 278 */           this.xy[i][4] = (short)(this.xy[i][4] - 1);
/* 279 */           if (isSrc(i)) {
/* 280 */             this.xy[i][5] = -1;
/* 281 */           } else if (isCollision(i)) {
/* 282 */             this.xy[i][4] = this.ballDate[this.xy[i][5]][0];
/* 283 */             this.myxy[4] = 1;
/* 284 */             this.xy[i][3] = this.ballDate[this.xy[i][5]][3];
/*     */           } 
/*     */         } 
/* 287 */       }  if (this.ballC0 < 1 && bb) {
/* 288 */         this.count = (short)(this.count * 15 / 10);
/* 289 */         this.gr.money += this.count;
/* 290 */         this.gr.say("获得：" + this.count + "金", 0);
/* 291 */         if (this.count > 0) this.gr.rmsOther[6] = (byte)(this.gr.rmsOther[6] | 1 << this.lv); 
/* 292 */         this.state = 2;
/*     */       }  }
/* 294 */     else if (this.state == 2 && this.gr.say_c == 0) { go(0); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/minigame/Bearer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */