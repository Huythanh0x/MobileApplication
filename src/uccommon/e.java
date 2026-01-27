package uccommon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

   private e(String var1, String var2, byte[] var3, Hashtable var4, int var5) {
      this.d = var1;
      this.e = var2.toUpperCase();
      this.g = null;
      this.f = var4;
      c = var5;
   }

   public e(String var1, String var2, String var3, Hashtable var4, int var5) {
      this.d = var1;
      this.e = var2.toUpperCase();
      if (var3 != null && !var3.equals("")) {
         if ("GET".equals(this.e)) {
            if (var1.indexOf("?") >= 0) {
               this.d = this.d + "&" + var3;
            } else {
               this.d = this.d + "?" + var3;
            }
         } else {
            try {
               this.g = var3.getBytes("UTF-8");
            } catch (Exception var6) {
               this.g = var3.getBytes();
            }
         }
      }

      this.f = var4;
      c = var5;
   }

   public static Hashtable a(e var0, boolean var1, String var2) {
      if (var0 == null) {
         return null;
      } else {
         String var3;
         if ((var3 = var0.d) != null && !"".equals(var3.trim())) {
            String var4 = var0.e;
            Hashtable var5 = var0.f;
            byte[] var6 = var0.g;
            String var8 = null;
            if (var3.toLowerCase().startsWith("http://")) {
               var8 = var3.substring(7);
            }

            int var7;
            if ((var7 = var8.indexOf(47)) > 0) {
               var8.substring(var7);
               var8 = var8.substring(0, var7);
            } else {
               var8 = var8;
            }

            if ((var7 = var8.indexOf(":")) >= 0) {
               var8.substring(var7 + 1);
               var8.substring(0, var7);
            }

            a = true;
            (new b(c)).start();
            try {
               if ("POST".equals(var4)) {
                  b = (HttpConnection)Connector.open(var3, 3, true);
               } else {
                  b = (HttpConnection)Connector.open(var3, 1, true);
               }

               b.setRequestMethod(var4);
               b.setRequestProperty("Connection", "close");
               String var24;
               if (var5 != null) {
                  Enumeration var25 = var5.keys();

                  while(var25.hasMoreElements()) {
                     var3 = (String)var25.nextElement();
                     var24 = (String)var5.get(var3);
                     b.setRequestProperty(var3, var24);
                  }
               }

               if (var2 != null && !"".equals(var2.trim())) {
                  b.setRequestProperty("Referer", var2);
               }

               if ("POST".equals(var4) && var6 != null) {
                  b.setRequestProperty("Content-Length", String.valueOf(var6.length));
                  (h = b.openOutputStream()).write(var6);
                  h.close();
               }

               int var21 = b.getResponseCode();
               Hashtable var20;
               (var20 = new Hashtable()).put("respCode", new Integer(var21));
               if ((var24 = b.getHeaderField("Content-Type")) != null) {
                  var20.put("contType", var24);
               }

               if (var21 != 200 && var21 != 302 && var21 != 301) {
                  if (b != null) {
                     try {
                        b.close();
                     } catch (Exception var9) {
                        var9.printStackTrace();
                     }
                  }

                  a = false;
                  return var20;
               } else if (var21 != 302 && var21 != 301) {
                  try {
                     i = b.openInputStream();
                     var8 = b.getHeaderField("content-length");

                     int var18;
                     try {
                        var18 = Integer.parseInt(var8);
                     } catch (Exception var14) {
                        var18 = -1;
                     }

                     ByteArrayOutputStream var19 = new ByteArrayOutputStream();
                     if (var18 > 0) {
                        for(int var22 = 0; var22 < var18 && (var21 = i.read()) >= 0; ++var22) {
                           var19.write(var21);
                        }
                     } else {
                        while((var21 = i.read()) != -1) {
                           var19.write((byte)var21);
                        }
                     }

                     byte[] var23 = var19.toByteArray();
                     var20.put("data", var23);
                     a = false;
                     var19.close();
                     if (i != null) {
                        try {
                           i.close();
                        } catch (Exception var13) {
                        }
                     }

                     if (b != null) {
                        try {
                           b.close();
                        } catch (Exception var12) {
                        }
                     }
                  } catch (Exception var16) {
                     var16.printStackTrace();
                     return null;
                  }

                  if (i != null) {
                     try {
                        i.close();
                     } catch (Exception var11) {
                     }
                  }

                  if (b != null) {
                     try {
                        b.close();
                     } catch (Exception var10) {
                     }
                  }

                  return var20;
               } else if ((var8 = b.getHeaderField("Location")) != null && !"".equals(var8.trim())) {
                  Hashtable var17 = a(new e(var8, "GET", (byte[])null, var0.f, c), var1, var0.d);
                  if (b != null) {
                     try {
                        b.close();
                     } catch (Exception var15) {
                     }
                  }

                  return var17;
               } else {
                  throw new Exception("WapPay.sendHttpRequest() " + var21 + " Jump==> conn.getRequestProperty(\"Location\") ==null ");
               }
            } catch (IOException var10) {
               var10.printStackTrace();
               a = false;
               return null;
            } catch (Exception var11) {
               var11.printStackTrace();
               a = false;
               return null;
            } finally {
               if (i != null) {
                  try {
                     i.close();
                  } catch (Exception var11) {
                  }
               }

               if (b != null) {
                  try {
                     b.close();
                  } catch (Exception var10) {
                  }
               }
            }
         } else {
            return null;
         }
      }
   }
}
