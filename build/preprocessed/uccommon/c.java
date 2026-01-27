package uccommon;

import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class c {
  private static final byte[] f = new byte[] { 
      -119, 80, 78, 71, 13, 10, 26, 10, 13, 73, 
      72, 68, 82, 87, 20, 8, 3, -49, -38, 81, 
      -100, 3, 115, 66, 73, 84, 8, 8, 8, -37, 
      -31, 79, -32, -73, 80, 76, 84, 69, -26, 51, 
      -13, -104, 108, -5, -35, -50, -18, 116, 57, -9, 
      -69, -98, -22, 88, 17, -1, -4, -6, -16, -120, 
      85, -8, -52, -74, -20, 99, 33, -3, -21, -30, 
      -11, -86, -123, -23, 79, 4, -17, Byte.MAX_VALUE, 73, -15, 
      -109, 101, -19, 105, 41, -7, -46, -66, -24, 73, 
      -10, -76, -107, -13, -96, 121, -2, -14, -19, -8, 
      -59, -83, -21, 92, 24, -4, -28, -40, -22, 85, 
      14, -19, 109, 48, -17, 122, 66, -2, -8, -10, 
      -24, 66, -6, -38, -55, -15, -113, 96, -14, -108, 
      106, -1, -1, -1, -9, -66, -93, -21, 96, 26, 
      -11, -88, -125, -16, -123, 82, -11, -82, -116, -4, 
      -31, -43, -2, -10, -15, -4, -25, -35, -18, 113, 
      52, -21, 88, 17, -9, -73, -103, -7, -49, -69, 
      -13, -100, 115, -15, -116, 90, -23, 75, 9, -6, 
      -42, -59, -22, 82, 8, -8, -62, -89, -14, -103, 
      109, -26, 58, -12, -92, 125, -7, -55, -78, -3, 
      -18, -26, -17, -127, 75, -21, 85, 14, -9, -75, 
      -116, -6, -35, -49, -9, -100, 107, 112, 22, -27, 
      -66, 9, 112, 72, 89, 115, 11, 18, 11, 18, 
      1, -46, -35, 126, -4, 28, 116, 69, 88, 116, 
      83, 111, 102, 116, 119, 97, 114, 101, 65, 100, 
      111, 98, 101, 32, 70, 105, 114, 101, 119, 111, 
      114, 107, 115, 32, 67, 83, 52, 6, -78, -45, 
      -96, 22, 116, 69, 88, 116, 67, 114, 101, 97, 
      116, 105, 111, 110, 32, 84, 105, 109, 101, 48, 
      52, 47, 50, 50, 47, 49, 48, 35, 64, 105, 
      -39, 3, 113, 73, 68, 65, 84, 56, -115, -67, 
      -108, 93, -109, -94, 58, 16, -122, 81, 16, -28, 
      99, -59, 86, -116, 1, 33, -45, 34, 31, -126, 
      -110, 49, -96, -61, 108, -51, -1, -1, 95, -89, 
      -125, -77, -77, 123, 113, -26, 106, -85, -74, 47, 
      -70, 40, 18, 30, 58, 111, -65, 105, -125, -3, 
      79, 84, -8, -41, 97, -4, 115, -18, -75, -72, 
      80, 62, 57, -65, 126, -78, 44, -106, -120, -75, 
      47, 116, 28, -15, -26, 76, 15, -94, -90, 21, 
      79, 12, 54, -30, 94, 110, -89, 125, -3, -12, 
      -35, -9, -36, -63, -96, 125, -75, 27, 124, 16, 
      -14, 37, -117, -50, -37, -76, 59, -41, 62, 4, 
      83, 84, -3, 61, 8, -108, 2, -13, -16, 40, 
      -10, -25, 32, 39, 110, 6, -31, -60, -35, -34, 
      -59, -109, -53, 57, -95, -38, -74, -91, -52, 21, 
      69, 59, 113, -19, 38, -56, 16, 35, 30, -21, 
      -94, -17, -3, 10, 84, -80, 88, -120, 21, 88, 
      -98, -73, 79, -96, 60, -20, -9, 14, 12, -5, 
      3, -106, -9, -63, 107, 67, -84, 101, -46, -70, 
      -123, 53, 43, -118, 102, 17, 75, 41, -115, -42, 
      -19, 79, -100, 113, -71, 75, 56, 83, -119, -65, 
      -20, 103, -18, -60, -35, 65, -110, 89, -76, 20, 
      -10, 125, 47, -31, 120, 20, 67, 87, -64, -48, 
      -63, -19, -10, 114, -13, -63, -89, 29, -101, 123, 
      73, 57, 114, -35, -73, 60, -60, 81, 41, -58, 
      -96, 11, 3, -59, 89, 75, -59, 25, 42, -58, 
      121, -64, -108, -113, 82, 81, -62, 121, -123, 66, 
      17, -9, 28, -62, 117, 107, -92, 105, -50, 83, 
      29, -67, 62, -95, 19, -52, -119, 123, -70, -5, 
      62, 108, -86, -117, -27, -68, 58, -106, 85, -37, 
      2, -92, 27, -38, -74, 103, 5, -115, 87, -113, 
      -29, 40, 64, 80, 54, 120, 108, -33, -120, 123, 
      66, 9, 91, -84, 98, -50, -101, 60, -81, -48, 
      54, -63, -63, -84, -68, -68, -65, -54, -53, -27, 
      -78, -23, 73, 63, -20, -46, 1, 87, 112, 43, 
      -95, -12, -31, 82, -34, -45, 32, 87, -87, -70, 
      -60, -82, 50, -13, -112, -42, 63, -108, 57, -23, 
      123, -126, 23, -83, -17, 23, -41, -31, 30, 38, 
      48, -55, 93, 97, -60, 22, -90, 86, 67, -92, 
      87, 20, -85, 105, -1, 38, 8, 28, 111, -8, 
      -28, -106, -34, 114, -105, -68, -118, -11, 122, -87, 
      -36, -21, -107, 105, 110, -58, -61, -18, 109, -37, 
      36, 33, 119, -109, 68, -2, -26, -66, -57, -24, 
      -27, -117, 79, 63, 28, -106, -90, -70, 81, -13, 
      76, 53, 63, 48, 87, -101, -55, 82, -15, 12, 
      -114, -37, 95, -11, 102, 9, -106, -87, 86, 57, 
      121, -83, 34, 30, -38, -47, -61, 100, -117, 116, 
      35, 120, -18, 82, -76, 90, 95, 59, 83, 76, 
      61, 80, -124, -44, -125, -59, -105, -49, 26, -82, 
      -19, -112, -25, 81, -19, -122, -60, -35, Byte.MIN_VALUE, 114, 
      118, -80, -23, 62, -71, -42, 91, 90, -107, 105, 
      89, 62, -48, 74, -59, -103, -4, -112, 1, 107, 
      -61, -11, -127, 62, -119, 16, 43, 119, -30, 122, 
      109, 11, 22, -118, -96, 34, -7, -38, -10, -23, 
      51, 123, -30, 126, 80, 87, -93, -119, -5, -125, 
      -79, 102, 7, -89, 14, -42, 4, 45, 83, 107, 
      9, -5, 50, 61, 21, 63, -88, -32, 102, 73, 
      126, -120, 110, 87, -83, 111, -44, -123, -31, 48, 
      14, -63, 15, -93, 109, -25, 88, 50, 97, 31, 
      -30, Byte.MIN_VALUE, -4, 60, -72, -82, 116, Byte.MAX_VALUE, 115, 119, 
      -44, -67, 39, -9, 109, -99, 55, -41, 96, 69, 
      58, -56, 96, 51, -125, 39, 119, 107, 22, -13, 
      -115, -65, -19, 24, -69, 28, 48, 83, 13, 98, 
      -111, -26, 57, -124, 109, -20, 25, -116, 55, 115, 
      58, -10, 40, 73, -116, -43, 56, -39, -119, Byte.MAX_VALUE, 
      113, -53, -76, -5, -28, -94, -57, -30, -84, 89, 
      -115, -43, -102, 26, -37, -36, -22, 94, 115, 31, 
      -119, 56, 25, -64, 126, -126, 55, 113, 51, -14, 
      -102, -105, 40, -43, 79, -9, -115, 57, -99, 116, 
      -55, -42, 76, -71, -78, -21, -110, 63, 116, -104, 
      -91, 23, -51, -43, 46, 59, -77, 48, -46, -1, 
      55, -63, 108, 19, -60, -93, -26, -106, -85, -43, 
      -34, -22, -5, 25, 119, -5, 3, 105, -42, 96, 
      15, 3, -30, 35, -32, -73, -25, 124, -104, 46, 
      47, -5, 124, -102, 124, -90, -71, -28, -121, 119, 
      88, -30, -24, -78, -11, 88, 107, -18, -120, -74, 
      -97, -125, 92, 47, -62, 121, 109, -23, -66, -7, 
      103, 125, 84, 60, 115, -102, 12, -47, -111, -72, 
      123, -98, -9, -69, -112, -85, 36, -6, 110, -18, 
      -40, 33, -75, -79, -50, -23, -22, 69, 46, -51, 
      -102, 7, -98, 85, 30, -95, -89, 64, 28, -50, 
      63, 1, -100, 35, 27, 79, -58, 115, Byte.MIN_VALUE, -31, 
      71, -112, 83, 99, 1, 72, -33, -110, 84, -127, 
      71, 9, -26, -73, -13, -84, 123, -40, 24, -119, 
      25, 73, -37, 75, 81, 28, 113, 20, 27, 42, 
      106, -69, -44, 55, 68, -54, 7, -39, 105, -9, 
      -34, 63, -71, 52, 39, -23, -12, 82, 94, -11, 
      52, 93, -83, -24, -19, -32, -4, -5, -71, -2, 
      119, -15, 31, -80, 115, -63, -80, -66, -47, -45, 
      67, 73, 69, 78, 68, -82, 66, 96, -126 };
  
  public static final Font a = Font.getFont(0, 0, 8);
  
  public static final Font b = Font.getFont(0, 1, 8);
  
  private static String g = "0";
  
  public static String c = "1";
  
  private static String h = "2";
  
  public static String d = "3";
  
  public static String e = "4";
  
  private static String i = "5";
  
  public static Image a() {
    Image image;
    return image = Image.createImage(f, 0, f.length);
  }
  
  public static Hashtable a(Vector paramVector, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2, String paramString3) {
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("c", paramVector);
    hashtable.put("r", new Integer(paramInt1));
    hashtable.put("q", new Integer(paramInt2));
    hashtable.put("k", new Integer(paramInt3));
    hashtable.put("l", new Integer(-1));
    hashtable.put("b", paramString1);
    hashtable.put("e", paramString2);
    hashtable.put("f", paramString3);
    return hashtable;
  }
  
  public static Hashtable a(String paramString1, Image paramImage, int paramInt1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2) {
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("a", "2");
    hashtable.put("b", paramString1);
    hashtable.put("c", paramImage);
    hashtable.put("d", new Integer(0));
    hashtable.put("e", paramString2);
    hashtable.put("f", paramString3);
    hashtable.put("h", paramString4);
    hashtable.put("s", paramString5);
    hashtable.put("l", new Integer(-1));
    return hashtable;
  }
  
  public static Hashtable a(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, String paramString5, String paramString6, Font paramFont, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString7) {
    Hashtable hashtable;
    (hashtable = a(paramString1, paramString2, 5, paramString3, paramString4, paramString5, paramString6, paramFont, paramInt2, paramInt3, paramInt4, e, 0)).put("a", "3");
    hashtable.put("r", new Integer(paramInt5));
    hashtable.put("q", new Integer(paramInt6));
    hashtable.put("n", new Integer(60));
    hashtable.put("u", paramString7);
    return hashtable;
  }
  
  public static Hashtable a(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, String paramString5, String paramString6, Font paramFont, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    Hashtable hashtable;
    (hashtable = a(paramString1, paramString2, 5, paramString3, paramString4, paramString5, paramString6, paramFont, paramInt2, paramInt3, paramInt4, e, 0)).put("a", "6");
    hashtable.put("r", new Integer(14));
    hashtable.put("q", new Integer(14));
    hashtable.put("z", "n");
    return hashtable;
  }
  
  public static void a(Vector paramVector, int paramInt1, int paramInt2, int paramInt3) {
    byte b = -1;
    int k = paramInt2;
    int j = paramInt1;
    int i = paramInt3;
    b = 0;
    String str = c;
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("a", "5");
    hashtable.put("h", str);
    hashtable.put("d", new Integer(0));
    hashtable.put("r", new Integer(i));
    hashtable.put("q", new Integer(j));
    hashtable.put("k", new Integer(k));
    hashtable.put("l", new Integer(-1));
    Vector vector;
    (vector = new Vector()).addElement(hashtable);
    paramVector.addElement(a(vector, paramInt3, paramInt1, paramInt2, -1, "n", "", ""));
  }
  
  public static Hashtable a(String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, String paramString5, String paramString6, Font paramFont, int paramInt2, int paramInt3, int paramInt4, String paramString7, int paramInt5) {
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("a", "1");
    hashtable.put("b", paramString1);
    hashtable.put("c", paramString2);
    hashtable.put("d", new Integer(paramInt1));
    hashtable.put("e", paramString3);
    hashtable.put("f", paramString4);
    hashtable.put("h", paramString5);
    hashtable.put("s", paramString6);
    hashtable.put("i", paramFont);
    hashtable.put("j", new Integer(paramInt2));
    hashtable.put("k", new Integer(paramInt3));
    hashtable.put("l", new Integer(paramInt4));
    hashtable.put("g", paramString7);
    hashtable.put("r", String.valueOf(paramInt5));
    return hashtable;
  }
  
  public static Hashtable a(String paramString, int paramInt1, int paramInt2, int paramInt3) {
    Hashtable hashtable = new Hashtable();
    int i = paramInt1 / 4 * 3;
    int j;
    if ((j = paramInt3 * 6) > paramInt2 - paramInt3 * 3)
      j = paramInt2 - paramInt3 * 3; 
    hashtable.put("a", "b");
    hashtable.put("c", new Vector());
    hashtable.put("v", "n");
    hashtable.put("x", paramString);
    hashtable.put("w", "0");
    hashtable.put("r", String.valueOf(i));
    hashtable.put("q", String.valueOf(j));
    hashtable.put("d", String.valueOf((paramInt1 - i) / 2));
    hashtable.put("m", String.valueOf((paramInt2 - j) / 2 - paramInt3 / 2));
    return hashtable;
  }
  
  public static void a(Hashtable paramHashtable, String paramString) {
    if (paramHashtable != null) {
      Vector vector;
      (vector = new Vector()).addElement(paramString);
      paramHashtable.put("c", vector);
    } 
  }
  
  public static Hashtable a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("d", new Integer(paramInt1));
    hashtable.put("m", new Integer(paramInt2));
    hashtable.put("r", new Integer(paramInt3));
    hashtable.put("q", new Integer(paramInt4));
    return hashtable;
  }
  
  public static void a(Hashtable paramHashtable, String paramString1, String paramString2, String paramString3) {
    paramHashtable.put("c", paramString1);
    paramHashtable.put("e", paramString2);
    paramHashtable.put("f", paramString3);
  }
  
  private static int a(String paramString, int paramInt1, int paramInt2) {
    return (paramString == g) ? 0 : ((paramString == h) ? (paramInt2 - paramInt1) : ((paramInt2 - paramInt1) / 2));
  }
  
  public static Object[] a(Graphics paramGraphics, Hashtable paramHashtable1, int paramInt1, Hashtable paramHashtable2, int paramInt2, int paramInt3, int[] paramArrayOfint) {
    Font font = (Font)paramHashtable1.get("i");
    int k = Integer.parseInt(paramHashtable1.get("d").toString());
    int m = a(paramHashtable1.get("h").toString(), font.getHeight(), paramInt1);
    int n = Integer.parseInt(paramHashtable1.get("j").toString());
    int i1 = Integer.parseInt(paramHashtable1.get("k").toString());
    int i2 = Integer.parseInt(paramHashtable1.get("r").toString());
    Integer.parseInt(paramHashtable1.get("l").toString());
    String str2 = paramHashtable1.get("g").toString();
    String str3 = paramHashtable1.get("c").toString();
    if (i2 < font.stringWidth(str3))
      i2 = font.stringWidth(str3) + 2; 
    if (paramHashtable2 == paramHashtable1) {
      i1 = paramArrayOfint[9];
      b(paramGraphics, 1000000, k + 1, m + paramInt2 + paramInt3, i2 - 3, font.getHeight() + 3);
    } 
    a(paramGraphics, i1, k + 2, m + 1 + paramInt2 + paramInt3, i2 - 4, font.getHeight() + 2);
    paramGraphics.setColor(n);
    paramGraphics.setFont(font);
    int j = i2;
    int i = font.stringWidth(str3) + 2;
    String str1;
    paramGraphics.drawString(str3, k + (((str1 = str2) == d) ? 0 : ((str1 == i) ? (j - i) : ((j - i) / 2))), m + paramInt2 + paramInt3 + 2, 0);
    return new Object[] { (paramHashtable1.get("b") != "y") ? null : paramHashtable1, String.valueOf(paramInt2 + paramInt3), String.valueOf(paramInt1), String.valueOf(k), String.valueOf(i2) };
  }
  
  private static void a(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != -1) {
      paramGraphics.setColor(paramInt1);
      paramGraphics.fillRect(paramInt2, paramInt3, paramInt4, paramInt5);
    } 
  }
  
  private static void b(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != -1) {
      paramGraphics.setColor(paramInt1);
      paramGraphics.drawRect(paramInt2, paramInt3, paramInt4, paramInt5);
    } 
  }
  
  public static Object[] a(Graphics paramGraphics, Hashtable paramHashtable1, int paramInt1, int paramInt2, Hashtable paramHashtable2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfint) {
    Font font = (Font)paramHashtable1.get("i");
    int i = Integer.parseInt(paramHashtable1.get("d").toString());
    paramInt2 = a(paramHashtable1.get("h").toString(), font.getHeight(), paramInt2);
    int j = Integer.parseInt(paramHashtable1.get("j").toString());
    int k = Integer.parseInt(paramHashtable1.get("k").toString());
    int m = Integer.parseInt(paramHashtable1.get("l").toString());
    int n = Integer.parseInt(paramHashtable1.get("r").toString());
    int i1 = Integer.parseInt(paramHashtable1.get("q").toString());
    String str1 = paramHashtable1.get("c").toString();
    String str2;
    if ((str2 = paramHashtable1.get("u").toString()) == "n" && str1.length() > 0) {
      for (str2 = ""; str2.length() < str1.length(); str2 = String.valueOf(str2) + "*");
      str1 = str2;
    } 
    if (paramHashtable2 == paramHashtable1)
      k = paramArrayOfint[9]; 
    a(paramGraphics, k, i, paramInt2 + paramInt3 + paramInt4, n, i1);
    paramGraphics.setFont(font);
    paramGraphics.setColor(j);
    paramGraphics.setClip(i, paramInt4 + paramInt2 + paramInt3, n, i1);
    paramGraphics.drawString(str1, i + 5, paramInt4 + paramInt2 + paramInt3 + (i1 - font.getHeight()) / 2, 0);
    paramGraphics.setClip(0, 0, paramInt5, paramInt6);
    b(paramGraphics, m, i, paramInt2 + paramInt3 + paramInt4, n, i1);
    return new Object[] { (paramHashtable1.get("b") != "y") ? null : paramHashtable1, String.valueOf(paramInt3 + paramInt4), String.valueOf(paramInt1), String.valueOf(i), String.valueOf(n) };
  }
  
  public static Object[] a(Graphics paramGraphics, Hashtable paramHashtable1, int paramInt1, int paramInt2, Hashtable paramHashtable2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfint) {
    Font font = (Font)paramHashtable1.get("i");
    int i = Integer.parseInt(paramHashtable1.get("d").toString());
    paramInt2 = a(paramHashtable1.get("h").toString(), font.getHeight(), paramInt2);
    int j = Integer.parseInt(paramHashtable1.get("j").toString());
    int k = Integer.parseInt(paramHashtable1.get("k").toString());
    int m = Integer.parseInt(paramHashtable1.get("l").toString());
    int n = Integer.parseInt(paramHashtable1.get("r").toString());
    int i1 = Integer.parseInt(paramHashtable1.get("q").toString());
    String str1 = paramHashtable1.get("z").toString();
    String str2 = "保存密码？";
    if (paramHashtable2 == paramHashtable1) {
      k = paramArrayOfint[9];
      b(paramGraphics, m, i, paramInt2 + paramInt3 + paramInt4, paramInt5 - 9, i1 + 11);
    } 
    a(paramGraphics, k, i + 1, paramInt2 + 1 + paramInt3 + paramInt4, paramInt5 - 10, i1 + 10);
    paramGraphics.setFont(font);
    paramGraphics.setColor(j);
    paramGraphics.drawString(str2, i + 3 + n + 5, paramInt4 + 5 + paramInt2 + paramInt3 + (i1 - font.getHeight()) / 2, 0);
    b(paramGraphics, m, i + 3, paramInt2 + paramInt3 + paramInt4 + 5, n, i1);
    if (str1 == "y") {
      a(paramGraphics, paramArrayOfint[1], i + 6, paramInt2 + paramInt3 + paramInt4 + 5 + 3, n - 5, i1 - 5);
    } else {
      a(paramGraphics, k, i + 6, paramInt2 + paramInt3 + paramInt4 + 5 + 3, n - 5, i1 - 5);
    } 
    return new Object[] { paramHashtable1, String.valueOf(paramInt3 + paramInt4), String.valueOf(paramInt1), String.valueOf(i), String.valueOf(n) };
  }
  
  public static Object[] a(Graphics paramGraphics, Hashtable paramHashtable, int paramInt1, int paramInt2, int paramInt3) {
    Image image = (Image)paramHashtable.get("c");
    int j = Integer.parseInt(paramHashtable.get("d").toString());
    int k = Integer.parseInt(paramHashtable.get("l").toString());
    int i = a(paramHashtable.get("h").toString(), image.getHeight(), paramInt1);
    paramGraphics.drawImage(image, j, i + paramInt2 + paramInt3, 0);
    b(paramGraphics, k, j - 1, i + paramInt2 - 1, image.getWidth() + 2 + paramInt3, image.getHeight() + 2);
    return null;
  }
  
  public static void b(Graphics paramGraphics, Hashtable paramHashtable, int paramInt1, int paramInt2, int paramInt3) {
    int j = Integer.parseInt(paramHashtable.get("r").toString());
    int k = Integer.parseInt(paramHashtable.get("q").toString());
    int m = Integer.parseInt(paramHashtable.get("k").toString());
    int n = Integer.parseInt(paramHashtable.get("l").toString());
    int i1 = Integer.parseInt(paramHashtable.get("d").toString());
    int i = a(paramHashtable.get("h").toString(), k, paramInt1);
    a(paramGraphics, m, i1, i + paramInt2 + paramInt3, j, k);
    b(paramGraphics, n, i1, i + paramInt2 + paramInt3, j, k);
  }
  
  public static void a(Graphics paramGraphics, Hashtable paramHashtable, int paramInt1, int paramInt2) {
    int j = Integer.parseInt(paramHashtable.get("r").toString());
    int k = Integer.parseInt(paramHashtable.get("k").toString());
    int m = Integer.parseInt(paramHashtable.get("l").toString());
    int i = Integer.parseInt(paramHashtable.get("q").toString());
    a(paramGraphics, k, 0, paramInt1 + paramInt2, j, i);
    b(paramGraphics, m, 0, paramInt1 + paramInt2, j, i);
  }
  
  public static void a(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint, String paramString1, String paramString2) {
    paramGraphics.setColor(paramArrayOfint[7]);
    paramGraphics.fillRect(0, paramInt2 - paramInt3, paramInt1, paramInt3);
    paramGraphics.setFont(b);
    paramGraphics.setColor(paramArrayOfint[0]);
    if (paramString1 != null)
      paramGraphics.drawString(paramString1, 5, paramInt2 - paramInt3 + (paramInt3 - a.getHeight()) / 2, 0); 
    if (paramString2 != null)
      paramGraphics.drawString(paramString2, paramInt1 - 5 - b.stringWidth(paramString2), paramInt2 - paramInt3 + (paramInt3 - a.getHeight()) / 2, 0); 
  }
  
  public static void a(Graphics paramGraphics, Hashtable paramHashtable, int paramInt, int[] paramArrayOfint, Vector paramVector) {
    int i = Integer.parseInt(paramHashtable.get("r").toString());
    int j = Integer.parseInt(paramHashtable.get("q").toString());
    int k = Integer.parseInt(paramHashtable.get("d").toString());
    int m = Integer.parseInt(paramHashtable.get("m").toString());
    int n = Integer.parseInt(paramHashtable.get("w").toString());
    String str1 = paramHashtable.get("v").toString();
    String str2 = paramHashtable.get("x").toString();
    paramGraphics.setColor(paramArrayOfint[7]);
    paramGraphics.fillRect(k, m, i, j);
    paramGraphics.setColor(paramArrayOfint[6]);
    paramGraphics.drawLine(k + 1, m - 1, k + 1 + i, m - 1);
    paramGraphics.drawLine(k + 2, m - 2, k + 2 + i, m - 2);
    paramGraphics.drawLine(k + 3, m - 3, k + 2 + i, m - 3);
    paramGraphics.drawLine(k + i, m - 1, k + i, m + j - 1);
    paramGraphics.drawLine(k + i + 1, m - 2, k + i + 1, m + j - 2);
    paramGraphics.drawLine(k + i + 2, m - 3, k + i + 2, m + j - 3);
    paramGraphics.setColor(paramArrayOfint[0]);
    paramGraphics.fillRect(k + 1, m + 1, i - 2, j - 2);
    paramGraphics.setColor(paramArrayOfint[1]);
    int i1 = 0;
    paramGraphics.setClip(k + 2, m + 2, i - 4, j - 4);
    int i2;
    for (i2 = 0; i2 < paramVector.size(); i2++) {
      paramGraphics.drawString(paramVector.elementAt(i2).toString(), k + 3, n + i1 + m + 3, 0);
      i1 += paramInt;
    } 
    if (str1 == "y") {
      if (i1 / paramInt < 4)
        i1 += paramInt; 
      paramGraphics.drawString("按“确定”键返回。", k + 3, n + i1 + m + 3, 0);
    } 
    paramGraphics.setClip(k, m, i, j + 15);
    if (str1 == "n") {
      paramGraphics.setColor(paramArrayOfint[6]);
      paramGraphics.fillRect(k, m + j - 15, i, 15);
      paramGraphics.clipRect(k + 1, m + j - 15 + 1, i - 2, 13);
      paramGraphics.setColor(paramArrayOfint[8]);
      paramGraphics.fillRect(k + 1, m + j - 15 + 1, i - 2, 13);
      paramGraphics.setColor(paramArrayOfint[6]);
      i2 = (int)(System.currentTimeMillis() % ((i - 4) * 10)) / 10;
      paramGraphics.fillRect(i2 + k + 1, m + j - 15 + 2, 5, 11);
      paramGraphics.fillRect(i2 + k + 8, m + j - 15 + 2, 5, 11);
      paramGraphics.fillRect(i2 + k + 16, m + j - 15 + 2, 5, 11);
      return;
    } 
    if (str2 == "y") {
      if (i1 + n > j - 5)
        n--; 
      if (i1 + n < j) {
        n = j - 5;
        a(500L);
      } 
      paramHashtable.put("w", String.valueOf(n));
    } 
  }
  
  public static void a(long paramLong) {
    try {
      Thread.sleep(paramLong);
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      return;
    } 
  }
  
  public static String a(String paramString, Hashtable paramHashtable) {
    return ((paramString = (String)paramHashtable.get(paramString)) == null) ? "" : paramString;
  }
  
  static {
    Font.getFont(0, 4, 8);
  }
}


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uccommon/c.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */