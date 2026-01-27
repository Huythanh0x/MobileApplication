/*     */ package dm;
/*     */ 
/*     */ import main.Constants_H;
/*     */ import main.GameRun;
/*     */ import main.Key_H;
/*     */ 
/*     */ public class Monster
/*     */   implements Constants_H, Key_H
/*     */ {
/*     */   public byte[] monster;
/*     */   public short[] monsterPro;
/*     */   public byte effect;
/*     */   public byte effect_time;
/*     */   
/*     */   public Monster(GameRun data, int enemy_name, int enemylevel, int enemy_no) {
/*  16 */     this.monsterPro = new short[8];
/*  17 */     this.monster = new byte[18];
/*  18 */     this.monster[0] = (byte)enemy_name;
/*  19 */     this.monster[2] = (byte)enemylevel;
/*     */ 
/*     */     
/*  22 */     byte[] temp_pro = data.monster_pro[enemy_name];
/*  23 */     this.monster[3] = temp_pro[6];
/*  24 */     this.monster[4] = temp_pro[5];
/*  25 */     this.monster[5] = temp_pro[12];
/*     */     
/*  27 */     this.monster[8] = 25;
/*  28 */     this.monster[9] = -1;
/*  29 */     this.monster[10] = -1;
/*  30 */     this.monster[11] = -1;
/*  31 */     this.monster[12] = -1;
/*  32 */     this.monster[13] = -1;
/*  33 */     this.monster[14] = -1;
/*  34 */     this.monster[15] = -1;
/*     */ 
/*     */ 
/*     */     
/*  38 */     if (enemy_no == -1) {
/*  39 */       this.monster[16] = (byte)(this.monster[3] * 2 + 4);
/*  40 */       this.monster[17] = 2;
/*  41 */     } else if (enemy_no == 0) {
/*  42 */       Ms.i(); this.monster[16] = (byte)(this.monster[3] * 2 + 3 + Ms.getRandom(2));
/*  43 */       Ms.i(); this.monster[17] = (byte)Ms.getRandom(3);
/*  44 */     } else if (enemy_no == 1) {
/*  45 */       Ms.i(); this.monster[16] = (byte)(this.monster[3] * 2 + 3 + ((Ms.getRandom(100) < 70) ? 1 : 0));
/*     */       
/*  47 */       Ms.i(); Ms.i(); this.monster[17] = (byte)((Ms.getRandom(100) < 70) ? 2 : Ms.getRandom(2));
/*     */     } 
/*     */ 
/*     */     
/*  51 */     this.monster[6] = (byte)((this.monster[4] > 3) ? 120 : 100);
/*  52 */     this.monsterPro[2] = (short)(temp_pro[0] + temp_pro[7] * enemylevel / 10);
/*  53 */     this.monsterPro[3] = (short)(temp_pro[1] + temp_pro[8] * enemylevel / 10);
/*  54 */     this.monsterPro[1] = this.monsterPro[3];
/*  55 */     this.monsterPro[0] = this.monsterPro[2];
/*     */ 
/*     */     
/*  58 */     this.monsterPro[4] = 0;
/*  59 */     setProAFD(temp_pro);
/*  60 */     if (this.monster[3] != -1) data.getMagic(this);
/*     */     
/*  62 */     if (data.monInfoList[enemy_name] == 0) {
/*  63 */       data.monInfoList[enemy_name] = 1;
/*  64 */       data.monInfoList[102] = (byte)(data.monInfoList[102] + 1);
/*     */       
/*  66 */       data.say("宠物图鉴已更新", 0);
/*     */     } 
/*  68 */     this.effect = 7;
/*  69 */     this.effect_time = 0;
/*  70 */     temp_pro = null;
/*     */   }
/*     */   public Monster() {}
/*     */   
/*     */   public void setProAFD(byte[] data) {
/*  75 */     this.monsterPro[5] = (short)(data[2] + data[9] * this.monster[2] / 10);
/*  76 */     this.monsterPro[6] = (short)(data[3] + data[10] * this.monster[2] / 10);
/*  77 */     this.monsterPro[7] = (short)(data[4] + data[11] * this.monster[2] / 10);
/*     */   }
/*     */   
/*     */   public boolean isEffect(int id) {
/*  81 */     return (this.effect == id);
/*     */   }
/*     */   public boolean isMonEffect(int id) {
/*  84 */     return (this.effect == id && this.effect_time != 0);
/*     */   }
/*     */   
/*     */   public boolean isMonReel(int reel) {
/*  88 */     return (this.monster[14] == reel || this.monster[15] == reel);
/*     */   }
/*     */   public boolean isBuffRate(int effect_id) {
/*  91 */     return (this.monster[16] == effect_id || this.monster[17] == effect_id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetMonster(GameRun data, int evolve) {
/*  96 */     if (evolve > -1) { this.monster[5] = (byte)evolve; }
/*  97 */     else if (evolve == -1)
/*  98 */     { Ms.i(); this.monster[5] = (byte)Ms.getRandom(this.monster[5] + 1); }
/*     */ 
/*     */     
/* 101 */     resetPro(data);
/*     */   }
/*     */   
/*     */   public void resetPro(GameRun data) {
/* 105 */     if (isBuffRate(2)) {
/* 106 */       this.monsterPro[2] = (short)(this.monsterPro[2] + this.monsterPro[2] * data.inhesion[2] / 100);
/* 107 */     } else if (isBuffRate(1)) {
/* 108 */       this.monsterPro[2] = (short)(this.monsterPro[2] + this.monsterPro[2] * data.inhesion[1] / 100);
/* 109 */     }  if (this.monsterPro[2] < 1) this.monsterPro[2] = 1; 
/* 110 */     this.monsterPro[0] = this.monsterPro[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetMonster(int skill6, int skill7, int fealty) {
/* 115 */     this.monster[16] = (byte)(this.monster[3] * 2 + 4);
/* 116 */     this.monster[17] = 2;
/* 117 */     this.monster[14] = (byte)skill6;
/* 118 */     this.monster[15] = (byte)skill7;
/* 119 */     this.monster[6] = (byte)fealty;
/*     */   }
/*     */   
/*     */   public void resetBoss(int fealty) {
/* 123 */     this.monster[16] = 4;
/* 124 */     this.monster[17] = 10;
/* 125 */     this.monster[9] = 4;
/* 126 */     this.monster[10] = 9;
/* 127 */     this.monster[11] = 14;
/* 128 */     this.monster[12] = 19;
/* 129 */     this.monster[13] = 24;
/* 130 */     this.monster[14] = 30;
/* 131 */     this.monster[15] = 48;
/* 132 */     this.monster[6] = (byte)fealty;
/*     */   }
/*     */   
/*     */   public void evolveMonster(GameRun data, int enemy_name, int evolve) {
/* 136 */     this.monster[0] = (byte)enemy_name;
/* 137 */     byte[] temp_pro = data.monster_pro[enemy_name];
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.monster[4] = temp_pro[5];
/* 142 */     this.monster[5] = (byte)(this.monster[5] - evolve);
/* 143 */     setProAFD(temp_pro);
/* 144 */     this.monsterPro[2] = (short)(temp_pro[0] + temp_pro[7] * this.monster[2] / 10);
/* 145 */     this.monsterPro[3] = (short)(temp_pro[1] + temp_pro[8] * this.monster[2] / 10);
/* 146 */     resetPro(data);
/* 147 */     this.monsterPro[1] = this.monsterPro[3];
/* 148 */     temp_pro = null;
/* 149 */     if (data.monInfoList[enemy_name] != 2) {
/* 150 */       if (data.monInfoList[enemy_name] == 0)
/* 151 */         data.monInfoList[102] = (byte)(data.monInfoList[102] + 1); 
/* 152 */       data.addMonInfoListBH();
/* 153 */       data.monInfoList[enemy_name] = 2;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Monster.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */