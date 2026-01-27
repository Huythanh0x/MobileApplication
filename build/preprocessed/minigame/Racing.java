/*     */ package minigame;
/*     */ 
/*     */ import dm.Ms;
/*     */ import dm.Sprite;
/*     */ import dm.Ui;
/*     */ import javax.microedition.lcdui.Image;
/*     */ import main.Constants_H;
/*     */ import main.GameRun;
/*     */ 
/*     */ 
/*     */ public class Racing
/*     */   implements MiniGame_H, Constants_H
/*     */ {
/*  14 */   private final byte NUM = 4;
/*  15 */   private final short MAP_HEIGHT = 450; GameRun gr; private final short WIN_HEIGHT = 70;
/*     */   public Racing(GameRun gr_) {
/*  17 */     this.gr = gr_;
/*  18 */   } private byte[][] now_a = new byte[4][3]; private byte[] myDate = new byte[] { 0, 0, 0, -1 }; private byte sel;
/*     */   private byte state;
/*     */   private byte length;
/*     */   private byte time;
/*     */   private byte lv;
/*     */   private byte speedLv;
/*     */   private Sprite[] sp;
/*     */   private Image imgCloud;
/*     */   private short srcY;
/*  27 */   private short[][] cloud = new short[10][3]; private short[] monY = new short[4]; private short[] money = new short[] { 100, 120, 300, 350, 1000, 1200 }; private short[][] gDate = new short[][] { { 450, 3, 8 }, { 390, 4, 9 }, { 270, 7, 6 }, { 210, 6, 10 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nullGame() {
/*  34 */     this.sp = null;
/*  35 */     this.myDate = null;
/*  36 */     this.money = null;
/*  37 */     this.now_a = (byte[][])null;
/*  38 */     this.monY = null;
/*  39 */     this.imgCloud = null;
/*  40 */     this.cloud = (short[][])null;
/*  41 */     this.gDate = (short[][])null;
/*     */   }
/*     */   
/*     */   private void draw0(int x, int y, int h, int fh) {
/*  45 */     Ui.i().fillRectB();
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
/*  64 */     Ui.i().drawString("游戏规则", 120, y + fh, 33, 3, 1);
/*  65 */     Ui.i().drawUi(7, 82, y + fh, 40, 0);
/*  66 */     Ui.i().drawUi(7, 158, y + fh, 36, 4);
/*  67 */     Ui.i().drawK(x - 1, y + fh + 10, 240 - (x << 1) + 2, h, 4);
/*  68 */     Ui.i().drawK(x, y + fh + 10 + h + 5, 240 - (x << 1), fh * 3, 4);
/*  69 */     Ui.i().drawStringY(this.gr.about_a, 12, y + fh + 15, fh, 0, 0);
/*  70 */     Ui.i().drawString("请选择押注的大小：", 19, y + fh + 10 + h + 7, 0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     byte i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     for (i = 0; i < this.length; i = (byte)(i + 1)) {
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
/* 112 */       Ui.i().drawK(33 + i * 67, y + fh * 2 + 10 + h + 15, 38, fh + 4, 1);
/* 113 */       Ui.i().drawString(this.gr.about_b[i].toString(), 35 + i * 67, y + fh * 2 + 10 + h + 15, 0, 3, 0);
/*     */     } 
/*     */     
/* 116 */     Ui.i().drawK4(33 + this.sel * 67, y + fh * 2 + 10 + h + 15, 38, fh + 4);
/* 117 */     Ui.i().drawTriangle(120, y + fh * 3 + 10 + h + 7, 110, true, true);
/*     */ 
/*     */     
/* 120 */     this.gr.drawMoney(120, 320, 3, false);
/* 121 */     Ui.i().drawYesNo(true, true);
/*     */   }
/*     */   
/*     */   private void draw1(int x, int y, int h) {
/* 125 */     Ui.i().fillRectB();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     Ui.i().drawK(x, y, 240 - (x << 1), 300 - (y << 1), 4);
/* 213 */     Ui.i().drawString("请选择押注的宠物：", 19, y + 5, 0, 0, 0); byte i;
/* 214 */     for (i = 0; i < this.length; i = (byte)(i + 1)) {
/* 215 */       Ui.i().drawK(19 + i * 52, y + 20 + 10, 44, h, 1);
/* 216 */       Ui.i().drawActionOne(this.sp[0], 0, 19 + i * 52 + 22, y + 20 + 8 + h, this.now_a[i], 0, (this.state == 1) ? ((this.sel == i)) : false);
/*     */       
/* 218 */       Ui.i().drawNum("" + (i + 1), 19 + i * 52 + 27, y + 40 + 12, 20, 2);
/*     */     } 
/* 220 */     if (this.state == 1) {
/* 221 */       Ui.i().drawK4(19 + this.sel * 52, y + 20 + 10, 44, 76);
/* 222 */       Ui.i().drawTriangle(120, y + 20 + 49, 118, true, true);
/*     */     } 
/*     */     
/* 225 */     Ui.i().drawString("兴奋度：", 19, y + 20 + 13 + h, 0, 0, 0);
/* 226 */     this.gr.drawFealty(6, 2, 76, y + 20 + 19 + h);
/* 227 */     Ui.i().drawStringY(this.gr.about_a, 19, y + 40 + 19 + h, 20, 0, 0);
/* 228 */     h = 59 + h + 20 + this.gr.about_a.length * 20;
/* 229 */     Ui.i().drawString("请选择道具：", 19, y + h, 0, 0, 0);
/* 230 */     h = h + 20 + 3;
/* 231 */     for (i = 0; i < this.length; i = (byte)(i + 1)) {
/* 232 */       Ui.i().drawK(19 + i * 52, y + h, 44, 34, 1);
/* 233 */       this.gr.drawItem(12, 19 + i * 52 + 5, y + h + 10, 0);
/* 234 */       Ui.i().drawString("x" + i, 19 + i * 52 + 21, y + h + 8, 0, 3, 1);
/*     */     } 
/* 236 */     if (this.state == 2) {
/* 237 */       Ui.i().drawK4(19 + this.sel * 52, y + h, 44, 34);
/* 238 */       Ui.i().drawTriangle(120, y + h + 14, 118, true, true);
/*     */     } 
/*     */ 
/*     */     
/* 242 */     this.gr.drawMoney(120, 320, 3, false);
/* 243 */     Ui.i().drawYesNo(true, (this.state != 1));
/*     */   }
/*     */   private void drawGame() {
/* 246 */     Ui.i().fillRect(5423359, 0, 0, 240, 320);
/* 247 */     drawCloud();
/* 248 */     drawEnd();
/*     */     
/* 250 */     for (byte i = 0; i < this.length; i = (byte)(i + 1)) {
/* 251 */       Ui.i().drawNum("" + (i + 1), 19 + i * 52 + 22, 20 - this.srcY, 1, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       Ui.i().drawActionOne(this.sp[0], 1, 19 + i * 52 + 22, this.monY[i] - this.srcY, this.now_a[i], 0, true);
/*     */     } 
/*     */ 
/*     */     
/* 260 */     if (this.state == 3 && this.time != 0)
/* 261 */       Ui.i().drawNum("" + (this.time / 20), 115, 160, 0, 2); 
/*     */   }
/*     */   
/*     */   private void setSpeed() {
/* 265 */     if (this.speedLv >= this.gDate.length)
/*     */       return;  byte i;
/* 267 */     for (i = 0; i < 4 && this.monY[i] > this.gDate[this.speedLv][0]; i = (byte)(i + 1));
/* 268 */     if (i >= 4)
/* 269 */       return;  for (i = 0; i < 4; i = (byte)(i + 1)) {
/* 270 */       Ms.i(); this.now_a[i][2] = (byte)(Ms.getRandom(this.gDate[this.speedLv][1]) + this.gDate[this.speedLv][1]);
/* 271 */     }  this.speedLv = (byte)(this.speedLv + 1);
/*     */   }
/*     */   public void go(int mode, int lv_) {
/* 274 */     this.lv = (byte)lv_;
/* 275 */     this.sp = new Sprite[3];
/* 276 */     this.sp[0] = Ms.i().createSprite("data/npc0/67", true);
/* 277 */     this.sp[1] = Ms.i().createSprite("data/brow/m1", true);
/* 278 */     this.imgCloud = Ms.i().createImage("data/cloud");
/* 279 */     go(mode);
/*     */   }
/*     */   public void go(int mode) {
/* 282 */     if (mode < 1) {
/* 283 */       this.sel = 0;
/* 284 */       this.state = 0;
/* 285 */       this.length = 3;
/*     */ 
/*     */ 
/*     */       
/* 289 */       this.gr.setStringB("选择你认为会得第一的一只宠物和押注的大小，注数越大获胜后的奖励越丰厚！#n一注：投入100金，获胜后得120金。#n二注：投入300金，获胜后得350金。#n三注：投入1000金，获胜后得1200金。", 200, 0);
/*     */       
/* 291 */       this.gr.setStringB("一注#n二注#n三注", 200, 1);
/* 292 */     } else if (mode == 1) {
/* 293 */       this.myDate[0] = this.sel;
/* 294 */       this.sel = 0;
/* 295 */       this.state = 1;
/* 296 */       this.length = 4;
/* 297 */       this.gr.setStringB("每喂一个兴奋菇花费10金，可增加一格兴奋度。", 200, 0);
/* 298 */     } else if (mode == 2) {
/* 299 */       this.myDate[1] = this.sel;
/* 300 */       this.sel = 0;
/* 301 */       this.state = 2;
/* 302 */     } else if (mode == 3) {
/* 303 */       this.myDate[2] = this.sel;
/* 304 */       this.sel = 0;
/* 305 */       this.state = 3;
/* 306 */       this.srcY = 130;
/* 307 */       this.speedLv = 0;
/* 308 */       this.myDate[3] = -1;
/* 309 */       this.time = 70;
/*     */       byte i;
/* 311 */       for (i = 0; i < this.length; ) { this.monY[i] = 450; i = (byte)(i + 1); }
/* 312 */        for (i = 0; i < this.cloud.length; ) { addCloud(i); i = (byte)(i + 1); }
/*     */     
/*     */     } 
/*     */   } public boolean key() {
/* 316 */     if (Ms.i().key_delay()) return false; 
/* 317 */     if (this.state == 0) {
/* 318 */       if (Ms.i().key_Left_Right()) { this.sel = Ms.i().select(this.sel, 0, this.length - 1); }
/* 319 */       else if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.money[this.sel << 1], true)) { go(1); }
/* 320 */       else if (Ms.i().key_S2())
/* 321 */       { nullGame();
/* 322 */         return true; }
/*     */     
/* 324 */     } else if (this.state == 1) {
/* 325 */       if (Ms.i().key_Left_Right()) { this.sel = Ms.i().select(this.sel, 0, this.length - 1); }
/* 326 */       else if (Ms.i().key_S1_Num5()) { go(2); } 
/* 327 */     } else if (this.state == 2) {
/* 328 */       if (Ms.i().key_Left_Right()) { this.sel = Ms.i().select(this.sel, 0, this.length - 1); }
/* 329 */       else if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.sel * 10, true)) { go(3); }
/* 330 */       else if (Ms.i().key_S2())
/* 331 */       { this.sel = this.myDate[1];
/* 332 */         this.state = 1; }
/*     */     
/* 334 */     } else if (this.state == 3) {
/*     */     
/* 336 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void patin() {
/* 346 */     if (this.state == 0) { draw0(10, 5, 200, 17); }
/* 347 */     else if (this.state == 1 || this.state == 2) { draw1(10, 15, 76); }
/*     */     else
/* 349 */     { drawGame(); }
/*     */   
/*     */   } public void run() {
/* 352 */     if (this.state == 3) {
/* 353 */       if (this.time == 0)
/* 354 */       { setSpeed();
/* 355 */         if (this.myDate[3] != -1) {
/* 356 */           this.time = 5;
/* 357 */           this.state = 4;
/*     */           return;
/*     */         } 
/* 360 */         byte j = -1;
/* 361 */         for (byte i = 0; i < 4; i = (byte)(i + 1)) {
/* 362 */           this.monY[i] = (short)(this.monY[i] - this.now_a[i][2]);
/* 363 */           if (this.monY[i] - this.srcY < 160) j = i; 
/* 364 */           if (this.monY[i] < 70 && this.myDate[3] == -1) this.myDate[3] = i; 
/*     */         } 
/* 366 */         if (j != -1) this.srcY = (short)(this.srcY - this.now_a[j][2]); 
/* 367 */         if (this.srcY < 0) this.srcY = 0;  }
/* 368 */       else { this.time = (byte)(this.time - 1); } 
/* 369 */     } else if (this.state == 4) {
/* 370 */       if (this.time == 3)
/* 371 */         if (this.myDate[3] == this.myDate[1])
/* 372 */         { this.gr.money += this.money[this.myDate[0] * 2 + 1];
/* 373 */           this.gr.say("获得：" + this.money[this.myDate[0] * 2 + 1] + "金", 0);
/* 374 */           this.gr.rmsOther[4] = (byte)(this.gr.rmsOther[4] | 1 << this.lv); }
/* 375 */         else { this.gr.say("不好意思，没有猜对。", 0); }
/*     */          
/* 377 */       if (this.time > 0) { this.time = (byte)(this.time - 1); }
/* 378 */       else if (this.time == 0 && this.gr.say_c == 0)
/* 379 */       { go(0); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addCloud(int i) {
/* 386 */     Ms.i(); this.cloud[i][0] = (short)(240 + Ms.getRandom(120));
/* 387 */     Ms.i(); this.cloud[i][1] = (short)(this.srcY - 80 + i * (Ms.getRandom(25) + 55));
/* 388 */     Ms.i(); this.cloud[i][2] = (short)(Ms.getRandom(5) + 2);
/*     */   }
/*     */   private void drawCloud() {
/*     */     byte i;
/* 392 */     for (i = 0; i < this.cloud.length; i = (byte)(i + 1))
/* 393 */       Ui.i().drawImage(this.imgCloud, this.cloud[i][0], this.cloud[i][1] - this.srcY, 0); 
/* 394 */     for (i = 0; i < this.cloud.length; i = (byte)(i + 1)) {
/* 395 */       if (isNpcSrc(this.cloud[i][0], this.cloud[i][1])) { addCloud(i); }
/* 396 */       else { this.cloud[i][0] = (short)(this.cloud[i][0] - this.cloud[i][2]); }
/*     */     
/*     */     } 
/*     */   } private boolean isNpcSrc(int ax, int ay) {
/* 400 */     return (ax < -40 || ay - this.srcY > 340);
/*     */   }
/*     */   
/*     */   private void drawEnd() {
/* 404 */     byte n = 5;
/* 405 */     Ui.i().drawModuleOne(this.sp[1], 0, 0, 40 - this.srcY, 0, 0);
/* 406 */     for (byte i = 0; i < n; i = (byte)(i + 1))
/* 407 */       Ui.i().drawModuleOne(this.sp[1], 1, 35 + i * 35, 44 - this.srcY, 0, 0); 
/* 408 */     Ui.i().drawModuleOne(this.sp[1], 0, 240, 40 - this.srcY, 1, 0);
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/minigame/Racing.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */