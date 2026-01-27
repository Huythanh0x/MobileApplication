package dm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;
import main.Constants_H;
import main.Key_H;

public class Ms implements Constants_H, Key_H {
   private static Ms msListener;
   private int sleep_time;
   private static RecordStore rms;
   final int RMSSIZE = 15360;
   public static int skip;
   public static int skip2;
   private final byte[] transA = new byte[]{0, 6, 3, 5, 2, 7, 1, 4};
   private static Random random = new Random();
   public static Font font = Font.getFont(0, 0, 8);
   public static boolean keyRepeat;
   public static int key;
   public static byte key_delay = 0;
   public static byte key_time = 10;

   public Ms() {
      msListener = this;
   }

   public static Ms i() {
      if (null == msListener) {
         msListener = new Ms();
      }

      return msListener;
   }

   public void sleep(int time) {
      this.sleep_time = time;
   }

   public int getSleep() {
      return this.sleep_time;
   }

   public byte[] rmsOptions(int recordId, byte[] info, int flag) {
      try {
         if (rms == null) {
            rms = RecordStore.openRecordStore("pk5_caihong", true);
         }

         if (rms.getNumRecords() == 0) {
            this.setRmsInit(true);
         }

         if (flag != 0) {
            if (flag == 1) {
               info = rms.getRecord(recordId);
               return info;
            }

            if (flag == 2) {
               rms.setRecord(recordId, info, 0, info.length);
            } else if (flag == 3) {
               this.setRmsInit(false);
            } else if (flag == 4) {
               if (rms != null) {
                  rms.closeRecordStore();
                  rms = null;
               }
            } else if (flag == 5) {
               if (rms.getSizeAvailable() > 15360) {
                  return null;
               }

               return new byte[]{1};
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return null;
   }

   public void setRmsInit(boolean mode) throws Exception {
      byte[] info = new byte[140];
      info[0] = -1;
      byte[] aaa = new byte[280];

      for(int i = 0; i < 82; ++i) {
         if (mode || i != 4) {
            if (i != 11) {
               if (mode) {
                  rms.addRecord(info, 0, info.length);
               } else {
                  rms.setRecord(i + 1, info, 0, info.length);
               }
            } else if (mode) {
               rms.addRecord(aaa, 0, aaa.length);
            } else {
               rms.setRecord(i + 1, aaa, 0, aaa.length);
            }
         }
      }

      byte[] info = null;
   }

   public byte[] getEventNowData(short[][] event_now) {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      byteArray.write(event_now.length);

      for(int i = 0; i < event_now.length; ++i) {
         if (event_now[i] == null) {
            byteArray.write(0);
         } else {
            byteArray.write(event_now[i].length);

            for(int j = 0; j < event_now[i].length; ++j) {
               byteArray.write(event_now[i][j] & 255);
               byteArray.write(event_now[i][j] >> 8 & 255);
            }
         }
      }

      return byteArray.toByteArray();
   }

   public short[][] readEventNowData() {
      short[][] event_now = (short[][])null;
      ByteArrayInputStream byteArray = new ByteArrayInputStream(this.rmsOptions(11, (byte[])null, 1));
      event_now = new short[byteArray.read()][];

      for(int i = 0; i < event_now.length; ++i) {
         int nn = byteArray.read();
         if (nn != 0) {
            event_now[i] = new short[nn];

            for(int j = 0; j < event_now[i].length; ++j) {
               event_now[i][j] = (short)(byteArray.read() | byteArray.read() << 8);
            }
         }
      }

      return event_now;
   }

   public static long getNum(byte[] b) {
      int rtn = 0;
      int len = b.length;

      for(int i = 0; i < len; ++i) {
         switch (len) {
            case 1:
               rtn += (byte)((b[i] & 255) << i * 8);
               break;
            case 2:
               rtn += (short)((b[i] & 255) << i * 8);
            case 3:
            case 5:
            case 6:
            case 7:
            default:
               break;
            case 4:
               rtn += (b[i] & 255) << i * 8;
               break;
            case 8:
               rtn = (int)((long)rtn + (long)((b[i] & 255) << i * 8));
         }
      }

      return (long)rtn;
   }

   public int getLen_byte(byte value) {
      return value < 0 ? value + 256 : value;
   }

   public int getLen_short(short value) {
      return value < 0 ? value + 65536 : value;
   }

   public int getInt(byte[] buf, int i) {
      return (buf[i] & 255) << 24 | (buf[i + 1] & 255) << 16 | (buf[i + 2] & 255) << 8 | buf[i + 3] & 255;
   }

   public void putInt(int value, byte[] buf, int i) {
      buf[i] = (byte)(value >> 24 & 255);
      buf[i + 1] = (byte)(value >> 16 & 255);
      buf[i + 2] = (byte)(value >> 8 & 255);
      buf[i + 3] = (byte)(value & 255);
   }

   public short getShort(byte[] buf, int i) {
      return (short)((buf[i] & 255) << 8 | buf[i + 1] & 255);
   }

   public void putShort(int value, byte[] buf, int i) {
      buf[i] = (byte)(value >> 8 & 255);
      buf[i + 1] = (byte)(value & 255);
   }

   public void putShort(byte[] buf, int value) {
      buf[skip++] = (byte)(value >> 8 & 255);
      buf[skip++] = (byte)(value >> 0 & 255);
   }

   public short[] byteArrayToShortArray(byte[] bytebuf) {
      skip = 0;
      int len = bytebuf.length >> 1;
      short[] shortbuf = new short[len];

      for(int i = 0; i < len; ++i) {
         shortbuf[i] = this.getStreamL(bytebuf, 2);
      }

      return shortbuf;
   }

   public byte[] shortArrayToByteArray(short[] shortbuf) {
      skip = 0;
      int len = shortbuf.length;
      byte[] bytebuf = new byte[len << 1];

      for(int i = 0; i < len; ++i) {
         this.putShort(bytebuf, shortbuf[i]);
      }

      return bytebuf;
   }

   private short getStreamL(byte[] data, int mode) {
      if (mode == 0) {
         return (short)data[skip++];
      } else if (mode == 1) {
         return (short)(data[skip++] + 100);
      } else {
         return mode == 2 ? (short)((data[skip++] & 255) << 8 | data[skip++] & 255) : (short)(data[skip++] & 255 | (data[skip++] & 255) << 8);
      }
   }

   public byte[] getStream(String i, int num) {
      byte[] data = null;

      try {
         DataInputStream dataInput = new DataInputStream(this.getClass().getResourceAsStream("/" + i));
         if (num > -1) {
            dataInput.readByte();

            for(byte n = 0; n < num; ++n) {
               dataInput.skip((long)this.getLen_short(dataInput.readShort()));
            }
         }

         data = new byte[this.getLen_short(dataInput.readShort())];
         dataInput.read(data);
         dataInput.close();
         dataInput = null;
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return data;
   }

   public int[] createIntArray(byte[] data) {
      int[] array = new int[this.getStreamL(data, 0)];

      for(int i = 0; i < array.length; ++i) {
         array[i] = data[skip++] & 255 | (data[skip++] & 255) << 8 | (data[skip++] & 255) << 16 | (data[skip++] & 255) << 24;
      }

      return array;
   }

   public short[] createShortArray(byte[] data, int mode) {
      short[] array = new short[this.getStreamL(data, mode)];

      for(int j = 0; j < array.length; ++j) {
         array[j] = this.getStreamL(data, mode == 2 ? 2 : -1);
      }

      return array;
   }

   public short[][] createShort2Array(byte[] data, int mode) {
      short[][] array = new short[this.getStreamL(data, mode)][];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.createShortArray(data, mode);
      }

      return array;
   }

   public short[][][] createShort3Array(byte[] data, int mode) {
      short[][][] array = new short[this.getStreamL(data, mode)][][];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.createShort2Array(data, mode);
      }

      return array;
   }

   public byte[] createArray(byte[] data) {
      byte[] array = new byte[this.getLen_byte(data[skip++])];

      for(int j = 0; j < array.length; ++j) {
         array[j] = data[skip++];
      }

      return array;
   }

   public byte[][] create2Array(byte[] data) {
      byte[][] array = new byte[this.getLen_byte(data[skip++])][];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.createArray(data);
      }

      return array;
   }

   public byte[][][] create3Array(byte[] data) {
      byte[][][] array = new byte[this.getLen_byte(data[skip++])][][];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.create2Array(data);
      }

      return array;
   }

   public byte[][][][] create4Array(byte[] data) {
      byte[][][][] array = new byte[this.getLen_byte(data[skip++])][][][];

      for(int i = 0; i < array.length; ++i) {
         array[i] = this.create3Array(data);
      }

      return array;
   }

   public StringBuffer[] createStringArray(byte[] data) {
      StringBuffer[] string = new StringBuffer[this.getLen_byte(data[skip++])];

      for(byte i = 0; i < string.length; ++i) {
         short l_c = (short)data[skip];
         if (l_c < 0) {
            l_c = (short)(l_c + 256);
         }

         string[i] = new StringBuffer(this.getDialogs(data, skip + 1, l_c));
         skip += 1 + l_c * 2;
      }

      return string;
   }

   public StringBuffer createStringArrayOne(byte[] data) {
      return new StringBuffer(this.getDialogs(data, 2, this.getLen_byte(data[1])));
   }

   public StringBuffer[][] createString2Array(byte[] dataa) {
      byte len = dataa[skip++];
      StringBuffer[][] string = new StringBuffer[len][];

      for(byte i = 0; i < string.length; ++i) {
         string[i] = this.createStringArray(dataa);
      }

      return string;
   }

   public String getDialogs(byte[] data, int start, int len) {
      StringBuffer dialog = new StringBuffer();

      for(int i = 0; i < len; ++i) {
         dialog.append((char)(data[start + (i << 1)] << 8 | 255 & data[start + (i << 1) + 1]));
      }

      return dialog.toString();
   }

   public StringBuffer[] groupString(String info, int width) {
      StringBuffer[] tempResult = new StringBuffer[30];
      StringBuffer temp = new StringBuffer();
      StringBuffer orig = new StringBuffer(info);
      short infoLength = (short)info.length();
      byte tc = 0;
      byte tw = (byte)this.getStringWidth("#0");
      byte rows = -1;
      boolean isNewRow = false;
      String tcolor = "";

      for(int i = 0; i < infoLength; ++i) {
         if (orig.charAt(0) == '#') {
            if (orig.charAt(1) == 'n') {
               isNewRow = true;
            } else {
               tcolor = "#" + orig.charAt(1);
               temp.append(tcolor);
               ++tc;
            }

            orig.deleteCharAt(0);
            orig.deleteCharAt(0);
            ++i;
         } else {
            temp.append(orig.charAt(0));
            if (width != 0 && this.getStringWidth(temp.toString()) <= width + tw * tc) {
               orig.deleteCharAt(0);
            } else if (width != 0) {
               --i;
               temp.deleteCharAt(temp.length() - 1);
               isNewRow = true;
            } else {
               orig.deleteCharAt(0);
            }

            if (i == infoLength - 1 && !isNewRow) {
               isNewRow = true;
            }
         }

         if (isNewRow) {
            ++rows;
            tc = (byte)(tcolor.length() == 0 ? 0 : 1);
            isNewRow = false;
            tempResult[rows] = temp;
            temp = null;
            temp = new StringBuffer();
            temp.append(tcolor);
         }
      }

      StringBuffer[] result = new StringBuffer[rows + 1];
      System.arraycopy(tempResult, 0, result, 0, rows + 1);
      tempResult = null;
      orig = null;
      temp = null;
      tcolor = null;
      return result;
   }

   public String[] loadText(byte[] word_uni) {
      try {
         StringBuffer stringbuffer = new StringBuffer("");
         int j = 2;

         int k;
         int l;
         while(j < word_uni.length) {
            k = word_uni[j++];
            if (k < 0) {
               k += 256;
            }

            l = word_uni[j++];
            if (l < 0) {
               l += 256;
            }

            char c = (char)(k + (l << 8));
            stringbuffer.append(c);
         }

         String strReturn = stringbuffer.toString();
         stringbuffer = null;
         Vector vecString = new Vector();
         k = 0;
         l = 0;

         for(j = 0; j < strReturn.length(); ++j) {
            if (strReturn.charAt(j) == '\n' || j == strReturn.length()) {
               String temp = strReturn.substring(k, l);
               vecString.addElement(temp);
               k = l + 1;
            }

            ++l;
         }

         strReturn = null;
         String[] reStr = new String[vecString.size()];

         for(j = 0; j < vecString.size(); ++j) {
            String s = (String)vecString.elementAt(j);
            reStr[j] = s;
         }

         vecString = null;
         return reStr;
      } catch (Exception var11) {
         var11.printStackTrace();
         return null;
      }
   }

   Image createImage(Image image, int x, int y, int width, int height, int trans) {
      if (x + width > image.getWidth()) {
         width = image.getWidth() - x;
      }

      if (y + height > image.getHeight()) {
         height = image.getHeight() - y;
      }

      return Image.createImage(image, x, y, width, height, this.transA[trans]);
   }

   Image createCellImage(Image image, int cell_index, int cell_width, int cell_height, int trans) {
      int temp_cell_x = cell_index % (image.getWidth() / cell_width) * cell_width;
      int temp_cell_y = cell_index % (image.getHeight() / cell_height) * cell_height;
      return this.createImage(image, temp_cell_x, temp_cell_y, cell_width, cell_height, trans);
   }

   public Image[] createImageArray(int len, String name) {
      Image[] img = new Image[len];

      for(short i = 0; i < img.length; ++i) {
         img[i] = this.createImage(name + i);
      }

      return img;
   }

   public Image createImage(String name, int no) {
      byte[] data = this.getStream(name, no);
      return Image.createImage(data, 0, data.length);
   }

   public Image createImage(String imageName) {
      try {
         return Image.createImage("/" + imageName + ".png");
      } catch (Exception var3) {
         return null;
      }
   }

   public Sprite createSprite(String name, boolean mode) {
      byte[] date = this.getStream(name + ".data", -1);
      skip = 0;
      return mode ? Sprite.Create(this.createImage(name), this.create2Array(date), this.create3Array(date), this.create3Array(date)) : Sprite.Create(this.createImage(name), this.createShort2Array(date, 2), this.createShort3Array(date, 2), this.createShort3Array(date, 2));
   }

   public void setSprite(Sprite sp, String name, boolean mode) {
      byte[] date = this.getStream(name + ".data", -1);
      skip = 0;
      sp.nullIMFA();
      if (mode) {
         sp.Set(this.createImage(name), this.create2Array(date), this.create3Array(date), this.create3Array(date));
      } else {
         sp.Set(this.createImage(name), this.createShort2Array(date, 2), this.createShort3Array(date, 2), this.createShort3Array(date, 2));
      }

      byte[] date = null;
   }

   public boolean equals(StringBuffer sbuff, String str) {
      return sbuff.toString().equals(str);
   }

   public boolean isRect(int a0, int a1, int aw, int ah, int b0, int b1, int bw, int bh) {
      return a0 < b0 + bw && a0 + aw > b0 && a1 < b1 + bh && a1 + ah > b1;
   }

   public String getPrecision(int t) {
      return t / 10 + "." + t % 10;
   }

   public int sqrt(int x) {
      if (x <= 0) {
         return 0;
      } else {
         x *= 10000;
         int b = 10000;

         int y;
         do {
            y = b;
            b = x / b + b >> 1;
         } while(b < y);

         y /= 100;
         return y;
      }
   }

   public static int getRandom(int ss) {
      return (random.nextInt() & Integer.MAX_VALUE) % ss;
   }

   public static int abs(int a) {
      return a > 0 ? a : -a;
   }

   public static int compare_min(int c0, int c1) {
      return c0 <= c1 ? c0 : c1;
   }

   public short mathPercent(int m0, int m1, int per) {
      if (per < 1) {
         per = 1;
      }

      return (short)(m0 * m1 / per);
   }

   public int getStringWidth(String str) {
      return font.stringWidth(str);
   }

   public byte getMin(byte i0, byte i1) {
      return i0 > i1 ? i1 : i0;
   }

   public short mathSpeedDown(int volue, int num, boolean bb) {
      if (volue / num != 0) {
         volue -= volue / num;
      } else if (bb && volue > 0) {
         --volue;
      } else if (bb && volue < 0) {
         ++volue;
      } else {
         volue = 0;
      }

      return (short)volue;
   }

   public short mathSpeedUp(int volue, int max, int speed) {
      volue -= (max - volue) / speed;
      return (short)(volue < 0 ? 0 : volue);
   }

   public short mathSpeedN(int volue, int maxv, int speed, boolean bb) {
      if (volue > maxv && volue - speed > maxv) {
         volue -= speed;
      } else if (volue < maxv && volue + speed < maxv) {
         volue += speed;
      } else if (bb && volue > maxv) {
         --volue;
      } else if (bb && volue < maxv) {
         ++volue;
      } else {
         volue = maxv;
      }

      return (short)volue;
   }

   public byte select(int select, int min, int max) {
      if (max == 0) {
         return (byte)select;
      } else {
         if (abs(key) % 2 == 1) {
            --select;
            if (select < min) {
               select = max;
               return (byte)select;
            }
         }

         if (abs(key) % 2 == 0) {
            ++select;
            if (select > max) {
               select = min;
            }
         }

         return (byte)select;
      }
   }

   public void selectS(byte[] select, int min, int max, int showLine) {
      if (max != 0) {
         select[0] = this.select(select[0], min, max - 1);
         if (select[1] - 1 == select[0]) {
            --select[1];
         } else if (select[1] + showLine == select[0]) {
            ++select[1];
         } else if (select[0] == max - 1) {
            select[1] = (byte)(max - min < showLine ? min : max - showLine);
         } else if (select[0] == min) {
            select[1] = (byte)min;
         }

      }
   }

   public void correctSelect(byte[] select, int max, int showLine) {
      if (select[0] < max) {
         select[1] = (byte)(select[0] - showLine + 1);
      } else {
         select[0] = (byte)(max - 1);
         select[1] = (byte)(max - showLine);
      }

      if (select[0] < 0) {
         select[0] = 0;
      }

      if (select[1] < 0) {
         select[1] = 0;
      }

   }

   private boolean checkIsSimulate() {
      if (Runtime.getRuntime().totalMemory() >= 8000000L) {
         return true;
      } else {
         try {
            Class.forName("emulator.Emulator");
            Class.forName("com.sprintpcs.util.System");
            return true;
         } catch (Exception var2) {
            String platForm = System.getProperty("microedition.platform");
            return platForm.toLowerCase().indexOf("wtk") != -1 || platForm.toLowerCase().indexOf("javasdk") != -1 || platForm.toLowerCase().indexOf("j2me") != -1;
         }
      }
   }

   public void runDelay() {
      if (key_delay > 0) {
         --key_delay;
      }

   }

   public boolean key_delay() {
      if (key_delay == 0) {
         key_delay = key_time;
         if (key_time > 1) {
            --key_time;
         }

         return false;
      } else {
         return true;
      }
   }

   public void keyRelease() {
      keyRepeat = false;
      key_delay = 0;
      key_time = 10;
   }

   public boolean key_Up_Down() {
      return key == -1 || key == -2;
   }

   public boolean key_Up() {
      return key == -1;
   }

   public boolean key_Down() {
      return key == -2;
   }

   public boolean key_Left_Right() {
      return key == -3 || key == -4;
   }

   public boolean key_Left() {
      return key == -3;
   }

   public boolean key_Right() {
      return key == -4;
   }

   public boolean key_S1_Num5() {
      return key == -6 || key == 53 || key == -5;
   }

   public boolean key_S1() {
      return key == -6;
   }

   public boolean key_S2() {
      return key == -7;
   }

   public boolean key_Num0() {
      return key == 48;
   }

   public boolean key_Num1() {
      return key == 49;
   }

   public boolean key_Num3() {
      return key == 51;
   }

   public boolean key_Num9() {
      return key == 57;
   }
}
