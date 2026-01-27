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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Guess
/*     */   implements MiniGame_H, Constants_H
/*     */ {
/*     */   GameRun gr;
/*     */   final short TIME;
/*     */   final short TIME2;
/*     */   final short BOX_Y1;
/*     */   final short MON_Y;
/*     */   private Image[] imgBox;
/*     */   private Sprite[] imgMon;
/*     */   private int sell_money;
/*     */   private int[][] box;
/*     */   private byte mon_size_move;
/*     */   private byte time;
/*     */   private byte state;
/*     */   private byte gameR;
/*     */   private byte winNum;
/*     */   private byte transNum;
/*     */   private byte maxTransNum;
/*     */   private byte tempTransNum;
/*     */   private byte b_win;
/*     */   private byte[] boxNum;
/*     */   private byte length;
/*     */   private byte lv;
/*     */   private byte[][] gameData;
/*     */   
/*     */   public Guess(GameRun gr_) {
/*  42 */     this.TIME = 20; this.TIME2 = 40; this.BOX_Y1 = 150; this.MON_Y = 165;
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
/* 292 */     this.mon_size_move = 0; this.state = 0; this.gameR = 0; this.winNum = 0; this.transNum = 0; this.maxTransNum = 20; this.tempTransNum = 0; this.gr = gr_;
/*     */   }
/*     */   public void patin() { Ui.i().fillRectB(); Ui.i().drawUi(7, 44, 22, 40, 0); Ui.i().drawUi(7, 196, 22, 36, 4); if (this.state == 0) { draw(); }
/*     */     else { drawGame(); }
/*     */      }
/*     */   private void drawGame() { int x = 2, y = 27, w = 236, h = 316, fh = 24; int wh = 300 - fh * 2; Ui.i().drawK(x, y, w, h, 4); x += 2; y += fh; w -= 4; h -= y - 10; Ui.i().drawK(x, y, w, h, 4); Ui.i().drawString("胜利次数：" + this.winNum + "/" + this.gameData[3][this.lv], 120, 4, 17, 3, 1); if (this.state == 1) { Ui.g.setClip(0, 65, 240, 100); int i; for (i = 0; i < this.box.length; i++) { if (this.box[i][1] != -1)
/*     */           drawMon(i, this.box[i][1], this.box[i][2], 165 + ((this.time < 40) ? 0 : this.mon_size_move));  }
/*     */        Ui.g.setClip(0, 0, 240, 320); if (this.time >= 20)
/*     */         for (i = 0; i < this.box.length; i++)
/*     */           Ui.i().drawImage(this.imgBox[1], this.box[i][2], this.box[i][3], 17);   }
/*     */     else if (this.state == 2) { for (int i = 0; i < this.box.length; i++)
/*     */         Ui.i().drawImage(this.imgBox[1], this.box[i][2], this.box[i][3], 17);  }
/*     */     else if (this.state == 3) { for (int i = 0; i < this.box.length; i++)
/*     */         Ui.i().drawImage(this.imgBox[1], this.box[i][2], this.box[i][3], 17);  Ui.i().drawImage(this.imgBox[0], this.box[this.gr.selectx][2] - 1, this.box[this.gr.selectx][3] - ((this.time % 4 <= 2) ? 5 : 0), 17); if ((this.time = (byte)(this.time + 1)) > 20)
/*     */         this.time = 0;  drawMon(this.box[this.gr.selecty][0], this.box[this.gr.selecty][1], this.gameData[0][1] + 100, 135); Ui.i().drawString("请指出" + this.gr.getNameMon(this.box[this.gr.selecty][1]) + "所在箱子", 120, wh, 17, 0, 0); }
/*     */     else if (this.state == 4) { drawMon(this.box[this.gr.selecty][0], this.box[this.gr.selecty][1], this.gameData[0][1] + 100, 135); Ui.i().drawImage(this.imgBox[1], this.box[this.gr.selectx][2], this.box[this.gr.selectx][3], 17); if (this.box[this.gr.selectx][1] != -1)
/*     */         drawMon(this.box[this.gr.selectx][0], this.box[this.gr.selectx][1], this.box[this.gr.selectx][2], this.box[this.gr.selectx][3] + 35);  if (this.b_win > 0) { Ui.i().drawString("您真厉害！", 120, wh, 17, 0, 0); }
/*     */       else
/*     */       { Ui.i().drawString("说不定下次就能行的。", 120, wh, 17, 0, 0); }
/*     */        }
/* 312 */      } private void draw() { Ui.i().drawString("游戏规则", 120, 4, 17, 3, 1); int x = 2, y = 28, w = 236, fh = 24; int h = fh * this.gr.line_max; Ui.i().drawK(x, y, w, h, 4); Ui.i().drawStringY(this.gr.about_a, x + 19, y + 4, fh, 0, 0); y += h + 5; h = 320 - y - 20; Ui.i().drawK(x, y, w, h, 4); Ui.i().drawYesNo(true, true); Ui.i().drawString("需要的参加费：" + this.sell_money + "金", x + 19, y + 4, 0, 0, 0); this.gr.drawMoney(120, 320, 3, false); } public void go(int mode, int lv_) { this.gr.setStringB(this.gr.createString("data/gamed.d"), 183, 0);
/* 313 */     this.lv = (byte)lv_;
/* 314 */     this.imgBox = new Image[2];
/* 315 */     this.imgBox[0] = Ms.i().createImage("data/brow/m2");
/* 316 */     this.imgBox[1] = Ms.i().createImage("data/brow/m3");
/* 317 */     this.boxNum = new byte[2];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     this.gameData = new byte[][] { { -60, 17, 90 }, { 8, 13, 20, 30 }, { 1, 2, 3, 3 }, { 6, 4, 2, 2 }, { 83, 30, 55 } };
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
/* 338 */     this.box = new int[3][4];
/* 339 */     this.imgMon = new Sprite[3];
/* 340 */     go(mode); }
/*     */   private void drawMon(int i, int id, int x, int y) { Ui.i().drawFrameOne(this.imgMon[i], this.imgMon[i].action(this.gr.mList_id[id][1] * 3, 0, 0), x, y, 0); }
/*     */   public void run() { if (this.state == 1) { if (this.time < 20) { this.time = (byte)(this.time + 1); } else if (this.box[0][3] < 150) { for (byte i = 0; i < 3; i = (byte)(i + 1)) this.box[i][3] = this.box[i][3] + 25;  } else if ((this.time = (byte)(this.time + 1)) >= 40) { if (this.mon_size_move < 40) { this.mon_size_move = (byte)(this.mon_size_move + 5); } else { this.state = 2; this.time = 0; }  }  } else if (this.state == 2) { if (this.time <= 0) { if (this.transNum < this.maxTransNum) { this.time = 1; this.transNum = (byte)(this.transNum + 1); Ms.i(); this.boxNum[0] = (byte)Ms.getRandom(3); do { Ms.i(); this.boxNum[1] = (byte)Ms.getRandom(3); } while (this.boxNum[1] == this.boxNum[0]); if (this.box[this.boxNum[1]][2] < this.box[this.boxNum[0]][2]) { this.gameR = this.boxNum[1]; this.boxNum[1] = this.boxNum[0]; this.boxNum[0] = this.gameR; }  Ms.i(); this.gameR = (byte)(Ms.abs(this.box[this.boxNum[1]][2] - this.box[this.boxNum[0]][2]) / 2); this.tempTransNum = (byte)(this.gameR * 2 / this.gameData[1][this.lv]); } else { this.time = 0; this.state = 3; do { Ms.i(); this.gr.selecty = (byte)Ms.getRandom(this.box.length); } while (this.box[this.gr.selecty][1] == -1); }  } else if (this.time > this.tempTransNum) { this.time = 0; int[] temp = new int[4]; byte i; for (i = 0; i < this.box.length; i = (byte)(i + 1)) { for (byte j = 0; j < this.box.length; j = (byte)(j + 1)) { if (this.box[j][2] > this.box[i][2]) { System.arraycopy(this.box[i], 0, temp, 0, temp.length); System.arraycopy(this.box[j], 0, this.box[i], 0, (this.box[j]).length); System.arraycopy(temp, 0, this.box[j], 0, temp.length); }  }  }  for (i = 0; i < 3; i = (byte)(i + 1)) { this.box[i][2] = this.gameData[0][i] + 100; this.box[i][3] = 150; }  } else { this.time = (byte)(this.time + 1); this.box[this.boxNum[0]][2] = this.box[this.boxNum[0]][2] + this.gameR * 2 / this.tempTransNum; int temp = this.gameR * 2 / this.tempTransNum * this.time; this.box[this.boxNum[0]][3] = 150 - Ms.i().sqrt(this.gameR * this.gameR - (temp - this.gameR) * (temp - this.gameR)); this.box[this.boxNum[1]][2] = this.box[this.boxNum[1]][2] - this.gameR * 2 / this.tempTransNum; this.box[this.boxNum[1]][3] = 150 + Ms.i().sqrt(this.gameR * this.gameR - (temp - this.gameR) * (temp - this.gameR)); }  } else if (this.state == 4 && ((this.gr.selectx == 2 && this.box[this.gr.selectx][2] > this.gameData[0][1] + 100) || (this.gr.selectx == 0 && this.box[this.gr.selectx][2] < this.gameData[0][1] + 100))) { this.box[this.gr.selectx][2] = this.box[this.gr.selectx][2] + (this.gameData[0][1] - this.gameData[0][this.gr.selectx]) / 7; }  }
/*     */   public boolean key() { if (Ms.i().key_delay()) return false;  if (this.state == 0) { if (Ms.i().key_Up_Down()) { this.gr.selecty = Ms.i().select(this.gr.selecty, 0, this.length); if (this.gr.selecty < 0) this.gr.selecty = 0;  } else { if (Ms.i().key_S2()) { Ms.i().keyRelease(); this.gr.about_a = null; this.imgBox = null; this.boxNum = null; this.gameData = (byte[][])null; this.box = (int[][])null; this.imgMon = null; return true; }  if (Ms.i().key_S1_Num5()) { Ms.i().keyRelease(); if (this.gr.isMoney(this.sell_money, true)) go(1, this.lv);  }  }  } else if (this.state == 3) { if (Ms.i().key_Left_Right()) { this.gr.selectx = Ms.i().select(this.gr.selectx, 0, this.box.length - 1); } else if (Ms.i().key_S1_Num5()) { Ms.i().keyRelease(); this.state = 4; this.b_win = (byte)((this.gr.selectx == this.gr.selecty) ? 1 : -1); }  } else if (this.state == 4 && Ms.i().key_S1_Num5()) { Ms.i().keyRelease(); if (this.b_win == 1) { this.b_win = win(); if (this.b_win == 3) go(1, this.lv);  } else if (this.b_win == -1 || (this.b_win == 2 && this.gr.say_c == 0)) { if (this.b_win == 2 && this.gr.rmsOther[11] == 3) { this.gr.rmsOther[11] = 4; this.gr.getMonster(83, 25, 0, -1); } else if (this.b_win == -1 && this.gr.rmsOther[11] < 3) { this.gr.rmsOther[11] = 0; }  go(0, this.lv); }  }  return false; }
/* 344 */   private byte win() { if ((this.winNum = (byte)(this.winNum + 1)) >= this.gameData[3][this.lv]) { int tm = this.sell_money + this.lv * this.lv * 150 + (2 - this.lv) * 25; this.gr.money += tm; this.gr.say("获得金钱：" + tm, 0); this.gr.rmsOther[10] = (byte)(this.gr.rmsOther[10] | 1 << this.lv); if (this.lv == 2 && this.gr.rmsOther[11] < 3) this.gr.rmsOther[11] = (byte)(this.gr.rmsOther[11] + 1);  return 2; }  return 3; } public void go(int mode) { if (mode < 1) {
/* 345 */       this.b_win = 0;
/* 346 */       this.gr.selecty = 0;
/* 347 */       this.gr.selectx = 0;
/* 348 */       this.winNum = 0;
/* 349 */       this.state = 0;
/* 350 */       this.gr.buyOk = 0;
/* 351 */       this.gr.line_max = 9;
/* 352 */       this.sell_money = this.lv * 200 + 200;
/* 353 */       this.length = (byte)(this.gr.about_a.length - this.gr.line_max);
/*     */     } else {
/* 355 */       this.time = 0;
/* 356 */       this.state = 1;
/* 357 */       initGame();
/*     */     }  }
/*     */   
/*     */   private void initGame() {
/* 361 */     byte[] monList = getGameMonList();
/* 362 */     for (byte i = 0; i < 3; i = (byte)(i + 1)) {
/* 363 */       this.box[i][0] = i;
/* 364 */       this.box[i][1] = monList[i];
/* 365 */       this.box[i][2] = this.gameData[0][i] + 100;
/* 366 */       this.box[i][3] = 0;
/*     */     } 
/*     */     
/* 369 */     monList = null;
/* 370 */     Ms.i(); this.maxTransNum = (byte)(Ms.getRandom(2 + this.lv) + 2 + this.lv);
/* 371 */     this.transNum = 0;
/* 372 */     this.mon_size_move = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] getGameMonList() {
/* 381 */     byte[] monList = new byte[3];
/*     */     byte i;
/* 383 */     for (i = 0; i < monList.length; i = (byte)(i + 1))
/* 384 */       monList[i] = -1; 
/* 385 */     for (i = 0; i < this.gameData[2][this.lv]; i = (byte)(i + 1)) {
/* 386 */       Ms.i(); monList[i] = this.gameData[4][Ms.getRandom((this.gameData[4]).length)];
/* 387 */       byte b = 0;
/* 388 */       while (b < i) {
/* 389 */         if (monList[b] == monList[i]) {
/* 390 */           Ms.i(); monList[i] = this.gameData[4][Ms.getRandom((this.gameData[4]).length)];
/* 391 */           b = 0; continue;
/* 392 */         }  b = (byte)(b + 1);
/*     */       } 
/*     */     } 
/*     */     
/* 396 */     Ms.i(); i = (byte)Ms.getRandom(2);
/* 397 */     byte n = monList[monList.length - 1];
/* 398 */     monList[monList.length - 1] = monList[i];
/* 399 */     monList[i] = n;
/*     */ 
/*     */     
/* 402 */     for (i = 0; i < 3; i = (byte)(i + 1)) {
/* 403 */       if (monList[i] != -1) {
/* 404 */         byte t = this.gr.mList_id[monList[i]][0];
/* 405 */         this.imgMon[i] = Ms.i().createSprite("data/npc2/" + t, this.gr.isNpc2ImageType(t));
/*     */       } 
/* 407 */     }  return monList;
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/minigame/Guess.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */