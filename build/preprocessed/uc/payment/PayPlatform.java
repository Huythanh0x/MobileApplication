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
    } catch (Exception exception) {
      aP = true;
    } 
    a();
  }
  
  private PayPlatform(MIDlet paramMIDlet, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
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
    setFullScreenMode(c);
    b = paramMIDlet;
    this.q = paramString;
    e = 0;
    f = 0;
    g = 0;
    h = 0;
    this.V = paramInt5;
    if (g == 0)
      g = getWidth(); 
    if (h == 0)
      h = getHeight(); 
    a();
    b();
    try {
      if (!aP) {
        i();
        String[] arrayOfString = m();
        F.put("c", arrayOfString[0]);
        G.put("c", arrayOfString[1]);
        ak = arrayOfString[2];
        return;
      } 
    } catch (Exception exception) {}
  }
  
  private static void a() {
    if (ai == null || ag == null || ah == null) {
      ai = new Hashtable();
      Vector vector1 = new Vector();
      Vector vector2 = new Vector();
      String str;
      if ((str = d("/APISetting.ini")) != null && !str.equals("")) {
        String[] arrayOfString = b(str, "\r\n");
        byte b1 = 0;
        for (byte b2 = 0; b2 < arrayOfString.length; b2++) {
          String str1;
          if ((str1 = arrayOfString[b2]) != null)
            if (str1.indexOf("[Settings]") >= 0) {
              b1 = 2;
            } else if (str1.indexOf("[Language1]") >= 0) {
              b1 = 3;
            } else if (str1.indexOf("[Language2]") >= 0) {
              b1 = 5;
            } else {
              int i;
              switch (b1) {
                case 2:
                  if (!str1.equals("") && (i = str1.indexOf("=")) >= 0) {
                    String str2 = str1.substring(0, i);
                    str1 = str1.substring(i + 1, str1.length());
                    ai.put(str2, str1);
                  } 
                  break;
                case 3:
                  vector1.addElement(str1);
                  break;
                case 5:
                  vector2.addElement(str1);
                  break;
              } 
            }  
        } 
      } 
      ag = new String[vector1.size()];
      vector1.copyInto((Object[])ag);
      ah = new String[vector2.size()];
      vector2.copyInto((Object[])ah);
      try {
        if ((aN = System.getProperty("wireless.messaging.sms.smsc").trim()).length() > 11 && aN.indexOf("13") >= 0)
          aN = aN.substring(aN.indexOf("13")); 
        return;
      } catch (Exception exception) {
        aN = "";
      } 
    } 
  }
  
  private void b() {
    this.aB = 0;
    this.P = 0;
    this.d = false;
    try {
      byte[] arrayOfByte = a(4, (byte[])null, "upg_api_private");
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
      DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
      try {
        at = dataInputStream.readUTF();
        av = dataInputStream.readUTF();
        au = dataInputStream.readUTF();
        dataInputStream.close();
        byteArrayInputStream.close();
      } catch (Exception exception) {
        (byteArrayInputStream = null).printStackTrace();
      } 
      this.aw = 0;
    } catch (Exception exception) {}
    if (at == null || at.equals(""))
      if (aN == null || aN.equals("") || aN.length() < 8) {
        this.aw = 0;
        av = "19000101";
      } else {
        String[] arrayOfString1 = b(c.a("smsclist", ai), "=#=");
        String[] arrayOfString2 = b(c.a("smsinfo", ai), "=#=");
        String[] arrayOfString3 = b(c.a("smstext", ai), "=#=");
        for (byte b1 = 0; b1 < arrayOfString1.length; b1++) {
          if ((at == null || at.equals("")) && arrayOfString1[b1].equals("all")) {
            at = arrayOfString2[b1];
            au = arrayOfString3[b1];
          } else {
            String[] arrayOfString4 = b(arrayOfString1[b1], ",");
            for (byte b2 = 0; b2 < arrayOfString4.length; b2++) {
              if (aN.startsWith(arrayOfString4[b2]) || aN.startsWith("+" + arrayOfString4[b2]) || aN.startsWith("+86" + arrayOfString4[b2]) || aN.startsWith("86" + arrayOfString4[b2]) || aN.startsWith("0" + arrayOfString4[b2])) {
                at = arrayOfString2[b1];
                au = arrayOfString3[b1];
              } 
            } 
          } 
        } 
        at = c("7D7026B0B3", at);
        if (c.a("smsendday", ai) != null && !c.a("smsendday", ai).equals("")) {
          this.aw = Integer.parseInt(c.a("smsendday", ai));
        } else {
          this.aw = 7;
        } 
      }  
    String str = c.a("urlpath", ai);
    aa = "http://" + c("7D7026B0B3", str);
    this.ab = String.valueOf(aa) + c("7D7026B0B3", c.a("upointpayUrl", ai));
    this.ac = String.valueOf(aa) + c("7D7026B0B3", c.a("updatesmsUrl", ai));
    this.ad = String.valueOf(aa) + c("7D7026B0B3", c.a("payresultUrl", ai));
    this.ae = String.valueOf(aa) + c("7D7026B0B3", c.a("discountUrl", ai));
    this.aV = c.a("ydsmsport", ai);
    this.aW = c.a("ltsmsport", ai);
    this.af = Integer.parseInt(c.a("forcecharge", ai));
    aK = c.a("idType", ai);
    aJ = c.a("idName", ai);
    if (d(200).equals(""))
      aE = aG; 
    aZ = Integer.parseInt(c.a("conntimeout", ai)) * 1000;
    aI = c.a("coinName", ai);
    this.ap = c.a("launchsite", ai);
    this.aq = c.a("phonetype", ai);
    this.ar = c.a("backupcode", ai);
    this.m = new int[10];
    if (!c.a("colors", ai).equals("")) {
      String[] arrayOfString1 = b(c.a("colors", ai), ",");
      for (byte b1 = 0; b1 < arrayOfString1.length; b1++)
        this.m[b1] = Integer.parseInt(arrayOfString1[b1]); 
    } 
    String[] arrayOfString = b(c.a("payMethod", ai), ",");
    Vector vector1 = new Vector();
    Vector vector2 = new Vector();
    for (byte b = 0; b < arrayOfString.length; b++) {
      String str2 = "";
      int i = Integer.parseInt(arrayOfString[b]);
      String str1 = arrayOfString[b];
      switch (i) {
        case 1:
          str2 = a(a(1, 1), "$(str)", "");
          break;
        case 2:
          str2 = a(a(2, 1), "$(str)", "");
          break;
      } 
      vector1.addElement(str1);
      vector2.addElement(str2);
    } 
    this.aL = new String[vector1.size()];
    this.aM = new String[vector1.size()];
    vector1.copyInto((Object[])this.aL);
    vector2.copyInto((Object[])this.aM);
    try {
      this.aj = Integer.parseInt(c.a("payDefault", ai));
      Integer.parseInt(this.aL[this.aj]);
    } catch (Exception exception) {}
    this.i = c.a();
    j = c.b.getHeight() + 8;
    this.M = new Command(a(0, 0), 4, 0);
    this.N = new Command(a(1, 0), 2, 1);
    F = c.a("y", "", 5, "n", "", c.c, a(8, 1), c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "y");
    G = c.a("y", "", 5, "n", "", c.c, a(5, 1), c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
    H = c.a("y", "", 5, "n", "", c.c, a(63, 1), c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
    I = c.a("y", "", 5, "n", "", c.c, a(74, 1), c.a, this.m[1], this.m[0], this.m[1], g - 10, j, 60, "n");
    J = c.a("y", "", 5, "c", "", c.c, "savepassword", c.a, this.m[1], this.m[0], this.m[1], 14, 14);
    (aO = new Hashtable()).put("Content-Type", "application/x-www-form-urlencoded");
  }
  
  private void c() {
    this.s = null;
    this.t = null;
    this.r = null;
    this.x = 0;
    this.n = new Vector();
    Vector vector;
    (vector = new Vector()).addElement(c.a("n", this.i, 0, "", "", c.c, "", -1));
    this.n.addElement(c.a(vector, g, this.i.getHeight(), this.m[0], -1, "n", "", ""));
    c.a(this.n, 1, this.m[2], g);
  }
  
  private void a(String paramString1, String paramString2, String paramString3, String paramString4) {
    c();
    Vector vector;
    (vector = new Vector()).addElement(c.a("n", a(63, 1), 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[6], -1, "n", "", ""));
    c.a(this.n, 2, -1, g);
    String[] arrayOfString = a(a(64, 1), c.a, g - 10);
    for (byte b = 0; b < arrayOfString.length; b++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString[b], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    c.a(this.n, 2, this.m[2], g);
    (vector = new Vector()).addElement(c.a("n", "支付密码", 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.e, 0));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    (vector = new Vector()).addElement(H);
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.n, 5, -1, g);
    (vector = new Vector()).addElement(c.a("y", a(0, 0), 5, "m", String.valueOf(3), c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.o, a(0, 0), paramString1, paramString2);
    c.a(this.p, a(1, 0), paramString3, paramString4);
    this.ba = true;
    this.u = 4;
  }
  
  private void b(String paramString1, String paramString2, String paramString3, String paramString4) {
    c();
    Vector vector;
    (vector = new Vector()).addElement(c.a("n", a(71, 1), 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[6], -1, "n", "", ""));
    c.a(this.n, 2, -1, g);
    String[] arrayOfString = a(a(72, 1), c.a, g - 10);
    byte b;
    for (b = 0; b < arrayOfString.length; b++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString[b], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    (vector = new Vector()).addElement(I);
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.n, 5, -1, g);
    arrayOfString = a(a(73, 1), c.a, g - 10);
    for (b = 0; b < arrayOfString.length; b++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString[b], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    (vector = new Vector()).addElement(c.a("n", a(75, 1), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("y", a(77, 1), 5, "t", String.valueOf(3), c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("n", a(76, 1), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("y", a(77, 1), 5, "t", String.valueOf(4), c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.o, a(0, 0), paramString1, paramString2);
    c.a(this.p, a(1, 0), paramString3, paramString4);
    this.ba = true;
    this.u = 4;
  }
  
  private void c(String paramString1, String paramString2, String paramString3, String paramString4) {
    c();
    Vector vector;
    (vector = new Vector()).addElement(c.a("n", a(20, 0), 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[6], -1, "n", "", ""));
    c.a(this.n, 0, this.m[2], g);
    (vector = new Vector()).addElement(c.a("n", a(a(67, 1), "$(str)", (new StringBuffer(String.valueOf(this.V))).toString()), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("n", a(66, 1), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    vector.addElement(c.a("n", String.valueOf(this.aS) + "折", a(a(66, 1), c.a) + 5, "", "", c.c, "", c.b, this.m[6], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("n", a(68, 1), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    vector.addElement(c.a("n", String.valueOf(this.aT) + " U点", a(a(68, 1), c.a) + 5, "", "", c.c, "", c.b, this.m[6], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    (vector = new Vector()).addElement(c.a("n", a(69, 1), 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    c.a(this.n, 5, this.m[2], g);
    (vector = new Vector()).addElement(c.a("y", a(0, 0), 5, "r", A, c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.n, 10, this.m[2], g);
    String[] arrayOfString = a(a(70, 1), c.a, g - 10);
    for (byte b = 0; b < arrayOfString.length; b++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString[b], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    c.a(this.n, 5, this.m[2], g);
    (vector = new Vector()).addElement(c.a("n", "优视科技 版权所有", 5, "", "", c.c, "", c.a, this.m[4], -1, -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    c.a(this.o, a(0, 0), paramString1, paramString2);
    c.a(this.p, a(1, 0), paramString3, paramString4);
    this.ba = false;
    this.u = 4;
  }
  
  private void a(String[] paramArrayOfString, Vector[] paramArrayOfVector, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9) {
    c();
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      Vector vector1;
      (vector1 = new Vector()).addElement(c.a("n", paramArrayOfString[b], 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector1, g, j, this.m[6], -1, "n", "", ""));
      c.a(this.n, 2, this.m[2], g);
      for (byte b1 = 0; b1 < paramArrayOfVector[b].size(); b1++) {
        String[] arrayOfString = a(paramArrayOfVector[b].elementAt(b1), c.a, g - 10);
        for (byte b2 = 0; b2 < arrayOfString.length; b2++) {
          (vector1 = new Vector()).addElement(c.a("n", arrayOfString[b2], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
          this.n.addElement(c.a(vector1, g, j, this.m[2], -1, "n", "", ""));
        } 
      } 
      c.a(this.n, 2, this.m[2], g);
    } 
    Vector vector;
    (vector = new Vector()).addElement(c.a("y", paramString7, 0, paramString8, paramString9, c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g));
    this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
    c.a(this.n, 20, this.m[2], g);
    (vector = new Vector()).addElement(c.a("n", "优视科技 版权所有", 5, "", "", c.c, "", c.a, this.m[4], -1, -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    c.a(this.o, paramString1, paramString2, paramString3);
    c.a(this.p, paramString4, paramString5, paramString6);
    this.u = 4;
  }
  
  private void d() {
    c();
    Vector vector;
    (vector = new Vector()).addElement(c.a("n", a(20, 0), 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[6], -1, "n", "", ""));
    c.a(this.n, 0, this.m[2], g);
    String[] arrayOfString1 = a(this.U, c.a, g - 10);
    for (byte b2 = 0; b2 < arrayOfString1.length; b2++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString1[b2], 5, "", "", c.c, "", c.a, this.m[1], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    (vector = new Vector()).addElement(c.a("n", a(0, 1), 5, "", "", c.c, "", c.b, this.m[0], -1, -1, c.d, 0));
    this.n.addElement(c.a(vector, g, j, this.m[6], -1, "n", "", ""));
    String[] arrayOfString2 = a(a(65, 1), c.b, g - 10);
    byte b1;
    for (b1 = 0; b1 < arrayOfString2.length; b1++) {
      (vector = new Vector()).addElement(c.a("n", arrayOfString2[b1], 5, "", "", c.c, "", c.b, this.m[6], -1, -1, c.d, 0));
      this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    } 
    c.a(this.n, 5, this.m[2], g);
    for (b1 = 0; b1 < this.aL.length; b1++) {
      (vector = new Vector()).addElement(c.a("y", this.aM[b1], 0, "t", String.valueOf(this.aL[b1]), c.c, "", c.b, this.m[1], this.m[4], -1, c.e, g));
      this.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
      c.a(this.n, 2, this.m[2], g);
    } 
    c.a(this.n, 20, this.m[2], g);
    System.out.println("ver:1.0final");
    (vector = new Vector()).addElement(c.a("n", "优视科技 版权所有", 5, "", "", c.c, "", c.a, this.m[4], -1, -1, c.e, g - 10));
    this.n.addElement(c.a(vector, g, j, this.m[2], -1, "n", "", ""));
    c.a(this.o, a(0, 0), "o", "");
    c.a(this.p, a(1, 0), "p", "");
    this.u = 4;
  }
  
  protected void paint(Graphics paramGraphics) {
    paramGraphics.setColor(this.m[2]);
    paramGraphics.fillRect(0, 0, g, h);
    this.l = 0;
    if (this.s == null || this.u == 4) {
      this.s = new Vector();
      this.t = new Vector();
      this.u = 5;
    } 
    if (this.n != null)
      for (byte b = 0; b < this.n.size(); b++)
        a(paramGraphics, this.n.elementAt(b));  
    this.k = this.l;
    if (this.u == 5)
      this.u = -1; 
    c.a(paramGraphics, g, h, j, this.m, this.o.get("c").toString(), this.p.get("c").toString());
    String str;
    if (y != null && (str = y.get("a").toString()) == "b") {
      Vector vector1 = (Vector)y.get("c");
      Vector vector2 = new Vector();
      for (byte b = 0; b < vector1.size(); b++) {
        String[] arrayOfString = a(vector1.elementAt(b), c.a, Integer.parseInt(y.get("r").toString()));
        for (byte b1 = 0; b1 < arrayOfString.length; b1++)
          vector2.addElement(arrayOfString[b1]); 
      } 
      c.a(paramGraphics, y, j, this.m, vector2);
    } 
  }
  
  private void a(Graphics paramGraphics, Hashtable paramHashtable) {
    int i = Integer.parseInt(paramHashtable.get("q").toString());
    c.a(paramGraphics, paramHashtable, this.l, this.x);
    Vector vector;
    if (this.l + this.x < h && this.l + this.x + i > 0 && (vector = (Vector)paramHashtable.get("c")) != null)
      for (byte b = 0; b < vector.size(); b++) {
        Hashtable hashtable;
        String str;
        if ((str = (hashtable = vector.elementAt(b)).get("a").toString()) == "2")
          c.a(paramGraphics, hashtable, i, this.l, this.x); 
        if (str == "5")
          c.b(paramGraphics, hashtable, i, this.l, this.x); 
        Object[] arrayOfObject;
        if (str == "1" && (arrayOfObject = c.a(paramGraphics, hashtable, i, this.r, this.l, this.x, this.m)) != null)
          a((Hashtable)arrayOfObject[0], Integer.parseInt(arrayOfObject[1].toString()), Integer.parseInt(arrayOfObject[2].toString()), Integer.parseInt(arrayOfObject[3].toString()), Integer.parseInt(arrayOfObject[4].toString())); 
        if (str == "3" && (arrayOfObject = c.a(paramGraphics, hashtable, i, j, this.r, this.l, this.x, g, h, this.m)) != null)
          a((Hashtable)arrayOfObject[0], Integer.parseInt(arrayOfObject[1].toString()), Integer.parseInt(arrayOfObject[2].toString()), Integer.parseInt(arrayOfObject[3].toString()), Integer.parseInt(arrayOfObject[4].toString())); 
        if (str == "6" && (arrayOfObject = c.a(paramGraphics, hashtable, i, j, this.r, this.l, this.x, g, this.m)) != null)
          a((Hashtable)arrayOfObject[0], Integer.parseInt(arrayOfObject[1].toString()), Integer.parseInt(arrayOfObject[2].toString()), Integer.parseInt(arrayOfObject[3].toString()), Integer.parseInt(arrayOfObject[4].toString())); 
      }  
    this.l += i;
  }
  
  public void run() {
    if (aP) {
      long l = System.currentTimeMillis();
      c.a(y = c.a("n", g, h, j), "正在初始化数据，请稍等");
      RecordStore recordStore = null;
      byte[] arrayOfByte = new byte[1024];
      try {
        RecordStore.deleteRecordStore("upg_api_private");
      } catch (Exception exception) {}
      try {
        (recordStore = RecordStore.openRecordStore("upg_api_private", true, 0, false)).addRecord(arrayOfByte, 0, 200);
        recordStore.addRecord(arrayOfByte, 0, 100);
        recordStore.addRecord(arrayOfByte, 0, 500);
        recordStore.addRecord(arrayOfByte, 0, 200);
        recordStore.addRecord(arrayOfByte, 0, 1);
        recordStore.addRecord(arrayOfByte, 0, 1);
        recordStore.addRecord(arrayOfByte, 0, 1);
      } catch (Exception exception) {
        (arrayOfByte = null).printStackTrace();
        aE = aF;
      } 
      a(recordStore);
      if (aE == 0) {
        j();
        av = a(true);
        if (aN == null || aN.equals("") || aN.length() < 8)
          av = "19000101"; 
        h();
      } 
      if (System.currentTimeMillis() - l < 1000L)
        c.a(1000L - System.currentTimeMillis() - l); 
      aP = false;
      y = null;
    } 
    while (this.w) {
      String str;
      Vector vector;
      boolean bool1;
      boolean bool2;
      PayPlatform payPlatform;
      boolean bool3;
      switch (this.P) {
        case 1:
          if (this.L != null) {
            TextBox textBox = this.L;
            PayPlatform payPlatform1 = this;
            Display display;
            Displayable displayable = (display = Display.getDisplay(b)).getCurrent();
            payPlatform1.K = true;
            display.setCurrent((Displayable)textBox);
            while (payPlatform1.K)
              c.a(60L); 
            display.setCurrent(displayable);
          } 
          str = this.L.getString();
          if (this.O == this.M)
            this.r.put("c", str); 
          this.u = 4;
          this.P = 0;
          break;
        case 6:
          aD += this.V;
          updatePaidMoney(this.R, this.V);
          (vector = new Vector()).addElement(a(5, 0));
          if (!aK.equals("noid")) {
            vector.addElement(String.valueOf(a(89, 1)) + ": " + F.get("c").toString());
            vector.addElement(String.valueOf(a(41, 1)) + ": " + this.W + " U点");
            vector.addElement(String.valueOf(a(88, 1)) + ": " + this.X + " U点");
          } 
          a(new String[] { a(20, 0) }new Vector[] { vector }, a(0, 0), "o", "", a(1, 0), "p", "", a(0, 0), "p", "");
          this.d = true;
          y = null;
          this.P = 0;
          break;
        case 2:
          if (b(0) < 0) {
            if (y != null)
              y.put("v", "y"); 
            this.P = 0;
            break;
          } 
          this.P = 6;
          break;
        case 3:
          if (b(1) < 0) {
            if (y != null)
              y.put("v", "y"); 
            this.P = 0;
            break;
          } 
          this.P = 6;
          break;
        case 4:
          if (y == null)
            c.a(y = c.a("n", g, h, j), a(61, 1)); 
          if (bool1 = a(this.an, this.ao, 2000)) {
            this.aC++;
            this.T += this.aA;
            updatePaidMoney(this.R, this.T);
            this.aB++;
            if (this.T < this.V) {
              String str3;
              str = a(a(a(a(a(str3 = String.valueOf(a(8, 0)) + "，" + a(10, 0), "$(str3)", this.as), "$(str2)", (new StringBuffer(String.valueOf(this.az - this.aB))).toString()), "$(str1)", (new StringBuffer(String.valueOf(this.aB))).toString()), "$(str4)", d(this.V)), "$(str5)", (new StringBuffer(String.valueOf(this.aB + 1))).toString());
              (vector = new Vector()).addElement(str);
              if (F.get("c").toString() != null && !"".equals(F.get("c").toString()))
                vector.addElement(String.valueOf(aJ) + ":" + " " + F.get("c").toString()); 
              y = null;
              this.P = 0;
              str = "r";
              String str2 = B;
              a(new String[] { a(20, 0) }new Vector[] { vector }, a(0, 0), "o", "", a(1, 0), str, str2, a(0, 0), "m", String.valueOf(4));
            } else {
              if (this.ax == 1 || (this.ax == 0 && this.af == 1))
                try {
                  if (!c(1))
                    c(1); 
                } catch (Exception exception) {} 
              String str2 = String.valueOf(d(this.V)) + "(发送短信：" + this.aB + "条)";
              Vector vector1;
              (vector1 = new Vector()).addElement(String.valueOf(a(5, 0)) + "\n" + a(19, 0) + " " + str2);
              this.d = true;
              y = null;
              this.P = 0;
              a(new String[] { a(20, 0) }new Vector[] { vector1 }, a(0, 0), "o", "", a(1, 0), "p", "", a(0, 0), "p", "");
            } 
            String str1 = this.R;
            PayPlatform payPlatform1 = this;
            try {
              ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
              DataOutputStream dataOutputStream;
              (dataOutputStream = new DataOutputStream(byteArrayOutputStream)).writeUTF(payPlatform1.an);
              dataOutputStream.writeUTF(payPlatform1.ao);
              dataOutputStream.writeInt(payPlatform1.aA);
              dataOutputStream.writeInt(payPlatform1.aB);
              dataOutputStream.writeInt(payPlatform1.az);
              dataOutputStream.writeUTF(payPlatform1.as);
              dataOutputStream.writeUTF(c.a("addtimes", ai));
              payPlatform1.am.put(str1, byteArrayOutputStream.toByteArray());
              dataOutputStream.close();
              byteArrayOutputStream.close();
              payPlatform1.g();
            } catch (Exception exception) {
              (str = null).printStackTrace();
            } 
            break;
          } 
          y.put("v", "y");
          c.a(y, a(7, 0));
          this.P = 0;
          break;
        case 8:
          o();
          this.P = 0;
          break;
        case 9:
          if (!(payPlatform = this).aU && !(bool3 = payPlatform.f())) {
            y.put("v", "y");
          } else {
            payPlatform.c("o", "", "r", z);
          } 
          this.P = 0;
          break;
        case 10:
          if (y == null)
            c.a(y = c.a("n", g, h, j), a(61, 1)); 
          if (I.get("c") == null || I.get("c").equals("")) {
            c.a(y, a(83, 1));
            y.put("v", "y");
            this.P = 0;
            break;
          } 
          if (bool2 = a(this.aX, "game:" + I.get("c").toString(), 0)) {
            c.a(y, a(82, 1));
            y.put("v", "y");
            this.P = 0;
            a("r", A);
            break;
          } 
          y.put("v", "y");
          c.a(y, a(7, 0));
          this.P = 0;
          break;
      } 
      if (aE == 1) {
        str = String.valueOf(a(14, 0)) + a(15, 0) + a(16, 0) + "\n";
        (y = c.a("y", g, h, j)).put("v", "y");
        c.a(y, str);
        aE = 0;
      } 
      c.a(50L);
    } 
    ag = null;
    ah = null;
    ai = null;
    F = null;
    G = null;
    H = null;
  }
  
  private boolean e() {
    byte[] arrayOfByte;
    if (y == null)
      y = c.a("n", g, h, j); 
    c.a(y, a(31, 0));
    String str1 = this.ac;
    String str2 = k();
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer()).append("user_id=" + str2);
    stringBuffer.append("&cpid=" + this.Y);
    stringBuffer.append("&gameid=" + this.q);
    stringBuffer.append("&area=");
    stringBuffer.append("&smsno=" + aN);
    e e = new e(str1, Q, stringBuffer.toString(), aO, aZ);
    try {
      Hashtable hashtable;
      if ((hashtable = e.a(e, Z, "")) == null) {
        c.a(y, "获取短信数据失败，请改用其他手段支付");
        return false;
      } 
      int i = Integer.parseInt(hashtable.get("respCode").toString());
      if (hashtable != null && !hashtable.equals("") && i >= 200 && i <= 299) {
        String str;
        if ((str = new String((byte[])hashtable.get("data"), "UTF-8")).startsWith("-102")) {
          if (a(str, 2, "#").equals("response_null")) {
            c.a(y, "对不起，您所在的地区没有开通短信付费通道，请使用U点进行支付！");
            return false;
          } 
          c.a(y, "获取短信数据失败，请改用其他手段支付");
          return false;
        } 
        arrayOfByte = (byte[])hashtable.get("data");
      } else {
        c.a(y, "获取短信数据失败，请改用其他手段支付");
        return false;
      } 
    } catch (Exception exception) {
      c.a(y, "获取短信数据失败");
      return false;
    } 
    try {
      str2 = new String(arrayOfByte, "UTF-8");
    } catch (Exception exception) {
      str2 = new String(arrayOfByte);
    } 
    if (str2.length() > 2) {
      av = (at = str2).substring(0, at.indexOf("^&*")).substring(at.lastIndexOf(',') + 1);
      au = at.substring(at.indexOf("^&*") + 3);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
      try {
        dataOutputStream.writeUTF(at);
        dataOutputStream.writeUTF(av);
        dataOutputStream.writeUTF(au);
        byte[] arrayOfByte1 = byteArrayOutputStream.toByteArray();
        a(4, arrayOfByte1, "upg_api_private");
        dataOutputStream.close();
        byteArrayOutputStream.close();
      } catch (Exception exception) {
        (stringBuffer = null).printStackTrace();
        aE = aF;
      } 
      y = null;
      return true;
    } 
    return false;
  }
  
  private boolean f() {
    byte[] arrayOfByte;
    String str2;
    if (y == null)
      y = c.a("n", g, h, j); 
    c.a(y, a(32, 0));
    String str1 = this.ae;
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer()).append("cpid=" + this.Y);
    stringBuffer.append("&gameid=" + this.q);
    stringBuffer.append("&u_money=" + this.V);
    e e = new e(str1, Q, stringBuffer.toString(), aO, aZ);
    try {
      Hashtable hashtable;
      if ((hashtable = e.a(e, Z, "")) == null) {
        c.a(y, "获取U点支付信息失败！");
        return false;
      } 
      int i = Integer.parseInt(hashtable.get("respCode").toString());
      if (hashtable != null && !hashtable.equals("") && i >= 200 && i <= 299) {
        arrayOfByte = (byte[])hashtable.get("data");
      } else {
        c.a(y, "获取U点支付信息失败！");
        return false;
      } 
    } catch (Exception exception) {
      c.a(y, "获取U点支付信息失败！");
      return false;
    } 
    try {
      str2 = new String(arrayOfByte, "UTF-8");
    } catch (Exception exception) {
      str2 = new String(arrayOfByte);
    } 
    if (str2.length() > 2) {
      y = null;
      try {
        if (a(str2, 2, "#").equals("none")) {
          this.aS = "无折扣";
          this.aT = String.valueOf(this.V) + " " + aI;
        } else {
          this.aS = a(str2, 2, "#");
          this.aT = a(str2, 4, "#");
        } 
        a(str2, 3, "#");
      } catch (Exception exception) {
        c.a(y, "获取U点支付信息失败！");
        return false;
      } 
      this.aU = true;
      return true;
    } 
    c.a(y, "获取U点支付信息失败！");
    return false;
  }
  
  private static void a(RecordStore paramRecordStore) {
    if (paramRecordStore == null)
      return; 
    try {
      paramRecordStore.closeRecordStore();
      return;
    } catch (Exception exception) {
      (paramRecordStore = null).printStackTrace();
      return;
    } 
  }
  
  private static byte[] a(int paramInt, byte[] paramArrayOfbyte, String paramString) {
    byte[] arrayOfByte = null;
    try {
      RecordStore recordStore = RecordStore.openRecordStore(paramString, false);
      if (paramArrayOfbyte == null) {
        arrayOfByte = new byte[recordStore.getRecordSize(paramInt)];
        recordStore.getRecord(paramInt, arrayOfByte, 0);
      } else {
        recordStore.setRecord(paramInt, paramArrayOfbyte, 0, paramArrayOfbyte.length);
      } 
      a(recordStore);
      return arrayOfByte;
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private void g() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    int i = this.am.size();
    Enumeration enumeration = this.am.keys();
    try {
      dataOutputStream.writeInt(i);
      dataOutputStream.writeInt(this.aC);
      while (enumeration.hasMoreElements()) {
        String str = enumeration.nextElement();
        byte[] arrayOfByte1 = (byte[])this.am.get(str);
        dataOutputStream.writeUTF(str);
        dataOutputStream.writeInt(arrayOfByte1.length);
        dataOutputStream.write(arrayOfByte1);
      } 
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      a(3, arrayOfByte, "upg_api_private");
      dataOutputStream.close();
      byteArrayOutputStream.close();
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      aE = aF;
      return;
    } 
  }
  
  private static void h() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    try {
      dataOutputStream.writeUTF("");
      dataOutputStream.writeUTF(av);
      dataOutputStream.writeUTF("");
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      a(4, arrayOfByte, "upg_api_private");
      dataOutputStream.close();
      byteArrayOutputStream.close();
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      aE = aF;
      return;
    } 
  }
  
  private void i() {
    byte[] arrayOfByte = a(3, (byte[])null, "upg_api_private");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    try {
      DataInputStream dataInputStream;
      int i = (dataInputStream = new DataInputStream(byteArrayInputStream)).readInt();
      this.aC = dataInputStream.readInt();
      for (byte b = 0; b < i; b++) {
        String str = dataInputStream.readUTF();
        byte[] arrayOfByte1 = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(arrayOfByte1);
        this.am.put(str, arrayOfByte1);
      } 
      dataInputStream.close();
      byteArrayInputStream.close();
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      return;
    } 
  }
  
  private boolean a(String paramString) {
    byte[] arrayOfByte;
    if ((arrayOfByte = (byte[])this.am.get(paramString)) == null)
      return false; 
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
    try {
      this.an = dataInputStream.readUTF();
      this.ao = dataInputStream.readUTF();
      this.aA = dataInputStream.readInt();
      this.aB = dataInputStream.readInt();
      this.az = dataInputStream.readInt();
      this.as = dataInputStream.readUTF();
      ai.put("addtimes", dataInputStream.readUTF());
      dataInputStream.close();
      byteArrayInputStream.close();
      return true;
    } catch (Exception exception) {
      (byteArrayInputStream = null).printStackTrace();
      return false;
    } 
  }
  
  private static void j() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    int i = al.size();
    Enumeration enumeration = al.keys();
    try {
      dataOutputStream.writeInt(i);
      while (enumeration.hasMoreElements()) {
        String str;
        int j = b(str = enumeration.nextElement());
        dataOutputStream.writeUTF(str);
        dataOutputStream.writeInt(j);
      } 
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      a(1, arrayOfByte, "upg_api_private");
      dataOutputStream.close();
      byteArrayOutputStream.close();
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      aE = aF;
      return;
    } 
  }
  
  private static int b(String paramString) {
    return !(paramString = String.valueOf(al.get(paramString))).equals("null") ? Integer.parseInt(paramString) : 0;
  }
  
  private static String k() {
    if (ak == null || ak.equals("")) {
      byte b1 = 8;
      long l1 = System.currentTimeMillis();
      long l2 = 1L;
      for (byte b2 = 0; b2 < 8; b2++)
        l2 *= 10L; 
      long l3;
      String str = String.valueOf(l3 = a(l2));
    } 
    return ak;
  }
  
  private static void l() {
    byte[] arrayOfByte = a(1, (byte[])null, "upg_api_private");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
    try {
      DataInputStream dataInputStream;
      int i = (dataInputStream = new DataInputStream(byteArrayInputStream)).readInt();
      for (byte b = 0; b < i; b++) {
        String str = dataInputStream.readUTF();
        int j = dataInputStream.readInt();
        al.put(str, new Integer(j));
      } 
      dataInputStream.close();
      byteArrayInputStream.close();
      return;
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
      return;
    } 
  }
  
  public static void updatePaidMoney(String paramString, int paramInt) {
    al.put(paramString, new Integer(paramInt));
    j();
  }
  
  private static long a(long paramLong) {
    if (aY == null)
      aY = new Random(); 
    long l;
    return l = (aY.nextLong() >>> 1L) % paramLong;
  }
  
  private static String a(int paramInt) {
    String str;
    for (str = ""; str.length() < paramInt; str = String.valueOf(str) + a(10L));
    if (str.length() > paramInt)
      str.substring(0, paramInt); 
    return str;
  }
  
  private static void a(String paramString1, String paramString2, boolean paramBoolean) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    Z = paramBoolean;
    try {
      if (paramString1 == null)
        paramString1 = ""; 
      if (paramString2 == null)
        paramString2 = ""; 
      if (ak == null)
        ak = ""; 
      dataOutputStream.writeUTF(paramString1);
      if (J.get("z") == "y") {
        dataOutputStream.writeUTF(paramString2);
      } else {
        dataOutputStream.writeUTF("");
      } 
      dataOutputStream.writeUTF(ak);
      byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
      a(2, arrayOfByte, "upg_api_private");
      dataOutputStream.close();
      byteArrayOutputStream.close();
      return;
    } catch (Exception exception) {
      (paramString1 = null).printStackTrace();
      aE = aF;
      return;
    } 
  }
  
  private static String[] m() {
    String[] arrayOfString = new String[2];
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a(2, (byte[])null, "upg_api_private"));
    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
    try {
      arrayOfString[0] = dataInputStream.readUTF();
      arrayOfString[1] = dataInputStream.readUTF();
      arrayOfString[2] = dataInputStream.readUTF();
      dataInputStream.close();
      byteArrayInputStream.close();
    } catch (Exception exception) {}
    return arrayOfString;
  }
  
  public void commandAction(Command paramCommand, Displayable paramDisplayable) {
    if (paramDisplayable != this.L)
      return; 
    if (this.L == null)
      return; 
    if (paramCommand == this.N || paramCommand == this.M || paramCommand == null) {
      this.O = paramCommand;
      this.K = false;
      return;
    } 
  }
  
  protected void pointerPressed(int paramInt1, int paramInt2) {
    if (y != null) {
      String str = y.get("a").toString();
      if (y.get("a").toString() == "a") {
        String[] arrayOfString = (String[])y.get("c");
        int j = Integer.parseInt(y.get("r").toString());
        int k = Integer.parseInt(y.get("q").toString());
        int m = Integer.parseInt(y.get("d").toString());
        int n = Integer.parseInt(y.get("m").toString());
        int i1 = k / arrayOfString.length;
        if (paramInt1 >= m && paramInt1 <= m + j && paramInt2 >= n && paramInt2 <= n + k)
          for (byte b1 = 0; b1 < arrayOfString.length; b1++) {
            if (paramInt2 > n + i1 * b1 && paramInt2 < n + i1 * (b1 + 1)) {
              a(((String[])y.get("e"))[b1], ((String[])y.get("f"))[b1]);
              y = null;
              break;
            } 
          }  
        int i = Integer.parseInt(y.get("t").toString());
        if (paramInt2 >= h - j && paramInt1 <= g / 3) {
          a(((String[])y.get("e"))[i], ((String[])y.get("f"))[i]);
          y = null;
          return;
        } 
        if (paramInt2 >= h - j && paramInt1 >= g - g / 3) {
          a("u", "");
          y = null;
        } 
      } 
      if (str == "b" && y.get("v").toString() == "y")
        y = null; 
      return;
    } 
    byte b = -1;
    PayPlatform payPlatform = this;
    if (this.t != null)
      for (byte b1 = 0; b1 < this.t.size(); b1++) {
        Hashtable hashtable;
        int k = Integer.parseInt((hashtable = payPlatform.t.elementAt(b1)).get("d").toString());
        int m = Integer.parseInt(hashtable.get("m").toString());
        int i = Integer.parseInt(hashtable.get("r").toString());
        int j = Integer.parseInt(hashtable.get("q").toString());
        if (paramInt1 > k && paramInt1 < k + i && paramInt2 > m && paramInt2 < m + j) {
          b = b1;
          break;
        } 
      }  
    if (b >= 0 && paramInt2 < h - j) {
      this.r = this.s.elementAt(b);
      this.u = 3;
      return;
    } 
    if (paramInt2 >= h - j && paramInt1 <= g / 3) {
      a(this.o.get("e").toString(), this.o.get("f").toString());
      return;
    } 
    if (paramInt2 >= h - j && paramInt1 >= g - g / 3)
      a(this.p.get("e").toString(), this.p.get("f").toString()); 
  }
  
  protected void keyPressed(int paramInt) {
    int i = 0;
    try {
      i = getGameAction(paramInt);
    } catch (Exception exception2) {
      Exception exception1;
      (exception1 = null).printStackTrace();
    } 
    if ((paramInt == -6 || paramInt == -21 || paramInt == 21 || (paramInt == e && e != 0)) && i != 6 && i != 1 && i != 2 && i != 5)
      i = 9; 
    if ((paramInt == -7 || paramInt == -22 || paramInt == 22 || (paramInt == f && f != 0)) && i != 6 && i != 1 && i != 2 && i != 5)
      i = 10; 
    if (this.u == 4 || this.u == 5)
      return; 
    if (y != null) {
      int j;
      String str;
      if ((str = y.get("a").toString()) == "a") {
        j = ((String[])y.get("c")).length;
        int k = Integer.parseInt(y.get("t").toString());
        switch (i) {
          case 8:
          case 9:
            if (y.get("a") == "a") {
              a(((String[])y.get("e"))[k], ((String[])y.get("f"))[k]);
              return;
            } 
            y = null;
            return;
          case 10:
            y = null;
            return;
          case 1:
            if (k > 0)
              k--; 
            y.put("t", String.valueOf(k));
            return;
          case 6:
            if (k < j - 1)
              k++; 
            y.put("t", String.valueOf(k));
            break;
        } 
        return;
      } 
      if (j == "b" && y.get("v").toString() == "y" && (i == 9 || i == 10 || i == 8))
        y = null; 
      return;
    } 
    switch (i) {
      case 6:
        this.u = 1;
        break;
      case 1:
        this.u = 2;
        break;
      case 8:
        this.u = 3;
        break;
      case 9:
        a(this.o.get("e").toString(), this.o.get("f").toString());
        break;
      case 10:
        a(this.p.get("e").toString(), this.p.get("f").toString());
        break;
    } 
    this.v = System.currentTimeMillis();
  }
  
  private void a(String paramString1, String paramString2) {
    if (paramString1 == "p") {
      this.w = false;
      return;
    } 
    if (paramString1 == "o") {
      this.u = 3;
      return;
    } 
    if (paramString1 == "r") {
      if (paramString2 == A) {
        paramString1 = "r";
        paramString2 = D;
        String str3 = paramString2;
        String str2 = paramString1;
        String str1 = "";
        paramString2 = "o";
        PayPlatform payPlatform;
        (payPlatform = this).c();
        Vector vector;
        (vector = new Vector()).addElement(c.a("n", a(6, 1), 5, "", "", c.c, "", c.b, payPlatform.m[0], -1, -1, c.d, 0));
        payPlatform.n.addElement(c.a(vector, g, j, payPlatform.m[6], -1, "n", "", ""));
        (vector = new Vector()).addElement(c.a("n", String.valueOf(a(8, 1)) + "：", 5, "", "", c.c, "", c.a, payPlatform.m[1], -1, -1, c.e, 0));
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        (vector = new Vector()).addElement(F);
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        (vector = new Vector()).addElement(c.a("n", String.valueOf(a(5, 1)) + "：", 5, "", "", c.c, "", c.a, payPlatform.m[1], -1, -1, c.e, 0));
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        (vector = new Vector()).addElement(G);
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        c.a(payPlatform.n, 5, -1, g);
        (vector = new Vector()).addElement(J);
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        c.a(payPlatform.n, 5, -1, g);
        (vector = new Vector()).addElement(c.a("y", a(80, 1), 5, "m", String.valueOf(2), c.c, "", c.b, payPlatform.m[1], payPlatform.m[4], -1, c.e, g - 10));
        payPlatform.n.addElement(c.a(vector, g, j, -1, -1, "n", "", ""));
        c.a(payPlatform.n, 20, payPlatform.m[2], g);
        (vector = new Vector()).addElement(c.a("n", "优视科技 版权所有", 5, "", "", c.c, "", c.a, payPlatform.m[4], -1, -1, c.e, g - 10));
        payPlatform.n.addElement(c.a(vector, g, j, payPlatform.m[2], -1, "n", "", ""));
        c.a(payPlatform.o, a(0, 0), paramString2, str1);
        c.a(payPlatform.p, a(1, 0), str2, str3);
        payPlatform.ba = false;
        payPlatform.u = 4;
        return;
      } 
      if (paramString2 == C) {
        paramString1 = "r";
        paramString2 = A;
        a("o", "", paramString1, paramString2);
        return;
      } 
      if (paramString2 == E) {
        paramString1 = "r";
        paramString2 = A;
        b("o", "", paramString1, paramString2);
        return;
      } 
      if (paramString2 == D) {
        paramString1 = "r";
        paramString2 = z;
        c("o", "", paramString1, paramString2);
        return;
      } 
      if (paramString2 == z) {
        d();
        return;
      } 
      if (paramString2 == B) {
        y = null;
        o();
        return;
      } 
    } else {
      int i;
      if (paramString1 == "t") {
        y = null;
        if ((i = Integer.parseInt(paramString2)) == 1) {
          this.P = (byte)Integer.parseInt("9");
          return;
        } 
        if (i == 2) {
          this.P = (byte)Integer.parseInt("8");
          return;
        } 
        if (i == 3) {
          this.aX = this.aV;
          this.P = (byte)Integer.parseInt("10");
          return;
        } 
        if (i == 4) {
          this.aX = this.aW;
          this.P = (byte)Integer.parseInt("10");
          return;
        } 
        return;
      } 
      if (i == "u") {
        y = null;
        return;
      } 
    } 
  }
  
  private void a(Hashtable paramHashtable, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: getfield s : Ljava/util/Vector;
    //   4: ifnonnull -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield u : B
    //   12: iconst_5
    //   13: if_icmpne -> 45
    //   16: aload_1
    //   17: ifnull -> 45
    //   20: aload_0
    //   21: getfield s : Ljava/util/Vector;
    //   24: aload_1
    //   25: invokevirtual addElement : (Ljava/lang/Object;)V
    //   28: aload_0
    //   29: getfield t : Ljava/util/Vector;
    //   32: iload #4
    //   34: iload_2
    //   35: iload #5
    //   37: iload_3
    //   38: invokestatic a : (IIII)Ljava/util/Hashtable;
    //   41: invokevirtual addElement : (Ljava/lang/Object;)V
    //   44: return
    //   45: aload_0
    //   46: getfield r : Ljava/util/Hashtable;
    //   49: ifnonnull -> 84
    //   52: aload_0
    //   53: getfield s : Ljava/util/Vector;
    //   56: ifnull -> 84
    //   59: aload_0
    //   60: getfield s : Ljava/util/Vector;
    //   63: invokevirtual size : ()I
    //   66: ifle -> 84
    //   69: aload_0
    //   70: aload_0
    //   71: getfield s : Ljava/util/Vector;
    //   74: iconst_0
    //   75: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   78: checkcast java/util/Hashtable
    //   81: putfield r : Ljava/util/Hashtable;
    //   84: invokestatic currentTimeMillis : ()J
    //   87: aload_0
    //   88: getfield v : J
    //   91: lsub
    //   92: ldc2_w 50
    //   95: lcmp
    //   96: ifge -> 100
    //   99: return
    //   100: aload_0
    //   101: dup
    //   102: astore_2
    //   103: getfield s : Ljava/util/Vector;
    //   106: ifnull -> 119
    //   109: aload_2
    //   110: getfield s : Ljava/util/Vector;
    //   113: invokevirtual size : ()I
    //   116: ifne -> 123
    //   119: iconst_m1
    //   120: goto -> 162
    //   123: iconst_0
    //   124: istore_3
    //   125: goto -> 150
    //   128: aload_2
    //   129: getfield s : Ljava/util/Vector;
    //   132: iload_3
    //   133: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   136: aload_2
    //   137: getfield r : Ljava/util/Hashtable;
    //   140: if_acmpne -> 147
    //   143: iload_3
    //   144: goto -> 162
    //   147: iinc #3, 1
    //   150: iload_3
    //   151: aload_2
    //   152: getfield s : Ljava/util/Vector;
    //   155: invokevirtual size : ()I
    //   158: if_icmplt -> 128
    //   161: iconst_m1
    //   162: istore_2
    //   163: aload_0
    //   164: getfield u : B
    //   167: iconst_1
    //   168: if_icmpne -> 297
    //   171: iload_2
    //   172: ifge -> 212
    //   175: aload_0
    //   176: getfield k : I
    //   179: aload_0
    //   180: getfield x : I
    //   183: iadd
    //   184: getstatic uc/payment/PayPlatform.h : I
    //   187: getstatic uc/payment/PayPlatform.j : I
    //   190: isub
    //   191: if_icmple -> 206
    //   194: aload_0
    //   195: dup
    //   196: getfield x : I
    //   199: getstatic uc/payment/PayPlatform.j : I
    //   202: isub
    //   203: putfield x : I
    //   206: aload_0
    //   207: iconst_4
    //   208: putfield u : B
    //   211: return
    //   212: iload_2
    //   213: aload_0
    //   214: getfield s : Ljava/util/Vector;
    //   217: invokevirtual size : ()I
    //   220: iconst_1
    //   221: isub
    //   222: if_icmpge -> 247
    //   225: aload_0
    //   226: aload_0
    //   227: getfield s : Ljava/util/Vector;
    //   230: iload_2
    //   231: iconst_1
    //   232: iadd
    //   233: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   236: checkcast java/util/Hashtable
    //   239: putfield r : Ljava/util/Hashtable;
    //   242: aload_0
    //   243: iconst_4
    //   244: putfield u : B
    //   247: iload_2
    //   248: aload_0
    //   249: getfield s : Ljava/util/Vector;
    //   252: invokevirtual size : ()I
    //   255: iconst_1
    //   256: isub
    //   257: if_icmpne -> 296
    //   260: aload_0
    //   261: getfield k : I
    //   264: aload_0
    //   265: getfield x : I
    //   268: iadd
    //   269: getstatic uc/payment/PayPlatform.h : I
    //   272: getstatic uc/payment/PayPlatform.j : I
    //   275: isub
    //   276: if_icmple -> 291
    //   279: aload_0
    //   280: dup
    //   281: getfield x : I
    //   284: getstatic uc/payment/PayPlatform.j : I
    //   287: isub
    //   288: putfield x : I
    //   291: aload_0
    //   292: iconst_4
    //   293: putfield u : B
    //   296: return
    //   297: aload_0
    //   298: getfield u : B
    //   301: iconst_2
    //   302: if_icmpne -> 389
    //   305: iload_2
    //   306: ifge -> 334
    //   309: aload_0
    //   310: getfield x : I
    //   313: ifge -> 328
    //   316: aload_0
    //   317: dup
    //   318: getfield x : I
    //   321: getstatic uc/payment/PayPlatform.j : I
    //   324: iadd
    //   325: putfield x : I
    //   328: aload_0
    //   329: iconst_4
    //   330: putfield u : B
    //   333: return
    //   334: iload_2
    //   335: ifne -> 362
    //   338: aload_0
    //   339: getfield x : I
    //   342: ifge -> 357
    //   345: aload_0
    //   346: dup
    //   347: getfield x : I
    //   350: getstatic uc/payment/PayPlatform.j : I
    //   353: iadd
    //   354: putfield x : I
    //   357: aload_0
    //   358: iconst_4
    //   359: putfield u : B
    //   362: iload_2
    //   363: ifle -> 388
    //   366: aload_0
    //   367: aload_0
    //   368: getfield s : Ljava/util/Vector;
    //   371: iload_2
    //   372: iconst_1
    //   373: isub
    //   374: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   377: checkcast java/util/Hashtable
    //   380: putfield r : Ljava/util/Hashtable;
    //   383: aload_0
    //   384: iconst_4
    //   385: putfield u : B
    //   388: return
    //   389: aload_0
    //   390: getfield r : Ljava/util/Hashtable;
    //   393: ifnull -> 404
    //   396: aload_0
    //   397: getfield r : Ljava/util/Hashtable;
    //   400: aload_1
    //   401: if_acmpeq -> 405
    //   404: return
    //   405: aload_0
    //   406: getfield u : B
    //   409: iconst_3
    //   410: if_icmpne -> 666
    //   413: aload_0
    //   414: getfield r : Ljava/util/Hashtable;
    //   417: ldc 'e'
    //   419: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   422: invokevirtual toString : ()Ljava/lang/String;
    //   425: astore_2
    //   426: aload_0
    //   427: getfield r : Ljava/util/Hashtable;
    //   430: ldc 'f'
    //   432: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   435: invokevirtual toString : ()Ljava/lang/String;
    //   438: astore_3
    //   439: aload_2
    //   440: ldc 'q'
    //   442: if_acmpeq -> 661
    //   445: aload_2
    //   446: ldc 'n'
    //   448: if_acmpne -> 544
    //   451: aload_0
    //   452: getfield r : Ljava/util/Hashtable;
    //   455: ldc 'n'
    //   457: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   460: invokevirtual toString : ()Ljava/lang/String;
    //   463: invokestatic parseInt : (Ljava/lang/String;)I
    //   466: istore #4
    //   468: aload_0
    //   469: getfield r : Ljava/util/Hashtable;
    //   472: ldc 's'
    //   474: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   477: invokevirtual toString : ()Ljava/lang/String;
    //   480: astore_1
    //   481: aload_0
    //   482: getfield r : Ljava/util/Hashtable;
    //   485: ldc 'c'
    //   487: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   490: invokevirtual toString : ()Ljava/lang/String;
    //   493: astore_2
    //   494: new javax/microedition/lcdui/TextBox
    //   497: dup
    //   498: aload_1
    //   499: aload_2
    //   500: iload #4
    //   502: iconst_0
    //   503: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;II)V
    //   506: dup
    //   507: astore_1
    //   508: aload_0
    //   509: getfield M : Ljavax/microedition/lcdui/Command;
    //   512: invokevirtual addCommand : (Ljavax/microedition/lcdui/Command;)V
    //   515: aload_1
    //   516: aload_0
    //   517: getfield N : Ljavax/microedition/lcdui/Command;
    //   520: invokevirtual addCommand : (Ljavax/microedition/lcdui/Command;)V
    //   523: aload_1
    //   524: aload_0
    //   525: invokevirtual setCommandListener : (Ljavax/microedition/lcdui/CommandListener;)V
    //   528: aload_0
    //   529: aload_1
    //   530: putfield L : Ljavax/microedition/lcdui/TextBox;
    //   533: aload_0
    //   534: iconst_m1
    //   535: putfield u : B
    //   538: aload_0
    //   539: iconst_1
    //   540: putfield P : I
    //   543: return
    //   544: aload_2
    //   545: ldc 'c'
    //   547: if_acmpne -> 591
    //   550: aload_1
    //   551: ldc 'z'
    //   553: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   556: ldc 'y'
    //   558: if_acmpne -> 576
    //   561: aload_1
    //   562: ldc 'z'
    //   564: ldc 'n'
    //   566: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   569: pop
    //   570: aload_0
    //   571: iconst_m1
    //   572: putfield u : B
    //   575: return
    //   576: aload_1
    //   577: ldc 'z'
    //   579: ldc 'y'
    //   581: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   584: pop
    //   585: aload_0
    //   586: iconst_m1
    //   587: putfield u : B
    //   590: return
    //   591: aload_2
    //   592: ldc 'm'
    //   594: if_acmpne -> 612
    //   597: aload_0
    //   598: iconst_m1
    //   599: putfield u : B
    //   602: aload_0
    //   603: aload_3
    //   604: invokestatic parseInt : (Ljava/lang/String;)I
    //   607: i2b
    //   608: putfield P : I
    //   611: return
    //   612: aload_2
    //   613: ldc 'p'
    //   615: if_acmpne -> 624
    //   618: aload_0
    //   619: iconst_0
    //   620: putfield w : Z
    //   623: return
    //   624: aload_2
    //   625: ldc 't'
    //   627: if_acmpne -> 647
    //   630: aconst_null
    //   631: putstatic uc/payment/PayPlatform.y : Ljava/util/Hashtable;
    //   634: aload_0
    //   635: iconst_m1
    //   636: putfield u : B
    //   639: aload_0
    //   640: ldc 't'
    //   642: aload_3
    //   643: invokespecial a : (Ljava/lang/String;Ljava/lang/String;)V
    //   646: return
    //   647: aload_2
    //   648: ldc 'r'
    //   650: if_acmpne -> 661
    //   653: aload_0
    //   654: ldc 'r'
    //   656: aload_3
    //   657: invokespecial a : (Ljava/lang/String;Ljava/lang/String;)V
    //   660: return
    //   661: aload_0
    //   662: iconst_4
    //   663: putfield u : B
    //   666: return
  }
  
  private int b(int paramInt) {
    String str2 = "";
    if (y == null)
      y = c.a("n", g, h, j); 
    if (F.get("c").equals("") || G.get("c").equals("")) {
      c.a(y, a(23, 0));
      return -1;
    } 
    if (!e(F.get("c").toString()) || !e(G.get("c").toString())) {
      c.a(y, "错误：您输入的账号密码中含有非法字符，请检查！");
      return -1;
    } 
    if (!f(F.get("c").toString())) {
      c.a(y, "错误：请输入由数字组成的账号/手机号。");
      return -1;
    } 
    if (paramInt == 1) {
      if (H.get("c").equals("")) {
        c.a(y, a(23, 0));
        return -1;
      } 
      if (!e(H.get("c").toString())) {
        c.a(y, "错误：支付密码中含有非法字符，请检查！");
        return -1;
      } 
    } 
    String str3;
    int i;
    if ((i = c(str3 = a(F.get("c").toString(), G.get("c").toString(), (paramInt == 0) ? "" : H.get("c").toString(), str2, 0))) == -905 || i == -902)
      i = c(str3 = a(F.get("c").toString(), G.get("c").toString(), (paramInt == 0) ? "" : H.get("c").toString(), str2, 1)); 
    if (i == -102 && a(str3, 2, "#").equals("uc_tm_modify")) {
      str2 = a(str3, 3, "#");
      i = c(str3 = a(F.get("c").toString(), G.get("c").toString(), (paramInt == 0) ? "" : H.get("c").toString(), str2, 1));
    } 
    if (i < 0) {
      String str = a(str3, 2, "#");
      str2 = a(str3, 3, "#");
      if (i == -102) {
        if (str.equals("uc_password_error")) {
          c.a(y, "您的账号或密码错误，请重新输入。");
          a("r", A);
          return -1;
        } 
        if (str.equals("um_system_error")) {
          c.a(y, "系统错误，支付失败，U点未被扣除，请重试或更换其他支付方式！");
          aQ = "";
          d();
          return -1;
        } 
        if (str.equals("um_account_error")) {
          c.a(y, "您的账户余额不足，无法完成本次支付，请用UC浏览器登录UC充值中心(pay.uc.cn)为U点账户充值。");
          d();
          return -1;
        } 
        if (str.equals("um_paypwd_error")) {
          if (this.ba) {
            c.a(y, "支付失败，原因：" + str2);
          } else {
            y = null;
            a("r", C);
          } 
          return -1;
        } 
        c.a(y, "支付失败，原因：" + str2);
        d();
        return -1;
      } 
      c.a(y, "网络繁忙，连接失败，本次支付不成功，请返回尝试重新支付。");
      d();
      return i;
    } 
    String str1 = a(str3, 2, "#");
    if (i == 200 && str1.equals("succ")) {
      this.W = a(str3, 3, "#");
      this.X = a(str3, 4, "#");
      a(F.get("c").toString(), G.get("c").toString(), Z);
      if (J.get("z") != "y")
        G.put("c", ""); 
      aQ = "";
      this.ba = false;
      return i;
    } 
    d();
    return -1;
  }
  
  private String a(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
    byte[] arrayOfByte;
    if (paramString1 == null || paramString2 == null)
      return "-1#" + a(29, 1); 
    a a = new a();
    String str1 = this.ab;
    String str2 = k();
    String str3 = this.q;
    String str7 = this.Y;
    String str6 = paramString1;
    if (aQ == null || aQ.equals(""))
      aQ = String.valueOf(str7) + str3 + str6 + a(10); 
    str3 = aQ;
    String str4 = (new StringBuffer(String.valueOf(e(this.V)))).toString();
    String str5 = String.valueOf((str2.length() <= 5) ? str2 : str2.substring(0, 5)) + ((this.Y.length() <= 5) ? this.Y : this.Y.substring(0, 5)) + ((this.q.length() <= 5) ? this.q : this.q.substring(0, 5)) + this.V;
    str5 = a.a(str5);
    paramString4 = paramString4.equals("") ? a(false) : paramString4;
    str6 = paramString2 = String.valueOf(paramString4) + "`" + paramString1 + "`" + paramString2;
    str7 = new String(a.b("ucweb#20100518").toUpperCase().substring(0, 10).getBytes());
    paramString2 = d.a(str6, str7);
    str6 = "";
    if (paramString3 != null && !paramString3.equals(""))
      str6 = a.a(String.valueOf(a.a(String.valueOf(paramString1) + paramString3).toUpperCase()) + paramString4); 
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer()).append("user_id=" + str2);
    stringBuffer.append("&cpid=" + this.Y);
    stringBuffer.append("&gameid=" + this.q);
    stringBuffer.append("&optid=" + this.R);
    stringBuffer.append("&u_money=" + this.V);
    stringBuffer.append("&charge=" + str4);
    stringBuffer.append("&consume_id=" + str3);
    stringBuffer.append("&ucid=" + paramString1);
    stringBuffer.append("&sign=" + str5);
    stringBuffer.append("&uc_token=" + paramString2);
    stringBuffer.append("&pay_pwd=" + str6);
    stringBuffer.append("&consume_time=" + paramString4);
    stringBuffer.append("&tm=" + paramString4);
    stringBuffer.append("&eid=4");
    stringBuffer.append("&ch_code=" + this.ap);
    e e = new e(str1, Q, stringBuffer.toString(), aO, aZ);
    try {
      if (paramInt == 0) {
        c.a(y, a(11, 0));
      } else {
        c.a(y, "正在重试联网...");
      } 
      Hashtable hashtable;
      if ((hashtable = e.a(e, Z, "")) == null) {
        c.a(y, String.valueOf(a(39, 1)) + ": -904 " + a(37, 1));
        return "-904#nullcontent";
      } 
      int i = Integer.parseInt(hashtable.get("respCode").toString());
      if (hashtable != null && i >= 200 && i <= 299) {
        arrayOfByte = (byte[])hashtable.get("data");
      } else {
        return "-905#ResponseCode=" + i;
      } 
    } catch (Exception exception) {
      c.a(y, "网络繁忙，连接失败，本次支付不成功，请返回尝试重新支付。");
      return "-902#network problem";
    } 
    try {
      paramString2 = new String(arrayOfByte, "UTF-8");
    } catch (Exception exception) {
      paramString2 = new String(arrayOfByte);
    } 
    return paramString2;
  }
  
  private boolean c(int paramInt) {
    byte[] arrayOfByte;
    c.a(y = c.a("n", g, h, j), a(31, 0));
    String str1 = this.ad;
    String str2 = k();
    if (aR == null || aR.equals(""))
      aR = a(20); 
    String str3 = aR;
    String str4 = (new a()).a(String.valueOf((str2.length() <= 5) ? str2 : str2.substring(0, 5)) + ((this.Y.length() <= 5) ? this.Y : this.Y.substring(0, 5)) + ((this.q.length() <= 5) ? this.q : this.q.substring(0, 5)) + e(this.T));
    StringBuffer stringBuffer;
    (stringBuffer = new StringBuffer()).append("user_id=" + str2);
    stringBuffer.append("&cpid=" + this.Y);
    stringBuffer.append("&gameid=" + this.q);
    stringBuffer.append("&optid=" + this.R);
    stringBuffer.append("&consume_id=" + str3);
    stringBuffer.append("&charge=" + e(this.T));
    stringBuffer.append("&optobj=" + this.S);
    stringBuffer.append("&sms_channel=" + this.ay);
    stringBuffer.append("&sms_port=" + this.an);
    stringBuffer.append("&sms_content=" + this.ao);
    stringBuffer.append("&sms_type=" + paramInt);
    stringBuffer.append("&sendtime=" + a(false));
    stringBuffer.append("&sign=" + str4);
    e e = new e(str1, Q, stringBuffer.toString(), aO, aZ);
    try {
      Hashtable hashtable;
      if ((hashtable = e.a(e, Z, "")) == null)
        return false; 
      int i = Integer.parseInt(hashtable.get("respCode").toString());
      if (hashtable != null && i >= 200 && i <= 299) {
        arrayOfByte = (byte[])hashtable.get("data");
      } else {
        return false;
      } 
    } catch (Exception exception) {
      return false;
    } 
    try {
      str1 = new String(arrayOfByte, "UTF-8");
    } catch (Exception exception) {
      str1 = new String(arrayOfByte);
    } 
    if (c(str1) == 200) {
      aR = "";
      return true;
    } 
    return false;
  }
  
  private static String a(int paramInt1, int paramInt2) {
    String str = "";
    try {
      switch (paramInt2) {
        case 0:
          str = ag[paramInt1];
          break;
        case 1:
          str = ah[paramInt1];
          break;
      } 
    } catch (Exception exception) {}
    return str;
  }
  
  private static String a(String paramString1, String paramString2, String paramString3) {
    if (paramString1 == null || "".equals(paramString1) || paramString2 == null || "".equals(paramString2))
      return paramString1; 
    String str = "";
    int i;
    int j;
    for (i = 0; (j = paramString1.indexOf(paramString2, i)) != -1; i = j + paramString2.length()) {
      str = String.valueOf(str) + paramString1.substring(i, j);
      str = String.valueOf(str) + paramString3;
    } 
    if (i != paramString1.length())
      str = String.valueOf(str) + paramString1.substring(i); 
    return str;
  }
  
  private static String[] b(String paramString1, String paramString2) {
    Vector vector;
    int j;
    if (paramString1 == null || paramString1.trim().length() == 0 || paramString2 == null)
      return null; 
    if (paramString1.equals("") || paramString2.equals(""))
      return null; 
    try {
      vector = new Vector();
      j = 0;
      if (paramString1.indexOf(paramString2) == -1)
        return new String[] { paramString1 }; 
    } catch (Exception exception) {
      return null;
    } 
    for (int i = paramString1.indexOf(paramString2); i < paramString1.length() && i != -1; i = paramString1.indexOf(paramString2, i + paramString2.length())) {
      String str = paramString1.substring(j, i);
      vector.addElement(str);
      j = i + paramString2.length();
    } 
    vector.addElement(paramString1.substring(j));
    String[] arrayOfString = new String[vector.size()];
    vector.copyInto((Object[])arrayOfString);
    return arrayOfString;
  }
  
  private static int a(String paramString, Font paramFont) {
    char[] arrayOfChar;
    int i = (arrayOfChar = paramString.toCharArray()).length;
    int j = 0;
    for (byte b = 0; b < i; b++)
      j += paramFont.charWidth(arrayOfChar[b]); 
    return j;
  }
  
  private static String[] a(String paramString, Font paramFont, int paramInt) {
    if (paramString == null || paramFont == null)
      return new String[] { "BUG split: " + paramString }; 
    StringBuffer stringBuffer = new StringBuffer(paramString);
    char[] arrayOfChar;
    int i = (arrayOfChar = paramString.toCharArray()).length;
    int j = 0;
    byte b1 = 0;
    byte b2 = 0;
    for (byte b3 = 0; b3 < i; b3++) {
      if (arrayOfChar[b3] == '\n') {
        b1++;
        j = 0;
      } else if ((j += paramFont.charWidth(arrayOfChar[b3])) > paramInt) {
        stringBuffer.insert(b3 + b2, '\n');
        b1++;
        b2++;
        j = paramFont.charWidth(arrayOfChar[b3]);
      } 
    } 
    b1++;
    stringBuffer.append('\n');
    return a(stringBuffer.toString(), '\n', b1);
  }
  
  private static String[] a(String paramString, char paramChar, int paramInt) {
    if (paramInt <= 0) {
      byte b1 = -1;
      int i = -1;
      while ((i = paramString.indexOf('\n', i + 1)) >= 0)
        b1++; 
      if (b1 < 0)
        return new String[] { paramString }; 
    } 
    String[] arrayOfString = new String[paramInt];
    for (byte b = 0; b < paramInt; b++) {
      int i;
      if ((i = paramString.indexOf('\n')) == -1) {
        arrayOfString[b] = paramString;
      } else {
        arrayOfString[b] = paramString.substring(0, i);
        paramString = paramString.substring(i + 1);
      } 
    } 
    return arrayOfString;
  }
  
  private static String c(String paramString1, String paramString2) {
    paramString2 = paramString1;
    paramString1 = paramString2;
    paramString2 = paramString2;
    char[] arrayOfChar = new char[paramString1.length() / 4];
    String str = "";
    byte b1 = 0;
    for (byte b2 = 0; b1 < paramString1.length() / 4; b2++) {
      if (b2 == paramString2.length())
        b2 = 0; 
      int i = Integer.parseInt(paramString1.substring(b1 << 2, (b1 << 2) + 4));
      arrayOfChar[b1] = (char)((char)i ^ paramString2.charAt(b2));
      b1++;
    } 
    for (b1 = 0; b1 < paramString1.length() / 4; b1++)
      str = String.valueOf(str) + arrayOfChar[b1]; 
    return str;
  }
  
  private static int c(String paramString) {
    int i;
    try {
      if (paramString.equals("") || paramString == null)
        return -110; 
    } catch (Exception exception) {
      return -110;
    } 
    if ((paramString = a(paramString, 1, "#")) == null)
      return -110; 
    try {
      i = Integer.parseInt(paramString);
    } catch (Exception exception) {
      return -110;
    } 
    return i;
  }
  
  private static String a(String paramString1, int paramInt, String paramString2) {
    String[] arrayOfString;
    return (paramInt <= 0) ? null : ((paramString1.indexOf(paramString2) < 0) ? null : (((arrayOfString = b(paramString1, paramString2)) == null) ? null : ((paramInt > arrayOfString.length) ? null : arrayOfString[paramInt - 1])));
  }
  
  private static String d(int paramInt) {
    String str1;
    a();
    String str2;
    if ((str2 = c.a("locale", ai)).equals("")) {
      aE = aG;
      return "";
    } 
    String[] arrayOfString;
    if ((arrayOfString = b(str2, ",")).length < 3) {
      aE = aG;
      return "";
    } 
    int i = Integer.parseInt((aH = arrayOfString)[2]);
    if (aH[1].equals("元")) {
      int j = paramInt * i / 100;
      if ((paramInt = paramInt * i % 100) < 10) {
        str1 = String.valueOf(j) + ".0" + aH[1];
      } else {
        str1 = String.valueOf(j) + "." + str1 + aH[1];
      } 
    } else {
      if (str1 * i % 100 >= 50)
        i++; 
      str1 = String.valueOf(aH[1]) + " " + i;
    } 
    return str1;
  }
  
  private static int e(int paramInt) {
    a();
    String str;
    if ((str = c.a("locale", ai)).equals("")) {
      aE = aG;
      return 0;
    } 
    String[] arrayOfString;
    if ((arrayOfString = b(str, ",")).length < 3) {
      aE = aG;
      return 0;
    } 
    int i = Integer.parseInt((aH = arrayOfString)[2]);
    return paramInt = paramInt * i / 100;
  }
  
  private static String d(String paramString) {
    String str = "";
    InputStream inputStream = null;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    byte[] arrayOfByte = new byte[1024];
    try {
      if ((inputStream = "".getClass().getResourceAsStream(paramString)) != null) {
        int i;
        while ((i = inputStream.read(arrayOfByte)) != -1)
          dataOutputStream.write(arrayOfByte, 0, i); 
        str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
      } 
      a(inputStream);
      a(dataOutputStream);
      a(byteArrayOutputStream);
    } catch (Exception exception) {
      a(inputStream);
      a(dataOutputStream);
      a(byteArrayOutputStream);
    } 
    return str;
  }
  
  private static void a(InputStream paramInputStream) {
    if (paramInputStream == null)
      return; 
    try {
      paramInputStream.close();
      return;
    } catch (Exception exception) {
      (paramInputStream = null).printStackTrace();
      return;
    } 
  }
  
  private static void a(OutputStream paramOutputStream) {
    if (paramOutputStream == null)
      return; 
    try {
      paramOutputStream.close();
      return;
    } catch (Exception exception) {
      (paramOutputStream = null).printStackTrace();
      return;
    } 
  }
  
  private static boolean a(String paramString1, String paramString2, int paramInt) {
    if (paramString1 == null || "".equals(paramString1.trim()))
      return false; 
    if (paramString2 == null || "".equals(paramString2.trim()))
      return false; 
    paramString1 = "sms://" + paramString1;
    MessageConnection messageConnection = null;
    boolean bool = false;
    long l = System.currentTimeMillis();
    try {
      TextMessage textMessage;
      (textMessage = (TextMessage)(messageConnection = (MessageConnection)Connector.open(paramString1)).newMessage("text")).setAddress(paramString1);
      textMessage.setPayloadText(paramString2);
      messageConnection.send((Message)textMessage);
      try {
        messageConnection.send(null);
      } catch (Exception exception) {
        long l1;
        if ((l1 = System.currentTimeMillis()) - l > paramInt)
          bool = true; 
      } 
    } catch (Exception exception) {
      (paramString1 = null).printStackTrace();
      bool = false;
    } 
    try {
      messageConnection.close();
    } catch (Exception exception) {
      (paramString1 = null).printStackTrace();
    } 
    return bool;
  }
  
  private void n() {
    Vector vector = new Vector();
    if (this.T > 0 && this.T < this.V) {
      String str = String.valueOf(a(8, 0)) + "，" + a(10, 0);
      vector.addElement(String.valueOf(a(a(a(str, "$(str3)", this.as), "$(str2)", (new StringBuffer(String.valueOf(this.az - this.aB))).toString()), "$(str1)", (new StringBuffer(String.valueOf(this.aB))).toString())) + au);
    } else {
      String str = String.valueOf(a(9, 0)) + "，" + a(10, 0);
      vector.addElement(String.valueOf(a(a(a(str, "$(str2)", this.as), "$(str1)", (new StringBuffer(String.valueOf(this.az))).toString()), "$(str3)", d(this.V))) + au);
    } 
    String[] arrayOfString = { a(20, 0) };
    Vector[] arrayOfVector = { vector };
    String str1 = "r";
    String str2 = z;
    a(arrayOfString, arrayOfVector, a(0, 0), "o", "", a(1, 0), str1, str2, a(0, 0), "m", String.valueOf(4));
  }
  
  private void o() {
    String[] arrayOfString;
    String str2 = "";
    if (av == null || av.equals(""))
      av = "20100101"; 
    String str3 = a(true);
    String str1 = av;
    int j = Integer.parseInt(str3.substring(0, 4));
    int k = Integer.parseInt(str3.substring(4, 6));
    int i = Integer.parseInt(str3.substring(6, 8));
    long l1 = a(j, k, i);
    j = Integer.parseInt(str1.substring(0, 4));
    k = Integer.parseInt(str1.substring(4, 6));
    i = Integer.parseInt(str1.substring(6, 8));
    long l2 = a(j, k, i);
    boolean bool;
    long l3;
    if ((int)((l3 = l1 - l2) / 1000L / 60L / 60L / 24L) > this.aw && !(bool = e())) {
      y.put("v", "y");
      d();
      return;
    } 
    if (at == null || at.equals("") || at.indexOf(",") <= 0) {
      c.a(y, "获取短信数据失败，请改用U点支付");
      y.put("v", "y");
      d();
      return;
    } 
    if (at.indexOf("^|*") > 0) {
      String str = (arrayOfString = b(at, "^|*"))[0];
      j = 500;
      k = 500;
      for (byte b = 0; b < arrayOfString.length; b++) {
        int m = e(this.V);
        int n;
        if ((n = Integer.parseInt(b(arrayOfString[b], ",")[4])) < k) {
          k = n;
          str = arrayOfString[b];
        } 
        if (m / n < j && m / n != 0) {
          j = m / n;
          str2 = arrayOfString[b];
        } 
      } 
      if (j == 500)
        str2 = str; 
      arrayOfString = b(str2, ",");
    } else {
      arrayOfString = b(at, ",");
    } 
    if (arrayOfString == null || arrayOfString.length < 2) {
      c.a(y, "获取短信数据失败，请改用U点支付");
      y.put("v", "y");
      d();
      return;
    } 
    this.as = arrayOfString[4];
    this.aA = Integer.parseInt(this.as) * 10;
    this.az = this.V / this.aA;
    this.ax = Integer.parseInt(arrayOfString[3]);
    if (this.V % this.aA > 0)
      this.az++; 
    this.ay = arrayOfString[0];
    this.an = arrayOfString[1];
    this.ao = arrayOfString[2];
    if (this.ax == 0) {
      this.ao = String.valueOf(this.ao) + this.Y;
      this.ao = String.valueOf(this.ao) + this.q;
      this.ao = String.valueOf(this.ao) + this.R;
      this.ao = String.valueOf(this.ao) + this.aq;
      this.ao = String.valueOf(this.ao) + this.ap;
      this.ao = String.valueOf(this.ao) + this.ar;
    } 
    if (this.ax == 1) {
      if (!c(0)) {
        y.put("v", "y");
        d();
        return;
      } 
      y.put("v", "y");
      y = null;
    } 
    n();
  }
  
  private static String a(boolean paramBoolean) {
    Calendar calendar;
    (calendar = Calendar.getInstance(TimeZone.getDefault())).setTime(new Date());
    int j = calendar.get(1);
    int k = calendar.get(2) + 1;
    int m = calendar.get(5);
    int n = calendar.get(11);
    int i1 = calendar.get(12);
    int i = calendar.get(13);
    String str5;
    if ((str5 = String.valueOf(n)).length() < 2)
      str5 = "0" + str5; 
    String str6;
    if ((str6 = String.valueOf(i1)).length() < 2)
      str6 = "0" + str6; 
    String str1;
    if ((str1 = String.valueOf(i)).length() < 2)
      str1 = "0" + str1; 
    String str2 = String.valueOf(j);
    String str3;
    if ((str3 = String.valueOf(k)).length() < 2)
      str3 = "0" + str3; 
    String str4;
    if ((str4 = String.valueOf(m)).length() < 2)
      str4 = "0" + str4; 
    return paramBoolean ? (String.valueOf(str2) + str3 + str4) : (String.valueOf(str2) + str3 + str4 + str5 + str6 + str1);
  }
  
  private static long a(int paramInt1, int paramInt2, int paramInt3) {
    Calendar calendar;
    (calendar = Calendar.getInstance(TimeZone.getDefault())).set(1, paramInt1);
    calendar.set(2, paramInt2 - 1);
    calendar.set(5, paramInt3);
    calendar.set(11, 0);
    calendar.set(12, 0);
    calendar.set(13, 0);
    calendar.set(14, 0);
    return calendar.getTime().getTime();
  }
  
  private static boolean e(String paramString) {
    boolean bool = true;
    try {
      char[] arrayOfChar;
      int i = (arrayOfChar = paramString.toCharArray()).length;
      for (byte b = 0; b < i; b++) {
        char c;
        if (((c = arrayOfChar[b]) < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9')) {
          bool = false;
          break;
        } 
      } 
    } catch (Exception exception) {
      (paramString = null).printStackTrace();
      bool = false;
    } 
    return bool;
  }
  
  private static boolean f(String paramString) {
    boolean bool = true;
    try {
      char[] arrayOfChar;
      int i = (arrayOfChar = paramString.toCharArray()).length;
      for (byte b = 0; b < i; b++) {
        char c;
        if ((c = arrayOfChar[b]) < '0' || c > '9') {
          bool = false;
          break;
        } 
      } 
    } catch (Exception exception) {
      (paramString = null).printStackTrace();
      bool = false;
    } 
    return bool;
  }
  
  public static boolean launchPay(MIDlet paramMIDlet, Displayable paramDisplayable, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt) {
    int i = paramInt;
    String str;
    if (!((b(str = paramString4) >= i) ? 1 : 0))
      return true; 
    if (paramInt % 20 != 0 || paramInt < 20) {
      System.out.println("消费金额填写不正确！");
      return false;
    } 
    Display display = Display.getDisplay(paramMIDlet);
    PayPlatform payPlatform1 = a = new PayPlatform(paramMIDlet, paramString3, 0, 0, 0, 0, paramInt);
    PayPlatform payPlatform2 = payPlatform1;
    payPlatform1.w = true;
    payPlatform2.d = false;
    payPlatform2.R = paramString4;
    payPlatform2.S = paramString5;
    payPlatform2.q = paramString3;
    payPlatform2.U = paramString1;
    payPlatform2.Y = paramString2;
    payPlatform2.T = b(paramString4);
    PayPlatform payPlatform3;
    (payPlatform3 = payPlatform2).w = true;
    (new Thread(payPlatform3)).start();
    if (payPlatform2.T > 0 && payPlatform2.T < payPlatform2.V) {
      if (payPlatform2.a(paramString4)) {
        payPlatform2.n();
      } else {
        payPlatform2.d();
      } 
    } else {
      payPlatform2.d();
    } 
    display.setCurrent((Displayable)a);
    while (payPlatform2.w) {
      try {
        a.serviceRepaints();
        a.repaint();
      } catch (Exception exception) {}
      c.a(33L);
    } 
    display.setCurrent(paramDisplayable);
    return payPlatform2.d;
  }
}


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uc/payment/PayPlatform.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */