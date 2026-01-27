/*     */ package minigame;
/*     */ import dm.Ms;
/*     */ import dm.Ui;
/*     */ 
/*     */ public class Cards implements MiniGame_H, Constants_H {
/*     */   GameRun gr;
/*     */   private short count;
/*     */   private short gx;
/*     */   private short gy;
/*     */   private short gw;
/*     */   private short gh;
/*     */   private short gspace;
/*     */   private short wh;
/*     */   private short hh;
/*     */   private int sell_money;
/*     */   private byte[][] findN;
/*     */   
/*     */   public Cards(GameRun gr_) {
/*  19 */     this.gw = 30; this.gh = 40; this.gspace = 4; this.wh = 120; this.hh = 145;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.time = 3; this.find_count = 3; this.num = new byte[][] { { 3, 4 }, { 3, 6 }, { 4, 6 }, { 5, 6 } };
/*     */     this.gr = gr_;
/*     */   }
/*     */   private byte[][] findC;
/*     */   private byte time;
/*     */   private byte find_count;
/*     */   private byte lv;
/*     */   private byte state;
/*     */   private byte length;
/*     */   private byte selx;
/*     */   private byte sely;
/*     */   private byte tx;
/*     */   private byte ty;
/*     */   private byte[][] num;
/*     */   
/*     */   public void go(int mode, int lv_) {
/*  45 */     this.gr.setStringB(this.gr.createString("data/gamec.d"), 183, 0);
/*  46 */     this.lv = (byte)lv_;
/*  47 */     this.findN = (byte[][])null;
/*  48 */     this.findN = new byte[this.num[this.lv][0]][this.num[this.lv][1]];
/*  49 */     this.findC = (byte[][])null;
/*  50 */     this.findC = new byte[this.num[this.lv][0]][this.num[this.lv][1]];
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.length = 9;
/*     */     
/*  56 */     go(mode);
/*     */   }
/*     */   
/*     */   public void go(int mode) {
/*  60 */     this.gr.line_max = 9;
/*  61 */     this.count = 100;
/*  62 */     this.time = (byte)(this.lv * 2 + 4);
/*  63 */     this.find_count = (byte)(this.num[this.lv][0] * this.num[this.lv][1] / 2);
/*  64 */     if (mode < 1) {
/*  65 */       this.ty = 0;
/*  66 */       this.state = 0;
/*  67 */       this.sell_money = this.lv * 200 + 200;
/*     */     } else {
/*  69 */       this.gx = (short)(-((this.gw + this.gspace) * this.num[this.lv][1]) >> 1);
/*  70 */       this.gy = (short)(-((this.gh + this.gspace) * this.num[this.lv][0]) >> 1);
/*     */ 
/*     */ 
/*     */       
/*  74 */       this.tx = this.ty = 0;
/*  75 */       this.state = 1;
/*  76 */       byte i = 0, randN[] = new byte[this.find_count];
/*  77 */       for (; i < this.findC.length; i = (byte)(i + 1)) {
/*  78 */         for (byte j = 0; j < (this.findC[i]).length; j = (byte)(j + 1)) {
/*  79 */           this.findC[i][j] = 2;
/*  80 */           this.findN[i][j] = 0;
/*     */         } 
/*     */       } 
/*  83 */       i = 0;
/*  84 */       while (i < randN.length) {
/*  85 */         Ms.i(); byte rd = (byte)(Ms.getRandom(35) + 1); byte j;
/*  86 */         for (j = (byte)(randN.length - 1); j > -1 && 
/*  87 */           randN[j] != rd; j = (byte)(j - 1));
/*  88 */         if (j == -1) {
/*  89 */           randN[i] = rd;
/*  90 */           i = (byte)(i + 1);
/*     */         } 
/*     */       } 
/*  93 */       for (i = 0; i < randN.length; i = (byte)(i + 1)) {
/*  94 */         for (byte j = 0; j < 2; ) {
/*     */           while (true) {
/*  96 */             Ms.i(); byte k = (byte)Ms.getRandom(this.num[this.lv][0] * this.num[this.lv][1]);
/*  97 */             if (this.findN[k / this.num[this.lv][1]][k % this.num[this.lv][1]] == 0)
/*  98 */             { this.findN[k / this.num[this.lv][1]][k % this.num[this.lv][1]] = randN[i]; break; } 
/*     */           }  j = (byte)(j + 1);
/*     */         } 
/* 101 */       }  randN = null;
/*     */     } 
/*     */   }
/*     */   private void nullGame() {
/* 105 */     this.findN = (byte[][])null;
/* 106 */     this.findC = (byte[][])null;
/* 107 */     this.num = (byte[][])null;
/*     */   }
/*     */   public boolean key() {
/* 110 */     if (Ms.i().key_delay()) return false; 
/* 111 */     if (this.state == 0) {
/* 112 */       if (Ms.i().key_Up_Down())
/* 113 */       { this.ty = Ms.i().select(this.ty, 0, this.length);
/* 114 */         if (this.ty < 0) this.ty = 0;  }
/* 115 */       else { if (Ms.i().key_S2()) {
/* 116 */           Ms.i().keyRelease();
/* 117 */           nullGame();
/* 118 */           return true;
/* 119 */         }  if (Ms.i().key_S1_Num5())
/* 120 */         { Ms.i().keyRelease();
/* 121 */           if (this.gr.isMoney(this.sell_money, true)) go(1, this.lv);  }  }
/*     */     
/* 123 */     } else if (this.state < 3) {
/* 124 */       if (Ms.i().key_Up_Down()) {
/* 125 */         this.ty = Ms.i().select(this.ty, 0, this.findN.length - 1);
/* 126 */       } else if (Ms.i().key_Left_Right()) {
/* 127 */         this.tx = Ms.i().select(this.tx, 0, (this.findN[this.ty]).length - 1);
/* 128 */       } else if (Ms.i().key_S1_Num5()) {
/* 129 */         Ms.i().keyRelease();
/* 130 */         if (this.state == 0) {
/* 131 */           go(0, this.lv);
/*     */         } else {
/* 133 */           this.findC[this.ty][this.tx] = (byte)(this.findC[this.ty][this.tx] + 1);
/* 134 */           if (this.findC[this.ty][this.tx] == 0 && (this.state = (byte)(this.state + 1)) == 2) {
/* 135 */             this.selx = this.tx;
/* 136 */             this.sely = this.ty;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   public void patin() {
/* 145 */     if (this.state == 0) { draw0(10, 2, 20 * this.length); }
/* 146 */     else { drawGame(10, 2, 20 * (this.length + 3)); }
/*     */   
/*     */   }
/*     */   public void run() {
/* 150 */     if (this.time < 1) {
/* 151 */       if (this.time == 0) {
/* 152 */         this.time = -1;
/* 153 */         this.gr.say("游戏失败！！！", -1);
/* 154 */       } else if (this.time == -1 && this.gr.say_c == 0) {
/* 155 */         go(0, this.lv);
/*     */       } 
/* 157 */     } else if (this.find_count < 1) {
/* 158 */       if (this.find_count == 0) {
/* 159 */         this.find_count = -1;
/* 160 */         this.gr.money += this.time * 125 + 100;
/* 161 */         this.gr.say("获得金钱：" + (this.time * 125 + 100), -1);
/* 162 */         this.gr.rmsOther[8] = (byte)(this.gr.rmsOther[8] | 1 << this.lv);
/*     */       }
/* 164 */       else if (this.find_count == -1 && this.gr.say_c == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 171 */         go(0, this.lv);
/*     */       } 
/* 173 */     } else if (this.state >= 3) {
/* 174 */       if ((this.state = (byte)(this.state + 1)) > 10) { this.state = 1; }
/* 175 */       else if (this.state == 10)
/* 176 */       { if (this.findN[this.ty][this.tx] != this.findN[this.sely][this.selx])
/* 177 */         { this.findC[this.ty][this.tx] = 3;
/* 178 */           this.findC[this.sely][this.selx] = 3;
/* 179 */           this.time = (byte)(this.time - 1); }
/* 180 */         else { this.find_count = (byte)(this.find_count - 1); }  }
/*     */     
/* 182 */     } else if (this.count > 0 && (
/* 183 */       this.count = (short)(this.count - 1)) == 0) {
/* 184 */       byte i = 0;
/* 185 */       for (; i < this.findC.length; i = (byte)(i + 1)) {
/* 186 */         for (byte j = 0; j < (this.findC[i]).length; j = (byte)(j + 1)) {
/* 187 */           this.findC[i][j] = 3;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw0(int x, int y, int h) {
/* 195 */     Ui.i().fillRectB();
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
/* 213 */     Ui.i().drawString("游戏规则", 120, y + 20, 33, 3, 1);
/* 214 */     Ui.i().drawUi(7, 82, y + 20, 40, 0);
/* 215 */     Ui.i().drawUi(7, 158, y + 20, 36, 4);
/* 216 */     Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
/* 217 */     Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 60, 4);
/* 218 */     Ui.i().drawStringY(this.gr.about_a, 19, y + 20 + 10, 20, 0, 0);
/* 219 */     Ui.i().drawString("需要的参加费：" + this.sell_money + "金", 19, y + 40 + 10 + h, 0, 0, 1);
/*     */     
/* 221 */     this.gr.drawMoney(120, 320, 3, false);
/* 222 */     Ui.i().drawYesNo(true, true);
/*     */   }
/*     */   private void drawGame(int x, int y, int h) {
/* 225 */     Ui.i().fillRectB();
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
/* 242 */     Ui.i().drawUi(7, 82, y + 20, 40, 0);
/* 243 */     Ui.i().drawUi(7, 158, y + 20, 36, 4);
/* 244 */     Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
/* 245 */     Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 40, 4);
/*     */     
/* 247 */     for (int i = 0; i < this.findN.length; i++) {
/* 248 */       for (int j = 0; j < (this.findN[i]).length; j++)
/*     */       {
/* 250 */         drawCard(i, j, this.wh + this.gx + j * (this.gw + this.gspace), this.hh + this.gy + i * (this.gh + this.gspace), (this.findC[i][j] == 3) ? 1 : this.findC[i][j]); } 
/*     */     } 
/* 252 */     Ui.i().drawRectZ(15400191, this.wh + this.gx + this.tx * (this.gw + this.gspace), this.hh + this.gy + this.ty * (this.gh + this.gspace), this.gw, this.gh, 3);
/* 253 */     if (this.count > 0) { Ui.i().drawNum("" + (this.count / 10), 130, 24, 36, 2);
/*     */        }
/*     */     
/*     */     else
/*     */     
/* 258 */     { Ui.i().drawString("游戏开始", 120, 4, 17, 3, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       Ui.i().drawString("可错次数：" + ((this.time < 0) ? 0 : (this.time - 1)) + "次", 120, 287, 17, 0, 0); }
/*     */   
/*     */   }
/*     */   
/*     */   private void drawCard(int i, int j, int x, int y, int mode) {
/* 269 */     if (mode == 0) {
/* 270 */       Ui.i().drawK(x, y, this.gw, this.gh, 1);
/* 271 */       Ui.i().drawUi(25, x + (this.gw >> 1), y + (this.gh >> 1), 3, 0);
/* 272 */     } else if (mode == 1) {
/* 273 */       Ui.i().drawK(x + (this.gw >> 1) - 2, y - 3, 5, this.gh + 6, 1);
/* 274 */       this.findC[i][j] = (byte)(this.findC[i][j] + 1); if ((byte)(this.findC[i][j] + 1) > 3) this.findC[i][j] = 0; 
/* 275 */       mode = 2;
/*     */     } else {
/* 277 */       Ui.i().drawK(x, y, this.gw, this.gh, 5);
/* 278 */       this.gr.drawItem(this.findN[i][j], x + (this.gw >> 1), y + (this.gh >> 1), 3);
/* 279 */       Ui.i().drawNum("" + this.findN[i][j], x + this.gw, y + this.gh, 40, 0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/minigame/Cards.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */