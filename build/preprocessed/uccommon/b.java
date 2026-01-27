package uccommon;

final class b extends Thread {
  private int a = 100;
  
  private int b;
  
  private int c;
  
  public b(int paramInt) {
    this.b = paramInt;
    this.c = 0;
  }
  
  public final void run() {
    System.out.println("timer running");
    while (true) {
      try {
        Thread.sleep(this.a);
      } catch (InterruptedException interruptedException) {
        continue;
      } 
      synchronized (this) {
        this.c += this.a;
        if (this.c > this.b) {
          if (e.a) {
            try {
              e.b = null;
            } catch (Exception exception) {}
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


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/uccommon/b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */