/*      */ package main;
/*      */ 
/*      */ import com.nokia.mid.ui.DirectGraphics;
/*      */ import com.nokia.mid.ui.DirectUtils;
/*      */ import com.nokia.mid.ui.FullCanvas;
/*      */ import dm.Ms;
/*      */ import dm.Sound;
/*      */ import dm.Sprite;
/*      */ import dm.Ui;
/*      */ import javax.microedition.lcdui.Displayable;
/*      */ import javax.microedition.lcdui.Graphics;
/*      */ import javax.microedition.lcdui.Image;
/*      */ import uc.payment.PayPlatform;
/*      */ 
/*      */ public final class MainCanvas
/*      */   extends FullCanvas
/*      */   implements Runnable, Constants_H {
/*      */   int error;
/*   19 */   String errorString = "";
/*      */   
/*      */   Image image_logo;
/*      */   
/*      */   XConnection game;
/*      */   
/*      */   GameRun gr;
/*      */   
/*      */   public int game_state;
/*      */   private int menu_state;
/*      */   boolean quitGame = false;
/*      */   private StringBuffer[] about;
/*      */   int loop_s;
/*   32 */   int logo_c = 0; int temp_loop = 0; private byte fisrt_paint; long starttime; long timetaken; private Sprite flashSP;
/*      */   private byte ma;
/*      */   private byte mt;
/*      */   byte battle_c;
/*      */   String strTemp;
/*      */   int[] imageData;
/*      */   boolean bblack;
/*      */   private static Graphics g;
/*      */   private static DirectGraphics dg;
/*      */   byte iii;
/*      */   int temp_state;
/*      */   public boolean createStop;
/*      */   int load_c;
/*      */   Image[] imgLogo;
/*      */   Image image_logo_word;
/*      */   StringBuffer[] dialog;
/*      */   private byte help_state;
/*      */   boolean result;
/*      */   
/*      */   private final void game_init() {
/*   52 */     this.gr.initOffG();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void game_start() {
/*   58 */     (new Thread(this)).start();
/*      */   }
/*      */ 
/*      */   
/*      */   public final void game_stop() {
/*   63 */     Ms.i().rmsOptions(0, null, 4);
/*   64 */     Sound.i().soundStop();
/*      */   }
/*   66 */   public MainCanvas(XConnection game) { this.starttime = 0L; this.timetaken = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  151 */     this.battle_c = 9;
/*  152 */     this.strTemp = "";
/*  153 */     this.imageData = null;
/*  154 */     this.bblack = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  547 */     this.temp_state = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  909 */     this.load_c = 1;
/*  910 */     this.imgLogo = null; this.game = game; this.gr = new GameRun(this); } public void run() { try { while (!this.quitGame) { this.starttime = System.currentTimeMillis(); Sound.i().soundPlay(); switch (this.game_state) { case 30: this.gr.run_gameRun(); break;case 20: if (this.gr.createOver == -1) { this.gr.time_count = 60; paint(); } else if (this.gr.time_count < 60) { this.gr.time_count = (byte)(this.gr.time_count + 1); }  if (this.gr.threadType == 0 && this.gr.createOver == -1 && this.gr.time_count == 60) { Ms.i().rmsOptions(0, null, 4); this.gr.start(); }  break; }  paint(); Ms.i(); if (Ms.keyRepeat) keyProcess();  Ms.i().runDelay(); this.timetaken = System.currentTimeMillis() - this.starttime; if (Ms.i().getSleep() > 0) { Thread.sleep(Ms.i().getSleep()); Ms.i().sleep(0); continue; }  if (this.timetaken < 60L) Thread.sleep(60L - this.timetaken);  }  } catch (Exception ex) { this.errorString = "r" + ex.toString(); ex.printStackTrace(); while (!Ms.i().key_Num0()); }  this.game.destroyApp(true); } void createFlashImage() { if (null == this.flashSP) this.flashSP = Ms.i().createSprite("data/fm", false);  this.ma = this.mt = 0; this.load_c = 0; } private void setMenuFrame_C() { this.mt = 0; if ((this.mt = (byte)(this.mt + 1)) > this.flashSP.action(this.load_c, this.ma, 1) && (this.ma = (byte)(this.ma + 1)) >= this.flashSP.aLength(this.load_c)) { this.ma = 0; if (this.load_c == 0) this.load_c = 1;  }  }
/*      */   void goMain_menu() { Sound.i().setMusicId(7); Sound.i().setMusic(false); this.game_state = 40; this.menu_state = 0; this.fisrt_paint = 0; }
/*      */   public void paint() { repaint(); serviceRepaints(); }
/*      */   public void initGraphics(Graphics _g) { g = _g; dg = DirectUtils.getDirectGraphics(_g); this.gr.initGraphics(g); Ui.i().initGraphics(g); }
/*  914 */   protected void paintMobileLogo() { if (null == this.imgLogo) {
/*  915 */       this.imgLogo = new Image[2];
/*      */       try {
/*  917 */         this.imgLogo[0] = Image.createImage("/cwa.png");
/*  918 */         this.imgLogo[1] = Image.createImage("/logo.png");
/*  919 */       } catch (Exception ex) {
/*  920 */         ex.printStackTrace();
/*      */       } 
/*      */     } 
/*  923 */     if (this.load_c < 0)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  940 */       Ui.i().fillRect(0, 0, 0, 240, 320);
/*  941 */       if (this.logo_c == 0) {
/*      */ 
/*      */         
/*  944 */         g.setClip(60, 150, this.logo_c * 10, 20);
/*      */       }
/*  946 */       else if (this.logo_c >= 20) {
/*  947 */         this.load_c = 0;
/*      */ 
/*      */         
/*  950 */         this.logo_c = 0;
/*  951 */       } else if (this.logo_c < 12) {
/*      */ 
/*      */ 
/*      */         
/*  955 */         g.setClip(60, 150, this.logo_c * 10, 20);
/*      */       } 
/*      */ 
/*      */       
/*  959 */       this.logo_c++;
/*  960 */       g.setClip(0, 0, 240, 320);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */        }
/*      */     
/*  970 */     else if (this.load_c++ >= 0 && this.load_c++ < 30)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  975 */       Ui.i().fillRect(0, 0, 0, 240, 320);
/*  976 */       g.drawImage(this.imgLogo[1], 120, 160, 0x1 | 0x2); }
/*  977 */     else if (this.load_c++ >= 30 && this.load_c++ < 60)
/*  978 */     { Ui.i().fillRect(0, 0, 0, 240, 320);
/*  979 */       g.drawImage(this.imgLogo[0], 120, 160, 0x1 | 0x2); }
/*  980 */     else if (this.load_c++ > 60)
/*  981 */     { this.image_logo = null;
/*  982 */       this.gr.popMenu = 0;
/*  983 */       this.game_state = 10;
/*  984 */       this.strTemp = "是否打开音乐?";
/*  985 */       game_init(); }  } protected void hideNotify() { Ms.i(); Ms.keyRepeat = false; if (this.game_state == 30) { this.game_state = 100; } else { if (this.game_state != 70)
/*      */         this.temp_state = this.game_state;  this.game_state = 70; }  Sound.i().soundStop(); }
/*      */   protected void showNotify() { if (this.game_state == 100) { this.game_state = 30; if (GameRun.run_state != 98)
/*      */         this.temp_state = GameRun.run_state;  GameRun.run_state = 98; } else if (this.game_state != 70) { this.fisrt_paint = 0; if (GameRun.run_state != 99)
/*      */         Sound.i().setMusic(false);  }  }
/*      */   protected final void keyPressed(int key) { if (key == 50) { key = -1; } else if (key == 52) { key = -3; }
/*      */     else if (key == 54) { key = -4; }
/*      */     else if (key == 56) { key = -2; }
/*      */      Ms.i(); Ms.keyRepeat = true; Ms.i(); Ms.key = key; }
/*  994 */   void goHELP_dialog(int mode) { this.game_state = 40;
/*  995 */     this.menu_state = 4;
/*  996 */     this.logo_c = 0;
/*  997 */     this.help_state = (byte)mode;
/*  998 */     this.gr.line_max = 11;
/*  999 */     this.gr.setStringB(this.gr.createString("data/help_d.t"), 202, 0); }
/*      */   protected final void keyReleased(int key) { Ms.i().keyRelease(); }
/*      */   private final void keyProcess() { this.error = 3; switch (this.game_state) { case 10: if (Ms.i().key_S1() || Ms.i().key_S2()) { this.logo_c = 0; Sound.i().setSoundON(Ms.i().key_S1()); Sound.i().setVolume(Ms.i().key_S1() ? 30 : 0); Ms.i().keyRelease(); this.gr.goMAIN_MENU(); }  Ms.i().keyRelease(); break;case 30: this.gr.key_gameRun(); break;case 11: if (Ms.i().key_S1_Num5()) { Ms.i().keyRelease(); this.gr.goMAIN_MENU(); }  break;case 101: this.quitGame = true; break;case 70: if (Ms.i().key_Num0()) { this.game_state = this.temp_state; Sound.i().setMusic(true); }  Ms.i().keyRelease(); break;case 40: switch (this.menu_state) { case 2: if (Ms.i().key_S1_Num5()) { newGame(); } else if (Ms.i().key_S2()) { this.menu_state = 0; this.gr.about_a = null; }  Ms.i().keyRelease(); break;case 0: Ms.i().keyRelease(); if (Ms.i().key_Num0()) { this.ma = 0; this.mt = 0; this.load_c = 1; } else if (this.load_c == 0) { return; }  if (Ms.i().key_S1_Num5()) { switch (this.gr.selectx) { case 1: if (this.gr.rmsOther[0] == -1) { newGame(); break; }  this.menu_state = 2; this.gr.gogoString = "这将清除您之前的所有记录，是否继续？"; break;
/*      */                 case 0: this.gr.data_init(); initChangeMapS(); System.gc(); this.gr.map.firstDrawMap = 0; goGameLoading(); break;
/*      */                 case 2: Sound.i().keyVolume(0); Sound.i().setMusic(true); break;
/*      */                 case 3: goHELP_dialog(0); break;
/*      */                 case 4: this.menu_state = 5; this.about = Ms.i().groupString("开发商：华娱无线", 221); break;
/*      */                 case 5: try { this.game.platformRequest("http://g.uc.cn"); } catch (Exception ex) { ex.printStackTrace(); } finally { this.quitGame = true; this.game.destroyApp(true); }  break;
/*      */                 case 6: this.quitGame = true; break; }  break; }  if (Ms.i().key_Left_Right())
/*      */               this.gr.selectx = Ms.i().select(this.gr.selectx, (this.gr.rmsOther[0] == -1) ? 1 : 0, this.gr.action_str.length - 1);  break;
/*      */           case 4: if (Ms.i().key_S2()) { this.gr.about_a = null; if (this.help_state == 1) { this.game_state = 30; this.gr.doPaint(0); this.gr.goYouPAUSE(2); this.gr.selectx = 1; this.gr.selecty = 2; this.gr.setPauseS(this.gr.selectx); } else { goMain_menu(); this.gr.select[0][0] = 3; }  } else if (Ms.i().key_Up_Down() || Ms.i().key_Left_Right()) { this.gr.help_page = Ms.i().select(this.gr.help_page, 0, this.gr.page_max - 1); }  Ms.i().keyRelease(); break;
/* 1010 */           case 5: if (Ms.i().key_S2()) { Ms.i().keyRelease(); this.gr.goMAIN_MENU(); }  break; }  break; }  } private void newGame() { this.gr.data_init();
/* 1011 */     initChangeMapS();
/* 1012 */     if (Ms.i().rmsOptions(0, null, 5) == null) {
/* 1013 */       System.gc();
/* 1014 */       this.gr.map.firstDrawMap = 0;
/* 1015 */       Ms.i().rmsOptions(0, null, 3);
/* 1016 */       this.gr.initRmsOther();
/* 1017 */       Ms.i().rmsOptions(10, this.gr.rmsOther, 2);
/* 1018 */       goGameLoading();
/* 1019 */       this.gr.map.initSrcEffect(1);
/*      */     } else {
/*      */       
/* 1022 */       this.gr.say("存储空间不足！", 0);
/*      */     } 
/* 1024 */     this.gr.about_a = null; }
/*      */   private void goQuit() { this.strTemp = "更多精彩尽在续作，是否退出本游戏？"; this.game_state = 101; }
/*      */   public void drawRectBG() { Ui.i().fillRectB(); int[] vertexX = { 0, 10, 20, 10 }; int[] vertexY = { 10, 0, 10, 20 }; byte j; for (j = 0; j < 17; j = (byte)(j + 1)) { for (byte i = 0; i < 13; i = (byte)(i + 1)) { g.translate(i * 20, j * 20); dg.fillPolygon(vertexX, 0, vertexY, 0, 4, -11548673); g.translate(i * -20, j * -20); }  }
/* 1027 */      vertexX = null; vertexY = null; } public void initChangeMapS() {} public void goGameLoading() { this.game_state = 20;
/* 1028 */     this.gr.time_count = 0;
/* 1029 */     this.flashSP = null;
/* 1030 */     this.gr.loadRmsOther();
/* 1031 */     this.gr.map.loadDate_Rms();
/* 1032 */     this.gr.map.anole_temp = this.gr.map.anole_type;
/*      */     
/* 1034 */     Ms.i(); this.gr.map.anole_type = (byte)Ms.getRandom(5);
/*      */ 
/*      */ 
/*      */     
/* 1038 */     this.gr.map.setAnole();
/* 1039 */     paint();
/* 1040 */     new CreateThread(this.gr, 0); }
/*      */ 
/*      */   
/*      */   private void drawGameAbout() {
/* 1044 */     String[] aboutWord = { "开发商：华娱无线", "客服电话：", "010-88901665", "客服邮箱：", "geekan@imy.cn" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1051 */     GameRun.g.setColor(255);
/* 1052 */     for (int i = 0; i < aboutWord.length; i++) {
/* 1053 */       GameRun.g.drawString(aboutWord[i], 120, 70 + i * 25, 17);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean uc_pay(String pay_text, String gameid, String cpid, String opcode, String opobject, int u_num, int pay_id) {
/* 1058 */     this.result = PayPlatform.launchPay(this.game, (Displayable)this, pay_text, cpid, gameid, opcode, opobject, u_num);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1070 */     if (this.result) {
/* 1071 */       switch (pay_id) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 8:
/* 1078 */           PayPlatform.updatePaidMoney(opcode, 0);
/*      */           break;
/*      */       } 
/*      */     }
/* 1082 */     return this.result;
/*      */   }
/*      */   
/*      */   protected void paint(Graphics paramGraphics) {
/*      */     initGraphics(paramGraphics);
/*      */     Ms.i();
/*      */     paramGraphics.setFont(Ms.font);
/*      */     paramGraphics.setClip(0, 0, 240, 320);
/*      */     try {
/*      */       switch (this.game_state) {
/*      */         case 30:
/*      */           this.gr.paint_gameRun(paramGraphics);
/*      */           break;
/*      */         case 0:
/*      */           paintMobileLogo();
/*      */           break;
/*      */         case 70:
/*      */           drawRectBG();
/*      */           Ui.i().drawK1(-5, 260, 250, 60, 3);
/*      */           this.gr.showStringM("游戏暂停，按0键返回。", 120, 278, 11, 3);
/*      */           break;
/*      */         case 10:
/*      */           Ui.i().fillRect(0, 0, 0, 240, 320);
/*      */           Ui.i().drawString("是否打开音乐?", 120, 140, 0x1 | 0x10, 0, 0);
/*      */           Ui.i().drawString("（apo642&onihot合作BT）", 120, 160, 0x1 | 0x10, 5, 0);
/*      */           Ui.i().drawString("是", 4, 320, 0x4 | 0x20, 0, 0);
/*      */           Ui.i().drawString("否", 236, 320, 0x8 | 0x20, 0, 0);
/*      */           break;
/*      */         case 20:
/*      */           if (null == this.dialog)
/*      */             initChangeMapS(); 
/*      */           this.gr.drawChangeMap(true, this.gr.time_count, 30, 282, 180);
/*      */           break;
/*      */         case 11:
/*      */           Ui.i().drawImage(this.image_logo, 0, 0, 0);
/*      */           break;
/*      */         case 40:
/*      */           switch (this.menu_state) {
/*      */             case 0:
/*      */               Ui.i().fillRect(0, 0, 0, 240, 320);
/*      */               Ui.i().drawFrameOne(this.flashSP, this.flashSP.action(this.load_c, this.ma, 0), 120, 160, 0);
/*      */               setMenuFrame_C();
/*      */               if (this.load_c == 1) {
/*      */                 Ui.i().drawK0(58, 294, 124, 26, 0);
/*      */                 if (this.gr.selectx == 2)
/*      */                   Ui.i().drawVolume(126, 301, Sound.i().getVolume(), true); 
/*      */                 Ui.i().drawString(this.gr.action_str[this.gr.selectx], 120, 318, 0x1 | 0x20, 3, 1);
/*      */                 Ui.i().drawTriangle(120, 307, 57, true, true);
/*      */                 Ui.i().drawYesNo(true, false);
/*      */                 break;
/*      */               } 
/*      */               Ui.i().drawString("按0键跳过", 120, 320, 0x1 | 0x20, 1, 2);
/*      */               break;
/*      */             case 2:
/*      */               Ui.i().drawKuang(0, 248, 240, 48);
/*      */               this.gr.showStringM(this.gr.gogoString, 120, 250, 12, 3);
/*      */               Ui.i().drawYesNo(true, true);
/*      */               break;
/*      */             case 4:
/*      */               Ui.i().drawK0(0, 0, 240, 320, 2);
/*      */               this.gr.drawHelpStr(paramGraphics, this.gr.about_a, this.gr.line_max, 120, 10, 0x1 | 0x10);
/*      */               Ui.i().drawYesNo(false, true);
/*      */               break;
/*      */             case 5:
/*      */               Ui.i().drawK0(0, 0, 240, 320, 2);
/*      */               Ui.i().drawYesNo(false, true);
/*      */               drawGameAbout();
/*      */               break;
/*      */           } 
/*      */           break;
/*      */         case 9999:
/*      */           paramGraphics.setColor(16711680);
/*      */           paramGraphics.drawString(this.game_state + " " + this.error + ":" + this.errorString, 0, 20, 20);
/*      */           paramGraphics.drawString(this.errorString, -128, 40, 20);
/*      */           paramGraphics.drawString(this.errorString, -256, 60, 20);
/*      */           paramGraphics.drawString("   " + GameRun.run_state, 0, 80, 20);
/*      */           break;
/*      */       } 
/*      */     } catch (Exception exception) {
/*      */       paramGraphics.setClip(0, 0, 240, 320);
/*      */       paramGraphics.setColor(0);
/*      */       exception.printStackTrace();
/*      */       paramGraphics.drawString(exception.toString() + this.error + "game" + this.game_state, 20, 20, 20);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/main/MainCanvas.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */