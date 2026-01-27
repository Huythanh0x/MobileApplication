package uccommon;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public final class e {
  public static boolean a = false;
  
  private static int c = 40000;
  
  private String d;
  
  private String e;
  
  private Hashtable f;
  
  private byte[] g;
  
  public static HttpConnection b = null;
  
  private static OutputStream h = null;
  
  private static InputStream i = null;
  
  private e(String paramString1, String paramString2, byte[] paramArrayOfbyte, Hashtable paramHashtable, int paramInt) {
    this.d = paramString1;
    this.e = paramString2.toUpperCase();
    this.g = null;
    this.f = paramHashtable;
    c = paramInt;
  }
  
  public e(String paramString1, String paramString2, String paramString3, Hashtable paramHashtable, int paramInt) {
    this.d = paramString1;
    this.e = paramString2.toUpperCase();
    if (paramString3 != null && !paramString3.equals(""))
      if ("GET".equals(this.e)) {
        if (paramString1.indexOf("?") >= 0) {
          this.d = String.valueOf(this.d) + "&" + paramString3;
        } else {
          this.d = String.valueOf(this.d) + "?" + paramString3;
        } 
      } else {
        try {
          this.g = paramString3.getBytes("UTF-8");
        } catch (Exception exception) {
          this.g = paramString3.getBytes();
        } 
      }  
    this.f = paramHashtable;
    c = paramInt;
  }
  
  public static Hashtable a(e parame, boolean paramBoolean, String paramString) {
    if (parame == null)
      return null; 
    String str1;
    e e1;
    if ((str1 = (e1 = parame).d) == null || "".equals(str1.trim()))
      return null; 
    e e2;
    String str2 = (e2 = parame).e;
    Hashtable hashtable1 = (e1 = parame).f;
    byte[] arrayOfByte = (e2 = parame).g;
    String str4 = null;
    if (str1.toLowerCase().startsWith("http://"))
      str4 = str1.substring(7); 
    int j;
    if ((j = str4.indexOf('/')) > 0) {
      str4.substring(j);
      str4 = str4.substring(0, j);
    } else {
      str4 = str4;
    } 
    if ((j = str4.indexOf(":")) >= 0) {
      str4.substring(j + 1);
      str4.substring(0, j);
    } 
    str1 = str1;
    a = true;
    (new b(c)).start();
    if ("POST".equals(str2)) {
      b = (HttpConnection)Connector.open(str1, 3, true);
    } else {
      b = (HttpConnection)Connector.open(str1, 1, true);
    } 
    b.setRequestMethod(str2);
    b.setRequestProperty("Connection", "close");
    if (hashtable1 != null) {
      Enumeration enumeration = hashtable1.keys();
      while (enumeration.hasMoreElements()) {
        str1 = enumeration.nextElement();
        String str = (String)hashtable1.get(str1);
        b.setRequestProperty(str1, str);
      } 
    } 
    if (paramString != null && !"".equals(paramString.trim()))
      b.setRequestProperty("Referer", paramString); 
    if ("POST".equals(str2) && arrayOfByte != null) {
      b.setRequestProperty("Content-Length", String.valueOf(arrayOfByte.length));
      (h = b.openOutputStream()).write(arrayOfByte);
      h.close();
    } 
    int i = b.getResponseCode();
    Hashtable hashtable;
    (hashtable = new Hashtable()).put("respCode", new Integer(i));
    String str3;
    if ((str3 = b.getHeaderField("Content-Type")) != null)
      hashtable.put("contType", str3); 
    if (i != 200 && i != 302 && i != 301) {
      if (b != null)
        try {
          b.close();
        } catch (Exception exception) {
          (str4 = null).printStackTrace();
        }  
      a = false;
      return hashtable;
    } 
    if (i == 302 || i == 301) {
      if ((str4 = b.getHeaderField("Location")) == null || "".equals(str4.trim()))
        throw new Exception("WapPay.sendHttpRequest() " + i + " Jump==> conn.getRequestProperty(\"Location\") ==null "); 
      e e3;
      Hashtable hashtable2 = a(new e(str4, "GET", null, (e3 = parame).f, c), paramBoolean, (e3 = parame).d);
      if (b != null)
        try {
          b.close();
        } catch (Exception exception) {} 
      return hashtable2;
    } 
    try {
      byte b;
      i = b.openInputStream();
      str4 = b.getHeaderField("content-length");
      try {
        b = Integer.parseInt(str4);
      } catch (Exception exception) {
        b = -1;
      } 
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      if (b > 0) {
        for (byte b1 = 0; b1 < b && (i = i.read()) >= 0; b1++)
          byteArrayOutputStream.write(i); 
      } else {
        while ((i = i.read()) != -1)
          byteArrayOutputStream.write((byte)i); 
      } 
      byte[] arrayOfByte1 = byteArrayOutputStream.toByteArray();
      hashtable.put("data", arrayOfByte1);
      a = false;
      byteArrayOutputStream.close();
      if (i != null)
        try {
          i.close();
        } catch (Exception exception) {} 
      if (b != null)
        try {
          b.close();
        } catch (Exception exception) {} 
    } catch (Exception exception) {
      (str4 = null).printStackTrace();
      return null;
    } 
    if (i != null)
      try {
        i.close();
      } catch (Exception exception) {} 
    if (b != null)
      try {
        b.close();
      } catch (Exception exception) {} 
    return hashtable;
  }
}


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uccommon/e.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */