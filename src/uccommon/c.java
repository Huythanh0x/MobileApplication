package uccommon;

import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class c {
   private static final byte[] f = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 87, 0, 0, 0, 20, 8, 3, 0, 0, 0, -49, -38, 81, -100, 0, 0, 0, 3, 115, 66, 73, 84, 8, 8, 8, -37, -31, 79, -32, 0, 0, 0, -73, 80, 76, 84, 69, -26, 51, 0, -13, -104, 108, -5, -35, -50, -18, 116, 57, -9, -69, -98, -22, 88, 17, -1, -4, -6, -16, -120, 85, -8, -52, -74, -20, 99, 33, -3, -21, -30, -11, -86, -123, -23, 79, 4, -17, 127, 73, -15, -109, 101, -19, 105, 41, -7, -46, -66, -24, 73, 0, -10, -76, -107, -13, -96, 121, -2, -14, -19, -8, -59, -83, -21, 92, 24, -4, -28, -40, -22, 85, 14, -19, 109, 48, -17, 122, 66, -2, -8, -10, -24, 66, 0, -6, -38, -55, -15, -113, 96, -14, -108, 106, -1, -1, -1, -9, -66, -93, -21, 96, 26, -11, -88, -125, -16, -123, 82, -11, -82, -116, -4, -31, -43, -2, -10, -15, -4, -25, -35, -18, 113, 52, -21, 88, 17, -9, -73, -103, -7, -49, -69, -13, -100, 115, -15, -116, 90, -23, 75, 9, -6, -42, -59, -22, 82, 8, -8, -62, -89, -14, -103, 109, -26, 58, 0, -12, -92, 125, -7, -55, -78, -3, -18, -26, -17, -127, 75, -21, 85, 14, -9, -75, -116, -6, -35, -49, -9, -100, 107, 112, 22, -27, -66, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 11, 18, 0, 0, 11, 18, 1, -46, -35, 126, -4, 0, 0, 0, 28, 116, 69, 88, 116, 83, 111, 102, 116, 119, 97, 114, 101, 0, 65, 100, 111, 98, 101, 32, 70, 105, 114, 101, 119, 111, 114, 107, 115, 32, 67, 83, 52, 6, -78, -45, -96, 0, 0, 0, 22, 116, 69, 88, 116, 67, 114, 101, 97, 116, 105, 111, 110, 32, 84, 105, 109, 101, 0, 48, 52, 47, 50, 50, 47, 49, 48, 35, 64, 105, -39, 0, 0, 3, 113, 73, 68, 65, 84, 56, -115, -67, -108, 93, -109, -94, 58, 16, -122, 81, 16, -28, 99, -59, 86, -116, 1, 33, -45, 34, 31, -126, -110, 49, -96, -61, 108, -51, -1, -1, 95, -89, -125, -77, -77, 123, 113, -26, 106, -85, -74, 47, -70, 40, 18, 30, 58, 111, -65, 105, -125, -3, 79, 84, -8, -41, 97, -4, 115, -18, -75, -72, 80, 62, 57, -65, 126, -78, 44, -106, -120, -75, 47, 116, 28, -15, -26, 76, 15, -94, -90, 21, 79, 12, 54, -30, 94, 110, -89, 125, -3, -12, -35, -9, -36, -63, -96, 125, -75, 27, 124, 16, -14, 37, -117, -50, -37, -76, 59, -41, 62, 4, 83, 84, -3, 61, 8, -108, 2, -13, -16, 40, -10, -25, 32, 39, 110, 6, -31, -60, -35, -34, -59, -109, -53, 57, -95, -38, -74, -91, -52, 21, 69, 59, 113, -19, 38, -56, 16, 35, 30, -21, -94, -17, -3, 10, 84, -80, 88, -120, 21, 88, -98, -73, 79, -96, 60, -20, -9, 14, 12, -5, 3, -106, -9, -63, 107, 67, -84, 101, -46, -70, -123, 53, 43, -118, 102, 17, 75, 41, -115, -42, -19, 79, -100, 113, -71, 75, 56, 83, -119, -65, -20, 103, -18, -60, -35, 65, -110, 89, -76, 20, -10, 125, 47, -31, 120, 20, 67, 87, -64, -48, -63, -19, -10, 114, -13, -63, -89, 29, -101, 123, 73, 57, 114, -35, -73, 60, -60, 81, 41, -58, -96, 11, 3, -59, 89, 75, -59, 25, 42, -58, 121, -64, -108, -113, 82, 81, -62, 121, -123, 66, 17, -9, 28, -62, 117, 107, -92, 105, -50, 83, 29, -67, 62, -95, 19, -52, -119, 123, -70, -5, 62, 108, -86, -117, -27, -68, 58, -106, 85, -37, 2, -92, 27, -38, -74, 103, 5, -115, 87, -113, -29, 40, 64, 80, 54, 120, 108, -33, -120, 123, 66, 9, 91, -84, 98, -50, -101, 60, -81, -48, 54, -63, -63, -84, -68, -68, -65, -54, -53, -27, -78, -23, 73, 63, -20, -46, 1, 87, 112, 43, -95, -12, -31, 82, -34, -45, 32, 87, -87, -70, -60, -82, 50, -13, -112, -42, 63, -108, 57, -23, 123, -126, 23, -83, -17, 23, -41, -31, 30, 38, 48, -55, 93, 97, -60, 22, -90, 86, 67, -92, 87, 20, -85, 105, -1, 38, 8, 28, 111, -8, -28, -106, -34, 114, -105, -68, -118, -11, 122, -87, -36, -21, -107, 105, 110, -58, -61, -18, 109, -37, 36, 33, 119, -109, 68, -2, -26, -66, -57, -24, -27, -117, 79, 63, 28, -106, -90, -70, 81, -13, 76, 53, 63, 48, 87, -101, -55, 82, -15, 12, -114, -37, 95, -11, 102, 9, -106, -87, 86, 57, 121, -83, 34, 30, -38, -47, -61, 100, -117, 116, 35, 120, -18, 82, -76, 90, 95, 59, 83, 76, 61, 80, -124, -44, -125, -59, -105, -49, 26, -82, -19, -112, -25, 81, -19, -122, -60, -35, -128, 114, 118, -80, -23, 62, -71, -42, 91, 90, -107, 105, 89, 62, -48, 74, -59, -103, -4, -112, 1, 107, -61, -11, -127, 62, -119, 16, 43, 119, -30, 122, 109, 11, 22, -118, -96, 34, -7, -38, -10, -23, 51, 123, -30, 126, 80, 87, -93, -119, -5, -125, -79, 102, 7, -89, 14, -42, 4, 45, 83, 107, 9, -5, 50, 61, 21, 63, -88, -32, 102, 73, 126, -120, 110, 87, -83, 111, -44, -123, -31, 48, 14, -63, 15, -93, 109, -25, 88, 50, 97, 31, -30, -128, -4, 60, -72, -82, 116, 127, 115, 119, -44, -67, 39, -9, 109, -99, 55, -41, 96, 69, 58, -56, 96, 51, -125, 39, 119, 107, 22, -13, -115, -65, -19, 24, -69, 28, 48, 83, 13, 98, -111, -26, 57, -124, 109, -20, 25, -116, 55, 115, 58, -10, 40, 73, -116, -43, 56, -39, -119, 127, 113, -53, -76, -5, -28, -94, -57, -30, -84, 89, -115, -43, -102, 26, -37, -36, -22, 94, 115, 31, -119, 56, 25, 0, -64, 126, -126, 55, 113, 51, -14, -102, -105, 40, -43, 79, -9, -115, 57, -99, 116, -55, -42, 76, -71, -78, -21, -110, 63, 116, -104, -91, 23, -51, -43, 46, 59, -77, 48, -46, -1, 55, -63, 108, 19, -60, -93, -26, -106, -85, -43, -34, -22, -5, 25, 119, -5, 3, 105, -42, 96, 15, 3, -30, 35, -32, -73, -25, 124, -104, 46, 47, -5, 124, -102, 124, -90, -71, -28, -121, 119, 88, -30, -24, -78, -11, 88, 107, -18, -120, -74, -97, -125, 92, 47, -62, 121, 109, -23, -66, -7, 103, 125, 84, 60, 115, -102, 12, -47, -111, -72, 123, -98, -9, -69, -112, -85, 36, -6, 110, -18, -40, 33, -75, -79, -50, -23, -22, 69, 46, -51, -102, 7, -98, 85, 30, -95, -89, 64, 28, -50, 63, 1, -100, 35, 27, 79, -58, 115, -128, -31, 71, -112, 83, 99, 1, 72, -33, -110, 84, -127, 71, 9, -26, -73, -13, -84, 123, -40, 24, -119, 25, 73, -37, 75, 81, 28, 113, 20, 27, 42, 106, -69, -44, 55, 68, -54, 7, -39, 105, -9, -34, 63, -71, 52, 39, -23, -12, 82, 94, -11, 52, 93, -83, -24, -19, -32, -4, -5, -71, -2, 119, -15, 31, -80, 115, -63, -80, -66, -47, -45, 67, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};
   public static final Font a = Font.getFont(0, 0, 8);
   public static final Font b;
   private static String g;
   public static String c;
   private static String h;
   public static String d;
   public static String e;
   private static String i;

   static {
      Font.getFont(0, 4, 8);
      b = Font.getFont(0, 1, 8);
      g = "0";
      c = "1";
      h = "2";
      d = "3";
      e = "4";
      i = "5";
   }

   public static Image a() {
      return Image.createImage(f, 0, f.length);
   }

   public static Hashtable a(Vector var0, int var1, int var2, int var3, int var4, String var5, String var6, String var7) {
      Hashtable var8;
      (var8 = new Hashtable()).put("c", var0);
      var8.put("r", new Integer(var1));
      var8.put("q", new Integer(var2));
      var8.put("k", new Integer(var3));
      var8.put("l", new Integer(-1));
      var8.put("b", var5);
      var8.put("e", var6);
      var8.put("f", var7);
      return var8;
   }

   public static Hashtable a(String var0, Image var1, int var2, String var3, String var4, String var5, String var6, int var7) {
      Hashtable var8;
      (var8 = new Hashtable()).put("a", "2");
      var8.put("b", var0);
      var8.put("c", var1);
      var8.put("d", new Integer(0));
      var8.put("e", var3);
      var8.put("f", var4);
      var8.put("h", var5);
      var8.put("s", var6);
      var8.put("l", new Integer(-1));
      return var8;
   }

   public static Hashtable a(String var0, String var1, int var2, String var3, String var4, String var5, String var6, Font var7, int var8, int var9, int var10, int var11, int var12, int var13, String var14) {
      Hashtable var15;
      (var15 = a(var0, var1, 5, var3, var4, var5, var6, var7, var8, var9, var10, e, 0)).put("a", "3");
      var15.put("r", new Integer(var11));
      var15.put("q", new Integer(var12));
      var15.put("n", new Integer(60));
      var15.put("u", var14);
      return var15;
   }

   public static Hashtable a(String var0, String var1, int var2, String var3, String var4, String var5, String var6, Font var7, int var8, int var9, int var10, int var11, int var12) {
      Hashtable var13;
      (var13 = a(var0, var1, 5, var3, var4, var5, var6, var7, var8, var9, var10, e, 0)).put("a", "6");
      var13.put("r", new Integer(14));
      var13.put("q", new Integer(14));
      var13.put("z", "n");
      return var13;
   }

   public static void a(Vector var0, int var1, int var2, int var3) {
      Vector var4;
      Vector var10000 = var4 = new Vector();
      boolean var5 = true;
      var5 = false;
      String var10 = c;
      Hashtable var9;
      (var9 = new Hashtable()).put("a", "5");
      var9.put("h", var10);
      var9.put("d", new Integer(0));
      var9.put("r", new Integer(var3));
      var9.put("q", new Integer(var1));
      var9.put("k", new Integer(var2));
      var9.put("l", new Integer(-1));
      var10000.addElement(var9);
      var0.addElement(a(var4, var3, var1, var2, -1, "n", "", ""));
   }

   public static Hashtable a(String var0, String var1, int var2, String var3, String var4, String var5, String var6, Font var7, int var8, int var9, int var10, String var11, int var12) {
      Hashtable var13;
      (var13 = new Hashtable()).put("a", "1");
      var13.put("b", var0);
      var13.put("c", var1);
      var13.put("d", new Integer(var2));
      var13.put("e", var3);
      var13.put("f", var4);
      var13.put("h", var5);
      var13.put("s", var6);
      var13.put("i", var7);
      var13.put("j", new Integer(var8));
      var13.put("k", new Integer(var9));
      var13.put("l", new Integer(var10));
      var13.put("g", var11);
      var13.put("r", String.valueOf(var12));
      return var13;
   }

   public static Hashtable a(String var0, int var1, int var2, int var3) {
      Hashtable var4 = new Hashtable();
      int var5 = var1 / 4 * 3;
      int var6;
      if ((var6 = var3 * 6) > var2 - var3 * 3) {
         var6 = var2 - var3 * 3;
      }

      var4.put("a", "b");
      var4.put("c", new Vector());
      var4.put("v", "n");
      var4.put("x", var0);
      var4.put("w", "0");
      var4.put("r", String.valueOf(var5));
      var4.put("q", String.valueOf(var6));
      var4.put("d", String.valueOf((var1 - var5) / 2));
      var4.put("m", String.valueOf((var2 - var6) / 2 - var3 / 2));
      return var4;
   }

   public static void a(Hashtable var0, String var1) {
      if (var0 != null) {
         Vector var2;
         (var2 = new Vector()).addElement(var1);
         var0.put("c", var2);
      }

   }

   public static Hashtable a(int var0, int var1, int var2, int var3) {
      Hashtable var4;
      (var4 = new Hashtable()).put("d", new Integer(var0));
      var4.put("m", new Integer(var1));
      var4.put("r", new Integer(var2));
      var4.put("q", new Integer(var3));
      return var4;
   }

   public static void a(Hashtable var0, String var1, String var2, String var3) {
      var0.put("c", var1);
      var0.put("e", var2);
      var0.put("f", var3);
   }

   private static int a(String var0, int var1, int var2) {
      if (var0 == g) {
         return 0;
      } else {
         return var0 == h ? var2 - var1 : (var2 - var1) / 2;
      }
   }

   public static Object[] a(Graphics var0, Hashtable var1, int var2, Hashtable var3, int var4, int var5, int[] var6) {
      Font var7 = (Font)var1.get("i");
      int var8 = Integer.parseInt(var1.get("d").toString());
      int var9 = a(var1.get("h").toString(), var7.getHeight(), var2);
      int var10 = Integer.parseInt(var1.get("j").toString());
      int var11 = Integer.parseInt(var1.get("k").toString());
      int var12 = Integer.parseInt(var1.get("r").toString());
      Integer.parseInt(var1.get("l").toString());
      String var13 = var1.get("g").toString();
      String var14 = var1.get("c").toString();
      if (var12 < var7.stringWidth(var14)) {
         var12 = var7.stringWidth(var14) + 2;
      }

      if (var3 == var1) {
         var11 = var6[9];
         b(var0, 1000000, var8 + 1, var9 + var4 + var5, var12 - 3, var7.getHeight() + 3);
      }

      a(var0, var11, var8 + 2, var9 + 1 + var4 + var5, var12 - 4, var7.getHeight() + 2);
      var0.setColor(var10);
      var0.setFont(var7);
      int var15 = var7.stringWidth(var14) + 2;
      var0.drawString(var14, var8 + (var13 == d ? 0 : (var13 == i ? var12 - var15 : (var12 - var15) / 2)), var9 + var4 + var5 + 2, 0);
      return new Object[]{var1.get("b") != "y" ? null : var1, String.valueOf(var4 + var5), String.valueOf(var2), String.valueOf(var8), String.valueOf(var12)};
   }

   private static void a(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      if (var1 != -1) {
         var0.setColor(var1);
         var0.fillRect(var2, var3, var4, var5);
      }

   }

   private static void b(Graphics var0, int var1, int var2, int var3, int var4, int var5) {
      if (var1 != -1) {
         var0.setColor(var1);
         var0.drawRect(var2, var3, var4, var5);
      }

   }

   public static Object[] a(Graphics var0, Hashtable var1, int var2, int var3, Hashtable var4, int var5, int var6, int var7, int var8, int[] var9) {
      Font var10 = (Font)var1.get("i");
      int var11 = Integer.parseInt(var1.get("d").toString());
      var3 = a(var1.get("h").toString(), var10.getHeight(), var3);
      int var12 = Integer.parseInt(var1.get("j").toString());
      int var13 = Integer.parseInt(var1.get("k").toString());
      int var14 = Integer.parseInt(var1.get("l").toString());
      int var15 = Integer.parseInt(var1.get("r").toString());
      int var16 = Integer.parseInt(var1.get("q").toString());
      String var17 = var1.get("c").toString();
      if (var1.get("u").toString() == "n" && var17.length() > 0) {
         String var18;
         for(var18 = ""; var18.length() < var17.length(); var18 = var18 + "*") {
         }

         var17 = var18;
      }

      if (var4 == var1) {
         var13 = var9[9];
      }

      a(var0, var13, var11, var3 + var5 + var6, var15, var16);
      var0.setFont(var10);
      var0.setColor(var12);
      var0.setClip(var11, var6 + var3 + var5, var15, var16);
      var0.drawString(var17, var11 + 5, var6 + var3 + var5 + (var16 - var10.getHeight()) / 2, 0);
      var0.setClip(0, 0, var7, var8);
      b(var0, var14, var11, var3 + var5 + var6, var15, var16);
      return new Object[]{var1.get("b") != "y" ? null : var1, String.valueOf(var5 + var6), String.valueOf(var2), String.valueOf(var11), String.valueOf(var15)};
   }

   public static Object[] a(Graphics var0, Hashtable var1, int var2, int var3, Hashtable var4, int var5, int var6, int var7, int[] var8) {
      Font var9 = (Font)var1.get("i");
      int var10 = Integer.parseInt(var1.get("d").toString());
      var3 = a(var1.get("h").toString(), var9.getHeight(), var3);
      int var11 = Integer.parseInt(var1.get("j").toString());
      int var12 = Integer.parseInt(var1.get("k").toString());
      int var13 = Integer.parseInt(var1.get("l").toString());
      int var14 = Integer.parseInt(var1.get("r").toString());
      int var15 = Integer.parseInt(var1.get("q").toString());
      String var16 = var1.get("z").toString();
      String var17 = "保存密码？";
      if (var4 == var1) {
         var12 = var8[9];
         b(var0, var13, var10, var3 + var5 + var6, var7 - 9, var15 + 11);
      }

      a(var0, var12, var10 + 1, var3 + 1 + var5 + var6, var7 - 10, var15 + 10);
      var0.setFont(var9);
      var0.setColor(var11);
      var0.drawString(var17, var10 + 3 + var14 + 5, var6 + 5 + var3 + var5 + (var15 - var9.getHeight()) / 2, 0);
      b(var0, var13, var10 + 3, var3 + var5 + var6 + 5, var14, var15);
      if (var16 == "y") {
         a(var0, var8[1], var10 + 6, var3 + var5 + var6 + 5 + 3, var14 - 5, var15 - 5);
      } else {
         a(var0, var12, var10 + 6, var3 + var5 + var6 + 5 + 3, var14 - 5, var15 - 5);
      }

      return new Object[]{var1, String.valueOf(var5 + var6), String.valueOf(var2), String.valueOf(var10), String.valueOf(var14)};
   }

   public static Object[] a(Graphics var0, Hashtable var1, int var2, int var3, int var4) {
      Image var5 = (Image)var1.get("c");
      int var6 = Integer.parseInt(var1.get("d").toString());
      int var7 = Integer.parseInt(var1.get("l").toString());
      int var8 = a(var1.get("h").toString(), var5.getHeight(), var2);
      var0.drawImage(var5, var6, var8 + var3 + var4, 0);
      b(var0, var7, var6 - 1, var8 + var3 - 1, var5.getWidth() + 2 + var4, var5.getHeight() + 2);
      return null;
   }

   public static void b(Graphics var0, Hashtable var1, int var2, int var3, int var4) {
      int var5 = Integer.parseInt(var1.get("r").toString());
      int var6 = Integer.parseInt(var1.get("q").toString());
      int var7 = Integer.parseInt(var1.get("k").toString());
      int var8 = Integer.parseInt(var1.get("l").toString());
      int var9 = Integer.parseInt(var1.get("d").toString());
      int var10 = a(var1.get("h").toString(), var6, var2);
      a(var0, var7, var9, var10 + var3 + var4, var5, var6);
      b(var0, var8, var9, var10 + var3 + var4, var5, var6);
   }

   public static void a(Graphics var0, Hashtable var1, int var2, int var3) {
      int var4 = Integer.parseInt(var1.get("r").toString());
      int var5 = Integer.parseInt(var1.get("k").toString());
      int var6 = Integer.parseInt(var1.get("l").toString());
      int var7 = Integer.parseInt(var1.get("q").toString());
      a(var0, var5, 0, var2 + var3, var4, var7);
      b(var0, var6, 0, var2 + var3, var4, var7);
   }

   public static void a(Graphics var0, int var1, int var2, int var3, int[] var4, String var5, String var6) {
      var0.setColor(var4[7]);
      var0.fillRect(0, var2 - var3, var1, var3);
      var0.setFont(b);
      var0.setColor(var4[0]);
      if (var5 != null) {
         var0.drawString(var5, 5, var2 - var3 + (var3 - a.getHeight()) / 2, 0);
      }

      if (var6 != null) {
         var0.drawString(var6, var1 - 5 - b.stringWidth(var6), var2 - var3 + (var3 - a.getHeight()) / 2, 0);
      }

   }

   public static void a(Graphics var0, Hashtable var1, int var2, int[] var3, Vector var4) {
      int var5 = Integer.parseInt(var1.get("r").toString());
      int var6 = Integer.parseInt(var1.get("q").toString());
      int var7 = Integer.parseInt(var1.get("d").toString());
      int var8 = Integer.parseInt(var1.get("m").toString());
      int var9 = Integer.parseInt(var1.get("w").toString());
      String var10 = var1.get("v").toString();
      String var11 = var1.get("x").toString();
      var0.setColor(var3[7]);
      var0.fillRect(var7, var8, var5, var6);
      var0.setColor(var3[6]);
      var0.drawLine(var7 + 1, var8 - 1, var7 + 1 + var5, var8 - 1);
      var0.drawLine(var7 + 2, var8 - 2, var7 + 2 + var5, var8 - 2);
      var0.drawLine(var7 + 3, var8 - 3, var7 + 2 + var5, var8 - 3);
      var0.drawLine(var7 + var5, var8 - 1, var7 + var5, var8 + var6 - 1);
      var0.drawLine(var7 + var5 + 1, var8 - 2, var7 + var5 + 1, var8 + var6 - 2);
      var0.drawLine(var7 + var5 + 2, var8 - 3, var7 + var5 + 2, var8 + var6 - 3);
      var0.setColor(var3[0]);
      var0.fillRect(var7 + 1, var8 + 1, var5 - 2, var6 - 2);
      var0.setColor(var3[1]);
      int var12 = 0;
      var0.setClip(var7 + 2, var8 + 2, var5 - 4, var6 - 4);

      int var13;
      for(var13 = 0; var13 < var4.size(); ++var13) {
         var0.drawString(var4.elementAt(var13).toString(), var7 + 3, var9 + var12 + var8 + 3, 0);
         var12 += var2;
      }

      if (var10 == "y") {
         if (var12 / var2 < 4) {
            var12 += var2;
         }

         var0.drawString("按“确定”键返回。", var7 + 3, var9 + var12 + var8 + 3, 0);
      }

      var0.setClip(var7, var8, var5, var6 + 15);
      if (var10 == "n") {
         var0.setColor(var3[6]);
         var0.fillRect(var7, var8 + var6 - 15, var5, 15);
         var0.clipRect(var7 + 1, var8 + var6 - 15 + 1, var5 - 2, 13);
         var0.setColor(var3[8]);
         var0.fillRect(var7 + 1, var8 + var6 - 15 + 1, var5 - 2, 13);
         var0.setColor(var3[6]);
         var13 = (int)(System.currentTimeMillis() % (long)((var5 - 4) * 10)) / 10;
         var0.fillRect(var13 + var7 + 1, var8 + var6 - 15 + 2, 5, 11);
         var0.fillRect(var13 + var7 + 8, var8 + var6 - 15 + 2, 5, 11);
         var0.fillRect(var13 + var7 + 16, var8 + var6 - 15 + 2, 5, 11);
      } else {
         if (var11 == "y") {
            if (var12 + var9 > var6 - 5) {
               --var9;
            }

            if (var12 + var9 < var6) {
               var9 = var6 - 5;
               a(500L);
            }

            var1.put("w", String.valueOf(var9));
         }

      }
   }

   public static void a(long var0) {
      try {
         Thread.sleep(var0);
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public static String a(String var0, Hashtable var1) {
      Object var2;
      return (var2 = var1.get(var0)) == null ? "" : (String)var2;
   }
}
