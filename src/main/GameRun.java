package main;

import com.nokia.mid.ui.DirectGraphics;
import dm.Battle;
import dm.Monster;
import dm.Ms;
import dm.Sound;
import dm.Sprite;
import dm.Ui;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import minigame.Mg;

public class GameRun extends GameRun_F {
   public byte immueEnemy = 0;
   public Map map;
   public int money = 1000;
   public int coin = 10;
   public byte view_state = 1;
   public byte select_T;
   public byte select_S;
   public byte createOver;
   public byte threadType = 0;
   public byte t_length;
   public byte line_max;
   public byte popMenu = -1;
   public byte b_c;
   public byte mini_state = 0;
   public static int run_state = 1;
   private byte buyItemID = 0;
   private byte battle_state = 0;
   private byte t_battle_state;
   private byte battle_type;
   private boolean saved;
   byte showtime = 0;
   private Image[] effectImage;
   private byte[][] buyItem;
   private short[][] buyPrice;
   public byte time_count;
   public byte buyOk = 0;
   public byte off = 0;
   private Image item_img;
   private byte[][] item_modules;
   private byte list_rows = 8;
   private byte infoStart = 0;
   private byte[][] infoxy = new byte[][]{{15, 14, 10, 6, 3, 1}, {23, 23, 22, 21, 20, 18}, {15, 18, 20, 21, 22, 23}, {0, 0, 1, 5, 9, 12}, {0, 1, 3, 6, 11, 14}, {15, 18, 20, 21, 22, 23}, {23, 23, 22, 21, 20, 18}, {15, 14, 10, 6, 3, 1}};
   private byte[][] monInfo_dir;
   int sell_money = 0;
   private byte mon_action;
   private byte mon_action_c = 0;
   private byte[][] itemMine;
   private byte item_type;
   private byte[] initFealty = new byte[]{100, 120};
   public byte say_c;
   public byte say_mode;
   public short enemyOff;
   public short say_s;
   public byte cityName_c;
   public byte bg_c;
   private Image[] imgItem;
   private Image[] imgBG;
   short cThrowX;
   short cThrowY;
   short cThrowS;
   String strHit;
   byte[] zb = new byte[]{-6, -4, -3, -2, -1, 0, 1, 2, 3, 4, 8, -3, 2, -1, 1, 1};
   private byte[][] Shuxing = new byte[][]{{2, 3}, {3, 4}, {4, 0}, {0, 1}, {1, 2}};
   private int hit_rate = 0;
   short exp;
   public byte magic_id = -2;
   public int magic_x = 0;
   public int magic_y = 0;
   public Sprite[] mon = new Sprite[2];
   public Sprite[] magic = new Sprite[6];
   private short[] now_action_Magic = new short[6];
   private short[] now_time_Magic = new short[6];
   public byte overMode = 0;
   public byte[][] enemyList;
   private boolean[] evolve = new boolean[]{true, true, true, true, true};
   private byte[] now_action = new byte[2];
   private byte[] now_time = new byte[2];
   String[] battleSay = new String[]{"敌人出现了，战斗吧！", "考验的时刻来了，训练师！", "尽情捕捉宠物吧！"};
   public boolean isUpdateList = false;
   public byte[] mapMove;
   public byte[][] mapRect;
   public byte[] monPro;
   public byte first_battle = 0;
   public byte lastSkill = 0;
   public byte dead_c;
   public byte[][] levelUp_in_battle;
   public byte[] mon_in_battle;
   public short[][] proReplace;
   Battle myB;
   Battle enB;
   Battle am;
   Battle dm;
   byte getSkill;
   public byte[][] mouduls;
   public byte[] inhesion;
   public byte[][] mList_id;
   public byte[] makeLevel;
   public byte[] musicNo;
   public byte[] npc2ImageType;
   public byte[][] monster_pro;
   private StringBuffer[] city_name;
   private StringBuffer[] nameTemp;
   private StringBuffer[] item_help;
   private StringBuffer[] item_name;
   private StringBuffer[] NAME;
   private StringBuffer[] buff_name;
   private StringBuffer[] buff_help;
   private StringBuffer[] skill_help;
   private StringBuffer[] skill_name;
   private StringBuffer[] monsterInfo;
   public StringBuffer[] monsterT;
   private byte[] skill_list = new byte[9];
   private byte[][] skill;
   private byte[][] skill_up;
   private byte[][] monAppearMap;
   private byte[][] monsterMake;
   public Image[] imgIco;
   byte b_ico = -124;

   public GameRun(MainCanvas maincanvas) {
      mc = maincanvas;
      this.map = null;
      this.map = new Map(this);
      this.initOtherImage();
      Ui.i().initUiModules();
      this.loadRmsOther();
   }

   public void goMAIN_MENU() {
      mc.logo_c = 29;
      mc.image_logo = null;
      this.setNull(true);
      this.myMonsters = null;
      this.cMonsters = null;
      System.gc();
      mc.createFlashImage();
      mc.goMain_menu();
      this.setAction_str(new String[]{"读取进度", "新游戏", "音量      ", "游戏帮助", "关   于", "更多精彩", "退出游戏"});
      this.selectx = this.selecty = (byte)(this.rmsOther[0] == -1 ? 1 : 0);
   }

   public void doPaint(int mode) {
      if (mode != 0) {
         this.b_c = (byte)mode;
      } else {
         run_state = -10;
      }

      mc.paint();
   }

   public void setNull(boolean bb) {
      if (this.map != null) {
         this.map.setNull();
      }

      if (null != this.mon[0]) {
         this.mon[0].nullIMFA();
      }

      if (null != this.mon[1]) {
         this.mon[1].nullIMFA();
      }

      if (bb) {
         this.data_null();
      }

   }

   public void start() {
      this.goRUN_IN_MAP(true);
      this.map.insertNpc();
      this.line_max = 2;
      this.cityName_c = 25;
      if (this.map.anole_temp != -2) {
         this.map.anole_type = this.map.anole_temp;
         this.map.anole_temp = -2;
         this.map.setAnole();
      }

   }

   public void saveGame() {
      this.map.save();
      this.saveMon(0);
      this.saveMon(1);
      this.saveItem();
      this.saveInfoList();
      this.rmsOther[0] = this.map.mapNo;
      Ms.i().rmsOptions(10, this.rmsOther, 2);
      Ms.i().rmsOptions(3, this.rmsNidus, 2);
      Ms.i().rmsOptions(0, (byte[])null, 4);
   }

   public void run_gameRun() {
      byte var1;
      switch (run_state) {
         case -87:
         default:
            break;
         case -86:
         case -15:
            if (this.b_c == 0) {
               if (this.levelUp_in_battle[this.myB.getMon().monster[1]][0] == 1) {
                  this.levelUp_in_battle[this.myB.getMon().monster[1]][0] = 0;
                  this.b_c = 1;
                  this.say_s = 52;
                  this.levelPro(this.myB.now_id, true);
                  this.setStringB("生命：+" + this.proReplace[this.myB.now_id][0] + "#n" + "能量" + "：+" + this.proReplace[this.myB.now_id][1], 240, 0);
                  this.setStringB("力量：+" + this.proReplace[this.myB.now_id][3] + "#n" + "防御" + "：+" + this.proReplace[this.myB.now_id][4] + "#n" + "敏捷" + "：+" + this.proReplace[this.myB.now_id][5], 240, 1);
                  this.initMonStream(2, this.mList_id[this.myB.getMon().monster[0]][0], 1);
               } else {
                  ++this.myB.now_id;
               }

               if (this.myB.now_id >= this.myMon_length) {
                  var1 = 0;
                  boolean var7 = false;

                  StringBuffer var8;
                  for(var8 = new StringBuffer(""); var1 < this.myMon_length; ++var1) {
                     if (this.myMonsters[var1].monster[5] > 0 && this.evolve[var1] && this.isEvolveKind(this.myMonsters[var1].monster[4], this.myMonsters[var1].monster[2]) == -1) {
                        if (!Ms.i().equals(var8, "")) {
                           var8.append("、");
                        }

                        var8.append(this.getNameMon(this.myMonsters[var1].monster[0]));
                        this.evolve[var1] = false;
                        var7 = true;
                     }
                  }

                  if (var7) {
                     var8.append("可以进化了");
                  }

                  StringBuffer var4 = new StringBuffer("");
                  var7 = false;

                  for(var1 = 0; var1 < 5; ++var1) {
                     if (this.getRid(var1) != -2 && this.getNexp(var1, 1) == this.getNexp(var1, 3)) {
                        if (!Ms.i().equals(var4, "")) {
                           var4.append("、");
                        }

                        var4.append(this.monsterT[this.monster_pro[this.getNid(var1)][6]] + "的宠物蛋");
                        var7 = true;
                     }
                  }

                  if (var7) {
                     var4.append("经验已满，可以孵化了。");
                     if (!Ms.i().equals(var8, "")) {
                        var8.append("#n");
                     }

                     var8.append(var4.toString());
                  }

                  if (!Ms.i().equals(var8, "")) {
                     this.say(var8.toString(), -1);
                  }

                  var8 = null;
                  var4 = null;
                  this.setNullBattle();
                  this.goGO_RUNINMAP();
               }
            } else if (this.b_c == 1) {
               this.say_s = Ms.i().mathSpeedDown(this.say_s, 4, true);
            }
            break;
         case -50:
            this.runMonsterAppear();
            break;
         case -31:
            this.runBattleState();
            break;
         case -30:
            if (this.createOver == -1 && ++this.b_c >= 8) {
               this.goMontsterAppear();
            }
            break;
         case -21:
            SMSSender.i(this).runLevel();
            break;
         case -20:
            SMSSender.i(this).run();
            break;
         case -19:
            if (this.cThrowX == 0 && this.b_c == 0) {
               var1 = this.items[this.selectx][this.select[0][0]][0];
               this.deleteItems(var1, 1);
               byte[] var2 = new byte[]{1, 3, 14};
               short var3 = (short)(50 + var2[var1 - 9] * 30 - this.enB.getMon().monster[2] + 60 / this.enB.getMon().monster[2] - this.enB.getMon().monsterPro[0] * 100 / this.enB.getMon().monsterPro[2]);
               var3 = (short)(var3 * (7 - 2 * (this.enB.getMon().monster[5] - 1)) / 10);
               if (this.enB.getMon().isEffect(3)) {
                  var3 = (short)(var3 + 30);
               }

               Object var6 = null;
               if (this.cMon_count == this.max_monsters && this.myMon_length == this.max_takes) {
                  this.say("所能携带的宠物已经达到上限！", -1);
                  this.enB.act_num = 1;
                  this.myB.act_num = 0;
                  run_state = -31;
                  this.battle_state = 9;
               } else {
                  Ms.i();
                  if (Ms.getRandom(100) < var3) {
                  }

                  this.enB.action = 2;
                  this.enB.dead = 1;
                  this.say("捕获" + this.getNameMon(this.enB.getMon().monster[0]) + "已成功！", 0);
                  if (this.getMonster(this.enB.getMon(), -1, false) == 0) {
                     this.myMonsters[this.myMon_length - 1].monster[1] = (byte)(this.myMon_length - 1);
                  }

                  run_state = -31;
                  this.goBattleExp(false);
               }
            } else if (this.b_c == 1 && this.say_c == 0) {
               this.arangeMonster();
               this.setNullBattle();
               this.goGO_RUNINMAP();
            }
            break;
         case -11:
            this.goRUN_IN_MAP(false);
            break;
         case -10:
            this.map.run_map();
            break;
         case 35:
            if (++this.mon_action_c > 50) {
               this.mon_action_c = 0;
               this.mon_action = 1;
            }

            if (this.cur_c == 2 && this.b_c == -1 && this.say_s == 0) {
               this.goView();
            }
            break;
         case 69:
            Mg.i().run();
            break;
         case 100:
            if (this.b_c == 1 && !this.saved) {
               this.saveGame();
               this.saved = true;
            } else if (this.b_c == 1 && this.saved && ++this.showtime > 10) {
               this.b_c = 0;
               this.showtime = 0;
               this.goRUN_IN_MAP(true);
            }
      }

   }

   public void paint_gameRun(Graphics g) {
      switch (run_state) {
         case -50:
            this.drawBattleBG();
            this.drawSelectMenu(g, true, 227);
            this.drawSrcLine(g, 0, 124, 10, false, this.src_c[2] == 0);
            this.drawSrcLine(g, 116, 124, 10, true, this.src_c[3] == 0);
            this.drawPlayerAppear(g);
         case -32:
         case -11:
         case 52:
         default:
            break;
         case -31:
            this.drawBattleBG();
            this.drawPlayer(g);
            this.drawSelectMenu(g, this.battle_state != 2, 227);
            this.paintBattleState(g);
            break;
         case -30:
            this.drawDarkScreen();
            break;
         case -21:
            SMSSender.i(this).paintLevel();
            break;
         case -20:
            SMSSender.i(this).paint();
            break;
         case -19:
            this.drawBattleBG();
            this.drawSelectMenu(g, true, 227);
            this.drawMyMon();
            this.drawEnemy();
            if (this.cThrowY < 17) {
               this.cThrowS += this.cThrowY;
               this.cThrowY = Ms.i().mathSpeedN(this.cThrowY, 17, 2, false);
            }

            this.cThrowX = Ms.i().mathSpeedN(this.cThrowX, 0, 8, false);
            Ui.i().drawImage(this.imgItem[0], 177 - this.cThrowX, this.cThrowS, 1 | 32);
            break;
         case -15:
            this.paintBATTLE_OVER();
            break;
         case -10:
            this.map.paint_map(g);
            break;
         case 18:
            this.drawBUY_ITEM();
            break;
         case 25:
            this.drawMY_BAG(0, 0, 240, 320);
            break;
         case 35:
            if (this.cur_c == 0) {
               this.drawMONSTER_UI(this.mini_state == 6 ? this.cMonsters : this.myMonsters);
            } else if (this.cur_c == 1) {
               this.drawMagicUI(this.mini_state == 6 ? this.cMonsters[this.selecty] : this.myMonsters[this.selecty]);
            } else if (this.cur_c == 2) {
               this.drawEvolveUI(1, this.selecty, false);
            }
            break;
         case 51:
            this.map.drawBlackSRC(g, 30, true);
            this.drawZero("结束", 310);
            break;
         case 60:
            mc.drawRectBG();
            this.showString("战斗失败了,训练师的梦想不能实现了!", 160, 0);
            break;
         case 61:
            mc.drawRectBG();
            this.showString("你要继续游戏，确定吗？", 280, 0);
            this.drawSelectMenu(this.action_str, 82, 120, 76, 2, 0, this.popMenu);
            break;
         case 65:
            this.drawVIEW_COMPUTER();
            break;
         case 66:
            this.drawNidus();
            break;
         case 67:
            this.drawMONSTER_INFO(this.getMonInfo_dir());
            break;
         case 68:
            this.drawLIST_INFO();
            break;
         case 69:
            Mg.i().paint();
            break;
         case 97:
            Ui.i().drawK0(-2, 0, 244, 28, 0);
            Ui.i().drawString("训练师等级：" + this.rmsOther[3], 120, 2, 17, 1, 2);
            this.drawPauseMenu(63, 40, 124, 200);
            Ui.i().drawK0(-2, 292, 244, 28, 0);
            this.gogoWordM(this.gogoString, 294, 16, 3, 0, 2);
            if (this.buyOk != 0) {
               String str = "是否保存游戏?";
               if (this.buyOk == 2) {
                  str = "是否读取进度?";
               } else if (this.buyOk == 3) {
                  str = "之前未保存的部分将会丢失，是否继续？";
               }

               this.showString(str, 60, 0);
               str = null;
            }

            Ui.i().drawYesNo(true, true);
            break;
         case 98:
            mc.drawRectBG();
            Ui.i().drawK1(-5, 260, 250, 60, 3);
            this.showStringM("游戏暂停，按0键返回。", 120, 278, 11, 3);
            break;
         case 100:
            this.b_c = 1;
            if (!this.saved) {
               this.showString("保存游戏中", 20, 0);
            } else if (this.saved) {
               this.showString("游戏保存成功！", 20, 0);
            }
      }

      if (this.say_c != 0) {
         this.map_flashString();
      }

   }

   private boolean magicEffectRate(Battle dmB, Monster am, Monster dm, int skill_no) {
      if (dm.effect != 7) {
         return false;
      } else {
         byte buff = this.skill[skill_no][3];
         byte DebuffRate = this.skill[skill_no][2];
         if (DebuffRate == 0) {
            return false;
         } else {
            if (buff == 0) {
               if (dm.isBuffRate(5)) {
                  DebuffRate += this.inhesion[5];
               } else if (dm.isBuffRate(6)) {
                  DebuffRate += this.inhesion[6];
               }
            } else if (buff == 4) {
               if (dm.isBuffRate(7)) {
                  DebuffRate += this.inhesion[7];
               } else if (dm.isBuffRate(8)) {
                  DebuffRate += this.inhesion[8];
               }
            } else if (buff == 2 || buff == 5) {
               if (dm.isBuffRate(11)) {
                  DebuffRate += this.inhesion[11];
               } else if (dm.isBuffRate(12)) {
                  DebuffRate += this.inhesion[12];
               }
            }

            Ms.i();
            if (Ms.getRandom(100) >= DebuffRate) {
               return false;
            } else {
               dm.effect = buff;
               if (buff != 3 && buff != 4 && buff != 5) {
                  if (buff == 2) {
                     dm.effect_time = (byte)(am.isMonReel(32) ? 5 : 4);
                  } else if (buff == 0) {
                     dm.effect_time = 2;
                  } else if (buff == 6) {
                     if (dm.monster[3] == -1) {
                        dm.effect = 7;
                        this.say("封闭效果，对幽暗鬼王不起作用", -1);
                     } else {
                        dm.effect_time = 4;
                     }
                  }
               } else {
                  dm.effect_time = 4;
               }

               if (buff == 5) {
                  dmB.fs_level = (byte)(skill_no + (am.isMonReel(31) ? this.skill[31][0] : 1));
               }

               return true;
            }
         }
      }
   }

   private void AddMP(int hp, Monster A_Monster) {
      hp += A_Monster.monsterPro[1];
      if (hp > A_Monster.monsterPro[3]) {
         A_Monster.monsterPro[1] = A_Monster.monsterPro[3];
      } else {
         A_Monster.monsterPro[1] = (short)hp;
      }

   }

   private void AddHP(int hp, Monster A_Monster) {
      hp += A_Monster.monsterPro[0];
      if (hp >= A_Monster.monsterPro[2]) {
         A_Monster.monsterPro[0] = A_Monster.monsterPro[2];
      } else {
         A_Monster.monsterPro[0] = (short)hp;
      }

   }

   private void drawEffectImage(Monster monster, int x, int y) {
      if (monster.effect > -1 && monster.effect < 7 && monster.effect_time > 0) {
         Ui var10000 = Ui.i();
         Image var10001 = this.effectImage[monster.effect];
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawImage(var10001, x, y, 8 | 32);
         var10000 = Ui.i();
         String var4 = "" + monster.effect_time;
         var10004 = g;
         var10005 = g;
         var10000.drawNum(var4, x, y, 8 | 32, 0);
      }

   }

   private void effectR(Monster monster) {
      if (monster.effect_time > 0) {
         --monster.effect_time;
      }

      if (monster.effect_time == 0) {
         monster.effect = 7;
      }

   }

   public byte getItemCount(int id) {
      if (this.buyItemID != 2) {
         this.findItemType(id);

         for(int i = 0; i < this.items[this.item_type].length; ++i) {
            if (this.items[this.item_type][i][0] == id) {
               return this.items[this.item_type][i][1];
            }
         }
      } else if (this.buyItemID == 2) {
      }

      return 0;
   }

   public void goBUY_ITEM(int id, int mode) {
      if (mode != 1) {
         this.setAction_str(new String[]{"道具", "矿石", "徽章"});
      } else {
         this.setAction_str(new String[]{"道具", "辅助", "卷轴"});
      }

      this.cur_a = (byte)id;
      this.cur_b = (byte)mode;
      this.select[0][1] = this.select[0][0] = 0;
      run_state = 18;
      this.list_rows = 7;
      this.buyOk = 0;
      this.popMenu = -1;
      byte[] data = Ms.i().getStream("data/shop.d", -1);
      Ms.i();
      Ms.skip = 0;
      this.buyItem = Ms.i().create2Array(data);
      this.buyPrice = Ms.i().createShort2Array(data, 0);
      data = null;
   }

   private short getPrice(int type, int select, boolean bb) {
      short price;
      if (bb) {
         price = type == 2 ? this.buyPrice[1][select] : this.buyPrice[0][this.buyItem[this.cur_a][select]];
         if (this.view_state == -1) {
            price = (short)(price * 12 / 10);
         }
      } else {
         price = (short)(this.buyPrice[0][select] / 5);
      }

      return price;
   }

   private int getBuyItemCount(int type, int select, boolean bb) {
      if (bb) {
         int c0 = type == 2 ? this.coin / this.sell_money : this.money / this.sell_money;
         int c1 = 99 - this.findItem(-2, this.buyItem[this.cur_a][select], true);
         if (c1 < 0) {
            c1 = 0;
         }

         return c0 > c1 ? c1 : c0;
      } else {
         return this.items[this.cur_a][select][1];
      }
   }

   private void drawBUY_ITEM() {
      Ui.i().fillRectB();
      Ui.i().drawK2(1, 2, 238, 318, 0);
      byte i = 0;
      byte ly = 32;
      byte fh = 25;
      if (this.cur_b == 0) {
         Ui.i().drawK1(97, 6, 46, 23, 4);
         Ui.i().drawString(this.action_str[this.cur_a], 120, 7, 17, 0, 0);
      } else {
         Ui.i().drawK1(26 + this.cur_a * 67, 6, 50, 23, 4);

         while(i < 3) {
            Ui.i().drawString(this.action_str[i], 36 + i * 67, 5, 0, this.cur_a == i ? 0 : 4, 0);
            ++i;
         }

         Ui.i().drawTriangle(120, 15, 118, true, true);
      }

      Ui.i().drawK1(6, ly, 214, this.list_rows * fh + 6, 1);
      Ui.i().drawK1(6, ly + fh * this.list_rows + 16, 228, fh * 3, 2);
      i = (byte)(this.cur_b == 1 ? this.itemsLength[this.cur_a] : this.buyItem[this.cur_a].length);
      this.drawItemList(10, ly + 3, 214, this.list_rows, this.select[0], this.cur_a, i, this.cur_b == 1 ? 2 : 1);
      Ui.i().sliding(225, ly + 5, this.list_rows * fh - 6, this.select[0][0], i, true);
      if (i > 0) {
         i = this.cur_b == 1 ? this.items[this.cur_a][this.select[0][0]][0] : this.buyItem[this.cur_a][this.select[0][0]];
         this.showStringM(this.item_help[i].toString(), 120, ly + fh * this.list_rows + 18, 12, 3);
      }

      this.drawMoney(120, 318, 0, this.cur_b != 1 && this.cur_a == 2);
      if (this.popMenu != -1) {
         if (this.buyOk == 1 && this.cur_b != 1 && this.buyItem[this.cur_a][this.select[0][0]] >= 35 && this.makeLevel[this.buyItem[this.cur_a][this.select[0][0]] - 35] > this.rmsOther[3]) {
            Ui.i().drawKuang(-5, 120, 250, 88);
            this.showStringM("需要训练师" + this.makeLevel[this.buyItem[this.cur_a][this.select[0][0]] - 35] + "级才能学习！", 120, 140, 12, 3);
            this.showString("是否购买？", 180, 0);
         } else {
            this.drawBuy(i, 120, 160, 76, 40, this.cur_b == 1 ? 1 : (this.cur_a == 2 ? 16 : 17));
         }
      }

      Ui.i().drawYesNo(true, true);
   }

   private void drawBuy(int id, int x, int y, int w_h, int h_h, int d) {
      Ui.i().drawKuang(x - w_h, y - h_h, w_h << 1, h_h << 1);
      int t = y - h_h + ((d & 16) != 0 ? 2 : 12);
      Ui.i().drawString("数量：" + this.popMenu + "/" + this.t_length, x, t, 17, 3, 0);
      StringBuffer s = new StringBuffer();
      if ((d & 16) != 0) {
         s.append("需要：");
      } else {
         s.append("获得：");
      }

      s.append(this.sell_money * this.popMenu);
      if ((d & 1) != 0) {
         s.append("金");
      } else {
         s.append("徽章");
      }

      Ui.i().drawString(s.toString(), x, t + 20 + 4, 17, 6, 0);
      if ((d & 16) != 0) {
         Ui.i().drawString("现有：" + this.findItem(-2, id, true), x, t + 48, 17, 3, 0);
      }

      Ui.i().drawTriangle(x, y - 2, w_h, true, this.cur_b != 2);
      s = null;
      if (this.buyOk == 1) {
         this.showString("是否" + ((d & 16) != 0 ? "购买" : "卖出") + "？", 180, 0);
      }

   }

   private void keyBuyItem() {
      if (!Ms.i().key_delay()) {
         if (this.popMenu == -1) {
            if (Ms.i().key_Left_Right() && this.cur_b != 0) {
               this.cur_a = Ms.i().select(this.cur_a, 0, 2);
               this.select[0][1] = this.select[0][0] = 0;
            } else if (Ms.i().key_Up_Down()) {
               Ms.i().selectS(this.select[0], 0, this.cur_b == 1 ? this.itemsLength[this.cur_a] : this.buyItem[this.cur_a].length, this.list_rows);
            } else if (Ms.i().key_S1_Num5()) {
               this.popMenu = 1;
               this.sell_money = this.getPrice(this.cur_a, this.cur_b != 1 ? this.select[0][0] : this.items[this.cur_a][this.select[0][0]][0], this.cur_b != 1);
               this.t_length = (byte)this.getBuyItemCount(this.cur_a, this.select[0][0], this.cur_b != 1);
            } else if (Ms.i().key_S2()) {
               this.buyItem = (byte[][])null;
               this.buyPrice = (short[][])null;
               if (this.view_state == -1) {
                  this.goBattleState();
               } else {
                  this.goGO_RUNINMAP();
               }
            }
         } else if (this.buyOk == 0 && Ms.i().key_Left_Right()) {
            this.popMenu = Ms.i().select(this.popMenu, 1, this.t_length);
         } else if (Ms.i().key_S1_Num5()) {
            if (this.buyOk == 1) {
               if (this.cur_b == 1) {
                  this.money += this.sell_money * this.popMenu;
                  this.deleteItems(this.items[this.cur_a][this.select[0][0]][0], this.popMenu);
                  if (this.select[0][0] >= this.itemsLength[this.cur_a]) {
                     if (--this.select[0][0] < 0) {
                        this.select[0][0] = 0;
                     }

                     if (--this.select[0][1] < 0) {
                        this.select[0][1] = 0;
                     }
                  }

                  this.say("获得金钱：" + this.sell_money * this.popMenu, -1);
               } else if (this.isMoneyItem(this.cur_a, this.buyItem[this.cur_a][this.select[0][0]], this.popMenu, this.cur_a != 2) && this.addItem(this.buyItem[this.cur_a][this.select[0][0]], this.popMenu) != -1) {
                  this.say("购买成功", -1);
               }

               this.buyOk = 0;
               this.popMenu = -1;
            } else {
               this.buyOk = 1;
            }
         } else if (Ms.i().key_S2()) {
            if (this.buyOk == 0) {
               this.popMenu = -1;
            } else {
               this.buyOk = 0;
            }
         }

      }
   }

   public boolean isMoneyItem(int var1, int var2, int var3, boolean var4) {
      if (this.findItem(-2, var2, true) + var3 > 99) {
         this.say("数量已到上限", 0);
         return false;
      } else if (var4) {
         if (this.money >= this.sell_money * var3) {
         }

         this.money += this.sell_money * var3;
         return true;
      } else {
         if (this.coin >= this.sell_money * var3) {
         }

         this.coin += this.sell_money * var3;
         return true;
      }
   }

   public boolean isMoney(int var1, boolean var2) {
      if (var2) {
         if (this.money >= var1) {
         }

         this.money += var1;
         return true;
      } else {
         if (this.coin >= var1) {
         }

         this.coin += var1;
         return true;
      }
   }

   private void drawMY_BAG(int x, int y, int w, int h) {
      byte i = (byte)(this.action_str.length - 1);
      Ui.i().fillRectB();
      Ui.i().drawK2(x + 1, y + 3, w - 2, h - 3, 0);
      Ui.i().drawK1(x + 5, y + 20 + 14, w - 23, 26 * this.list_rows, 1);
      Ui.i().drawK1(x + 5, y + 20 + 20 + 26 * this.list_rows, w - 10, 75, 2);
      if (i == 0) {
         Ui.i().drawK1(x + (w >> 1) - 19 - 9, y + 8, 54, 23, 4);
         Ui.i().drawString(this.action_str[i], x + (w >> 1), y + 9, 17, 0, 0);
      } else {
         Ui.i().drawK1(x + (this.selectx + 1) * w / 5 - 22, y + 8, 46, 23, 4);

         while(i > -1) {
            Ui.i().drawString(this.action_str[i], x + (i + 1) * w / 5 - 14, y + 9, 0, this.selectx == i ? 0 : 1, 0);
            --i;
         }

         Ui.i().drawTriangle(x + (w >> 1), y + 16, 116, true, true);
      }

      this.drawItemList(x + 9, y + 20 + 17, w - 23, this.list_rows, this.select[0], this.selectx, this.itemsLength[this.selectx], 0);
      Ui.i().sliding(x + w - 15, y + 20 + 17, this.list_rows * 26 - 6, this.select[0][0], this.itemsLength[this.selectx], true);
      if (this.itemsLength[this.selectx] > 0) {
         this.showStringM(this.item_help[this.items[this.selectx][this.select[0][0]][0]].toString(), x + (w >> 1), y + 20 + 22 + 26 * this.list_rows, 12, 3);
      }

      this.drawMoney(x + (w >> 1), y + h - 2, 0, this.selectx == 2);
      Ui.i().drawYesNo(this.itemsLength[this.selectx] > 0, true);
      if (this.popMenu != -1) {
         this.drawSelectMenu(this.about_a, x + (w >> 1) - 19 - 8, 50, 57, 2, 0, this.popMenu);
      }

   }

   public void drawMoney(int x, int y, int c, boolean bb) {
      String s = (bb ? this.coin : this.money) + (bb ? "徽章" : "金");
      Ui var10000 = Ui.i();
      int var10001 = bb ? 53 : 52;
      int var10002 = x - (Ms.i().getStringWidth(s) >> 1) - 1;
      Graphics var10004 = g;
      Graphics var10005 = g;
      var10000.drawUi(var10001, var10002, y, 8 | 32, 0);
      var10000 = Ui.i();
      var10002 = x + 4;
      int var10003 = y - 1;
      var10004 = g;
      var10005 = g;
      var10000.drawString(s, var10002, var10003, 1 | 32, c, 1);
      s = null;
   }

   private void drawItemList(int x, int y, int w, int show_num, byte[] sel, int itemType, int length, int mode) {
      byte i = sel[1];
      byte fh = 25;
      int wh_temp = y + 2;
      int wh = y + 2;
      String str = "";
      Ui.i().drawListKY(show_num, x - 4, y, w, 4, fh + 1, -1, sel[0] - sel[1], 4, 2);

      for(; i < sel[1] + show_num; ++i) {
         if (i < length) {
            Ui var10000;
            int var10002;
            int var10003;
            Graphics var10004;
            Graphics var10005;
            if (mode != 0 && mode != 2) {
               this.drawItem(this.buyItem[itemType][i], x + 2, y + 4 + (i - sel[1]) * fh, 0);
               byte color = (byte)(sel[0] == i ? 0 : 3);
               Ui.i().drawString(this.getNameItem(this.buyItem[itemType][i]), x + 20, wh + (i - sel[1]) * fh, 0, color, 0);
               if (itemType == 2) {
                  str = "徽章";
               } else {
                  str = "金";
               }

               var10000 = Ui.i();
               String var10001 = this.getPrice(itemType, i, true) + str;
               var10002 = x + w - 12;
               var10003 = wh + 1 + (i - sel[1]) * fh;
               var10004 = g;
               var10005 = g;
               var10000.drawString(var10001, var10002, var10003, 8 | 16, color, 0);
            } else {
               this.drawItem(this.items[itemType][i][0], x + 3, y + 4 + (i - sel[1]) * fh, 0);
               Ui.i().drawString(this.getNameItem(this.items[itemType][i][0]), x + 21, wh + 1 + (i - sel[1]) * fh, 0, sel[0] == i ? 0 : 3, 0);
               if (mode == 2) {
                  str = "" + this.getPrice(itemType, this.items[itemType][i][0], false) + "金";
               } else {
                  str = "x" + this.items[itemType][i][1];
               }

               var10000 = Ui.i();
               var10002 = x + w - 12;
               var10003 = wh + 1 + (i - sel[1]) * fh;
               var10004 = g;
               var10005 = g;
               var10000.drawString(str, var10002, var10003, 8 | 16, sel[0] == i ? 0 : 3, 0);
            }
         }
      }

   }

   public void initItemModules() {
      this.item_img = Ms.i().createImage("data/item");
      Ms.i();
      Ms.skip = 0;
      this.item_modules = Ms.i().create2Array(Ms.i().getStream("data/item_m.d", -1));
   }

   public void drawItem(int action, int x, int y, int anchor) {
      if (action >= 58) {
         action = 40 + this.monster_pro[this.nidusList[0][action - 58]][6];
      } else if (action >= 35) {
         action = 34 + this.makeLevel[action - 35];
      }

      Ui.i().drawRegion(this.item_img, this.item_modules[action][0], this.item_modules[action][1], this.item_modules[action][2], this.item_modules[action][3], x, y, anchor, 0, g);
   }

   private void popBagMenu(int selectx, int select_i) {
      String ts = "返回";
      byte id = this.items[selectx][select_i][0];
      if ((selectx == 0 || id == 32 || id == 33) && this.isBagUse(id)) {
         ts = "使用#n返回";
      } else if (selectx == 2) {
         ts = "学习#n返回";
      } else if (this.view_state == 2 && selectx == 3) {
         ts = "孵化#n返回";
      }

      this.setStringB(ts, 240, 0);
      this.popMenu = 0;
   }

   private boolean isBagUse(int id) {
      if (this.view_state == -1) {
         return id != 14 && id != 15;
      } else {
         return id != 9 && id != 10 && id != 11 && id != 8;
      }
   }

   private void popBagState() {
      if (Ms.i().key_Up_Down()) {
         this.popMenu = Ms.i().select(this.popMenu, 0, this.about_a.length - 1);
      }

      if (Ms.i().key_S1_Num5()) {
         byte var1 = this.items[this.selectx][this.select[0][0]][0];
         if (!Ms.i().equals(this.about_a[this.popMenu], "使用")) {
            if (Ms.i().equals(this.about_a[this.popMenu], "学习")) {
               this.mini_state = 13;
               this.getSkill = (byte)(var1 - 9);
               this.goVIEW_MONSTER();
            } else if (Ms.i().equals(this.about_a[this.popMenu], "孵化")) {
               if (this.addNidus(var1 - 58)) {
                  this.deleteItems(var1, 1);
                  this.select_it[this.selectx] = this.select[0][0];
                  this.select_st[this.selectx] = this.select[0][1];
               } else {
                  this.say("孵化所已经没有空位了", 0);
               }

               this.popMenu = -1;
            } else {
               this.popMenu = -1;
            }
         } else {
            switch (var1) {
               case 9:
               case 10:
               case 11:
                  if (this.battle_type != 4 && this.battle_type == 1) {
                  }

                  this.goCatchMonster();
                  break;
               case 12:
               case 13:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               case 26:
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               default:
                  this.goVIEW_MONSTER();
                  this.mini_state = 15;
                  break;
               case 14:
               case 15:
                  this.popMenu = -1;
                  if (this.map.notMeet == 0) {
                     this.map.notMeet(1, var1);
                     this.say("使用" + this.getNameItem(var1) + "，" + "获得了" + "躲避怪物效果", 1);
                     this.deleteItems(var1, 1);
                  } else if (this.map.notMeet == 1) {
                     this.say("已拥有躲避怪物效果", 0);
                  }
                  break;
               case 32:
               case 33:
                  if (this.findItem(-2, var1 == 32 ? 33 : 32, true) > 0) {
                     this.deleteItems(32, 1);
                     this.deleteItems(33, 1);
                     byte var2 = 3;
                     byte var3 = 1;
                     byte var4 = -1;
                     Ms.i();
                     byte var5 = (byte)Ms.getRandom(100);
                     if (var5 < 70) {
                        var2 = 0;
                     } else if (var5 < 85) {
                        var2 = 1;
                     } else if (var5 < 95) {
                        var2 = 2;
                     }

                     Ms.i();

                     for(var5 = (byte)Ms.getRandom(100); var3 < this.itemMine[var2].length; var3 = (byte)(var3 + 2)) {
                        if (var5 < this.itemMine[var2][var3]) {
                           var4 = this.itemMine[var2][var3 - 1];
                           break;
                        }
                     }

                     if (var4 == 34 && this.findItem(-2, var4, true) > 0) {
                        var4 = -1;
                     } else if (var4 == -1 || this.findItem(-2, var4, true) >= 99) {
                        var4 = -1;
                     }

                     if (var4 != -1) {
                        this.addItem(var4, 1);
                        this.say("获得：" + this.getNameItem(var4) + "x1", 0);
                     } else {
                        this.say("这个宝箱是空的。", 0);
                     }
                  } else {
                     this.say("没有" + (var1 == 32 ? "钥匙，钥匙在商店中可以购买。" : "宝箱，宝箱在战斗后一定几率获得。"), 0);
                  }

                  this.popMenu = -1;
            }
         }

         Ms.i().correctSelect(this.select[0], this.itemsLength[this.selectx], this.list_rows);
      } else if (Ms.i().key_S2()) {
         this.popMenu = -1;
      }

   }

   private void keyMY_BAG() {
      if (!Ms.i().key_delay()) {
         if (this.popMenu == -1) {
            if (Ms.i().key_S1_Num5() && this.itemsLength[this.selectx] > 0) {
               this.popBagMenu(this.selectx, this.select[0][0]);
            } else if (Ms.i().key_Left_Right()) {
               this.selectx = Ms.i().select(this.selectx, 0, this.action_str.length - 1);
               this.select[0][1] = this.select_st[this.selectx];
               this.select[0][0] = this.select_it[this.selectx];
            } else if (Ms.i().key_S2()) {
               if (this.mini_state == 9) {
                  this.mini_state = 5;
                  this.selecty = this.select_T;
                  this.selecty = 0;
                  this.goVIEW_MONSTER();
               } else if (this.view_state == -1) {
                  this.mini_state = 1;
                  this.goBattleState();
                  this.initMonStream(2, this.mList_id[this.myMonsters[0].monster[0]][0], 1);
                  this.itemMine = (byte[][])null;
               } else if (this.view_state == 2) {
                  run_state = -10;
                  mc.paint();
                  this.goNidus(0);
               } else {
                  this.doPaint(0);
                  this.goYouPAUSE(2);
                  this.itemMine = (byte[][])null;
               }
            } else if (Ms.i().key_Up_Down()) {
               if (this.itemsLength[this.selectx] > 0) {
                  Ms.i().selectS(this.select[0], 0, this.itemsLength[this.selectx], this.list_rows);
               }

               this.select_it[this.selectx] = this.select[0][0];
               this.select_st[this.selectx] = this.select[0][1];
            }
         } else {
            this.popBagState();
         }

      }
   }

   public void goVIEW_COMPUTER(int pop) {
      run_state = 65;
      this.setStringB("存放#n取出#n离开", 240, 3);
      this.popMenu = (byte)pop;
   }

   private void drawVIEW_COMPUTER() {
      Ui.i().drawK2(74, 112, 92, 136, 0);
      this.drawSelectMenu(this.about_d, 97, 140, 46, 2, 0, this.popMenu);
      Ui.i().drawString("寄存宠物", 120, 114, 17, 1, 1);
      Ui.i().drawYesNo(true, true);
   }

   public void goNidus(int pop) {
      run_state = 66;
      this.mini_state = 16;
      this.setStringB("查看孵化#n选择蛋#n离开", 240, 3);
      this.popMenu = (byte)pop;
      this.b_c = -2;
   }

   private void drawNidus() {
      if (this.mini_state == 16) {
         this.drawSelectMenu(this.about_d, 63, 160 - 20 * (this.about_d.length >> 1), 114, 1, 0, this.popMenu);
      } else {
         Ui.i().fillRectB();
         this.drawBG0(this.bg_c, 1, 108, 0, 222);
         Ui.i().drawString(this.popMenu + 1 + "/" + 5, 10, 106, 36, 0, 2);
         Ui.i().drawString(this.getRid(this.popMenu) == -2 ? "此位置目前还是空着的" : this.monsterT[this.monster_pro[this.getNid(this.popMenu)][6]] + "的宠物蛋", 20, 110, 0, 1, 0);
         if (this.getRid(this.popMenu) != -2) {
            if (this.b_c < 2 && this.drawCartoonOne(1, 1, this.mon_action, 177, 97, this.b_c != 1, 0)) {
               this.initMonStream(2, this.mList_id[this.getNid(this.popMenu)][0], 1);
               this.b_c = 2;
            } else if (this.b_c > 1 && this.b_c < 14) {
               this.drawClipPic(this.mList_id[this.getNid(this.popMenu)][1], 1, 177, 97, 80, 77, 15, this.b_c - 2, 0);
               if (++this.b_c == 14) {
                  this.b_c = -1;
               }

               this.mon_action = (byte)(this.mList_id[this.getNid(this.popMenu)][1] * 3);
            }

            if (this.b_c != -1) {
               short barW = 180;
               this.str_cur = this.getNexp(this.popMenu, 1) + "/" + this.getNexp(this.popMenu, 3);
               Ui.i().drawBarOne(20, 148, barW, Ms.i().mathPercent(this.getNexp(this.popMenu, 1), barW - 2, this.getNexp(this.popMenu, 3)), Ms.i().mathPercent(this.getNexp(this.popMenu, 1), barW - 2, this.getNexp(this.popMenu, 3)), 2);
               Ui.i().drawNum(this.str_cur, 20 + (barW - 8 * this.str_cur.length() >> 1), 154, 0, 0);
               Ui.i().drawString(this.getNexp(this.popMenu, 1) == this.getNexp(this.popMenu, 3) ? "孵化已完成" : "孵化中...", 20 + (barW >> 1), 154, 17, 3, 1);
               Ui.i().drawString("孵化所需材料", 20 + (barW >> 1), 188, 17, 3, 1);
               this.drawEvolveMake(this.getNid(this.popMenu), 25, 214, 23);
            } else {
               Ui.i().drawStringY(this.about_a, 26, 150, 0, 3, 25, 3, 0);
               Ui.i().drawStringY(this.about_b, 126, 150, 0, 3, 25, 3, 0);
               Ui.i().drawString("档次：", 58, 233, 0, 3, 1);
               this.drawMonKind(this.monster_pro[this.getNid(this.popMenu)][5], 99, 238, 0);
            }

            if (this.getNexp(this.popMenu, 1) == this.getNexp(this.popMenu, 3)) {
               Ui.i().drawString("按5键完成孵化", 20, 279, 0, 0, 0);
            }
         }

         if (this.b_c == -2) {
            Ui.i().drawTriangle(120, 120, 114, true, true);
         }

         Ui.i().drawYesNo(false, this.b_c != -1);
      }

   }

   private void keyNidus() {
      if (!Ms.i().key_delay()) {
         if (this.mini_state == 16) {
            if (Ms.i().key_Up_Down()) {
               this.popMenu = Ms.i().select(this.popMenu, 0, this.about_d.length - 1);
            } else if (Ms.i().key_S1_Num5()) {
               if (Ms.i().equals(this.about_d[this.popMenu], "查看孵化")) {
                  this.mini_state = 17;
                  this.popMenu = 0;
                  this.initMonStream(0, 56, 1);
                  if (this.getRid(this.popMenu) == -2) {
                     this.bg_c = this.popMenu;
                  } else {
                     this.bg_c = this.monster_pro[this.getNid(this.popMenu)][6];
                     this.mon_action = (byte)(this.bg_c + 23);
                  }
               } else if (Ms.i().equals(this.about_d[this.popMenu], "选择蛋")) {
                  this.view_state = 2;
                  this.goMY_BAG(3);
               } else if (Ms.i().equals(this.about_d[this.popMenu], "离开")) {
                  this.exitNidus();
               }
            } else if (Ms.i().key_S2()) {
               this.exitNidus();
            }
         } else if (this.b_c == -2 && Ms.i().key_Left_Right()) {
            this.popMenu = Ms.i().select(this.popMenu, 0, 4);
            if (this.getRid(this.popMenu) == -2) {
               this.bg_c = this.popMenu;
            } else {
               this.bg_c = this.monster_pro[this.getNid(this.popMenu)][6];
               this.mon_action = (byte)(this.bg_c + 23);
            }
         } else if (Ms.i().key_S1_Num5()) {
            if (this.getRid(this.popMenu) == -2) {
               return;
            }

            if (this.b_c == -2 && this.getNexp(this.popMenu, 1) == this.getNexp(this.popMenu, 3)) {
               if (this.cMon_count == this.max_monsters && this.myMon_length == this.max_takes) {
                  this.say("宠物空间已满", 0);
               } else if (this.isEvolveMake(this.getNid(this.popMenu)) == 0) {
                  this.mon_action = (byte)(this.bg_c + 28);
                  this.b_c = 1;
                  this.setNidusPro(this.getNid(this.popMenu), 1);
                  this.delItemEvolve(this.getNid(this.popMenu));
               } else {
                  this.say("材料不足，不能孵化", 0);
               }
            } else if (this.b_c == -1) {
               this.b_c = -2;
               this.getMonster(this.getNid(this.popMenu), this.getNLevel(this.popMenu), -2, -1);
               this.delNidus(this.popMenu);
               this.initMonStream(0, 56, 1);
            }
         } else if (this.b_c == -2 && Ms.i().key_S2()) {
            this.exitNidus();
         }

      }
   }

   public void exitNidus() {
      run_state = -10;
      this.mon[1] = null;
      this.initNidusMap(1);
   }

   private void setNidusPro(int i, int level) {
      StringBuffer sbuff = new StringBuffer();
      sbuff.append("生命：" + (this.monster_pro[i][0] + this.monster_pro[i][7] * level / 10) + "#n");
      sbuff.append("能量：" + (this.monster_pro[i][1] + this.monster_pro[i][8] * level / 10) + "#n");
      sbuff.append("进化：" + this.monster_pro[i][12]);
      this.setStringB(sbuff.toString(), 240, 0);
      sbuff.delete(0, sbuff.length());
      sbuff.append("力量：" + (this.monster_pro[i][3] + this.monster_pro[i][10] * level / 10) + "#n");
      sbuff.append("防御：" + (this.monster_pro[i][4] + this.monster_pro[i][11] * level / 10) + "#n");
      sbuff.append("敏捷：" + (this.monster_pro[i][4] + this.monster_pro[i][11] * level / 10));
      this.setStringB(sbuff.toString(), 240, 1);
      sbuff = null;
   }

   public void initNidusMap(int mode) {
      if (null == this.nidusMap) {
         this.nidusMap = new byte[5];
      }

      byte i = 0;
      if (mode == 0) {
         while(i < this.nidusMap.length) {
            this.nidusMap[i] = -1;
            ++i;
         }
      } else {
         for(; i < this.nidusMap.length; ++i) {
            if (this.nidusMap[i] != -1) {
               if (this.getRid(i) != -2) {
                  this.map.npc[0][this.nidusMap[i]].other[4] = 1;
                  this.map.npc[0][this.nidusMap[i]].other[7] = (byte)(17 + this.monster_pro[this.getNid(i)][6]);
               } else {
                  this.map.npc[0][this.nidusMap[i]].other[4] = 0;
               }
            }
         }
      }

   }

   private void drawLIST_INFO() {
      Ui.i().fillRectB();
      this.drawInfoBG(24, 25);
      Ui var10000 = Ui.i();
      String var10001 = "发现度 " + Ms.i().getPrecision(this.monInfoList[102] * 1000 / 102) + "%";
      Graphics var10004 = g;
      Graphics var10005 = g;
      var10000.drawString(var10001, 228, 25, 8 | 16, 3, 1);
      Ui.i().drawString(this.monsterT[this.cur_a].toString(), 28, 25, 0, 1, 2);
      var10000 = Ui.i();
      var10001 = "捕捉种类 " + this.monInfoList[103] + "/" + 100;
      var10004 = g;
      var10005 = g;
      var10000.drawString(var10001, 120, 317, 1 | 32, 3, 1);

      for(byte i = 0; i < 5; ++i) {
         var10000 = Ui.i();
         int var2 = i + (this.cur_a == i ? 5 : 0);
         int var10002 = 214 * (i + 1) / 6 + 18;
         var10004 = g;
         var10005 = g;
         var10000.drawUi(var2, var10002, 12, 4 | 2, 0);
      }

      this.drawInfoList(32, 44, 190, 24, this.list_rows, this.t_length, this.select[0]);
      this.drawInfoList(32, 44, 190, 24, this.list_rows, this.t_length, this.select[0]);
      Ui.i().drawTriangle(130, 12, 101, true, true);
      Ui.i().drawYesNo(this.monInfoList[this.getMonInfo_dir()] != 0, true);
   }

   private void drawInfoBG(int w, int fh) {
      Ui.i().fillRect(0, 0, fh, w, 320 - fh * 2);
      g.fillRect(0, 0, 240, fh);
      g.fillRect(0, 320 - fh, 240, fh);
      g.fillRect(0, fh - 2, 240, fh);
      Ui.i().fillRect(15400191, 2, fh, w - 4, 320 - fh * 2);
      g.fillRect(0, 2, 240, fh - 4);
      g.fillRect(0, 320 - fh + 2, 240, fh - 4);
      g.fillRect(w, fh, 240, fh - 4);
      Ui.i().fillArc(5422575, -25, 141, 50, 70, 270, 180);
      Ui.i().drawUi(31, 2, 176, 36, 0);
      Ui.i().drawUi(31, 2, 176, 0, 6);
      Ui.i().drawUi(68, 0, 0, 0, 0);
      Ui.i().drawUi(68, 0, 320, 36, 6);
   }

   private void drawInfoList(int x, int y, int w, int sh, int show_num, int listMax, byte[] sel) {
      int yy = y;
      Ui.i().drawK1(x + 19 + 2, y + this.move_y / 8 + sel[0] * sh - 2, w - 19 - 2, sh + 2, 4);
      g.setClip(0, y + sh, 240, (show_num - 2) * sh);
      byte i = 0;

      for(byte j = sel[1]; i < show_num; ++j) {
         if (j >= this.t_length) {
            j = 0;
         }

         byte of;
         if (i == sel[0] - 1) {
            of = 15;
         } else if (i == sel[0]) {
            of = 23;
         } else if (i == sel[0] + 1) {
            of = 15;
         } else {
            of = 0;
         }

         if (this.move_x < 6) {
            if (this.cur_b == 0 && i > sel[0] - 3 && i < sel[0] + 2) {
               of = this.infoxy[i - sel[0] + 2][this.move_x];
            } else if (this.cur_b == 1 && i > sel[0] - 2 && i < sel[0] + 3) {
               of = this.infoxy[i - sel[0] + 5][this.move_x];
            }
         }

         Ui var10000 = Ui.i();
         int var10001 = this.monInfoList[this.monInfo_dir[this.cur_a][j]] == 2 ? 25 : 48;
         int var10002 = x + of - 12;
         int var10003 = yy - this.move_y + i * sh + 4;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawUi(var10001, var10002, var10003, 8 | 16, 0);
         byte color = (byte)(sel[0] == i && this.move_y == 0 ? 0 : (this.monInfoList[this.monInfo_dir[this.cur_a][j]] != 0 ? 3 : -1));
         Ui.i().drawString((this.infoStart + j + 1 < 10 ? "0" : "") + (this.infoStart + j + 1) + " " + (this.monInfoList[this.monInfo_dir[this.cur_a][j]] != 0 ? this.getNameMon(this.monInfo_dir[this.cur_a][j]) : "未知宠物"), x + of, yy - this.move_y + i * sh, 0, color, 0);
         var10000 = Ui.i();
         String var13 = this.getInfoType(this.monAppearMap[this.monInfo_dir[this.cur_a][j]][0]);
         var10002 = x + of + 152;
         var10003 = yy - this.move_y + i * sh;
         var10004 = g;
         var10005 = g;
         var10000.drawString(var13, var10002, var10003, 8 | 16, color, 0);
         ++i;
      }

      Ui.i().drawString((this.infoStart + this.getSelInfo() + 1 < 10 ? "0" : "") + (this.infoStart + this.getSelInfo() + 1), 1, yy + sel[0] * sh + 1, 0, 0, 2);
      this.move_y = Ms.i().mathSpeedN(this.move_y, 0, 4, false);
      this.move_x = Ms.i().mathSpeedN(this.move_x, 6, 1, false);
      Ui.i().sliding(x + w + 5, y + 20 + 10, (show_num - 2) * 23 - 4, sel[1], listMax, false);
      g.setClip(0, 0, 240, 320);
   }

   private String getInfoType(int i) {
      if (i == -1) {
         return "任务";
      } else if (i == -2) {
         return "进化";
      } else if (i == -3) {
         return "异化";
      } else if (i == -4) {
         return "竞技场";
      } else {
         return i == -5 ? "孵化" : "捕获";
      }
   }

   private byte getSelInfo() {
      byte t = (byte)(this.select[0][0] + this.select[0][1]);
      if (t >= this.t_length) {
         t -= this.t_length;
      }

      return t;
   }

   private byte getMonInfo_dir() {
      return this.monInfo_dir[this.cur_a][this.getSelInfo()];
   }

   public void keyLIST_INFO() {
      if (!Ms.i().key_delay()) {
         if (Ms.i().key_S1_Num5()) {
            if (this.monInfoList[this.getMonInfo_dir()] != 0) {
               this.goMONSTER_INFO(this.getMonInfo_dir());
            }
         } else if (Ms.i().key_S2()) {
            this.action_str = null;
            this.monInfo_dir = (byte[][])null;
            this.doPaint(0);
            this.goYouPAUSE(3);
         } else if (Ms.i().key_Left_Right()) {
            this.cur_a = Ms.i().select(this.cur_a, 0, 4);
            this.infoStart = 0;
            if (this.cur_a > 0) {
               for(byte i = 0; i < this.cur_a; ++i) {
                  this.infoStart = (byte)(this.infoStart + this.monInfo_dir[i].length);
               }
            }

            this.t_length = (byte)this.monInfo_dir[this.cur_a].length;
            this.select[0][1] = (byte)(this.t_length - this.select[0][0]);
         } else if (Ms.i().key_Up_Down()) {
            this.select[0][1] = Ms.i().select(this.select[0][1], 0, this.t_length - 1);
            this.move_y = (short)(Ms.i().key_Up() ? 24 : -24);
            this.move_x = 0;
            this.cur_b = (byte)(Ms.i().key_Up() ? 1 : 0);
         }

      }
   }

   private void goLIST_INFO(boolean bb) {
      run_state = 68;
      if (null == this.monInfo_dir) {
         byte[] data = Ms.i().getStream("data/book.d", -1);
         Ms.i();
         Ms.skip = 0;
         this.monInfo_dir = Ms.i().create2Array(data);
         data = null;
      }

      if (bb) {
         this.infoStart = 0;
         this.cur_a = 0;
         this.cur_b = 0;
         this.move_x = 6;
         this.move_y = 0;
         this.t_length = (byte)this.monInfo_dir[this.cur_a].length;
         this.select[0][0] = 5;
         this.select[0][1] = (byte)(this.t_length - this.select[0][0]);
      }

      this.list_rows = 11;
   }

   private void goMONSTER_INFO(int id) {
      run_state = 67;
      this.initMonStream(2, this.mList_id[id][0], 1);
   }

   private void drawMONSTER_INFO(int id) {
      short x = 12;
      short y = 35;
      short w = 100;
      short h = 90;
      short fw = 23;
      short fh = 24;
      Ui.i().fillRectB();
      Ui.i().drawK2(1, 2, 238, 318, 0);
      Ui.i().drawK1(120 - fw * 4 - 4, 6, fw * 8, fh, 4);
      Ui.i().drawK1(x, y, w, h, 1);
      Ui.i().drawK1(x + fw + 8, y + h + 4, 240 - (x + fw + 18), fh * 4, 1);
      Ui.i().drawK1(x, y + h + 4, fw, fh * 4, 3);
      Ui.i().drawK1(x, 307 - fh * 3, 240 - (x << 1) + 2, fh * 3 + 10, 2);
      Ui.i().drawYesNo(false, true);
      Ui.i().drawUi(this.cur_a + 5, x + 2, y + 2, 0, 0);
      Ui.i().drawString(this.getNameMon(id) + "（" + (this.monInfoList[id] == 2 ? "已捕获" : "未捕获") + "）", 120, 6, 17, 0, 0);
      if (this.drawCartoonOne(1, 1, this.mList_id[id][1] * 3 + this.mon_action, x + (w >> 1), y + h - 10, this.mon_action != 1, 0)) {
         this.mon_action = 0;
      }

      Ui.i().drawString("发现地点", x + w + 5, y, 0, 1, 1);
      byte i;
      if (this.monAppearMap[id][0] > -1) {
         for(i = 0; i < this.monAppearMap[id].length; ++i) {
            Ui.i().drawString(this.city_name[this.monAppearMap[id][i]].toString(), x + w + 5, y + 20 * (i + 1), 0, 1, 0);
         }
      } else {
         Ui.i().drawString("未知", x + w + 5, y + 20, 0, 1, 0);
      }

      Ui.i().drawString("进", x + 9 + 2, y + h + 20, 17, 3, 1);
      Ui.i().drawString("化", x + 9 + 2, y + h + 28 + fh, 17, 3, 1);
      Ui.i().drawListKY(3, x + fw + 8, y + h + 10, 240 - (x + fw + 18), 3, fh + 4, 0, -1, 4, 2);
      i = 2;

      for(byte j = 0; i < 7; ++j) {
         Ui.i().drawString("材料：" + (this.monsterMake[id].length != 0 && i < this.monsterMake[id].length ? this.getNameItem(this.monsterMake[id][i]) + " x" + this.monsterMake[id][i + 1] : ""), x + fw + 14, y + h + 12 + (fh + 4) * j, 0, 3, 0);
         i = (byte)(i + 2);
      }

      this.showStringM(this.getNameMonsterInfo(id), 120, 308 - fh * 3, 12, 3);
      if (++this.mon_action_c > 50) {
         this.mon_action_c = 0;
         this.mon_action = 1;
      }

   }

   private void drawMONSTER_UI(Monster[] monsters) {
      Ui.i().fillRectB();
      Ui.i().drawK2(1, 2, 238, 318, 0);
      short lx = 6;
      short ly = 5;
      short lw = 118;
      short lh = 116;
      Ui.i().drawK1(lx, ly + 6, lw, lh, 1);
      if (this.t_length != 0) {
         this.drawMonsterHp(monsters[this.select[0][0]], lx + 30, ly, 74, 2, 2, monsters[this.select[0][0]].monsterPro[4]);
         Ui.i().drawNum(monsters[this.select[0][0]].monster[2] + "级", lx + 2, ly + 10, 0, 0);
         Ui.i().drawUi(monsters[this.select[0][0]].monster[3] + 5, lx + 9, ly + 11, 17, 0);
         if (this.drawCartoonOne(1, 1, this.mList_id[monsters[this.select[0][0]].monster[0]][1] * 3 + this.mon_action, 61, 111, this.mon_action != 1, 0)) {
            this.mon_action = 0;
         }
      }

      ly = (short)(ly + lh + 22);
      lw = (short)(240 - (lx << 1));
      Ui.i().drawK1(lx, ly, lw, 320 - lh - 32, 1);
      Ui.i().drawK(lx + 8, ly + 10, lw - 16, 28, 3);
      Ui.i().drawK(lx + 8, ly + 120 + 6, lw - 16, 28, 3);
      if (this.t_length != 0) {
         this.gogoWordM(this.getNameMonsterInfo(monsters[this.select[0][0]].monster[0]), ly + 120 + 8, lx + 8, 0, 1, 2);
         this.drawMonsterFealty(monsters[this.select[0][0]], lx + 2, ly - 14);
         Ui.i().drawString(this.monsterT[monsters[this.select[0][0]].monster[3]].toString(), lx + 25, ly + 12, 0, 0, 1);
         if (monsters[this.select[0][0]].monster[5] == 0) {
            Ui.i().drawString("不可进化", lx + 115, ly + 12, 0, 0, 1);
         } else {
            Ui.i().drawString("可进化" + monsters[this.select[0][0]].monster[5] + "次", lx + 115, ly + 12, 0, 0, 1);
         }
      }

      this.drawMonPro(monsters, lx, ly);
      lx = 181;
      ly = 0;
      lw = 0;
      if (this.t_length != 0) {
         this.drawMonList(monsters, lx + 2, ly + 13, this.list_rows, this.t_length, this.select[0]);
      }

      Ui.i().sliding(223, ly + 12, lh + 6, this.select[0][0], this.t_length, true);
      Ui.i().drawNum(this.t_length + "/" + (this.mini_state == 6 ? this.max_monsters : this.max_takes), 200, ly + 12, 0, 0);
      if (this.popMenu != -1) {
         this.drawSelectMenu(this.about_d, 101, 30, 57, 2, 0, this.popMenu);
         if (this.buyOk == 1) {
            this.showString(this.gogoString, 140, 0);
         }
      }

      Ui.i().drawYesNo(this.t_length != 0, true);
   }

   public String[] getStrArray(String str, byte wordNumOneLine) {
      int totalLine = str.length() / wordNumOneLine + (str.length() % wordNumOneLine == 0 ? 0 : 1);
      String[] arrayS = new String[totalLine];

      for(int i = 0; i < arrayS.length; ++i) {
         int start = i * wordNumOneLine;
         int end = start + wordNumOneLine;
         if (end > str.length()) {
            end = str.length();
         }

         arrayS[i] = str.substring(start, end);
      }

      return arrayS;
   }

   private void drawMonPro(Monster[] monsters, short lx, short ly) {
      Ui.i().drawStringY(this.about_a, lx + 18, ly + 44, 0, 3, 23, 3, 0);
      Ui.i().drawStringY(this.about_b, lx + 135, ly + 44, 0, 3, 23, 3, 0);
      if (this.t_length != 0) {
         Ui.i().drawStringY(this.about_c, lx + 135 + 38, ly + 44, 0, 3, 23, -1, 0);
         this.drawMonsterHp(monsters[this.select[0][0]], lx + 20 + 38, ly + 56, 48, 0, 0, monsters[this.select[0][0]].monsterPro[0]);
         this.drawMonsterHp(monsters[this.select[0][0]], lx + 20 + 38, ly + 56 + 20 + 3, 48, 1, 0, monsters[this.select[0][0]].monsterPro[1]);
         this.drawMonKind(monsters[this.select[0][0]].monster[4], lx + 20 + 38, ly + 70 + 23, 0);
      }

   }

   private void drawMonList(Monster[] monster, int x, int y, int show_num, int max_length, byte[] select) {
      byte i = select[1];
      if (this.mini_state != 6) {
         Ui var10000 = Ui.i();
         int var10002 = x - 38 - 5;
         int var10003 = y + 2;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawUi(34, var10002, var10003, 8 | 16, 0);
      }

      while(i < select[1] + show_num && i < max_length) {
         if (select[0] == i) {
            Ui.i().drawK(x - 38, y + (i - select[1]) * 24, 76, 21, 1);
         }

         Ui.i().drawString(this.getNameMon(monster[i].monster[0]).toString(), x, y + (i - select[1]) * 24, 17, select[0] == i ? 0 : 1, 1);
         ++i;
      }

   }

   private void drawSkillList(int x, int y, int w, int fontH, int show_num, byte[] sel) {
      byte i = sel[1];
      Ui.i().drawListKY(show_num, x, y, w, 3, fontH, 0, sel[0] - sel[1], 4, 2);

      while(i < sel[1] + show_num && i < this.skill_list[8]) {
         Ui.i().drawUi(this.skill_list[i] > 25 ? 50 : 51, x + 8, y + 8 + (i - sel[1]) * fontH, 0, 0);
         Ui.i().drawString(this.getNameSkill(this.skill_list[i]).toString(), x + 21, y + 1 + (i - sel[1]) * fontH, 0, sel[0] == i ? 0 : 3, 0);
         Ui var10000 = Ui.i();
         String var10001 = this.skill_list[i] <= 30 ? this.skill[this.skill_list[i]][1] + "能量" : "被动";
         int var10002 = x + w - 8;
         int var10003 = y + 1 + (i - sel[1]) * fontH;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawString(var10001, var10002, var10003, 8 | 16, sel[0] == i ? 0 : 3, 0);
         ++i;
      }

   }

   private void drawBuffList(Monster monster, int x, int y, int w, int fontH, int show_num, byte[] sel) {
      byte i = 0;
      Ui.i().drawListKY(show_num, x, y, w, 3, fontH, 0, sel[0] - sel[1], 4, 2);

      for(; i < 2; ++i) {
         if (i <= 0 || monster.monster[16 + i] >= 1) {
            Ui.i().drawUi(49, x + 8, y + 8 + (i - this.select[0][1]) * fontH, 0, 0);
            Ui.i().drawString(this.getNameBuff(monster.monster[16 + i]), x + 21, y + 1 + (i - this.select[0][1]) * fontH, 0, sel[0] == i ? 0 : 3, 0);
         }
      }

   }

   private void drawMagicUI(Monster monsters) {
      Ui.i().fillRectB();
      short lx = 1;
      short ly = 108;
      this.drawBG0(this.bg_c, lx, ly, 0, 216);
      Ui.i().drawK1(120 + (this.popMenu == 0 ? -92 : 15), ly + 3, 76, 22, 4);
      Ui var10000 = Ui.i();
      int var10003 = ly + 3;
      Graphics var10004 = g;
      Graphics var10005 = g;
      var10000.drawString("普通技能", 101, var10003, 8 | 16, this.popMenu == 0 ? 0 : 1, 0);
      Ui.i().drawString("天赋技能", 139, ly + 3, 0, this.popMenu == 0 ? 1 : 0, 0);
      if (this.drawCartoonOne(1, 1, this.mList_id[monsters.monster[0]][1] * 3 + this.mon_action, 177 - this.move_x, 97 - this.move_y, this.mon_action != 1, 0)) {
         this.mon_action = 0;
      }

      this.move_x = Ms.i().mathSpeedDown(this.move_x, 3, false);
      this.move_y = Ms.i().mathSpeedDown(this.move_y, 3, false);
      if (this.popMenu == 0) {
         this.drawSkillList(lx + 7, ly + 20 + 12, 211, 26, 5, this.select[0]);
         if (this.skill_list[this.select[0][0]] != -1) {
            this.gogoWord(this.skill_help[this.skill_list[this.select[0][0]]].toString(), lx + 10, 278, 23, 0, 1, 2);
         }
      } else {
         this.drawBuffList(monsters, lx + 7, ly + 20 + 12, 211, 26, 5, this.select[0]);
         if (monsters.monster[16 + this.select[0][0]] > 0) {
            this.gogoWord(this.buff_help[monsters.monster[16 + this.select[0][0]]].toString(), lx + 10, 278, 23, 0, 1, 2);
         }
      }

      Ui.i().sliding(240 - (lx << 1) - 13, ly + 20 + 13, 320 - ly - 54, this.select[0][0], this.popMenu == 0 ? this.skill_list[8] : (monsters.monster[16 + this.select[0][0]] > 0 ? 2 : 1), true);
      if (this.mini_state != 13) {
         Ui.i().drawTriangle(120, ly + 12, 114, true, true);
      }

      Ui.i().drawYesNo(this.mini_state == 13, true);
   }

   private void drawBG0(int id, int lx, int ly, int sh, int w) {
      Ui.i().drawImage(this.imgBG[id], 0, 0, 0);
      Ui.i().drawK2(lx, ly, 240 - (lx << 1), 320 - ly, 0);
      Ui.i().drawK1(lx + 5, ly + 20 + 7 + sh, w, 320 - ly - 36 - sh, 1);
      Ui.i().drawK(lx + 7, 278, w - 4, 24, 3);
   }

   private void keyMagicUI() {
      if (Ms.i().key_S1_Num5() && this.mini_state == 13) {
         if (this.skill_list[this.select[0][0]] <= 25) {
            this.say("该技能不能替换！", 0);
         } else {
            if (this.skill_list[this.select[0][0]] == this.myMonsters[this.selecty].monster[14]) {
               this.myMonsters[this.selecty].monster[14] = this.getSkill;
            } else {
               this.myMonsters[this.selecty].monster[15] = this.getSkill;
            }

            this.delItemSkill(this.myMonsters[this.selecty]);
         }
      } else if (Ms.i().key_S2()) {
         this.select[0][1] = this.selectx;
         this.select[0][0] = this.selecty;
         this.cur_c = 0;
         this.popMenu = -1;
      } else if (Ms.i().key_Left_Right() && this.mini_state != 13) {
         this.popMenu = (byte)(this.popMenu ^ 1);
         this.select[0][1] = this.select[0][0] = 0;
      } else if (Ms.i().key_Up_Down()) {
         this.introX = 120;
         if (this.popMenu == 0) {
            Ms.i().selectS(this.select[0], 0, this.skill_list[8], 5);
         } else {
            byte[] var10000;
            if (this.mini_state == 6) {
               if (this.cMonsters[this.selecty].monster[17] > 0) {
                  var10000 = this.select[0];
                  var10000[0] = (byte)(var10000[0] ^ 1);
               }
            } else if (this.myMonsters[this.selecty].monster[17] > 0) {
               var10000 = this.select[0];
               var10000[0] = (byte)(var10000[0] ^ 1);
            }
         }
      }

   }

   public void drawEvolveUI(int mode, int id, boolean bb) {
      Ui.i().fillRectB();
      short lx = 1;
      short ly = 108;
      short magicType = 0;
      this.drawBG0(this.bg_c, lx, ly, 5, 223);
      if (mode == 1 && this.b_c > 0) {
         magicType = 5;
         if (this.b_c == 1 && this.drawMagicC(magicType, magicType, magicType == 5 ? 6 : 1, 177, 97, 0)) {
            this.initMonStream(2, this.mList_id[this.myMonsters[id].monster[0]][0], 1);
            this.b_c = 2;
         } else if (this.b_c > 1 && this.b_c < 14) {
            this.drawClipPic(this.mList_id[this.myMonsters[id].monster[0]][1], 1, 177, 97, 80, 77, 15, this.b_c - 2, 0);
            if (++this.b_c == 14) {
               this.b_c = -1;
               this.say("进化成  " + this.getNameMon(this.myMonsters[id].monster[0]), 0);
            }
         }
      } else {
         this.drawCartoonOne(1, 1, this.mList_id[this.myMonsters[id].monster[0]][1] * 3, 177 - this.move_x, 97 - this.move_y, true, 0);
      }

      if (mode == 1 && this.popMenu == 1) {
         this.drawEvolveMake(this.myMonsters[id].monster[0], lx + 25, ly + 20 + 22, 23);
      } else {
         Ui.i().drawStringY(this.about_b, lx + 25, ly + 20 + 22, 0, 3, 25, 3, 0);
         Ui.i().drawStringY(this.about_a, lx + 125, ly + 20 + 22, 0, 3, 25, 3, 0);
      }

      if (mode == 0) {
         this.move_x = this.move_y = 0;
         Ui var10000 = Ui.i();
         String var10001 = this.getNameMon(this.myMonsters[id].monster[0]) + "升到了" + this.myMonsters[id].monster[2] + "级";
         int var10003 = ly + 4;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawString(var10001, 232, var10003, 8 | 16, 1, 0);
         Ui.i().drawImage(this.imgItem[1], lx - this.say_s, ly - 4, 36);
         if (this.levelUp_in_battle[bb ? id : this.myMonsters[id].monster[1]][1] != -1) {
            Ui.i().drawString("习得了技能：" + this.getNameSkill(this.levelUp_in_battle[bb ? id : this.myMonsters[id].monster[1]][1]), 120, 280, 17, 9, 0);
         }
      } else if (mode == 1) {
         Ui.i().drawK1(120 + (this.popMenu == 0 ? -92 : 15), ly + 6, 76, 22, 4);
         Ui.i().drawString("基本属性", 67, ly + 5, 17, this.popMenu == 0 ? 0 : 1, 0);
         Ui.i().drawString("进化素材", 173, ly + 5, 17, this.popMenu == 0 ? 1 : 0, 0);
         Ui.i().drawTriangle(120, ly + 15, 114, true, true);
         this.move_x = Ms.i().mathSpeedDown(this.move_x, 3, false);
         this.move_y = Ms.i().mathSpeedDown(this.move_y, 3, false);
         Ui.i().drawString("确定进化该宠物？", 120, 278, 17, 0, 1);
      }

      Ui.i().drawYesNo(true, true);
   }

   private void keyEvolveUI() {
      if (Ms.i().key_S1_Num5() && this.b_c == 0) {
         byte kind = this.myMonsters[this.selecty].monster[4];
         byte level = this.myMonsters[this.selecty].monster[2];
         byte id = this.myMonsters[this.selecty].monster[0];
         byte dd = this.isEvolveMake(id);
         if (this.isEvolveKind(kind, level) != -1) {
            dd = this.isEvolveKind(kind, level);
         }

         if (dd == 0) {
            this.myMonsters[this.selecty].evolveMonster(this, this.monsterMake[id][1], 1);
         } else if (dd > 0) {
            this.myMonsters[this.selecty].evolveMonster(this, this.monsterMake[id][0], this.myMonsters[this.selecty].monster[5]);
         } else if (dd == -1) {
            this.say("材料不足，不能进化！", 0);
            this.popMenu = 1;
         } else if (dd == -2) {
            this.say("等级没有达到15级，不能进化！", 0);
         } else if (dd == -3) {
            this.say("等级没有达到25级，不能进化！", 0);
         } else {
            this.say("等级没有达到30级，不能进化！", 0);
         }

         if (dd > -1) {
            this.b_c = 1;
            this.setMagic(6);
            this.setShowPro(this.myMonsters[this.selecty]);
            this.popMenu = 0;
            this.delItemEvolve(id);
            if (dd > 0) {
               this.deleteItems(34, 1);
            }
         }
      } else if (Ms.i().key_S2()) {
         this.goView();
         this.setShowPro(this.myMonsters[this.selecty]);
      } else if (Ms.i().key_Left_Right()) {
         if (this.b_c > 0) {
            return;
         }

         this.popMenu = (byte)(this.popMenu ^ 1);
      }

   }

   private void delItemEvolve(byte id) {
      byte i = 2;

      do {
         this.deleteItems(this.monsterMake[id][i], this.monsterMake[id][i + 1]);
         i = (byte)(i + 2);
      } while(i < this.monsterMake[id].length - 1);

   }

   private byte isEvolveKind(byte var1, byte var2) {
      byte var3 = 0;
      if (var1 == 1 && var2 < 1) {
         var3 = -2;
      } else if (var1 == 2 && var2 < 1) {
         var3 = -3;
      } else if (var1 == 3 && var2 < 1) {
         var3 = -4;
      }

      return var3;
   }

   private void goView() {
      this.select[0][1] = this.selectx;
      this.select[0][0] = this.selecty;
      this.cur_c = 0;
      this.popMenu = -1;
      this.setStringB("生命#n能量#n档次", 240, 0);
      this.setStringB("力量#n防御#n敏捷", 240, 1);
   }

   private void drawEvolveMake(int id, int x, int y, int fontH) {
      byte i = 2;
      byte j = 0;

      do {
         Ui.i().drawString(this.getNameItem(this.monsterMake[id][i]) + "（" + this.findItem(-2, this.monsterMake[id][i], true) + "/" + this.monsterMake[id][i + 1] + "）", x, y + j * fontH, 0, 3, 0);
         i = (byte)(i + 2);
         ++j;
      } while(i < this.monsterMake[id].length - 1);

      if (this.monsterMake[id][0] > 0) {
         i = this.findItem(-2, 34, true);
         if (i > 0) {
            Ui.i().drawString(this.getNameItem(34) + "（" + i + "/" + 1 + "）", x, y + j * fontH, 0, -1, 0);
         }
      }

   }

   private byte isEvolveMake(int id) {
      byte i = 2;
      byte evolve = this.monsterMake[id][0];

      while(this.findItem(-2, this.monsterMake[id][i], true) >= this.monsterMake[id][i + 1]) {
         i = (byte)(i + 2);
         if (i >= this.monsterMake[id].length - 1) {
            if (evolve > 0 && this.findItem(-2, 34, true) < 1) {
               evolve = 0;
            }

            return evolve;
         }
      }

      evolve = 1;
      return -1;
   }

   private void key_MONSTER_LIST(Monster[] mon) {
      if (this.popMenu == -1) {
         if (Ms.i().key_S1_Num5() && this.t_length != 0) {
            if (this.view_state == 1) {
               if (this.mini_state == 15) {
                  this.useItem();
               } else if (this.mini_state == 13) {
                  this.keyGetSkill(mon[this.select[0][0]]);
               } else {
                  StringBuffer sbuff = new StringBuffer("天赋#n技能");
                  if (this.mini_state == 4) {
                     if (mon[this.select[0][0]].monster[5] > 0) {
                        sbuff.append("#n进化");
                     }

                     if (this.t_length > 1) {
                        sbuff.append("#n存放#n卖宠");
                     }
                  } else if (this.mini_state == 6) {
                     if (!this.map.gmErr) {
                        sbuff.append("#n取出#n卖宠");
                     } else {
                        sbuff.append("#n卖宠");
                     }
                  } else {
                     if (this.select[0][0] != 0) {
                        sbuff.append("#n参战");
                     }

                     if (mon[this.select[0][0]].monster[5] > 0) {
                        sbuff.append("#n进化");
                     }

                     if (this.t_length > 1) {
                        sbuff.append("#n放生");
                     }
                  }

                  this.setStringB(sbuff.toString(), 240, 3);
                  sbuff = null;
                  this.popMenu = 0;
               }
            } else if (this.view_state < 0) {
               if (this.mini_state == 15) {
                  this.useItem();
               } else {
                  if (this.select[0][0] != 0) {
                     this.setStringB("参战#n技能", 240, 3);
                  } else {
                     this.setStringB("技能", 240, 3);
                  }

                  this.popMenu = 0;
               }
            }
         } else if (Ms.i().key_S2()) {
            if (this.mini_state == 13) {
               this.goMY_BAG(2);
            } else if (this.view_state == 1) {
               if (this.mini_state == 15) {
                  this.goMY_BAG(0);
                  this.mini_state = 3;
               } else if (this.mini_state != 6 && this.mini_state != 4) {
                  this.doPaint(0);
                  this.goYouPAUSE(1);
               } else if (!this.map.gmErr) {
                  this.doPaint(0);
                  this.goVIEW_COMPUTER(this.mini_state == 6 ? 1 : 0);
               } else {
                  this.map.bkEvent_getMon();
               }
            } else if (this.view_state == -2) {
               if (mon[0].monsterPro[0] > 0) {
                  if (this.select[0][0] != 0) {
                     this.initMonStream(2, this.mList_id[mon[0].monster[0]][0], 1);
                  }

                  this.goBattleState();
               } else {
                  this.say("当前宠物不能参战", 0);
               }

               this.view_state = -1;
            } else if (this.view_state == -1) {
               if (this.mini_state == 15) {
                  this.goMY_BAG(0);
               } else {
                  this.say("当前宠物不能参战", 0);
               }
            }
         } else if (Ms.i().key_Up_Down() && this.t_length != 0) {
            Ms.i().selectS(this.select[0], 0, this.t_length, this.list_rows);
            this.initMonStream(2, this.mList_id[mon[this.select[0][0]].monster[0]][0], 1);
            this.setShowPro(mon[this.select[0][0]]);
            this.introT = 120;
         }
      } else {
         this.popState();
      }

   }

   private void keyGetSkill(Monster mon) {
      if (this.getSkill + 9 >= 35 && this.makeLevel[this.getSkill + 9 - 35] > this.rmsOther[3]) {
         this.say("需要训练师" + this.makeLevel[this.getSkill + 9 - 35] + "级才能学习！", 0);
      } else if (mon.isMonReel(this.getSkill)) {
         this.say(this.getNameMon(mon.monster[0]) + "已拥有该技能", 0);
      } else if (mon.monster[14] != -1 && mon.monster[15] != -1) {
         this.initSkillList(mon);
         this.bg_c = mon.monster[3];
         this.restMove(1, 0);
         this.say("请选择要替换的技能！", -1);
      } else {
         if (mon.monster[14] == -1) {
            mon.monster[14] = this.getSkill;
         } else {
            mon.monster[15] = this.getSkill;
         }

         this.delItemSkill(mon);
      }

   }

   private void delItemSkill(Monster mon) {
      this.say(this.getNameMon(mon.monster[0]) + "习得了技能：" + this.getNameSkill(this.getSkill), 0);
      this.goMY_BAG(2);
      this.deleteItems(this.getSkill + 35 - 25 - 1, 1);
      Ms.i().correctSelect(this.select[0], this.itemsLength[this.selectx], this.list_rows);
   }

   private void sellMonster(Monster mon) {
      this.sell_money = 100 + mon.monster[2] * mon.monster[4] * 5;
   }

   private void putMonster() {
      if (this.cMon_count >= this.max_monsters) {
         this.say("宠物空间已满", 0);
      } else if (!this.isMyMonsters(this.select[0][0])) {
         this.say("身上必须携带一只能参战的宠物！", 0);
      } else {
         this.cMonsters[this.cMon_count] = this.myMonsters[this.select[0][0]];
         ++this.cMon_count;
         this.setPutMonster();
      }

   }

   private void setPutMonster() {
      byte i;
      for(i = this.select[0][0]; i < this.myMon_length - 1; ++i) {
         this.myMonsters[i] = this.myMonsters[i + 1];
         this.evolve[i] = this.evolve[i + 1];
      }

      for(i = this.myMon_length; i < this.myMonsters.length; ++i) {
         this.evolve[i] = true;
      }

      --this.myMon_length;
      if (this.select[0][0] == this.myMon_length) {
         --this.select[0][0];
      }

      this.initMonStream(2, this.mList_id[this.myMonsters[this.select[0][0]].monster[0]][0], 1);
      this.setShowPro(this.myMonsters[this.select[0][0]]);
      this.introT = 120;
      this.t_length = this.myMon_length;
      this.popMenu = -1;
      Ms.i().correctSelect(this.select[0], this.t_length, this.list_rows);
   }

   private void changeMonster(Monster a, Monster b) {
      Monster c = new Monster();
      c.monster = a.monster;
      c.effect = a.effect;
      c.monsterPro = a.monsterPro;
      a.monster = b.monster;
      a.effect = b.effect;
      a.monsterPro = b.monsterPro;
      b.monster = c.monster;
      b.effect = c.effect;
      b.monsterPro = c.monsterPro;
      c = null;
   }

   private void takenMonster() {
      if (this.myMon_length < this.max_takes) {
         this.myMonsters[this.myMon_length] = this.cMonsters[this.select[0][0]];
         this.evolve[this.myMon_length] = true;
         ++this.myMon_length;
         this.setTakenMonster();
      } else {
         this.say("所能携带的宠物已经达到上限！", 1);
      }

   }

   private void setTakenMonster() {
      for(byte i = this.select[0][0]; i < this.cMon_count - 1; ++i) {
         this.cMonsters[i] = this.cMonsters[i + 1];
      }

      --this.cMon_count;
      this.t_length = this.cMon_count;
      if (this.select[0][0] == this.cMon_count) {
         --this.select[0][0];
      }

      if (this.t_length > 0) {
         this.initMonStream(2, this.mList_id[this.cMonsters[this.select[0][0]].monster[0]][0], 1);
         this.setShowPro(this.cMonsters[this.select[0][0]]);
      }

      this.introT = 120;
      this.popMenu = -1;
      Ms.i().correctSelect(this.select[0], this.t_length, this.list_rows);
   }

   public void goVIEW_MONSTER() {
      run_state = 35;
      this.selectx = this.selecty = this.cur_c = 0;
      this.t_length = this.mini_state == 6 ? this.cMon_count : this.myMon_length;
      this.select[0][0] = this.select[0][1] = 0;
      this.popMenu = -1;
      this.list_rows = 5;
      this.mon_action = 0;
      this.setStringB("生命#n能量#n档次", 240, 0);
      this.setStringB("力量#n防御#n敏捷", 240, 1);
      this.cur_mon = this.mini_state == 6 ? this.cMonsters[this.select[0][0]] : this.myMonsters[this.select[0][0]];
      if (this.t_length != 0) {
         this.setShowPro(this.cur_mon);
         this.initMonStream(2, this.mList_id[this.cur_mon.monster[0]][0], 1);
      }

   }

   private void setShowPro(Monster monster) {
      StringBuffer sbuff = new StringBuffer();
      sbuff.append(monster.monsterPro[6] + "#n");
      sbuff.append(monster.monsterPro[7] + "#n");
      sbuff.append(monster.monsterPro[5]);
      this.setStringB(sbuff.toString(), 240, 2);
      sbuff = null;
   }

   private void goGO_RUNINMAP() {
      this.b_c = 0;
      this.popMenu = -1;
      System.gc();
      run_state = -11;
   }

   private void goMY_BAG(int type) {
      this.mini_state = 15;
      this.list_rows = 7;
      run_state = 25;
      this.popMenu = -1;
      if (this.view_state == 1) {
         this.setAction_str(new String[]{"道具", "辅助", "卷轴", "孵化"});
      } else if (this.view_state == -1) {
         this.setAction_str(new String[]{"道具"});
      } else if (this.view_state == 2) {
         this.setAction_str(new String[]{"孵化"});
      }

      this.selectx = (byte)type;
      this.selecty = 0;
      this.select[0][1] = this.select_st[this.selectx];
      this.select[0][0] = this.select_it[this.selectx];
      Ms.i().correctSelect(this.select[0], this.itemsLength[this.selectx], this.list_rows);
      this.itemMine = (byte[][])null;
      Ms.i();
      Ms.skip = 0;
      this.itemMine = Ms.i().create2Array(Ms.i().getStream("data/box.d", -1));
   }

   public void popState() {
      if (this.buyOk == 0 && Ms.i().key_Up_Down()) {
         this.popMenu = Ms.i().select(this.popMenu, 0, this.about_d.length - 1);
      } else if (Ms.i().key_S1_Num5()) {
         if (!Ms.i().equals(this.about_d[this.popMenu], "技能") && !Ms.i().equals(this.about_d[this.popMenu], "天赋")) {
            if (Ms.i().equals(this.about_d[this.popMenu], "参战")) {
               if (this.myMonsters[this.select[0][0]].monsterPro[0] <= 0) {
                  this.say("当前宠物不能参战", 0);
               } else {
                  if (this.view_state < 0) {
                     for(byte i = 1; i <= this.mon_in_battle[0] && this.mon_in_battle[i] != this.myMonsters[this.select[0][0]].monster[1]; ++i) {
                        if (i == this.mon_in_battle[0]) {
                           this.mon_in_battle[++this.mon_in_battle[0]] = this.myMonsters[this.select[0][0]].monster[1];
                        }
                     }

                     this.initSkillList(this.myMonsters[this.select[0][0]]);
                  }

                  this.changeMonster(this.myMonsters[this.select[0][0]], this.myMonsters[0]);
                  this.initMonStream(2, this.mList_id[this.myMonsters[this.select[0][0]].monster[0]][0], 1);
                  this.setShowPro(this.myMonsters[this.select[0][0]]);
                  if (this.view_state < 0) {
                     this.setMyThrow();
                     run_state = -50;
                     this.battle_state = 0;
                     if (this.view_state == -1) {
                        this.myB.act_num = 1;
                        this.enB.act_num = 1;
                     }

                     this.myB.dead = 0;
                     this.first_battle = 0;
                  }

                  this.lastSkill = 0;
               }

               this.popMenu = -1;
            } else if (Ms.i().equals(this.about_d[this.popMenu], "进化")) {
               this.setEvolveStringB(this.myMonsters[this.select[0][0]]);
               this.bg_c = this.myMonsters[this.select[0][0]].monster[3];
               this.b_c = 0;
               this.restMove(2, 0);
            } else if (Ms.i().equals(this.about_d[this.popMenu], "放生")) {
               if (!this.isMyMonsters(this.select[0][0])) {
                  this.say("身上必须携带一只能参战的宠物！", 0);
               } else if (this.buyOk == 1) {
                  this.buyOk = 0;
                  this.delMonster(this.select[0][0]);
                  if (this.select[0][0] == this.myMon_length) {
                     --this.select[0][0];
                  }

                  this.initMonStream(2, this.mList_id[this.myMonsters[this.select[0][0]].monster[0]][0], 1);
                  this.setShowPro(this.myMonsters[this.select[0][0]]);
                  this.introT = 120;
                  this.t_length = this.myMon_length;
                  this.popMenu = -1;
               } else {
                  this.buyOk = 1;
                  this.gogoString = "放生的宠物将会消失，是否继续？";
               }
            } else if (Ms.i().equals(this.about_d[this.popMenu], "存放")) {
               if (run_state == 35) {
                  this.putMonster();
               } else {
                  this.mini_state = 4;
                  this.view_state = 1;
                  this.goVIEW_MONSTER();
               }
            } else if (Ms.i().equals(this.about_d[this.popMenu], "取出")) {
               if (run_state == 35) {
                  this.takenMonster();
               } else {
                  this.mini_state = 6;
                  this.view_state = 1;
                  this.goVIEW_MONSTER();
               }
            } else if (Ms.i().equals(this.about_d[this.popMenu], "卖宠")) {
               if (this.buyOk == 1) {
                  this.buyOk = 0;
                  this.money += this.sell_money;
                  this.say("获得金钱：" + this.sell_money, 0);
                  if (this.mini_state == 6) {
                     this.setTakenMonster();
                  } else {
                     this.setPutMonster();
                  }
               } else if (this.mini_state == 4 && !this.isMyMonsters(this.select[0][0])) {
                  this.say("身上必须携带一只能参战的宠物！", 0);
               } else {
                  this.buyOk = 1;
                  this.sellMonster(this.mini_state == 4 ? this.myMonsters[this.select[0][0]] : this.cMonsters[this.select[0][0]]);
                  this.gogoString = "获得金钱：" + this.sell_money + "，是否卖出？";
               }
            } else if (Ms.i().equals(this.about_d[this.popMenu], "离开")) {
               this.goRUN_IN_MAP(false);
            }
         } else {
            this.initSkillList(this.mini_state == 6 ? this.cMonsters[this.select[0][0]] : this.myMonsters[this.select[0][0]]);
            if (this.view_state > 0) {
               this.bg_c = this.mini_state == 6 ? this.cMonsters[this.select[0][0]].monster[3] : this.myMonsters[this.select[0][0]].monster[3];
            } else if (this.view_state < 0 && null != this.myB) {
               this.bg_c = this.myB.bg_id;
            }

            this.restMove(1, Ms.i().equals(this.about_d[this.popMenu], "技能") ? 0 : 1);
         }
      } else if (Ms.i().key_S2()) {
         if (this.buyOk != 0) {
            this.buyOk = 0;
         } else if (run_state == 65) {
            this.goRUN_IN_MAP(false);
         } else {
            this.popMenu = -1;
         }
      }

   }

   private void restMove(int type, int i) {
      this.selectx = this.select[0][1];
      this.selecty = this.select[0][0];
      this.cur_c = (byte)type;
      this.select[0][1] = this.select[0][0] = 0;
      this.popMenu = (byte)i;
      this.move_x = 116;
      this.move_y = -14;
   }

   private void setEvolveStringB(Monster monster) {
      byte eid = (byte)(monster.monster[0] + 1);
      byte level = monster.monster[2];
      boolean b_e = false;
      if (this.monsterMake[monster.monster[0]][0] > 0 && this.findItem(-2, 34, true) > 0) {
         eid = this.monsterMake[monster.monster[0]][0];
         b_e = true;
      }

      this.proReplace = (short[][])null;
      this.proReplace = new short[1][6];
      this.proReplace[0][0] = (short)(this.getbuffRateV(monster, this.monster_pro[eid][0] + this.monster_pro[eid][7] * level / 10) - monster.monsterPro[2]);
      this.proReplace[0][1] = (short)(this.monster_pro[eid][1] + this.monster_pro[eid][8] * level / 10 - monster.monsterPro[3]);
      this.proReplace[0][2] = (short)(-(b_e ? monster.monster[5] : 1));
      this.proReplace[0][3] = (short)((byte)(this.monster_pro[eid][3] + this.monster_pro[eid][10] * level / 10 - monster.monsterPro[6]));
      this.proReplace[0][4] = (short)((byte)(this.monster_pro[eid][4] + this.monster_pro[eid][11] * level / 10 - monster.monsterPro[7]));
      this.proReplace[0][5] = (short)((byte)(this.monster_pro[eid][2] + this.monster_pro[eid][9] * level / 10 - monster.monsterPro[5]));
      this.setStringB("生命：+" + this.proReplace[0][0] + "#n" + "能量" + "：+" + this.proReplace[0][1] + "#n" + "进化" + "：" + this.proReplace[0][2], 240, 0);
      this.setStringB("力量：+" + this.proReplace[0][3] + "#n" + "防御" + "：+" + this.proReplace[0][4] + "#n" + "敏捷" + "：+" + this.proReplace[0][5], 240, 1);
      this.proReplace = (short[][])null;
   }

   private void drawMonsterFealty(Monster monster, int x, int y) {
      byte kind = monster.monster[4];
      byte nn = (byte)(this.initFealty[kind < 4 ? 0 : 1] / 20);
      byte n = (byte)(monster.monster[6] / 20);
      this.drawFealty(nn, n, x, y);
   }

   public void drawFealty(int length, int n, int x, int y) {
      for(byte i = 0; i < length; ++i) {
         Ui.i().drawUi(i > n ? 22 : 21, x + i * 14, y, 0, 0);
      }

   }

   private void drawMonKind(byte monKind, int x, int y, int arh) {
      for(byte i = 0; i < 5; ++i) {
         Ui.i().drawUi(monKind > i ? 23 : 24, x + i * 14, y, arh, 0);
      }

   }

   public void keyGameOver1(boolean over) {
      if (Ms.i().key_S1_Num5()) {
         if (this.popMenu == 1) {
            run_state = 0;
            this.action_str = null;
            this.goMAIN_MENU();
         } else if (this.popMenu == 0) {
            this.loadGame();
         }

         this.popMenu = -1;
      } else if (Ms.i().key_Up_Down()) {
         this.popMenu = (byte)(this.popMenu ^ 1);
      }

      Ms.i().keyRelease();
   }

   public void key_gameRun() {
      if (run_state == 98) {
         if (Ms.i().key_Num0()) {
            this.doPaint(0);
            run_state = mc.temp_state;
            Sound.i().setMusic(true);
         }

         Ms.i().keyRelease();
      } else if (this.say_c > 0) {
         Ms.i().keyRelease();
      } else if (this.say_c < 0) {
         if (this.say_s == 0 && Ms.i().key_S1_Num5()) {
            this.say_c = 0;
         }

         Ms.i().keyRelease();
      } else {
         switch (run_state) {
            case -31:
               this.keyBattleState();
               break;
            case -21:
               SMSSender.i(this).keyLevel();
               break;
            case -20:
               SMSSender.i(this).key();
               break;
            case -15:
               if (Ms.i().key_delay()) {
                  return;
               }

               if (this.b_c == 1 && this.say_s == 0) {
                  this.b_c = 0;
               }
               break;
            case -10:
               this.map.key_map();
               break;
            case 18:
               this.keyBuyItem();
               break;
            case 25:
               this.keyMY_BAG();
               break;
            case 35:
               if (Ms.i().key_delay()) {
                  return;
               }

               if (this.cur_c == 0) {
                  this.key_MONSTER_LIST(this.mini_state == 6 ? this.cMonsters : this.myMonsters);
               } else if (this.cur_c == 1) {
                  this.keyMagicUI();
               } else if (this.cur_c == 2) {
                  this.keyEvolveUI();
               }
            case 50:
            case 52:
            default:
               break;
            case 51:
               if (Ms.i().key_Num0()) {
                  this.map.my.state = 0;
                  this.map.eventGoing = 2;
                  Ms.i().keyRelease();
                  this.map.dialog_no = -1;
                  this.goGO_RUNINMAP();
               }
               break;
            case 60:
               Ms.i().keyRelease();
               run_state = 61;
               this.setAction_str(new String[]{"继续", "放弃"});
               this.popMenu = 0;
               break;
            case 61:
               this.keyGameOver1(true);
               break;
            case 65:
               if (Ms.i().key_delay()) {
                  return;
               }

               this.popState();
               break;
            case 66:
               this.keyNidus();
               break;
            case 67:
               if (Ms.i().key_S2()) {
                  this.goLIST_INFO(false);
                  Ms.i().keyRelease();
               }
               break;
            case 68:
               this.keyLIST_INFO();
               break;
            case 69:
               Mg.i().key(this);
               break;
            case 97:
               if (Ms.i().key_delay()) {
                  return;
               }

               if (Ms.i().key_S1_Num5()) {
                  if (this.selectx == 0) {
                     if (this.selecty == 0) {
                        if (this.buyOk == 1) {
                           run_state = 100;
                           this.b_c = 0;
                           this.buyOk = 0;
                        } else {
                           this.buyOk = 1;
                        }
                     } else if (this.selecty == 1) {
                        this.goVIEW_MONSTER();
                     } else if (this.selecty == 2) {
                        this.goMY_BAG(0);
                     } else if (this.selecty == 3) {
                        this.goLIST_INFO(true);
                     } else if (this.selecty == 4) {
                        this.map.goMission(0, true);
                     } else if (this.selecty == 5) {
                        this.goRUN_IN_MAP(true);
                        this.action_str = null;
                     }
                  } else if (this.selecty == 0) {
                     if (this.buyOk == 2) {
                        this.loadGame();
                     } else {
                        this.buyOk = 2;
                     }
                  } else if (this.selecty == 1) {
                     Sound.i().keyVolume(0);
                  } else if (this.selecty == 2) {
                     mc.goHELP_dialog(1);
                  } else if (this.selecty == 3) {
                     if (this.buyOk == 3) {
                        run_state = 0;
                        this.action_str = null;
                        this.goMAIN_MENU();
                        this.buyOk = 0;
                     } else {
                        this.buyOk = 3;
                     }
                  }
               } else if (Ms.i().key_S2()) {
                  if (this.buyOk == 0) {
                     this.action_str = null;
                     this.goRUN_IN_MAP(true);
                  } else {
                     this.doPaint(0);
                     run_state = 97;
                     this.buyOk = 0;
                  }
               } else if (this.buyOk == 0) {
                  if (Ms.i().key_Left_Right()) {
                     this.selectx = (byte)(this.selectx ^ 1);
                     this.selecty = 0;
                     this.setPauseS(this.selectx);
                  } else if (Ms.i().key_Up_Down()) {
                     this.selecty = Ms.i().select(this.selecty, 0, this.action_str.length - 1);
                  }
               }
         }

         if (Ms.i().key_S1_Num5() || Ms.i().key_S2()) {
            Ms.i().keyRelease();
         }

      }
   }

   private void loadGame() {
      this.map.firstDrawMap = 0;
      this.buyOk = 0;
      this.map.mapImg = null;
      System.gc();
      mc.goGameLoading();
   }

   private void drawMonsterHp(Monster monster, int x, int y, int w, int mode, int type, int t2) {
      short t0;
      short t1;
      if (mode < 2) {
         t0 = monster.monsterPro[0 + mode];
         t1 = monster.monsterPro[2 + mode];
      } else {
         if (t2 == 0) {
            t2 = monster.monsterPro[4];
         }

         t0 = (short)t2;
         t1 = this.getMonsterExp(monster);
      }

      Ui.i().drawBarOne(x, y, w, Ms.i().mathPercent(t2, w - 2, t1), Ms.i().mathPercent(t0, w - 2, t1), mode);
      if (type != 2) {
         String s = t0 + "/" + t1;
         Ui.i().drawNum(s, x + (w - 8 * s.length() >> 1), y + (type == 1 ? 5 : 0), 0, 0);
         s = null;
      }

   }

   private short getMonsterExp(Monster monster) {
      return (short)(210 * monster.monster[2] / 10 - 2);
   }

   private boolean level_up(int var1, int var2) {
      int var10000;
      short var3;
      short var4;
      short[] var5;
      if (var2 == 0) {
         var10000 = this.myMonsters[var1].monster[2];
         Ms.i();
         var3 = (byte)(var10000 - Ms.skip2);
         var4 = 0;
         Ms.i();
         Ms.skip = this.exp;
         if (this.myMonsters[var1].isMonReel(36)) {
            var4 = (short)(var4 + 100);
         }

         if (this.myMonsters[var1].isMonReel(37)) {
            var4 = (short)(var4 + 200);
         }

         if (var3 < 0) {
            var3 = 0;
         } else if (var3 > 4) {
            var3 = 5;
         }

         Ms.i();
         Ms.i();
         Ms.skip = (short)(Ms.skip * (9 - 2 * var3) * (100 + var4) / 1000);
         Ms.i();
         if (Ms.skip < 1) {
            Ms.i();
            Ms.i();
            Ms.skip = Ms.getRandom(6) + 5;
         }

         if (this.battle_type == 3 || this.battle_type == 0) {
            Ms.i();
            Ms.i();
            Ms.skip = (short)(Ms.skip * 3 / 2);
         }

         var5 = this.proReplace[var1];
         Ms.i();
         var5[2] = (short)Ms.skip;
      } else if (var2 == 1) {
         if (this.myMonsters[var1].monster[2] < 60) {
            Ms.i();
            if (Ms.skip > 0) {
               var3 = this.getMonsterExp(this.myMonsters[var1]);
               var10000 = this.myMonsters[var1].monsterPro[4] * 10;
               Ms.i();
               var4 = (short)(var10000 + Ms.skip);
               if (var4 >= var3) {
                  this.myB.cexp = this.myMonsters[var1].monsterPro[4];
                  this.myMonsters[var1].monsterPro[4] = var3;
                  Ms.i();
                  Ms.skip = (short)(var4 - var3);
               } else {
                  this.myB.cexp = this.myMonsters[var1].monsterPro[4];
                  var5 = this.myMonsters[var1].monsterPro;
                  short var10002 = var5[4];
                  Ms.i();
                  var5[4] = (short)(var10002 + Ms.skip);
                  Ms.i();
                  Ms.skip = -1;
               }

               return true;
            }
         }

         Ms.i();
         Ms.skip = -1;
         this.proReplace[var1][2] = 0;
      } else if (var2 == 2) {
         Ms.i();
         if (Ms.skip <= -1) {
            return false;
         }

         this.levelPro(var1, false);
         this.getMagic(this.myMonsters[var1]);
         this.levelUp_in_battle[this.myMonsters[var1].monster[1]][0] = 1;
         this.levelUp_in_battle[this.myMonsters[var1].monster[1]][1] = this.getSkill;
         this.myMonsters[var1].monsterPro[4] = 0;
      }

      return true;
   }

   public void levelPro(int var1, boolean var2) {
      byte var3;
      byte[] var4;
      Object var6;
      if (var2) {
         var3 = (byte)this.proReplace[var1][6];
         var4 = this.monster_pro[this.myMonsters[var1].monster[0]];
         short var5 = (short)(var4[0] + var4[7] * var3 / 10);
         var5 = this.getbuffRateV(this.myMonsters[var1], var5);
         this.proReplace[var1][0] = (short)(this.myMonsters[var1].monsterPro[2] - var5);
         var5 = (short)(var4[1] + var4[8] * var3 / 10);
         this.proReplace[var1][1] = (short)(this.myMonsters[var1].monsterPro[3] - var5);
         var5 = (short)(var4[3] + var4[10] * var3 / 10);
         this.proReplace[var1][3] = (short)(this.myMonsters[var1].monsterPro[6] - var5);
         var5 = (short)(var4[4] + var4[11] * var3 / 10);
         this.proReplace[var1][4] = (short)(this.myMonsters[var1].monsterPro[7] - var5);
         var5 = (short)(var4[2] + var4[9] * var3 / 10);
         this.proReplace[var1][5] = (short)(this.myMonsters[var1].monsterPro[5] - var5);
         var6 = null;
      } else {
         var3 = ++this.myMonsters[var1].monster[2];
         var4 = this.monster_pro[this.myMonsters[var1].monster[0]];
         this.myMonsters[var1].monsterPro[2] = (short)(var4[0] + var4[7] * var3 / 10);
         this.myMonsters[var1].monsterPro[3] = (short)(var4[1] + var4[8] * var3 / 10);
         this.myMonsters[var1].monsterPro[6] = (short)((byte)(var4[3] + var4[10] * var3 / 10));
         this.myMonsters[var1].monsterPro[7] = (short)((byte)(var4[4] + var4[11] * var3 / 10));
         this.myMonsters[var1].monsterPro[5] = (short)((byte)(var4[2] + var4[9] * var3 / 10));
         this.myMonsters[var1].resetPro(this);
         var6 = null;
         this.healMonster(this.myMonsters[var1]);
      }

   }

   private short getbuffRateV(Monster mon, int temp) {
      if (mon.isBuffRate(2)) {
         temp += temp * this.inhesion[2] / 100;
      } else if (mon.isBuffRate(1)) {
         temp += temp * this.inhesion[1] / 100;
      }

      return (short)temp;
   }

   private void paintBATTLE_OVER() {
      if (this.b_c == 1) {
         this.drawEvolveUI(0, this.myB.now_id, false);
      }

   }

   public byte delMonster(int no) {
      byte id = this.myMonsters[no].monster[0];
      if (this.monsterRemove(no) != -1) {
         this.say("失去宠物：" + this.getNameMon(id), 0);
         return 1;
      } else {
         this.say("身上必须携带一只能参战的宠物！", 0);
         return -1;
      }
   }

   private byte monsterRemove(int no) {
      if (this.myMon_length <= 1) {
         return -1;
      } else {
         this.myMonsters[no] = null;

         byte i;
         for(i = (byte)no; i < this.myMon_length - 1; ++i) {
            this.myMonsters[i] = this.myMonsters[i + 1];
            this.evolve[i] = this.evolve[i + 1];
         }

         for(i = this.myMon_length; i < this.myMonsters.length; ++i) {
            this.evolve[i] = true;
         }

         --this.myMon_length;
         return 1;
      }
   }

   void healMonster(Monster monster) {
      monster.effect = 7;
      monster.monsterPro[1] = monster.monsterPro[3];
      monster.monsterPro[0] = monster.monsterPro[2];
   }

   public boolean checkMonster() {
      for(byte i = 0; i < this.myMon_length; ++i) {
         if (this.myMonsters[i].monsterPro[1] < this.myMonsters[i].monsterPro[3] || this.myMonsters[i].monsterPro[0] < this.myMonsters[i].monsterPro[2]) {
            return false;
         }
      }

      this.say("您的宠物不需要回复！", -1);
      return true;
   }

   public void healMonster(boolean bb) {
      for(byte i = 0; i < this.myMon_length; ++i) {
         this.healMonster(this.myMonsters[i]);
      }

      if (bb) {
         this.say("您携带的宠物已回复。", 0);
      }

   }

   public byte findMonster(int id, int lv) {
      for(byte i = 0; i < this.myMon_length; ++i) {
         if (this.myMonsters[i].monster[0] == id && this.myMonsters[i].monster[2] >= lv) {
            return i;
         }
      }

      return -1;
   }

   public byte findMonsterMinLv(int id, int lv) {
      byte minLv = -1;

      for(byte i = (byte)(this.myMon_length - 1); i > -1; --i) {
         if (this.myMonsters[i].monster[0] == id && this.myMonsters[i].monster[2] >= lv && (minLv == -1 || this.myMonsters[i].monster[2] < this.myMonsters[minLv].monster[2])) {
            minLv = i;
         }
      }

      return minLv;
   }

   public byte getMonster(int id, int level, int evolve, int buff) {
      Monster enemyMB = new Monster(this, id, level, buff);
      if (this.cMon_count == this.max_monsters && this.myMon_length == this.max_takes) {
         this.say("宠物空间已满", 0);
         return -1;
      } else {
         if (this.getMonster(enemyMB, evolve, true) != 1) {
            this.say("获得宠物：" + this.getNameMon((byte)id), 0);
         } else {
            this.say("携带已满，" + this.getNameMon(enemyMB.monster[0]) + "已转入寄放处", 0);
         }

         enemyMB = null;
         return 1;
      }
   }

   private byte getMonster(Monster monster, int evolve, boolean bb) {
      if (this.monInfoList[monster.monster[0]] != 2) {
         this.monInfoList[monster.monster[0]] = 2;
         this.addMonInfoListBH();
         this.map.addAnole(monster.monster[0]);
      }

      if (this.myMon_length < this.max_takes) {
         this.myMonsters[this.myMon_length] = monster;
         if (bb) {
            this.myMonsters[this.myMon_length].resetMonster(this, evolve);
         }

         ++this.myMon_length;
         return 0;
      } else if (this.cMon_count < this.max_monsters) {
         this.say("携带已满，" + this.getNameMon(monster.monster[0]) + "已转入寄放处", 0);
         this.cMonsters[this.cMon_count] = monster;
         if (bb) {
            this.cMonsters[this.cMon_count].resetMonster(this, evolve);
         }

         ++this.cMon_count;
         return 1;
      } else {
         return -1;
      }
   }

   public void addMonInfoListBH() {
      ++this.monInfoList[104];
      ++this.monInfoList[103];
      if (this.monInfoList[103] == 102) {
         this.say("恭喜您把所有的宠物都收集齐了", 0);
         mc.paint();
         Ms.i().sleep(600);
      }

   }

   public byte findItemType(int item_id) {
      if (item_id < 16) {
         return 0;
      } else if (item_id < 35) {
         return 1;
      } else {
         return (byte)(item_id < 58 ? 2 : 3);
      }
   }

   public byte findItem(int item_type, int item_id, boolean bb) {
      byte i = 0;
      if (item_type == -2) {
         item_type = this.findItemType(item_id);
      }

      while(i < this.itemsLength[item_type]) {
         if (this.items[item_type][i][0] == item_id) {
            return bb ? this.items[item_type][i][1] : i;
         }

         ++i;
      }

      return (byte)(bb ? 0 : -1);
   }

   public byte getItem(int item_id, int count) {
      if (this.addItem(item_id, count) == 1) {
         this.say("获得：“" + this.getNameItem((byte)item_id) + "”x" + count, 0);
         return 1;
      } else {
         return -1;
      }
   }

   public byte addItem(int item_id, int count) {
      byte item_type = this.findItemType(item_id);
      byte item_no = this.findItem(item_type, item_id, false);
      if (item_no != -1) {
         byte[] var10000 = this.items[item_type][item_no];
         if ((var10000[1] = (byte)(var10000[1] + count)) > 99) {
            this.items[item_type][item_no][1] = 99;
            this.say(this.getNameItem(this.items[item_type][item_no][0]) + "数量已到上限", 0);
            return -1;
         }
      } else {
         this.items[item_type][this.itemsLength[item_type]][0] = (byte)item_id;
         this.items[item_type][this.itemsLength[item_type]][1] = (byte)count;
         ++this.itemsLength[item_type];
      }

      return 1;
   }

   public void deleteItems(int item_id, int count) {
      byte item_type = this.findItemType(item_id);
      byte item_no = this.findItem(item_type, item_id, false);
      if (item_no != -1) {
         if (this.items[item_type][item_no][1] >= count) {
            byte[] var10000 = this.items[item_type][item_no];
            var10000[1] = (byte)(var10000[1] - count);
         }

         if (this.items[item_type][item_no][1] == 0) {
            this.dItemIn(item_type, item_no);
         }
      }

   }

   private void dItemIn(int item_type, int item_no) {
      for(int i = item_no; i < this.itemsLength[item_type] - 1; ++i) {
         this.items[item_type][i] = this.items[item_type][i + 1];
      }

      --this.itemsLength[item_type];
      this.items[item_type][this.itemsLength[item_type]] = null;
      this.items[item_type][this.itemsLength[item_type]] = new byte[2];
   }

   private void useState(int type, int id) {
      mc.paint();
      Ms.i().sleep(200);
      if (this.view_state == -1) {
         this.myB.act_num = 0;
         this.goBattleState();
         this.battle_state = 9;
         this.initMonStream(2, this.mList_id[this.myMonsters[0].monster[0]][0], 1);
      } else {
         this.goMY_BAG(type);
      }

      this.deleteItems(this.items[type][id][0], 1);
   }

   private void mpAdd(int hp) {
      if (this.myMonsters[this.select[0][0]].monsterPro[1] < this.myMonsters[this.select[0][0]].monsterPro[3] && this.myMonsters[this.select_T].monsterPro[0] > 0) {
         hp = hp * this.myMonsters[this.select[0][0]].monsterPro[3] / 100;
         this.AddMP(hp, this.myMonsters[this.select[0][0]]);
         this.useState(0, this.select_it[0]);
      } else if (this.myMonsters[this.select[0][0]].monsterPro[1] < 1) {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "已经死亡，无法使用！", 0);
      } else {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "不需要使用这个道具", 0);
      }

   }

   private void hpAdd(int hp, int offer) {
      if (this.myMonsters[this.select[0][0]].monsterPro[0] < this.myMonsters[this.select[0][0]].monsterPro[2] && this.myMonsters[this.select[0][0]].monsterPro[0] > 0) {
         hp = hp * this.myMonsters[this.select[0][0]].monsterPro[2] / 100;
         this.AddHP(offer + hp, this.myMonsters[this.select[0][0]]);
         this.useState(0, this.select_it[0]);
      } else if (this.myMonsters[this.select[0][0]].monsterPro[0] < 1) {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "已经死亡，无法使用！", 0);
      } else {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "不需要使用这个道具", 0);
      }

   }

   private void allAdd(int hp) {
      boolean bmp = false;
      boolean bhp = false;
      if (this.myMonsters[this.select[0][0]].monsterPro[0] < 1) {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "已经死亡，无法使用！", 0);
      } else {
         if (this.myMonsters[this.select[0][0]].monsterPro[1] < this.myMonsters[this.select[0][0]].monsterPro[3] && this.myMonsters[this.select[0][0]].monsterPro[0] > 0) {
            this.AddMP(hp * this.myMonsters[this.select[0][0]].monsterPro[3] / 100, this.myMonsters[this.select[0][0]]);
            bmp = true;
         }

         if (this.myMonsters[this.select[0][0]].monsterPro[0] < this.myMonsters[this.select[0][0]].monsterPro[2] && this.myMonsters[this.select[0][0]].monsterPro[0] > 0) {
            this.AddHP(hp * this.myMonsters[this.select[0][0]].monsterPro[2] / 100, this.myMonsters[this.select[0][0]]);
            bhp = true;
         }

         if (!bhp && !bmp) {
            this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "不需要使用这个道具", 0);
         } else {
            this.useState(0, this.select_it[0]);
         }
      }

   }

   private void changeEffect() {
      if (this.myMonsters[this.select[0][0]].effect != 7) {
         this.myMonsters[this.select[0][0]].effect = 7;
         this.useState(0, this.select_it[0]);
      } else {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "没有异常！", 0);
      }

   }

   private void resetMonster(int hp) {
      if (this.myMonsters[this.select[0][0]].monsterPro[0] < 1) {
         this.AddMP(hp * this.myMonsters[this.select[0][0]].monsterPro[3] / 100, this.myMonsters[this.select[0][0]]);
         this.AddHP(hp * this.myMonsters[this.select[0][0]].monsterPro[2] / 100, this.myMonsters[this.select[0][0]]);
         this.myMonsters[this.select[0][0]].effect = 7;
         this.useState(0, this.select_it[0]);
      } else {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "没有死亡！", 0);
      }

   }

   private void resetFealty(int hp) {
      byte fealty = this.initFealty[this.myMonsters[this.select[0][0]].monster[4] / 4];
      if (this.myMonsters[this.select[0][0]].monster[6] < fealty && this.myMonsters[this.select[0][0]].monsterPro[0] > 0) {
         if (hp == -1) {
            this.myMonsters[this.select[0][0]].monster[6] = fealty;
         } else if (this.myMonsters[this.select[0][0]].monster[6] + hp > fealty) {
            this.myMonsters[this.select[0][0]].monster[6] = fealty;
         } else {
            byte[] var10000 = this.myMonsters[this.select[0][0]].monster;
            var10000[6] = (byte)(var10000[6] + hp);
         }

         this.useState(0, this.select_it[0]);
         if (this.view_state == -1) {
            this.getHitCoefficient(this.myB, this.myB.getMon(), this.enB.getMon());
         }
      } else if (this.myMonsters[this.select[0][0]].monsterPro[0] > 0) {
         this.say(this.getNameMon(this.myMonsters[this.select[0][0]].monster[0]) + "兴奋度已满", 0);
      }

   }

   private void useItem() {
      switch (this.items[0][this.select_it[0]][0]) {
         case 0:
            this.hpAdd(35, 30);
            break;
         case 1:
            this.hpAdd(65, 30);
            break;
         case 2:
            this.hpAdd(100, 0);
            break;
         case 3:
            this.mpAdd(50);
            break;
         case 4:
            this.mpAdd(100);
            break;
         case 5:
            this.allAdd(50);
            break;
         case 6:
            this.allAdd(100);
            break;
         case 7:
            this.resetMonster(30);
            break;
         case 8:
            this.changeEffect();
         case 9:
         case 10:
         case 11:
         default:
            break;
         case 12:
            this.resetFealty(30);
            break;
         case 13:
            this.resetFealty(-1);
      }

   }

   public void goYouPAUSE(int sy) {
      run_state = 97;
      this.selectx = 0;
      this.selecty = (byte)sy;
      this.setPauseS(0);
      Sound.i().soundStop();
      this.say_c = 0;
      this.view_state = 1;
      this.mini_state = 3;
      this.buyItemID = 0;
      this.buyOk = 0;
      this.gogoString = "金属克野兽，野兽克植物，植物克飞行，飞行克海洋，海洋克金属";
   }

   public void setPauseS(int mode) {
      if (mode == 0) {
         this.setAction_str(new String[]{"保存游戏", "查看宠物", "人物背包", "宠物图鉴", "查看任务", "返回游戏"});
      } else {
         this.setAction_str(new String[]{"读取进度", "音量      ", "游戏帮助", "回主菜单"});
      }

   }

   private void goRUN_IN_MAP(boolean bb) {
      this.selecty = 0;
      this.selectx = 0;
      this.select[0][1] = 0;
      this.mini_state = 0;
      this.view_state = 0;
      mc.game_state = 30;
      this.map.my.state = 0;
      Sound.i().setMusicId(this.musicNo[this.map.mapNo]);
      Sound.i().setMusic(bb);
      run_state = -10;
      this.createOver = 0;
      this.buyItemID = 0;
      this.saved = false;
   }

   public String getNameMonsterInfo(int id) {
      return this.monsterInfo[id].toString();
   }

   public String getNameItem(int item_id) {
      return this.item_name[item_id].toString();
   }

   public String getNameCity(int id) {
      return this.city_name[id].toString();
   }

   public String getNameMon(int mon_id) {
      return this.NAME[mon_id].toString();
   }

   private String getNameSkill(byte skill_id) {
      return this.skill_name[skill_id].toString();
   }

   private String getNameBuff(int buff_id) {
      if (buff_id < 1) {
         buff_id = 0;
      }

      return this.buff_name[buff_id].toString();
   }

   public void say(String string, int flag) {
      this.showS = Ms.i().groupString(string, 202);
      this.say_s = 240;
      this.say_mode = 0;
      if (flag == 0) {
         this.say_c = 15;
      } else if (flag == -1) {
         this.say_c = -1;
      } else if (flag == 1) {
         this.say_c = 15;
         this.say_mode = 1;
      }

   }

   public void map_flashString() {
      if (this.say_c > 0 && run_state != 98) {
         this.showString(this.showS, this.say_mode == 0 ? 160 - (this.showS.length >> 1) * 20 - 12 : 262, this.say_s, 1);
         if (this.say_s == 0) {
            --this.say_c;
         } else {
            this.say_s = Ms.i().mathSpeedDown(this.say_s, 3, true);
         }
      } else if (this.say_c < 0 && run_state != 98) {
         this.showString(this.showS, 160 - (this.showS.length >> 1) * 20 - 12, this.say_s, 2);
         if (this.say_s != 0) {
            this.say_s = Ms.i().mathSpeedDown(this.say_s, 3, true);
         }
      }

   }

   public void drawCityName() {
      if (this.cityName_c != -20) {
         short w = (short)(Ms.i().getStringWidth(this.city_name[this.map.mapNo].toString()) + 8);
         Ui.i().drawKuang(120 - (w >> 1), this.cityName_c > 0 ? 0 : this.cityName_c, w, 26);
         Ui.i().drawString(this.city_name[this.map.mapNo].toString(), 120, 1 + (this.cityName_c > 0 ? 0 : this.cityName_c), 17, 4, 2);
         --this.cityName_c;
      }
   }

   private void initOtherImage() {
      if (null == this.imgItem) {
         this.imgItem = new Image[2];
         this.imgItem[0] = Ms.i().createImage("data/ball");
         this.imgItem[1] = Ms.i().createImage("data/ll");
         this.initItemModules();
      }

      if (null == this.imgBG) {
         this.imgBG = new Image[5];

         for(byte i = 0; i < this.imgBG.length; ++i) {
            this.imgBG[i] = Ms.i().createImage("data/battle/" + i);
         }
      }

      this.initImgIco();
   }

   public void data_init() {
      byte[] data = Ms.i().getStream("data/data.d", -1);
      Ms.i();
      Ms.skip = 0;
      this.skill_up = Ms.i().create2Array(data);
      this.monster_pro = Ms.i().create2Array(data);
      this.skill = Ms.i().create2Array(data);
      this.skill_help = Ms.i().createStringArray(data);
      this.skill_name = Ms.i().createStringArray(data);
      this.monsterT = Ms.i().createStringArray(data);
      this.NAME = Ms.i().createStringArray(data);
      this.item_help = Ms.i().createStringArray(data);
      this.item_name = Ms.i().createStringArray(data);
      this.city_name = Ms.i().createStringArray(data);
      this.buff_name = Ms.i().createStringArray(data);
      this.buff_help = Ms.i().createStringArray(data);
      this.monAppearMap = Ms.i().create2Array(data);
      this.mList_id = Ms.i().create2Array(data);
      this.monsterMake = Ms.i().create2Array(data);
      this.monsterInfo = Ms.i().createStringArray(data);
      this.inhesion = Ms.i().createArray(data);
      this.makeLevel = Ms.i().createArray(data);
      this.musicNo = Ms.i().createArray(data);
      this.npc2ImageType = Ms.i().createArray(data);
      this.map.boatCourse = Ms.i().create2Array(data);
      this.nidusList = Ms.i().create2Array(data);
      data = null;
   }

   private void data_null() {
      this.skill_up = (byte[][])null;
      this.monster_pro = (byte[][])null;
      this.skill = (byte[][])null;
      this.city_name = null;
      this.skill_help = null;
      this.skill_name = null;
      this.NAME = null;
      this.item_help = null;
      this.item_name = null;
   }

   public boolean isNpc2ImageType(int id) {
      for(byte i = 0; i < this.npc2ImageType.length; ++i) {
         if (this.npc2ImageType[i] == id) {
            return false;
         }
      }

      return true;
   }

   private void goCatchMonster() {
      run_state = -19;
      this.cThrowX = 151;
      this.cThrowY = -20;
      this.cThrowS = 146;
      this.b_c = 0;
   }

   private void drawClipPic(int action_id, int id, int x, int y, int w, int h, int piece, int cc, int dir) {
      byte i = 1;
      byte ph = (byte)(w / piece);

      for(short speed = (short)(2 << cc); i < piece; speed = (short)(speed / 2)) {
         g.clipRect(x - i * ph, y - h, ph - speed <= 0 ? ph : speed, h + 20);
         this.drawCartoonOne(id, id, action_id * 3, x, y, true, dir);
         g.setClip(0, 0, 240, 320);
         g.clipRect(x + (i - 1) * ph, y - h, ph - speed <= 0 ? ph : speed, h + 20);
         this.drawCartoonOne(id, id, action_id * 3, x, y, true, dir);
         g.setClip(0, 0, 240, 320);
         ++i;
      }

   }

   private void goMontsterAppear() {
      run_state = -50;
      this.battle_state = 0;
      this.enemyOff = 63;
      this.setAimBattle(1);
   }

   private void setEnemyThrow() {
      this.enemyOff = 63;
      this.enB.cThrow[0] = 83;
      this.enB.cThrow[1] = 60;
      this.enB.cThrow[2] = 0;
      this.setThrow(this.enB, this.enB.getMon(), 0);
      this.getHitCoefficient(this.myB, this.myB.getMon(), this.enB.getMon());
      this.getHitCoefficient(this.enB, this.enB.getMon(), this.myB.getMon());
   }

   private void setMyThrow() {
      this.myB.cThrow[0] = -66;
      this.myB.cThrow[1] = 60;
      this.myB.cThrow[2] = 0;
      this.setThrow(this.myB, this.myB.getMon(), 1);
      this.getHitCoefficient(this.myB, this.myB.getMon(), this.enB.getMon());
      this.getHitCoefficient(this.enB, this.enB.getMon(), this.myB.getMon());
   }

   private void setThrow(Battle be, Monster mon, int mini) {
      be.throw_state = -1;
      this.initMonStream(2, this.mList_id[mon.monster[0]][0], mini);
      if (mon.isMonReel(mon.monster[3] + 43)) {
         if (be.bg_id != mon.monster[3]) {
            this.src_c[mini + 2] = 1;
         }
      } else if (this.monPro.length != 0 && be.bg_id != this.monPro[0]) {
         this.src_c[mini + 2] = 1;
      }

      be.action = 0;
      be.dead = 0;
      be.chp = mon.monsterPro[0];
   }

   private void drawThrowBall(Battle be, Monster mon, int mini, int x, int y) {
      if (be.throw_state == 0) {
         Ui var10000 = Ui.i();
         Image var10001 = this.imgItem[0];
         int var10002 = x + be.cThrow[0];
         int var10003 = y - be.cThrow[1];
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawImage(var10001, var10002, var10003, 1 | 32);
      } else if (be.throw_state == 1) {
         this.drawClipPic(this.mList_id[mon.monster[0]][1], mini, x, y, 80, 77, 15, be.cThrow[2], mini);
      } else if (be.throw_state == 2) {
         this.drawCartoonOne(mini, mini, this.mList_id[mon.monster[0]][1] * 3, x, y, true, mini);
      }

      this.runThrowBall(be, mon, mini);
   }

   private void runThrowBall(Battle be, Monster mon, int mini) {
      if (this.src_c[mini + 2] == 0 && be.throw_state == -1 && this.src_c[mini] == 12) {
         be.throw_state = 0;
      } else if (this.src_c[mini + 2] == 1 && this.src_c[mini] == 0) {
         this.src_c[mini + 2] = 0;
         this.getBattleBG(be, mon);
      }

      if (be.throw_state == 0) {
         be.cThrow[0] = (byte)Ms.i().mathSpeedN(be.cThrow[0], 0, 6, false);
         be.cThrow[1] = (byte)Ms.i().mathSpeedUp(be.cThrow[1], 62, 2);
         if (be.cThrow[0] == 0 && be.cThrow[1] == 0) {
            be.throw_state = 1;
         }
      } else if (be.throw_state == 1) {
         if (be.cThrow[2] < 12) {
            ++be.cThrow[2];
         } else {
            be.throw_state = 2;
         }
      }

   }

   private void drawPlayerAppear(Graphics g) {
      switch (this.battle_state) {
         case 0:
            if (this.battle_type > 2) {
               if (this.src_c[2] == 0 && this.enB.throw_state == -1 && this.src_c[0] == 12) {
                  this.enB.throw_state = 0;
               } else if (this.enB.throw_state == 0 || this.enB.throw_state == 2) {
                  this.drawCartoonOne(0, 0, this.mList_id[this.enB.getMon().monster[0]][1] * 3 + this.enB.action, 177 + this.enemyOff, 97, false, 0);
                  this.enemyOff = Ms.i().mathSpeedN(this.enemyOff, 0, 4, false);
                  if (this.enemyOff == 0) {
                     this.enB.throw_state = 2;
                  }
               }
            } else if (this.battle_type < 2) {
               this.drawThrowBall(this.enB, this.enB.getMon(), 0, 177, 97);
            }

            this.drawThrowBall(this.myB, this.myB.getMon(), 1, 46, 206);
         default:
      }
   }

   private void runMonsterAppear() {
      switch (this.battle_state) {
         case 0:
            if (this.myB.throw_state == 2 && this.myB.throw_state == 2) {
               if (this.view_state == -2) {
                  this.setAimBattle(0);
                  this.myB.act_num = 0;
                  this.enB.act_num = 0;
               }

               this.view_state = -1;
               this.goBattleState();
               if (this.t_battle_state != -3 && this.t_battle_state != -2) {
                  this.goSelectAction(this.mini_state);
               } else {
                  this.battle_state = this.t_battle_state;
                  this.t_battle_state = 0;
                  this.getHitCoefficient(this.enB, this.enB.getMon(), this.myB.getMon());
               }

               if (this.first_battle == 0) {
                  this.first_battle = 1;
                  byte coe = (byte)(this.myB.getMon().monster[6] - 99);
                  coe = (byte)(coe > 1 ? 20 : coe / 20 * 20);
                  if (coe < 0) {
                     this.say("兴奋度降低，攻击力下降，请及时补充！", -1);
                  }
               }
            }
         default:
      }
   }

   private void getHitCoefficient(Battle be, Monster am, Monster dm) {
      be.countS[1] = 0;
      byte coe = (byte)(am.monster[6] - 99);
      byte[] var10000 = be.countS;
      var10000[1] = (byte)(var10000[1] + (coe > 1 ? 20 : coe / 20 * 20));
      if (am.monster[3] != -1 && be.bg_id != am.monster[3]) {
         var10000 = be.countS;
         var10000[1] = (byte)(var10000[1] + (be.bg_id == am.monster[3] ? 0 : -20));
      }

      var10000 = be.countS;
      var10000[1] += this.isShuXing(am, dm);
   }

   private boolean drawHitOne(Battle dm, Battle am, int x, int y) {
      boolean bb = true;
      this.strHit = "";

      for(byte i = 0; i < dm.hit.length; ++i) {
         if (dm.hit[i][4] < this.zb.length) {
            bb = false;
            if (dm.hit[i][0] == -1) {
               this.strHit = "闪避";
            } else if (dm.hit[i][0] == -2) {
               this.strHit = "忠诚度减少";
            } else {
               StringBuffer var10001 = (new StringBuffer()).append(dm.hit[i][1] > -1 ? "-" : "+");
               Ms.i();
               this.strHit = var10001.append(Ms.abs(dm.hit[i][1])).toString();
            }

            if (dm.hit[i][0] > 0 && dm.hit[i][0] < 4) {
               if (dm.hit[i][0] == 2) {
                  Ui.i().drawUi(32, x + dm.hit[i][2] + (this.strHit.length() >> 1) * 14 + 15, y + dm.hit[i][3] + 8, 40, 0);
                  Ui.i().drawUi(33, x + dm.hit[i][2] + (this.strHit.length() >> 1) * 14 + 15, y + dm.hit[i][3] + 8, 36, 0);
               }

               Ui.i().drawNum(this.strHit, x + dm.hit[i][2], y + dm.hit[i][3], 0, dm.hit[i][0] == 2 ? 2 : 1);
            } else if (dm.hit[i][0] < 0 || dm.hit[i][0] == 4) {
               Ui.i().drawString(this.strHit, x + dm.hit[i][2], y + dm.hit[i][3], 0, dm.hit[i][0] == 4 ? 1 : 0, 2);
            }

            short[] var10000;
            if (i == 0) {
               var10000 = dm.hit[i];
               var10000[2] = (short)(var10000[2] + (this.mini_state == 1 ? 1 : -1));
               var10000 = dm.hit[i];
               var10000[3] = (short)(var10000[3] + this.zb[dm.hit[i][4]]);
            } else if (i == 1) {
               var10000 = dm.hit[i];
               var10000[2] = (short)(var10000[2] + (this.mini_state == 1 ? 1 : -1));
               var10000 = dm.hit[i];
               var10000[3] = (short)(var10000[3] + this.zb[dm.hit[i][4]]);
            } else if (i == 2) {
               var10000 = dm.hit[i];
               var10000[3] = (short)(var10000[3] - dm.hit[i][4] / 3);
            }

            if (++dm.hit[i][4] == 1) {
               if (dm.hit[i][0] == 4) {
                  var10000 = dm.getMon().monsterPro;
                  var10000[1] -= dm.hit[i][1];
               } else if (dm.hit[i][0] == -2 && this.mini_state == 0 && !dm.getMon().isMonReel(40)) {
                  byte[] var7 = dm.getMon().monster;
                  var7[6] = (byte)(var7[6] - dm.hit[i][1]);
                  this.getHitCoefficient(dm, dm.getMon(), am.getMon());
               } else if (dm.hit[i][0] > 0) {
                  var10000 = dm.getMon().monsterPro;
                  var10000[0] -= dm.hit[i][1];
               }
            }
         }
      }

      this.valueMend(dm.getMon());
      return bb;
   }

   public void valueMend(Monster monster) {
      if (monster.monsterPro[0] < 0) {
         monster.monsterPro[0] = 0;
      } else if (monster.monsterPro[0] > monster.monsterPro[2]) {
         monster.monsterPro[0] = monster.monsterPro[2];
      }

      if (monster.monsterPro[1] < 0) {
         monster.monsterPro[1] = 0;
      } else if (monster.monsterPro[1] > monster.monsterPro[3]) {
         monster.monsterPro[1] = monster.monsterPro[3];
      }

   }

   private void damage(Battle amB, Battle dmB, Monster am, Monster dm, int skill_no, int mini) {
      int LevelA = am.monster[2];
      int LevelB = dm.monster[2];
      int DebuffB = 0;
      int StrA = am.monsterPro[6];
      int DefB = dm.monsterPro[7];
      int SkillDam = this.skill[skill_no][0];
      int LevelCha = LevelA - LevelB;
      byte buff = 100;
      int hit = StrA * SkillDam * (1000 - DefB * 5) / 10000 - DefB / 10 - LevelA / (SkillDam * 2);
      hit = hit + 6 - (LevelA - 1) / 10;
      if (LevelCha > 10) {
         LevelCha = 10;
      } else if (LevelCha < -10) {
         LevelCha = -10;
      }

      hit = hit * (LevelCha * 4 + 100) / 100;
      if (dm.effect == 0) {
         DebuffB = -30;
      }

      byte baoji;
      label43: {
         label47: {
            hit = hit * (amB.countS[1] + DebuffB + 100) / 100;
            baoji = 1;
            if (am.isBuffRate(9)) {
               Ms.i();
               if (Ms.getRandom(100) < this.inhesion[9]) {
                  break label47;
               }
            }

            if (dm.isMonReel(48)) {
               Ms.i();
               if (Ms.getRandom(100) < this.skill[48][0]) {
                  break label47;
               }
            }

            if (am.isBuffRate(10)) {
               Ms.i();
               if (Ms.getRandom(100) < this.inhesion[10]) {
                  hit = hit * 15 / 10;
                  baoji = 2;
               }
            }
            break label43;
         }

         hit >>= 1;
      }

      if (dm.monster[3] == 1 && dm.isMonReel(33)) {
         buff = this.skill[33][0];
      }

      hit = hit * buff / 100;
      if (hit < 1) {
         hit = 1;
      }

      dmB.addHit(hit, baoji, 0);
   }

   private byte isShuXing(Monster am, Monster dm) {
      if (am.monster[3] == -1) {
         return 30;
      } else if (this.Shuxing[am.monster[3]][0] == dm.monster[3]) {
         return 30;
      } else {
         return (byte)(this.Shuxing[am.monster[3]][1] == dm.monster[3] ? 0 : 0);
      }
   }

   private void hit_rate(Battle amB, Battle dmB, Monster am, Monster dm, int skill_no) {
      amB.initHit();
      dmB.initHit();
      this.hit_rate = am.monsterPro[5] - dm.monsterPro[5];
      this.hit_rate = 92 + this.hit_rate / 2 + amB.rate_off;
      Ms.i();
      byte rand = (byte)Ms.getRandom(100);
      amB.rate_off = 0;
      if (skill_no > 26 && skill_no < 30) {
         switch (skill_no) {
            case 27:
            case 28:
               amB.addHit(-this.skill[skill_no][0] * 10, 1, 1);
               break;
            case 29:
               dmB.addHit(am.monsterPro[6] * this.skill[skill_no][0] + am.monsterPro[1], 1, 0);
               amB.addHit(am.monsterPro[1], 4, 2);
         }
      } else if (rand < this.hit_rate || this.mini_state == 1 && am.monster[2] < 11) {
         label54: {
            this.damage(amB, dmB, am, dm, skill_no, this.mini_state ^ 1);
            if (skill_no == 26 && !dmB.getMon().isMonReel(40)) {
               Ms.i();
               if (Ms.getRandom(100) < this.skill[skill_no][2]) {
                  dmB.addHit(this.skill[skill_no][3] * dmB.getMon().monster[6] / 100, -2, 2);
                  break label54;
               }
            }

            if (skill_no < 25 || skill_no == 30) {
               this.magicEffectRate(dmB, am, dm, skill_no);
            }
         }

         if (am.monster[3] == 2 && am.isMonReel(34)) {
            dmB.addHit(dm.monsterPro[3] * this.skill[34][0] / 100, 4, 2);
         }
      } else {
         dmB.addHit(0, -1, 2);
         amB.rate_off = 20;
      }

   }

   private void runHit() {
      if (!this.am.b_renascence && !this.dm.b_renascence) {
         this.hit_rate(this.am, this.dm, this.am.getMon(), this.dm.getMon(), this.am.skill);
      } else {
         this.am.b_renascence = false;
         this.dm.b_renascence = false;
      }

      this.battle_state = -2;
   }

   private void getExpOne(boolean half) {
      byte i = 0;
      this.exp = 0;
      Ms.i();

      for(Ms.skip2 = this.enB.mon[0].monster[2]; i < this.enB.mon.length; ++i) {
         this.exp = (short)(this.exp + 3 * this.enB.mon[i].monster[2] + 80);
         byte var10000 = this.enB.mon[i].monster[2];
         Ms.i();
         if (var10000 > Ms.skip2) {
            Ms.i();
            Ms.skip2 = this.enB.mon[i].monster[2];
         }
      }

      this.exp = (short)(this.exp * (56 / this.mon_in_battle[0] - 2 * (this.mon_in_battle[0] - 1) + 44) / 100);
      if (!half) {
         this.exp = (short)(this.exp / 2);
      }

   }

   private void arangeMonster() {
      if (this.myMon_length > 1) {
         for(byte i = 0; i < this.myMon_length - 1; ++i) {
            for(byte j = (byte)(i + 1); j < this.myMon_length; ++j) {
               if (this.myMonsters[i].monster[1] > this.myMonsters[j].monster[1]) {
                  this.changeMonster(this.myMonsters[i], this.myMonsters[j]);
               }
            }
         }
      }

   }

   private void goBattleItem() {
      this.battle_state = -4;
      this.cur_b = -1;
      StringBuffer sbuff = new StringBuffer();
      Ms.i();
      if (Ms.getRandom(100) < 50 && this.findItem(-2, 32, true) < 99) {
         this.addItem(32, 1);
         sbuff.append("获得：" + this.getNameItem(32) + "x1");
      }

      this.setStringB(sbuff.toString(), 10000, 0);
      sbuff = null;
   }

   private void goBattleExp(boolean half) {
      this.battle_state = -5;
      this.cur_c = 0;
      this.b_c = 0;
      this.myB.now_id = this.myMonsters[this.myB.now_id].monster[1];
      this.proReplace[this.myB.now_id][2] = -2;
      this.arangeMonster();
      this.getExpOne(half);
   }

   private void goBattleOver() {
      run_state = -15;
      this.b_c = 0;
      this.myB.now_id = 0;
      this.mini_state = 3;
      this.view_state = 1;
      this.bg_c = this.myB.bg_id;
      this.mon[0].nullIMFA();
   }

   private void setNullBattle() {
      this.proReplace = (short[][])null;
      this.effectImage = null;
      this.levelUp_in_battle = (byte[][])null;
      this.myB = null;
      this.enB = null;
   }

   private void goGameOver() {
      run_state = 60;
   }

   private void drawAnimationBattle(Battle amB, int mini_am) {
      if (amB.action == 0) {
         byte skill_type = (byte)(amB.skill / 5);
         byte skill_id = (byte)(amB.skill % 5);
         if (skill_type > 5) {
            skill_type = 5;
         }

         if (skill_type == 5) {
            skill_id = (byte)(amB.skill - 25);
         }

         if (this.drawMagicC(skill_type, skill_type, skill_id, this.getBXY(this.mini_state, amB.skill, true), this.getBXY(this.mini_state, amB.skill, false), this.mini_state ^ 1)) {
            if (this.mini_state == 1) {
               short[] var10000 = amB.getMon().monsterPro;
               var10000[1] = (short)(var10000[1] - this.getSkillMana(amB.getMon(), amB.skill));
               this.valueMend(amB.getMon());
            }

            this.runHit();
         }

      }
   }

   private boolean isSkillToMe(int skill) {
      return skill == 27 || skill == 28;
   }

   private int getBXY(int state, int skill, boolean bb) {
      int x = 46;
      int y = 206;
      if (state == 1 && !this.isSkillToMe(skill) || state == 0 && this.isSkillToMe(skill)) {
         x = 177;
         y = 97;
      }

      return bb ? x : y;
   }

   private void drawDarkScreen() {
      this.drawColorLine(0, 0, 240, 320, 1 + this.b_c, 6, 1, 2, 16777215, 4802889);
   }

   private void drawColorLine(int x, int y, int width, int height, int line_height, int max_h, int speed, int dir, int bgColor, int lineColor) {
      if ((bgColor & -16777216) != 0) {
         g.setColor(bgColor);
         g.fillRect(x, y, width, height);
      }

      g.setColor(lineColor);
      int over = 0;
      int grid = 1;
      if (dir == 1) {
         over = y + height;

         while(y < over) {
            g.fillRect(x, y, width, line_height);
            y += line_height;
            y += grid;
            line_height -= speed;
            if (line_height <= 0) {
               --max_h;
               if (grid < max_h) {
                  ++grid;
               }

               line_height = 1;
            }
         }
      } else if (dir == 2) {
         over = y;
         y += height;

         while(y > over) {
            g.fillRect(x, y - line_height, width, line_height);
            y -= line_height;
            y -= grid;
            line_height -= speed;
            if (line_height <= 0) {
               --max_h;
               if (grid < max_h) {
                  ++grid;
               }

               line_height = 1;
            }
         }
      }

   }

   private void drawBattleBG() {
      Ui.i().fillRectB();
      Ui.i().drawK0(-2, 108, 244, 11, 0);
      Ui.i().drawImage(this.imgBG[this.enB.bg_id], 0, 0, 0);
      Ui var10000 = Ui.i();
      Image var10001 = this.imgBG[this.myB.bg_id];
      DirectGraphics var10005 = dg;
      var10000.drawImage(var10001, 0, 119, 0, 8192);
   }

   private void drawPlayer(Graphics g) {
      this.drawMyMon();
      this.drawMyMonState();
      this.drawEnemy();
      this.drawEnemyState();
   }

   private void paintBattleState(Graphics g) {
      switch (this.battle_state) {
         case -5:
            if (this.proReplace[this.myB.now_id][2] > -1) {
               String s = this.getNameMon(this.myMonsters[this.myB.now_id].monster[0]) + "获得经验：";
               Ui.i().drawString(s, 4, 273, 36, 1, 0);
               Ui.i().drawNum("" + this.proReplace[this.myB.now_id][2], Ms.i().getStringWidth(s) + 4, 268, 0, 1);
               s = null;
            } else if (this.proReplace[this.myB.now_id][2] == -1) {
               Ui.i().drawString("您的宠物已经无法获得经验", 4, 252, 36, 7, 0);
               Ms.i().sleep(600);
            }
         case -4:
         case 0:
         case 1:
         case 2:
         case 4:
         case 5:
         default:
            break;
         case -3:
            if (this.drawHitOne(this.am, this.dm, this.getBXY(this.mini_state ^ 1, 25, true) - 15, this.getBXY(this.mini_state ^ 1, 25, false) - 34) && this.runHitToState(this.dm, this.am, this.mini_state ^ 1)) {
               this.battle_state = 9;
               this.effectR(this.dm.getMon());
            }
            break;
         case -2:
            if (this.drawHitOne(this.dm, this.am, this.getBXY(this.mini_state, this.am.skill, true) - 15, this.getBXY(this.mini_state, this.am.skill, false) - 34) && this.drawHitOne(this.am, this.dm, this.getBXY(this.mini_state ^ 1, 25, true) - 15, this.getBXY(this.mini_state ^ 1, 25, false) - 34) && this.runHitToState(this.am, this.dm, this.mini_state)) {
               this.battle_state = -3;
               this.am.initHit();
               this.doEffectValue(this.am);
               this.doBuffValue(this.am);
            }
            break;
         case -1:
            this.drawAnimationBattle(this.am, this.mini_state);
            break;
         case 3:
            Ui.i().drawK0(25, 45, 190, 136, 0);
            Ui.i().sliding(196, 55, 116, this.select[0][0], this.skill_list[8], false);
            this.drawSkillList(35, 55, 152, 23, 5, this.select[0]);
            this.showStringM(this.skill_help[this.skill_list[this.select[0][0]]].toString(), 120, 244, 12, 0);
            Ui.i().drawYesNo(true, true);
      }

   }

   private void doEffectValue(Battle be) {
      short hit = 0;
      if (be.getMon().isMonEffect(2)) {
         hit = (short)(be.getMon().monsterPro[0] / 10);
      } else if (be.getMon().isMonEffect(5)) {
         hit = (short)(be.fs_level * be.fs_level + 4);
      }

      if (be.getMon().isMonReel(3)) {
         hit = (short)(hit + hit * this.inhesion[3] / 100);
      }

      if (hit != 0) {
         be.addHit(hit, 1, 0);
      }

   }

   private void doBuffValue(Battle be) {
      if (be.getMon().monsterPro[0] >= 1) {
         if (be.getMon().isBuffRate(4)) {
            be.addHit(-be.getMon().monsterPro[2] * this.inhesion[4] / 100, 1, 1);
         }

      }
   }

   private boolean changeMy() {
      for(byte i = 1; i < this.myMon_length; ++i) {
         if (this.myMonsters[i].monsterPro[0] > 0) {
            this.setAimBattle(1);
            this.goVIEW_MONSTER();
            return true;
         }
      }

      return false;
   }

   private boolean isMyMonsters(int select_i) {
      for(byte i = 0; i < this.myMon_length; ++i) {
         if (i != select_i && this.myMonsters[i].monsterPro[0] > 0) {
            return true;
         }
      }

      return false;
   }

   private boolean changeMon(Battle be, int length) {
      byte d = 0;
      byte last_id = be.now_id;

      do {
         if (++be.now_id >= length) {
            be.now_id = 0;
         }

         ++d;
      } while(d <= length && (last_id == be.now_id || be.getMon().monsterPro[0] < 1));

      if (d > length) {
         be.now_id = last_id;
      }

      return d <= length;
   }

   private void runBattleState() {
      this.myB.countS[0] = (byte)Ms.i().mathSpeedN(this.myB.countS[0], this.myB.countS[1], 1, false);
      this.enB.countS[0] = (byte)Ms.i().mathSpeedN(this.enB.countS[0], this.enB.countS[1], 1, false);
      switch (this.battle_state) {
         case -5:
            if (this.b_c == 0 && this.cur_c >= this.mon_in_battle[0]) {
               this.goBattleOver();
               this.setNidusExp(this.exp);
            } else {
               byte id = this.mon_in_battle[this.cur_c + 1];
               if (this.b_c == 0) {
                  if (this.myMonsters[id].monsterPro[0] > 0) {
                     if (!this.myMonsters[id].isMonReel(40)) {
                        --this.myMonsters[id].monster[6];
                     }

                     this.myB.now_id = id;
                     this.myB.chp = this.myMonsters[id].monsterPro[0];
                     this.myB.cexp = this.myMonsters[id].monsterPro[4];
                     this.initMonStream(2, this.mList_id[this.myMonsters[id].monster[0]][0], 1);
                     if (this.level_up(id, 0)) {
                        this.b_c = 1;
                     }
                  } else {
                     ++this.cur_c;
                  }
               } else if (this.b_c == 1) {
                  this.level_up(id, 1);
                  this.b_c = 2;
               } else if (this.b_c == 2 && this.myB.cexp == this.myMonsters[id].monsterPro[4]) {
                  if (!this.level_up(id, 2)) {
                     this.b_c = 15;
                  } else {
                     this.b_c = 1;
                  }
               } else if (this.b_c > 2 && this.b_c < 21 & this.myB.cexp == this.myMonsters[id].monsterPro[4] && --this.b_c == 3) {
                  this.b_c = 0;
                  ++this.cur_c;
               }

               this.myB.cexp = Ms.i().mathSpeedN(this.myB.cexp, this.myMonsters[id].monsterPro[4], 8, false);
            }
            break;
         case -4:
            if (this.say_c == 0) {
               if (++this.cur_b >= this.about_a.length) {
                  this.about_a = null;
                  this.goBattleExp(true);
               } else {
                  this.say(this.about_a[this.cur_b].toString(), 0);
               }
            }
         case -3:
         case -2:
         case -1:
         case 0:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         default:
            break;
         case 1:
            if (this.bufferAI(this.enB.getMon())) {
               this.aiEnemy();
               if (this.enB.getMon().effect != 6 || this.getSkillLevel(this.enB.skill) <= 3) {
                  this.setAcionSkill(this.enB);
               }
            } else {
               this.enB.act_num = 0;
               this.battle_state = -2;
            }
            break;
         case 9:
            if (this.myB.act_num == 0 && this.enB.act_num == 0) {
               this.myB.act_num = 1;
               this.enB.act_num = 1;
               if (!this.myB.getMon().isMonEffect(0)) {
                  this.setAimBattle(1);
                  this.goBattleState();
                  String s = "";
                  if (this.myB.getMon().isMonEffect(2)) {
                     s = "撕裂效果，减少当前血量的10%";
                  } else if (this.myB.getMon().isMonEffect(3)) {
                     s = "魅惑效果，不能逃跑，增加捕获宠物的几率";
                  } else if (this.myB.getMon().isMonEffect(4)) {
                     s = "禁锢效果，不能逃跑，不能换怪，不能使用道具";
                  } else if (this.myB.getMon().isMonEffect(5)) {
                     s = "腐蚀效果，每回合受到一定伤害";
                  }

                  if (!s.equals("")) {
                     this.say(s, -1);
                  }

                  return;
               }

               this.myB.act_num = 0;
               this.say("石化效果无法行动，受到的伤害减少30%", -1);
            }

            if (this.myB.act_num != 0 && this.enB.act_num != 0) {
               this.setAimBattle(this.myB.getMon().monsterPro[5] >= this.enB.getMon().monsterPro[5] ? 1 : 0);
            } else {
               this.setAimBattle(this.myB.act_num != 0 ? 1 : 0);
            }

            if (this.mini_state == 1) {
               this.myB.act_num = 0;
               if (this.myB.getMon().monster[6] < 41) {
                  Ms.i();
                  if (Ms.getRandom(100) < 25) {
                     this.say("忠诚度过低，宠物不服从主人命令", -1);
                     return;
                  }
               }

               if (this.myB.getMon().isMonEffect(0)) {
                  this.say("石化效果无法行动，受到的伤害减少30%", -1);
               } else {
                  this.setAcionSkill(this.myB);
               }
            } else if (this.say_c == 0) {
               this.enB.act_num = 0;
               if (!this.enB.getMon().isMonEffect(0)) {
                  this.battle_state = 1;
               }
            }
      }

   }

   private boolean runHitToState(Battle amB, Battle dmB, int mState) {
      if (dmB.getMon().monsterPro[0] > 0) {
         return true;
      } else {
         if (dmB.dead == 1) {
            if (dmB.getMon().isMonReel(38)) {
               Ms.i();
               if (Ms.getRandom(100) < this.skill[38][1]) {
                  this.setRelive(amB, dmB);
                  return false;
               }
            }

            if (mState == 0) {
               this.enB.act_num = 1;
               if (this.changeMy()) {
                  return false;
               }

               if (this.overMode != 0) {
                  if (this.overMode == 2) {
                     this.map.sIfElse = 1;
                  }

                  this.overMode = 0;
                  this.arangeMonster();
                  this.setNullBattle();
                  this.goGO_RUNINMAP();
                  this.healMonster(false);
                  return false;
               }

               if (SMSSender.i(this).sms_a) {
                  SMSSender.i(this).go(3, true);
               } else {
                  SMSSender.i(this).sms_a = true;
                  this.goGameOver();
               }
            } else if (mState == 1) {
               if (this.changeMon(this.enB, this.enB.mon.length)) {
                  this.setEnemyThrow();
                  this.enB.act_num = 0;
                  run_state = -50;
                  this.t_battle_state = this.battle_state;
                  this.battle_state = 0;
               } else {
                  this.goBattleItem();
                  if (this.overMode == 2) {
                     this.map.sIfElse = 0;
                  }

                  this.overMode = 0;
               }
            }
         } else if (dmB.action != 2) {
            dmB.action = 2;
            this.now_action[this.mini_state] = 0;
            this.now_time[this.mini_state] = 0;
            if (!dmB.getMon().isMonReel(40)) {
               byte[] var10000 = dmB.getMon().monster;
               var10000[6] = (byte)(var10000[6] - 10);
            }
         }

         return false;
      }
   }

   private void setRelive(Battle amB, Battle dmB) {
      dmB.initHit();
      dmB.addHit(-dmB.getMon().monsterPro[2] * this.skill[38][0] / 100, 1, 1);
      dmB.skill = 28;
      dmB.dead = 0;
      dmB.action = 0;
      dmB.b_renascence = true;
      dmB.act_num = 0;
      this.battle_state = -1;
      this.setMagic(5);
      this.getHitCoefficient(dmB, dmB.getMon(), amB.getMon());
      this.setAimBattle(this.mini_state ^ 1);
   }

   private void setAcionSkill(Battle be) {
      be.action = 1;
      this.now_action[this.mini_state] = 0;
      this.now_time[this.mini_state] = 0;
      this.battle_state = -1;
      this.setMagic(be.skill / 5);
   }

   public void setMagic(int pro) {
      if (pro > 5) {
         pro = 5;
      }

      this.initMagicStream(pro);
      this.now_action_Magic[pro] = 0;
      this.now_time_Magic[pro] = 0;
   }

   private void keyBattleState() {
      if (!Ms.i().key_delay()) {
         switch (this.battle_state) {
            case 2:
               if (Ms.i().key_Left_Right()) {
                  this.cur_a = Ms.i().select(this.cur_a, 0, this.action_str.length - 1);
               } else if (Ms.i().key_S1_Num5()) {
                  if (this.cur_a == 0) {
                     if (this.myB.getMon().monster[6] == 0) {
                        this.say("宠物忠诚度为0，请立刻补充！", -1);
                     } else {
                        for(this.battle_state = 3; this.skill_list[this.lastSkill] > 30; ++this.lastSkill) {
                        }

                        this.select[0][0] = this.lastSkill;
                        this.select[0][1] = (byte)(this.select[0][0] - 4);
                        if (this.select[0][1] < 1) {
                           this.select[0][1] = 0;
                        }
                     }
                  } else if (this.cur_a == 1) {
                     if (this.myB.getMon().effect == 4) {
                        this.say("禁锢中无法使用道具！", -1);
                     } else {
                        this.goMY_BAG(0);
                     }
                  } else if (this.cur_a == 2) {
                     if (this.myB.getMon().effect == 4) {
                        this.say("禁锢中无法更换宠物！", -1);
                     } else {
                        this.view_state = -2;
                        this.goVIEW_MONSTER();
                     }
                  } else if (this.cur_a == 3) {
                     this.goBUY_ITEM(0, 0);
                  } else if (this.cur_a == 4) {
                     if (this.battle_type == 4) {
                        short var1 = (short)(this.myB.getMon().monster[2] - this.enB.getMon().monster[2] + 60);
                        boolean var2 = false;
                        Ms.i();
                        if (Ms.getRandom(100) >= var1) {
                           var2 = true;
                        }

                        if (this.myB.getMon().isMonReel(42)) {
                           var2 = false;
                        } else if (this.myB.getMon().isEffect(3) || this.myB.getMon().isEffect(4)) {
                           var2 = true;
                        }

                        if (!var2) {
                        }

                        this.arangeMonster();
                        this.setNullBattle();
                        this.goGO_RUNINMAP();
                     } else {
                        this.say("无法逃跑！", 0);
                     }
                  }
               }
               break;
            case 3:
               if (Ms.i().key_Up_Down()) {
                  Ms.i().selectS(this.select[0], 0, this.skill_list[8], 5);
                  this.lastSkill = this.select[0][0];
               } else if (Ms.i().key_S1_Num5()) {
                  if (this.skill_list[this.select[0][0]] > 30) {
                     this.say("被动技能无法使用!", -1);
                  } else if (this.myB.getMon().effect == 6 && this.getSkillLevel(this.skill_list[this.select[0][0]]) > 3) {
                     this.say("封闭效果，不能使用4级以上技能", -1);
                  } else if (this.myB.getMon().monsterPro[1] >= this.getSkillMana(this.myB.getMon(), this.skill_list[this.select[0][0]])) {
                     this.myB.skill = this.skill_list[this.select[0][0]];
                     this.battle_state = 9;
                  } else {
                     this.say("需要的技能值不够！", -1);
                  }
               } else if (Ms.i().key_S2()) {
                  this.battle_state = 2;
               }
               break;
            case 4:
               if (Ms.i().key_Up_Down()) {
                  Ms.i().selectS(this.select[0], 0, 6, this.skill_list[8]);
               } else if (!Ms.i().key_S1_Num5() && Ms.i().key_S2()) {
                  this.battle_state = 2;
               }
               break;
            case 5:
               if (Ms.i().key_Up_Down()) {
                  Ms.i().selectS(this.select[0], 0, 6, this.skill_list[8]);
               } else if (!Ms.i().key_S1_Num5() && Ms.i().key_S2()) {
                  this.battle_state = 2;
               }
         }

      }
   }

   private void drawBattleExp(Monster mon, int y) {
      Ui.i().drawK0(-2, y, 244, 10, 0);
      this.drawMonsterHp(mon, 37, y + 3, 195, 2, 2, this.battle_state != -5 ? mon.monsterPro[4] : this.myB.cexp);
      Ui.i().drawNum(mon.monster[2] + "级", 6, y + 9, 0, 0);
   }

   private void drawSelectMenu(Graphics g, boolean bb, int y) {
      Ui.i().drawK2(1, y + 15, 238, 320 - y - 19, 0);
      this.drawBattleExp(this.myB.getMon(), y);
      if (!bb) {
         Ui.i().drawString("要做什么？", 6, y + 20, 0, 0, 0);

         for(byte i = 0; i < this.action_str.length; ++i) {
            Ui.i().drawK1(5 + i * 45, y + 23 + 20 + 3, 43, 22, this.cur_a == i ? 4 : 1);
            Ui.i().drawString(this.action_str[i], 8 + i * 45, y + 25 + 20 + 2, 0, this.cur_a == i ? 0 : 3, 0);
         }

      }
   }

   private void drawEnemy() {
      this.drawEffectImage(this.enB.getMon(), 235, 21);
      if (this.enB.getMon().monsterPro[0] >= 1 || this.enB.dead != 1) {
         if (this.drawCartoonOne(0, 0, this.mList_id[this.enB.getMon().monster[0]][1] * 3 + this.enB.action, 177 + (this.enB.ceff[0] != 0 ? 3 : 0), 97 + (this.enB.ceff[0] != 0 ? -3 : 0), this.enB.action == 0, 0)) {
            if (this.enB.getMon().monsterPro[0] < 1) {
               this.enB.action = 2;
               this.enB.dead = 1;
            } else if (this.enB.dead != 1) {
               this.enB.action = 0;
            }
         }

         this.enB.ceff[0] = (byte)Ms.i().mathSpeedN(this.enB.ceff[0], 0, 1, true);
         this.drawMonsterHp(this.enB.getMon(), 152, 97, 50, 0, 1, this.enB.chp);
         this.enB.chp = Ms.i().mathSpeedN(this.enB.chp, this.enB.getMon().monsterPro[0], this.enB.chp < this.enB.getMon().monsterPro[0] ? 20 : 6, true);
      }
   }

   public void drawMyMon() {
      this.drawEffectImage(this.myB.getMon(), 21, 138);
      if (this.drawCartoonOne(1, 1, this.mList_id[this.myB.getMon().monster[0]][1] * 3 + this.myB.action, 46 + (this.myB.ceff[0] != 0 ? -3 : 0), 206 + (this.myB.ceff[0] != 0 ? 3 : 0), this.myB.action == 0, 1)) {
         if (this.myB.getMon().monsterPro[0] < 1) {
            this.myB.action = 2;
            this.myB.dead = 1;
         } else {
            this.myB.action = 0;
         }
      }

      this.myB.ceff[0] = (byte)Ms.i().mathSpeedN(this.myB.ceff[0], 0, 1, true);
      this.drawMonsterHp(this.myB.getMon(), 21, 207, 50, 0, 1, this.myB.chp);
      this.drawMonsterHp(this.myB.getMon(), 21, 212, 50, 1, 2, this.myB.getMon().monsterPro[1]);
      this.myB.chp = Ms.i().mathSpeedN(this.myB.chp, this.myB.getMon().monsterPro[0], this.myB.chp < this.myB.getMon().monsterPro[0] ? 20 : 6, true);
   }

   private void drawEnemyState() {
      short w = 103;
      Ui.i().drawKuang(0, 0, w, 28);
      if (this.enB.getMon().monster[3] != -1) {
         Ui.i().drawUi(this.enB.getMon().monster[3] + 5, 11, 14, 3, 0);
      }

      Ui.i().drawString(this.getNameMon(this.enB.getMon().monster[0]).toString(), 61, 1, 17, 4, 2);
      Ui.i().drawString(this.enB.getMon().monster[2] + "级", 105, 1, 0, 0, 2);
      Ui.i().drawK0(2, 29, 62, 14, 4);
      Ui.i().drawUi(34, 4, 30, 0, 0);
      Ui.i().drawNum(100 + this.enB.countS[0] + "%", 60, 42, 1, 1);
      Ui.i().drawUi(this.monInfoList[this.enB.getMon().monster[0]] == 2 ? 25 : 26, 66, 29, 0, 0);
   }

   private void drawMyMonState() {
      short w = 103;
      short y = 199;
      Ui.i().drawKuang(240 - w, y, w, 28);
      Ui.i().drawUi(this.myB.getMon().monster[3] + 5, 240 - w + 11, y + 14, 3, 0);
      Ui.i().drawString(this.getNameMon(this.myB.getMon().monster[0]).toString(), 240 - w + 57 + 4, y + 1, 17, 4, 2);
      Ui.i().drawK0(176, y - 15, 62, 14, 4);
      Ui.i().drawUi(34, 178, y - 14, 0, 0);
      Ui.i().drawNum(100 + this.myB.countS[0] + "%", 234, y - 2, 1, 1);
   }

   public boolean drawMagicC(int i, int skill_mode, int select, int x, int y, int dir) {
      if (this.now_action_Magic[i] >= this.magic[skill_mode].aLength(select)) {
         this.now_action_Magic[i] = 0;
         return true;
      } else {
         short frame_id;
         for(frame_id = (short)this.magic[skill_mode].action(select, this.now_action_Magic[i], 0); null != this.dm && frame_id < 2; frame_id = (short)this.magic[skill_mode].action(select, this.now_action_Magic[i], 0)) {
            if (frame_id == 0) {
               this.dm.ceff[0] = 2;
            } else if (frame_id == 1) {
            }

            this.now_time_Magic[i] = 0;
            if (++this.now_action_Magic[i] >= this.magic[skill_mode].aLength(select)) {
               this.now_action_Magic[i] = 0;
               return true;
            }
         }

         Ui.i().drawFrameOne(this.magic[skill_mode], frame_id, x, y, dir);
         return this.setCartoonFrameMagic_C(i, skill_mode, select, true);
      }
   }

   private boolean setCartoonFrameMagic_C(int i, int skill_mode, int select, boolean mode) {
      if (++this.now_time_Magic[i] >= this.magic[skill_mode].action(select, this.now_action_Magic[i], 1)) {
         ++this.now_action_Magic[i];
         this.now_time_Magic[i] = 0;
         if (!mode && this.now_action_Magic[i] >= this.magic[skill_mode].aLength(select)) {
            --this.now_action_Magic[i];
            return true;
         }
      }

      return false;
   }

   public boolean drawCartoonOne(int i, int skill_mode, int select, int x, int y, boolean mode, int dir) {
      if (this.now_action[i] >= this.mon[skill_mode].aLength(select)) {
         this.now_action[i] = 0;
      }

      Ui.i().drawFrameOne(this.mon[skill_mode], this.mon[skill_mode].action(select, this.now_action[i], 0), x, y, dir);
      return this.setCartoonFrame_C(i, skill_mode, select, mode);
   }

   private boolean setCartoonFrame_C(int i, int skill_mode, int select, boolean mode) {
      if (++this.now_time[i] >= this.mon[skill_mode].action(select, this.now_action[i], 1)) {
         ++this.now_action[i];
         this.now_time[i] = 0;
         if (!mode && this.now_action[i] >= this.mon[skill_mode].aLength(select)) {
            --this.now_action[i];
            return true;
         }
      }

      return false;
   }

   public void initSkillList(Monster mon) {
      this.skill_list[8] = 0;

      byte i;
      for(i = 7; i > -1; --i) {
         this.skill_list[i] = mon.monster[8 + i];
         if (this.skill_list[i] != -1) {
            ++this.skill_list[8];
         }
      }

      for(i = 1; i < 8; ++i) {
         for(byte j = i; j > 0 && this.skill_list[j] > this.skill_list[j - 1]; --j) {
            byte temp = this.skill_list[j];
            this.skill_list[j] = this.skill_list[j - 1];
            this.skill_list[j - 1] = temp;
         }
      }

   }

   private void initMagicStream(int i) {
      if (null == this.magic[i]) {
         String s = "data/battle/s" + i;
         this.magic[i] = Ms.i().createSprite(s, false);
      }
   }

   public void initMonStream(int type, int id, int i) {
      String name = "data/npc" + type + "/" + id;
      boolean bb = true;
      if (type == 2) {
         bb = this.isNpc2ImageType(id);
      }

      if (null == this.mon[i]) {
         this.mon[i] = Ms.i().createSprite(name, bb);
      } else {
         Ms.i().setSprite(this.mon[i], name, bb);
      }

   }

   public void goBattlePVC() {
      this.enemyList = (byte[][])null;
      this.enemyList = new byte[1][2];
      this.getEnemy(this.enemyList[0], -1, 0);
      Sound.i().setMusicId(6);
      Sound.i().setMusic(true);
      this.goBattle();
   }

   private void goBattleState() {
      this.setAction_str(new String[]{"攻击", "道具", "宠物", "商店", "逃跑"});
      run_state = -31;
      this.battle_state = (byte)(this.mini_state == 1 ? 2 : 1);
   }

   private void goSelectAction(int mini) {
      run_state = -31;
      this.battle_state = (byte)(mini == 1 ? 2 : 1);
      this.setAimBattle(mini);
      if (mini == 1) {
         this.initSkillList(this.myB.getMon());
      }

      this.getHitCoefficient(this.myB, this.myB.getMon(), this.enB.getMon());
      this.getHitCoefficient(this.enB, this.enB.getMon(), this.myB.getMon());
   }

   public void battleType(int mode) {
      this.battle_type = (byte)mode;
   }

   private void enemy_init() {
      Monster[] eMonsters = new Monster[this.enemyList.length];

      for(int i = 0; i < this.enemyList.length; ++i) {
         eMonsters[i] = new Monster(this, this.enemyList[i][0], this.enemyList[i][1], this.myMonsters[0].isMonReel(39) ? 1 : 0);
         if (this.battle_type != 3 && this.battle_type != 0) {
            eMonsters[i].resetMonster(this, -1);
         } else {
            if (eMonsters[i].monster[3] == -1) {
               eMonsters[i].resetBoss(this.enemyList[i][4]);
            } else {
               eMonsters[i].resetMonster(this.enemyList[i][2], this.enemyList[i][3], this.enemyList[i][4]);
            }

            eMonsters[i].resetPro(this);
         }
      }

      this.enemyList = (byte[][])null;
      this.enB = new Battle(eMonsters);
   }

   public void initMonPro(int no, boolean mode) {
      Ms.i();
      Ms.skip = 0;
      byte[] data = Ms.i().getStream("data/map/" + (mode ? "m" : "boss") + no + ".d", -1);
      this.monPro = Ms.i().createArray(data);
      this.mapMove = Ms.i().createArray(data);
      this.mapRect = Ms.i().create2Array(data);
   }

   public void getEnemy(byte[] enemy_list, int type, int level) {
      if (type == -1) {
         Ms.i();
         if (Ms.getRandom(100) < 5 && this.monPro.length > 6) {
            type = 6;
         } else {
            byte[] var10000 = this.monPro;
            Ms.i();
            type = var10000[Ms.getRandom(this.monPro.length > 6 ? 3 : this.monPro.length - 3) + 3];
         }

         Ms.i();
         enemy_list[1] = (byte)(Ms.getRandom(this.monPro[2] + 1) + this.monPro[1]);
      } else {
         enemy_list[1] = (byte)level;
      }

      enemy_list[0] = (byte)type;
   }

   public void setAimBattle(int state) {
      this.mini_state = (byte)state;
      this.am = this.mini_state == 1 ? this.myB : this.enB;
      this.dm = this.mini_state == 1 ? this.enB : this.myB;
   }

   public void goBattle() {
      this.first_battle = 0;
      this.lastSkill = 0;
      this.b_c = 0;
      this.cur_a = 0;
      this.mon_in_battle = null;
      this.mon_in_battle = new byte[this.max_takes + 1];
      this.levelUp_in_battle = (byte[][])null;
      this.levelUp_in_battle = new byte[this.max_takes][2];
      this.proReplace = (short[][])null;
      this.proReplace = new short[this.myMonsters.length][7];
      this.myB = new Battle(this.myMonsters);
      this.enemy_init();

      byte i;
      for(i = 0; i < this.myMon_length; ++i) {
         this.myMonsters[i].effect = 7;
         this.myMonsters[i].effect_time = 0;
         this.myMonsters[i].monster[1] = i;
         this.proReplace[i][6] = (short)this.myMonsters[i].monster[2];
      }

      for(i = 0; i < this.myMon_length; ++i) {
         if (this.myMonsters[i].monsterPro[0] > 0) {
            this.mon_in_battle[++this.mon_in_battle[0]] = this.myMonsters[i].monster[1];
            if (i != 0) {
               this.changeMonster(this.myMonsters[i], this.myMonsters[0]);
            }
            break;
         }
      }

      if (null == this.effectImage) {
         this.effectImage = Ms.i().createImageArray(7, "data/brow/e");
      }

      this.map.my.state = 15;
      run_state = -30;
      this.view_state = -1;
      new CreateThread(this, 1);
   }

   public void initBattle() {
      System.gc();
      this.src_c[0] = this.src_c[1] = this.src_c[2] = this.src_c[3] = 0;
      this.getBattleBG(this.enB, this.enB.getMon());
      this.getBattleBG(this.myB, this.myB.getMon());
      this.setEnemyThrow();
      this.setMyThrow();
   }

   private void getBattleBG(Battle be, Monster mon) {
      if (this.monPro.length != 0) {
         be.bg_id = this.monPro[0];
      }

      for(byte i = 0; i < 5; ++i) {
         if (mon.isMonReel(i + 43)) {
            be.bg_id = i;
            break;
         }
      }

   }

   private boolean bufferAI(Monster mon) {
      return !mon.isMonEffect(0);
   }

   private void aiEnemy() {
      byte skill_num = 5;
      Ms.i();

      byte n;
      for(n = (byte)Ms.getRandom(100); skill_num > -1 && this.enB.getMon().monster[8 + skill_num] == -1; --skill_num) {
      }

      ++skill_num;
      if (this.battle_type == 4) {
         switch (skill_num) {
            case 1:
               this.enB.skill = 0;
               break;
            case 2:
               if (n < 65) {
                  this.enB.skill = 1;
               } else {
                  this.enB.skill = 0;
               }
               break;
            case 3:
               if (n < 45) {
                  this.enB.skill = 2;
               } else if (n > 75) {
                  this.enB.skill = 1;
               } else {
                  this.enB.skill = 0;
               }
               break;
            default:
               if (n < 45) {
                  this.enB.skill = (byte)(skill_num - 1);
               } else if (n < 75) {
                  this.enB.skill = (byte)(skill_num - 2);
               } else if (n < 95) {
                  this.enB.skill = (byte)(skill_num - 3);
               } else {
                  this.enB.skill = (byte)(skill_num - 4);
               }
         }
      } else {
         byte i = 0;
         Ms.i();
         byte rand = (byte)Ms.getRandom(100);

         label92: {
            label91:
            while(true) {
               if (i >= 2) {
                  break label92;
               }

               switch (this.enB.getMon().monster[14 + i]) {
                  case 27:
                     if (this.isMonHp(this.enB.getMon(), 50) && rand < 30) {
                        break label91;
                     }
                     break;
                  case 28:
                     if (this.isMonHp(this.enB.getMon(), 30) && rand < 30) {
                        break label91;
                     }
               }

               ++i;
            }

            this.enB.skill = (byte)(6 + i);
         }

         if (i > 1) {
            switch (skill_num) {
               case 1:
                  this.enB.skill = 0;
                  break;
               case 2:
                  if (n < 20) {
                     this.enB.skill = 0;
                  } else {
                     this.enB.skill = 1;
                  }
                  break;
               case 3:
                  if (n < 5) {
                     this.enB.skill = 0;
                  } else if (n > 40) {
                     this.enB.skill = 2;
                  } else {
                     this.enB.skill = 1;
                  }
                  break;
               case 4:
                  if (n < 15) {
                     this.enB.skill = 1;
                  } else if (n < 40) {
                     this.enB.skill = 2;
                  } else {
                     this.enB.skill = 3;
                  }
                  break;
               default:
                  if (n < 60) {
                     this.enB.skill = (byte)(skill_num - 1);
                  } else if (n < 85) {
                     this.enB.skill = (byte)(skill_num - 2);
                  } else if (n < 95) {
                     this.enB.skill = (byte)(skill_num - 3);
                  } else {
                     this.enB.skill = (byte)(skill_num - 4);
                  }
            }
         }
      }

      this.enB.skill = this.enB.getMon().monster[this.enB.skill + 8];
   }

   private boolean isMonHp(Monster mon, int percent) {
      return mon.monsterPro[0] < mon.monster[2] * percent / 100;
   }

   private void getSkill(int skill_level, int pro) {
      this.getSkill = skill_level == 0 ? 25 : this.skill_up[skill_level - 1][pro];
   }

   private int getSkillLevel(int skill) {
      if (skill == 25) {
         return 0;
      } else {
         return skill < 25 ? skill % 5 + 1 : this.makeLevel[skill - 25 - 1];
      }
   }

   private int getSkillMana(Monster mon, int sl) {
      byte vaule = this.skill[sl][1];
      return mon.monster[3] == 4 && mon.isMonReel(35) ? vaule * this.skill[sl][0] / 100 : vaule;
   }

   public void getMagic(Monster monster) {
      byte[] level = new byte[]{0, 7, 14, 21, 28, 35};
      this.getSkill = -1;

      for(byte i = 0; i < level.length; ++i) {
         if (monster.monster[8 + i] == -1 && monster.monster[2] >= level[i]) {
            this.getSkill(i, monster.monster[3]);
            monster.monster[8 + i] = this.getSkill;
         }
      }

   }

   public void drawChangeMap(boolean bb, int t_c, int x, int y, int w) {
      if (bb) {
         Ui.i().fillRect(5422575, 0, 0, 240, 320);
      }

      g.setColor(0);
      g.fillRect(x, y + 1, w, 5);
      g.fillRect(x + 1, y, w - 2, 7);
      g.setColor(15400191);
      g.fillRect(x + 2, y + 1, (w - 4) * t_c / 60, 5);
      g.fillRect(x + 1, y + 2, (w - 2) * t_c / 60, 3);
      if (bb) {
         Map var10001 = this.map;
         if (null != Map.npcSp[1][0].img) {
            this.map.drawMyAni(this.map.my, 0, x + (w - 4) * t_c / 60, y + 10, 5);
         }
      }

   }

   public void initImgIco() {
      if (this.imgIco == null) {
         this.imgIco = Ms.i().createImageArray(2, "data/brow/i");
      }
   }

   public void drawPauseIco(int state) {
      boolean var10000;
      label24: {
         Ui.i().drawImage(this.imgIco[0], this.b_ico, 320, 36);
         Ui.i().drawImage(this.imgIco[1], 240 - this.b_ico, 320, 40);
         if (state == 0) {
            Ms.i();
            if (!Ms.keyRepeat && this.say_c == 0) {
               var10000 = true;
               break label24;
            }
         }

         var10000 = false;
      }

      boolean b = var10000;
      this.b_ico = (byte)Ms.i().mathSpeedN(this.b_ico, b ? 0 : -124, b ? 4 : 10, false);
   }
}
