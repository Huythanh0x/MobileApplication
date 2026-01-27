package dm;

import main.Constants_H;
import main.GameRun;
import main.Key_H;

public class Monster implements Constants_H, Key_H {
   public byte[] monster;
   public short[] monsterPro;
   public byte effect;
   public byte effect_time;

   public Monster(GameRun data, int enemy_name, int enemylevel, int enemy_no) {
      this.monsterPro = new short[8];
      this.monster = new byte[18];
      this.monster[0] = (byte)enemy_name;
      this.monster[2] = (byte)enemylevel;
      byte[] temp_pro = data.monster_pro[enemy_name];
      this.monster[3] = temp_pro[6];
      this.monster[4] = temp_pro[5];
      this.monster[5] = temp_pro[12];
      this.monster[8] = 25;
      this.monster[9] = -1;
      this.monster[10] = -1;
      this.monster[11] = -1;
      this.monster[12] = -1;
      this.monster[13] = -1;
      this.monster[14] = -1;
      this.monster[15] = -1;
      if (enemy_no == -1) {
         this.monster[16] = (byte)(this.monster[3] * 2 + 4);
         this.monster[17] = 2;
      } else {
         byte[] var10000;
         int var10002;
         if (enemy_no == 0) {
            var10000 = this.monster;
            var10002 = this.monster[3] * 2 + 3;
            Ms.i();
            var10000[16] = (byte)(var10002 + Ms.getRandom(2));
            var10000 = this.monster;
            Ms.i();
            var10000[17] = (byte)Ms.getRandom(3);
         } else if (enemy_no == 1) {
            var10000 = this.monster;
            var10002 = this.monster[3] * 2 + 3;
            Ms.i();
            var10000[16] = (byte)(var10002 + (Ms.getRandom(100) < 70 ? 1 : 0));
            var10000 = this.monster;
            Ms.i();
            if (Ms.getRandom(100) < 70) {
               var10002 = 2;
            } else {
               Ms.i();
               var10002 = Ms.getRandom(2);
            }

            var10000[17] = (byte)var10002;
         }
      }

      this.monster[6] = (byte)(this.monster[4] > 3 ? 120 : 100);
      this.monsterPro[2] = (short)(temp_pro[0] + temp_pro[7] * enemylevel / 10);
      this.monsterPro[3] = (short)(temp_pro[1] + temp_pro[8] * enemylevel / 10);
      this.monsterPro[1] = this.monsterPro[3];
      this.monsterPro[0] = this.monsterPro[2];
      this.monsterPro[4] = 0;
      this.setProAFD(temp_pro);
      if (this.monster[3] != -1) {
         data.getMagic(this);
      }

      if (data.monInfoList[enemy_name] == 0) {
         data.monInfoList[enemy_name] = 1;
         ++data.monInfoList[102];
         data.say("宠物图鉴已更新", 0);
      }

      this.effect = 7;
      this.effect_time = 0;
   }

   public Monster() {
   }

   public void setProAFD(byte[] data) {
      this.monsterPro[5] = (short)(data[2] + data[9] * this.monster[2] / 10);
      this.monsterPro[6] = (short)(data[3] + data[10] * this.monster[2] / 10);
      this.monsterPro[7] = (short)(data[4] + data[11] * this.monster[2] / 10);
   }

   public boolean isEffect(int id) {
      return this.effect == id;
   }

   public boolean isMonEffect(int id) {
      return this.effect == id && this.effect_time != 0;
   }

   public boolean isMonReel(int reel) {
      return this.monster[14] == reel || this.monster[15] == reel;
   }

   public boolean isBuffRate(int effect_id) {
      return this.monster[16] == effect_id || this.monster[17] == effect_id;
   }

   public void resetMonster(GameRun data, int evolve) {
      if (evolve > -1) {
         this.monster[5] = (byte)evolve;
      } else if (evolve == -1) {
         byte[] var10000 = this.monster;
         Ms.i();
         var10000[5] = (byte)Ms.getRandom(this.monster[5] + 1);
      }

      this.resetPro(data);
   }

   public void resetPro(GameRun data) {
      short[] var10000;
      if (this.isBuffRate(2)) {
         var10000 = this.monsterPro;
         var10000[2] = (short)(var10000[2] + this.monsterPro[2] * data.inhesion[2] / 100);
      } else if (this.isBuffRate(1)) {
         var10000 = this.monsterPro;
         var10000[2] = (short)(var10000[2] + this.monsterPro[2] * data.inhesion[1] / 100);
      }

      if (this.monsterPro[2] < 1) {
         this.monsterPro[2] = 1;
      }

      this.monsterPro[0] = this.monsterPro[2];
   }

   public void resetMonster(int skill6, int skill7, int fealty) {
      this.monster[16] = (byte)(this.monster[3] * 2 + 4);
      this.monster[17] = 2;
      this.monster[14] = (byte)skill6;
      this.monster[15] = (byte)skill7;
      this.monster[6] = (byte)fealty;
   }

   public void resetBoss(int fealty) {
      this.monster[16] = 4;
      this.monster[17] = 10;
      this.monster[9] = 4;
      this.monster[10] = 9;
      this.monster[11] = 14;
      this.monster[12] = 19;
      this.monster[13] = 24;
      this.monster[14] = 30;
      this.monster[15] = 48;
      this.monster[6] = (byte)fealty;
   }

   public void evolveMonster(GameRun data, int enemy_name, int evolve) {
      this.monster[0] = (byte)enemy_name;
      byte[] temp_pro = data.monster_pro[enemy_name];
      this.monster[4] = temp_pro[5];
      byte[] var10000 = this.monster;
      var10000[5] = (byte)(var10000[5] - evolve);
      this.setProAFD(temp_pro);
      this.monsterPro[2] = (short)(temp_pro[0] + temp_pro[7] * this.monster[2] / 10);
      this.monsterPro[3] = (short)(temp_pro[1] + temp_pro[8] * this.monster[2] / 10);
      this.resetPro(data);
      this.monsterPro[1] = this.monsterPro[3];
      if (data.monInfoList[enemy_name] != 2) {
         if (data.monInfoList[enemy_name] == 0) {
            ++data.monInfoList[102];
         }

         data.addMonInfoListBH();
         data.monInfoList[enemy_name] = 2;
      }

   }
}
