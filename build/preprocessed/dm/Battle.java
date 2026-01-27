package dm;

import main.Constants_H;

public class Battle implements Constants_H {
   public Monster[] mon;
   public byte now_id;
   public byte action;
   public byte skill;
   public byte dead;
   public byte act_num = 1;
   public byte fs_level;
   public byte rate_off;
   public byte throw_state = -1;
   public byte[] countS = new byte[10];
   public byte[] cThrow = new byte[4];
   public byte bg_id;
   public byte[] ceff = new byte[6];
   public byte baoji;
   public short chp;
   public short cexp;
   public short[][] hit = new short[3][5];
   public boolean b_renascence = false;

   public Battle(Monster[] _mon) {
      this.mon = _mon;
   }

   public Monster getMon() {
      return this.mon[this.now_id];
   }

   public void initHit() {
      for(byte i = 0; i < this.hit.length; ++i) {
         this.hit[i][1] = 0;
      }

   }

   public void addHit(int h, int type, int i) {
      this.hit[i][0] = (short)type;
      short[] var10000 = this.hit[i];
      var10000[1] = (short)(var10000[1] + h);
      this.hit[i][2] = 0;
      this.hit[i][3] = 0;
      this.hit[i][4] = 0;
   }
}
