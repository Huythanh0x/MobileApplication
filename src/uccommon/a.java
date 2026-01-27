package uccommon;

public final class a {
   private static byte[] a;
   private long[] b = new long[4];
   private long[] c = new long[2];
   private byte[] d = new byte[64];
   private String e;
   private byte[] f = new byte[16];

   static {
      char[] var10000 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      a = new byte[]{-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   public final String a(String var1) {
      this.a();
      this.a(var1.getBytes(), var1.length());
      byte[] var3;
      a(var3 = new byte[8], this.c, 8);
      int var4 = (var4 = (int)(this.c[0] >>> 3) & 63) >= 56 ? 120 - var4 : 56 - var4;
      this.a(a, var4);
      this.a(var3, 8);
      a(this.f, this.b, 16);
      this.e = "";

      for(int var5 = 0; var5 < 16; ++var5) {
         StringBuffer var10003 = new StringBuffer(String.valueOf(this.e));
         byte var2 = this.f[var5];
         char[] var6 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
         char[] var7;
         (var7 = new char[2])[0] = var6[var2 >>> 4 & 15];
         var7[1] = var6[var2 & 15];
         this.e = var10003.append(new String(var7)).toString();
      }

      return this.e;
   }

   public a() {
      this.a();
   }

   private void a() {
      this.c[0] = 0L;
      this.c[1] = 0L;
      this.b[0] = 1732584193L;
      this.b[1] = 4023233417L;
      this.b[2] = 2562383102L;
      this.b[3] = 271733878L;
   }

   private long a(long var1, long var3, long var5, long var7, long var9, long var11, long var13) {
      return (long)((int)(var1 += (var3 & var5 | ~var3 & var7) + var9 + var13) << (int)var11 | (int)var1 >>> (int)(32L - var11)) + var3;
   }

   private long b(long var1, long var3, long var5, long var7, long var9, long var11, long var13) {
      return (long)((int)(var1 += (var3 & var7 | var5 & ~var7) + var9 + var13) << (int)var11 | (int)var1 >>> (int)(32L - var11)) + var3;
   }

   private long c(long var1, long var3, long var5, long var7, long var9, long var11, long var13) {
      return (long)((int)(var1 += (var3 ^ var5 ^ var7) + var9 + var13) << (int)var11 | (int)var1 >>> (int)(32L - var11)) + var3;
   }

   private long d(long var1, long var3, long var5, long var7, long var9, long var11, long var13) {
      return (long)((int)(var1 += (var5 ^ (var3 | ~var7)) + var9 + var13) << (int)var11 | (int)var1 >>> (int)(32L - var11)) + var3;
   }

   private void a(byte[] var1, int var2) {
      byte[] var3 = new byte[64];
      int var4 = (int)(this.c[0] >>> 3) & 63;
      long[] var10000 = this.c;
      if ((var10000[0] += (long)(var2 << 3)) < (long)(var2 << 3)) {
         int var10002 = (int) this.c[1]++;
      }

      var10000 = this.c;
      var10000[1] += (long)(var2 >>> 29);
      int var5 = 64 - var4;
      if (var2 >= var5) {
         a(this.d, var1, var4, 0, var5);
         this.a(this.d);

         for(var5 = var5; var5 + 63 < var2; var5 += 64) {
            a(var3, var1, 0, var5, 64);
            this.a(var3);
         }

         var4 = 0;
      } else {
         var5 = 0;
      }

      a(this.d, var1, var4, var5, var2 - var5);
   }

   private static void a(byte[] var0, byte[] var1, int var2, int var3, int var4) {
      for(int var5 = 0; var5 < var4; ++var5) {
         var0[var2 + var5] = var1[var3 + var5];
      }

   }

   private void a(byte[] var1) {
      long var2 = this.b[0];
      long var4 = this.b[1];
      long var6 = this.b[2];
      long var8 = this.b[3];
      long[] var10;
      long[] var10000 = var10 = new long[16];
      boolean var14 = true;
      byte[] var11 = var1;
      long[] var15 = var10000;
      int var12 = 0;

      for(int var13 = 0; var13 < 64; var13 += 4) {
         var15[var12] = a(var11[var13]) | a(var11[var13 + 1]) << 8 | a(var11[var13 + 2]) << 16 | a(var11[var13 + 3]) << 24;
         ++var12;
      }

      var2 = this.a(var2, var4, var6, var8, var10[0], 7L, 3614090360L);
      var8 = this.a(var8, var2, var4, var6, var10[1], 12L, 3905402710L);
      var6 = this.a(var6, var8, var2, var4, var10[2], 17L, 606105819L);
      var4 = this.a(var4, var6, var8, var2, var10[3], 22L, 3250441966L);
      var2 = this.a(var2, var4, var6, var8, var10[4], 7L, 4118548399L);
      var8 = this.a(var8, var2, var4, var6, var10[5], 12L, 1200080426L);
      var6 = this.a(var6, var8, var2, var4, var10[6], 17L, 2821735955L);
      var4 = this.a(var4, var6, var8, var2, var10[7], 22L, 4249261313L);
      var2 = this.a(var2, var4, var6, var8, var10[8], 7L, 1770035416L);
      var8 = this.a(var8, var2, var4, var6, var10[9], 12L, 2336552879L);
      var6 = this.a(var6, var8, var2, var4, var10[10], 17L, 4294925233L);
      var4 = this.a(var4, var6, var8, var2, var10[11], 22L, 2304563134L);
      var2 = this.a(var2, var4, var6, var8, var10[12], 7L, 1804603682L);
      var8 = this.a(var8, var2, var4, var6, var10[13], 12L, 4254626195L);
      var6 = this.a(var6, var8, var2, var4, var10[14], 17L, 2792965006L);
      var4 = this.a(var4, var6, var8, var2, var10[15], 22L, 1236535329L);
      var2 = this.b(var2, var4, var6, var8, var10[1], 5L, 4129170786L);
      var8 = this.b(var8, var2, var4, var6, var10[6], 9L, 3225465664L);
      var6 = this.b(var6, var8, var2, var4, var10[11], 14L, 643717713L);
      var4 = this.b(var4, var6, var8, var2, var10[0], 20L, 3921069994L);
      var2 = this.b(var2, var4, var6, var8, var10[5], 5L, 3593408605L);
      var8 = this.b(var8, var2, var4, var6, var10[10], 9L, 38016083L);
      var6 = this.b(var6, var8, var2, var4, var10[15], 14L, 3634488961L);
      var4 = this.b(var4, var6, var8, var2, var10[4], 20L, 3889429448L);
      var2 = this.b(var2, var4, var6, var8, var10[9], 5L, 568446438L);
      var8 = this.b(var8, var2, var4, var6, var10[14], 9L, 3275163606L);
      var6 = this.b(var6, var8, var2, var4, var10[3], 14L, 4107603335L);
      var4 = this.b(var4, var6, var8, var2, var10[8], 20L, 1163531501L);
      var2 = this.b(var2, var4, var6, var8, var10[13], 5L, 2850285829L);
      var8 = this.b(var8, var2, var4, var6, var10[2], 9L, 4243563512L);
      var6 = this.b(var6, var8, var2, var4, var10[7], 14L, 1735328473L);
      var4 = this.b(var4, var6, var8, var2, var10[12], 20L, 2368359562L);
      var2 = this.c(var2, var4, var6, var8, var10[5], 4L, 4294588738L);
      var8 = this.c(var8, var2, var4, var6, var10[8], 11L, 2272392833L);
      var6 = this.c(var6, var8, var2, var4, var10[11], 16L, 1839030562L);
      var4 = this.c(var4, var6, var8, var2, var10[14], 23L, 4259657740L);
      var2 = this.c(var2, var4, var6, var8, var10[1], 4L, 2763975236L);
      var8 = this.c(var8, var2, var4, var6, var10[4], 11L, 1272893353L);
      var6 = this.c(var6, var8, var2, var4, var10[7], 16L, 4139469664L);
      var4 = this.c(var4, var6, var8, var2, var10[10], 23L, 3200236656L);
      var2 = this.c(var2, var4, var6, var8, var10[13], 4L, 681279174L);
      var8 = this.c(var8, var2, var4, var6, var10[0], 11L, 3936430074L);
      var6 = this.c(var6, var8, var2, var4, var10[3], 16L, 3572445317L);
      var4 = this.c(var4, var6, var8, var2, var10[6], 23L, 76029189L);
      var2 = this.c(var2, var4, var6, var8, var10[9], 4L, 3654602809L);
      var8 = this.c(var8, var2, var4, var6, var10[12], 11L, 3873151461L);
      var6 = this.c(var6, var8, var2, var4, var10[15], 16L, 530742520L);
      var4 = this.c(var4, var6, var8, var2, var10[2], 23L, 3299628645L);
      var2 = this.d(var2, var4, var6, var8, var10[0], 6L, 4096336452L);
      var8 = this.d(var8, var2, var4, var6, var10[7], 10L, 1126891415L);
      var6 = this.d(var6, var8, var2, var4, var10[14], 15L, 2878612391L);
      var4 = this.d(var4, var6, var8, var2, var10[5], 21L, 4237533241L);
      var2 = this.d(var2, var4, var6, var8, var10[12], 6L, 1700485571L);
      var8 = this.d(var8, var2, var4, var6, var10[3], 10L, 2399980690L);
      var6 = this.d(var6, var8, var2, var4, var10[10], 15L, 4293915773L);
      var4 = this.d(var4, var6, var8, var2, var10[1], 21L, 2240044497L);
      var2 = this.d(var2, var4, var6, var8, var10[8], 6L, 1873313359L);
      var8 = this.d(var8, var2, var4, var6, var10[15], 10L, 4264355552L);
      var6 = this.d(var6, var8, var2, var4, var10[6], 15L, 2734768916L);
      var4 = this.d(var4, var6, var8, var2, var10[13], 21L, 1309151649L);
      var2 = this.d(var2, var4, var6, var8, var10[4], 6L, 4149444226L);
      var8 = this.d(var8, var2, var4, var6, var10[11], 10L, 3174756917L);
      var6 = this.d(var6, var8, var2, var4, var10[2], 15L, 718787259L);
      var4 = this.d(var4, var6, var8, var2, var10[9], 21L, 3951481745L);
      var10000 = this.b;
      var10000[0] += var2;
      var10000 = this.b;
      var10000[1] += var4;
      var10000 = this.b;
      var10000[2] += var6;
      var10000 = this.b;
      var10000[3] += var8;
   }

   private static void a(byte[] var0, long[] var1, int var2) {
      int var3 = 0;

      for(int var4 = 0; var4 < var2; var4 += 4) {
         var0[var4] = (byte)((int)(var1[var3] & 255L));
         var0[var4 + 1] = (byte)((int)(var1[var3] >>> 8 & 255L));
         var0[var4 + 2] = (byte)((int)(var1[var3] >>> 16 & 255L));
         var0[var4 + 3] = (byte)((int)(var1[var3] >>> 24 & 255L));
         ++var3;
      }

   }

   private static long a(byte var0) {
      return (long)(var0 >= 0 ? var0 : var0 & 255);
   }

   public static String b(String var0) {
      return (new a()).a(var0);
   }
}
