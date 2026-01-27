package minigame;

import dm.Ms;
import dm.Sprite;
import dm.Ui;
import javax.microedition.lcdui.Image;
import main.Constants_H;
import main.GameRun;

public class Bearer implements MiniGame_H, Constants_H {
   GameRun gr;
   private byte state;
   private byte length;
   private byte cur;
   private byte lv;
   private short ballC0;
   private short ballC1;
   private short time0;
   private short time1;
   private short WIDTH_B;
   private short[][] gDate = new short[][]{{9, 1, 10, 2, 11, 4, 27, 8, 2, -10}, {50, 100, 150, 200}, {60, 40, 30, 10}, {8, 6, 13, 3, 20, 5, 30, 10}};
   private short[][] ballDate = new short[][]{{10, 20, 60, 10, 220}, {13, 40, 60, 12, 170}, {10, 40, 60, 10, 120}};
   private short[] money = new short[]{150, 200, 300, 450};
   private short[][] xy = new short[40][7];
   private short[] myxy = new short[]{0, 275, 67, 10, 0};
   private short count;
   private Sprite[] sp;
   private Image img;
   private Sprite bsp;
   private byte[] ball_time = new byte[]{20, 15, 10, 5};
   private final byte SPEED = 10;

   public Bearer(GameRun gr_) {
      this.gr = gr_;
      this.WIDTH_B = 240;
   }

   private void nullGame() {
      this.gDate = (short[][])null;
      this.money = null;
      this.sp = null;
      this.img = null;
      this.ballDate = (short[][])null;
   }

   public void setLv(int lv_) {
      this.lv = (byte)lv_;
   }

   public void go(int mode, int lv_) {
      this.lv = (byte)lv_;
      this.sp = new Sprite[2];
      this.sp[0] = Ms.i().createSprite("data/npc2/38", true);
      this.img = Ms.i().createImage("data/brow/m0");
      byte[] date = Ms.i().getStream("data/gamee.data", -1);
      Ms.i();
      Ms.skip = 0;
      this.bsp = Sprite.Create(Ms.i().createImage("data/map/5"), Ms.i().createShort2Array(date, 2), Ms.i().createShort3Array(date, 2), Ms.i().createShort3Array(date, 2));
      this.go(mode);
   }

   public void go(int mode) {
      if (mode < 1) {
         this.state = 0;
         this.length = 10;
         this.gr.setStringB("在限定时间里接尽量多的球到框里，最后根据框里球的分数来换取金钱。", 200, 0);
      } else if (mode == 1) {
         this.state = 1;
         this.count = 0;
         this.cur = 0;
         this.ballC0 = this.gDate[1][this.lv];
         this.initABall(true);

         for(byte i = 0; i < this.xy.length; ++i) {
            this.xy[i][5] = -1;
         }
      }

   }

   public boolean key() {
      if (this.state == 0) {
         if (Ms.i().key_S1_Num5() && this.gr.isMoney(this.money[this.lv], true)) {
            this.go(1);
         } else if (Ms.i().key_S2()) {
            this.nullGame();
            return true;
         }
      } else if (this.state == 1) {
         short[] var10000;
         if (Ms.i().key_Left()) {
            var10000 = this.myxy;
            var10000[0] = (short)(var10000[0] - 10);
            if (this.myxy[0] < 0) {
               this.myxy[0] = 0;
            }
         } else if (Ms.i().key_Right()) {
            var10000 = this.myxy;
            var10000[0] = (short)(var10000[0] + 10);
            if (this.myxy[0] + this.myxy[2] > this.WIDTH_B - 20) {
               this.myxy[0] = (short)(this.WIDTH_B - this.myxy[2] - 20);
            }
         }
      }

      return false;
   }

   public void patin() {
      if (this.state == 0) {
         this.draw0(10, 2, 20 * this.length);
      } else {
         this.drawGame();
      }

   }

   private void draw0(int x, int y, int h) {
      Ui.i().fillRectB();
      Ui.i().drawString("游戏规则", 120, y + 20, 33, 3, 1);
      Ui.i().drawUi(7, 82, y + 20, 40, 0);
      Ui.i().drawUi(7, 158, y + 20, 36, 4);
      Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
      Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 60, 4);
      Ui.i().drawStringY(this.gr.about_a, 19, y + 20 + 10, 20, 0, 0);
      int ty = y + 40 + this.gr.about_a.length * 20;
      Ui.i().drawString("不同种类的球的分数：", 19, ty, 0, 0, 0);
      ty += 40;

      for(int i = 0; i < this.gDate[0].length; i += 2) {
         this.gr.drawItem(this.gDate[0][i], 38 + (i % 4 == 2 ? 76 : 0), ty + (i >> 2) * 25, 0);
         Ui.i().drawString((this.gDate[0][i + 1] < 0 ? "" : "+") + this.gDate[0][i + 1], 38 + (i % 4 == 2 ? 76 : 0) + 16, ty + (i >> 2) * 25 - 4, 0, 0, 1);
      }

      Ui.i().drawString("需要的参加费：" + this.money[this.lv] + "金", 19, y + 40 + 10 + h, 0, 0, 1);
      this.gr.drawMoney(120, 320, 3, false);
      Ui.i().drawYesNo(true, true);
   }

   private void drawGame() {
      Ui.i().fillRect(5423359, 0, 0, 240, 320);
      Ui.i().drawFrameOne(this.bsp, 0, this.WIDTH_B / 2, 160, 0);
      Ui.i().drawString("分数：" + this.count, 0, 0, 20, 3, 1);
      Ui.i().drawString("球总数：" + this.ballC0, this.WIDTH_B, 0, 24, 3, 1);
      Ui.i().drawImage(this.img, this.WIDTH_B, 305, 40);

      byte i;
      for(i = 0; i < this.xy.length; ++i) {
         if (this.xy[i][5] != -1) {
            this.gr.drawItem(this.gDate[0][this.xy[i][6] << 1], this.xy[i][0], this.xy[i][1], 0);
         }
      }

      for(i = 0; i < 2; ++i) {
         Ui.i().drawFrameOne(this.sp[0], this.myxy[4] == 0 ? 3 : 4, this.myxy[0] + i * 30 + 20, this.myxy[1] + 25, 0);
      }

   }

   private void addBall(int id) {
      this.xy[this.cur][0] = -20;
      this.xy[this.cur][1] = this.ballDate[id][4];
      this.xy[this.cur][2] = (short)(this.ballDate[id][1] / this.ballDate[id][0]);
      this.xy[this.cur][4] = this.ballDate[id][0];
      this.xy[this.cur][5] = (short)id;
      this.xy[this.cur][3] = this.ballDate[id][3];
      short[] var10000 = this.xy[this.cur];
      Ms.i();
      var10000[6] = (short)Ms.getRandom(this.gDate[0].length >> 1);
      if (++this.cur >= this.xy.length) {
         this.cur = 0;
      }

   }

   private void initABall(boolean mode) {
      this.time0 = mode ? 10 : this.gDate[2][this.lv];
      this.time1 = (short)this.ball_time[this.lv];
      Ms.i();
      this.ballC1 = (short)(Ms.getRandom(this.gDate[3][this.lv << 1]) + this.gDate[3][(this.lv << 1) + 1]);
   }

   private void getAY(int i) {
      if (this.xy[i][4] > 0 && this.xy[i][3] > 1) {
         --this.xy[i][3];
      } else if (this.xy[i][4] == 0) {
         this.xy[i][3] = 0;
      } else if (this.xy[i][4] < 0 && this.xy[i][3] > -this.ballDate[this.xy[i][5]][3]) {
         --this.xy[i][3];
      }

   }

   private boolean isSrc(int i) {
      boolean bb = this.xy[i][1] > 330;
      if (Ms.i().isRect(this.WIDTH_B - 10, 280, 20, 10, this.xy[i][0], this.xy[i][1], 16, 16) || this.xy[i][0] > this.WIDTH_B && this.xy[i][1] < this.myxy[1]) {
         bb = true;
         this.count += this.gDate[0][this.xy[i][6] * 2 + 1];
      }

      return bb;
   }

   private boolean isCollision(int i) {
      boolean bb = Ms.i().isRect(this.myxy[0], this.myxy[1], this.myxy[2], this.myxy[3], this.xy[i][0], this.xy[i][1], 16, 16);
      return bb;
   }

   public void run() {
      if (this.state == 1) {
         if (this.ballC0 > 0) {
            if (this.time0 > 0) {
               --this.time0;
            } else if (this.time1 > 0) {
               --this.time1;
            } else if (this.ballC1 > 0) {
               this.time1 = (short)this.ball_time[this.lv];
               Ms.i();
               this.addBall(Ms.getRandom(this.ballDate.length));
               --this.ballC1;
               --this.ballC0;
            } else if (this.ballC1 == 0) {
               this.initABall(false);
            }
         }

         if (this.myxy[4] > 0) {
            --this.myxy[4];
         }

         boolean bb = true;

         for(byte i = 0; i < this.xy.length; ++i) {
            if (this.xy[i][5] != -1) {
               bb = false;
               short[] var10000 = this.xy[i];
               var10000[0] += this.xy[i][2];
               var10000 = this.xy[i];
               var10000[1] -= this.xy[i][3];
               this.getAY(i);
               --this.xy[i][4];
               if (this.isSrc(i)) {
                  this.xy[i][5] = -1;
               } else if (this.isCollision(i)) {
                  this.xy[i][4] = this.ballDate[this.xy[i][5]][0];
                  this.myxy[4] = 1;
                  this.xy[i][3] = this.ballDate[this.xy[i][5]][3];
               }
            }
         }

         if (this.ballC0 < 1 && bb) {
            this.count = (short)(this.count * 15 / 10);
            GameRun var3 = this.gr;
            var3.money += this.count;
            this.gr.say("获得：" + this.count + "金", 0);
            if (this.count > 0) {
               byte[] var4 = this.gr.rmsOther;
               var4[6] = (byte)(var4[6] | 1 << this.lv);
            }

            this.state = 2;
         }
      } else if (this.state == 2 && this.gr.say_c == 0) {
         this.go(0);
      }

   }
}
