package main;

import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectUtils;
import com.nokia.mid.ui.FullCanvas;
import dm.Ms;
import dm.Sound;
import dm.Sprite;
import dm.Ui;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import uc.payment.PayPlatform;

public final class MainCanvas extends FullCanvas implements Runnable, Constants_H {
   int error;
   String errorString = "";
   Image image_logo;
   XConnection game;
   GameRun gr;
   public int game_state;
   private int menu_state;
   boolean quitGame = false;
   private StringBuffer[] about;
   int loop_s;
   int logo_c = 0;
   int temp_loop = 0;
   private byte fisrt_paint;
   long starttime = 0L;
   long timetaken = 0L;
   private Sprite flashSP;
   private byte ma;
   private byte mt;
   byte battle_c = 9;
   String strTemp = "";
   int[] imageData = null;
   boolean bblack = false;
   private static Graphics g;
   private static DirectGraphics dg;
   byte iii;
   int temp_state = 0;
   public boolean createStop;
   int load_c = 1;
   Image[] imgLogo = null;
   Image image_logo_word;
   StringBuffer[] dialog;
   private byte help_state;
   boolean result;

   public MainCanvas(XConnection game) {
      this.game = game;
      this.gr = new GameRun(this);
   }

   private final void game_init() {
      this.gr.initOffG();
   }

   public final void game_start() {
      (new Thread(this)).start();
   }

   public final void game_stop() {
      Ms.i().rmsOptions(0, (byte[])null, 4);
      Sound.i().soundStop();
   }

   public void run() {
      while(true) {
         try {
            if (!this.quitGame) {
               this.starttime = System.currentTimeMillis();
               Sound.i().soundPlay();
               switch (this.game_state) {
                  case 20:
                     if (this.gr.createOver == -1) {
                        this.gr.time_count = 60;
                        this.paint();
                     } else if (this.gr.time_count < 60) {
                        ++this.gr.time_count;
                     }

                     if (this.gr.threadType == 0 && this.gr.createOver == -1 && this.gr.time_count == 60) {
                        Ms.i().rmsOptions(0, (byte[])null, 4);
                        this.gr.start();
                     }
                     break;
                  case 30:
                     this.gr.run_gameRun();
               }

               this.paint();
               Ms.i();
               if (Ms.keyRepeat) {
                  this.keyProcess();
               }

               Ms.i().runDelay();
               this.timetaken = System.currentTimeMillis() - this.starttime;
               if (Ms.i().getSleep() > 0) {
                  Thread.sleep((long)Ms.i().getSleep());
                  Ms.i().sleep(0);
                  continue;
               }

               if (this.timetaken < 60L) {
                  Thread.sleep(60L - this.timetaken);
               }
               continue;
            }
         } catch (Exception var2) {
            this.errorString = "r" + var2.toString();
            var2.printStackTrace();

            while(true) {
               if (!Ms.i().key_Num0()) {
                  continue;
               }
            }
         }

         this.game.destroyApp(true);
         return;
      }
   }

   void createFlashImage() {
      if (null == this.flashSP) {
         this.flashSP = Ms.i().createSprite("data/fm", false);
      }

      this.ma = this.mt = 0;
      this.load_c = 0;
   }

   private void setMenuFrame_C() {
      if (++this.mt > this.flashSP.action(this.load_c, this.ma, 1)) {
         this.mt = 0;
         if (++this.ma >= this.flashSP.aLength(this.load_c)) {
            this.ma = 0;
            if (this.load_c == 0) {
               this.load_c = 1;
            }
         }
      }

   }

   void goMain_menu() {
      Sound.i().setMusicId(7);
      Sound.i().setMusic(false);
      this.game_state = 40;
      this.menu_state = 0;
      this.fisrt_paint = 0;
   }

   public void paint() {
      this.repaint();
      this.serviceRepaints();
   }

   public void initGraphics(Graphics _g) {
      g = _g;
      dg = DirectUtils.getDirectGraphics(_g);
      this.gr.initGraphics(g);
      Ui.i().initGraphics(g);
   }

   protected void paint(Graphics var1) {
      this.initGraphics(var1);
      Ms.i();
      var1.setFont(Ms.font);
      var1.setClip(0, 0, 240, 320);

      try {
         switch (this.game_state) {
            case 0:
               this.paintMobileLogo();
               break;
            case 10:
               Ui.i().fillRect(0, 0, 0, 240, 320);
               Ui.i().drawString("是否打开音乐?", 120, 140, 1 | 16, 0, 0);
               Ui.i().drawString("（apo642&onihot合作BT）", 120, 160, 1 | 16, 5, 0);
               Ui.i().drawString("是", 4, 320, 4 | 32, 0, 0);
               Ui.i().drawString("否", 236, 320, 8 | 32, 0, 0);
               break;
            case 11:
               Ui.i().drawImage(this.image_logo, 0, 0, 0);
               break;
            case 20:
               if (null == this.dialog) {
                  this.initChangeMapS();
               }

               this.gr.drawChangeMap(true, this.gr.time_count, 30, 282, 180);
               break;
            case 30:
               this.gr.paint_gameRun(var1);
               break;
            case 40:
               switch (this.menu_state) {
                  case 0:
                     Ui.i().fillRect(0, 0, 0, 240, 320);
                     Ui.i().drawFrameOne(this.flashSP, this.flashSP.action(this.load_c, this.ma, 0), 120, 160, 0);
                     this.setMenuFrame_C();
                     if (this.load_c == 1) {
                        Ui.i().drawK0(58, 294, 124, 26, 0);
                        if (this.gr.selectx == 2) {
                           Ui.i().drawVolume(126, 301, Sound.i().getVolume(), true);
                        }

                        Ui.i().drawString(this.gr.action_str[this.gr.selectx], 120, 318, 1 | 32, 3, 1);
                        Ui.i().drawTriangle(120, 307, 57, true, true);
                        Ui.i().drawYesNo(true, false);
                     } else {
                        Ui.i().drawString("按0键跳过", 120, 320, 1 | 32, 1, 2);
                     }
                  case 1:
                  case 3:
                  default:
                     break;
                  case 2:
                     Ui.i().drawKuang(0, 248, 240, 48);
                     this.gr.showStringM(this.gr.gogoString, 120, 250, 12, 3);
                     Ui.i().drawYesNo(true, true);
                     break;
                  case 4:
                     Ui.i().drawK0(0, 0, 240, 320, 2);
                     this.gr.drawHelpStr(var1, this.gr.about_a, this.gr.line_max, 120, 10, 1 | 16);
                     Ui.i().drawYesNo(false, true);
                     break;
                  case 5:
                     Ui.i().drawK0(0, 0, 240, 320, 2);
                     Ui.i().drawYesNo(false, true);
                     this.drawGameAbout();
               }
            case 60:
            case 101:
            default:
               break;
            case 70:
               this.drawRectBG();
               Ui.i().drawK1(-5, 260, 250, 60, 3);
               this.gr.showStringM("游戏暂停，按0键返回。", 120, 278, 11, 3);
               break;
            case 9999:
               var1.setColor(16711680);
               var1.drawString(this.game_state + " " + this.error + ":" + this.errorString, 0, 20, 20);
               var1.drawString(this.errorString, -128, 40, 20);
               var1.drawString(this.errorString, -256, 60, 20);
               StringBuffer var10001 = (new StringBuffer()).append("   ");
               GameRun var10002 = this.gr;
               var1.drawString(var10001.append(GameRun.run_state).toString(), 0, 80, 20);
         }
      } catch (Exception var3) {
         var1.setClip(0, 0, 240, 320);
         var1.setColor(0);
         var3.printStackTrace();
         var1.drawString(var3.toString() + this.error + "game" + this.game_state, 20, 20, 20);
      }

   }

   protected void hideNotify() {
      Ms.i();
      Ms.keyRepeat = false;
      if (this.game_state == 30) {
         this.game_state = 100;
      } else {
         if (this.game_state != 70) {
            this.temp_state = this.game_state;
         }

         this.game_state = 70;
      }

      Sound.i().soundStop();
   }

   protected void showNotify() {
      GameRun var10000;
      if (this.game_state == 100) {
         this.game_state = 30;
         var10000 = this.gr;
         if (GameRun.run_state != 98) {
            GameRun var10001 = this.gr;
            this.temp_state = GameRun.run_state;
         }

         var10000 = this.gr;
         GameRun.run_state = 98;
      } else if (this.game_state != 70) {
         this.fisrt_paint = 0;
         var10000 = this.gr;
         if (GameRun.run_state != 99) {
            Sound.i().setMusic(false);
         }
      }

   }

   protected final void keyPressed(int key) {
      if (key == 50) {
         key = -1;
      } else if (key == 52) {
         key = -3;
      } else if (key == 54) {
         key = -4;
      } else if (key == 56) {
         key = -2;
      }

      Ms.i();
      Ms.keyRepeat = true;
      Ms.i();
      Ms.key = key;
   }

   protected final void keyReleased(int key) {
      Ms.i().keyRelease();
   }

   private final void keyProcess() {
      this.error = 3;
      switch (this.game_state) {
         case 10:
            if (Ms.i().key_S1() || Ms.i().key_S2()) {
               this.logo_c = 0;
               Sound.i().setSoundON(Ms.i().key_S1());
               Sound.i().setVolume(Ms.i().key_S1() ? 30 : 0);
               Ms.i().keyRelease();
               this.gr.goMAIN_MENU();
            }

            Ms.i().keyRelease();
            break;
         case 11:
            if (Ms.i().key_S1_Num5()) {
               Ms.i().keyRelease();
               this.gr.goMAIN_MENU();
            }
            break;
         case 30:
            this.gr.key_gameRun();
            break;
         case 40:
            switch (this.menu_state) {
               case 0:
                  Ms.i().keyRelease();
                  if (Ms.i().key_Num0()) {
                     this.ma = 0;
                     this.mt = 0;
                     this.load_c = 1;
                  } else if (this.load_c == 0) {
                     return;
                  }

                  if (Ms.i().key_S1_Num5()) {
                     switch (this.gr.selectx) {
                        case 0:
                           this.gr.data_init();
                           this.initChangeMapS();
                           System.gc();
                           this.gr.map.firstDrawMap = 0;
                           this.goGameLoading();
                           return;
                        case 1:
                           if (this.gr.rmsOther[0] == -1) {
                              this.newGame();
                           } else {
                              this.menu_state = 2;
                              this.gr.gogoString = "This will clear all previous records. Continue?";
                           }

                           return;
                        case 2:
                           Sound.i().keyVolume(0);
                           Sound.i().setMusic(true);
                           return;
                        case 3:
                           this.goHELP_dialog(0);
                           return;
                        case 4:
                           this.menu_state = 5;
                           this.about = Ms.i().groupString("开发商：华娱无线", 221);
                           return;
                        case 5:
                           try {
                              try {
                                 this.game.platformRequest("http://g.uc.cn");
                              } catch (Exception var5) {
                                 var5.printStackTrace();
                              }

                              return;
                           } finally {
                              this.quitGame = true;
                              this.game.destroyApp(true);
                           }
                        case 6:
                           this.quitGame = true;
                     }
                  } else if (Ms.i().key_Left_Right()) {
                     this.gr.selectx = Ms.i().select(this.gr.selectx, this.gr.rmsOther[0] == -1 ? 1 : 0, this.gr.action_str.length - 1);
                  }

                  return;
               case 1:
               case 3:
               default:
                  return;
               case 2:
                  if (Ms.i().key_S1_Num5()) {
                     this.newGame();
                  } else if (Ms.i().key_S2()) {
                     this.menu_state = 0;
                     this.gr.about_a = null;
                  }

                  Ms.i().keyRelease();
                  return;
               case 4:
                  if (Ms.i().key_S2()) {
                     this.gr.about_a = null;
                     if (this.help_state == 1) {
                        this.game_state = 30;
                        this.gr.doPaint(0);
                        this.gr.goYouPAUSE(2);
                        this.gr.selectx = 1;
                        this.gr.selecty = 2;
                        this.gr.setPauseS(this.gr.selectx);
                     } else {
                        this.goMain_menu();
                        this.gr.select[0][0] = 3;
                     }
                  } else if (Ms.i().key_Up_Down() || Ms.i().key_Left_Right()) {
                     this.gr.help_page = Ms.i().select(this.gr.help_page, 0, this.gr.page_max - 1);
                  }

                  Ms.i().keyRelease();
                  return;
               case 5:
                  if (Ms.i().key_S2()) {
                     Ms.i().keyRelease();
                     this.gr.goMAIN_MENU();
                  }

                  return;
            }
         case 70:
            if (Ms.i().key_Num0()) {
               this.game_state = this.temp_state;
               Sound.i().setMusic(true);
            }

            Ms.i().keyRelease();
            break;
         case 101:
            this.quitGame = true;
      }

   }

   private void goQuit() {
      this.strTemp = "更多精彩尽在续作，是否退出本游戏？";
      this.game_state = 101;
   }

   public void drawRectBG() {
      Ui.i().fillRectB();
      int[] vertexX = new int[]{0, 10, 20, 10};
      int[] vertexY = new int[]{10, 0, 10, 20};

      for(byte j = 0; j < 17; ++j) {
         for(byte i = 0; i < 13; ++i) {
            g.translate(i * 20, j * 20);
            dg.fillPolygon(vertexX, 0, vertexY, 0, 4, -11548673);
            g.translate(i * -20, j * -20);
         }
      }

      int[] vertexX_temp = null;
      int[] vertexY_temp = null;
   }

   protected void paintMobileLogo() {
      if (null == this.imgLogo) {
         this.imgLogo = new Image[2];

         try {
            this.imgLogo[0] = Image.createImage("/cwa.png");
            this.imgLogo[1] = Image.createImage("/logo.png");
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      if (this.load_c < 0) {
         Ui.i().fillRect(0, 0, 0, 240, 320);
         if (this.logo_c == 0) {
            g.setClip(60, 150, this.logo_c * 10, 20);
         } else if (this.logo_c >= 20) {
            this.load_c = 0;
            this.logo_c = 0;
         } else if (this.logo_c < 12) {
            g.setClip(60, 150, this.logo_c * 10, 20);
         }

         ++this.logo_c;
         g.setClip(0, 0, 240, 320);
      } else {
         Image var10001;
         Graphics var10004;
         Graphics var10005;
         if (this.load_c++ >= 0 && this.load_c++ < 30) {
            Ui.i().fillRect(0, 0, 0, 240, 320);
            var10001 = this.imgLogo[1];
            var10004 = g;
            var10005 = g;
            g.drawImage(var10001, 120, 160, 1 | 2);
         } else if (this.load_c++ >= 30 && this.load_c++ < 60) {
            Ui.i().fillRect(0, 0, 0, 240, 320);
            var10001 = this.imgLogo[0];
            var10004 = g;
            var10005 = g;
            g.drawImage(var10001, 120, 160, 1 | 2);
         } else if (this.load_c++ > 60) {
            this.image_logo = null;
            this.gr.popMenu = 0;
            this.game_state = 10;
            this.strTemp = "是否打开音乐?";
            this.game_init();
         }
      }

   }

   void goHELP_dialog(int mode) {
      this.game_state = 40;
      this.menu_state = 4;
      this.logo_c = 0;
      this.help_state = (byte)mode;
      this.gr.line_max = 11;
      this.gr.setStringB(this.gr.createString("data/help_d.t"), 202, 0);
   }

   public void initChangeMapS() {
   }

   private void newGame() {
      this.gr.data_init();
      this.initChangeMapS();
      if (Ms.i().rmsOptions(0, (byte[])null, 5) == null) {
         System.gc();
         this.gr.map.firstDrawMap = 0;
         Ms.i().rmsOptions(0, (byte[])null, 3);
         this.gr.initRmsOther();
         Ms.i().rmsOptions(10, this.gr.rmsOther, 2);
         this.goGameLoading();
         this.gr.map.initSrcEffect(1);
      } else {
         this.gr.say("存储空间不足！", 0);
      }

      this.gr.about_a = null;
   }

   public void goGameLoading() {
      this.game_state = 20;
      this.gr.time_count = 0;
      this.flashSP = null;
      this.gr.loadRmsOther();
      this.gr.map.loadDate_Rms();
      this.gr.map.anole_temp = this.gr.map.anole_type;
      Map var10000 = this.gr.map;
      Ms.i();
      var10000.anole_type = (byte)Ms.getRandom(5);
      this.gr.map.setAnole();
      this.paint();
      new CreateThread(this.gr, 0);
   }

   private void drawGameAbout() {
      String[] aboutWord = new String[]{"Developer: Huayou Wireless", "Support Phone:", "010-88901665", "Support Email:", "geekan@imy.cn"};
      GameRun var10000 = this.gr;
      GameRun.g.setColor(255);

      for(int i = 0; i < aboutWord.length; ++i) {
         var10000 = this.gr;
         GameRun.g.drawString(aboutWord[i], 120, 70 + i * 25, 17);
      }

   }

   public boolean uc_pay(String pay_text, String gameid, String cpid, String opcode, String opobject, int u_num, int pay_id) {
      this.result = PayPlatform.launchPay(this.game, this, pay_text, cpid, gameid, opcode, opobject, u_num);
      if (this.result) {
         switch (pay_id) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
               PayPlatform.updatePaidMoney(opcode, 0);
            case 6:
            case 7:
         }
      }

      return this.result;
   }
}
