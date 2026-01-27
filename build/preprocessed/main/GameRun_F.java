/*     */ package main;
/*     */ 
/*     */ import com.nokia.mid.ui.DirectGraphics;
/*     */ import com.nokia.mid.ui.DirectUtils;
/*     */ import dm.Monster;
/*     */ import dm.Ms;
/*     */ import dm.Sound;
/*     */ import dm.Ui;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.microedition.lcdui.Graphics;
/*     */ import javax.microedition.lcdui.Image;
/*     */ 
/*     */ public class GameRun_F
/*     */   implements Constants_H, Key_H {
/*     */   static MainCanvas mc;
/*     */   static Image scrT;
/*     */   static Graphics offG;
/*     */   static Graphics g;
/*     */   static DirectGraphics dg;
/*     */   
/*     */   public void initGraphics(Graphics _g) {
/*  24 */     g = _g;
/*  25 */     dg = DirectUtils.getDirectGraphics(_g);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initOffG() {
/*  31 */     scrT = Image.createImage(260, 340);
/*  32 */     offG = scrT.getGraphics();
/*     */   }
/*     */   
/*  35 */   final byte[] anchor = new byte[] { 20, 36, 40, 24, 24, 40, 36, 20 }; public StringBuffer[] about_a; public StringBuffer[] about_b;
/*  36 */   final short[] transB = new short[] { 0, 90, 180, 270, 8192, 8282, 8372, 8462 }; public StringBuffer[] about_c;
/*     */   public StringBuffer[] about_d;
/*     */   
/*     */   public void setStringB(String str, int w, int mode) {
/*  40 */     if (mode == 0) { this.about_a = Ms.i().groupString(str, w); }
/*  41 */     else if (mode == 1) { this.about_b = Ms.i().groupString(str, w); }
/*  42 */     else if (mode == 2) { this.about_c = Ms.i().groupString(str, w); }
/*  43 */     else if (mode == 3) { this.about_d = Ms.i().groupString(str, w); }
/*     */   
/*     */   }
/*     */   public short getSIndexW(String str) {
/*  47 */     byte num = 0, start = 0, end = (byte)str.indexOf("#", start);
/*  48 */     if (end == 0) end = 1; 
/*  49 */     while (end != -1) {
/*  50 */       start = (byte)(start + end);
/*  51 */       end = (byte)str.indexOf("#", start);
/*  52 */       num = (byte)(num + 1);
/*     */     } 
/*  54 */     return (short)(Ms.i().getStringWidth("#1") * num);
/*     */   }
/*     */   public void drawZero(int x, int y, int anchor, int color) {
/*  57 */     Ui.i().drawString("按0键继续", x, y, anchor, color, 0);
/*     */   }
/*     */   public void drawZero(String str, int y) {
/*  60 */     Ui.i().drawString("按0键" + str, 120, y, 0x1 | 0x20, 0, 0);
/*     */   }
/*  62 */   public int introX = 120;
/*  63 */   public String gogoString = "";
/*     */   
/*     */   public void gogoWord(String string, int x, int y, int w, int color, int strT, int speed) {
/*  66 */     short sw = (short)(Ms.i().getStringWidth(string) - getSIndexW(string));
/*  67 */     if (sw > 240 - x - w)
/*  68 */     { this.introX -= speed;
/*  69 */       if (this.introX + sw < x) this.introX = 240 - w - 57;  }
/*  70 */     else { this.introX = x; }
/*  71 */      g.clipRect(x, y, 240 - w - x, 25);
/*  72 */     Ui.i().drawStringColor(string, this.introX, y, color, strT);
/*  73 */     g.setClip(0, 0, 240, 320);
/*     */   }
/*  75 */   public int introT = 240;
/*  76 */   public String gogoST = "";
/*     */   
/*     */   public void gogoWordM(String string, int y, int w, int color, int strT, int speed) {
/*  79 */     short sw = (short)Ms.i().getStringWidth(string);
/*  80 */     this.introT -= speed;
/*  81 */     if (this.introT + sw < w << 1) this.introT = 241; 
/*  82 */     g.clipRect(w, y, 240 - (w << 1), 25);
/*  83 */     Ui.i().drawString(string, this.introT, y, 0, color, strT);
/*  84 */     g.setClip(0, 0, 240, 320);
/*     */   }
/*     */   public void setGogoWord() {
/*  87 */     this.introT = 240;
/*  88 */     Ms.i(); Ms.skip = 0;
/*  89 */     StringBuffer[] sr = Ms.i().createStringArray(Ms.i().getStream("data/other/hint.t", -1));
/*  90 */     Ms.i(); this.gogoST = sr[Ms.getRandom(sr.length)].toString();
/*  91 */     sr = null;
/*     */   }
/*  93 */   private byte brow5 = 0; public StringBuffer[] showS;
/*     */   private void drawSnare5(int x, int y) {
/*  95 */     Ui.i().drawUi(40, x, y + ((this.brow5 % 3 == 0) ? 1 : 0), 0, 0);
/*  96 */     this.brow5 = (byte)(this.brow5 + 1); if (this.brow5 > 100) this.brow5 = 0;
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void showString(StringBuffer[] showS, int y, int offx, int mode) {
/* 102 */     if (mode != 0) {
/* 103 */       Ui.i().drawKuang(-5 + offx, y, 250, 20 * showS.length + 12);
/* 104 */       if (mode == 2) drawSnare5(120 + offx, y + 20 * showS.length + 4); 
/*     */     }  byte d;
/* 106 */     for (d = 0; d < showS.length; d = (byte)(d + 1))
/* 107 */       Ui.i().drawStringColor(showS[d].toString(), 19 + offx, y + 4 + d * 20, -1, 1); 
/*     */   }
/*     */   
/*     */   public void showString(String string, int y, int offx) {
/* 111 */     byte strNum = (byte)(string.length() / 13);
/*     */ 
/*     */     
/* 114 */     Ui.i().drawK0(-5 + offx, y, 250, 20 * (strNum + 1) + 8, 3);
/*     */ 
/*     */ 
/*     */     
/* 118 */     showStringM(string, 120 + offx, y + 3, 13, 0);
/*     */   }
/*     */   
/*     */   public void showStringM(String str, int x, int y, int num, int c) {
/* 122 */     byte strNum = (byte)(str.length() / num); byte d;
/* 123 */     for (d = 0; d <= strNum; d = (byte)(d + 1)) {
/* 124 */       if (strNum == 0) {
/* 125 */         Ui.i().drawString(str, x, y, 0x1 | 0x10, c, 1);
/*     */       } else {
/* 127 */         Ui.i().drawString(str.substring(d * num, (d + 1 > strNum) ? str.length() : ((d + 1) * num)), x, y + d * 20, 0x1 | 0x10, c, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public byte page_max = 0; public byte help_page = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawHelpStr(Graphics g, StringBuffer[] help_strbuff, int line_num, int x, int y, int anchor) {
/* 159 */     byte line_start = (byte)(line_num * this.help_page); byte i;
/* 160 */     for (i = line_start; i < line_start + line_num && i < help_strbuff.length; i = (byte)(i + 1))
/* 161 */       Ui.i().drawString(help_strbuff[i].toString(), x, y + (i - line_start) * 22, anchor, 3, 0); 
/* 162 */     if (help_strbuff.length > line_num)
/* 163 */     { this.page_max = (byte)(help_strbuff.length / line_num + ((help_strbuff.length % line_num != 0) ? 1 : 0));
/* 164 */       Ui.i().drawString((this.help_page + 1) + "/" + this.page_max, 120, 318, 0x1 | 0x20, 3, 0);
/* 165 */       Ui.i().drawTriangle(120, 308, 57, true, true); }
/* 166 */     else { this.page_max = 1; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public byte[] src_c = new byte[] { 0, 0, 0, 0 }; public byte[] info; public byte[] len;
/*     */   public String str_cur;
/*     */   public String[] action_str;
/*     */   public byte cur_a;
/*     */   public byte cur_b;
/*     */   public byte cur_c;
/*     */   public byte selectx;
/*     */   public byte selecty;
/*     */   
/*     */   public void drawSrcLine(Graphics g, int y, int h, int piece, boolean dir, boolean mode) {
/* 195 */     byte i = 0, ph = (byte)(h / piece);
/*     */     
/* 197 */     g.setColor(0);
/* 198 */     if (!dir)
/* 199 */     { short speed = (short)(2 << this.src_c[0]);
/* 200 */       for (i = (byte)(piece - 1); i > -1; i = (byte)(i - 1), speed = (short)(speed / 2)) {
/* 201 */         if (ph - speed > 0)
/* 202 */           g.fillRect(0, y + i * ph + ((speed < 2) ? 0 : speed), 240, ph - ((speed < 2) ? 0 : speed)); 
/*     */       } 
/* 204 */       if (mode && this.src_c[0] < 12) { this.src_c[0] = (byte)(this.src_c[0] + 1); }
/* 205 */       else if (!mode && this.src_c[0] > 0) { this.src_c[0] = (byte)(this.src_c[0] - 1); }
/*     */        }
/* 207 */     else { short speed = (short)(2 << this.src_c[1]);
/* 208 */       for (; dir && i < piece; i = (byte)(i + 1), speed = (short)(speed / 2)) {
/* 209 */         if (ph - speed > 0)
/* 210 */           g.fillRect(0, y + i * ph, 240, ph - ((speed < 2) ? 0 : speed)); 
/*     */       } 
/* 212 */       if (mode && this.src_c[1] < 12) { this.src_c[1] = (byte)(this.src_c[1] + 1); }
/* 213 */       else if (!mode && this.src_c[1] > 0) { this.src_c[1] = (byte)(this.src_c[1] - 1); }
/*     */        }
/*     */   
/*     */   }
/*     */   public String createString(String name) {
/* 218 */     Ms.i(); Ms.skip = 0;
/* 219 */     StringBuffer[] str = Ms.i().createStringArray(Ms.i().getStream(name, -1));
/* 220 */     return str[0].toString();
/*     */   }
/*     */   public byte[] createData(int no) {
/* 223 */     return Ms.i().getStream("4", no);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction_str(String[] temp_str) {
/* 231 */     this.action_str = temp_str;
/* 232 */     temp_str = null;
/*     */   }
/* 234 */   public byte[] select_it = new byte[4]; public byte[] select_st = new byte[4]; public byte[][] selectBag = new byte[4][2]; public byte[][] select = new byte[2][2];
/*     */ 
/*     */   
/*     */   public short move_x;
/*     */ 
/*     */   
/*     */   public short move_y;
/*     */ 
/*     */   
/*     */   public byte[][][] items;
/*     */ 
/*     */   
/*     */   public byte[] itemsLength;
/*     */ 
/*     */   
/*     */   public void drawMenu(StringBuffer[] menu, int x, int y, int w) {
/* 250 */     byte color = 10;
/* 251 */     if (w > 0) { Ui.i().drawKuang(x, y, w, menu.length * 22 + 12); }
/* 252 */     else { Ms.i(); w = Ms.abs(w); }
/*     */      byte d;
/* 254 */     for (d = 0; d < menu.length; d = (byte)(d + 1)) {
/* 255 */       if (d == 0) { color = 9; }
/* 256 */       else if (d == this.cur_a) { color = 8; }
/* 257 */       else { color = 0; }
/* 258 */        Ui.i().drawString(menu[d].toString(), x + (w >> 1), y + 4 + d * 22, 0x1 | 0x10, color, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawSelectMenu(Object[] menu, int x, int y, int w, int dis, int cn, int s_f) {
/*     */     byte d;
/* 268 */     for (d = 0; d < menu.length; d = (byte)(d + 1)) {
/* 269 */       Ui.i().drawK0(x, y + d * (28 + dis), w, 28, (d == s_f) ? 1 : cn);
/* 270 */       Ui.i().drawString(menu[d].toString(), x + (w >> 1), y + 2 + d * (28 + dis), 0x1 | 0x10, (d == s_f) ? 0 : 3, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawPauseMenu(int x, int y, int w, int h) {
/* 275 */     Ui.i().drawK2(x, y, w, h, 1);
/* 276 */     Ui.i().drawK1(x + 9, y + 20 + 11, w - 18, h - 20 - 3 * this.action_str.length - 5, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     Ui.i().drawListKY(this.action_str.length, x + 8, y + 20 + 13, w - 16, 1, 27, -1, this.selecty, 4, 2);
/* 288 */     Ui.i().drawListSY((Object[])this.action_str, x + 8, y + 20 + 13, w - 16, 26, this.selecty, 0, 3, 0);
/*     */     
/* 290 */     if (this.selectx == 1)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 296 */       Ui.i().drawVolume(x + (w >> 1) + 19 - 8, y + 20 + 16 + 28, Sound.i().getVolume(), (this.selecty == 1));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     Ui.i().drawK1(x + (w >> 1) + ((this.selectx == 0) ? -40 : 0), y + 4, 40, 24, 4);
/* 304 */     Ui.i().drawString("菜单", x + (w >> 1) - 19, y + 5, 0x1 | 0x10, (this.selectx == 0) ? 0 : 1, 0);
/* 305 */     Ui.i().drawString("系统", x + (w >> 1) + 19, y + 5, 0x1 | 0x10, (this.selectx == 0) ? 1 : 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     Ui.i().drawTriangle(x + (w >> 1), y + 12, 57, true, true);
/*     */   }
/*     */ 
/*     */   
/* 320 */   public byte max_takes = 3; public byte max_monsters = 10;
/*     */   
/*     */   public byte[] monInfoList;
/*     */   
/*     */   public byte myMon_length;
/*     */   
/*     */   public byte cMon_count;
/*     */   
/*     */   public Monster cur_mon;
/*     */   public Monster cur_emon;
/*     */   public Monster[] myMonsters;
/*     */   public Monster[] cMonsters;
/*     */   public byte[] rmsOther;
/*     */   public byte[] rmsSms;
/*     */   public byte[] rmsNidus;
/*     */   public byte[] nidusMap;
/*     */   public byte[][] nidusList;
/*     */   
/*     */   public void saveMon(ByteArrayOutputStream byteArray, Monster mon) throws IOException {
/* 339 */     byteArray.write(mon.monster);
/* 340 */     int xxx = 0, i = 0;
/* 341 */     for (; i < 5; i++) {
/* 342 */       xxx = mon.monsterPro[i];
/* 343 */       byteArray.write((byte)(xxx & 0xFF));
/* 344 */       byteArray.write((byte)(xxx >> 8 & 0xFF));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadMon(ByteArrayInputStream byteArray, Monster mon, byte[][] data) throws IOException {
/* 349 */     mon.monster = new byte[18];
/* 350 */     mon.monsterPro = new short[8];
/* 351 */     byteArray.read(mon.monster);
/* 352 */     this.len = new byte[10];
/* 353 */     byteArray.read(this.len);
/* 354 */     for (int i = 0; i < 5; i++)
/* 355 */       mon.monsterPro[i] = (short)((this.len[1 + i * 2] & 0xFF) << 8 | this.len[0 + i * 2] & 0xFF); 
/* 356 */     mon.setProAFD(data[mon.monster[0]]);
/* 357 */     this.len = null;
/*     */   }
/*     */   
/*     */   public void saveMon(int flag) {
/* 361 */     ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
/* 362 */     int i = 0;
/*     */     try {
/* 364 */       if (flag == 0) {
/* 365 */         byteArray.write(this.cMon_count);
/* 366 */         for (; i < this.cMon_count; i++)
/* 367 */           saveMon(byteArray, this.cMonsters[i]); 
/* 368 */         Ms.i().rmsOptions(8, byteArray.toByteArray(), 2);
/*     */       } else {
/* 370 */         byteArray.write(this.myMon_length);
/* 371 */         for (; i < this.myMon_length; i++)
/* 372 */           saveMon(byteArray, this.myMonsters[i]); 
/* 373 */         Ms.i().rmsOptions(9, byteArray.toByteArray(), 2);
/*     */       } 
/* 375 */       byteArray.close();
/* 376 */       byteArray = null;
/* 377 */     } catch (Exception ex) {
/* 378 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadMon(int flag, byte[][] data) {
/* 383 */     this.info = Ms.i().rmsOptions(8 + flag, null, 1);
/* 384 */     int i = 0;
/*     */     try {
/* 386 */       if (flag == 0) {
/* 387 */         this.cMonsters = new Monster[95];
/* 388 */         if (this.info[0] == -1) {
/* 389 */           this.cMon_count = 0;
/*     */           return;
/*     */         } 
/* 392 */         byteArray = new ByteArrayInputStream(this.info);
/* 393 */         this.cMon_count = (byte)byteArray.read();
/* 394 */         for (; i < this.cMon_count; i++) {
/* 395 */           this.cMonsters[i] = new Monster();
/* 396 */           loadMon(byteArray, this.cMonsters[i], data);
/*     */         } 
/*     */       } else {
/* 399 */         this.myMonsters = new Monster[5];
/* 400 */         if (this.info[0] == -1) {
/* 401 */           this.myMon_length = 0;
/*     */           return;
/*     */         } 
/* 404 */         byteArray = new ByteArrayInputStream(this.info);
/* 405 */         this.myMon_length = (byte)byteArray.read();
/* 406 */         for (; i < this.myMon_length; i++) {
/* 407 */           this.myMonsters[i] = new Monster();
/* 408 */           loadMon(byteArray, this.myMonsters[i], data);
/*     */         } 
/*     */       } 
/* 411 */       byteArray.close();
/* 412 */       ByteArrayInputStream byteArray = null;
/* 413 */       this.info = null;
/* 414 */     } catch (Exception ex) {
/* 415 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   public void saveInfoList() {
/* 419 */     Ms.i().rmsOptions(6, this.monInfoList, 2);
/*     */   }
/*     */   public void loadInfoList() {
/* 422 */     this.info = Ms.i().rmsOptions(6, null, 1);
/* 423 */     if (this.info[0] == -1) {
/* 424 */       this.monInfoList = new byte[105];
/* 425 */       for (int i = 0; i < this.monInfoList.length; i++)
/* 426 */         this.monInfoList[i] = 0; 
/*     */     } else {
/* 428 */       this.monInfoList = this.info;
/* 429 */     }  this.info = null;
/*     */   }
/*     */   public void loadRmsOther() {
/* 432 */     this.info = Ms.i().rmsOptions(10, null, 1);
/* 433 */     if (this.info[0] == -1) {
/* 434 */       setMaxTakes(true);
/* 435 */       initRmsOther();
/*     */     } else {
/* 437 */       this.rmsOther = this.info;
/* 438 */       setMaxTakes(false);
/*     */     } 
/* 440 */     this.info = null;
/*     */   }
/*     */   public void setMaxTakes(boolean bb) {
/* 443 */     if (bb) {
/* 444 */       this.max_takes = 3;
/* 445 */       this.max_monsters = 10;
/*     */     }
/* 447 */     else if (this.rmsOther[3] == 2) {
/* 448 */       this.max_takes = 4;
/* 449 */       this.max_monsters = 20;
/* 450 */     } else if (this.rmsOther[3] == 3) {
/* 451 */       this.max_takes = 5;
/* 452 */       this.max_monsters = 40;
/* 453 */     } else if (this.rmsOther[3] == 4) {
/* 454 */       this.max_takes = 5;
/* 455 */       this.max_monsters = 80;
/* 456 */     } else if (this.rmsOther[3] == 5) {
/* 457 */       this.max_takes = 5;
/* 458 */       this.max_monsters = 95;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte isMyLevel(boolean bb) {
/* 464 */     switch (this.rmsOther[3]) {
/*     */       case 1:
/* 466 */         if (bb && this.monInfoList[103] > 4) {
/* 467 */           this.rmsOther[3] = 2; break;
/* 468 */         }  return 5;
/*     */       
/*     */       case 2:
/* 471 */         if (bb && this.monInfoList[103] > 9) {
/* 472 */           this.rmsOther[3] = 3; break;
/* 473 */         }  return 10;
/*     */       
/*     */       case 3:
/* 476 */         if (bb && this.monInfoList[103] > 14) {
/* 477 */           this.rmsOther[3] = 4; break;
/* 478 */         }  return 15;
/*     */       
/*     */       case 4:
/* 481 */         if (bb && this.monInfoList[103] > 29) {
/* 482 */           this.rmsOther[3] = 5; break;
/* 483 */         }  return 30;
/*     */     } 
/*     */     
/* 486 */     return -1;
/*     */   }
/*     */   public void saveItem() {
/* 489 */     int len = 0; int i;
/* 490 */     for (i = 0; i < 4; ) { len += this.itemsLength[i]; i++; }
/* 491 */      this.info = new byte[this.items.length + len * 2];
/* 492 */     len = 0;
/* 493 */     for (i = 0; i < 4; ) { this.info[i] = this.itemsLength[i]; i++; }
/* 494 */      for (i = 0; i < this.items.length; i++) {
/* 495 */       for (int j = 0; j < this.itemsLength[i]; j++, len++) {
/* 496 */         this.info[len * 2 + 4] = this.items[i][j][0];
/* 497 */         this.info[len * 2 + 4 + 1] = this.items[i][j][1];
/*     */       } 
/* 499 */     }  Ms.i().rmsOptions(4, this.info, 2);
/* 500 */     this.info = null;
/*     */   }
/*     */   
/*     */   public void loadItem() {
/* 504 */     this.info = Ms.i().rmsOptions(4, null, 1);
/* 505 */     this.len = new byte[] { 16, 19, 23, 12 };
/* 506 */     this.itemsLength = new byte[4];
/* 507 */     int i = 0, j = 0, k = 0;
/* 508 */     if (this.info[0] != -1) for (i = 0; i < 4; ) { this.itemsLength[i] = this.info[i]; i++; }
/* 509 */         this.items = new byte[this.itemsLength.length][][];
/* 510 */     for (i = 0, k = 0; i < this.items.length; i++) {
/* 511 */       this.items[i] = new byte[this.len[i]][2];
/* 512 */       for (j = 0; j < this.itemsLength[i]; j++, k++) {
/* 513 */         this.items[i][j][0] = this.info[k * 2 + 4];
/* 514 */         this.items[i][j][1] = this.info[k * 2 + 4 + 1];
/*     */       } 
/*     */     } 
/* 517 */     this.info = null;
/* 518 */     this.len = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initRmsOther() {
/* 524 */     this.rmsOther = null;
/* 525 */     this.rmsOther = new byte[] { -1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadRmsSms() {
/* 535 */     this.rmsSms = Ms.i().rmsOptions(5, null, 1);
/* 536 */     if (this.rmsSms[0] == -1) {
/* 537 */       this.rmsSms = null;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 542 */       this.rmsSms = new byte[] { -3, 0, 0, 0, 0, 0, 0 };
/*     */     } 
/*     */     
/* 545 */     if (this.rmsSms[0] == 10) this.rmsOther[2] = (byte)(this.rmsOther[2] | 0x8);
/*     */   
/*     */   }
/*     */   
/*     */   public void loadRmsNidus() {
/* 550 */     this.rmsNidus = Ms.i().rmsOptions(3, null, 1);
/* 551 */     if (this.rmsNidus[0] == -1) {
/* 552 */       this.rmsNidus = null;
/* 553 */       this.rmsNidus = new byte[] { -2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10 };
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getRid(int i) {
/* 563 */     return this.rmsNidus[i * 5];
/* 564 */   } public byte getNid(int i) { return this.nidusList[0][getRid(i)]; }
/* 565 */   public byte getNLevel(int i) { return this.nidusList[1][getRid(i)]; } public short getNexp(int i, int n) {
/* 566 */     return Ms.i().getShort(this.rmsNidus, i * 5 + n);
/*     */   }
/*     */   public boolean addNidus(int id) {
/* 569 */     for (byte i = 0; i < this.rmsNidus.length; ) {
/* 570 */       if (this.rmsNidus[i] != -2) { i = (byte)(i + 5); continue; }
/* 571 */        this.rmsNidus[i] = (byte)id;
/*     */       
/* 573 */       Ms.i().putShort(0, this.rmsNidus, i + 1);
/* 574 */       Ms.i().putShort(200, this.rmsNidus, i + 3);
/* 575 */       return true;
/*     */     } 
/* 577 */     return false;
/*     */   } public void delNidus(int i) {
/* 579 */     this.rmsNidus[i * 5] = -2;
/*     */   } public void setNidusExp(int exp) {
/* 581 */     exp = (exp / 10 < 1) ? 1 : (exp / 10);
/* 582 */     for (byte i = 0; i < 5; i = (byte)(i + 1)) {
/* 583 */       if (getRid(i) != -2) {
/* 584 */         Ms.i().putShort(getNexp(i, 1) + exp, this.rmsNidus, i * 5 + 1);
/* 585 */         if (getNexp(i, 1) > getNexp(i, 3)) Ms.i().putShort(getNexp(i, 3), this.rmsNidus, i * 5 + 1); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/main/GameRun_F.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */