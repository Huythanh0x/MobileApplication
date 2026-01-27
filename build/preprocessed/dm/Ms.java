/*     */ package dm;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ import javax.microedition.lcdui.Font;
/*     */ import javax.microedition.lcdui.Image;
/*     */ import javax.microedition.rms.RecordStore;
/*     */ import main.Constants_H;
/*     */ import main.Key_H;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ms
/*     */   implements Constants_H, Key_H
/*     */ {
/*     */   private static Ms msListener;
/*     */   private int sleep_time;
/*     */   private static RecordStore rms;
/*     */   final int RMSSIZE;
/*     */   public static int skip;
/*     */   public static int skip2;
/*     */   private final byte[] transA;
/*     */   
/*     */   public Ms() {
/*  39 */     this.RMSSIZE = 15360;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 427 */     this.transA = new byte[] { 0, 6, 3, 5, 2, 7, 1, 4 }; msListener = this;
/*     */   } public static Ms i() { if (null == msListener) msListener = new Ms();  return msListener; } public void sleep(int time) { this.sleep_time = time; } public int getSleep() { return this.sleep_time; } public byte[] rmsOptions(int recordId, byte[] info, int flag) { try { if (rms == null) rms = RecordStore.openRecordStore("pk5_caihong", true);  if (rms.getNumRecords() == 0) setRmsInit(true);  if (flag != 0) { if (flag == 1) { info = rms.getRecord(recordId); return info; }  if (flag == 2) { rms.setRecord(recordId, info, 0, info.length); } else if (flag == 3) { setRmsInit(false); } else if (flag == 4) { if (rms != null) { rms.closeRecordStore(); rms = null; }  } else if (flag == 5) { if (rms.getSizeAvailable() > 15360) return null;  return new byte[] { 1 }; }  }  } catch (Exception e) { e.printStackTrace(); }  return null; } public void setRmsInit(boolean mode) throws Exception { byte[] info = new byte[140]; info[0] = -1; byte[] aaa = new byte[280]; int i = 0; for (; i < 82; i++) { if (mode || i != 4) if (i != 11) { if (mode) { rms.addRecord(info, 0, info.length); } else { rms.setRecord(i + 1, info, 0, info.length); }  } else if (mode) { rms.addRecord(aaa, 0, aaa.length); } else { rms.setRecord(i + 1, aaa, 0, aaa.length); }   }  info = null; } public byte[] getEventNowData(short[][] event_now) { ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); byteArray.write(event_now.length); for (int i = 0; i < event_now.length; i++) { if (event_now[i] == null) { byteArray.write(0); } else { byteArray.write((event_now[i]).length); for (int j = 0; j < (event_now[i]).length; j++) { byteArray.write(event_now[i][j] & 0xFF); byteArray.write(event_now[i][j] >> 8 & 0xFF); }  }  }  return byteArray.toByteArray(); } public short[][] readEventNowData() { short[][] event_now = (short[][])null; ByteArrayInputStream byteArray = new ByteArrayInputStream(rmsOptions(11, null, 1)); event_now = new short[byteArray.read()][]; for (int i = 0; i < event_now.length; i++) { int nn = byteArray.read(); if (nn != 0) { event_now[i] = new short[nn]; for (int j = 0; j < (event_now[i]).length; j++) event_now[i][j] = (short)(byteArray.read() | byteArray.read() << 8);  }  }  return event_now; } public static long getNum(byte[] b) { int rtn = 0, len = b.length; for (int i = 0; i < len; i++) { switch (len) { case 1: rtn += (byte)((b[i] & 0xFF) << i * 8); break;case 2: rtn += (short)((b[i] & 0xFF) << i * 8); break;case 4: rtn += (b[i] & 0xFF) << i * 8; break;case 8: rtn = (int)(rtn + ((b[i] & 0xFF) << i * 8)); break; }  }  return rtn; } public int getLen_byte(byte value) { return (value < 0) ? (value + 256) : value; } public int getLen_short(short value) { return (value < 0) ? (value + 65536) : value; } public int getInt(byte[] buf, int i) { return (buf[i] & 0xFF) << 24 | (buf[i + 1] & 0xFF) << 16 | (buf[i + 2] & 0xFF) << 8 | buf[i + 3] & 0xFF; } public void putInt(int value, byte[] buf, int i) { buf[i] = (byte)(value >> 24 & 0xFF); buf[i + 1] = (byte)(value >> 16 & 0xFF); buf[i + 2] = (byte)(value >> 8 & 0xFF); buf[i + 3] = (byte)(value & 0xFF); } public short getShort(byte[] buf, int i) { return (short)((buf[i] & 0xFF) << 8 | buf[i + 1] & 0xFF); } public void putShort(int value, byte[] buf, int i) { buf[i] = (byte)(value >> 8 & 0xFF); buf[i + 1] = (byte)(value & 0xFF); } public void putShort(byte[] buf, int value) { buf[skip++] = (byte)(value >> 8 & 0xFF); buf[skip++] = (byte)(value >> 0 & 0xFF); } public short[] byteArrayToShortArray(byte[] bytebuf) { skip = 0; int len = bytebuf.length >> 1; short[] shortbuf = new short[len]; for (int i = 0; i < len; i++) shortbuf[i] = getStreamL(bytebuf, 2);  return shortbuf; }
/* 429 */   Image createImage(Image image, int x, int y, int width, int height, int trans) { if (x + width > image.getWidth()) width = image.getWidth() - x; 
/* 430 */     if (y + height > image.getHeight()) height = image.getHeight() - y; 
/* 431 */     return Image.createImage(image, x, y, width, height, this.transA[trans]); }
/*     */   public byte[] shortArrayToByteArray(short[] shortbuf) { skip = 0; int len = shortbuf.length; byte[] bytebuf = new byte[len << 1]; for (int i = 0; i < len; i++) putShort(bytebuf, shortbuf[i]);  return bytebuf; }
/*     */   private short getStreamL(byte[] data, int mode) { if (mode == 0) return (short)data[skip++];  if (mode == 1) return (short)(data[skip++] + 100);  if (mode == 2) return (short)((data[skip++] & 0xFF) << 8 | data[skip++] & 0xFF);  return (short)(data[skip++] & 0xFF | (data[skip++] & 0xFF) << 8); }
/*     */   public byte[] getStream(String i, int num) { byte[] data = null; try { DataInputStream dataInput = new DataInputStream(getClass().getResourceAsStream("/" + i)); if (num > -1) { dataInput.readByte(); for (byte n = 0; n < num; n = (byte)(n + 1)) dataInput.skip(getLen_short(dataInput.readShort()));  }  data = new byte[getLen_short(dataInput.readShort())]; dataInput.read(data); dataInput.close(); dataInput = null; } catch (Exception ex) { ex.printStackTrace(); }  return data; }
/*     */   public int[] createIntArray(byte[] data) { int[] array = new int[getStreamL(data, 0)]; for (int i = 0; i < array.length; i++) array[i] = data[skip++] & 0xFF | (data[skip++] & 0xFF) << 8 | (data[skip++] & 0xFF) << 16 | (data[skip++] & 0xFF) << 24;  return array; }
/*     */   public short[] createShortArray(byte[] data, int mode) { short[] array = new short[getStreamL(data, mode)]; for (int j = 0; j < array.length; j++) array[j] = getStreamL(data, (mode == 2) ? 2 : -1);  return array; }
/*     */   public short[][] createShort2Array(byte[] data, int mode) { short[][] array = new short[getStreamL(data, mode)][]; for (int i = 0; i < array.length; i++) array[i] = createShortArray(data, mode);  return array; }
/*     */   public short[][][] createShort3Array(byte[] data, int mode) { short[][][] array = new short[getStreamL(data, mode)][][]; for (int i = 0; i < array.length; i++)
/*     */       array[i] = createShort2Array(data, mode);  return array; }
/*     */   public byte[] createArray(byte[] data) { byte[] array = new byte[getLen_byte(data[skip++])]; for (int j = 0; j < array.length; j++)
/* 441 */       array[j] = data[skip++];  return array; } Image createCellImage(Image image, int cell_index, int cell_width, int cell_height, int trans) { int temp_cell_x = cell_index % image.getWidth() / cell_width * cell_width;
/* 442 */     int temp_cell_y = cell_index % image.getHeight() / cell_height * cell_height;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 452 */     return createImage(image, temp_cell_x, temp_cell_y, cell_width, cell_height, trans); }
/*     */   public byte[][] create2Array(byte[] data) { byte[][] array = new byte[getLen_byte(data[skip++])][]; for (int i = 0; i < array.length; i++) array[i] = createArray(data);  return array; }
/*     */   public byte[][][] create3Array(byte[] data) { byte[][][] array = new byte[getLen_byte(data[skip++])][][]; for (int i = 0; i < array.length; i++) array[i] = create2Array(data);  return array; }
/* 455 */   public byte[][][][] create4Array(byte[] data) { byte[][][][] array = new byte[getLen_byte(data[skip++])][][][]; for (int i = 0; i < array.length; i++) array[i] = create3Array(data);  return array; } public StringBuffer[] createStringArray(byte[] data) { StringBuffer[] string = new StringBuffer[getLen_byte(data[skip++])]; for (byte i = 0; i < string.length; i = (byte)(i + 1)) { short l_c = (short)data[skip]; if (l_c < 0) l_c = (short)(l_c + 256);  string[i] = new StringBuffer(getDialogs(data, skip + 1, l_c)); skip += 1 + l_c * 2; }  return string; } public StringBuffer createStringArrayOne(byte[] data) { return new StringBuffer(getDialogs(data, 2, getLen_byte(data[1]))); } public StringBuffer[][] createString2Array(byte[] dataa) { byte len = dataa[skip++]; StringBuffer[][] string = new StringBuffer[len][]; for (byte i = 0; i < string.length; i = (byte)(i + 1)) string[i] = createStringArray(dataa);  return string; } public String getDialogs(byte[] data, int start, int len) { StringBuffer dialog = new StringBuffer(); for (int i = 0; i < len; i++) dialog.append((char)(data[start + (i << 1)] << 8 | 0xFF & data[start + (i << 1) + 1]));  return dialog.toString(); } public StringBuffer[] groupString(String info, int width) { StringBuffer[] tempResult = new StringBuffer[30]; StringBuffer temp = new StringBuffer(); StringBuffer orig = new StringBuffer(info); short infoLength = (short)info.length(); byte tc = 0, tw = (byte)getStringWidth("#0"), rows = -1; boolean isNewRow = false; String tcolor = ""; for (int i = 0; i < infoLength; i++) { if (orig.charAt(0) == '#') { if (orig.charAt(1) == 'n') { isNewRow = true; } else { tcolor = "#" + orig.charAt(1); temp.append(tcolor); tc = (byte)(tc + 1); }  orig.deleteCharAt(0); orig.deleteCharAt(0); i++; } else { temp.append(orig.charAt(0)); if (width != 0 && getStringWidth(temp.toString()) <= width + tw * tc) { orig.deleteCharAt(0); } else if (width != 0) { i--; temp.deleteCharAt(temp.length() - 1); isNewRow = true; } else { orig.deleteCharAt(0); }  if (i == infoLength - 1 && !isNewRow) isNewRow = true;  }  if (isNewRow) { rows = (byte)(rows + 1); tc = (byte)((tcolor.length() == 0) ? 0 : 1); isNewRow = false; tempResult[rows] = temp; temp = null; temp = new StringBuffer(); temp.append(tcolor); }  }  StringBuffer[] result = new StringBuffer[rows + 1]; System.arraycopy(tempResult, 0, result, 0, rows + 1); tempResult = null; temp = orig = null; tcolor = null; return result; } public String[] loadText(byte[] word_uni) { String[] reStr; try { StringBuffer stringbuffer = new StringBuffer(""); int j; for (j = 2; j < word_uni.length; ) { int i = word_uni[j++]; if (i < 0) i += 256;  int m = word_uni[j++]; if (m < 0) m += 256;  char c = (char)(i + (m << 8)); stringbuffer.append(c); }  String strReturn = stringbuffer.toString(); stringbuffer = null; Vector vecString = new Vector(); int k = 0; int l = 0; for (j = 0; j < strReturn.length(); j++) { if (strReturn.charAt(j) == '\n' || j == strReturn.length()) { String temp = strReturn.substring(k, l); vecString.addElement(temp); k = l + 1; }  l++; }  strReturn = null; reStr = new String[vecString.size()]; for (j = 0; j < vecString.size(); j++) { String s = vecString.elementAt(j); reStr[j] = s; }  vecString = null; } catch (Exception e) { e.printStackTrace(); return null; }  return reStr; } public Image[] createImageArray(int len, String name) { Image[] img = new Image[len]; short i;
/* 456 */     for (i = 0; i < img.length; i = (short)(i + 1))
/* 457 */       img[i] = createImage(name + i); 
/* 458 */     return img; }
/*     */ 
/*     */   
/*     */   public Image createImage(String name, int no) {
/* 462 */     byte[] data = getStream(name, no);
/* 463 */     return Image.createImage(data, 0, data.length);
/*     */   }
/*     */   
/*     */   public Image createImage(String imageName) {
/*     */     try {
/* 468 */       return Image.createImage("/" + imageName + ".png");
/* 469 */     } catch (Exception ex) {
/*     */       
/* 471 */       return null;
/*     */     } 
/*     */   }
/*     */   public Sprite createSprite(String name, boolean mode) {
/* 475 */     byte[] date = getStream(name + ".data", -1);
/*     */     
/* 477 */     skip = 0;
/* 478 */     return mode ? Sprite.Create(createImage(name), create2Array(date), create3Array(date), create3Array(date)) : Sprite.Create(createImage(name), createShort2Array(date, 2), createShort3Array(date, 2), createShort3Array(date, 2));
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
/*     */   public void setSprite(Sprite sp, String name, boolean mode) {
/* 490 */     byte[] date = getStream(name + ".data", -1);
/* 491 */     skip = 0;
/* 492 */     sp.nullIMFA();
/* 493 */     if (mode) { sp.Set(createImage(name), create2Array(date), create3Array(date), create3Array(date));
/*     */        }
/*     */     
/*     */     else
/*     */     
/* 498 */     { sp.Set(createImage(name), createShort2Array(date, 2), createShort3Array(date, 2), createShort3Array(date, 2)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 503 */     date = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(StringBuffer sbuff, String str) {
/* 508 */     return sbuff.toString().equals(str);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRect(int a0, int a1, int aw, int ah, int b0, int b1, int bw, int bh) {
/* 513 */     return (a0 < b0 + bw && a0 + aw > b0 && a1 < b1 + bh && a1 + ah > b1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrecision(int t) {
/* 524 */     return (t / 10) + "." + (t % 10);
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
/*     */   public int sqrt(int x) {
/* 538 */     if (x > 0) {
/* 539 */       x *= 10000;
/* 540 */       int b = 10000;
/*     */       while (true)
/* 542 */       { int y = b;
/* 543 */         b = x / b + b >> 1;
/* 544 */         if (b >= y)
/* 545 */         { y /= 100;
/* 546 */           return y; }  } 
/* 547 */     }  return 0;
/*     */   }
/* 549 */   private static Random random = new Random();
/*     */   public static int getRandom(int ss) {
/* 551 */     return (random.nextInt() & Integer.MAX_VALUE) % ss;
/*     */   } public static int abs(int a) {
/* 553 */     return (a > 0) ? a : -a;
/*     */   } public static int compare_min(int c0, int c1) {
/* 555 */     return (c0 <= c1) ? c0 : c1;
/*     */   }
/*     */   public short mathPercent(int m0, int m1, int per) {
/* 558 */     if (per < 1) per = 1; 
/* 559 */     return (short)(m0 * m1 / per);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 564 */   public static Font font = Font.getFont(0, 0, 8); public static boolean keyRepeat;
/*     */   public static int key;
/*     */   
/* 567 */   public int getStringWidth(String str) { return font.stringWidth(str); } public byte getMin(byte i0, byte i1) {
/* 568 */     return (i0 > i1) ? i1 : i0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short mathSpeedDown(int volue, int num, boolean bb) {
/* 577 */     if (volue / num != 0) { volue -= volue / num; }
/* 578 */     else if (bb && volue > 0) { volue--; }
/* 579 */     else if (bb && volue < 0) { volue++; }
/* 580 */     else { volue = 0; }
/* 581 */      return (short)volue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short mathSpeedUp(int volue, int max, int speed) {
/* 592 */     volue -= (max - volue) / speed;
/* 593 */     return (short)((volue < 0) ? 0 : volue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short mathSpeedN(int volue, int maxv, int speed, boolean bb) {
/* 602 */     if (volue > maxv && volue - speed > maxv) { volue -= speed; }
/* 603 */     else if (volue < maxv && volue + speed < maxv) { volue += speed; }
/* 604 */     else if (bb && volue > maxv) { volue--; }
/* 605 */     else if (bb && volue < maxv) { volue++; }
/* 606 */     else { volue = maxv; }
/* 607 */      return (short)volue;
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
/*     */   public byte select(int select, int min, int max) {
/* 619 */     if (max == 0) return (byte)select; 
/* 620 */     if (abs(key) % 2 == 1 && --select < min) {
/* 621 */       select = max;
/* 622 */     } else if (abs(key) % 2 == 0 && ++select > max) {
/* 623 */       select = min;
/* 624 */     }  return (byte)select;
/*     */   }
/*     */   public void selectS(byte[] select, int min, int max, int showLine) {
/* 627 */     if (max == 0)
/* 628 */       return;  select[0] = select(select[0], min, max - 1);
/* 629 */     if (select[1] - 1 == select[0]) {
/* 630 */       select[1] = (byte)(select[1] - 1);
/* 631 */     } else if (select[1] + showLine == select[0]) {
/* 632 */       select[1] = (byte)(select[1] + 1);
/* 633 */     } else if (select[0] == max - 1) {
/* 634 */       select[1] = (byte)((max - min < showLine) ? min : (max - showLine));
/* 635 */     } else if (select[0] == min) {
/* 636 */       select[1] = (byte)min;
/*     */     } 
/*     */   } public void correctSelect(byte[] select, int max, int showLine) {
/* 639 */     if (select[0] < max) {
/* 640 */       select[1] = (byte)(select[0] - showLine + 1);
/*     */     } else {
/* 642 */       select[0] = (byte)(max - 1);
/* 643 */       select[1] = (byte)(max - showLine);
/*     */     } 
/* 645 */     if (select[0] < 0) select[0] = 0; 
/* 646 */     if (select[1] < 0) select[1] = 0;
/*     */   
/*     */   }
/*     */   
/*     */   private boolean checkIsSimulate() {
/* 651 */     if (Runtime.getRuntime().totalMemory() >= 8000000L) return true; 
/*     */     try {
/* 653 */       Class.forName("emulator.Emulator");
/* 654 */       Class.forName("com.sprintpcs.util.System");
/* 655 */       return true;
/* 656 */     } catch (Exception e) {
/* 657 */       String platForm = System.getProperty("microedition.platform");
/* 658 */       return (platForm.toLowerCase().indexOf("wtk") != -1 || platForm.toLowerCase().indexOf("javasdk") != -1 || platForm.toLowerCase().indexOf("j2me") != -1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public static byte key_delay = 0, key_time = 10; public void runDelay() {
/* 667 */     if (key_delay > 0) key_delay = (byte)(key_delay - 1); 
/*     */   } public boolean key_delay() {
/* 669 */     if (key_delay == 0) {
/* 670 */       key_delay = key_time;
/* 671 */       if (key_time > 1) key_time = (byte)(key_time - 1); 
/* 672 */       return false;
/*     */     } 
/* 674 */     return true;
/*     */   } public void keyRelease() {
/* 676 */     keyRepeat = false; key_delay = 0; key_time = 10;
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
/*     */   public boolean key_Up_Down() {
/* 696 */     return (key == -1 || key == -2);
/* 697 */   } public boolean key_Up() { return (key == -1); }
/* 698 */   public boolean key_Down() { return (key == -2); }
/* 699 */   public boolean key_Left_Right() { return (key == -3 || key == -4); }
/* 700 */   public boolean key_Left() { return (key == -3); }
/* 701 */   public boolean key_Right() { return (key == -4); }
/* 702 */   public boolean key_S1_Num5() { return (key == -6 || key == 53 || key == -5); }
/* 703 */   public boolean key_S1() { return (key == -6); }
/* 704 */   public boolean key_S2() { return (key == -7); }
/* 705 */   public boolean key_Num0() { return (key == 48); }
/* 706 */   public boolean key_Num1() { return (key == 49); }
/* 707 */   public boolean key_Num3() { return (key == 51); } public boolean key_Num9() {
/* 708 */     return (key == 57);
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Ms.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */