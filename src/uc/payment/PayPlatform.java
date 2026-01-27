package uc.payment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import uccommon.a;
import uccommon.c;
import uccommon.d;
import uccommon.e;

public class PayPlatform extends Canvas implements Runnable, CommandListener {
   private static PayPlatform a = null;
   private static MIDlet b = null;
   private static boolean c = true;
   private boolean d = false;
   private static int e;
   private static int f;
   private static int g;
   private static int h;
   private Image i = null;
   private static int j = 0;
   private int k = 0;
   private int l = 0;
   private int[] m;
   private Vector n = null;
   private Hashtable o = new Hashtable();
   private Hashtable p = new Hashtable();
   private String q;
   private Hashtable r = null;
   private Vector s;
   private Vector t;
   private byte u = 0;
   private long v = 0L;
   private boolean w = false;
   private int x;
   private static Hashtable y = null;
   private static String z = "a";
   private static String A = "b";
   private static String B = "e";
   private static String C = "d";
   private static String D = "g";
   private static String E = "f";
   private static Hashtable F = null;
   private static Hashtable G = null;
   private static Hashtable H = null;
   private static Hashtable I = null;
   private static Hashtable J = null;
   private boolean K;
   private TextBox L;
   private Command M;
   private Command N;
   private Command O;
   private int P;
   private static String Q = "POST";
   private String R;
   private String S;
   private int T;
   private String U;
   private int V;
   private String W;
   private String X;
   private String Y;
   private static boolean Z = false;
   private static String aa = "";
   private String ab;
   private String ac;
   private String ad;
   private String ae;
   private int af;
   private static String[] ag = null;
   private static String[] ah = null;
   private static Hashtable ai = null;
   private int aj;
   private static String ak = "";
   private static Hashtable al = new Hashtable();
   private Hashtable am;
   private String an;
   private String ao;
   private String ap;
   private String aq;
   private String ar;
   private String as;
   private static String at = "";
   private static String au = "";
   private static String av = "";
   private int aw;
   private int ax;
   private String ay;
   private int az;
   private int aA;
   private int aB;
   private int aC;
   private static int aD = 0;
   private static int aE = 0;
   private static int aF = 1;
   private static int aG = 2;
   private static String[] aH = new String[5];
   private static String aI = "";
   private static String aJ = "";
   private static String aK = "";
   private String[] aL;
   private String[] aM;
   private static String aN = "";
   private static Hashtable aO = null;
   private static boolean aP = false;
   private static String aQ;
   private static String aR;
   private String aS;
   private String aT;
   private boolean aU;
   private String aV;
   private String aW;
   private String aX;
   private static Random aY;
   private static int aZ = 30000;
   private boolean ba = false;

   static {
      try {
         l();
      } catch (Exception var0) {
         aP = true;
      }

      a();
   }

   private PayPlatform(MIDlet var1, String var2, int var3, int var4, int var5, int var6, int var7) {
      this.w = false;
      this.x = 0;
      this.K = false;
      this.P = 0;
      this.R = "1";
      this.S = "0";
      this.T = 0;
      this.U = "";
      this.ab = "";
      this.ac = "";
      this.ad = "";
      this.ae = "";
      this.af = 0;
      this.aj = 0;
      aR = "";
      aQ = "";
      this.am = new Hashtable();
      this.an = "";
      this.ao = "";
      this.as = "";
      this.aA = 0;
      this.aB = 0;
      this.aC = 0;
      this.aL = null;
      this.aM = null;
      this.aS = "";
      this.aT = "";
      this.aU = false;
      this.setFullScreenMode(c);
      b = var1;
      this.q = var2;
      e = 0;
      f = 0;
      g = 0;
      h = 0;
      this.V = var7;
      if (g == 0) {
         g = this.getWidth();
      }

      if (h == 0) {
         h = this.getHeight();
      }

      a();
      this.b();

      try {
         if (!aP) {
            this.i();
            String[] var9 = m();
            F.put("c", var9[0]);
            G.put("c", var9[1]);
            ak = var9[2];
            return;
         }
      } catch (Exception var8) {
      }

   }

   private static void a() {
      if (ai == null || ag == null || ah == null) {
         ai = new Hashtable();
         Vector var0 = new Vector();
         Vector var1 = new Vector();
         String var2;
         if ((var2 = d("/APISetting.ini")) != null && !var2.equals("")) {
            String[] var9 = b(var2, "\r\n");
            byte var3 = 0;

            for(int var4 = 0; var4 < var9.length; ++var4) {
               String var5;
               if ((var5 = var9[var4]) != null) {
                  if (var5.indexOf("[Settings]") >= 0) {
                     var3 = 2;
                  } else if (var5.indexOf("[Language1]") >= 0) {
                     var3 = 3;
                  } else if (var5.indexOf("[Language2]") >= 0) {
                     var3 = 5;
                  } else {
                     switch (var3) {
                        case 2:
                           int var6;
                           if (!var5.equals("") && (var6 = var5.indexOf("=")) >= 0) {
                              String var7 = var5.substring(0, var6);
                              var5 = var5.substring(var6 + 1, var5.length());
                              ai.put(var7, var5);
                           }
                           break;
                        case 3:
                           var0.addElement(var5);
                        case 4:
                        default:
                           break;
                        case 5:
                           var1.addElement(var5);
                     }
                  }
               }
            }
         }

         ag = new String[var0.size()];
         var0.copyInto(ag);
         ah = new String[var1.size()];
         var1.copyInto(ah);

         try {
            if ((aN = System.getProperty("wireless.messaging.sms.smsc").trim()).length() > 11 && aN.indexOf("13") >= 0) {
               aN = aN.substring(aN.indexOf("13"));
            }

            return;
         } catch (Exception var8) {
            aN = "";
         }
      }

   }

   private void b() {
      this.aB = 0;
      this.P = 0;
      this.d = false;

      try {
         byte[] var1 = a(4, (byte[])null, "upg_api_private");
         ByteArrayInputStream var11 = new ByteArrayInputStream(var1);
         DataInputStream var2 = new DataInputStream(var11);

         try {
            at = var2.readUTF();
            av = var2.readUTF();
            au = var2.readUTF();
            var2.close();
            var11.close();
         } catch (Exception var9) {
            var9.printStackTrace();
         }

         this.aw = 0;
      } catch (Exception var10) {
      }

      int var4;
      String[] var12;
      if (at == null || at.equals("")) {
         if (aN != null && !aN.equals("") && aN.length() >= 8) {
            var12 = b(uccommon.c.a("smsclist", ai), "=#=");
            String[] var14 = b(uccommon.c.a("smsinfo", ai), "=#=");
            String[] var3 = b(uccommon.c.a("smstext", ai), "=#=");

            for(var4 = 0; var4 < var12.length; ++var4) {
               if ((at == null || at.equals("")) && var12[var4].equals("all")) {
                  at = var14[var4];
                  au = var3[var4];
               } else {
                  String[] var5 = b(var12[var4], ",");

                  for(int var6 = 0; var6 < var5.length; ++var6) {
                     if (aN.startsWith(var5[var6]) || aN.startsWith("+" + var5[var6]) || aN.startsWith("+86" + var5[var6]) || aN.startsWith("86" + var5[var6]) || aN.startsWith("0" + var5[var6])) {
                        at = var14[var4];
                        au = var3[var4];
                     }
                  }
               }
            }

            at = c("7D7026B0B3", at);
            if (uccommon.c.a("smsendday", ai) != null && !uccommon.c.a("smsendday", ai).equals("")) {
               this.aw = Integer.parseInt(uccommon.c.a("smsendday", ai));
            } else {
               this.aw = 7;
            }
         } else {
            this.aw = 0;
            av = "19000101";
         }
      }

      String var13 = uccommon.c.a("urlpath", ai);
      aa = "http://" + c("7D7026B0B3", var13);
      this.ab = aa + c("7D7026B0B3", uccommon.c.a("upointpayUrl", ai));
      this.ac = aa + c("7D7026B0B3", uccommon.c.a("updatesmsUrl", ai));
      this.ad = aa + c("7D7026B0B3", uccommon.c.a("payresultUrl", ai));
      this.ae = aa + c("7D7026B0B3", uccommon.c.a("discountUrl", ai));
      this.aV = uccommon.c.a("ydsmsport", ai);
      this.aW = uccommon.c.a("ltsmsport", ai);
      this.af = Integer.parseInt(uccommon.c.a("forcecharge", ai));
      aK = uccommon.c.a("idType", ai);
      aJ = uccommon.c.a("idName", ai);
      if (d(200).equals("")) {
         aE = aG;
      }

      aZ = Integer.parseInt(uccommon.c.a("conntimeout", ai)) * 1000;
      aI = uccommon.c.a("coinName", ai);
      this.ap = uccommon.c.a("launchsite", ai);
      this.aq = uccommon.c.a("phonetype", ai);
      this.ar = uccommon.c.a("backupcode", ai);
      this.m = new int[10];
      if (!uccommon.c.a("colors", ai).equals("")) {
         var12 = b(uccommon.c.a("colors", ai), ",");

         for(int var15 = 0; var15 < var12.length; ++var15) {
            this.m[var15] = Integer.parseInt(var12[var15]);
         }
      }

      var12 = b(uccommon.c.a("payMethod", ai), ",");
      Vector var16 = new Vector();
      Vector var17 = new Vector();

      for(var4 = 0; var4 < var12.length; ++var4) {
         String var19 = "";
         int var7 = Integer.parseInt(var12[var4]);
         String var18 = var12[var4];
         switch (var7) {
            case 1:
               var19 = a(a(1, 1), "$(str)", "");
               break;
            case 2:
               var19 = a(a(2, 1), "$(str)", "");
         }

         var16.addElement(var18);
         var17.addElement(var19);
      }

      this.aL = new String[var16.size()];
      this.aM = new String[var16.size()];
      var16.copyInto(this.aL);
      var17.copyInto(this.aM);

      try {
         this.aj = Integer.parseInt(uccommon.c.a("payDefault", ai));
         Integer.parseInt(this.aL[this.aj]);
      } catch (Exception var8) {
      }

      this.i = uccommon.c.a();
      j = uccommon.c.b.getHeight() + 8;
      this.M = new Command(a(0, 0), 4, 0);
      this.N = new Command(a(1, 0), 2, 1);
      F = uccommon.c.a("y", "", 5, "n", "", uccommon.c.c, a(8, 1), uccommon.c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "y");
      G = uccommon.c.a("y", "", 5, "n", "", uccommon.c.c, a(5, 1), uccommon.c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
      H = uccommon.c.a("y", "", 5, "n", "", uccommon.c.c, a(63, 1), uccommon.c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
      I = uccommon.c.a("y", "", 5, "n", "", uccommon.c.c, a(74, 1), uccommon.c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
      J = uccommon.c.a("y", "", 5, "c", "", uccommon.c.c, "savepassword", uccommon.c.a, this.m[1], this.m[0], this.m[1], 14, 14);
      (aO = new Hashtable()).put("Content-Type", "application/x-www-form-urlencoded");
   }

   private void c() {
      this.s = null;
      this.t = null;
      this.r = null;
      this.x = 0;
      this.n = new Vector();
      Vector var1;
      (var1 = new Vector()).addElement(uccommon.c.a("n", this.i, 0, "", "", uccommon.c.c, "", -1));
      this.n.addElement(uccommon.c.a(var1, g, this.i.getHeight(), this.m[0], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 1, this.m[2], g);
   }

   private void a(String var1, String var2, String var3, String var4) {
      this.c();
      Vector var5;
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(63, 1), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[6], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 2, -1, g);
      String[] var6 = a(a(64, 1), uccommon.c.a, g - 10);

      for(int var7 = 0; var7 < var6.length; ++var7) {
         (var5 = new Vector()).addElement(uccommon.c.a("n", var6[var7], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      }

      uccommon.c.a((Vector)this.n, 2, this.m[2], g);
      (var5 = new Vector()).addElement(uccommon.c.a("n", "支付密码", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.e, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      (var5 = new Vector()).addElement(H);
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 5, -1, g);
      (var5 = new Vector()).addElement(uccommon.c.a("y", a(0, 0), 5, "m", String.valueOf(3), uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      uccommon.c.a(this.o, a(0, 0), var1, var2);
      uccommon.c.a(this.p, a(1, 0), var3, var4);
      this.ba = true;
      this.u = 4;
   }

   private void b(String var1, String var2, String var3, String var4) {
      this.c();
      Vector var5;
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(71, 1), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[6], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 2, -1, g);
      String[] var6 = a(a(72, 1), uccommon.c.a, g - 10);

      int var7;
      for(var7 = 0; var7 < var6.length; ++var7) {
         (var5 = new Vector()).addElement(uccommon.c.a("n", var6[var7], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      }

      (var5 = new Vector()).addElement(I);
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 5, -1, g);
      var6 = a(a(73, 1), uccommon.c.a, g - 10);

      for(var7 = 0; var7 < var6.length; ++var7) {
         (var5 = new Vector()).addElement(uccommon.c.a("n", var6[var7], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      }

      (var5 = new Vector()).addElement(uccommon.c.a("n", a(75, 1), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("y", a(77, 1), 5, "t", String.valueOf(3), uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(76, 1), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("y", a(77, 1), 5, "t", String.valueOf(4), uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      uccommon.c.a(this.o, a(0, 0), var1, var2);
      uccommon.c.a(this.p, a(1, 0), var3, var4);
      this.ba = true;
      this.u = 4;
   }

   private void c(String var1, String var2, String var3, String var4) {
      this.c();
      Vector var5;
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(20, 0), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[6], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 0, this.m[2], g);
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(a(67, 1), "$(str)", String.valueOf(this.V)), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(66, 1), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      var5.addElement(uccommon.c.a("n", this.aS + "折", a(a(66, 1), uccommon.c.a) + 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[6], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(68, 1), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      var5.addElement(uccommon.c.a("n", this.aT + " U点", a(a(68, 1), uccommon.c.a) + 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[6], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      (var5 = new Vector()).addElement(uccommon.c.a("n", a(69, 1), 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 5, this.m[2], g);
      (var5 = new Vector()).addElement(uccommon.c.a("y", a(0, 0), 5, "r", A, uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var5, g, j, -1, -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 10, this.m[2], g);
      String[] var6 = a(a(70, 1), uccommon.c.a, g - 10);

      for(int var7 = 0; var7 < var6.length; ++var7) {
         (var5 = new Vector()).addElement(uccommon.c.a("n", var6[var7], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      }

      uccommon.c.a((Vector)this.n, 5, this.m[2], g);
      (var5 = new Vector()).addElement(uccommon.c.a("n", "优视科技 版权所有", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[4], -1, -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var5, g, j, this.m[2], -1, "n", "", ""));
      uccommon.c.a(this.o, a(0, 0), var1, var2);
      uccommon.c.a(this.p, a(1, 0), var3, var4);
      this.ba = false;
      this.u = 4;
   }

   private void a(String[] var1, Vector[] var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11) {
      this.c();

      Vector var12;
      for(int var13 = 0; var13 < var1.length; ++var13) {
         (var12 = new Vector()).addElement(uccommon.c.a("n", var1[var13], 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var12, g, j, this.m[6], -1, "n", "", ""));
         uccommon.c.a((Vector)this.n, 2, this.m[2], g);

         for(int var14 = 0; var14 < var2[var13].size(); ++var14) {
            String[] var15 = a((String)var2[var13].elementAt(var14), uccommon.c.a, g - 10);

            for(int var16 = 0; var16 < var15.length; ++var16) {
               (var12 = new Vector()).addElement(uccommon.c.a("n", var15[var16], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
               this.n.addElement(uccommon.c.a(var12, g, j, this.m[2], -1, "n", "", ""));
            }
         }

         uccommon.c.a((Vector)this.n, 2, this.m[2], g);
      }

      (var12 = new Vector()).addElement(uccommon.c.a("y", var9, 0, var10, var11, uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g));
      this.n.addElement(uccommon.c.a(var12, g, j, -1, -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 20, this.m[2], g);
      (var12 = new Vector()).addElement(uccommon.c.a("n", "优视科技 版权所有", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[4], -1, -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var12, g, j, this.m[2], -1, "n", "", ""));
      uccommon.c.a(this.o, var3, var4, var5);
      uccommon.c.a(this.p, var6, var7, var8);
      this.u = 4;
   }

   private void d() {
      this.c();
      Vector var1;
      (var1 = new Vector()).addElement(uccommon.c.a("n", a(20, 0), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var1, g, j, this.m[6], -1, "n", "", ""));
      uccommon.c.a((Vector)this.n, 0, this.m[2], g);
      String[] var2 = a(this.U, uccommon.c.a, g - 10);

      for(int var3 = 0; var3 < var2.length; ++var3) {
         (var1 = new Vector()).addElement(uccommon.c.a("n", var2[var3], 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var1, g, j, this.m[2], -1, "n", "", ""));
      }

      (var1 = new Vector()).addElement(uccommon.c.a("n", a(0, 1), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
      this.n.addElement(uccommon.c.a(var1, g, j, this.m[6], -1, "n", "", ""));
      String[] var5 = a(a(65, 1), uccommon.c.b, g - 10);

      int var4;
      for(var4 = 0; var4 < var5.length; ++var4) {
         (var1 = new Vector()).addElement(uccommon.c.a("n", var5[var4], 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[6], -1, -1, uccommon.c.d, 0));
         this.n.addElement(uccommon.c.a(var1, g, j, this.m[2], -1, "n", "", ""));
      }

      uccommon.c.a((Vector)this.n, 5, this.m[2], g);

      for(var4 = 0; var4 < this.aL.length; ++var4) {
         (var1 = new Vector()).addElement(uccommon.c.a("y", this.aM[var4], 0, "t", String.valueOf(this.aL[var4]), uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g));
         this.n.addElement(uccommon.c.a(var1, g, j, -1, -1, "n", "", ""));
         uccommon.c.a((Vector)this.n, 2, this.m[2], g);
      }

      uccommon.c.a((Vector)this.n, 20, this.m[2], g);
      System.out.println("ver:1.0final");
      (var1 = new Vector()).addElement(uccommon.c.a("n", "优视科技 版权所有", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[4], -1, -1, uccommon.c.e, g - 10));
      this.n.addElement(uccommon.c.a(var1, g, j, this.m[2], -1, "n", "", ""));
      uccommon.c.a(this.o, a(0, 0), "o", "");
      uccommon.c.a(this.p, a(1, 0), "p", "");
      this.u = 4;
   }

   protected void paint(Graphics var1) {
      var1.setColor(this.m[2]);
      var1.fillRect(0, 0, g, h);
      this.l = 0;
      if (this.s == null || this.u == 4) {
         this.s = new Vector();
         this.t = new Vector();
         this.u = 5;
      }

      if (this.n != null) {
         for(int var2 = 0; var2 < this.n.size(); ++var2) {
            this.a(var1, (Hashtable)this.n.elementAt(var2));
         }
      }

      this.k = this.l;
      if (this.u == 5) {
         this.u = -1;
      }

      uccommon.c.a(var1, g, h, j, this.m, this.o.get("c").toString(), this.p.get("c").toString());
      if (y != null && y.get("a").toString() == "b") {
         Vector var7 = (Vector)y.get("c");
         Vector var3 = new Vector();

         for(int var4 = 0; var4 < var7.size(); ++var4) {
            String[] var5 = a((String)var7.elementAt(var4), uccommon.c.a, Integer.parseInt(y.get("r").toString()));

            for(int var6 = 0; var6 < var5.length; ++var6) {
               var3.addElement(var5[var6]);
            }
         }

         uccommon.c.a(var1, y, j, this.m, var3);
      }

   }

   private void a(Graphics var1, Hashtable var2) {
      int var3 = Integer.parseInt(var2.get("q").toString());
      if (this.l + this.x < h && this.l + this.x + var3 > 0) {
         uccommon.c.a(var1, var2, this.l, this.x);
         Vector var8;
         if ((var8 = (Vector)var2.get("c")) != null) {
            for(int var4 = 0; var4 < var8.size(); ++var4) {
               Hashtable var5;
               String var6;
               if ((var6 = (var5 = (Hashtable)var8.elementAt(var4)).get("a").toString()) == "2") {
                  uccommon.c.a(var1, var5, var3, this.l, this.x);
               }

               if (var6 == "5") {
                  uccommon.c.b(var1, var5, var3, this.l, this.x);
               }

               Object[] var7;
               if (var6 == "1" && (var7 = uccommon.c.a(var1, var5, var3, this.r, this.l, this.x, this.m)) != null) {
                  this.a((Hashtable)var7[0], Integer.parseInt(var7[1].toString()), Integer.parseInt(var7[2].toString()), Integer.parseInt(var7[3].toString()), Integer.parseInt(var7[4].toString()));
               }

               if (var6 == "3" && (var7 = uccommon.c.a(var1, var5, var3, j, this.r, this.l, this.x, g, h, this.m)) != null) {
                  this.a((Hashtable)var7[0], Integer.parseInt(var7[1].toString()), Integer.parseInt(var7[2].toString()), Integer.parseInt(var7[3].toString()), Integer.parseInt(var7[4].toString()));
               }

               if (var6 == "6" && (var7 = uccommon.c.a(var1, var5, var3, j, this.r, this.l, this.x, g, this.m)) != null) {
                  this.a((Hashtable)var7[0], Integer.parseInt(var7[1].toString()), Integer.parseInt(var7[2].toString()), Integer.parseInt(var7[3].toString()), Integer.parseInt(var7[4].toString()));
               }
            }
         }
      }

      this.l += var3;
   }

   public void run() {
      if (aP) {
         long var9 = System.currentTimeMillis();
         uccommon.c.a(y = uccommon.c.a("n", g, h, j), "正在初始化数据，请稍等");
         RecordStore var1 = null;
         byte[] var2 = new byte[1024];

         try {
            RecordStore.deleteRecordStore("upg_api_private");
         } catch (Exception var14) {
         }

         try {
            ((RecordStore)(var1 = RecordStore.openRecordStore("upg_api_private", true, 0, false))).addRecord(var2, 0, 200);
            ((RecordStore)var1).addRecord(var2, 0, 100);
            ((RecordStore)var1).addRecord(var2, 0, 500);
            ((RecordStore)var1).addRecord(var2, 0, 200);
            ((RecordStore)var1).addRecord(var2, 0, 1);
            ((RecordStore)var1).addRecord(var2, 0, 1);
            ((RecordStore)var1).addRecord(var2, 0, 1);
         } catch (Exception var13) {
            var13.printStackTrace();
            aE = aF;
         }

         a((RecordStore)var1);
         if (aE == 0) {
            j();
            av = a(true);
            if (aN == null || aN.equals("") || aN.length() < 8) {
               av = "19000101";
            }

            h();
         }

         if (System.currentTimeMillis() - var9 < 1000L) {
            uccommon.c.a(1000L - (System.currentTimeMillis() - var9));
         }

         aP = false;
         y = null;
      }

      for(; this.w; uccommon.c.a(50L)) {
         String var15;
         Vector var16;
         switch (this.P) {
            case 1:
               if (this.L != null) {
                  TextBox var20 = this.L;
                  PayPlatform var21 = this;
                  Display var22;
                  Displayable var23 = (var22 = Display.getDisplay(b)).getCurrent();
                  this.K = true;
                  var22.setCurrent(var20);

                  while(var21.K) {
                     uccommon.c.a(60L);
                  }

                  var22.setCurrent(var23);
               }

               var15 = this.L.getString();
               if (this.O == this.M) {
                  this.r.put("c", var15);
               }

               this.u = 4;
               this.P = 0;
               break;
            case 2:
               if (this.b(0) < 0) {
                  if (y != null) {
                     y.put("v", "y");
                  }

                  this.P = 0;
               } else {
                  this.P = 6;
               }
               break;
            case 3:
               if (this.b(1) < 0) {
                  if (y != null) {
                     y.put("v", "y");
                  }

                  this.P = 0;
               } else {
                  this.P = 6;
               }
               break;
            case 4:
               if (y == null) {
                  uccommon.c.a(y = uccommon.c.a("n", g, h, j), a(61, 1));
               }

               if (!a(this.an, (String)this.ao, 2000)) {
                  y.put("v", "y");
                  uccommon.c.a(y, a(7, 0));
                  this.P = 0;
               } else {
                  ++this.aC;
                  this.T += this.aA;
                  updatePaidMoney(this.R, this.T);
                  ++this.aB;
                  if (this.T < this.V) {
                     var15 = a(a(a(a(a(a(8, 0) + "，" + a(10, 0), "$(str3)", this.as), "$(str2)", String.valueOf(this.az - this.aB)), "$(str1)", String.valueOf(this.aB)), "$(str4)", d(this.V)), "$(str5)", String.valueOf(this.aB + 1));
                     (var16 = new Vector()).addElement(var15);
                     if (F.get("c").toString() != null && !"".equals(F.get("c").toString())) {
                        var16.addElement(aJ + ":" + " " + F.get("c").toString());
                     }

                     y = null;
                     this.P = 0;
                     var15 = "r";
                     String var3 = B;
                     this.a(new String[]{a(20, 0)}, new Vector[]{var16}, a(0, 0), "o", "", a(1, 0), var15, var3, a(0, 0), "m", String.valueOf(4));
                  } else {
                     if (this.ax == 1 || this.ax == 0 && this.af == 1) {
                        try {
                           if (!this.c(1)) {
                              this.c(1);
                           }
                        } catch (Exception var12) {
                        }
                     }

                     String var4 = d(this.V) + "(发送短信：" + this.aB + "条)";
                     Vector var17;
                     (var17 = new Vector()).addElement(a(5, 0) + "\n" + a(19, 0) + " " + var4);
                     this.d = true;
                     y = null;
                     this.P = 0;
                     this.a(new String[]{a(20, 0)}, new Vector[]{var17}, a(0, 0), "o", "", a(1, 0), "p", "", a(0, 0), "p", "");
                  }

                  String var10 = this.R;
                  PayPlatform var24 = this;

                  try {
                     ByteArrayOutputStream var18 = new ByteArrayOutputStream();
                     DataOutputStream var19;
                     (var19 = new DataOutputStream(var18)).writeUTF(var24.an);
                     var19.writeUTF(var24.ao);
                     var19.writeInt(var24.aA);
                     var19.writeInt(var24.aB);
                     var19.writeInt(var24.az);
                     var19.writeUTF(var24.as);
                     var19.writeUTF(uccommon.c.a("addtimes", ai));
                     var24.am.put(var10, var18.toByteArray());
                     var19.close();
                     var18.close();
                     var24.g();
                  } catch (Exception var11) {
                     var11.printStackTrace();
                  }
               }
            case 5:
            case 7:
            default:
               break;
            case 6:
               aD += this.V;
               updatePaidMoney(this.R, this.V);
               (var16 = new Vector()).addElement(a(5, 0));
               if (!aK.equals("noid")) {
                  var16.addElement(a(89, 1) + ": " + F.get("c").toString());
                  var16.addElement(a(41, 1) + ": " + this.W + " U点");
                  var16.addElement(a(88, 1) + ": " + this.X + " U点");
               }

               this.a(new String[]{a(20, 0)}, new Vector[]{var16}, a(0, 0), "o", "", a(1, 0), "p", "", a(0, 0), "p", "");
               this.d = true;
               y = null;
               this.P = 0;
               break;
            case 8:
               this.o();
               this.P = 0;
               break;
            case 9:
               if (!this.aU && !this.f()) {
                  y.put("v", "y");
               } else {
                  this.c("o", "", "r", z);
               }

               this.P = 0;
               break;
            case 10:
               if (y == null) {
                  uccommon.c.a(y = uccommon.c.a("n", g, h, j), a(61, 1));
               }

               if (I.get("c") != null && !I.get("c").equals("")) {
                  if (a(this.aX, (String)("game:" + I.get("c").toString()), 0)) {
                     uccommon.c.a(y, a(82, 1));
                     y.put("v", "y");
                     this.P = 0;
                     this.a("r", A);
                  } else {
                     y.put("v", "y");
                     uccommon.c.a(y, a(7, 0));
                     this.P = 0;
                  }
               } else {
                  uccommon.c.a(y, a(83, 1));
                  y.put("v", "y");
                  this.P = 0;
               }
         }

         if (aE == 1) {
            var15 = a(14, 0) + a(15, 0) + a(16, 0) + "\n";
            (y = uccommon.c.a("y", g, h, j)).put("v", "y");
            uccommon.c.a(y, var15);
            aE = 0;
         }
      }

      ag = null;
      ah = null;
      ai = null;
      F = null;
      G = null;
      H = null;
   }

   private boolean e() {
      if (y == null) {
         y = uccommon.c.a("n", g, h, j);
      }

      uccommon.c.a(y, a(31, 0));
      String var1 = this.ac;
      String var2 = k();
      StringBuffer var3;
      (var3 = new StringBuffer()).append("user_id=" + var2);
      var3.append("&cpid=" + this.Y);
      var3.append("&gameid=" + this.q);
      var3.append("&area=");
      var3.append("&smsno=" + aN);
      e var7 = new e(var1, Q, var3.toString(), aO, aZ);

      byte[] var9;
      try {
         label72: {
            Hashtable var8;
            if ((var8 = uccommon.e.a(var7, Z, "")) == null) {
               uccommon.c.a(y, "获取短信数据失败，请改用其他手段支付");
               return false;
            }

            int var10 = Integer.parseInt(var8.get("respCode").toString());
            if (var8 != null && !var8.equals("") && var10 >= 200 && var10 <= 299) {
               if (!(var2 = new String((byte[])var8.get("data"), "UTF-8")).startsWith("-102")) {
                  var9 = (byte[])var8.get("data");
                  break label72;
               }

               if (a(var2, 2, "#").equals("response_null")) {
                  uccommon.c.a(y, "对不起，您所在的地区没有开通短信付费通道，请使用U点进行支付！");
                  return false;
               }

               uccommon.c.a(y, "获取短信数据失败，请改用其他手段支付");
               return false;
            }

            uccommon.c.a(y, "获取短信数据失败，请改用其他手段支付");
            return false;
         }
      } catch (Exception var6) {
         uccommon.c.a(y, "获取短信数据失败");
         return false;
      }

      try {
         var2 = new String(var9, "UTF-8");
      } catch (Exception var5) {
         var2 = new String(var9);
      }

      if (var2.length() > 2) {
         at = var2;
         av = var2.substring(0, at.indexOf("^&*")).substring(at.lastIndexOf(44) + 1);
         au = at.substring(at.indexOf("^&*") + 3);
         ByteArrayOutputStream var11 = new ByteArrayOutputStream();
         DataOutputStream var13 = new DataOutputStream(var11);

         try {
            var13.writeUTF(at);
            var13.writeUTF(av);
            var13.writeUTF(au);
            byte[] var12 = var11.toByteArray();
            a(4, var12, "upg_api_private");
            var13.close();
            var11.close();
         } catch (Exception var4) {
            var4.printStackTrace();
            aE = aF;
         }

         y = null;
         return true;
      } else {
         return false;
      }
   }

   private boolean f() {
      if (y == null) {
         y = uccommon.c.a("n", g, h, j);
      }

      uccommon.c.a(y, a(32, 0));
      String var1 = this.ae;
      StringBuffer var2;
      (var2 = new StringBuffer()).append("cpid=" + this.Y);
      var2.append("&gameid=" + this.q);
      var2.append("&u_money=" + this.V);
      e var6 = new e(var1, Q, var2.toString(), aO, aZ);

      byte[] var8;
      try {
         Hashtable var7;
         if ((var7 = uccommon.e.a(var6, Z, "")) == null) {
            uccommon.c.a(y, "获取U点支付信息失败！");
            return false;
         }

         int var9 = Integer.parseInt(var7.get("respCode").toString());
         if (var7 == null || var7.equals("") || var9 < 200 || var9 > 299) {
            uccommon.c.a(y, "获取U点支付信息失败！");
            return false;
         }

         new String((byte[])var7.get("data"), "UTF-8");
         var8 = (byte[])var7.get("data");
      } catch (Exception var5) {
         uccommon.c.a(y, "获取U点支付信息失败！");
         return false;
      }

      String var10;
      try {
         var10 = new String(var8, "UTF-8");
      } catch (Exception var4) {
         var10 = new String(var8);
      }

      if (var10.length() > 2) {
         y = null;

         try {
            if (a(var10, 2, "#").equals("none")) {
               this.aS = "无折扣";
               this.aT = this.V + " " + aI;
            } else {
               this.aS = a(var10, 2, "#");
               this.aT = a(var10, 4, "#");
            }

            a(var10, 3, "#");
         } catch (Exception var3) {
            uccommon.c.a(y, "获取U点支付信息失败！");
            return false;
         }

         this.aU = true;
         return true;
      } else {
         uccommon.c.a(y, "获取U点支付信息失败！");
         return false;
      }
   }

   private static void a(RecordStore var0) {
      if (var0 != null) {
         try {
            var0.closeRecordStore();
         } catch (Exception var1) {
            var1.printStackTrace();
         }
      }
   }

   private static byte[] a(int var0, byte[] var1, String var2) {
      byte[] var3 = null;

      try {
         RecordStore var5 = RecordStore.openRecordStore(var2, false);
         if (var1 == null) {
            var3 = new byte[var5.getRecordSize(var0)];
            var5.getRecord(var0, var3, 0);
         } else {
            var5.setRecord(var0, var1, 0, var1.length);
         }

         a(var5);
         return var3;
      } catch (Exception var4) {
         return null;
      }
   }

   private void g() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      DataOutputStream var2 = new DataOutputStream(var1);
      int var3 = this.am.size();
      Enumeration var4 = this.am.keys();

      try {
         var2.writeInt(var3);
         var2.writeInt(this.aC);

         byte[] var7;
         while(var4.hasMoreElements()) {
            String var5 = (String)var4.nextElement();
            var7 = (byte[])this.am.get(var5);
            var2.writeUTF(var5);
            var2.writeInt(var7.length);
            var2.write(var7);
         }

         var7 = var1.toByteArray();
         a(3, var7, "upg_api_private");
         var2.close();
         var1.close();
      } catch (Exception var6) {
         var6.printStackTrace();
         aE = aF;
      }
   }

   private static void h() {
      ByteArrayOutputStream var0 = new ByteArrayOutputStream();
      DataOutputStream var1 = new DataOutputStream(var0);

      try {
         var1.writeUTF("");
         var1.writeUTF(av);
         var1.writeUTF("");
         byte[] var2 = var0.toByteArray();
         a(4, var2, "upg_api_private");
         var1.close();
         var0.close();
      } catch (Exception var3) {
         var3.printStackTrace();
         aE = aF;
      }
   }

   private void i() {
      byte[] var1 = a(3, (byte[])null, "upg_api_private");
      ByteArrayInputStream var8 = new ByteArrayInputStream(var1);

      try {
         DataInputStream var2;
         int var3 = (var2 = new DataInputStream(var8)).readInt();
         this.aC = var2.readInt();

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2.readUTF();
            byte[] var6 = new byte[var2.readInt()];
            var2.readFully(var6);
            this.am.put(var5, var6);
         }

         var2.close();
         var8.close();
      } catch (Exception var7) {
         var7.printStackTrace();
      }
   }

   private boolean a(String var1) {
      byte[] var4;
      if ((var4 = (byte[])this.am.get(var1)) == null) {
         return false;
      } else {
         ByteArrayInputStream var5 = new ByteArrayInputStream(var4);
         DataInputStream var2 = new DataInputStream(var5);

         try {
            this.an = var2.readUTF();
            this.ao = var2.readUTF();
            this.aA = var2.readInt();
            this.aB = var2.readInt();
            this.az = var2.readInt();
            this.as = var2.readUTF();
            ai.put("addtimes", var2.readUTF());
            var2.close();
            var5.close();
            return true;
         } catch (Exception var3) {
            var3.printStackTrace();
            return false;
         }
      }
   }

   private static void j() {
      ByteArrayOutputStream var0 = new ByteArrayOutputStream();
      DataOutputStream var1 = new DataOutputStream(var0);
      int var2 = al.size();
      Enumeration var3 = al.keys();

      try {
         var1.writeInt(var2);

         while(var3.hasMoreElements()) {
            String var6;
            int var4 = b(var6 = (String)var3.nextElement());
            var1.writeUTF(var6);
            var1.writeInt(var4);
         }

         byte[] var7 = var0.toByteArray();
         a(1, var7, "upg_api_private");
         var1.close();
         var0.close();
      } catch (Exception var5) {
         var5.printStackTrace();
         aE = aF;
      }
   }

   private static int b(String var0) {
      return !(var0 = String.valueOf(al.get(var0))).equals("null") ? Integer.parseInt(var0) : 0;
   }

   private static String k() {
      if (ak != null && !ak.equals("")) {
         return ak;
      } else {
         boolean var0 = true;
         long var1 = System.currentTimeMillis();
         long var3 = 1L;

         for(int var5 = 0; var5 < 8; ++var5) {
            var3 *= 10L;
         }

         String var7;
         for(var7 = String.valueOf(a(var3)); var7.length() < 8; var7 = var7 + a(10L)) {
         }

         ak = var1 + var7;
         a(F.get("c").toString(), G.get("c").toString(), Z);
         return ak;
      }
   }

   private static void l() {
      byte[] var0 = a(1, (byte[])null, "upg_api_private");
      ByteArrayInputStream var7 = new ByteArrayInputStream(var0);

      try {
         DataInputStream var1;
         int var2 = (var1 = new DataInputStream(var7)).readInt();

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4 = var1.readUTF();
            int var5 = var1.readInt();
            al.put(var4, new Integer(var5));
         }

         var1.close();
         var7.close();
      } catch (Exception var6) {
         var6.printStackTrace();
      }
   }

   public static void updatePaidMoney(String var0, int var1) {
      al.put(var0, new Integer(var1));
      j();
   }

   private static long a(long var0) {
      if (aY == null) {
         aY = new Random();
      }

      return (aY.nextLong() >>> 1) % var0;
   }

   private static String a(int var0) {
      String var1;
      for(var1 = ""; var1.length() < var0; var1 = var1 + a(10L)) {
      }

      if (var1.length() > var0) {
         var1.substring(0, var0);
      }

      return var1;
   }

   private static void a(String var0, String var1, boolean var2) {
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      DataOutputStream var4 = new DataOutputStream(var3);
      Z = var2;

      try {
         if (var0 == null) {
            var0 = "";
         }

         if (var1 == null) {
            var1 = "";
         }

         if (ak == null) {
            ak = "";
         }

         var4.writeUTF(var0);
         if (J.get("z") == "y") {
            var4.writeUTF(var1);
         } else {
            var4.writeUTF("");
         }

         var4.writeUTF(ak);
         byte[] var6 = var3.toByteArray();
         a(2, var6, "upg_api_private");
         var4.close();
         var3.close();
      } catch (Exception var5) {
         var5.printStackTrace();
         aE = aF;
      }
   }

   private static String[] m() {
      String[] var0 = new String[2];
      ByteArrayInputStream var1 = new ByteArrayInputStream(a(2, (byte[])null, "upg_api_private"));
      DataInputStream var2 = new DataInputStream(var1);

      try {
         var0[0] = var2.readUTF();
         var0[1] = var2.readUTF();
         var0[2] = var2.readUTF();
         var2.close();
         var1.close();
      } catch (Exception var3) {
      }

      return var0;
   }

   public void commandAction(Command var1, Displayable var2) {
      if (var2 == this.L) {
         if (this.L != null) {
            if (var1 == this.N || var1 == this.M || var1 == null) {
               this.O = var1;
               this.K = false;
            }
         }
      }
   }

   protected void pointerPressed(int var1, int var2) {
      int var6;
      int var7;
      int var8;
      int var9;
      if (y != null) {
         String var13 = y.get("a").toString();
         if (y.get("a").toString() == "a") {
            String[] var12 = (String[])y.get("c");
            var6 = Integer.parseInt(y.get("r").toString());
            var7 = Integer.parseInt(y.get("q").toString());
            var8 = Integer.parseInt(y.get("d").toString());
            var9 = Integer.parseInt(y.get("m").toString());
            int var14 = var7 / var12.length;
            int var11;
            if (var1 >= var8 && var1 <= var8 + var6 && var2 >= var9 && var2 <= var9 + var7) {
               for(var11 = 0; var11 < var12.length; ++var11) {
                  if (var2 > var9 + var14 * var11 && var2 < var9 + var14 * (var11 + 1)) {
                     this.a(((String[])y.get("e"))[var11], ((String[])y.get("f"))[var11]);
                     y = null;
                     break;
                  }
               }
            }

            var11 = Integer.parseInt(y.get("t").toString());
            if (var2 >= h - j && var1 <= g / 3) {
               this.a(((String[])y.get("e"))[var11], ((String[])y.get("f"))[var11]);
               y = null;
               return;
            }

            if (var2 >= h - j && var1 >= g - g / 3) {
               this.a("u", "");
               y = null;
            }
         }

         if (var13 == "b" && y.get("v").toString() == "y") {
            y = null;
         }

      } else {
         int var4 = -1;
         PayPlatform var3 = this;
         if (this.t != null) {
            for(var9 = 0; var9 < this.t.size(); ++var9) {
               Hashtable var10;
               var7 = Integer.parseInt((var10 = (Hashtable)var3.t.elementAt(var9)).get("d").toString());
               var8 = Integer.parseInt(var10.get("m").toString());
               int var5 = Integer.parseInt(var10.get("r").toString());
               var6 = Integer.parseInt(var10.get("q").toString());
               if (var1 > var7 && var1 < var7 + var5 && var2 > var8 && var2 < var8 + var6) {
                  var4 = var9;
                  break;
               }
            }
         }

         if (var4 >= 0 && var2 < h - j) {
            this.r = (Hashtable)this.s.elementAt(var4);
            this.u = 3;
         } else if (var2 >= h - j && var1 <= g / 3) {
            this.a(this.o.get("e").toString(), this.o.get("f").toString());
         } else {
            if (var2 >= h - j && var1 >= g - g / 3) {
               this.a(this.p.get("e").toString(), this.p.get("f").toString());
            }

         }
      }
   }

   protected void keyPressed(int var1) {
      int var2 = 0;

      try {
         var2 = this.getGameAction(var1);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      if ((var1 == -6 || var1 == -21 || var1 == 21 || var1 == e && e != 0) && var2 != 6 && var2 != 1 && var2 != 2 && var2 != 5) {
         var2 = 9;
      }

      if ((var1 == -7 || var1 == -22 || var1 == 22 || var1 == f && f != 0) && var2 != 6 && var2 != 1 && var2 != 2 && var2 != 5) {
         var2 = 10;
      }

      if (this.u != 4 && this.u != 5) {
         if (y != null) {
            String var5;
            if ((var5 = y.get("a").toString()) == "a") {
               var1 = ((String[])y.get("c")).length;
               int var3 = Integer.parseInt(y.get("t").toString());
               switch (var2) {
                  case 1:
                     if (var3 > 0) {
                        --var3;
                     }

                     y.put("t", String.valueOf(var3));
                     return;
                  case 6:
                     if (var3 < var1 - 1) {
                        ++var3;
                     }

                     y.put("t", String.valueOf(var3));
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                  case 7:
                  default:
                     return;
                  case 8:
                  case 9:
                     if (y.get("a") == "a") {
                        this.a(((String[])y.get("e"))[var3], ((String[])y.get("f"))[var3]);
                        return;
                     }

                     y = null;
                     return;
                  case 10:
                     y = null;
               }
            } else {
               if (var5 == "b" && y.get("v").toString() == "y" && (var2 == 9 || var2 == 10 || var2 == 8)) {
                  y = null;
               }

            }
         } else {
            switch (var2) {
               case 1:
                  this.u = 2;
               case 2:
               case 3:
               case 4:
               case 5:
               case 7:
               default:
                  break;
               case 6:
                  this.u = 1;
                  break;
               case 8:
                  this.u = 3;
                  break;
               case 9:
                  this.a(this.o.get("e").toString(), this.o.get("f").toString());
                  break;
               case 10:
                  this.a(this.p.get("e").toString(), this.p.get("f").toString());
            }

            this.v = System.currentTimeMillis();
         }
      }
   }

   private void a(String var1, String var2) {
      if (var1 == "p") {
         this.w = false;
      } else if (var1 == "o") {
         this.u = 3;
      } else {
         if (var1 == "r") {
            if (var2 == A) {
               var1 = "r";
               var2 = D;
               String var5 = var2;
               String var3 = "";
               var2 = "o";
               this.c();
               Vector var6;
               (var6 = new Vector()).addElement(uccommon.c.a("n", a(6, 1), 5, "", "", uccommon.c.c, "", uccommon.c.b, this.m[0], -1, -1, uccommon.c.d, 0));
               this.n.addElement(uccommon.c.a(var6, g, j, this.m[6], -1, "n", "", ""));
               (var6 = new Vector()).addElement(uccommon.c.a("n", a(8, 1) + "：", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.e, 0));
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               (var6 = new Vector()).addElement(F);
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               (var6 = new Vector()).addElement(uccommon.c.a("n", a(5, 1) + "：", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[1], -1, -1, uccommon.c.e, 0));
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               (var6 = new Vector()).addElement(G);
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               uccommon.c.a((Vector)this.n, 5, -1, g);
               (var6 = new Vector()).addElement(J);
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               uccommon.c.a((Vector)this.n, 5, -1, g);
               (var6 = new Vector()).addElement(uccommon.c.a("y", a(80, 1), 5, "m", String.valueOf(2), uccommon.c.c, "", uccommon.c.b, this.m[1], this.m[4], -1, uccommon.c.e, g - 10));
               this.n.addElement(uccommon.c.a(var6, g, j, -1, -1, "n", "", ""));
               uccommon.c.a((Vector)this.n, 20, this.m[2], g);
               (var6 = new Vector()).addElement(uccommon.c.a("n", "优视科技 版权所有", 5, "", "", uccommon.c.c, "", uccommon.c.a, this.m[4], -1, -1, uccommon.c.e, g - 10));
               this.n.addElement(uccommon.c.a(var6, g, j, this.m[2], -1, "n", "", ""));
               uccommon.c.a(this.o, a(0, 0), var2, var3);
               uccommon.c.a(this.p, a(1, 0), var1, var5);
               this.ba = false;
               this.u = 4;
               return;
            }

            if (var2 == C) {
               var1 = "r";
               var2 = A;
               this.a("o", "", var1, var2);
               return;
            }

            if (var2 == E) {
               var1 = "r";
               var2 = A;
               this.b("o", "", var1, var2);
               return;
            }

            if (var2 == D) {
               var1 = "r";
               var2 = z;
               this.c("o", "", var1, var2);
               return;
            }

            if (var2 == z) {
               this.d();
               return;
            }

            if (var2 == B) {
               y = null;
               this.o();
               return;
            }
         } else {
            if (var1 == "t") {
               y = null;
               int var7;
               if ((var7 = Integer.parseInt(var2)) == 1) {
                  this.P = (byte)Integer.parseInt("9");
                  return;
               }

               if (var7 == 2) {
                  this.P = (byte)Integer.parseInt("8");
                  return;
               }

               if (var7 == 3) {
                  this.aX = this.aV;
                  this.P = (byte)Integer.parseInt("10");
                  return;
               }

               if (var7 == 4) {
                  this.aX = this.aW;
                  this.P = (byte)Integer.parseInt("10");
                  return;
               }

               return;
            }

            if (var1 == "u") {
               y = null;
               return;
            }
         }

      }
   }

   private void a(Hashtable var1, int var2, int var3, int var4, int var5) {
      if (this.s != null) {
         if (this.u == 5 && var1 != null) {
            this.s.addElement(var1);
            this.t.addElement(uccommon.c.a(var4, var2, var5, var3));
         } else {
            if (this.r == null && this.s != null && this.s.size() > 0) {
               this.r = (Hashtable)this.s.elementAt(0);
            }

            if (System.currentTimeMillis() - this.v >= 50L) {
               PayPlatform var8 = this;
               int var10000;
               if (this.s != null && this.s.size() != 0) {
                  label139: {
                     for(var3 = 0; var3 < var8.s.size(); ++var3) {
                        if (var8.s.elementAt(var3) == var8.r) {
                           var10000 = var3;
                           break label139;
                        }
                     }

                     var10000 = -1;
                  }
               } else {
                  var10000 = -1;
               }

               var2 = var10000;
               if (this.u == 1) {
                  if (var2 < 0) {
                     if (this.k + this.x > h - j) {
                        this.x -= j;
                     }

                     this.u = 4;
                  } else {
                     if (var2 < this.s.size() - 1) {
                        this.r = (Hashtable)this.s.elementAt(var2 + 1);
                        this.u = 4;
                     }

                     if (var2 == this.s.size() - 1) {
                        if (this.k + this.x > h - j) {
                           this.x -= j;
                        }

                        this.u = 4;
                     }

                  }
               } else if (this.u == 2) {
                  if (var2 < 0) {
                     if (this.x < 0) {
                        this.x += j;
                     }

                     this.u = 4;
                  } else {
                     if (var2 == 0) {
                        if (this.x < 0) {
                           this.x += j;
                        }

                        this.u = 4;
                     }

                     if (var2 > 0) {
                        this.r = (Hashtable)this.s.elementAt(var2 - 1);
                        this.u = 4;
                     }

                  }
               } else if (this.r != null && this.r == var1) {
                  if (this.u == 3) {
                     String var9 = this.r.get("e").toString();
                     String var10 = this.r.get("f").toString();
                     if (var9 != "q") {
                        if (var9 == "n") {
                           var4 = Integer.parseInt(this.r.get("n").toString());
                           String var6 = this.r.get("s").toString();
                           var9 = this.r.get("c").toString();
                           TextBox var7;
                           (var7 = new TextBox(var6, var9, var4, 0)).addCommand(this.M);
                           var7.addCommand(this.N);
                           var7.setCommandListener(this);
                           this.L = var7;
                           this.u = -1;
                           this.P = 1;
                           return;
                        }

                        if (var9 == "c") {
                           if (var1.get("z") == "y") {
                              var1.put("z", "n");
                              this.u = -1;
                              return;
                           }

                           var1.put("z", "y");
                           this.u = -1;
                           return;
                        }

                        if (var9 == "m") {
                           this.u = -1;
                           this.P = (byte)Integer.parseInt(var10);
                           return;
                        }

                        if (var9 == "p") {
                           this.w = false;
                           return;
                        }

                        if (var9 == "t") {
                           y = null;
                           this.u = -1;
                           this.a("t", var10);
                           return;
                        }

                        if (var9 == "r") {
                           this.a("r", var10);
                           return;
                        }
                     }

                     this.u = 4;
                  }

               }
            }
         }
      }
   }

   private int b(int var1) {
      String var2 = "";
      if (y == null) {
         y = uccommon.c.a("n", g, h, j);
      }

      if (!F.get("c").equals("") && !G.get("c").equals("")) {
         if (e(F.get("c").toString()) && e(G.get("c").toString())) {
            if (!f(F.get("c").toString())) {
               uccommon.c.a(y, "错误：请输入由数字组成的账号/手机号。");
               return -1;
            } else {
               if (var1 == 1) {
                  if (H.get("c").equals("")) {
                     uccommon.c.a(y, a(23, 0));
                     return -1;
                  }

                  if (!e(H.get("c").toString())) {
                     uccommon.c.a(y, "错误：支付密码中含有非法字符，请检查！");
                     return -1;
                  }
               }

               String var3;
               int var4;
               if ((var4 = c(var3 = this.a(F.get("c").toString(), G.get("c").toString(), var1 == 0 ? "" : H.get("c").toString(), var2, 0))) == -905 || var4 == -902) {
                  var4 = c(var3 = this.a(F.get("c").toString(), G.get("c").toString(), var1 == 0 ? "" : H.get("c").toString(), var2, 1));
               }

               if (var4 == -102 && a(var3, 2, "#").equals("uc_tm_modify")) {
                  var2 = a(var3, 3, "#");
                  var4 = c(var3 = this.a(F.get("c").toString(), G.get("c").toString(), var1 == 0 ? "" : H.get("c").toString(), var2, 1));
               }

               String var5;
               if (var4 < 0) {
                  var5 = a(var3, 2, "#");
                  var2 = a(var3, 3, "#");
                  if (var4 == -102) {
                     if (var5.equals("uc_password_error")) {
                        uccommon.c.a(y, "您的账号或密码错误，请重新输入。");
                        this.a("r", A);
                        return -1;
                     } else if (var5.equals("um_system_error")) {
                        uccommon.c.a(y, "系统错误，支付失败，U点未被扣除，请重试或更换其他支付方式！");
                        aQ = "";
                        this.d();
                        return -1;
                     } else if (var5.equals("um_account_error")) {
                        uccommon.c.a(y, "您的账户余额不足，无法完成本次支付，请用UC浏览器登录UC充值中心(pay.uc.cn)为U点账户充值。");
                        this.d();
                        return -1;
                     } else if (var5.equals("um_paypwd_error")) {
                        if (this.ba) {
                           uccommon.c.a(y, "支付失败，原因：" + var2);
                        } else {
                           y = null;
                           this.a("r", C);
                        }

                        return -1;
                     } else {
                        uccommon.c.a(y, "支付失败，原因：" + var2);
                        this.d();
                        return -1;
                     }
                  } else {
                     uccommon.c.a(y, "网络繁忙，连接失败，本次支付不成功，请返回尝试重新支付。");
                     this.d();
                     return var4;
                  }
               } else {
                  var5 = a(var3, 2, "#");
                  if (var4 == 200 && var5.equals("succ")) {
                     this.W = a(var3, 3, "#");
                     this.X = a(var3, 4, "#");
                     a(F.get("c").toString(), G.get("c").toString(), Z);
                     if (J.get("z") != "y") {
                        G.put("c", "");
                     }

                     aQ = "";
                     this.ba = false;
                     return var4;
                  } else {
                     this.d();
                     return -1;
                  }
               }
            }
         } else {
            uccommon.c.a(y, "错误：您输入的账号密码中含有非法字符，请检查！");
            return -1;
         }
      } else {
         uccommon.c.a(y, a(23, 0));
         return -1;
      }
   }

   private String a(String var1, String var2, String var3, String var4, int var5) {
      if (var1 != null && var2 != null) {
         a var6 = new a();
         String var7 = this.ab;
         String var8 = k();
         String var9 = this.q;
         String var13 = this.Y;
         if (aQ == null || aQ.equals("")) {
            aQ = var13 + var9 + var1 + a(10);
         }

         var9 = aQ;
         String var10 = String.valueOf(e(this.V));
         String var11 = (var8.length() <= 5 ? var8 : var8.substring(0, 5)) + (this.Y.length() <= 5 ? this.Y : this.Y.substring(0, 5)) + (this.q.length() <= 5 ? this.q : this.q.substring(0, 5)) + this.V;
         var11 = var6.a(var11);
         var4 = var4.equals("") ? a(false) : var4;
         String var12 = var4 + "`" + var1 + "`" + var2;
         var13 = new String(uccommon.a.b("ucweb#20100518").toUpperCase().substring(0, 10).getBytes());
         var2 = uccommon.d.a(var12, var13);
         var12 = "";
         if (var3 != null && !var3.equals("")) {
            var12 = var6.a(var6.a(var1 + var3).toUpperCase() + var4);
         }

         StringBuffer var20;
         (var20 = new StringBuffer()).append("user_id=" + var8);
         var20.append("&cpid=" + this.Y);
         var20.append("&gameid=" + this.q);
         var20.append("&optid=" + this.R);
         var20.append("&u_money=" + this.V);
         var20.append("&charge=" + var10);
         var20.append("&consume_id=" + var9);
         var20.append("&ucid=" + var1);
         var20.append("&sign=" + var11);
         var20.append("&uc_token=" + var2);
         var20.append("&pay_pwd=" + var12);
         var20.append("&consume_time=" + var4);
         var20.append("&tm=" + var4);
         var20.append("&eid=4");
         var20.append("&ch_code=" + this.ap);
         e var16 = new e(var7, Q, var20.toString(), aO, aZ);

         byte[] var18;
         try {
            if (var5 == 0) {
               uccommon.c.a(y, a(11, 0));
            } else {
               uccommon.c.a(y, "正在重试联网...");
            }

            Hashtable var17;
            if ((var17 = uccommon.e.a(var16, Z, "")) == null) {
               uccommon.c.a(y, a(39, 1) + ": -904 " + a(37, 1));
               return "-904#nullcontent";
            }

            int var19 = Integer.parseInt(var17.get("respCode").toString());
            if (var17 == null || var19 < 200 || var19 > 299) {
               return "-905#ResponseCode=" + var19;
            }

            var18 = (byte[])var17.get("data");
         } catch (Exception var15) {
            uccommon.c.a(y, "网络繁忙，连接失败，本次支付不成功，请返回尝试重新支付。");
            return "-902#network problem";
         }

         try {
            var2 = new String(var18, "UTF-8");
         } catch (Exception var14) {
            var2 = new String(var18);
         }

         return var2;
      } else {
         return "-1#" + a(29, 1);
      }
   }

   private boolean c(int var1) {
      uccommon.c.a(y = uccommon.c.a("n", g, h, j), a(31, 0));
      String var2 = this.ad;
      String var3 = k();
      if (aR == null || aR.equals("")) {
         aR = a(20);
      }

      String var4 = aR;
      String var5 = (new a()).a((var3.length() <= 5 ? var3 : var3.substring(0, 5)) + (this.Y.length() <= 5 ? this.Y : this.Y.substring(0, 5)) + (this.q.length() <= 5 ? this.q : this.q.substring(0, 5)) + e(this.T));
      StringBuffer var6;
      (var6 = new StringBuffer()).append("user_id=" + var3);
      var6.append("&cpid=" + this.Y);
      var6.append("&gameid=" + this.q);
      var6.append("&optid=" + this.R);
      var6.append("&consume_id=" + var4);
      var6.append("&charge=" + e(this.T));
      var6.append("&optobj=" + this.S);
      var6.append("&sms_channel=" + this.ay);
      var6.append("&sms_port=" + this.an);
      var6.append("&sms_content=" + this.ao);
      var6.append("&sms_type=" + var1);
      var6.append("&sendtime=" + a(false));
      var6.append("&sign=" + var5);
      e var9 = new e(var2, Q, var6.toString(), aO, aZ);

      byte[] var11;
      try {
         Hashtable var10;
         if ((var10 = uccommon.e.a(var9, Z, "")) == null) {
            return false;
         }

         int var12 = Integer.parseInt(var10.get("respCode").toString());
         if (var10 == null || var12 < 200 || var12 > 299) {
            return false;
         }

         var11 = (byte[])var10.get("data");
      } catch (Exception var8) {
         return false;
      }

      try {
         var2 = new String(var11, "UTF-8");
      } catch (Exception var7) {
         var2 = new String(var11);
      }

      if (c(var2) == 200) {
         aR = "";
         return true;
      } else {
         return false;
      }
   }

   private static String a(int var0, int var1) {
      String var2 = "";

      try {
         switch (var1) {
            case 0:
               var2 = ag[var0];
               break;
            case 1:
               var2 = ah[var0];
         }
      } catch (Exception var3) {
      }

      return var2;
   }

   private static String a(String var0, String var1, String var2) {
      if (var0 != null && !"".equals(var0) && var1 != null && !"".equals(var1)) {
         String var3 = "";

         int var4;
         int var5;
         for(var4 = 0; (var5 = var0.indexOf(var1, var4)) != -1; var4 = var5 + var1.length()) {
            var3 = var3 + var0.substring(var4, var5);
            var3 = var3 + var2;
         }

         if (var4 != var0.length()) {
            var3 = var3 + var0.substring(var4);
         }

         return var3;
      } else {
         return var0;
      }
   }

   private static String[] b(String var0, String var1) {
      if (var0 != null && var0.trim().length() != 0 && var1 != null) {
         if (!var0.equals("") && !var1.equals("")) {
            Vector var3;
            int var4;
            try {
               var3 = new Vector();
               var4 = 0;
               if (var0.indexOf(var1) == -1) {
                  return new String[]{var0};
               }
            } catch (Exception var5) {
               return null;
            }

            for(int var2 = var0.indexOf(var1); var2 < var0.length() && var2 != -1; var2 = var0.indexOf(var1, var2 + var1.length())) {
               String var7 = var0.substring(var4, var2);
               var3.addElement(var7);
               var4 = var2 + var1.length();
            }

            var3.addElement(var0.substring(var4));
            String[] var6 = new String[var3.size()];
            var3.copyInto(var6);
            return var6;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private static int a(String var0, Font var1) {
      char[] var5;
      int var2 = (var5 = var0.toCharArray()).length;
      int var3 = 0;

      for(int var4 = 0; var4 < var2; ++var4) {
         var3 += var1.charWidth(var5[var4]);
      }

      return var3;
   }

   private static String[] a(String var0, Font var1, int var2) {
      if (var0 != null && var1 != null) {
         StringBuffer var3 = new StringBuffer(var0);
         char[] var9;
         int var4 = (var9 = var0.toCharArray()).length;
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;

         for(int var8 = 0; var8 < var4; ++var8) {
            if (var9[var8] == '\n') {
               ++var6;
               var5 = 0;
            } else if ((var5 += var1.charWidth(var9[var8])) > var2) {
               var3.insert(var8 + var7, '\n');
               ++var6;
               ++var7;
               var5 = var1.charWidth(var9[var8]);
            }
         }

         ++var6;
         var3.append('\n');
         return a(var3.toString(), '\n', var6);
      } else {
         return new String[]{"BUG split: " + var0};
      }
   }

   private static String[] a(String var0, char var1, int var2) {
      int var3;
      if (var2 <= 0) {
         var1 = -1;

         for(var3 = -1; (var3 = var0.indexOf(10, var3 + 1)) >= 0; ++var1) {
         }

         if (var1 < 0) {
            return new String[]{var0};
         }
      }

      String[] var5 = new String[var2];

      for(var3 = 0; var3 < var2; ++var3) {
         int var4;
         if ((var4 = var0.indexOf(10)) == -1) {
            var5[var3] = var0;
         } else {
            var5[var3] = var0.substring(0, var4);
            var0 = var0.substring(var4 + 1);
         }
      }

      return var5;
   }

   private static String c(String var0, String var1) {
      String var10000 = var1;
      var1 = var0;
      var0 = var10000;
      var1 = var1;
      char[] var2 = new char[var0.length() / 4];
      String var3 = "";
      int var4 = 0;

      for(int var5 = 0; var4 < var0.length() / 4; ++var5) {
         if (var5 == var1.length()) {
            var5 = 0;
         }

         int var6 = Integer.parseInt(var0.substring(var4 << 2, (var4 << 2) + 4));
         var2[var4] = (char)((char)var6 ^ var1.charAt(var5));
         ++var4;
      }

      for(var4 = 0; var4 < var0.length() / 4; ++var4) {
         var3 = var3 + var2[var4];
      }

      return var3;
   }

   private static int c(String var0) {
      try {
         if (var0.equals("") || var0 == null) {
            return -110;
         }
      } catch (Exception var2) {
         return -110;
      }

      if ((var0 = a(var0, 1, "#")) == null) {
         return -110;
      } else {
         try {
            int var3 = Integer.parseInt(var0);
            return var3;
         } catch (Exception var1) {
            return -110;
         }
      }
   }

   private static String a(String var0, int var1, String var2) {
      if (var1 <= 0) {
         return null;
      } else if (var0.indexOf(var2) < 0) {
         return null;
      } else {
         String[] var3;
         if ((var3 = b(var0, var2)) == null) {
            return null;
         } else {
            return var1 > var3.length ? null : var3[var1 - 1];
         }
      }
   }

   private static String d(int var0) {
      a();
      String var1;
      if ((var1 = uccommon.c.a("locale", ai)).equals("")) {
         aE = aG;
         return "";
      } else {
         String[] var4;
         if ((var4 = b(var1, ",")).length < 3) {
            aE = aG;
            return "";
         } else {
            aH = var4;
            int var5 = Integer.parseInt(var4[2]);
            String var3;
            if (aH[1].equals("元")) {
               int var2 = var0 * var5 / 100;
               if ((var0 = var0 * var5 % 100) < 10) {
                  var3 = var2 + ".0" + aH[1];
               } else {
                  var3 = var2 + "." + var0 + aH[1];
               }
            } else {
               if (var0 * var5 % 100 >= 50) {
                  ++var5;
               }

               var3 = aH[1] + " " + var5;
            }

            return var3;
         }
      }
   }

   private static int e(int var0) {
      a();
      String var1;
      if ((var1 = uccommon.c.a("locale", ai)).equals("")) {
         aE = aG;
         return 0;
      } else {
         String[] var2;
         if ((var2 = b(var1, ",")).length < 3) {
            aE = aG;
            return 0;
         } else {
            aH = var2;
            int var3 = Integer.parseInt(var2[2]);
            return var0 * var3 / 100;
         }
      }
   }

   private static String d(String var0) {
      String var1 = "";
      InputStream var2 = null;
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      DataOutputStream var4 = new DataOutputStream(var3);
      byte[] var5 = new byte[1024];

      try {
         if ((var2 = "".getClass().getResourceAsStream(var0)) != null) {
            int var7;
            while((var7 = var2.read(var5)) != -1) {
               var4.write(var5, 0, var7);
            }

            var1 = new String(var3.toByteArray(), "UTF-8");
         }

         a(var2);
         a((OutputStream)var4);
         a((OutputStream)var3);
      } catch (Exception var6) {
         a(var2);
         a((OutputStream)var4);
         a((OutputStream)var3);
      }

      return var1;
   }

   private static void a(InputStream var0) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (Exception var1) {
            var1.printStackTrace();
         }
      }
   }

   private static void a(OutputStream var0) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (Exception var1) {
            var1.printStackTrace();
         }
      }
   }

   private static boolean a(String var0, String var1, int var2) {
      if (var0 != null && !"".equals(var0.trim())) {
         if (var1 != null && !"".equals(var1.trim())) {
            var0 = "sms://" + var0;
            MessageConnection var3 = null;
            boolean var4 = false;
            long var6 = System.currentTimeMillis();

            try {
               TextMessage var5;
               (var5 = (TextMessage)(var3 = (MessageConnection)Connector.open(var0)).newMessage("text")).setAddress(var0);
               var5.setPayloadText(var1);
               var3.send(var5);

               try {
                  var3.send((Message)null);
               } catch (Exception var12) {
                  if (System.currentTimeMillis() - var6 > (long)var2) {
                     var4 = true;
                  }
               }
            } catch (Exception var13) {
               var13.printStackTrace();
               var4 = false;
            }

            try {
               var3.close();
            } catch (Exception var11) {
               var11.printStackTrace();
            }

            return var4;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void n() {
      Vector var1 = new Vector();
      String var2;
      if (this.T > 0 && this.T < this.V) {
         var2 = a(8, 0) + "，" + a(10, 0);
         var1.addElement(a(a(a(var2, "$(str3)", this.as), "$(str2)", String.valueOf(this.az - this.aB)), "$(str1)", String.valueOf(this.aB)) + au);
      } else {
         var2 = a(9, 0) + "，" + a(10, 0);
         var1.addElement(a(a(a(var2, "$(str2)", this.as), "$(str1)", String.valueOf(this.az)), "$(str3)", d(this.V)) + au);
      }

      String[] var6 = new String[]{a(20, 0)};
      Vector[] var5 = new Vector[]{var1};
      String var3 = "r";
      String var4 = z;
      this.a(var6, var5, a(0, 0), "o", "", a(1, 0), var3, var4, a(0, 0), "m", String.valueOf(4));
   }

   private void o() {
      String var2 = "";
      if (av == null || av.equals("")) {
         av = "20100101";
      }

      String var10000 = av;
      String var3 = a(true);
      String var1 = var10000;
      int var4 = Integer.parseInt(var3.substring(0, 4));
      int var5 = Integer.parseInt(var3.substring(4, 6));
      int var22 = Integer.parseInt(var3.substring(6, 8));
      long var15 = a(var4, var5, var22);
      var4 = Integer.parseInt(var1.substring(0, 4));
      var5 = Integer.parseInt(var1.substring(4, 6));
      var22 = Integer.parseInt(var1.substring(6, 8));
      long var17 = a(var4, var5, var22);
      if ((int)((var15 - var17) / 1000L / 60L / 60L / 24L) > this.aw && !this.e()) {
         y.put("v", "y");
         this.d();
      } else if (at != null && !at.equals("") && at.indexOf(",") > 0) {
         String[] var21;
         if (at.indexOf("^|*") > 0) {
            var3 = (var21 = b(at, "^|*"))[0];
            var4 = 500;
            var5 = 500;

            for(int var6 = 0; var6 < var21.length; ++var6) {
               int var7 = e(this.V);
               int var8;
               if ((var8 = Integer.parseInt(b(var21[var6], ",")[4])) < var5) {
                  var5 = var8;
                  var3 = var21[var6];
               }

               if (var7 / var8 < var4 && var7 / var8 != 0) {
                  var4 = var7 / var8;
                  var2 = var21[var6];
               }
            }

            if (var4 == 500) {
               var2 = var3;
            }

            var21 = b(var2, ",");
         } else {
            var21 = b(at, ",");
         }

         if (var21 != null && var21.length >= 2) {
            this.as = var21[4];
            this.aA = Integer.parseInt(this.as) * 10;
            this.az = this.V / this.aA;
            this.ax = Integer.parseInt(var21[3]);
            if (this.V % this.aA > 0) {
               ++this.az;
            }

            this.ay = var21[0];
            this.an = var21[1];
            this.ao = var21[2];
            if (this.ax == 0) {
               this.ao = this.ao + this.Y;
               this.ao = this.ao + this.q;
               this.ao = this.ao + this.R;
               this.ao = this.ao + this.aq;
               this.ao = this.ao + this.ap;
               this.ao = this.ao + this.ar;
            }

            if (this.ax == 1) {
               if (!this.c(0)) {
                  y.put("v", "y");
                  this.d();
                  return;
               }

               y.put("v", "y");
               y = null;
            }

            this.n();
         } else {
            uccommon.c.a(y, "获取短信数据失败，请改用U点支付");
            y.put("v", "y");
            this.d();
         }
      } else {
         uccommon.c.a(y, "获取短信数据失败，请改用U点支付");
         y.put("v", "y");
         this.d();
      }
   }

   private static String a(boolean var0) {
      Calendar var1;
      (var1 = Calendar.getInstance(TimeZone.getDefault())).setTime(new Date());
      int var2 = var1.get(1);
      int var3 = var1.get(2) + 1;
      int var4 = var1.get(5);
      int var5 = var1.get(11);
      int var6 = var1.get(12);
      int var7 = var1.get(13);
      String var12;
      if ((var12 = String.valueOf(var5)).length() < 2) {
         var12 = "0" + var12;
      }

      String var13;
      if ((var13 = String.valueOf(var6)).length() < 2) {
         var13 = "0" + var13;
      }

      String var8;
      if ((var8 = String.valueOf(var7)).length() < 2) {
         var8 = "0" + var8;
      }

      String var9 = String.valueOf(var2);
      String var10;
      if ((var10 = String.valueOf(var3)).length() < 2) {
         var10 = "0" + var10;
      }

      String var11;
      if ((var11 = String.valueOf(var4)).length() < 2) {
         var11 = "0" + var11;
      }

      return var0 ? var9 + var10 + var11 : var9 + var10 + var11 + var12 + var13 + var8;
   }

   private static long a(int var0, int var1, int var2) {
      Calendar var3;
      (var3 = Calendar.getInstance(TimeZone.getDefault())).set(1, var0);
      var3.set(2, var1 - 1);
      var3.set(5, var2);
      var3.set(11, 0);
      var3.set(12, 0);
      var3.set(13, 0);
      var3.set(14, 0);
      return var3.getTime().getTime();
   }

   private static boolean e(String var0) {
      boolean var1 = true;

      try {
         char[] var6;
         int var2 = (var6 = var0.toCharArray()).length;

         for(int var3 = 0; var3 < var2; ++var3) {
            char var4;
            if (((var4 = var6[var3]) < 'a' || var4 > 'z') && (var4 < 'A' || var4 > 'Z') && (var4 < '0' || var4 > '9')) {
               var1 = false;
               break;
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         var1 = false;
      }

      return var1;
   }

   private static boolean f(String var0) {
      boolean var1 = true;

      try {
         char[] var6;
         int var2 = (var6 = var0.toCharArray()).length;

         for(int var3 = 0; var3 < var2; ++var3) {
            char var4;
            if ((var4 = var6[var3]) < '0' || var4 > '9') {
               var1 = false;
               break;
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         var1 = false;
      }

      return var1;
   }

   public static boolean launchPay(MIDlet var0, Displayable var1, String var2, String var3, String var4, String var5, String var6, int var7) {
      if (b(var5) < var7) {
         return true;
      } else if (var7 % 20 == 0 && var7 >= 20) {
         Display var8 = Display.getDisplay(var0);
         PayPlatform var11;
         PayPlatform var12 = var11 = a = new PayPlatform(var0, var4, 0, 0, 0, 0, var7);
         var11.w = true;
         var12.d = false;
         var12.R = var5;
         var12.S = var6;
         var12.q = var4;
         var12.U = var2;
         var12.Y = var3;
         var12.T = b(var5);
         var12.w = true;
         (new Thread(var12)).start();
         if (var12.T > 0 && var12.T < var12.V) {
            if (var12.a(var5)) {
               var12.n();
            } else {
               var12.d();
            }
         } else {
            var12.d();
         }

         var8.setCurrent(a);

         for(; var12.w; uccommon.c.a(33L)) {
            try {
               a.serviceRepaints();
               a.repaint();
            } catch (Exception var10) {
            }
         }

         var8.setCurrent(var1);
         return var12.d;
      } else {
         System.out.println("消费金额填写不正确！");
         return false;
      }
   }
}
