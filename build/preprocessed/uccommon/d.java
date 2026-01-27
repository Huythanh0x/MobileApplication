package uccommon;

public final class d {
  public static String a(String paramString1, String paramString2) {
    paramString2 = paramString2;
    int[] arrayOfInt = new int[paramString1.length()];
    String str1 = "";
    String str2 = "";
    byte b1 = 0;
    for (byte b2 = 0; b1 < paramString1.length(); b2++) {
      if (b2 == paramString2.length())
        b2 = 0; 
      arrayOfInt[b1] = paramString1.charAt(b1) ^ paramString2.charAt(b2);
      b1++;
    } 
    for (b1 = 0; b1 < paramString1.length(); b1++) {
      if (arrayOfInt[b1] < 10) {
        str2 = "000" + arrayOfInt[b1];
      } else if (arrayOfInt[b1] < 100) {
        str2 = "00" + arrayOfInt[b1];
      } else if (arrayOfInt[b1] < 1000) {
        str2 = "0" + arrayOfInt[b1];
      } 
      str1 = String.valueOf(str1) + str2;
    } 
    return str1;
  }
}


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uccommon/d.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */