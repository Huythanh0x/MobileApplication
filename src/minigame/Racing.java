package minigame;

import dm.Ms;
import dm.Sprite;
import dm.Ui;
import javax.microedition.lcdui.Image;
import main.Constants_H;
import main.GameRun;

public class Racing implements MiniGame_H, Constants_H {
   private final byte NUM = 4;
   private final short MAP_HEIGHT = 450;
   private final short WIN_HEIGHT = 70;
   GameRun gr;
   private byte[][] now_a = new byte[4][3];
   private byte[] myDate = new byte[]{0, 0, 0, -1};
   private byte sel;
   private byte state;
   private byte length;
   private byte time;
   private byte lv;
   private byte speedLv;
   private Sprite[] sp;
   private Image imgCloud;
   private short srcY;
   private short[][] cloud = new short[10][3];
   private short[] monY = new short[4];
   private short[] money = new short[]{100, 120, 300, 350, 1000, 1200};
   private short[][] gDate = new short[][]{{450, 3, 8}, {390, 4, 9}, {270, 7, 6}, {210, 6, 10}};

   public Racing(GameRun gr_) {
      this.gr = gr_;
   }

   private void nullGame() {
      this.sp = null;
      this.myDate = null;
      this.money = null;
      this.now_a = (byte[][])null;
      this.monY = null;
      this.imgCloud = null;
      this.cloud = (short[][])null;
      this.gDate = (short[][])null;
   }

   private void draw0(int x, int y, int h, int fh) {
      Ui.i().fillRectB();
      Ui.i().drawString("游戏规则", 120, y + fh, 33, 3, 1);
      Ui.i().drawUi(7, 82, y + fh, 40, 0);
      Ui.i().drawUi(7, 158, y + fh, 36, 4);
      Ui.i().drawK(x - 1, y + fh + 10, 240 - (x << 1) + 2, h, 4);
      Ui.i().drawK(x, y + fh + 10 + h + 5, 240 - (x << 1), fh * 3, 4);
      Ui.i().drawStringY(this.gr.about_a, 12, y + fh + 15, fh, 0, 0);
      Ui.i().drawString("请选择押注的大小：", 19, y + fh + 10 + h + 7, 0, 0, 0);

      for(byte i = 0; i < this.length; ++i) {
         Ui.i().drawK(33 + i * 67, y + fh * 2 + 10 + h + 15, 38, fh + 4, 1);
         Ui.i().drawString(this.gr.about_b[i].toString(), 35 + i * 67, y + fh * 2 + 10 + h + 15, 0, 3, 0);
      }

      Ui.i().drawK4(33 + this.sel * 67, y + fh * 2 + 10 + h + 15, 38, fh + 4);
      Ui.i().drawTriangle(120, y + fh * 3 + 10 + h + 7, 110, true, true);
      this.gr.drawMoney(120, 320, 3, false);
      Ui.i().drawYesNo(true, true);
   }

   private void draw1(int x, int y, int h) {
      Ui.i().fillRectB();
      Ui.i().drawK(x, y, 240 - (x << 1), 300 - (y << 1), 4);
      Ui.i().drawString("请选择押注的宠物：", 19, y + 5, 0, 0, 0);

      byte i;
      for(i = 0; i < this.length; ++i) {
         Ui.i().drawK(19 + i * 52, y + 20 + 10, 44, h, 1);
         Ui.i().drawActionOne(this.sp[0], 0, 19 + i * 52 + 22, y + 20 + 8 + h, this.now_a[i], 0, this.state == 1 ? this.sel == i : false);
         Ui.i().drawNum("" + (i + 1), 19 + i * 52 + 27, y + 40 + 12, 20, 2);
      }

      if (this.state == 1) {
         Ui.i().drawK4(19 + this.sel * 52, y + 20 + 10, 44, 76);
         Ui.i().drawTriangle(120, y + 20 + 49, 118, true, true);
      }

      Ui.i().drawString("兴奋度：", 19, y + 20 + 13 + h, 0, 0, 0);
      this.gr.drawFealty(6, 2, 76, y + 20 + 19 + h);
      Ui.i().drawStringY(this.gr.about_a, 19, y + 40 + 19 + h, 20, 0, 0);
      h = 59 + h + 20 + this.gr.about_a.length * 20;
      Ui.i().drawString("请选择道具：", 19, y + h, 0, 0, 0);
      h = h + 20 + 3;

      for(i = 0; i < this.length; ++i) {
         Ui.i().drawK(19 + i * 52, y + h, 44, 34, 1);
         this.gr.drawItem(12, 19 + i * 52 + 5, y + h + 10, 0);
         Ui.i().drawString("x" + i, 19 + i * 52 + 21, y + h + 8, 0, 3, 1);
      }

      if (this.state == 2) {
         Ui.i().drawK4(19 + this.sel * 52, y + h, 44, 34);
         Ui.i().drawTriangle(120, y + h + 14, 118, true, true);
      }

      this.gr.drawMoney(120, 320, 3, false);
      Ui.i().drawYesNo(true, this.state != 1);
   }

   private void drawGame() {
      Ui.i().fillRect(5423359, 0, 0, 240, 320);
      this.drawCloud();
      this.drawEnd();

      for(byte i = 0; i < this.length; ++i) {
         Ui.i().drawNum("" + (i + 1), 19 + i * 52 + 22, 20 - this.srcY, 1, 2);
         Ui.i().drawActionOne(this.sp[0], 1, 19 + i * 52 + 22, this.monY[i] - this.srcY, this.now_a[i], 0, true);
      }

      if (this.state == 3 && this.time != 0) {
         Ui.i().drawNum("" + this.time / 20, 115, 160, 0, 2);
      }

   }

   private void setSpeed() {
      if (this.speedLv < this.gDate.length) {
         byte i;
         for(i = 0; i < 4 && this.monY[i] > this.gDate[this.speedLv][0]; ++i) {
         }

         if (i < 4) {
            for(i = 0; i < 4; ++i) {
               byte[] var10000 = this.now_a[i];
               Ms.i();
               var10000[2] = (byte)(Ms.getRandom(this.gDate[this.speedLv][1]) + this.gDate[this.speedLv][1]);
            }

            ++this.speedLv;
         }
      }
   }

   public void go(int mode, int lv_) {
      this.lv = (byte)lv_;
      this.sp = new Sprite[3];
      this.sp[0] = Ms.i().createSprite("data/npc0/67", true);
      this.sp[1] = Ms.i().createSprite("data/brow/m1", true);
      this.imgCloud = Ms.i().createImage("data/cloud");
      this.go(mode);
   }

   public void go(int mode) {
      if (mode < 1) {
         this.sel = 0;
         this.state = 0;
         this.length = 3;
         this.gr.setStringB("选择你认为会得第一的一只宠物和押注的大小，注数越大获胜后的奖励越丰厚！#n一注：投入100金，获胜后得120金。#n二注：投入300金，获胜后得350金。#n三注：投入1000金，获胜后得1200金。", 200, 0);
         this.gr.setStringB("一注#n二注#n三注", 200, 1);
      } else if (mode == 1) {
         this.myDate[0] = this.sel;
         this.sel = 0;
         this.state = 1;
         this.length = 4;
         this.gr.setStringB("每喂一个兴奋菇花费10金，可增加一格兴奋度。", 200, 0);
      } else if (mode == 2) {
         this.myDate[1] = this.sel;
         this.sel = 0;
         this.state = 2;
      } else if (mode == 3) {
         this.myDate[2] = this.sel;
         this.sel = 0;
         this.state = 3;
         this.srcY = 130;
         this.speedLv = 0;
         this.myDate[3] = -1;
         this.time = 70;

         byte i;
         for(i = 0; i < this.length; ++i) {
            this.monY[i] = 450;
         }

         for(i = 0; i < this.cloud.length; ++i) {
            this.addCloud(i);
         }
      }

   }

   public boolean key() {
      if (Ms.i().key_delay()) {
         return false;
      } else {
         if (this.state == 0) {
            if (Ms.i().key_Left_Right()) {
               this.sel = Ms.i().select(this.sel, 0, this.length - 1);
            } else if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.money[this.sel << 1], true)) {
               this.go(1);
            } else if (Ms.i().key_S2()) {
               this.nullGame();
               return true;
            }
         } else if (this.state == 1) {
            if (Ms.i().key_Left_Right()) {
               this.sel = Ms.i().select(this.sel, 0, this.length - 1);
            } else if (Ms.i().key_S1_Num5()) {
               this.go(2);
            }
         } else if (this.state == 2) {
            if (Ms.i().key_Left_Right()) {
               this.sel = Ms.i().select(this.sel, 0, this.length - 1);
            } else if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.sel * 10, true)) {
               this.go(3);
            } else if (Ms.i().key_S2()) {
               this.sel = this.myDate[1];
               this.state = 1;
            }
         } else if (this.state == 3) {
         }

         return false;
      }
   }

   public void patin() {
      if (this.state == 0) {
         this.draw0(10, 5, 200, 17);
      } else if (this.state != 1 && this.state != 2) {
         this.drawGame();
      } else {
         this.draw1(10, 15, 76);
      }

   }

   public void run() {
      if (this.state == 3) {
         if (this.time == 0) {
            this.setSpeed();
            if (this.myDate[3] != -1) {
               this.time = 5;
               this.state = 4;
               return;
            }

            byte j = -1;

            for(byte i = 0; i < 4; ++i) {
               short[] var10000 = this.monY;
               var10000[i] = (short)(var10000[i] - this.now_a[i][2]);
               if (this.monY[i] - this.srcY < 160) {
                  j = i;
               }

               if (this.monY[i] < 70 && this.myDate[3] == -1) {
                  this.myDate[3] = i;
               }
            }

            if (j != -1) {
               this.srcY = (short)(this.srcY - this.now_a[j][2]);
            }

            if (this.srcY < 0) {
               this.srcY = 0;
            }
         } else {
            --this.time;
         }
      } else if (this.state == 4) {
         if (this.time == 3) {
            if (this.myDate[3] == this.myDate[1]) {
               GameRun var3 = this.gr;
               var3.money += this.money[this.myDate[0] * 2 + 1];
               this.gr.say("获得：" + this.money[this.myDate[0] * 2 + 1] + "金", 0);
               byte[] var4 = this.gr.rmsOther;
               var4[4] = (byte)(var4[4] | 1 << this.lv);
            } else {
               this.gr.say("不好意思，没有猜对。", 0);
            }
         }

         if (this.time > 0) {
            --this.time;
         } else if (this.time == 0 && this.gr.say_c == 0) {
            this.go(0);
         }
      }

   }

   private void addCloud(int i) {
      short[] var10000 = this.cloud[i];
      Ms.i();
      var10000[0] = (short)(240 + Ms.getRandom(120));
      var10000 = this.cloud[i];
      int var10002 = this.srcY - 80;
      Ms.i();
      var10000[1] = (short)(var10002 + i * (Ms.getRandom(25) + 55));
      var10000 = this.cloud[i];
      Ms.i();
      var10000[2] = (short)(Ms.getRandom(5) + 2);
   }

   private void drawCloud() {
      byte i;
      for(i = 0; i < this.cloud.length; ++i) {
         Ui.i().drawImage(this.imgCloud, this.cloud[i][0], this.cloud[i][1] - this.srcY, 0);
      }

      for(i = 0; i < this.cloud.length; ++i) {
         if (this.isNpcSrc(this.cloud[i][0], this.cloud[i][1])) {
            this.addCloud(i);
         } else {
            short[] var10000 = this.cloud[i];
            var10000[0] -= this.cloud[i][2];
         }
      }

   }

   private boolean isNpcSrc(int ax, int ay) {
      return ax < -40 || ay - this.srcY > 340;
   }

   private void drawEnd() {
      byte n = 5;
      Ui.i().drawModuleOne(this.sp[1], 0, 0, 40 - this.srcY, 0, 0);

      for(byte i = 0; i < n; ++i) {
         Ui.i().drawModuleOne(this.sp[1], 1, 35 + i * 35, 44 - this.srcY, 0, 0);
      }

      Ui.i().drawModuleOne(this.sp[1], 0, 240, 40 - this.srcY, 1, 0);
   }
}
