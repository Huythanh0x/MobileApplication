package uccommon;

final class b extends Thread {
   private int a = 100;
   private int b;
   private int c;

   public b(int var1) {
      this.b = var1;
      this.c = 0;
   }

   public final void run() {
      System.out.println("timer running");

      while(true) {
         while(true) {
            try {
               Thread.sleep((long)this.a);
               break;
            } catch (InterruptedException var4) {
            }
         }

         synchronized(this) {
            this.c += this.a;
            if (this.c > this.b) {
               if (e.a) {
                  try {
                     e.b = null;
                  } catch (Exception var2) {
                  }

                  System.out.println("connection timedout close");
                  return;
               }

               System.out.println("connection timedout");
               return;
            }
         }
      }
   }
}
