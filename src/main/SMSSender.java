package main;

import dm.Ms;
import dm.Ui;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class SMSSender implements Constants_H, Key_H {
   private static SMSSender smsSender;
   private GameRun gr;
   StringBuffer sms = new StringBuffer();
   StringBuffer address = new StringBuffer();
   private String[][] menuTxt = new String[][]{{"SMS Shop"}, {"Buy 30000 Gold", "As the young master of the four great families, you can't be without money! A one-time investment is even better value. Pay now and get 30000 Gold instantly. Total: 4 yuan"}, {"Buy 5000 Gold", "As the young master of the four great families, you can't be without money! Pay now and get 5000 Gold instantly. Total: 2 yuan"}, {"Buy 50 Badges", "Badges can purchase double experience, pet skills, powerful pet capture balls and various magical items. Pay now and get 50 Badges instantly. Total: 4 yuan"}, {"Buy 10 Badges", "Badges can purchase double experience, pet skills, powerful pet capture balls and various magical items. Pay now and get 10 Badges instantly. Total: 2 yuan"}, {"Pet Upgrade", "All your carried pets instantly level up by 5 (pets over level 70 cannot level up). Total: 2 yuan"}, {"Buy Strange Beast", "Riding the cute Strange Beast doubles movement speed and you won't encounter any enemies! Pay now for unlimited use. Act now! Total: 2 yuan"}, {"Official Verification", "Permanently unlock all subsequent game content and maps. Also receive 5 free Badges (can buy powerful items). Total: 4 yuan"}, {"Battle Revival", "Death is inevitable in battle. Fully recover all your carried pets. Also freely level them up by 5 (pets over level 70 cannot level up), making the next battles easier. Total: 2 yuan"}};
   public boolean sms_a = true;
   public boolean sms_b = false;
   private StringBuffer[] about;
   private int tState;
   private byte[][] menu = new byte[][]{{0, 1, 2, 3, 4, 5}, {6}, {7}, {8}};
   private byte[] sel = new byte[2];
   private byte showLine;
   private byte tMyState;
   private byte menu_state;
   private byte smsType;
   private byte sendSms = -1;
   private byte[][] smsCount = new byte[][]{{1, 1, 1}, {1, 1, 2}, {1, 1, 3}, {1, 1, 4}, {1, 1, 5}, {1, 1, 0}, {1, 1, 6}, {1, 1, 5}};
   public byte idSmsLevel;
   public String[][] uc_text = new String[][]{{"Buy 30000 Gold, only 4 yuan.", "74", "161", "01", "01"}, {"Buy 5000 Gold, only 2 yuan.", "74", "161", "02", "01"}, {"Buy 50 Badges, only 4 yuan.", "74", "161", "03", "01"}, {"Buy 10 Badges, only 2 yuan.", "74", "161", "04", "01"}, {"Pet Upgrade, only 2 yuan.", "74", "161", "05", "01"}, {"Buy Strange Beast, only 2 yuan.", "74", "161", "06", "01"}, {"Official Verification, only 4 yuan.", "74", "161", "07", "01"}, {"Battle Revival, only 2 yuan.", "74", "161", "08", "01"}};
   public int[] uc_number = new int[]{40, 20, 40, 20, 20, 20, 40, 20};
   public boolean success = false;

   public SMSSender(GameRun gr_) {
      this.gr = gr_;
      smsSender = this;
   }

   public static SMSSender i(GameRun gr_) {
      if (smsSender == null) {
         smsSender = new SMSSender(gr_);
      }

      return smsSender;
   }

   private void setAddress() {
      this.sms.delete(0, this.sms.length());
      this.address.delete(0, this.address.length());
      String[] a = new String[]{"02", "03", "04", "05", "06", "01"};
      String[] sms_id = new String[]{"", ""};
      this.sms.append(sms_id[this.smsCount[this.smsType][0]]);
      this.sms.append("040");
      this.sms.append(a[this.smsType]);
      String address_ = "";
      this.address.append(address_);
   }

   public boolean send() {
      this.setAddress();
      MessageConnection smsconn = null;

      try {
         String s = "sms://" + this.address.toString();
         smsconn = (MessageConnection)Connector.open(s);
         TextMessage txtmessage = (TextMessage)smsconn.newMessage("text");
         txtmessage.setAddress(s);
         txtmessage.setPayloadText(this.sms.toString());
         smsconn.send(txtmessage);
         smsconn.close();
         smsconn = null;
         return true;
      } catch (Exception var5) {
         if (smsconn != null) {
            try {
               smsconn.close();
            } catch (Exception var4) {
            }
         }

         return false;
      }
   }

   public void go(int menu_state_, boolean bb) {
      if (bb) {
         GameRun var10001 = this.gr;
         this.tState = GameRun.run_state;
         this.tMyState = this.gr.map.my.state;
      } else {
         this.tState = -1;
      }

      this.sms_a = true;
      this.sms_b = false;
      GameRun var10000 = this.gr;
      GameRun.run_state = -20;
      this.menu_state = (byte)menu_state_;
      this.showLine = 5;
      this.sendSms = -1;
      this.sel[0] = this.sel[1] = (byte)(this.menu[this.menu_state].length > 1 ? 1 : 0);
      this.smsType = (byte)(this.menu[this.menu_state][this.sel[0]] - 1);
      this.about = Ms.i().groupString(this.menuTxt[this.menu[this.menu_state][this.sel[0]]][1], 202);
   }

   public void paint() {
      this.draw();
   }

   public void run() {
      if (this.sendSms == 1) {
         this.sendSms = 2;
      } else if (this.sendSms == 2) {
         this.success = this.sendSms(this.smsType);
         if (this.success) {
            this.sendSms = 4;
         } else {
            this.sendSms = 3;
         }

         if (this.sendSms == 4 && this.smsCount[this.smsType][1] > 1) {
            if (++this.gr.rmsSms[this.smsCount[this.smsType][2]] != this.smsCount[this.smsType][1]) {
               this.sendSms = 0;
               Ms.i().rmsOptions(5, this.gr.rmsSms, 2);
               Ms.i().rmsOptions(5, (byte[])null, 4);
            } else {
               this.gr.rmsSms[this.smsCount[this.smsType][2]] = 0;
            }
         }
      }

      if (this.sendSms > 3 && this.sendSms < 15) {
         byte var10002 = this.sendSms;
         this.sendSms = (byte)(var10002 + 1);
         if (var10002 == 14) {
            GameRun var1;
            switch (this.smsType) {
               case 0:
                  var1 = this.gr;
                  var1.money += 30000;
                  break;
               case 1:
                  var1 = this.gr;
                  var1.money += 5000;
                  break;
               case 2:
                  var1 = this.gr;
                  var1.coin += 50;
                  break;
               case 3:
                  var1 = this.gr;
                  var1.coin += 10;
                  break;
               case 4:
                  this.tState = -1;
               case 7:
                  this.goLevel();
                  break;
               case 5:
                  this.gr.rmsSms[0] = 10;
                  break;
               case 6:
                  this.gr.rmsSms[this.smsCount[this.smsType][2]] = 10;
                  var1 = this.gr;
                  var1.coin += 5;
                  this.sms_b = true;
            }
         }
      } else if (this.sendSms == 15) {
         this.gr.saveGame();
         this.sendSms = 16;
      } else if (this.sendSms == 23) {
         if (this.menu_state != 0) {
            if (this.gr.say_c == 0) {
               this.outState();
            }
         } else {
            this.sendSms = -1;
         }
      } else if (this.sendSms == 34) {
         this.sendSms = -1;
      }

   }

   public void key() {
      if (this.sendSms == -1 && Ms.i().key_Up_Down()) {
         if (Ms.i().key_delay()) {
            return;
         }

         if (this.menu[this.menu_state].length > 1) {
            Ms.i().selectS(this.sel, 1, this.menu[this.menu_state].length, this.showLine);
            this.smsType = (byte)(this.menu[this.menu_state][this.sel[0]] - 1);
            this.about = Ms.i().groupString(this.menuTxt[this.menu[this.menu_state][this.sel[0]]][1], 202);
         }
      } else if ((this.sendSms == -1 || this.sendSms == 0) && Ms.i().key_S1()) {
         Ms.i().keyRelease();
         if (this.smsType == 4 && this.sel[0] != 7 && (this.gr.myMon_length < 1 || !this.isMyMonLevel())) {
            this.sendSms = -1;
            this.gr.say("目前没有可以升级的宠物！", 0);
         } else if (this.sendSms != 0 && this.smsCount[this.smsType][1] > 1 && this.gr.rmsSms[this.smsCount[this.smsType][2]] > 0 && this.gr.rmsSms[this.smsCount[this.smsType][2]] != this.smsCount[this.smsType][1]) {
            this.sendSms = 0;
         } else if (this.sendSms != 0) {
            this.sendSms = 0;
         } else {
            this.sendSms = 1;
         }
      } else if ((this.sendSms == -1 || this.sendSms == 0 || this.sendSms == 3 || this.sendSms == 24) && Ms.i().key_S2()) {
         Ms.i().keyRelease();
         if (this.sendSms == -1) {
            this.outState();
            if (this.smsType == 6) {
            }
         } else {
            this.sendSms = -1;
         }
      }

   }

   public void draw() {
      int x = 1;
      int y = 1;
      int w = 238;
      int h = 319;
      int fh = 23;
      Ui.i().fillRectB();
      Ui.i().drawK2(x, y, w, h, 0);
      if (this.menu[this.menu_state][0] == 6) {
         Ui.i().drawK1(63, y + 3, 114, fh, 4);
      } else {
         Ui.i().drawK1(82, y + 3, 76, fh - 4, 4);
      }

      Ui.i().drawString(this.menuTxt[this.menu[this.menu_state][0]][0], 120, y + 3, 17, 0, 1);
      x += 5;
      y += fh + 1;
      w -= 10;
      int i;
      if (this.menu[this.menu_state].length > 1) {
         Ui.i().drawK1(x, y, w - 15, fh * this.showLine, 1);
         Ui.i().sliding(x + w - 13, y, fh * this.showLine, this.sel[0] - 1, this.menu[this.menu_state].length - 1, true);
         Ui.i().drawListKY(this.showLine, x, y + 2, w - 15, 2, fh, -1, this.sel[0] - this.sel[1], 4, 2);

         for(i = this.sel[1]; i < this.sel[1] + this.showLine && i < this.menu[this.menu_state].length; ++i) {
            Ui.i().drawString(this.menuTxt[this.menu[this.menu_state][i]][0], x + (w >> 1), y + 4 + (i - this.sel[1]) * (fh - 1), 17, this.sel[0] == i ? 0 : 3, 0);
         }

         y += fh * this.showLine + 5;
      }

      h -= y + 10;
      Ui.i().drawK1(x, y, w, h, 2);

      for(i = 0; i < this.about.length; ++i) {
         Ui.i().drawStringColor(this.about[i].toString(), 19, y + fh * i, 12, 0);
      }

      this.draw0();
   }

   private void draw0() {
      boolean bLeft = true;
      boolean bRight = true;
      if (this.sendSms > -1) {
         String smsTip = "";
         if (this.sendSms == 0) {
            int i = this.smsCount[this.smsType][2];
            if (i < 0) {
               i = 0;
            } else {
               i = this.gr.rmsSms[i];
            }

            smsTip = this.getSmsTip(i, this.smsCount[this.smsType][1] - i);
         } else if (this.sendSms != 1 && this.sendSms != 2) {
            if (this.sendSms == 3) {
               smsTip = "发送失败！";
               bLeft = false;
            } else if (this.sendSms > 3 && this.sendSms < 15 || this.sendSms > 23 && this.sendSms < 34) {
               smsTip = "购买已成功！";
               bRight = false;
               bLeft = false;
               if (this.sendSms > 23 && this.sendSms < 34) {
                  ++this.sendSms;
               }
            } else if (this.sendSms == 15) {
               smsTip = "自动保存游戏。";
               bRight = false;
               bLeft = false;
            } else if (this.sendSms < 23) {
               smsTip = "保存游戏成功。";
               ++this.sendSms;
               bRight = false;
               bLeft = false;
               if (this.smsType == 5) {
                  this.gr.say("购买已成功！游戏已保存。#n新游戏后此功能不再要求付费。", -1);
               } else if (this.smsType == 6) {
                  this.gr.say("购买已成功！获得5枚徽章(背包的卷轴界面可查看）。游戏已保存。#n新游戏后此功能不再要求付费。", 0);
               }
            }
         } else {
            bRight = false;
            bLeft = false;
         }

         this.gr.showString(smsTip, 120, 0);
      }

      Ui.i().drawYesNo(bLeft, bRight);
   }

   private String getSmsTip(int i0, int i1) {
      String tip = "";
      tip = tip + "确认付费吗？";
      return tip;
   }

   private void outState() {
      GameRun var10000;
      if (this.tState != -1) {
         var10000 = this.gr;
         GameRun.run_state = this.tState;
         this.gr.map.my.state = this.tMyState;
         var10000 = this.gr;
         if (GameRun.run_state == -31) {
            this.sms_a = false;
         }
      } else {
         var10000 = this.gr;
         GameRun.run_state = -10;
      }

   }

   public void paintLevel() {
      if (this.gr.b_c == 1) {
         this.gr.drawEvolveUI(0, this.idSmsLevel, true);
      }
   }

   public void runLevel() {
      if (this.gr.b_c == 0) {
         if (this.gr.levelUp_in_battle[this.idSmsLevel][0] == 1) {
            this.gr.levelUp_in_battle[this.idSmsLevel][0] = 0;
            this.gr.b_c = 1;
            this.gr.say_s = 52;
            this.gr.levelPro(this.idSmsLevel, true);
            this.gr.setStringB("生命：+" + this.gr.proReplace[this.idSmsLevel][0] + "#n" + "能量" + "：+" + this.gr.proReplace[this.idSmsLevel][1], 240, 0);
            this.gr.setStringB("力量：+" + this.gr.proReplace[this.idSmsLevel][3] + "#n" + "防御" + "：+" + this.gr.proReplace[this.idSmsLevel][4] + "#n" + "敏捷" + "：+" + this.gr.proReplace[this.idSmsLevel][5], 240, 1);
            this.gr.initMonStream(2, this.gr.mList_id[this.gr.myMonsters[this.idSmsLevel].monster[0]][0], 1);
         } else {
            ++this.idSmsLevel;
         }

         if (this.idSmsLevel >= this.gr.myMon_length) {
            GameRun var10000;
            if (this.tState == -1) {
               var10000 = this.gr;
               GameRun.run_state = -20;
               this.gr.levelUp_in_battle = (byte[][])null;
               this.gr.proReplace = (short[][])null;
            } else {
               var10000 = this.gr;
               GameRun.run_state = this.tState;
               if (this.tState == -31) {
                  this.gr.initMonStream(2, this.gr.mList_id[this.gr.myB.getMon().monster[0]][0], 1);
                  this.gr.myB.act_num = 0;
                  this.gr.initSkillList(this.gr.myB.getMon());

                  for(byte i = 0; i < this.gr.myMon_length; ++i) {
                     this.gr.proReplace[this.gr.myMonsters[i].monster[1]][6] = (short)this.gr.myMonsters[i].monster[2];
                  }
               }
            }
         }
      } else if (this.gr.b_c == 1) {
         this.gr.say_s = (short)((byte)Ms.i().mathSpeedDown(this.gr.say_s, 4, true));
      }

   }

   public void keyLevel() {
      if (!Ms.i().key_delay()) {
         if (this.gr.b_c == 1 && this.gr.say_s == 0) {
            this.gr.b_c = 0;
         }

      }
   }

   public void goLevel() {
      GameRun var10000 = this.gr;
      GameRun.run_state = -21;
      this.idSmsLevel = 0;
      this.gr.b_c = 0;
      if (this.tState != -31) {
         this.gr.levelUp_in_battle = (byte[][])null;
         this.gr.levelUp_in_battle = new byte[this.gr.max_takes][2];
         this.gr.proReplace = (short[][])null;
         this.gr.proReplace = new short[this.gr.myMonsters.length][7];
      }

      for(byte i = 0; i < this.gr.myMon_length; ++i) {
         if (this.gr.myMonsters[i].monster[2] >= 70) {
            this.gr.healMonster(this.gr.myMonsters[i]);
         } else {
            this.gr.proReplace[i][6] = (short)this.gr.myMonsters[i].monster[2];
            this.gr.levelUp_in_battle[i][0] = 1;
            this.gr.levelUp_in_battle[i][1] = -1;

            for(byte j = 0; j < 5; ++j) {
               this.gr.levelPro(i, false);
               this.gr.getMagic(this.gr.myMonsters[i]);
               if (this.gr.getSkill != -1) {
                  this.gr.levelUp_in_battle[i][1] = this.gr.getSkill;
               }
            }
         }
      }

   }

   public boolean isMyMonLevel() {
      for(byte i = (byte)(this.gr.myMon_length - 1); i > -1; --i) {
         if (this.gr.myMonsters[i].monster[2] < 70) {
            return true;
         }
      }

      return false;
   }

   public boolean sendSms(int buyID) {
      GameRun var10000 = this.gr;
      boolean aa = GameRun.mc.uc_pay(this.uc_text[buyID][0], this.uc_text[buyID][1], this.uc_text[buyID][2], this.uc_text[buyID][3], this.uc_text[buyID][4], this.uc_number[buyID], buyID);
      return aa;
   }
}
