package main;

import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectUtils;
import dm.Monster;
import dm.Ms;
import dm.Sound;
import dm.Translator;
import dm.Ui;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class GameRun_F implements Constants_H, Key_H {
   static MainCanvas mc;
   static Image scrT;
   static Graphics offG;
   static Graphics g;
   static DirectGraphics dg;
   final byte[] anchor = new byte[]{20, 36, 40, 24, 24, 40, 36, 20};
   final short[] transB = new short[]{0, 90, 180, 270, 8192, 8282, 8372, 8462};
   public StringBuffer[] about_a;
   public StringBuffer[] about_b;
   public StringBuffer[] about_c;
   public StringBuffer[] about_d;
   public int introX = 120;
   public String gogoString = "";
   public int introT = 240;
   public String gogoST = "";
   private byte brow5 = 0;
   public StringBuffer[] showS;
   public byte page_max = 0;
   public byte help_page = 0;
   public byte[] src_c = new byte[]{0, 0, 0, 0};
   public byte[] info;
   public byte[] len;
   public String str_cur;
   public String[] action_str;
   public byte cur_a;
   public byte cur_b;
   public byte cur_c;
   public byte selectx;
   public byte selecty;
   public byte[] select_it = new byte[4];
   public byte[] select_st = new byte[4];
   public byte[][] selectBag = new byte[4][2];
   public byte[][] select = new byte[2][2];
   public short move_x;
   public short move_y;
   public byte[][][] items;
   public byte[] itemsLength;
   public byte max_takes = 3;
   public byte max_monsters = 10;
   public byte[] monInfoList;
   public byte myMon_length;
   public byte cMon_count;
   public Monster cur_mon;
   public Monster cur_emon;
   public Monster[] myMonsters;
   public Monster[] cMonsters;
   public byte[] rmsOther;
   public byte[] rmsSms;
   public byte[] rmsNidus;
   public byte[] nidusMap;
   public byte[][] nidusList;

   public void initGraphics(Graphics _g) {
      g = _g;
      dg = DirectUtils.getDirectGraphics(_g);
   }

   public void initOffG() {
      scrT = Image.createImage(260, 340);
      offG = scrT.getGraphics();
   }

   public void setStringB(String str, int w, int mode) {
      if (mode == 0) {
         this.about_a = Ms.i().groupString(str, w);
      } else if (mode == 1) {
         this.about_b = Ms.i().groupString(str, w);
      } else if (mode == 2) {
         this.about_c = Ms.i().groupString(str, w);
      } else if (mode == 3) {
         this.about_d = Ms.i().groupString(str, w);
      }

   }

   public short getSIndexW(String str) {
      byte num = 0;
      byte start = 0;
      byte end = (byte)str.indexOf("#", start);
      if (end == 0) {
         end = 1;
      }

      while(end != -1) {
         start += end;
         end = (byte)str.indexOf("#", start);
         ++num;
      }

      return (short)(Ms.i().getStringWidth("#1") * num);
   }

   public void drawZero(int x, int y, int anchor, int color) {
      Ui.i().drawString("Press 0 to continue", x, y, anchor, color, 0);
   }

   public void drawZero(String str, int y) {
      Ui var10000 = Ui.i();
      String var10001 = "Press 0 to " + str;
      Graphics var10004 = g;
      Graphics var10005 = g;
      var10000.drawString(var10001, 120, y, 1 | 32, 0, 0);
   }

   public void gogoWord(String string, int x, int y, int w, int color, int strT, int speed) {
      short sw = (short)(Ms.i().getStringWidth(string) - this.getSIndexW(string));
      if (sw > 240 - x - w) {
         this.introX -= speed;
         if (this.introX + sw < x) {
            this.introX = 240 - w - 57;
         }
      } else {
         this.introX = x;
      }

      g.clipRect(x, y, 240 - w - x, 25);
      Ui.i().drawStringColor(string, this.introX, y, color, strT);
      g.setClip(0, 0, 240, 320);
   }

   public void gogoWordM(String string, int y, int w, int color, int strT, int speed) {
      short sw = (short)Ms.i().getStringWidth(string);
      this.introT -= speed;
      if (this.introT + sw < w << 1) {
         this.introT = 241;
      }

      g.clipRect(w, y, 240 - (w << 1), 25);
      Ui.i().drawString(string, this.introT, y, 0, color, strT);
      g.setClip(0, 0, 240, 320);
   }

   public void setGogoWord() {
      this.introT = 240;
      Ms.i();
      Ms.skip = 0;
      StringBuffer[] sr = Ms.i().createStringArray(Ms.i().getStream("data/other/hint.t", -1));
      Ms.i();
      this.gogoST = sr[Ms.getRandom(sr.length)].toString();
      sr = null;
   }

   private void drawSnare5(int x, int y) {
      Ui.i().drawUi(40, x, y + (this.brow5 % 3 == 0 ? 1 : 0), 0, 0);
      byte var10002 = this.brow5;
      this.brow5 = (byte)(var10002 + 1);
      if (var10002 > 100) {
         this.brow5 = 0;
      }

   }

   public void showString(StringBuffer[] showS, int y, int offx, int mode) {
      if (mode != 0) {
         Ui.i().drawKuang(-5 + offx, y, 250, 20 * showS.length + 12);
         if (mode == 2) {
            this.drawSnare5(120 + offx, y + 20 * showS.length + 4);
         }
      }

      for(byte d = 0; d < showS.length; ++d) {
         Ui.i().drawStringColor(showS[d].toString(), 19 + offx, y + 4 + d * 20, -1, 1);
      }

   }

   public void showString(String string, int y, int offx) {
      byte strNum = (byte)(string.length() / 13);
      Ui.i().drawK0(-5 + offx, y, 250, 20 * (strNum + 1) + 8, 3);
      this.showStringM(string, 120 + offx, y + 3, 13, 0);
   }

   public void showStringM(String str, int x, int y, int num, int c) {
      byte strNum = (byte)(str.length() / num);

      for(byte d = 0; d <= strNum; ++d) {
         Ui var10000;
         Graphics var10004;
         Graphics var10005;
         if (strNum == 0) {
            var10000 = Ui.i();
            var10004 = g;
            var10005 = g;
            var10000.drawString(str, x, y, 1 | 16, c, 1);
         } else {
            var10000 = Ui.i();
            String var10001 = str.substring(d * num, d + 1 > strNum ? str.length() : (d + 1) * num);
            int var10003 = y + d * 20;
            var10004 = g;
            var10005 = g;
            var10000.drawString(var10001, x, var10003, 1 | 16, c, 0);
         }
      }

   }

   public void drawHelpStr(Graphics g, StringBuffer[] help_strbuff, int line_num, int x, int y, int anchor) {
      byte line_start = (byte)(line_num * this.help_page);

      for(byte i = line_start; i < line_start + line_num && i < help_strbuff.length; ++i) {
         Ui.i().drawString(help_strbuff[i].toString(), x, y + (i - line_start) * 22, anchor, 3, 0);
      }

      if (help_strbuff.length > line_num) {
         this.page_max = (byte)(help_strbuff.length / line_num + (help_strbuff.length % line_num != 0 ? 1 : 0));
         Ui.i().drawString(this.help_page + 1 + "/" + this.page_max, 120, 318, 1 | 32, 3, 0);
         Ui.i().drawTriangle(120, 308, 57, true, true);
      } else {
         this.page_max = 1;
      }

   }

   public void drawSrcLine(Graphics g, int y, int h, int piece, boolean dir, boolean mode) {
      byte i = 0;
      byte ph = (byte)(h / piece);
      g.setColor(0);
      short speed;
      if (!dir) {
         speed = (short)(2 << this.src_c[0]);

         for(i = (byte)(piece - 1); i > -1; speed = (short)(speed / 2)) {
            if (ph - speed > 0) {
               g.fillRect(0, y + i * ph + (speed < 2 ? 0 : speed), 240, ph - (speed < 2 ? 0 : speed));
            }

            --i;
         }

         if (mode && this.src_c[0] < 12) {
            ++this.src_c[0];
         } else if (!mode && this.src_c[0] > 0) {
            --this.src_c[0];
         }
      } else {
         for(speed = (short)(2 << this.src_c[1]); dir && i < piece; speed = (short)(speed / 2)) {
            if (ph - speed > 0) {
               g.fillRect(0, y + i * ph, 240, ph - (speed < 2 ? 0 : speed));
            }

            ++i;
         }

         if (mode && this.src_c[1] < 12) {
            ++this.src_c[1];
         } else if (!mode && this.src_c[1] > 0) {
            --this.src_c[1];
         }
      }

   }

   public String createString(String name) {
      Ms.i();
      Ms.skip = 0;
      // Best-effort mapping: "data/help_d.t" -> "help_d_t.json", "data/gamec.d" -> "gamec_d.json", etc.
      try {
         int slash = name.lastIndexOf(47);
         String base = slash >= 0 ? name.substring(slash + 1) : name;
         String group = base.replace('.', '_') + ".json";
         Translator.setGroup(group);
      } catch (Exception var4) {
         // ignore; translations will fallback to others
      }
      StringBuffer[] str = Ms.i().createStringArray(Ms.i().getStream(name, -1));
      return str[0].toString();
   }

   public byte[] createData(int no) {
      return Ms.i().getStream("4", no);
   }

   public void setAction_str(String[] temp_str) {
      this.action_str = temp_str;
      temp_str = null;
   }

   public void drawMenu(StringBuffer[] menu, int x, int y, int w) {
      byte color;
      if (w > 0) {
         Ui.i().drawKuang(x, y, w, menu.length * 22 + 12);
      } else {
         Ms.i();
         w = Ms.abs(w);
      }

      for(byte d = 0; d < menu.length; ++d) {
         if (d == 0) {
            color = 9;
         } else if (d == this.cur_a) {
            color = 8;
         } else {
            color = 0;
         }

         Ui var10000 = Ui.i();
         String var10001 = menu[d].toString();
         int var10002 = x + (w >> 1);
         int var10003 = y + 4 + d * 22;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawString(var10001, var10002, var10003, 1 | 16, color, 0);
      }

   }

   public void drawSelectMenu(Object[] menu, int x, int y, int w, int dis, int cn, int s_f) {
      for(byte d = 0; d < menu.length; ++d) {
         Ui.i().drawK0(x, y + d * (28 + dis), w, 28, d == s_f ? 1 : cn);
         Ui var10000 = Ui.i();
         String var10001 = menu[d].toString();
         int var10002 = x + (w >> 1);
         int var10003 = y + 2 + d * (28 + dis);
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawString(var10001, var10002, var10003, 1 | 16, d == s_f ? 0 : 3, 1);
      }

   }

   public void drawPauseMenu(int x, int y, int w, int h) {
      Ui.i().drawK2(x, y, w, h, 1);
      Ui.i().drawK1(x + 9, y + 20 + 11, w - 18, h - 20 - 3 * this.action_str.length - 5, 0);
      Ui.i().drawListKY(this.action_str.length, x + 8, y + 20 + 13, w - 16, 1, 27, -1, this.selecty, 4, 2);
      Ui.i().drawListSY(this.action_str, x + 8, y + 20 + 13, w - 16, 26, this.selecty, 0, 3, 0);
      if (this.selectx == 1) {
         Ui.i().drawVolume(x + (w >> 1) + 19 - 8, y + 20 + 16 + 28, Sound.i().getVolume(), this.selecty == 1);
      }

      Ui.i().drawK1(x + (w >> 1) + (this.selectx == 0 ? -40 : 0), y + 4, 40, 24, 4);
      Ui var10000 = Ui.i();
      int var10002 = x + (w >> 1) - 19;
      int var10003 = y + 5;
      Graphics var10004 = g;
      Graphics var10005 = g;
      var10000.drawString("Menu", var10002, var10003, 1 | 16, this.selectx == 0 ? 0 : 1, 0);
      var10000 = Ui.i();
      var10002 = x + (w >> 1) + 19;
      var10003 = y + 5;
      var10004 = g;
      var10005 = g;
      var10000.drawString("System", var10002, var10003, 1 | 16, this.selectx == 0 ? 1 : 0, 0);
      Ui.i().drawTriangle(x + (w >> 1), y + 12, 57, true, true);
   }

   public void saveMon(ByteArrayOutputStream byteArray, Monster mon) throws IOException {
      byteArray.write(mon.monster);

      for(int i = 0; i < 5; ++i) {
         int xxx = mon.monsterPro[i];
         byteArray.write((byte)(xxx & 255));
         byteArray.write((byte)(xxx >> 8 & 255));
      }

   }

   public void loadMon(ByteArrayInputStream byteArray, Monster mon, byte[][] data) throws IOException {
      mon.monster = new byte[18];
      mon.monsterPro = new short[8];
      byteArray.read(mon.monster);
      this.len = new byte[10];
      byteArray.read(this.len);

      for(int i = 0; i < 5; ++i) {
         mon.monsterPro[i] = (short)((this.len[1 + i * 2] & 255) << 8 | this.len[0 + i * 2] & 255);
      }

      mon.setProAFD(data[mon.monster[0]]);
      this.len = null;
   }

   public void saveMon(int flag) {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      int i = 0;

      try {
         if (flag == 0) {
            byteArray.write(this.cMon_count);

            while(i < this.cMon_count) {
               this.saveMon(byteArray, this.cMonsters[i]);
               ++i;
            }

            Ms.i().rmsOptions(8, byteArray.toByteArray(), 2);
         } else {
            byteArray.write(this.myMon_length);

            while(i < this.myMon_length) {
               this.saveMon(byteArray, this.myMonsters[i]);
               ++i;
            }

            Ms.i().rmsOptions(9, byteArray.toByteArray(), 2);
         }

         byteArray.close();
         byteArray = null;
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public void loadMon(int flag, byte[][] data) {
      this.info = Ms.i().rmsOptions(8 + flag, (byte[])null, 1);
      int i = 0;

      try {
         ByteArrayInputStream byteArray;
         if (flag == 0) {
            this.cMonsters = new Monster[95];
            if (this.info[0] == -1) {
               this.cMon_count = 0;
               return;
            }

            byteArray = new ByteArrayInputStream(this.info);

            for(this.cMon_count = (byte)byteArray.read(); i < this.cMon_count; ++i) {
               this.cMonsters[i] = new Monster();
               this.loadMon(byteArray, this.cMonsters[i], data);
            }
         } else {
            this.myMonsters = new Monster[5];
            if (this.info[0] == -1) {
               this.myMon_length = 0;
               return;
            }

            byteArray = new ByteArrayInputStream(this.info);

            for(this.myMon_length = (byte)byteArray.read(); i < this.myMon_length; ++i) {
               this.myMonsters[i] = new Monster();
               this.loadMon(byteArray, this.myMonsters[i], data);
            }
         }

         byteArray.close();
         byteArray = null;
         this.info = null;
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public void saveInfoList() {
      Ms.i().rmsOptions(6, this.monInfoList, 2);
   }

   public void loadInfoList() {
      this.info = Ms.i().rmsOptions(6, (byte[])null, 1);
      if (this.info[0] == -1) {
         this.monInfoList = new byte[105];

         for(int i = 0; i < this.monInfoList.length; ++i) {
            this.monInfoList[i] = 0;
         }
      } else {
         this.monInfoList = this.info;
      }

      this.info = null;
   }

   public void loadRmsOther() {
      this.info = Ms.i().rmsOptions(10, (byte[])null, 1);
      if (this.info[0] == -1) {
         this.setMaxTakes(true);
         this.initRmsOther();
      } else {
         this.rmsOther = this.info;
         this.setMaxTakes(false);
      }

      this.info = null;
   }

   public void setMaxTakes(boolean bb) {
      if (bb) {
         this.max_takes = 3;
         this.max_monsters = 10;
      } else if (this.rmsOther[3] == 2) {
         this.max_takes = 4;
         this.max_monsters = 20;
      } else if (this.rmsOther[3] == 3) {
         this.max_takes = 5;
         this.max_monsters = 40;
      } else if (this.rmsOther[3] == 4) {
         this.max_takes = 5;
         this.max_monsters = 80;
      } else if (this.rmsOther[3] == 5) {
         this.max_takes = 5;
         this.max_monsters = 95;
      }

   }

   public byte isMyLevel(boolean bb) {
      switch (this.rmsOther[3]) {
         case 1:
            if (bb && this.monInfoList[103] > 4) {
               this.rmsOther[3] = 2;
               break;
            }

            return 5;
         case 2:
            if (bb && this.monInfoList[103] > 9) {
               this.rmsOther[3] = 3;
               break;
            }

            return 10;
         case 3:
            if (bb && this.monInfoList[103] > 14) {
               this.rmsOther[3] = 4;
               break;
            }

            return 15;
         case 4:
            if (!bb || this.monInfoList[103] <= 29) {
               return 30;
            }

            this.rmsOther[3] = 5;
      }

      return -1;
   }

   public void saveItem() {
      int len = 0;

      for(int i = 0; i < 4; ++i) {
         len += this.itemsLength[i];
      }

      this.info = new byte[this.items.length + len * 2];
      len = 0;

      for(int i = 0; i < 4; ++i) {
         this.info[i] = this.itemsLength[i];
      }

      for(int i = 0; i < this.items.length; ++i) {
         for(int j = 0; j < this.itemsLength[i]; ++len) {
            this.info[len * 2 + 4] = this.items[i][j][0];
            this.info[len * 2 + 4 + 1] = this.items[i][j][1];
            ++j;
         }
      }

      Ms.i().rmsOptions(4, this.info, 2);
      this.info = null;
   }

   public void loadItem() {
      this.info = Ms.i().rmsOptions(4, (byte[])null, 1);
      this.len = new byte[]{16, 19, 23, 12};
      this.itemsLength = new byte[4];

      if (this.info[0] != -1) {
         for(int i = 0; i < 4; ++i) {
            this.itemsLength[i] = this.info[i];
         }
      }

      this.items = new byte[this.itemsLength.length][][];
      int k = 0;

      for(int i = 0; i < this.items.length; ++i) {
         this.items[i] = new byte[this.len[i]][2];

         for(int j = 0; j < this.itemsLength[i]; ++k) {
            this.items[i][j][0] = this.info[k * 2 + 4];
            this.items[i][j][1] = this.info[k * 2 + 4 + 1];
            ++j;
         }
      }

      this.info = null;
      this.len = null;
   }

   public void initRmsOther() {
      this.rmsOther = null;
      this.rmsOther = new byte[]{-1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   public void loadRmsSms() {
      this.rmsSms = Ms.i().rmsOptions(5, (byte[])null, 1);
      if (this.rmsSms[0] == -1) {
         this.rmsSms = null;
         this.rmsSms = new byte[]{-3, 0, 0, 0, 0, 0, 0};
      }

      if (this.rmsSms[0] == 10) {
         byte[] var10000 = this.rmsOther;
         var10000[2] = (byte)(var10000[2] | 8);
      }

   }

   public void loadRmsNidus() {
      this.rmsNidus = Ms.i().rmsOptions(3, (byte[])null, 1);
      if (this.rmsNidus[0] == -1) {
         this.rmsNidus = null;
         this.rmsNidus = new byte[]{-2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10, -2, 0, 0, 10, 10};
      }

   }

   public byte getRid(int i) {
      return this.rmsNidus[i * 5];
   }

   public byte getNid(int i) {
      return this.nidusList[0][this.getRid(i)];
   }

   public byte getNLevel(int i) {
      return this.nidusList[1][this.getRid(i)];
   }

   public short getNexp(int i, int n) {
      return Ms.i().getShort(this.rmsNidus, i * 5 + n);
   }

   public boolean addNidus(int id) {
      for(byte i = 0; i < this.rmsNidus.length; i = (byte)(i + 5)) {
         if (this.rmsNidus[i] == -2) {
            this.rmsNidus[i] = (byte)id;
            Ms.i().putShort(0, this.rmsNidus, i + 1);
            Ms.i().putShort(200, this.rmsNidus, i + 3);
            return true;
         }
      }

      return false;
   }

   public void delNidus(int i) {
      this.rmsNidus[i * 5] = -2;
   }

   public void setNidusExp(int exp) {
      exp = exp / 10 < 1 ? 1 : exp / 10;

      for(byte i = 0; i < 5; ++i) {
         if (this.getRid(i) != -2) {
            Ms.i().putShort(this.getNexp(i, 1) + exp, this.rmsNidus, i * 5 + 1);
            if (this.getNexp(i, 1) > this.getNexp(i, 3)) {
               Ms.i().putShort(this.getNexp(i, 3), this.rmsNidus, i * 5 + 1);
            }
         }
      }

   }
}
