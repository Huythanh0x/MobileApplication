package uccommon;

public final class a {
  private static byte[] a = new byte[] { Byte.MIN_VALUE };
  
  private long[] b = new long[4];
  
  private long[] c = new long[2];
  
  private byte[] d = new byte[64];
  
  private String e;
  
  private byte[] f = new byte[16];
  
  public final String a(String paramString) {
    a();
    a(paramString.getBytes(), paramString.length());
    a a1 = this;
    byte[] arrayOfByte;
    a(arrayOfByte = new byte[8], a1.c, 8);
    int i = ((i = (int)(a1.c[0] >>> 3L) & 0x3F) >= 56) ? (120 - i) : (56 - i);
    a1.a(a, i);
    a1.a(arrayOfByte, 8);
    a(a1.f, a1.b, 16);
    this.e = "";
    for (byte b = 0; b < 16; b++) {
      byte b1 = this.f[b];
      char[] arrayOfChar1 = { 
          '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
          'a', 'b', 'c', 'd', 'e', 'f' };
      char[] arrayOfChar2;
      (arrayOfChar2 = new char[2])[0] = arrayOfChar1[b1 >>> 4 & 0xF];
      arrayOfChar2[1] = arrayOfChar1[b1 & 0xF];
      String str;
      this.e = String.valueOf(this.e) + (str = new String(arrayOfChar2));
    } 
    return this.e;
  }
  
  public a() {
    a();
  }
  
  private void a() {
    this.c[0] = 0L;
    this.c[1] = 0L;
    this.b[0] = 1732584193L;
    this.b[1] = 4023233417L;
    this.b[2] = 2562383102L;
    this.b[3] = 271733878L;
  }
  
  private long a(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7) {
    long l3 = paramLong4;
    long l2 = paramLong3;
    long l1;
    return paramLong1 = (paramLong1 = ((int)(paramLong1 += ((l1 = paramLong2) & l2 | (l1 ^ 0xFFFFFFFFFFFFFFFFL) & l3) + paramLong5 + paramLong7) << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6))) + paramLong2;
  }
  
  private long b(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7) {
    long l3 = paramLong4;
    long l2 = paramLong3;
    long l1;
    return paramLong1 = (paramLong1 = ((int)(paramLong1 += ((l1 = paramLong2) & l3 | l2 & (l3 ^ 0xFFFFFFFFFFFFFFFFL)) + paramLong5 + paramLong7) << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6))) + paramLong2;
  }
  
  private long c(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7) {
    long l3 = paramLong4;
    long l2 = paramLong3;
    long l1;
    return paramLong1 = (paramLong1 = ((int)(paramLong1 += ((l1 = paramLong2) ^ l2 ^ l3) + paramLong5 + paramLong7) << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6))) + paramLong2;
  }
  
  private long d(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7) {
    long l3 = paramLong4;
    long l2 = paramLong3;
    long l1 = paramLong2;
    return paramLong1 = (paramLong1 = ((int)(paramLong1 += (l2 ^ (l1 | l3 ^ 0xFFFFFFFFFFFFFFFFL)) + paramLong5 + paramLong7) << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6))) + paramLong2;
  }
  
  private void a(byte[] paramArrayOfbyte, int paramInt) {
    byte[] arrayOfByte = new byte[64];
    int i = (int)(this.c[0] >>> 3L) & 0x3F;
    this.c[0] = this.c[0] + (paramInt << 3);
    if (this.c[0] + (paramInt << 3) < (paramInt << 3))
      this.c[1] = this.c[1] + 1L; 
    this.c[1] = this.c[1] + (paramInt >>> 29);
    int j = 64 - i;
    if (paramInt >= j) {
      a(this.d, paramArrayOfbyte, i, 0, j);
      a(this.d);
      for (j = j; j + 63 < paramInt; j += 64) {
        a(arrayOfByte, paramArrayOfbyte, 0, j, 64);
        a(arrayOfByte);
      } 
      i = 0;
    } else {
      j = 0;
    } 
    a(this.d, paramArrayOfbyte, i, j, paramInt - j);
  }
  
  private static void a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt1, int paramInt2, int paramInt3) {
    for (byte b = 0; b < paramInt3; b++)
      paramArrayOfbyte1[paramInt1 + b] = paramArrayOfbyte2[paramInt2 + b]; 
  }
  
  private void a(byte[] paramArrayOfbyte) {
    long l1 = this.b[0];
    long l2 = this.b[1];
    long l3 = this.b[2];
    long l4 = this.b[3];
    byte b1 = 64;
    byte[] arrayOfByte = paramArrayOfbyte;
    long[] arrayOfLong2 = new long[16];
    long[] arrayOfLong1 = arrayOfLong2;
    byte b2 = 0;
    for (byte b3 = 0; b3 < 64; b3 += 4) {
      arrayOfLong1[b2] = a(arrayOfByte[b3]) | a(arrayOfByte[b3 + 1]) << 8L | a(arrayOfByte[b3 + 2]) << 16L | a(arrayOfByte[b3 + 3]) << 24L;
      b2++;
    } 
    l1 = a(l1, l2, l3, l4, arrayOfLong2[0], 7L, 3614090360L);
    l4 = a(l4, l1, l2, l3, arrayOfLong2[1], 12L, 3905402710L);
    l3 = a(l3, l4, l1, l2, arrayOfLong2[2], 17L, 606105819L);
    l2 = a(l2, l3, l4, l1, arrayOfLong2[3], 22L, 3250441966L);
    l1 = a(l1, l2, l3, l4, arrayOfLong2[4], 7L, 4118548399L);
    l4 = a(l4, l1, l2, l3, arrayOfLong2[5], 12L, 1200080426L);
    l3 = a(l3, l4, l1, l2, arrayOfLong2[6], 17L, 2821735955L);
    l2 = a(l2, l3, l4, l1, arrayOfLong2[7], 22L, 4249261313L);
    l1 = a(l1, l2, l3, l4, arrayOfLong2[8], 7L, 1770035416L);
    l4 = a(l4, l1, l2, l3, arrayOfLong2[9], 12L, 2336552879L);
    l3 = a(l3, l4, l1, l2, arrayOfLong2[10], 17L, 4294925233L);
    l2 = a(l2, l3, l4, l1, arrayOfLong2[11], 22L, 2304563134L);
    l1 = a(l1, l2, l3, l4, arrayOfLong2[12], 7L, 1804603682L);
    l4 = a(l4, l1, l2, l3, arrayOfLong2[13], 12L, 4254626195L);
    l3 = a(l3, l4, l1, l2, arrayOfLong2[14], 17L, 2792965006L);
    l2 = a(l2, l3, l4, l1, arrayOfLong2[15], 22L, 1236535329L);
    l1 = b(l1, l2, l3, l4, arrayOfLong2[1], 5L, 4129170786L);
    l4 = b(l4, l1, l2, l3, arrayOfLong2[6], 9L, 3225465664L);
    l3 = b(l3, l4, l1, l2, arrayOfLong2[11], 14L, 643717713L);
    l2 = b(l2, l3, l4, l1, arrayOfLong2[0], 20L, 3921069994L);
    l1 = b(l1, l2, l3, l4, arrayOfLong2[5], 5L, 3593408605L);
    l4 = b(l4, l1, l2, l3, arrayOfLong2[10], 9L, 38016083L);
    l3 = b(l3, l4, l1, l2, arrayOfLong2[15], 14L, 3634488961L);
    l2 = b(l2, l3, l4, l1, arrayOfLong2[4], 20L, 3889429448L);
    l1 = b(l1, l2, l3, l4, arrayOfLong2[9], 5L, 568446438L);
    l4 = b(l4, l1, l2, l3, arrayOfLong2[14], 9L, 3275163606L);
    l3 = b(l3, l4, l1, l2, arrayOfLong2[3], 14L, 4107603335L);
    l2 = b(l2, l3, l4, l1, arrayOfLong2[8], 20L, 1163531501L);
    l1 = b(l1, l2, l3, l4, arrayOfLong2[13], 5L, 2850285829L);
    l4 = b(l4, l1, l2, l3, arrayOfLong2[2], 9L, 4243563512L);
    l3 = b(l3, l4, l1, l2, arrayOfLong2[7], 14L, 1735328473L);
    l2 = b(l2, l3, l4, l1, arrayOfLong2[12], 20L, 2368359562L);
    l1 = c(l1, l2, l3, l4, arrayOfLong2[5], 4L, 4294588738L);
    l4 = c(l4, l1, l2, l3, arrayOfLong2[8], 11L, 2272392833L);
    l3 = c(l3, l4, l1, l2, arrayOfLong2[11], 16L, 1839030562L);
    l2 = c(l2, l3, l4, l1, arrayOfLong2[14], 23L, 4259657740L);
    l1 = c(l1, l2, l3, l4, arrayOfLong2[1], 4L, 2763975236L);
    l4 = c(l4, l1, l2, l3, arrayOfLong2[4], 11L, 1272893353L);
    l3 = c(l3, l4, l1, l2, arrayOfLong2[7], 16L, 4139469664L);
    l2 = c(l2, l3, l4, l1, arrayOfLong2[10], 23L, 3200236656L);
    l1 = c(l1, l2, l3, l4, arrayOfLong2[13], 4L, 681279174L);
    l4 = c(l4, l1, l2, l3, arrayOfLong2[0], 11L, 3936430074L);
    l3 = c(l3, l4, l1, l2, arrayOfLong2[3], 16L, 3572445317L);
    l2 = c(l2, l3, l4, l1, arrayOfLong2[6], 23L, 76029189L);
    l1 = c(l1, l2, l3, l4, arrayOfLong2[9], 4L, 3654602809L);
    l4 = c(l4, l1, l2, l3, arrayOfLong2[12], 11L, 3873151461L);
    l3 = c(l3, l4, l1, l2, arrayOfLong2[15], 16L, 530742520L);
    l2 = c(l2, l3, l4, l1, arrayOfLong2[2], 23L, 3299628645L);
    l1 = d(l1, l2, l3, l4, arrayOfLong2[0], 6L, 4096336452L);
    l4 = d(l4, l1, l2, l3, arrayOfLong2[7], 10L, 1126891415L);
    l3 = d(l3, l4, l1, l2, arrayOfLong2[14], 15L, 2878612391L);
    l2 = d(l2, l3, l4, l1, arrayOfLong2[5], 21L, 4237533241L);
    l1 = d(l1, l2, l3, l4, arrayOfLong2[12], 6L, 1700485571L);
    l4 = d(l4, l1, l2, l3, arrayOfLong2[3], 10L, 2399980690L);
    l3 = d(l3, l4, l1, l2, arrayOfLong2[10], 15L, 4293915773L);
    l2 = d(l2, l3, l4, l1, arrayOfLong2[1], 21L, 2240044497L);
    l1 = d(l1, l2, l3, l4, arrayOfLong2[8], 6L, 1873313359L);
    l4 = d(l4, l1, l2, l3, arrayOfLong2[15], 10L, 4264355552L);
    l3 = d(l3, l4, l1, l2, arrayOfLong2[6], 15L, 2734768916L);
    l2 = d(l2, l3, l4, l1, arrayOfLong2[13], 21L, 1309151649L);
    l1 = d(l1, l2, l3, l4, arrayOfLong2[4], 6L, 4149444226L);
    l4 = d(l4, l1, l2, l3, arrayOfLong2[11], 10L, 3174756917L);
    l3 = d(l3, l4, l1, l2, arrayOfLong2[2], 15L, 718787259L);
    l2 = d(l2, l3, l4, l1, arrayOfLong2[9], 21L, 3951481745L);
    this.b[0] = this.b[0] + l1;
    this.b[1] = this.b[1] + l2;
    this.b[2] = this.b[2] + l3;
    this.b[3] = this.b[3] + l4;
  }
  
  private static void a(byte[] paramArrayOfbyte, long[] paramArrayOflong, int paramInt) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt; b2 += 4) {
      paramArrayOfbyte[b2] = (byte)(int)(paramArrayOflong[b1] & 0xFFL);
      paramArrayOfbyte[b2 + 1] = (byte)(int)(paramArrayOflong[b1] >>> 8L & 0xFFL);
      paramArrayOfbyte[b2 + 2] = (byte)(int)(paramArrayOflong[b1] >>> 16L & 0xFFL);
      paramArrayOfbyte[b2 + 3] = (byte)(int)(paramArrayOflong[b1] >>> 24L & 0xFFL);
      b1++;
    } 
  }
  
  private static long a(byte paramByte) {
    return ((paramByte >= 0) ? paramByte : (paramByte & 0xFF));
  }
  
  public static String b(String paramString) {
    a a1;
    return (a1 = new a()).a(paramString);
  }
  
  static {
    (new char[16])[0] = '0';
    (new char[16])[1] = '1';
    (new char[16])[2] = '2';
    (new char[16])[3] = '3';
    (new char[16])[4] = '4';
    (new char[16])[5] = '5';
    (new char[16])[6] = '6';
    (new char[16])[7] = '7';
    (new char[16])[8] = '8';
    (new char[16])[9] = '9';
    (new char[16])[10] = 'a';
    (new char[16])[11] = 'b';
    (new char[16])[12] = 'c';
    (new char[16])[13] = 'd';
    (new char[16])[14] = 'e';
    (new char[16])[15] = 'f';
  }
}


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uccommon/a.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */