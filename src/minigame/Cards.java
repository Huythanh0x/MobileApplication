package minigame;

import dm.Ms;
import dm.Ui;
import main.Constants_H;
import main.GameRun;

public class Cards implements MiniGame_H, Constants_H {
   GameRun gr;
   private short count;
   private short gx;
   private short gy;
   private short gw = 30;
   private short gh = 40;
   private short gspace = 4;
   private short wh = 120;
   private short hh = 145;
   private int sell_money;
   private byte[][] findN;
   private byte[][] findC;
   private byte time = 3;
   private byte find_count = 3;
   private byte lv;
   private byte state;
   private byte length;
   private byte selx;
   private byte sely;
   private byte tx;
   private byte ty;
   private byte[][] num = new byte[][]{{3, 4}, {3, 6}, {4, 6}, {5, 6}};

   public Cards(GameRun gr_) {
      this.gr = gr_;
   }

   public void go(int mode, int lv_) {
      this.gr.setStringB(this.gr.createString("data/gamec.d"), 183, 0);
      this.lv = (byte)lv_;
      this.findN = (byte[][])null;
      this.findN = new byte[this.num[this.lv][0]][this.num[this.lv][1]];
      this.findC = (byte[][])null;
      this.findC = new byte[this.num[this.lv][0]][this.num[this.lv][1]];
      this.length = 9;
      this.go(mode);
   }

   public void go(int mode) {
      this.gr.line_max = 9;
      this.count = 100;
      this.time = (byte)(this.lv * 2 + 4);
      this.find_count = (byte)(this.num[this.lv][0] * this.num[this.lv][1] / 2);
      if (mode < 1) {
         this.ty = 0;
         this.state = 0;
         this.sell_money = this.lv * 200 + 200;
      } else {
         this.gx = (short)(-((this.gw + this.gspace) * this.num[this.lv][1]) >> 1);
         this.gy = (short)(-((this.gh + this.gspace) * this.num[this.lv][0]) >> 1);
         this.tx = this.ty = 0;
         this.state = 1;
         byte i = 0;

         byte j;
         byte[] randN;
         for(randN = new byte[this.find_count]; i < this.findC.length; ++i) {
            for(j = 0; j < this.findC[i].length; ++j) {
               this.findC[i][j] = 2;
               this.findN[i][j] = 0;
            }
         }

         i = 0;

         while(i < randN.length) {
            Ms.i();
            byte rd = (byte)(Ms.getRandom(35) + 1);

            for(j = (byte)(randN.length - 1); j > -1 && randN[j] != rd; --j) {
            }

            if (j == -1) {
               randN[i] = rd;
               ++i;
            }
         }

         for(i = 0; i < randN.length; ++i) {
            for(j = 0; j < 2; ++j) {
               byte k;
               do {
                  Ms.i();
                  k = (byte)Ms.getRandom(this.num[this.lv][0] * this.num[this.lv][1]);
               } while(this.findN[k / this.num[this.lv][1]][k % this.num[this.lv][1]] != 0);

               this.findN[k / this.num[this.lv][1]][k % this.num[this.lv][1]] = randN[i];
            }
         }

         Object var7 = null;
      }

   }

   private void nullGame() {
      this.findN = (byte[][])null;
      this.findC = (byte[][])null;
      this.num = (byte[][])null;
   }

   public boolean key() {
      if (Ms.i().key_delay()) {
         return false;
      } else {
         if (this.state == 0) {
            if (Ms.i().key_Up_Down()) {
               this.ty = Ms.i().select(this.ty, 0, this.length);
               if (this.ty < 0) {
                  this.ty = 0;
               }
            } else {
               if (Ms.i().key_S2()) {
                  Ms.i().keyRelease();
                  this.nullGame();
                  return true;
               }

               if (Ms.i().key_S1_Num5()) {
                  Ms.i().keyRelease();
                  if (this.gr.isMoney(this.sell_money, true)) {
                     this.go(1, this.lv);
                  }
               }
            }
         } else if (this.state < 3) {
            if (Ms.i().key_Up_Down()) {
               this.ty = Ms.i().select(this.ty, 0, this.findN.length - 1);
            } else if (Ms.i().key_Left_Right()) {
               this.tx = Ms.i().select(this.tx, 0, this.findN[this.ty].length - 1);
            } else if (Ms.i().key_S1_Num5()) {
               Ms.i().keyRelease();
               if (this.state == 0) {
                  this.go(0, this.lv);
               } else if (this.findC[this.ty][this.tx] == 0) {
                  ++this.findC[this.ty][this.tx];
                  if (++this.state == 2) {
                     this.selx = this.tx;
                     this.sely = this.ty;
                  }
               }
            }
         }

         return false;
      }
   }

   public void patin() {
      if (this.state == 0) {
         this.draw0(10, 2, 20 * this.length);
      } else {
         this.drawGame(10, 2, 20 * (this.length + 3));
      }

   }

   public void run() {
      if (this.time < 1) {
         if (this.time == 0) {
            this.time = -1;
            this.gr.say("游戏失败！！！", -1);
         } else if (this.time == -1 && this.gr.say_c == 0) {
            this.go(0, this.lv);
         }
      } else if (this.find_count < 1) {
         if (this.find_count == 0) {
            this.find_count = -1;
            GameRun var10000 = this.gr;
            var10000.money += this.time * 125 + 100;
            this.gr.say("获得金钱：" + (this.time * 125 + 100), -1);
            byte[] var3 = this.gr.rmsOther;
            var3[8] = (byte)(var3[8] | 1 << this.lv);
         } else if (this.find_count == -1 && this.gr.say_c == 0) {
            this.go(0, this.lv);
         }
      } else if (this.state >= 3) {
         if (++this.state > 10) {
            this.state = 1;
         } else if (this.state == 10) {
            if (this.findN[this.ty][this.tx] != this.findN[this.sely][this.selx]) {
               this.findC[this.ty][this.tx] = 3;
               this.findC[this.sely][this.selx] = 3;
               --this.time;
            } else {
               --this.find_count;
            }
         }
      } else if (this.count > 0 && --this.count == 0) {
         for(byte i = 0; i < this.findC.length; ++i) {
            for(byte j = 0; j < this.findC[i].length; ++j) {
               this.findC[i][j] = 3;
            }
         }
      }

   }

   public void draw0(int x, int y, int h) {
      Ui.i().fillRectB();
      Ui.i().drawString("游戏规则", 120, y + 20, 33, 3, 1);
      Ui.i().drawUi(7, 82, y + 20, 40, 0);
      Ui.i().drawUi(7, 158, y + 20, 36, 4);
      Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
      Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 60, 4);
      Ui.i().drawStringY(this.gr.about_a, 19, y + 20 + 10, 20, 0, 0);
      Ui.i().drawString("需要的参加费：" + this.sell_money + "金", 19, y + 40 + 10 + h, 0, 0, 1);
      this.gr.drawMoney(120, 320, 3, false);
      Ui.i().drawYesNo(true, true);
   }

   private void drawGame(int x, int y, int h) {
      Ui.i().fillRectB();
      Ui.i().drawUi(7, 82, y + 20, 40, 0);
      Ui.i().drawUi(7, 158, y + 20, 36, 4);
      Ui.i().drawK(x, y + 20 + 5, 240 - (x << 1), h, 4);
      Ui.i().drawK(x, y + 20 + 10 + h, 240 - (x << 1), 40, 4);

      for(int i = 0; i < this.findN.length; ++i) {
         for(int j = 0; j < this.findN[i].length; ++j) {
            this.drawCard(i, j, this.wh + this.gx + j * (this.gw + this.gspace), this.hh + this.gy + i * (this.gh + this.gspace), this.findC[i][j] == 3 ? 1 : this.findC[i][j]);
         }
      }

      Ui.i().drawRectZ(15400191, this.wh + this.gx + this.tx * (this.gw + this.gspace), this.hh + this.gy + this.ty * (this.gh + this.gspace), this.gw, this.gh, 3);
      if (this.count > 0) {
         Ui.i().drawNum("" + this.count / 10, 130, 24, 36, 2);
      } else {
         Ui.i().drawString("游戏开始", 120, 4, 17, 3, 0);
         Ui.i().drawString("可错次数：" + (this.time < 0 ? 0 : this.time - 1) + "次", 120, 287, 17, 0, 0);
      }

   }

   private void drawCard(int i, int j, int x, int y, int mode) {
      if (mode == 0) {
         Ui.i().drawK(x, y, this.gw, this.gh, 1);
         Ui.i().drawUi(25, x + (this.gw >> 1), y + (this.gh >> 1), 3, 0);
      } else if (mode == 1) {
         Ui.i().drawK(x + (this.gw >> 1) - 2, y - 3, 5, this.gh + 6, 1);
         if (++this.findC[i][j] > 3) {
            this.findC[i][j] = 0;
         }

         int mode = true;
      } else {
         Ui.i().drawK(x, y, this.gw, this.gh, 5);
         this.gr.drawItem(this.findN[i][j], x + (this.gw >> 1), y + (this.gh >> 1), 3);
         Ui.i().drawNum("" + this.findN[i][j], x + this.gw, y + this.gh, 40, 0);
      }

   }
}
