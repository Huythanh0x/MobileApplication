package main;

import dm.Ms;
import dm.Npc;
import dm.Sound;
import dm.Sprite;
import dm.Ui;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import minigame.Mg;

public class Map implements Constants_H, Key_H {
   private static Graphics g;
   private int map_x;
   private int map_y;
   private short cols;
   private short rows;
   private short leftCol;
   private short topRow;
   private short mapLeft_NOmove;
   private short mapRight_NOmove;
   private short mapUp_NOmove;
   private short mapDown_NOmove;
   public Npc[][] npc = new Npc[3][];
   private static Npc cur_npc;
   private byte map_key;
   private byte srcNpcNo = -1;
   private byte get_meet;
   private byte lastMap;
   private byte lastExit;
   private byte sleep_count = 0;
   private short meet_step;
   private short step_MEET = 4;
   private byte[] mapChange;
   private byte[][] door;
   private byte[][] item;
   private byte event_state = 0;
   private byte now_eV1;
   private byte now_eV2;
   private byte[][] mapTemp = new byte[70][];
   private byte[][] event;
   private int eventCount;
   public byte anole_temp = -1;
   public byte anole_type = -1;
   public byte mapNo = 0;
   public byte inShop = 0;
   public byte notMeet = 0;
   public byte dialog_no = -1;
   public short[] mapInfo;
   public short[][] event_now_list;
   public short[] event_now;
   public StringBuffer[] dialog;
   public GameRun gr;
   private String[] npcStringData;
   private String[] npcNameData;
   private byte bStep = -1;
   private byte[] npcPos;
   private byte go = -2;
   boolean bExitBoss = false;
   byte mapNotemp = 0;
   public byte fmap = -1;
   public String fString;
   public byte sIfElse = -1;
   public byte sEvent_eV1;
   public byte sEvent_eV2;
   public byte eventGoing;
   private String npcName;
   public boolean gmErr = false;
   private byte xxx = 0;
   public byte black_0;
   public byte black_1;
   private byte[][] dir_select = new byte[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
   private byte anoleSel = 0;
   public byte npcDirTalk = -1;
   public boolean bDirTalk = false;
   byte checkType;
   byte checkNpcT = -1;
   private byte roadType = 0;
   private boolean miniMapMode = false;
   private byte[][] mapdataMap;
   private byte[][] mapdataArea;
   private byte showArea = -1;
   private byte myMiniMap;
   private byte selectMap;
   private short mapoffx;
   private short mapoffy;
   private short littleMapClipY = 300;
   private boolean bPause = false;
   private StringBuffer[][] sMission;
   public byte[] bMission;
   public byte[] mDirect;
   short[][] moduleData = (short[][])null;
   Image[] mapImg = null;
   int bgColor = 0;
   short[] bottomData = null;
   short[] frontData = null;
   short[][] worldData = (short[][])null;
   public byte firstDrawMap = 0;
   int map_left = 0;
   int map_top = 0;
   int map_right = 0;
   int map_bottom = 0;
   int rightCol = 0;
   int bottomRow = 0;
   private Image imgFace = null;
   private boolean faceDir = false;
   private byte faceLast = -1;
   private short face_c = 0;
   Sprite brow;
   private byte action_5;
   private byte time_5;
   public Image imgShadow;
   static Sprite[][] npcSp = new Sprite[3][];
   private byte[] npcList;
   private short black_width;
   private short bSrc_c = 0;
   private boolean bSrc = false;
   private byte srcFlash_c = 0;
   Npc my;
   private Image imgCloud;
   private short[][] cloud;
   public byte boatSelect = -1;
   public byte[][] boatCourse;

   public void setTempNull() {
      this.mapTemp = (byte[][])null;
      this.mapTemp = new byte[70][];
   }

   public void notMeet(int kind, int id) {
      if (kind == 0) {
         this.notMeet = 0;
         Ms.i();
         this.step_MEET = (short)(Ms.getRandom(30) + 1);
         this.meet_step = (short)((this.step_MEET - 15) * (this.step_MEET - 15));
         short var10000 = this.meet_step;
         Ms.i();
         if (var10000 > Ms.getRandom(50)) {
            Ms.i();
            int var10001 = 17 + Ms.getRandom(9);
            Ms.i();
            this.step_MEET = (short)((byte)(var10001 - Ms.getRandom(7)));
         }

         this.step_MEET = (short)(this.step_MEET << 2);
         this.meet_step = 0;
         this.bStep = -1;
      } else if (kind == 1) {
         this.notMeet = 1;
         this.step_MEET = (short)(200 * (id - 14 + 1));
         this.step_MEET = (short)(this.step_MEET << 2);
         this.meet_step = 0;
         this.bStep = 100;
      }

   }

   public Map(GameRun gameRun) {
      this.gr = gameRun;
      System.gc();
      this.init();
   }

   public void createNpcString() {
      this.npcNameData = Ms.i().loadText(this.initData(this.gr.createData(1), this.mapNo));
      this.npcStringData = Ms.i().loadText(this.initData(this.gr.createData(2), this.mapNo));
   }

   public void loadDate_Rms() {
      byte[] info = Ms.i().rmsOptions(2, (byte[])null, 1);
      if (info[0] == -1) {
         byte[] one = new byte[]{58, 9, 16, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, -1};
         info = one;
         Ms.i().rmsOptions(2, one, 2);
         byte[] one = null;
         this.event_now_list = new short[70][];
         Ms.i().rmsOptions(11, Ms.i().getEventNowData(this.event_now_list), 2);
      } else {
         this.event_now_list = Ms.i().readEventNowData();
      }

      this.mapNo = info[0];
      this.lastMap = info[5];
      this.lastExit = info[6];
      this.my.setXY(info[1], info[2], 0, 0);
      this.my.dir = info[3];
      this.my.setActionNo(false);
      this.gr.money = Ms.i().getInt(info, 7);
      this.gr.coin = Ms.i().getInt(info, 11);
      this.anole_type = info[15];
      byte[] info = null;
   }

   public void map_saveGame() {
      byte[] info = new byte[16];
      info[0] = this.mapNo;
      info[5] = this.lastMap;
      info[6] = this.lastExit;
      info[1] = (byte)(this.my.x / 20);
      info[2] = (byte)(this.my.y / 20);
      info[3] = this.my.dir;
      Ms.i().putInt(this.gr.money, info, 7);
      Ms.i().putInt(this.gr.coin, info, 11);
      info[15] = this.anole_type;
      Ms.i().rmsOptions(2, info, 2);
      byte[] info = null;
   }

   public void save() {
      this.map_saveGame();
      this.saveNpc();

      for(int i = 0; i < this.mapInfo.length / 2; ++i) {
         if (this.mapChange[i] == 1) {
            this.mapChange[i] = 0;
            Ms.i().rmsOptions(13 + i, this.mapTemp[i], 2);
         }
      }

      Ms.i().rmsOptions(12, Ms.i().shortArrayToByteArray(this.mapInfo), 2);
      Ms.i().rmsOptions(11, Ms.i().getEventNowData(this.event_now_list), 2);
      Ms.i().rmsOptions(1, this.mapChange, 2);
      Ms.i().rmsOptions(7, this.bMission, 2);
      Ms.i().rmsOptions(5, this.gr.rmsSms, 2);
      Ms.i().rmsOptions(1, (byte[])null, 4);
   }

   public byte[] initData(byte[] tempdata, int n) {
      int len = 1;

      int temp;
      for(int i = 0; i < n; ++i) {
         temp = (tempdata[len++] & 255) << 8 | tempdata[len++] & 255;
         len += temp;
      }

      temp = (tempdata[len++] & 255) << 8 | tempdata[len++] & 255;
      byte[] data = new byte[temp];
      System.arraycopy(tempdata, len, data, 0, data.length);
      return data;
   }

   public void createEvent() {
      byte[] data = Ms.i().getStream("event", this.mapNo);
      int start_pos = 0;
      if (data.length == 1) {
         this.eventCount = 0;
      } else {
         this.eventCount = data[start_pos++];
         byte i;
         if (this.mapInfo[this.mapNo * 2] < 1) {
            this.event_now_list[this.mapNo] = null;
            this.event_now_list[this.mapNo] = new short[this.eventCount];
         } else {
            for(i = 0; i < this.eventCount; ++i) {
               if (this.isMapEvent(this.mapNo, i)) {
                  this.event_now_list[this.mapNo][i] = 24224;
               }
            }
         }

         this.event_now = this.event_now_list[this.mapNo];
         this.event = new byte[this.eventCount][];
         short length_ = false;

         for(i = 0; i < this.eventCount; ++i) {
            short length_ = (short)(data[start_pos++] << 8 | data[start_pos++] & 255);
            this.event[i] = new byte[length_];
            System.arraycopy(data, start_pos, this.event[i], 0, this.event[i].length);
            start_pos += length_;
         }

      }
   }

   private void init() {
      this.initBrowImage();
      this.configureNpc();
      if (null == npcSp[1][0]) {
         npcSp[1][0] = new Sprite();
         this.my = new Npc(8);
         this.my.other[4] = 1;
         this.setAnole();
      }

      if (null == this.imgShadow) {
         this.imgShadow = Ms.i().createImage("data/shadow");
      }

   }

   private void configureNpc() {
      npcSp[0] = new Sprite[100];
      npcSp[1] = new Sprite[5];
      npcSp[2] = new Sprite[60];
   }

   public void initMap() {
      this.my.dir = 4;
      this.srcNpcNo = -1;
      this.gr.loadRmsSms();
      this.gr.loadRmsNidus();
      this.notMeet(0, 0);
      this.mapInfo = Ms.i().byteArrayToShortArray(Ms.i().rmsOptions(12, (byte[])null, 1));
      this.mapChange = Ms.i().rmsOptions(1, (byte[])null, 1);

      for(short i = 0; i < this.mapChange.length; ++i) {
         if (this.mapChange[i] == 1) {
            this.mapTemp[i] = Ms.i().rmsOptions(13 + i, (byte[])null, 1);
         }
      }

      System.gc();
      this.gr.createOver = 33;
      this.createOther();
      this.gr.createOver = 35;
      this.createLayer();
      this.gr.createOver = 37;
      this.createEvent();
      this.gr.createOver = 39;
      this.createNpcString();
      this.gr.createOver = 40;
      Ms.i().rmsOptions(1, (byte[])null, 4);
      this.gr.initMonPro(this.mapNo, true);
      this.initMissionList();
      this.initCloud();
   }

   public void createOther() {
      byte[] Datas = null;
      Ms.i();
      Ms.skip = 0;
      Ms.i();
      Ms.skip2 = 0;
      byte[] Datas = this.initData(this.gr.createData(0), this.mapNo);
      this.npcPos = this.createNpcPos(Datas, this.npcPos);
      if (this.mapInfo[this.mapNo * 2] > 0 && this.mapTemp[this.mapNo] == null) {
         this.mapTemp[this.mapNo] = Ms.i().rmsOptions(13 + this.mapNo, (byte[])null, 1);
      }

      this.npc[0] = this.createNpc(Datas, this.npc[0]);
      this.npc[1] = this.createNpc(Datas, this.npc[1]);
      this.npc[2] = this.createNpc(Datas, this.npc[2]);
      this.item = this.createItem(Datas, this.item);
      this.door = this.createItem(Datas, this.door);
      this.npcList = null;
      this.npcList = new byte[this.npc[0].length + 1 + this.item.length];
      Datas = null;
      this.initNpcAni();
   }

   Npc[] createNpc(byte[] data, Npc[] npc) {
      npc = null;
      Ms.i();
      npc = new Npc[data[Ms.skip++]];

      byte[] var10000;
      int i;
      int j;
      for(i = 0; i < npc.length; ++i) {
         Ms.i();
         npc[i] = new Npc(data[Ms.skip++]);

         for(j = 0; j < npc[i].other.length; ++j) {
            var10000 = npc[i].other;
            Ms.i();
            var10000[j] = data[Ms.skip++];
         }

         npc[i].setXY_npc();
      }

      if (this.mapInfo[this.mapNo * 2] > 0) {
         for(i = 0; i < npc.length; ++i) {
            if (npc[i].other[9] == 1) {
               for(j = 0; j < npc[i].other.length; ++j) {
                  var10000 = npc[i].other;
                  byte[] var10002 = this.mapTemp[this.mapNo];
                  Ms.i();
                  var10000[j] = var10002[Ms.skip2++];
               }

               npc[i].setXY_npc();
            }
         }
      }

      return npc;
   }

   byte[][] createItem(byte[] data, byte[][] npc) {
      npc = (byte[][])null;
      byte[] var10000;
      int i;
      int j;
      if (this.mapInfo[this.mapNo * 2] > 0) {
         data = this.mapTemp[this.mapNo];
         Ms.i();
         npc = new byte[data[Ms.skip2++]][];

         for(i = 0; i < npc.length; ++i) {
            Ms.i();
            npc[i] = new byte[data[Ms.skip2++]];

            for(j = 0; j < npc[i].length; ++j) {
               var10000 = npc[i];
               Ms.i();
               var10000[j] = data[Ms.skip2++];
            }
         }
      } else {
         Ms.i();
         npc = new byte[data[Ms.skip++]][];

         for(i = 0; i < npc.length; ++i) {
            Ms.i();
            npc[i] = new byte[data[Ms.skip++]];

            for(j = 0; j < npc[i].length; ++j) {
               var10000 = npc[i];
               Ms.i();
               var10000[j] = data[Ms.skip++];
            }
         }
      }

      return npc;
   }

   private byte getNpcLayer(int i) {
      return (byte)((this.npcPos[i] & 255) >> 6);
   }

   private byte getNpcId(int i) {
      return (byte)(this.npcPos[i] & 63);
   }

   public byte[] createNpcPos(byte[] data, byte[] npc) {
      byte[] npc = null;
      Ms.i();
      npc = new byte[data[Ms.skip++]];

      for(int i = 0; i < npc.length; ++i) {
         Ms.i();
         npc[i] = data[Ms.skip++];
      }

      return npc;
   }

   public void saveNpc() {
      this.mapTemp[this.mapNo] = null;
      this.mapTemp[this.mapNo] = this.getNpcData();
      this.mapInfo[this.mapNo * 2] = 1;
      this.mapChange[this.mapNo] = 1;
   }

   private void setMapData() {
      this.map_x = this.map_set(this.my.getIx(), this.cols, 12, 6, 240, 20);
      this.map_y = this.map_set(this.my.getIy(), this.rows, 16, 8, 320, 20);
      this.mapLeft_NOmove = 110;
      this.mapRight_NOmove = (short)(this.cols * 20 - 120 - 10);
      this.mapUp_NOmove = 150;
      this.mapDown_NOmove = (short)(this.rows * 20 - 160 - 10);
   }

   public void createLayer() {
      this.createMap();
      this.setMapData();
   }

   public byte exitMap(byte[][] door) {
      byte go;
      if (door[this.get_meet][3] == -1) {
         go = this.mapNo;
         this.mapNo = this.lastMap;
         this.lastMap = go;
         this.lastExit = this.get_meet;
         go = -1;
      } else {
         go = this.mapNo;
         this.mapNo = door[this.get_meet][4];
         this.lastMap = go;
         this.lastExit = this.get_meet;
         go = door[this.get_meet][3];
      }

      return go;
   }

   public void saveMapBak() {
      this.saveNpc();
      this.mapChange[this.mapNo] = 1;
      Ms.i().rmsOptions(1, this.mapChange, 2);
   }

   public void goNextMap() {
      this.setfmap();
      this.firstDrawMap = 0;
      this.gr.doPaint(2);
      this.saveMapBak();
      byte exit_b = this.lastExit;
      if (this.go != -2) {
         this.lastMap = this.mapNo;
         this.mapNo = this.mapNotemp;
         this.mapNotemp = 0;
         this.lastExit = this.go;
      }

      this.gr.doPaint(5);
      if (this.go == -2) {
         this.go = this.exitMap(this.door);
      }

      this.gr.setNull(false);
      this.gr.doPaint(8);
      this.createOther();
      this.gr.doPaint(10);
      if (this.go == -1) {
         this.go = exit_b;
      }

      if (!this.bExitBoss) {
         this.getInMap(this.go, this.door);
      }

      this.createEvent();
      this.gr.doPaint(15);
      this.createNpcString();
      this.createLayer();
      this.gr.doPaint(20);
      if (this.bExitBoss || this.door[this.go][5] != 1 && this.door[this.go][5] != 2) {
         this.my.setActionNo(false);
      } else {
         this.my.frame_c = (byte)(20 / this.my.speed);
         this.my.speed_x = (byte)(this.dir_select[this.my.dir][0] * this.my.speed);
         this.my.speed_y = (byte)(this.dir_select[this.my.dir][1] * this.my.speed);
         this.my.setActionNo(true);
      }

      this.gr.b_c = 0;
      this.setMapMusic(false);
      this.go = -2;
      this.gr.initMonPro(this.mapNo, true);
      this.gr.cityName_c = 25;
      this.srcNpcNo = -1;
      Ms.i().rmsOptions(1, (byte[])null, 4);
      this.initCloud();
      this.bExitBoss = false;
      this.my.state = 9;
      if (this.boatSelect == -1) {
         this.my.state = 0;
         this.my.setIXY(-1, -1);
         this.my.speed = this.getMySpeed();
      }

      this.checkNpcT = -1;
   }

   private void exitBoss(int map_No, int zuobiao_x, int zuobiao_y, int mydir) {
      this.chuansong(map_No, 0);
      this.bExitBoss = true;
      this.my.setXY(zuobiao_x, zuobiao_y, 0, 0);
      this.my.dir = (byte)mydir;
   }

   public void chuansong(int mapno, int door) {
      this.my.state = 5;
      this.mapNotemp = (byte)mapno;
      this.go = (byte)door;
      this.setMoveStop_m();
   }

   public void setfmap() {
      this.fmap = 0;
   }

   private void getInMap(byte exit_no, byte[][] door) {
      this.my.dir = (byte)((door[exit_no][2] - 1) / 2 * 2 + 1 + door[exit_no][2] % 2);
      this.my.setXY(door[exit_no][0], door[exit_no][1], 0, 0);
   }

   private int map_set(int _my_x, int cols, int xcells, int half_xcells, int show_width, int cell_width) {
      int map_x;
      if (cols <= xcells) {
         map_x = show_width - cell_width * cols >> 1;
      } else if (_my_x + 1 <= half_xcells) {
         map_x = 0;
      } else if (_my_x >= cols - half_xcells) {
         map_x = show_width - cols * cell_width;
      } else {
         map_x = show_width / 2 - _my_x * cell_width - cell_width / 2;
      }

      return map_x;
   }

   private boolean setSIfElse(int _mode, boolean _bt, boolean _bmode) {
      if (_mode == 2) {
         this.sIfElse = (byte)(_bt ? 0 : 1);
         return true;
      } else if (_bmode && _bt) {
         return true;
      } else {
         return !_bmode && _mode == 1;
      }
   }

   private void ifEvent(int i, int next_id, int next_mode, int mode, boolean bt) {
      if (this.setSIfElse(mode, bt, true)) {
         short[] var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + next_id);
         this.nextEvent(next_mode);
      } else if (this.setSIfElse(mode, bt, false)) {
         this.setEventNow(i, false);
      }

   }

   private boolean isMapEvent(int _mapNo, int _i) {
      return (this.mapInfo[_mapNo * 2 + 1] & 1 << _i) != 0;
   }

   public void setRestEvent(int map, int id) {
      if (this.isMapEvent(map, id)) {
         short[] var10000 = this.mapInfo;
         var10000[map * 2 + 1] = (short)(var10000[map * 2 + 1] ^ 1 << id);
      }

      if (map == this.mapNo) {
         this.event_now[id] = 0;
      } else if (null != this.event_now_list[map]) {
         this.event_now_list[map][id] = 0;
      }

   }

   public void setOverEvent(int map, int id) {
      if (!this.isMapEvent(map, id)) {
         short[] var10000 = this.mapInfo;
         var10000[map * 2 + 1] = (short)(var10000[map * 2 + 1] | 1 << id);
      }

      if (map == this.mapNo) {
         this.event_now[id] = 24224;
      } else if (null != this.event_now_list[map]) {
         this.event_now_list[map][id] = 24224;
      }

   }

   private void setEventNow(int i, boolean bb) {
      if (bb) {
         this.my.state = 0;
      }

      this.now_eV1 = 0;
      this.now_eV2 = 0;
      this.eventGoing = 0;
      this.event_now[i] = 0;
   }

   private void nextEvent(int mode) {
      if ((mode & 4096) != 0) {
         this.now_eV1 = 0;
         this.now_eV2 = 0;
      }

      if ((mode & 256) != 0) {
         this.eventGoing = 0;
      }

      if ((mode & 16) != 0) {
         this.event_state = 1;
      }

      if ((mode & 1) != 0) {
         this.my.state = 0;
      }

   }

   public void eventCheck() {
      boolean var2 = false;

      for(int var1 = 0; this.event != null && var1 < this.event.length; ++var1) {
         if (this.event[var1] != null && this.event_now[var1] < this.event[var1].length) {
            do {
               this.event_state = 0;
               short[] var10000;
               if (this.event_now[var1] >= this.event[var1].length) {
                  var10000 = this.mapInfo;
                  int var9 = this.mapNo * 2 + 1;
                  var10000[var9] = (short)(var10000[var9] | 1 << var1);
                  break;
               }

               boolean var3;
               int var6;
               switch (this.event[var1][this.event_now[var1]]) {
                  case 1:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 != -1) {
                        this.now_eV1 = this.getNpcLayer(var6);
                        this.now_eV2 = this.getNpcId(var6);
                        var3 = this.my.state == 2 && this.get_meet == this.now_eV2;
                        this.ifEvent(var1, 4, 16, this.event[var1][this.event_now[var1] + 3], var3);
                     }
                     break;
                  case 2:
                     this.now_eV1 = this.event[var1][this.event_now[var1] + 2];
                     this.now_eV2 = this.event[var1][this.event_now[var1] + 3];
                     this.ifEvent(var1, 5, 4112, this.event[var1][this.event_now[var1] + 4], this.isMapEvent(this.now_eV1, this.now_eV2));
                     break;
                  case 3:
                     var3 = this.gr.money >= this.event[var1][this.event_now[var1] + 2] * 100;
                     this.ifEvent(var1, 4, 16, this.event[var1][this.event_now[var1] + 3], var3);
                     break;
                  case 4:
                     var3 = this.gr.monInfoList[103] >= this.event[var1][this.event_now[var1] + 2];
                     this.ifEvent(var1, 4, 16, this.event[var1][this.event_now[var1] + 3], var3);
                     break;
                  case 5:
                     byte var8 = this.gr.findItem(-2, this.event[var1][this.event_now[var1] + 2], true);
                     if (this.event[var1][this.event_now[var1] + 3] == 0) {
                        var3 = var8 == 0;
                     } else {
                        var3 = var8 >= this.event[var1][this.event_now[var1] + 3];
                     }

                     this.ifEvent(var1, 5, 16, this.event[var1][this.event_now[var1] + 4], var3);
                     break;
                  case 6:
                     var3 = Ms.i().isRect(this.my.x, this.my.y, 19, 19, this.event[var1][this.event_now[var1] + 2] * 20, this.event[var1][this.event_now[var1] + 3] * 20, (this.event[var1][this.event_now[var1] + 4] - this.event[var1][this.event_now[var1] + 2] + 1) * 20, (this.event[var1][this.event_now[var1] + 5] - this.event[var1][this.event_now[var1] + 3] + 1) * 20);
                     this.ifEvent(var1, 7, 16, this.event[var1][this.event_now[var1] + 6], var3);
                  case 7:
                  case 9:
                  case 10:
                  case 14:
                  case 20:
                  case 34:
                  default:
                     break;
                  case 8:
                     var3 = this.gr.findMonster(this.event[var1][this.event_now[var1] + 2], this.event[var1][this.event_now[var1] + 3]) != -1;
                     this.ifEvent(var1, 5, 16, this.event[var1][this.event_now[var1] + 4], var3);
                     break;
                  case 11:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     this.now_eV1 = this.getNpcLayer(var6);
                     this.now_eV2 = this.getNpcId(var6);
                     if (this.npc[this.now_eV1][this.now_eV2].other[8] == this.event[var1][this.event_now[var1] + 3]) {
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 4);
                        this.nextEvent(16);
                     } else {
                        this.setEventNow(var1, false);
                     }
                     break;
                  case 12:
                     this.runEvent_select(var1);
                     break;
                  case 13:
                     if (this.eventGoing == 0) {
                        this.eventGoing = 1;
                        if (this.event[var1][this.event_now[var1] + 3] == 0) {
                           this.bMission[14] = this.event[var1][this.event_now[var1] + 2];
                           if (this.bMission[14] != -1) {
                              this.gr.say("#4主线任务#0已更新，按#71键#0可查看任务表。", -1);
                           }
                        } else {
                           this.setMission(this.event[var1][this.event_now[var1] + 2], this.event[var1][this.event_now[var1] + 3] == 2);
                           if (this.event[var1][this.event_now[var1] + 3] == 2) {
                              this.gr.say("#4分支任务#0已更新，按#73键#0可查看任务表。", -1);
                           }
                        }
                     } else if (this.gr.say_c == 0) {
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 4);
                        this.nextEvent(272);
                     }
                     break;
                  case 15:
                     this.setEventNow(var1, this.event[var1][this.event_now[var1] + 2] == 0);
                     break;
                  case 16:
                     this.runEvent_goShop(var1);
                     break;
                  case 17:
                     this.now_eV1 = this.event[var1][this.event_now[var1] + 2];
                     this.now_eV2 = this.event[var1][this.event_now[var1] + 3];
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.setOverEvent(this.now_eV1, this.now_eV2);
                     this.nextEvent(4112);
                     break;
                  case 18:
                     this.runEvent_item_add_del(var1);
                     break;
                  case 19:
                     Ms.i();
                     Ms.skip = 0;
                     byte[] var4 = Ms.i().getStream("data/battle/boss.d", -1);
                     byte[][] var5 = Ms.i().create2Array(var4);
                     Object var7 = null;
                     var6 = this.event[var1][this.event_now[var1] + 1] >> 1;
                     this.gr.enemyList = new byte[var6][5];

                     for(this.now_eV2 = 0; this.now_eV2 < var6; ++this.now_eV2) {
                        this.now_eV1 = this.event[var1][this.event_now[var1] + 2 + this.now_eV2 * 2];
                        this.gr.enemyList[this.now_eV2][0] = var5[this.now_eV1][0];
                        this.gr.enemyList[this.now_eV2][1] = this.event[var1][this.event_now[var1] + 3 + this.now_eV2 * 2];
                        this.gr.enemyList[this.now_eV2][2] = var5[this.now_eV1][1];
                        this.gr.enemyList[this.now_eV2][3] = var5[this.now_eV1][2];
                        this.gr.enemyList[this.now_eV2][4] = var5[this.now_eV1][3];
                     }

                     var5 = (byte[][])null;
                     Sound.i().setMusicId(6);
                     Sound.i().setMusic(false);
                     this.gr.battleType(3);
                     this.gr.goBattle();
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 2 + this.event[var1][this.event_now[var1] + 1]);
                     break;
                  case 21:
                     if (this.eventGoing == 0) {
                        this.eventGoing = 1;
                        var6 = this.event[var1][this.event_now[var1] + 2] * 100;
                        if (var6 < 0) {
                           int var11 = this.gr.money;
                           Ms.i();
                           if (var11 < Ms.abs(var6) - 100000) {
                              this.gr.say("金钱不足！", 0);
                              this.eventGoing = 2;
                           }
                        }

                        if (this.eventGoing == 1) {
                           GameRun var12 = this.gr;
                           var12.money += var6;
                           var12 = this.gr;
                           StringBuffer var10001 = (new StringBuffer()).append(var6 < 0 ? "失去：" : "获得：");
                           Ms.i();
                           var12.say(var10001.append(Ms.abs(var6)).append("金").toString(), 0);
                        }
                     } else if (this.gr.say_c == 0) {
                        if (this.eventGoing == 2) {
                           this.setEventNow(var1, true);
                        } else {
                           var10000 = this.event_now;
                           var10000[var1] = (short)(var10000[var1] + 3);
                           this.nextEvent(272);
                        }
                     }
                     break;
                  case 22:
                     this.exitBoss(this.event[var1][this.event_now[var1] + 2], this.event[var1][this.event_now[var1] + 3], this.event[var1][this.event_now[var1] + 4], this.event[var1][this.event_now[var1] + 5]);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 6);
                     this.nextEvent(16);
                     break;
                  case 23:
                     this.now_eV1 = this.event[var1][this.event_now[var1] + 2];
                     this.now_eV2 = this.event[var1][this.event_now[var1] + 3];
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.setRestEvent(this.now_eV1, this.now_eV2);
                     this.nextEvent(4112);
                     break;
                  case 24:
                     this.initBoat(this.event[var1][this.event_now[var1] + 2]);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(16);
                     break;
                  case 25:
                     this.gr.overMode = (byte)(this.event[var1][this.event_now[var1] + 2] + 1);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     break;
                  case 26:
                     this.runEvent_getMon(var1);
                     break;
                  case 27:
                     if (this.eventGoing == 0) {
                        this.now_eV2 = this.gr.findMonsterMinLv(this.event[var1][this.event_now[var1] + 2], this.event[var1][this.event_now[var1] + 3]);
                        if (this.now_eV2 != -1) {
                           this.now_eV1 = this.gr.delMonster(this.now_eV2);
                           this.eventGoing = 1;
                        } else {
                           this.setEventNow(var1, false);
                        }
                     } else if (this.gr.say_c == 0) {
                        if (this.now_eV1 == -1) {
                           this.setEventNow(var1, false);
                        } else {
                           var10000 = this.event_now;
                           var10000[var1] = (short)(var10000[var1] + this.event[var1][this.event_now[var1] + 1] + 2);
                           this.nextEvent(4368);
                        }
                     }
                     break;
                  case 28:
                     this.runEvent_dialog(var1);
                     break;
                  case 29:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        cur_npc = this.my;
                     } else {
                        cur_npc = this.npc[this.getNpcLayer(var6)][this.getNpcId(var6)];
                     }

                     cur_npc.setLastAction(false, this.event[var1][this.event_now[var1] + 4]);
                     cur_npc.other[7] = this.event[var1][this.event_now[var1] + 3];
                     cur_npc.now_action = 0;
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 5);
                     this.nextEvent(4096);
                     break;
                  case 30:
                     this.my.state = 10;
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        cur_npc = this.my;
                     } else {
                        cur_npc = this.npc[this.getNpcLayer(var6)][this.getNpcId(var6)];
                     }

                     cur_npc.setIXY(this.event[var1][this.event_now[var1] + 3], this.event[var1][this.event_now[var1] + 4]);
                     cur_npc.speed = this.event[var1][this.event_now[var1] + 5];
                     cur_npc.b_check = this.event[var1][this.event_now[var1] + 6] == 0;
                     cur_npc.setLastAction(false, 127);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 7);
                     this.nextEvent(16);
                     break;
                  case 31:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        this.now_eV1 = this.event[var1][this.event_now[var1] + 3];
                        this.my.dir = this.now_eV1;
                        this.my.setActionNo(false);
                     } else {
                        this.now_eV1 = this.getNpcLayer(var6);
                        if (this.now_eV1 == 0) {
                           this.now_eV2 = this.getNpcId(var6);
                           this.npc[this.now_eV1][this.now_eV2].dir = this.event[var1][this.event_now[var1] + 3];
                           this.npc[this.now_eV1][this.now_eV2].setActionNo(false);
                        }
                     }

                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(4112);
                     break;
                  case 32:
                     this.runEvent_miniGame_myLevel(var1);
                     break;
                  case 33:
                     this.anole_type = (byte)(this.event[var1][this.event_now[var1] + 2] - 1);
                     this.setAnole();
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(16);
                     break;
                  case 35:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        this.my.other[4] = this.event[var1][this.event_now[var1] + 3];
                        switch (this.event[var1][this.event_now[var1] + 3]) {
                           case 0:
                           case 2:
                              this.my.state = 0;
                              break;
                           case 1:
                           case 3:
                              this.my.state = 10;
                        }
                     } else {
                        this.now_eV1 = this.getNpcLayer(var6);
                        this.now_eV2 = this.getNpcId(var6);
                        this.npc[this.now_eV1][this.now_eV2].other[4] = this.event[var1][this.event_now[var1] + 3];
                     }

                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(4112);
                     break;
                  case 36:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     this.now_eV1 = this.getNpcLayer(var6);
                     this.now_eV2 = this.getNpcId(var6);
                     this.npc[this.now_eV1][this.now_eV2].other[5] = (byte)(this.event[var1][this.event_now[var1] + 3] + 1);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(4112);
                     break;
                  case 37:
                     this.my.state = 10;
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        cur_npc = this.my;
                     } else {
                        cur_npc = this.npc[this.getNpcLayer(var6)][this.getNpcId(var6)];
                     }

                     cur_npc.setLastAction(true, this.event[var1][this.event_now[var1] + 6]);
                     cur_npc.setIXY(this.event[var1][this.event_now[var1] + 3], this.event[var1][this.event_now[var1] + 4]);
                     cur_npc.other[7] = this.event[var1][this.event_now[var1] + 5];
                     if (this.event[var1][this.event_now[var1] + 5] < 0) {
                        cur_npc.dir = 4;
                     }

                     cur_npc.speed = this.event[var1][this.event_now[var1] + 7];
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 8);
                     this.nextEvent(16);
                     break;
                  case 38:
                     this.runEvent_brow(var1);
                     break;
                  case 39:
                     this.now_eV1 = this.event[var1][this.event_now[var1] + 2];
                     this.now_eV2 = this.event[var1][this.event_now[var1] + 3];
                     if ((this.gr.rmsOther[4 + this.now_eV1 * 2] & 1 << this.now_eV2) != 0) {
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 4);
                        this.nextEvent(4113);
                     }
                     break;
                  case 40:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        byte[] var10 = this.my.other;
                        Ms.i();
                        var10[7] = (byte)Ms.abs(this.event[var1][this.event_now[var1] + 3]);
                        if (this.event[var1][this.event_now[var1] + 3] < 0) {
                           this.my.dir = 4;
                        }
                     } else {
                        cur_npc = this.npc[this.getNpcLayer(var6)][this.getNpcId(var6)];
                        cur_npc.other[7] = this.event[var1][this.event_now[var1] + 3];
                        cur_npc.setNpcNum(npcSp[cur_npc.other[3] - 1][cur_npc.other[2]].aLength());
                     }

                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(16);
                     break;
                  case 41:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     if (var6 == -1) {
                        this.my.setXY(this.event[var1][this.event_now[var1] + 3], this.event[var1][this.event_now[var1] + 4], 0, 0);
                        this.my.frame_c = -1;
                        if (this.srcNpcNo == -1) {
                           this.map_x = this.map_set(this.event[var1][this.event_now[var1] + 3], this.cols, 12, 6, 240, 20);
                           this.map_y = this.map_set(this.event[var1][this.event_now[var1] + 4], this.rows, 16, 8, 320, 20);
                        }
                     } else {
                        this.now_eV1 = this.getNpcLayer(var6);
                        this.now_eV2 = this.getNpcId(var6);
                        this.npc[this.now_eV1][this.now_eV2].other[0] = this.event[var1][this.event_now[var1] + 3];
                        this.npc[this.now_eV1][this.now_eV2].other[1] = this.event[var1][this.event_now[var1] + 4];
                        this.npc[this.now_eV1][this.now_eV2].setXY_npc();
                     }

                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 5);
                     this.nextEvent(4112);
                     break;
                  case 42:
                     this.now_eV1 = this.event[var1][this.event_now[var1] + 2];
                     this.now_eV2 = this.event[var1][this.event_now[var1] + 3];
                     Mg.i().go(this.gr, this.now_eV1, -1, this.now_eV2);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(4113);
                     break;
                  case 43:
                     var6 = this.event[var1][this.event_now[var1] + 2] - 1;
                     this.now_eV1 = this.getNpcLayer(var6);
                     this.now_eV2 = this.getNpcId(var6);
                     this.npc[this.now_eV1][this.now_eV2].other[8] = this.event[var1][this.event_now[var1] + 3];
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 4);
                     this.nextEvent(16);
                     break;
                  case 44:
                     var6 = this.event[var1][this.event_now[var1] + 2];
                     if (var6 == 0) {
                        var3 = this.gr.rmsSms[6] == 10;
                        this.ifEvent(var1, 3, 16, 2, var3);
                     } else if (var6 == 1) {
                        SMSSender.i(this.gr).go(2, true);
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 3);
                        this.nextEvent(0);
                     } else if (var6 == 2) {
                        this.ifEvent(var1, 3, 16, 2, SMSSender.i(this.gr).sms_b);
                     }
                     break;
                  case 45:
                     this.runEvent_srcMove(var1);
                     break;
                  case 46:
                     Sound.i().setMusicId(this.event[var1][this.event_now[var1] + 2]);
                     Sound.i().setMusic(false);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(16);
                     break;
                  case 47:
                     this.my.state = 10;
                     if (++this.sleep_count == this.event[var1][this.event_now[var1] + 2]) {
                        this.sleep_count = 0;
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 3);
                        this.nextEvent(17);
                     }
                     break;
                  case 48:
                     this.my.state = 10;
                     this.srcFlash_c = (byte)(this.event[var1][this.event_now[var1] + 2] << 1);
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(16);
                     break;
                  case 49:
                     if (this.gr.magic_id == -2) {
                        this.my.state = 10;
                        this.gr.magic_id = this.event[var1][this.event_now[var1] + 2];
                        this.gr.magic_x = this.map_x + this.event[var1][this.event_now[var1] + 3] * 20 + 10;
                        this.gr.magic_y = this.map_y + this.event[var1][this.event_now[var1] + 4] * 20 + 20;
                        this.gr.setMagic(this.event[var1][this.event_now[var1] + 2] / 5);
                     } else if (this.gr.magic_id == -1) {
                        this.gr.magic_id = -2;
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 5);
                        this.nextEvent(16);
                     }
                     break;
                  case 50:
                     if (this.my.state != 24 && this.my.state != 25) {
                        if (this.my.frame_c == -1 && this.my.state != 23) {
                           this.my.state = 23;
                        }
                        break;
                     }

                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(0);
                     if (this.my.state == 24) {
                        this.gr.say("保存进度完成", 1);
                        this.gr.saveGame();
                     }

                     this.my.state = 0;
                     break;
                  case 51:
                     switch (this.event[var1][this.event_now[var1] + 2]) {
                        case 0:
                           if (this.eventGoing == 0) {
                              this.gr.say(Ms.i().getDialogs(this.event[var1], this.event_now[var1] + 4, this.event[var1][this.event_now[var1] + 3]), -1);
                              this.eventGoing = 1;
                           } else if (this.gr.say_c == 0) {
                              var10000 = this.event_now;
                              var10000[var1] = (short)(var10000[var1] + (this.event[var1][this.event_now[var1] + 3] << 1) + 4);
                              this.nextEvent(272);
                           }
                           continue;
                        case 1:
                        case 2:
                           if (this.eventGoing == 0) {
                              this.my.state = 22;
                              this.black_0 = this.black_1 = 0;
                              this.gr.setStringB(Ms.i().getDialogs(this.event[var1], this.event_now[var1] + 4, this.event[var1][this.event_now[var1] + 3]), 202, 3);
                              this.eventGoing = this.event[var1][this.event_now[var1] + 2];
                           } else if (this.eventGoing == 3) {
                              var10000 = this.event_now;
                              var10000[var1] = (short)(var10000[var1] + (this.event[var1][this.event_now[var1] + 3] << 1) + 4);
                              this.nextEvent(272);
                              this.gr.about_d = null;
                              this.black_0 = -1;
                           }
                        default:
                           continue;
                     }
                  case 52:
                     this.my.state = 10;
                     this.xxx = this.event[var1][this.event_now[var1] + 2];
                     var10000 = this.event_now;
                     var10000[var1] = (short)(var10000[var1] + 3);
                     this.nextEvent(16);
                     break;
                  case 53:
                     if (this.initSrcEffect(this.event[var1][this.event_now[var1] + 2])) {
                        var10000 = this.event_now;
                        var10000[var1] = (short)(var10000[var1] + 3);
                        this.nextEvent(16);
                     }
               }
            } while(this.event_state == 1);
         }
      }

   }

   private void runEvent_getMon(int i) {
      this.gmErr = false;
      if (this.gr.getMonster(this.event[i][this.event_now[i] + 2], this.event[i][this.event_now[i] + 3], this.event[i][this.event_now[i] + 4], -1) == -1) {
         this.gr.say("宠物空间已满，无法获得新的宠物，请整理寄存所空出一个空间。", -1);
         this.gmErr = true;
         this.gr.mini_state = 6;
         this.gr.view_state = 1;
         this.gr.goVIEW_MONSTER();
      } else {
         short[] var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + this.event[i][this.event_now[i] + 1] + 2);
         this.nextEvent(4368);
      }

   }

   public void bkEvent_getMon() {
      GameRun var10000 = this.gr;
      GameRun.run_state = -10;
   }

   private void runEvent_brow(int i) {
      int event_temp = this.event[i][this.event_now[i] + 2] - 1;
      if (event_temp == -1) {
         cur_npc = this.my;
      } else {
         cur_npc = this.npc[this.getNpcLayer(event_temp)][this.getNpcId(event_temp)];
      }

      if (this.event[i][this.event_now[i] + 4] == 0) {
         cur_npc.brow_type = this.event[i][this.event_now[i] + 3];
         cur_npc.brow_action = 0;
         cur_npc.brow_time = 0;
      } else if (this.event[i][this.event_now[i] + 4] == 1) {
         cur_npc.other[6] = this.event[i][this.event_now[i] + 3];
      } else if (this.event[i][this.event_now[i] + 4] == 2) {
         cur_npc.brow_type = -1;
         cur_npc.other[6] = 0;
      }

      short[] var10000 = this.event_now;
      var10000[i] = (short)(var10000[i] + 5);
      this.nextEvent(4112);
   }

   private void runEvent_srcMove(int i) {
      this.my.state = 10;
      int event_temp = this.event[i][this.event_now[i] + 2] - 1;
      int x = false;
      int y = false;
      int x;
      int y;
      if (event_temp == -1) {
         x = this.my.getIx();
         y = this.my.getIy();
         this.srcNpcNo = -1;
      } else {
         this.now_eV1 = this.getNpcLayer(event_temp);
         this.now_eV2 = this.getNpcId(event_temp);
         this.srcNpcNo = this.now_eV1 == 0 ? this.now_eV2 : 0;
         x = this.npc[this.now_eV1][this.now_eV2].other[0];
         y = this.npc[this.now_eV1][this.now_eV2].other[1];
      }

      byte step = 10;
      x = this.map_set(x, this.cols, 12, 6, 240, 20) - this.map_x;
      y = this.map_set(y, this.rows, 16, 8, 320, 20) - this.map_y;
      Ms.i();
      if (Ms.abs(y) < step) {
         Ms.i();
         if (Ms.abs(x) < step) {
            short[] var10000 = this.event_now;
            var10000[i] = (short)(var10000[i] + 3);
            this.nextEvent(4112);
            return;
         }
      }

      Ms.i();
      int var10001;
      if (Ms.abs(x) >= step) {
         var10001 = this.map_x;
         Ms.i();
         this.map_x = var10001 + x / Ms.abs(x) * step;
      }

      Ms.i();
      if (Ms.abs(y) >= step) {
         var10001 = this.map_y;
         Ms.i();
         this.map_y = var10001 + y / Ms.abs(y) * step;
      }

   }

   private void runEvent_goShop(int i) {
      int event_temp = this.event[i][this.event_now[i] + 3];
      short[] var10000;
      if (event_temp == 4) {
         if (this.inShop == 0 && this.my.state != 16) {
            this.my.state = 16;
            this.inShop = 1;
            this.gr.setAction_str(new String[]{"回复", "不回复"});
            this.gr.popMenu = 0;
            this.gr.sell_money = 0;

            for(byte d = 0; d < this.gr.myMon_length; ++d) {
               GameRun var4 = this.gr;
               var4.sell_money += 40 + 20 * this.gr.myMonsters[d].monster[4];
            }
         } else if (this.my.state == 0) {
            if (this.inShop == 1 && this.gr.popMenu == 0) {
               this.inShop = 2;
               if (!this.gr.checkMonster() && this.gr.isMoney(this.gr.sell_money, true)) {
                  this.gr.healMonster(true);
               }
            } else if (this.gr.say_c == 0) {
               this.inShop = 0;
               var10000 = this.event_now;
               var10000[i] = (short)(var10000[i] + 4);
               this.nextEvent(0);
            }
         }
      } else {
         if (event_temp < 3) {
            this.gr.goBUY_ITEM(this.event[i][this.event_now[i] + 2], event_temp);
         } else if (event_temp == 3) {
            this.gr.goVIEW_COMPUTER(0);
         } else if (event_temp == 5) {
            this.gr.goNidus(0);
         } else if (event_temp == 6) {
            this.gr.view_state = 4;
            this.gr.popMenu = -1;
         } else if (event_temp == 7) {
            this.gr.view_state = 1;
         }

         var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + 4);
         this.nextEvent(16);
      }

   }

   private void runEvent_miniGame_myLevel(int i) {
      int event_temp = this.event[i][this.event_now[i] + 2];
      short[] var10000;
      if (event_temp > 1) {
         var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + 3);
         this.nextEvent(4368);
      } else if (this.eventGoing < 2) {
         if (event_temp == 0) {
            if (this.gr.magic_id == -2) {
               if (this.gr.rmsOther[3] == 5) {
                  this.gr.magic_id = -2;
                  this.eventGoing = 2;
                  this.gr.say("您当前的训练师等级已达到上限！", 0);
               } else {
                  this.gr.sell_money = this.gr.isMyLevel(true);
                  if (this.gr.sell_money == -1) {
                     this.my.state = 10;
                     this.gr.magic_id = 28;
                     this.gr.magic_x = this.map_x + this.my.x + 10;
                     this.gr.magic_y = this.map_y + this.my.y + 19;
                     this.gr.setMagic(this.gr.magic_id / 5);
                     this.gr.setMaxTakes(false);
                  } else {
                     this.eventGoing = 2;
                  }
               }
            } else if (this.gr.magic_id == -1) {
               this.gr.magic_id = -2;
               this.eventGoing = 2;
               if (this.gr.rmsOther[3] > 3) {
                  this.gr.say("恭喜您的训练师等级已提升为#7" + this.gr.rmsOther[3] + "#0，您商店能存放的宠物#7暴增到" + this.gr.max_monsters, -1);
               } else {
                  this.gr.say("恭喜您的训练师等级已提升为#7" + this.gr.rmsOther[3] + "#0，您随身可携带宠物数量#7暴增到" + this.gr.max_takes + "#0，您商店能存放的宠物#7暴增到" + this.gr.max_monsters, -1);
               }
            }
         } else if (event_temp == 1) {
            if (this.eventGoing == 0 && this.gr.monInfoList[104] == 0) {
               this.gr.sell_money = 0;
               this.eventGoing = 3;
               this.gr.say("此次您没有捕获到全新的宠物，捕获并不是唯一途径，有些宠物需要通过进化才能获得哦。", -1);
            } else if (this.eventGoing == 0) {
               this.eventGoing = 3;
               this.gr.sell_money = this.gr.monInfoList[104];
               this.gr.monInfoList[104] = 0;
               this.gr.say("此次您捕获了" + this.gr.sell_money + "个新宠物，这里是王国训练师机构奖励给您的" + this.gr.sell_money + "个徽章，希望再接再厉哦。", -1);
               GameRun var3 = this.gr;
               var3.coin += this.gr.sell_money;
            }
         }
      } else if (this.gr.say_c == 0) {
         if (this.eventGoing == 2) {
            if (this.gr.rmsOther[3] == 5) {
               this.gr.say("您当前的训练师等级已达到上限！", 0);
            } else {
               this.gr.sell_money = (byte)(this.gr.isMyLevel(false) - this.gr.monInfoList[103]);
               if (this.gr.sell_money < 1) {
                  this.gr.sell_money = 0;
               }

               this.gr.say("当前训练师等级为#7" + this.gr.rmsOther[3] + "#0距离下次升级还需要捕捉#7" + this.gr.sell_money + "#0只不同的宠物，努力获得新宠吧。", -1);
            }

            this.eventGoing = 4;
         } else if (this.eventGoing == 3 && this.gr.sell_money > 0) {
            this.gr.say("获得：徽章x" + this.gr.sell_money, 0);
            this.eventGoing = 4;
         } else {
            var10000 = this.event_now;
            var10000[i] = (short)(var10000[i] + 3);
            this.nextEvent(4368);
         }
      }

   }

   private void runEvent_item_add_del(int i) {
      int event_temp = 0;
      if (this.eventGoing == 0) {
         this.now_eV1 = this.event[i][this.event_now[i] + 2];
         this.now_eV2 = this.event[i][this.event_now[i] + 3];
         GameRun var3;
         if (this.event[i][this.event_now[i] + 3] >= 0) {
            event_temp = this.gr.addItem(this.now_eV1, this.now_eV2);
         } else {
            byte var10000 = this.gr.findItem(-2, this.now_eV1, true);
            Ms.i();
            if (var10000 >= Ms.abs(this.now_eV2)) {
               var3 = this.gr;
               byte var10001 = this.now_eV1;
               Ms.i();
               var3.deleteItems(var10001, Ms.abs(this.now_eV2));
            } else {
               event_temp = -1;
               this.gr.say("道具不足！", 0);
            }
         }

         if (event_temp != -1) {
            var3 = this.gr;
            StringBuffer var5 = (new StringBuffer()).append(this.now_eV2 >= 0 ? "获得：" : "失去：").append(this.gr.getNameItem(this.now_eV1)).append("x");
            Ms.i();
            var3.say(var5.append(Ms.abs(this.now_eV2)).toString(), 0);
         }

         this.eventGoing = 1;
      } else if (this.gr.say_c == 0) {
         if (event_temp == -1) {
            this.setEventNow(i, true);
         } else {
            short[] var4 = this.event_now;
            var4[i] = (short)(var4[i] + 4);
            this.nextEvent(4368);
         }
      }

   }

   private void runEvent_dialog(int i) {
      this.checkNpcT = -1;
      if (this.eventGoing == 0) {
         this.eventGoing = 1;
         if (this.anole_type == 1) {
            this.setAnole();
         }

         int event_temp = this.event[i][this.event_now[i] + 2] - 1;
         if (event_temp == -1) {
            this.npcName = "塞其";
            this.now_eV2 = 0;
         } else {
            this.now_eV1 = this.getNpcLayer(event_temp);
            this.now_eV2 = this.getNpcId(event_temp);
            this.npcName = this.npcNameData[this.now_eV2];
            if (this.npcName.equals("路人")) {
               this.npcName = null;
            }

            this.now_eV2 = this.npc[this.now_eV1][this.now_eV2].other[2];
         }

         this.initNpcFaceOne(this.npcName);
         this.dialog = Ms.i().groupString(Ms.i().getDialogs(this.event[i], this.event_now[i] + 5, this.event[i][this.event_now[i] + 4]), 220);
         this.dialog_no = 0;
         this.my.state = 1;
         this.bDirTalk = false;
      } else if (this.eventGoing == 2) {
         short[] var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + (this.event[i][this.event_now[i] + 4] << 1) + 5);
         this.nextEvent(4369);
      }

   }

   private void runEvent_select(int i) {
      if (this.my.state != 12 && this.my.state != 13 && this.my.state != 14) {
         this.sEvent_eV2 = this.event[i][this.event_now[i] + 1];
         this.sEvent_eV1 = 0;
         this.gr.action_str = null;
         this.gr.action_str = new String[this.sEvent_eV2];

         for(byte j = 0; j < this.sEvent_eV2; ++j) {
            this.gr.action_str[j] = Ms.i().getDialogs(this.event[i], this.event_now[i] + 2 + this.sEvent_eV1 + 1, this.event[i][this.event_now[i] + 2 + this.sEvent_eV1]);
            this.sEvent_eV1 = (byte)(this.sEvent_eV1 + (this.event[i][this.event_now[i] + 2 + this.sEvent_eV1] << 1) + 1);
         }

         if (this.sIfElse != -1) {
            this.event_state = 1;
            this.gr.cur_a = this.sIfElse;
            this.my.state = 13;
            this.gr.action_str = null;
            this.sIfElse = -1;
         } else {
            this.gr.cur_a = 0;
            this.my.state = 12;
         }
      } else if (this.my.state == 13) {
         this.sEvent_eV1 = (byte)(this.sEvent_eV1 + 2);
         byte a = this.event[i][this.event_now[i] + this.sEvent_eV1 + (this.gr.cur_a << 1)];
         byte b = this.event[i][this.event_now[i] + this.sEvent_eV1 + (this.gr.cur_a << 1) + 1];
         short d = (short)((a & 255) << 8 | b & 255);
         short[] var10000 = this.event_now;
         var10000[i] = (short)(var10000[i] + this.sEvent_eV1 + (this.sEvent_eV2 << 1) + d);
         this.nextEvent(17);
      } else if (this.my.state == 14) {
         this.setEventNow(i, true);
      }

   }

   public void run_map() {
      if (this.my.state != 9 && this.my.state != 6) {
         this.eventCheck();
      }

      GameRun var10000;
      if (this.my.state == 5 && this.my.frame_c < 1) {
         this.setMoveStop_m();
         this.my.state = 6;
         var10000 = this.gr;
         GameRun.mc.initChangeMapS();
      }

      if (this.my.state == 6) {
         this.gr.createOver = 1;
         this.goNextMap();
      } else {
         if (this.my.state == 2 && this.eventGoing == 0) {
            if ((this.npc[0][this.get_meet].other[4] == 1 || this.npc[0][this.get_meet].other[4] == 3) && this.npc[0][this.get_meet].other[5] > 0) {
               this.npcName = this.npcNameData[this.get_meet];
               if (this.npcName.equals("路人")) {
                  this.npcName = null;
               }

               this.initNpcFaceOne(this.npcName);
               this.dialog = Ms.i().groupString(this.npcStringData[this.npc[0][this.get_meet].other[5] - 1], 220);
               this.dialog_no = 0;
               this.my.state = 1;
            } else {
               this.my.state = 0;
            }
         } else if (this.my.state == 8 && this.my.frame_c == -1) {
            if (this.gr.immueEnemy == 0) {
               var10000 = this.gr;
               String[] var10001 = this.gr.battleSay;
               Ms.i();
               var10000.say(var10001[Ms.getRandom(3)], 0);
               var10000 = this.gr;
               GameRun.mc.repaint();
               this.gr.battleType(4);
               this.gr.goBattlePVC();
            } else {
               this.my.state = 0;
            }
         }

         this.runMove();
      }
   }

   public void paint_map(Graphics g) {
      Map.g = g;
      if (this.my.state == 6) {
         this.gr.drawChangeMap(false, this.gr.b_c, 30, 282, 180);
      } else if (this.my.state == 17) {
         this.drawMiniMap();
      } else if (this.my.state == 18) {
         this.drawMission();
      } else {
         Ui.i().fillRect(this.bgColor, 0, 0, 240, 320);
         this.setMapOffer();
         if (this.xxx > 0) {
            --this.xxx;
            g.translate(this.xxx % 2, this.xxx % 2);
         }

         GameRun var10001 = this.gr;
         this.drawMap(GameRun.offG);
         this.drawNpcAni(this.npc[2]);
         this.drawNMAni(this.npc[0]);
         this.bkgroundToPaint(this.frontData, this.mapImg, -this.map_x, -this.map_y, this.moduleData, g, 0, 0, 240, 320, false);
         this.drawNpcAni(this.npc[1]);
         this.drawMapRect(g);
         this.drawStep(g, 223, 0, 16);
         this.drawBrow();
         this.drawCheck5(4);
         this.drawCloud();
         if (this.gr.magic_id > -1 && this.gr.drawMagicC(this.gr.magic_id / 5 > 5 ? 5 : this.gr.magic_id / 5, this.gr.magic_id / 5 > 5 ? 5 : this.gr.magic_id / 5, this.gr.magic_id / 5 >= 5 ? this.gr.magic_id - 25 : this.gr.magic_id % 5, this.gr.magic_x, this.gr.magic_y + 16, 0)) {
            if (this.my.state == 21) {
               this.gr.magic_id = -2;
               this.setAnole();
            } else {
               this.gr.magic_id = -1;
            }

            this.my.state = 0;
         }

         g.translate(-g.getTranslateX(), -g.getTranslateY());
         this.gr.drawPauseIco(this.my.state);
         if (this.my.state == 19) {
            this.drawMySave();
         } else {
            if (this.gr.rmsOther[2] > 0) {
               Ui.i().drawString("9:骑乘", 120, 312, 1 | 32, 0, 2);
            }

            this.gr.drawCityName();
            this.drawSrcEffect();
            if (this.gr.view_state == 4) {
               byte[] id = new byte[]{4, 25, 56};
               Ui.i().drawKuang(0, 260, 240, 60);
               this.gr.showStringM(this.gr.getNameMonsterInfo(id[this.gr.cur_a]), 120, 272, 10, 3);
               Object var3 = null;
            } else if (this.my.state == 16) {
               Ui.i().drawKuang(0, 240, 240, 80);
               this.gr.showStringM("是否回复所有宠物？", 120, 244, 10, 3);
               this.gr.drawSelectMenu(this.gr.action_str, 82, 140, 76, -1, 0, this.gr.popMenu);
               this.gr.showStringM("所需金额：" + this.gr.sell_money, 120, 264, 10, 3);
               this.gr.drawMoney(120, 300, 3, false);
            }

            if (this.my.state == 23) {
               Ui.i().drawKuang(0, 280, 240, 40);
               Ui.i().drawString("是否保存进度？", 120, 284, 17, 3, 0);
               Ui.i().drawYesNo(true, true);
            } else if (this.my.state == 12) {
               this.gr.drawSelectMenu(this.gr.action_str, 63, 160 - 20 * (this.gr.action_str.length >> 1), 114, 0, 0, this.gr.cur_a);
            }

            if (this.dialog_no > -1) {
               this.dialog(this.npcName);
            }

            this.gr.map_flashString();
            if (this.my.state == 20) {
               this.drawAnoleSel();
            }

         }
      }
   }

   private void setMapOffer() {
      if (this.map_y + this.rows * 20 < 320 && this.rows > 16) {
         this.map_y = 320 - this.rows * 20;
      } else if (this.map_y > 0 && this.rows > 16) {
         this.map_y = 0;
      } else if (this.map_x > 0 && this.cols > 12) {
         this.map_x = 0;
      } else if (this.map_x + this.cols * 20 < 240 && this.cols > 12) {
         this.map_x = 240 - this.cols * 20;
      }

   }

   private void drawStep(Graphics g, int x, int y, int h) {
      if (this.bStep > 0) {
         g.setColor(3947323);
         g.drawRect(x + 1, y, h - 2, h);
         g.drawRect(x, y + 1, h, h - 2);
         g.setColor(13421772);
         g.drawRect(x + 2, y + 1, h - 4, h - 2);
         g.drawRect(x + 1, y + 2, h - 2, h - 4);
         Ui.i().fillRect(16777215, x + 2, y + 2, h - 3, h - 3);
         Ui.i().drawUi(67, x + (h >> 1), y + (h >> 1), 2 | 1, 0);
         if (this.bStep % 12 < 6) {
            Ui.i().drawLine(15597568, x + h - 1, y, x, y + h - 1);
            Ui.i().drawLine(0, x + h, y, x, y + h);
            Ui.i().drawLine(15597568, x + h, y + 1, x + 1, y + h);
         }

         if (--this.bStep < 2) {
            this.bStep = 100;
         }
      }

   }

   public void drawBlackSRC(Graphics g, int y, boolean mode) {
      g.setColor(16777215);
      byte d = 0;
      byte offy = (byte)(this.black_0 - 5 > 0 ? this.black_0 - 5 : 0);

      while(true) {
         if (mode) {
            if (d >= this.gr.about_d.length) {
               break;
            }
         } else if (d > this.black_0 || d >= this.gr.about_d.length) {
            break;
         }

         if (!mode) {
            if (this.black_0 == d) {
               g.setClip(0, y + (d - offy) * 24, this.black_1 << 1, 24);
            }

            Ui.i().drawStringColor(this.gr.about_d[d].toString(), 19, y + (d - offy) * 24, 0, 0);
            g.setClip(0, 0, 240, 320);
         } else {
            short sw = (short)(Ms.i().getStringWidth(this.gr.about_d[d].toString()) - this.gr.getSIndexW(this.gr.about_d[d].toString()));
            Ui.i().drawStringColor(this.gr.about_d[d].toString(), 240 - sw >> 1, y + d * 24, 0, 0);
         }

         ++d;
      }

      if (!mode) {
         g.setColor(0);
         g.fillRect(0, 0, 240, 40);
         g.fillRect(0, 280, 240, 40);
      }

      if (this.black_0 == this.gr.about_d.length) {
         this.gr.drawZero(120, 296, 1 | 32, 0);
      } else if (!mode && (this.black_1 = (byte)(this.black_1 + 4)) >= 120) {
         this.black_1 = 0;
         ++this.black_0;
      } else if (mode) {
         this.black_0 = (byte)this.gr.about_d.length;
      }

   }

   public void key_map() {
      if (this.my.state == 17) {
         this.keyMiniMap();
      } else if (this.my.state == 18) {
         this.keyMission();
      } else if (this.my.state == 12) {
         this.keySelectMenu();
      } else if (this.my.state == 19) {
         this.keyMySave();
      } else if (this.my.state == 22 && this.black_0 > -1 && this.black_0 == this.gr.about_d.length && Ms.i().key_Num0()) {
         this.eventGoing = 3;
         this.black_0 = -1;
         Ms.i().keyRelease();
      } else {
         GameRun var10000;
         if (this.my.state == 16) {
            if (Ms.i().key_delay()) {
               return;
            }

            if (Ms.i().key_Up_Down()) {
               var10000 = this.gr;
               var10000.popMenu = (byte)(var10000.popMenu ^ 1);
            } else if (Ms.i().key_S1_Num5() || Ms.i().key_S2()) {
               if (Ms.i().key_S2()) {
                  this.gr.popMenu = 1;
               }

               this.my.state = 0;
               return;
            }
         } else {
            if (this.my.state == 10) {
               Ms.i().keyRelease();
               return;
            }

            if (this.my.state == 23) {
               if (Ms.i().key_S2()) {
                  this.my.state = 25;
               } else if (Ms.i().key_S1_Num5()) {
                  this.my.state = 24;
               }

               Ms.i().keyRelease();
               return;
            }

            if (this.my.state == 11) {
               if (Ms.i().key_S1_Num5()) {
                  this.gr.say_c = 0;
               }

               if (this.gr.say_c == 0) {
                  this.my.state = 0;
               }

               return;
            }

            if (this.my.state == 20) {
               this.keyAnoleSel();
               return;
            }
         }

         if (this.my.state == 1 && Ms.i().key_S1_Num5()) {
            if (this.dialog_no + 2 >= this.dialog.length) {
               this.dialog_no = -1;
               this.dialog = null;
               if (this.eventGoing == 0) {
                  this.my.state = 0;
               } else {
                  this.eventGoing = 2;
               }

               if (this.bDirTalk) {
                  this.npc[0][this.get_meet].other[7] = this.npcDirTalk;
                  if (this.npc[0][this.get_meet].other[7] > -1) {
                     this.npc[0][this.get_meet].dir = 3;
                  }

                  this.bDirTalk = false;
               }

               return;
            }

            this.dialog_no = (byte)(this.dialog_no + 2);
            Ms.i().keyRelease();
         }

         if (this.my.frame_c <= 0) {
            if (this.my.state == 0) {
               var10000 = this.gr;
               if (GameRun.run_state != 98) {
                  if (Ms.i().key_S1()) {
                     this.gr.goYouPAUSE(0);
                  } else if (Ms.i().key_S2()) {
                     SMSSender.i(this.gr).go(0, true);
                  }
               }
            }

            if (this.my.state == 0) {
               Ms.i();
               this.map_key = (byte)(-Ms.key);
               this.doKey();
            }
         }
      }
   }

   private void drawAnoleSel() {
      byte h = 20;
      this.gr.cur_a = (byte)Ms.i().mathSpeedN(this.gr.cur_a, h * 2 + 12, 8, false);
      Ui.i().drawK1(0, 320 - this.gr.cur_a, 76, h + 4, 2);
      Ui.i().drawK2(0, 320 + h + 4 - this.gr.cur_a, 240, h + 8, 0);

      byte j;
      for(byte i = 0; i < 5; ++i) {
         byte c;
         if (!this.isAnoleType(i)) {
            c = 6;
            j = (byte)(70 + i);
         } else {
            c = (byte)(i == this.anoleSel ? 1 : 0);
            if (i == this.anole_type) {
               j = 69;
            } else {
               j = (byte)(i == this.anoleSel ? 5 + i : i);
            }
         }

         Ui.i().drawK0(32 + i * 240 / 6, 320 + h + 8 - this.gr.cur_a, 20, 20, c);
         Ui.i().drawUi(j, 32 + i * 240 / 6 + 10, 320 + h + 8 - this.gr.cur_a + 18, 33, 0);
      }

      if (this.anoleSel == this.anole_type) {
         j = 0;
      } else {
         j = (byte)(this.isAnoleType(this.anoleSel) ? this.anoleSel + 1 : 6);
      }

      Ui.i().drawString(this.gr.about_a[j].toString(), 38, 320 - this.gr.cur_a, 17, 1, 2);
      Ui.i().drawK4(31 + this.anoleSel * 240 / 6 + this.gr.cur_b / 3, 320 + h + 7 - this.gr.cur_a + this.gr.cur_b / 3, 22 - (this.gr.cur_b / 3 << 1), 22 - (this.gr.cur_b / 3 << 1));
      GameRun var10000 = this.gr;
      byte var10002 = var10000.cur_b;
      var10000.cur_b = (byte)(var10002 + 1);
      if (var10002 > 4) {
         this.gr.cur_b = 0;
      }

      Ui.i().drawYesNo(true, true);
   }

   private void keyAnoleSel() {
      if (!Ms.i().key_delay()) {
         if (Ms.i().key_Left_Right()) {
            this.anoleSel = Ms.i().select(this.anoleSel, 0, 4);
         } else if (Ms.i().key_S1_Num5()) {
            if (!this.isAnoleType(this.anoleSel)) {
               this.gr.say("还没有获得该骑宠", 0);
            } else if (this.anoleSel == 4) {
               this.gr.say("只能在水里使用", 0);
            } else {
               if (this.anole_type == this.anoleSel) {
                  this.anole_type = -1;
               } else {
                  this.anole_type = this.anoleSel;
               }

               if (this.anole_type == 3 && this.gr.rmsSms[0] == 0) {
                  SMSSender.i(this.gr).go(1, true);
                  this.anole_type = -1;
               } else {
                  if (this.anole_type == 3 && this.gr.rmsSms[0] < 0) {
                     ++this.gr.rmsSms[0];
                     this.gr.say("奇异兽不仅跑得快而且可以不遇敌，现提供3次试骑体验，先试后买！", -1);
                  }

                  this.gr.magic_id = 13;
                  this.gr.magic_x = this.map_x + this.my.x + 10;
                  this.gr.magic_y = this.map_y + this.my.y + 19;
                  this.gr.setMagic(this.gr.magic_id / 5);
                  this.my.state = 21;
               }
            }

            Ms.i().keyRelease();
         } else if (Ms.i().key_S2() || Ms.i().key_Num9()) {
            this.my.state = 0;
            Ms.i().keyRelease();
         }

      }
   }

   public boolean initAnoleValue() {
      if (this.isAnole(this.my.x, this.my.y, this.anole_type)) {
         this.gr.say("不能更换坐骑", 0);
         return false;
      } else {
         this.my.state = 20;
         this.gr.cur_a = 0;
         this.gr.cur_b = 0;
         this.gr.setStringB("下坐骑#n植物坐骑#n金属坐骑#n飞行坐骑#n野兽坐骑#n海洋坐骑#n无此坐骑", 240, 0);
         return true;
      }
   }

   private boolean isAnoleType(int i) {
      return (this.gr.rmsOther[2] & 1 << i) != 0;
   }

   public void addAnole(int i) {
      if (i == 13 || i == 34 || i == 49 || i == 66 || i == 82) {
         byte i;
         if (i == 13) {
            i = 0;
         } else if (i == 34) {
            i = 1;
         } else if (i == 49) {
            i = 2;
         } else if (i == 66) {
            i = 3;
         } else {
            i = 4;
         }

         byte[] var10000 = this.gr.rmsOther;
         var10000[2] = (byte)(var10000[2] | 1 << i);
      }
   }

   public void setAnole() {
      this.gr.immueEnemy = (byte)(this.anole_type == 3 ? 1 : 0);
      this.my.speed = this.getMySpeed();
      Ms.i().setSprite(npcSp[1][0], "data/npc1/" + (this.anole_type == -1 ? "0" : "r" + this.anole_type), this.anole_type == -1);
      this.my.setActionNo(false);
   }

   public byte getMySpeed() {
      if (this.anole_type == 0) {
         return 6;
      } else if (this.anole_type == 1) {
         return 6;
      } else if (this.anole_type == 2) {
         return 10;
      } else if (this.anole_type == 4) {
         return 5;
      } else {
         return (byte)(this.anole_type == 3 ? 10 : 5);
      }
   }

   private void doKey() {
      switch (this.map_key) {
         case -57:
            Ms.i().keyRelease();
            if (this.gr.rmsOther[2] > 0) {
               this.initAnoleValue();
            }
         case -55:
         default:
            break;
         case -53:
         case 5:
            this.checkSoftKey(this.my.x, this.my.y, this.dir_select[this.my.dir][0] * this.my.speed, this.dir_select[this.my.dir][1] * this.my.speed);
            break;
         case -51:
            this.goMission(1, false);
            break;
         case -49:
            this.goMission(0, false);
            break;
         case -48:
            this.goMiniMap();
            break;
         case 1:
         case 2:
         case 3:
         case 4:
            if (this.my.dir != this.map_key) {
               this.turnAround(this.map_key);
            }

            this.canMove(this.dir_select[this.map_key][0] * this.my.speed, this.dir_select[this.map_key][1] * this.my.speed);
      }

   }

   private void turnAround(int dir) {
      this.my.dir = (byte)dir;
      this.my.setActionNo(true);
   }

   public void mapMove(int x, int y, int xs, int ys) {
      if (xs > 0 && x >= this.mapLeft_NOmove && x < this.mapRight_NOmove || xs < 0 && x > this.mapLeft_NOmove && x <= this.mapRight_NOmove) {
         this.map_x -= xs;
      }

      if (ys > 0 && y >= this.mapUp_NOmove && y < this.mapDown_NOmove || ys < 0 && y > this.mapUp_NOmove && y <= this.mapDown_NOmove) {
         this.map_y -= ys;
      }

   }

   private void oneMove(Npc npc_, int i) {
      if (npc_.frame_c > 0) {
         if (this.srcNpcNo == i) {
            this.mapMove(npc_.x, npc_.y, npc_.speed_x, npc_.speed_y);
         }

         npc_.x = (short)(npc_.x + npc_.speed_x);
         npc_.y = (short)(npc_.y + npc_.speed_y);
         --npc_.frame_c;
      } else if (npc_.frame_c != -1) {
         npc_.frame_c = -1;
         npc_.setActionNo(false);
      }

   }

   private byte checkIfNpc(Npc[] npc, int ix, int iy) {
      for(byte i = (byte)(npc.length - 1); i > -1; --i) {
         if (Ms.i().isRect(ix, iy, 19, 19, npc[i].x, npc[i].y, 20, 20) && (npc[i].other[4] == 1 || npc[i].other[4] == 3)) {
            return i;
         }
      }

      return -1;
   }

   private byte checkIfOther(byte[][] data, int ix, int iy) {
      for(byte i = (byte)(data.length - 1); i > -1; --i) {
         if (Ms.i().isRect(ix, iy, 19, 19, data[i][0] * 20, data[i][1] * 20, 20, 20)) {
            return i;
         }
      }

      return -1;
   }

   private byte checkSoftKey(int A_X, int A_Y, int _speed_x, int _speed_y) {
      if (A_X + _speed_x >= 0 && A_X + _speed_x < this.cols * 20 && A_Y + _speed_y >= 0 && A_Y + _speed_y < this.rows * 20) {
         byte type = false;
         byte type = this.checkIfNpc(this.npc[0], A_X + _speed_x, A_Y + _speed_y);
         if (this.my.state == 0 && type != -1) {
            if (this.anole_type == 1 && this.npc[0][type].other[8] == 4) {
               this.npc[0][type].other[8] = -4;
               ++this.npc[0][type].other[7];
               this.npc[0][type].now_action = 0;
               this.npc[0][type].now_time = 0;
            } else if (this.npc[0][type].other[8] != -4) {
               this.my.state = 2;
               this.get_meet = type;
               if (this.npc[0][type].other[8] != 2 && this.npc[0][type].other[8] != 3 && !this.npcNameData[type].equals("路人")) {
                  this.bDirTalk = true;
                  this.npcDirTalk = this.npc[0][type].other[7];
                  byte[] var10000 = this.npc[0][type].other;
                  Ms.i();
                  var10000[7] = (byte)(Ms.abs(this.npcDirTalk) / 3 * 3 + (this.my.dir < 4 ? this.my.dir - 1 : this.my.dir - 2));
                  if (this.my.dir == 3) {
                     this.npc[0][type].other[7] = (byte)(-this.npc[0][type].other[7]);
                  } else {
                     this.npc[0][type].dir = 3;
                  }
               } else {
                  this.bDirTalk = false;
               }
            }

            return 9;
         } else {
            type = this.checkIfOther(this.item, A_X + _speed_x, A_Y + _speed_y);
            if (type != -1 && this.item[type][2] != -1) {
               Ms.i().keyRelease();
               this.get_meet = type;
               this.getItem();
               return 0;
            } else {
               return -1;
            }
         }
      } else {
         return 0;
      }
   }

   private void getItem() {
      if (this.item[this.get_meet][2] == 2) {
         this.gr.say("这个宝箱是空的。", 0);
      } else {
         byte id = (byte)(this.item[this.get_meet][3] - 2);
         GameRun var10000;
         if (id == -2) {
            var10000 = this.gr;
            var10000.money += this.item[this.get_meet][4] * 100;
            this.gr.say("获得金钱：" + this.item[this.get_meet][4] * 100 + "金", 0);
         } else if (id == -1) {
            var10000 = this.gr;
            var10000.coin += this.item[this.get_meet][4];
            this.gr.say("获得徽章：" + this.item[this.get_meet][4] + "徽章", 0);
         } else {
            this.gr.getItem(id, this.item[this.get_meet][4]);
         }

         this.item[this.get_meet][2] = 2;
      }
   }

   private boolean isTrundleNpc(int ix, int iy) {
      byte type = this.checkIfNpc(this.npc[0], ix, iy);
      if (type == -1) {
         return ix < 0 || ix >= this.cols || iy < 0 || iy >= this.rows || this.worldData[ix][iy] == 1;
      } else {
         return this.npc[0][type].other[4] == 1 || this.npc[0][type].other[4] == 3;
      }
   }

   private byte checkMap(int A_X, int A_Y, int _speed_x, int _speed_y) {
      this.checkNpcT = -1;
      this.checkType = this.checkIfOther(this.item, A_X + _speed_x, A_Y + _speed_y);
      if (this.checkType != -1) {
         this.checkNpcT = -2;
         return 1;
      } else if (A_X + _speed_x >= 0 && A_X + _speed_x < this.cols * 20 && A_Y + _speed_y >= 0 && A_Y + _speed_y < this.rows * 20) {
         this.checkType = this.checkIfNpc(this.npc[0], A_X + _speed_x, A_Y + _speed_y);
         if (this.checkType != -1) {
            if (!this.npcNameData[this.checkType].equals("路人") || this.npc[0][this.checkType].other[5] > 0) {
               this.checkNpcT = this.checkType;
            }

            return 1;
         } else {
            this.checkType = this.checkIfOther(this.door, A_X + _speed_x, A_Y + _speed_y);
            if (this.checkType == -1 || this.door[this.checkType][5] != 2 && this.map_key != this.door[this.checkType][2]) {
               return this.checkWorld(A_X, A_Y, _speed_x, _speed_y, false);
            } else {
               this.my.state = 5;
               this.get_meet = this.checkType;
               return 0;
            }
         }
      } else {
         return 1;
      }
   }

   private byte checkWorld(int A_X, int A_Y, int _speed_x, int _speed_y, boolean mode) {
      byte i = 0;
      byte check_n = 1;
      if (_speed_x == 0 && A_X % 20 != 0 || _speed_y == 0 && A_Y % 20 != 0) {
         check_n = 2;
      }

      for(; i < check_n; ++i) {
         byte ix;
         byte iy;
         if (_speed_y == 0) {
            iy = (byte)((A_Y + 20 * i) / 20);
            ix = (byte)(_speed_x >= 0 ? (A_X + _speed_x + 19) / 20 : (A_X + _speed_x) / 20);
         } else {
            ix = (byte)((A_X + 20 * i) / 20);
            iy = (byte)(_speed_y >= 0 ? (A_Y + _speed_y + 19) / 20 : (A_Y + _speed_y) / 20);
         }

         if (mode) {
            if (this.worldData[ix][iy] != 0) {
               return (byte)this.worldData[ix][iy];
            }
         } else if (this.isCan_not_pass(ix, iy)) {
            return 1;
         }
      }

      return 0;
   }

   private boolean isAnole(int _x, int _y, int _anole_type) {
      byte _anole_type;
      if (_anole_type == 0) {
         _anole_type = 3;
      } else if (_anole_type == 2) {
         _anole_type = 4;
      } else {
         if (_anole_type != 4) {
            return false;
         }

         _anole_type = 2;
      }

      byte check_n = 0;
      if (_x % 20 != 0) {
         ++check_n;
      }

      if (_y % 20 != 0) {
         ++check_n;
      }

      check_n = (byte)(1 << check_n);
      if (check_n == 4 && this.worldData[(_x + 19) / 20][_y / 20] == _anole_type) {
         return true;
      } else {
         if (check_n > 1) {
            if (_x % 20 != 0 && this.worldData[(_x + 19) / 20][(_y + 19) / 20] == _anole_type) {
               return true;
            }

            if (_y % 20 != 0 && this.worldData[_x / 20][_y / 20] == _anole_type) {
               return true;
            }
         }

         return this.worldData[_x / 20][_y / 20] == _anole_type;
      }
   }

   private boolean isCan_not_pass(int ix, int iy) {
      if (this.worldData[ix][iy] == 1) {
         return true;
      } else if (this.anole_type == 0) {
         return this.worldData[ix][iy] == 2 || this.worldData[ix][iy] == 4;
      } else if (this.anole_type == 2) {
         return this.worldData[ix][iy] == 3 || this.worldData[ix][iy] == 2;
      } else if (this.anole_type == 4) {
         return this.worldData[ix][iy] != 2;
      } else {
         return this.worldData[ix][iy] == 2 || this.worldData[ix][iy] == 3 || this.worldData[ix][iy] == 4;
      }
   }

   private void meetGrass() {
      if (this.meet_step < this.step_MEET) {
         if (this.worldData[this.my.getIx()][this.my.getIy()] != 7) {
            ++this.meet_step;
         }
      } else {
         if (this.gr.monPro.length > 1 && this.gr.immueEnemy == 0) {
            this.my.frame_c = 0;
            this.my.state = 8;
         }

         this.notMeet(0, 0);
      }

   }

   private void canMove(int temp_xspeed, int temp_yspeed) {
      this.my.speed_x = (byte)temp_xspeed;
      this.my.speed_y = (byte)temp_yspeed;
      if (this.my.x + this.my.speed_x >= 0 && this.my.x + this.my.speed_x + 19 < this.cols * 20 && this.my.y + this.my.speed_y >= 0 && this.my.y + this.my.speed_y + 19 < this.rows * 20) {
         this.roadType = this.checkMap(this.my.x, this.my.y, this.my.speed_x, this.my.speed_y);
         if (0 == this.roadType) {
            this.initMoveMy();
         } else if (this.checkMoveOff(this.checkNpcT)) {
            this.initMoveMy();
         } else if (this.checkNpcT != -1 && !this.isCheckNpcOff()) {
            Ms.i().keyRelease();
            this.setMoveStop_m();
         } else {
            this.roadType = this.isMapChenk(this.roadType);
            if (this.roadType == -1) {
               this.moveOff((byte)3, (byte)1, -this.my.speed);
            } else if (this.roadType == -2) {
               this.moveOff((byte)4, (byte)2, this.my.speed);
            } else {
               this.checkAnole();
            }
         }
      } else {
         this.setMoveStop_m();
         this.checkType = this.checkIfOther(this.door, this.my.x, this.my.y);
         if (this.checkType != -1 && this.door[this.checkType][5] == 0 && this.map_key == this.door[this.checkType][2]) {
            this.my.state = 5;
            this.get_meet = this.checkType;
         }
      }

   }

   private boolean isCheckNpcOff() {
      if (this.checkNpcT < 0) {
         return false;
      } else {
         byte w;
         if (this.my.speed_x != 0) {
            Ms.i();
            w = (byte)(20 - Ms.abs(this.my.y - this.npc[0][this.checkNpcT].y));
            if (w > 0 && w < 11) {
               return true;
            }
         } else if (this.my.speed_y != 0) {
            Ms.i();
            w = (byte)(20 - Ms.abs(this.my.x - this.npc[0][this.checkNpcT].x));
            if (w > 0 && w < 11) {
               return true;
            }
         }

         return false;
      }
   }

   private void moveOff(byte dirx, byte diry, int speed) {
      if (this.my.speed_x == 0) {
         this.my.dir = dirx;
      } else if (this.my.speed_y == 0) {
         this.my.dir = diry;
      }

      this.my.speed_x = (byte)(this.my.speed_x != 0 ? 0 : speed);
      this.my.speed_y = (byte)(this.my.speed_y != 0 ? 0 : speed);
      this.checkMoveOff(this.checkNpcT);
      this.initMoveMy();
   }

   private boolean checkMoveOff(int npcid) {
      byte oxl;
      byte oxr;
      byte oyl;
      byte oyr;
      if (npcid > -1) {
         oxl = (byte)(this.npc[0][npcid].x + 20 - this.my.x);
         oxr = (byte)(this.npc[0][npcid].x - this.my.x - 20);
         oyl = (byte)(this.npc[0][npcid].y + 20 - this.my.y);
         oyr = (byte)(this.npc[0][npcid].y - this.my.y - 20);
         if (this.my.speed_x < 0 && oxl != 0) {
            this.my.speed_x = oxl < this.my.speed_x ? this.my.speed_x : oxl;
            return true;
         }

         if (this.my.speed_x > 0 && oxr != 0) {
            this.my.speed_x = oxr < this.my.speed_x ? oxr : this.my.speed_x;
            return true;
         }

         if (this.my.speed_y < 0 && oyl != 0) {
            this.my.speed_y = oyl < this.my.speed_y ? this.my.speed_y : oyl;
            return true;
         }

         if (this.my.speed_y > 0 && oyr != 0) {
            this.my.speed_y = oyr < this.my.speed_y ? oyr : this.my.speed_y;
            return true;
         }
      } else {
         oxl = this.my.getIx_off();
         oxr = (byte)(20 - this.my.getIx_off());
         oyl = this.my.getIy_off();
         oyr = (byte)(20 - this.my.getIy_off());
         if (this.my.speed_x != 0 && oxl != 0) {
            if (this.my.speed_x < 0) {
               this.my.speed_x = (byte)(-oxl < this.my.speed_x ? this.my.speed_x : -oxl);
            } else {
               this.my.speed_x = oxr < this.my.speed_x ? oxr : this.my.speed_x;
            }

            return true;
         }

         if (this.my.speed_y != 0 && oyl != 0) {
            if (this.my.speed_y < 0) {
               this.my.speed_y = (byte)(-oyl < this.my.speed_y ? this.my.speed_y : -oyl);
            } else {
               this.my.speed_y = oyr < this.my.speed_y ? oyr : this.my.speed_y;
            }

            return true;
         }
      }

      return false;
   }

   private void checkAnole() {
      this.checkType = this.checkWorld(this.my.x, this.my.y, this.my.speed_x, this.my.speed_y, true);
      if (this.checkType == 2) {
         if (this.isCheckAnole(4)) {
            return;
         }
      } else if (this.checkType == 3) {
         if (this.isCheckAnole(0)) {
            return;
         }
      } else if (this.checkType == 4) {
         if (this.isCheckAnole(2)) {
            return;
         }
      } else if (this.checkType == 0 && this.anole_type == 4) {
         if (this.anole_type != 3 && this.isAnoleType(3)) {
            this.anole_type = 3;
         } else {
            this.anole_type = -1;
         }

         this.setAnole();
         this.initMoveMy();
         return;
      }

      Ms.i().keyRelease();
      this.setMoveStop_m();
   }

   private boolean isCheckAnole(int id) {
      if (this.anole_type != id && this.isAnoleType(id)) {
         this.anole_type = (byte)id;
         this.setAnole();
         return true;
      } else {
         if (!this.isAnoleType(id)) {
            this.gr.say("没有" + this.gr.monsterT[id] + "坐骑" + "，不能通过该地形", -1);
         }

         return false;
      }
   }

   private void setMoveStop_m() {
      this.my.speed_x = 0;
      this.my.speed_y = 0;
      this.my.frame_c = -1;
      this.my.setActionNo(false);
   }

   private byte isMapChenk(byte checkType) {
      byte checkLeft = 0;
      byte checkRight = 0;
      byte ix = (byte)(this.my.x / 20);
      byte iy = (byte)(this.my.y / 20);
      if (this.my.speed_x == 0) {
         checkLeft = this.checkMap((ix - (this.my.x % 20 != 0 ? 0 : 1)) * 20, (iy + (this.my.speed_y >= 0 ? 1 : -1)) * 20, 0, 0);
         checkRight = this.checkMap((ix + 1) * 20, (iy + (this.my.speed_y >= 0 ? 1 : -1)) * 20, 0, 0);
      } else if (this.my.speed_y == 0) {
         checkLeft = this.checkMap((ix + (this.my.speed_x >= 0 ? 1 : -1)) * 20, (iy - (this.my.y % 20 != 0 ? 0 : 1)) * 20, 0, 0);
         checkRight = this.checkMap((ix + (this.my.speed_x >= 0 ? 1 : -1)) * 20, (iy + 1) * 20, 0, 0);
      }

      if (checkLeft == 1 && checkRight == 1) {
         return checkType;
      } else if (checkLeft == 0 && this.checkMap((this.my.speed_x == 0 ? ix - (this.my.x % 20 != 0 ? 0 : 1) : ix) * 20, (this.my.speed_y == 0 ? iy - (this.my.y % 20 != 0 ? 0 : 1) : iy) * 20, 0, 0) == 0) {
         return -1;
      } else {
         return checkRight == 0 && this.checkMap((this.my.speed_x == 0 ? ix + (this.my.x % 20 != 0 ? 0 : 1) : ix) * 20, (this.my.speed_y == 0 ? iy + (this.my.y % 20 != 0 ? 0 : 1) : iy) * 20, 0, 0) == 0 ? -2 : checkType;
      }
   }

   private void dialog(String npcName) {
      byte x = 10;
      short y = 272;
      byte height = 51;
      byte line_c = 0;
      Ui.i().drawKuang(4, 320 - height - 4, 236, height + 4);
      if (null != npcName) {
         int var10001 = this.faceDir ? 190 : 50;
         int var10002 = 320 - height - 4;
         Graphics var10003 = g;
         Graphics var10004 = g;
         this.drawNpcFace(var10001, var10002, 1 | 32);
         Ui.i().drawKuang(0, y - height + 20, Ms.i().getStringWidth(npcName) + 16, 28);
         Ui.i().drawString(npcName, 8, y - height + 2 + 20, 0, 9, 1);
      }

      while(line_c < 2 && this.dialog_no + line_c < this.dialog.length) {
         Ui.i().drawStringColor(this.dialog[this.dialog_no + line_c].toString(), x, y - 2 + line_c * 20, -1, 1);
         ++line_c;
      }

   }

   public void setNull() {
      this.npc[0] = null;
      this.item = (byte[][])null;
      this.door = (byte[][])null;
      this.event = (byte[][])null;
      this.mapImg = null;
      this.moduleData = (short[][])null;
   }

   private byte[] getNpcData() {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      this.getNpcData(byteArray, this.npc[0]);
      this.getNpcData(byteArray, this.npc[1]);
      this.getNpcData(byteArray, this.npc[2]);
      this.getItemData(byteArray, this.item);
      this.getItemData(byteArray, this.door);
      return byteArray.toByteArray();
   }

   private void getNpcData(ByteArrayOutputStream byteArray, Npc[] npc) {
      try {
         for(int i = 0; i < npc.length; ++i) {
            if (npc[i].other[9] == 1) {
               byteArray.write(npc[i].other);
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void getItemData(ByteArrayOutputStream byteArray, byte[][] data) {
      try {
         byteArray.write(data.length);

         for(int i = 0; i < data.length; ++i) {
            byteArray.write(data[i].length);
            byteArray.write(data[i]);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void drawMiniMap() {
      short x = true;
      short fh = 24;
      Ui.i().fillRectB();
      Ui.i().drawK2(1, 2, 238, 318, 0);
      Ui.i().drawK1(6, 28, 228, 320 - fh * 3 - 15, 1);
      Ui.i().drawK1(6, 314 - fh * 2, 228, fh * 2, 2);
      Ui.i().drawYesNo(false, true);
      Ui.i().drawString("游戏地图", 120, 4, 17, 1, 2);
      if (this.showArea == -1) {
         Ui var10000 = Ui.i();
         int var10003 = 316 - fh * 2;
         Graphics var10004 = g;
         Graphics var10005 = g;
         var10000.drawString("该区域没有地图！", 120, var10003, 16 | 1, 3, 0);
      } else {
         this.gr.showStringM("人物当前位置：" + this.gr.getNameCity(this.myMiniMap), 120, 316 - fh * 2, 10, 3);
         g.setClip(6, 28, 228, 305 - fh * 3);
         byte ww = 13;
         byte hh = 8;
         byte j = 0;
         int t = 120 - this.mapdataMap[this.selectMap][0] * ww - (this.miniMapMode ? this.mapdataMap[this.selectMap][6] * ww / 2 : 25);
         this.mapoffx = Ms.i().mathSpeedN(this.mapoffx, t, 10, false);
         t = this.littleMapClipY / 2 - this.mapdataMap[this.selectMap][1] * hh - (this.miniMapMode ? this.mapdataMap[this.selectMap][7] * hh / 2 : 15);

         for(this.mapoffy = Ms.i().mathSpeedN(this.mapoffy, t, 10, false); j < this.mapdataArea[this.showArea].length; ++j) {
            byte tmap = this.mapdataArea[this.showArea][j];
            if (tmap >= 0 && this.mapdataMap[tmap].length > 0 && (this.miniMapMode || tmap != this.myMiniMap)) {
               this.drawMapRect(ww, hh, tmap, false);
            }
         }

         if (!this.miniMapMode) {
            this.drawMapRect(ww, hh, this.myMiniMap, false);
         }

         this.drawMapRect(ww, hh, this.selectMap, true);
         g.setClip(0, 0, 240, 320);
      }
   }

   private void drawMapRect(byte ww, byte hh, int tmap, boolean select) {
      int width = 6 * ww;
      int height = 6 * hh;
      if (this.miniMapMode) {
         width = this.mapdataMap[tmap][6] * ww;
         height = this.mapdataMap[tmap][7] * hh;
      }

      if (select) {
         Ui.i().drawRectZ(12352252, this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 5);
      } else {
         if (tmap == this.myMiniMap) {
            Ui.i().drawK1(this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 4);
         } else {
            Ui.i().drawK0(this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 2);
         }

         if (this.miniMapMode) {
            this.gr.showStringM(this.gr.getNameCity(tmap), this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1) + 19, this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1) - 20, 4, tmap == this.myMiniMap ? 0 : 3);
         } else {
            this.gr.showStringM(this.gr.getNameCity(tmap), this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1), this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1) - 20, 4, tmap == this.myMiniMap ? 0 : 3);
         }

         if (tmap == this.myMiniMap) {
            Ui.i().drawUi(67, this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1), this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1), 3, 0);
         }
      }

   }

   private void keyMiniMap() {
      Ms.i().keyRelease();
      if (!Ms.i().key_S1_Num5()) {
         if (!Ms.i().key_S2() && !Ms.i().key_Num0()) {
            if ((Ms.i().key_Left_Right() || Ms.i().key_Up_Down()) && this.mapdataMap[this.selectMap].length >= 6) {
               byte[] var10000 = this.mapdataMap[this.selectMap];
               Ms.i();
               Ms.i();
               if (var10000[Ms.abs(Ms.key) + 1] != -1) {
                  byte[] var10001 = this.mapdataMap[this.selectMap];
                  Ms.i();
                  Ms.i();
                  this.selectMap = var10001[Ms.abs(Ms.key) + 1];
               }
            }
         } else {
            this.my.state = 0;
            this.mapdataArea = (byte[][])null;
            this.mapdataMap = (byte[][])null;
         }
      }

   }

   public void goMiniMap() {
      Ms.i().keyRelease();
      byte[] data = Ms.i().getStream("data/map.d", -1);
      Ms.i();
      Ms.skip = 0;
      this.mapdataArea = Ms.i().create2Array(data);
      this.mapdataMap = Ms.i().create2Array(data);
      byte[] data = null;
      this.my.state = 17;
      this.showArea = -1;
      this.myMiniMap = -1;
      this.selectMap = 0;

      for(byte i = 0; i < this.mapdataArea.length; ++i) {
         for(byte k = 0; k < this.mapdataArea[i].length; ++k) {
            if (this.mapNo == this.mapdataArea[i][k]) {
               this.selectMap = this.mapNo;
               if (this.mapdataMap[this.selectMap].length > 0) {
                  this.myMiniMap = this.selectMap;
                  this.showArea = i;
                  this.miniMapMode = this.mapdataArea[this.showArea][0] > -1;
                  return;
               }
            }
         }
      }

   }

   private void drawMySave() {
      this.gr.showString("是否存盘?", 260, 0);
      Ui.i().drawYesNo(true, true);
   }

   private void keyMySave() {
      if (Ms.i().key_S1()) {
         this.my.state = 0;
         this.eventGoing = 2;
      } else if (Ms.i().key_S2()) {
         this.my.state = 0;
         this.eventGoing = 3;
      }

   }

   private void initMissionList() {
      byte[] info = Ms.i().rmsOptions(7, (byte[])null, 1);
      if (info[0] == -1) {
         this.bMission = null;
      } else {
         this.bMission = info;
      }

      byte[] info = null;
      if (null == this.bMission) {
         this.bMission = new byte[20];
      }

   }

   public void goMission(int type, boolean b) {
      if (this.my.state == 18 || this.my.state == 0) {
         Ms.i().keyRelease();
         this.bPause = b;
         GameRun var10000 = this.gr;
         GameRun.run_state = -10;
         this.my.state = 18;
         this.gr.cur_a = (byte)type;
         this.gr.select[0][0] = this.gr.select[0][1] = 0;
         Ms.i();
         Ms.skip = 0;
         byte[] data = Ms.i().getStream("data/mission" + type + ".t", -1);
         this.sMission = Ms.i().createString2Array(data);
         this.gr.line_max = 6;
         this.gr.about_a = null;
         if (type == 1) {
            byte i = 0;
            byte j = 0;

            byte[] tMis;
            for(tMis = new byte[50]; i < this.sMission.length; ++i) {
               if (this.isMission(i)) {
                  tMis[j++] = i;
               }
            }

            this.mDirect = null;
            this.mDirect = new byte[j];
            System.arraycopy(tMis, 0, this.mDirect, 0, this.mDirect.length);
            if (this.mDirect.length > 0) {
               this.gr.setStringB(this.sMission[this.mDirect[this.gr.select[0][0]]][1].toString(), 202, 0);
            }

            Object var7 = null;
         } else {
            this.gr.select[0][0] = this.bMission[14];
            if (this.bMission[14] >= this.gr.line_max) {
               this.gr.select[0][1] = (byte)(this.gr.select[0][0] - this.gr.line_max + 1);
            } else {
               this.gr.select[0][1] = 0;
            }

            this.gr.setStringB(this.sMission[this.gr.select[0][0]][1].toString(), 202, 0);
         }

      }
   }

   public void setMission(int id, boolean bb) {
      byte type = (byte)(id / 8);
      int id = (byte)(id % 8);
      byte[] var10000;
      if (bb) {
         var10000 = this.bMission;
         var10000[type] = (byte)(var10000[type] | 1 << id);
      } else if ((this.bMission[type] & 1 << id) != 0) {
         var10000 = this.bMission;
         var10000[type] = (byte)(var10000[type] ^ 1 << id);
         ++this.bMission[15];
      }

   }

   private boolean isMission(int id) {
      byte type = (byte)(id / 8);
      int id = (byte)(id % 8);
      return (this.bMission[type] & 1 << id) != 0;
   }

   private void drawMission() {
      Ui.i().fillRectB();
      Ui.i().drawK2(1, 6, 238, 314, 0);
      short ly = 35;
      Ui.i().drawK1(8, ly, 214, this.gr.line_max * 24 + 10, 1);
      Ui.i().drawK1(8, ly + 18 + this.gr.line_max * 24, 224, 100, 2);
      this.drawMisstionList(8, ly + 6, 214, 24, this.gr.line_max, this.gr.select[0], this.gr.cur_a == 0 ? this.bMission[14] + 1 : this.mDirect.length);

      for(byte d = 0; null != this.gr.about_a && d < this.gr.about_a.length; ++d) {
         Ui.i().drawStringColor(this.gr.about_a[d].toString(), 19, ly + 20 + this.gr.line_max * 24 + d * 20, 3, 0);
      }

      Ui.i().drawK1(this.gr.cur_a == 0 ? 25 : 140, 9, 76, 24, 4);
      Ui.i().drawString("主线任务", 63, 9, 17, this.gr.cur_a == 0 ? 0 : 1, 0);
      Ui.i().drawString("分支任务", 177, 9, 17, this.gr.cur_a == 1 ? 0 : 1, 0);
      Ui.i().drawTriangle(120, 18, 114, true, true);
      Ui.i().drawString((this.gr.cur_a == 0 ? "主线完成度：" : "分支完成度：") + Ms.i().getPrecision(this.bMission[this.gr.cur_a == 0 ? 14 : 15] * 1000 / (this.gr.cur_a == 0 ? this.sMission.length - 1 : this.sMission.length)) + "%", 120, 317, 33, 0, 1);
      Ui.i().drawYesNo(false, true);
   }

   private void drawMisstionList(int x, int y, int w, int sh, int show_num, byte[] sel, int length) {
      byte i = sel[1];
      Ui.i().drawListKY(show_num, x, y, w, 6, sh, -1, sel[0] - sel[1], 4, 2);

      for(; i < sel[1] + show_num; ++i) {
         if (i < length) {
            String s;
            if (this.gr.cur_a == 0) {
               s = i + 1 + "、" + this.sMission[i][0].toString();
            } else {
               s = i + 1 + "、" + this.sMission[this.mDirect[i]][0].toString();
            }

            Ui.i().drawString(s, x + 8, y + (i - sel[1]) * (sh - 1), 0, i < this.bMission[14] ? -1 : (sel[0] == i ? 0 : 3), 0);
            if (this.gr.cur_a == 0 && i != length - 1) {
               Ui.i().drawString("完成", x + w - 8, y + (i - sel[1]) * (sh - 1), 24, i < this.bMission[14] ? -1 : (sel[0] == i ? 0 : 3), 0);
            }
         }
      }

      Ui.i().sliding(x + w + 4, y, show_num * sh, sel[0], length, true);
   }

   private void keyMission() {
      if (!Ms.i().key_delay()) {
         GameRun var10000;
         if (!Ms.i().key_Left_Right() && (!Ms.i().key_Num1() || this.gr.cur_a != 1) && (!Ms.i().key_Num3() || this.gr.cur_a != 0)) {
            if (Ms.i().key_Up_Down()) {
               byte length;
               if (this.gr.cur_a == 0) {
                  length = (byte)(this.bMission[14] + 1 > this.sMission.length ? this.sMission.length : this.bMission[14] + 1);
               } else {
                  length = (byte)this.mDirect.length;
               }

               Ms.i().selectS(this.gr.select[0], 0, length, this.gr.line_max);
               if (this.gr.cur_a == 0) {
                  this.gr.setStringB(this.sMission[this.gr.select[0][0]][1].toString(), 202, 0);
               } else if (this.mDirect.length > 0) {
                  this.gr.setStringB(this.sMission[this.mDirect[this.gr.select[0][0]]][1].toString(), 202, 0);
               } else {
                  this.gr.about_a = null;
               }
            } else if (Ms.i().key_S2() || Ms.i().key_Num1() || Ms.i().key_Num3()) {
               this.my.state = 0;
               if (this.bPause) {
                  this.gr.doPaint(0);
                  var10000 = this.gr;
                  GameRun.run_state = 97;
               } else {
                  var10000 = this.gr;
                  GameRun.run_state = -10;
               }

               this.mDirect = null;
               this.sMission = (StringBuffer[][])null;
               this.gr.about_a = null;
               Ms.i().keyRelease();
            }
         } else {
            var10000 = this.gr;
            var10000.cur_a = (byte)(var10000.cur_a ^ 1);
            this.goMission(this.gr.cur_a, this.bPause);
         }

      }
   }

   private void createMap() {
      this.loadMapModuleAndImage();
      this.loadMapData(this.mapNo);
   }

   private void loadMapModuleAndImage() {
      Ms.i();
      Ms.skip = 0;
      byte[] data = Ms.i().getStream("data/map/area.dat", -1);
      byte[] areaMap = Ms.i().createArray(data);
      byte[][] areaPic = Ms.i().create2Array(data);
      byte[] data = null;
      Object areaMap;
      if (null != this.mapImg && areaMap[this.lastMap] == areaMap[this.mapNo]) {
         areaMap = null;
         areaPic = (byte[][])null;
      } else {
         Ms.i();
         Ms.skip = 0;
         this.moduleData = Ms.i().createShort2Array(Ms.i().getStream("data/map/" + areaMap[this.mapNo] + ".data", -1), 1);
         this.mapImg = null;
         this.mapImg = new Image[areaPic[areaMap[this.mapNo]].length];

         for(byte i = 0; i < areaPic[areaMap[this.mapNo]].length; ++i) {
            this.mapImg[i] = Ms.i().createImage("data/map/" + areaPic[areaMap[this.mapNo]][i]);
         }

         areaMap = null;
         areaPic = (byte[][])null;
      }
   }

   private void loadMapData(int id) {
      int elemNum = false;
      int dataType = false;
      byte[] buffShort = new byte[2];
      byte[] buffInt = new byte[4];
      byte layerType = false;
      this.bottomData = null;
      this.frontData = null;
      this.worldData = (short[][])null;
      short arrayIndex = false;

      try {
         DataInputStream dis = new DataInputStream(this.getClass().getResourceAsStream("/data/map/" + id + ".mid"));
         dis.skip(2L);
         dis.read(buffInt);
         Ms.i();
         this.bgColor = (int)Ms.getNum(buffInt);
         int dataType = dis.readByte();
         byte[] buff = new byte[dataType];
         dis.read(buff);
         dis.read(buff);
         Ms.i();
         this.cols = (short)((byte)((int)Ms.getNum(buff)));
         dis.read(buff);
         Ms.i();
         this.rows = (short)((byte)((int)Ms.getNum(buff)));
         this.worldData = new short[this.cols][this.rows];

         for(byte i = 0; i < 3; ++i) {
            byte layerType = dis.readByte();
            if (layerType != -1) {
               dis.read(buffInt);
               Ms.i();
               int elemNum = (int)Ms.getNum(buffInt);
               elemNum *= 3;
               switch (layerType) {
                  case 1:
                     this.bottomData = new short[elemNum];
                  case 2:
                  default:
                     break;
                  case 3:
                     this.frontData = new short[elemNum];
               }

               short arrayIndex = 0;

               while(arrayIndex < elemNum) {
                  dis.read(buff);
                  Ms.i();
                  int x = (short)((int)Ms.getNum(buff));
                  dis.read(buff);
                  long var10000 = (long)(this.rows - 1);
                  Ms.i();
                  int y = (short)((int)(var10000 - Ms.getNum(buff)));
                  dis.read(buffShort);
                  Ms.i();
                  int z = (short)((int)Ms.getNum(buffShort));
                  switch (layerType) {
                     case 1:
                        this.bottomData[arrayIndex++] = (short)x;
                        this.bottomData[arrayIndex++] = (short)y;
                        this.bottomData[arrayIndex++] = (short)z;
                        break;
                     case 2:
                        arrayIndex = (short)(arrayIndex + 3);
                        this.worldData[x][y] = (short)(z >> 3);
                        break;
                     case 3:
                        this.frontData[arrayIndex++] = (short)x;
                        this.frontData[arrayIndex++] = (short)y;
                        this.frontData[arrayIndex++] = (short)z;
                  }
               }
            }
         }

         dis.close();
         byte[] buff = null;
         byte[] buffShort = null;
         byte[] buffInt = null;
         dis = null;
      } catch (Exception var14) {
      }

   }

   void drawMap(Graphics g) {
      int tempLeftCol = this.leftCol;
      int tempTopRow = this.topRow;
      if (this.map_x >= 0) {
         this.leftCol = 0;
         this.map_left = this.map_x;
      } else {
         this.leftCol = (short)(-this.map_x / 20);
         this.map_left = this.map_x % 20;
      }

      if (this.map_y >= 0) {
         this.topRow = 0;
         this.map_top = this.map_y;
      } else {
         this.topRow = (short)(-this.map_y / 20);
         this.map_top = this.map_y % 20;
      }

      int left = this.leftCol * 20;
      int top = this.topRow * 20;
      if (this.firstDrawMap == 0) {
         this.firstDrawMap = 1;
         this.bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, 260, 340, true);
      } else if (this.leftCol != tempLeftCol || this.topRow != tempTopRow) {
         g.setClip(0, 0, 260, 340);
         g.copyArea(0, 0, 260, 340, (tempLeftCol - this.leftCol) * 20, (tempTopRow - this.topRow) * 20, 20);
         if (this.leftCol < tempLeftCol) {
            this.bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, (tempLeftCol - this.leftCol) * 20, 340, true);
         } else if (this.leftCol > tempLeftCol) {
            this.bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 260 + (tempLeftCol - this.leftCol) * 20, 0, 260, 340, true);
         }

         if (this.topRow < tempTopRow) {
            this.bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, 260, (tempTopRow - this.topRow) * 20, true);
         } else if (this.topRow > tempTopRow) {
            this.bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 340 + (tempTopRow - this.topRow) * 20, 260, 340, true);
         }
      }

      GameRun var10001 = this.gr;
      Map.g.drawImage(GameRun.scrT, this.map_left, this.map_top, 20);
   }

   public void bkgroundToPaint(short[] bkgroundData, Image[] img, int screen_x, int screen_y, short[][] moduleData, Graphics gtem, int x1, int y1, int x2, int y2, boolean fillBlack) {
      if (null != bkgroundData) {
         screen_y -= 20;
         if (fillBlack) {
            gtem.setClip(x1, y1, x2 - x1, y2 - y1);
            gtem.setColor(this.bgColor);
            gtem.fillRect(x1, y1, x2 - x1, y2 - y1);
         }

         int bkdata_id = true;
         int bkdata_trans = false;
         int bkPic_w = false;
         int bkPic_h = false;

         for(int i = 0; i < bkgroundData.length; i += 3) {
            if (bkgroundData[i + 2] >= 0) {
               int bkdata_id = bkgroundData[i + 2] >> 3;
               int bkdata_trans = bkgroundData[i + 2] & 3;
               int realX = bkgroundData[i] * 20 - screen_x;
               int realY = bkgroundData[i + 1] * 20 - screen_y;
               short bkPic_w;
               short bkPic_h;
               if (bkdata_trans % 2 == 1) {
                  bkPic_w = moduleData[bkdata_id][4];
                  bkPic_h = moduleData[bkdata_id][3];
               } else {
                  bkPic_w = moduleData[bkdata_id][3];
                  bkPic_h = moduleData[bkdata_id][4];
               }

               if (realX + bkPic_w >= x1 && realX <= x2 && realY >= y1 && realY - bkPic_h <= y2) {
                  Ui.i().drawRegion(img[moduleData[bkdata_id][0]], moduleData[bkdata_id][1], moduleData[bkdata_id][2], moduleData[bkdata_id][3], moduleData[bkdata_id][4], realX, realY, 4 | 32, bkgroundData[i + 2] & 7, gtem);
               }
            }
         }

      }
   }

   private void keySelectMenu() {
      if (!Ms.i().key_delay()) {
         if (Ms.i().key_S1_Num5()) {
            this.my.state = 13;
         } else if (Ms.i().key_Up_Down()) {
            this.gr.cur_a = Ms.i().select(this.gr.cur_a, 0, this.gr.action_str.length - 1);
         }

      }
   }

   private void drawNpcFace(int x, int y, int dir) {
      if (null != this.imgFace) {
         Ui.i().drawImage(this.imgFace, x + this.face_c, y, dir);
      }

      this.face_c = Ms.i().mathSpeedN(this.face_c, 0, 20, false);
   }

   public void initNpcFaceOne(String name) {
      if (null != name) {
         byte id = -1;
         this.faceDir = true;
         if (name.equals("塞其")) {
            id = 0;
            this.faceDir = false;
         } else if (!name.equals("安琪儿") && !name.equals("蓝发女孩")) {
            if (name.equals("幽暗鬼王")) {
               id = 2;
            } else if (name.equals("杰奇校长")) {
               id = 3;
            } else if (!name.equals("普罗") && !name.equals("普罗校长")) {
               if (name.equals("神秘东方男子")) {
                  id = 5;
               } else if (name.equals("南宫云")) {
                  id = 6;
               }
            } else {
               id = 4;
            }
         } else {
            id = 1;
         }

         if (id > -1) {
            if (null == this.imgFace || this.faceLast != id) {
               this.face_c = (short)(this.faceDir ? 50 : -50);
               this.faceLast = id;
               this.imgFace = null;
               this.imgFace = Ms.i().createImage("data/face/" + id);
            }
         } else {
            this.nullNpcFace();
         }

      }
   }

   public void nullNpcFace() {
      this.imgFace = null;
   }

   public void initBrowImage() {
      if (null == this.brow) {
         this.brow = Ms.i().createSprite("data/face/brow", true);
      }

   }

   private void drawCheck5(int id) {
      if (this.checkNpcT >= 0 && this.my.state == 0 && this.npc[0][this.checkNpcT].other[6] == 0) {
         Ui.i().drawFrameOne(this.brow, this.brow.action(id, this.action_5, 0), this.map_x + this.npc[0][this.checkNpcT].x + 10, this.map_y + this.npc[0][this.checkNpcT].y - 16 - 10, 0);
         if (++this.time_5 > this.brow.action(id, this.action_5, 1)) {
            if (++this.action_5 >= this.brow.aLength(id)) {
               this.action_5 = 0;
            }

            this.time_5 = 0;
         }

      }
   }

   private void drawBrow() {
      for(byte i = 0; i < this.npcList.length; ++i) {
         byte id = this.npcList[i];
         if (id >= -1) {
            if (id == -1) {
               cur_npc = this.my;
            } else {
               cur_npc = this.npc[0][id];
            }

            if ((cur_npc.other[4] == 1 || cur_npc.other[4] == 2) && (cur_npc.brow_type > -1 || cur_npc.other[6] != 0) && (id <= -1 || this.isNpcSrc(cur_npc.x, cur_npc.y))) {
               if (cur_npc.other[6] != 0) {
                  cur_npc.brow_type = cur_npc.other[6];
               }

               if (cur_npc.brow_action >= this.brow.aLength(cur_npc.brow_type)) {
                  if (cur_npc.other[6] == 0) {
                     cur_npc.brow_type = -1;
                     continue;
                  }

                  cur_npc.brow_action = 0;
               }

               Ui.i().drawFrameOne(this.brow, this.brow.action(cur_npc.brow_type, cur_npc.brow_action, 0), this.map_x + cur_npc.x + 14, this.map_y + cur_npc.y - 15 - 10, 0);
               if (++cur_npc.brow_time > this.brow.action(cur_npc.brow_type, cur_npc.brow_action, 1)) {
                  ++cur_npc.brow_action;
                  cur_npc.brow_time = 0;
               }
            }
         }
      }

   }

   public void initNpcAniOne(String sid, int id, int type) {
      npcSp[type][id] = Ms.i().createSprite("data/npc" + type + "/" + sid, type == 2 ? this.gr.isNpc2ImageType(Integer.parseInt(sid)) : true);
   }

   public void initNpcAni() {
      byte k = 0;
      byte[][] em = new byte[][]{new byte[100], new byte[5], new byte[60]};
      this.gr.initNidusMap(0);

      byte i;
      byte j;
      for(i = (byte)(this.npc.length - 1); i > -1; --i) {
         for(j = (byte)(this.npc[i].length - 1); j > -1; --j) {
            em[this.npc[i][j].other[3] - 1][this.npc[i][j].other[2]] = 1;
            if (i == 0) {
               this.npcList[j + 1] = j;
               if (this.npc[i][j].other[8] == 1) {
                  this.gr.nidusMap[k++] = j;
               }
            }
         }
      }

      this.gr.initNidusMap(1);
      this.npcList[0] = -1;

      for(i = (byte)(this.item.length + this.npc[0].length); i > this.npc[0].length; --i) {
         this.npcList[i] = (byte)(-2 - i + this.npc[0].length + 1);
      }

      this.initNpcAni_1(100, 0, em[0]);
      this.initNpcAni_1(5, 1, em[1]);
      this.initNpcAni_1(60, 2, em[2]);
      em = (byte[][])null;

      for(j = (byte)(this.npc[0].length - 1); j > -1; --j) {
         this.npc[0][j].setNpcNum(npcSp[this.npc[0][j].other[3] - 1][this.npc[0][j].other[2]].aLength());
      }

   }

   private void initNpcAni_1(int length, int type, byte[] em) {
      for(int i = length - 1; i > 0; --i) {
         if (em[i] != 0) {
            if (null == npcSp[type][i]) {
               this.initNpcAniOne("" + i, i, type);
            }
         } else {
            npcSp[type][i] = null;
         }
      }

   }

   private boolean isNpcSrc(int ax, int ay) {
      return this.map_x + ax >= -80 && this.map_x + ax <= 300 && this.map_y + ay >= -80 && this.map_y + ay <= 380;
   }

   private void drawNpcAni(Npc[] npc) {
      if (null != npc) {
         for(byte i = 0; i < npc.length; ++i) {
            if ((npc[i].other[4] == 1 || npc[i].other[4] == 2) && this.isNpcSrc(npc[i].x, npc[i].y)) {
               this.drawNpcAniOne(npc, i);
            }

            this.runNpcAniOne(npc, i);
         }

      }
   }

   private void drawNMAni(Npc[] npc) {
      if (null != npc) {
         for(byte i = 0; i < this.npcList.length; ++i) {
            byte id = this.npcList[i];
            if (id == -1) {
               if (this.my.other[4] == 1 || this.my.other[4] == 2) {
                  this.drawMyAni(this.my, 0, this.map_x + this.my.x + 10, this.map_y + this.my.y + 19, this.my.other[7]);
               }
            } else if (id < -1) {
               Ms.i();
               id = (byte)(Ms.abs(id) - 2);
               if (this.isNpcSrc(this.item[id][0] * 20, this.item[id][1] * 20)) {
                  Ui.i().drawUi(this.item[id][2] == 2 ? 55 : 54, this.map_x + this.item[id][0] * 20 + 10, this.map_y + this.item[id][1] * 20 + 20, 33, 0);
               }
            } else {
               if ((npc[id].other[4] == 1 || npc[id].other[4] == 2) && this.isNpcSrc(npc[id].x, npc[id].y)) {
                  if (npc[id].other[8] != 3 && !this.npcNameData[id].equals("路人")) {
                     g.drawImage(this.imgShadow, this.map_x + npc[id].x + 10, this.map_y + npc[id].y + 20, 33);
                  }

                  this.drawNpcAniOne(npc, id);
               }

               this.runNpcAniOne(npc, id);
            }
         }

      }
   }

   private void drawNpcAniOne(Npc[] npc, int i) {
      byte type = (byte)(npc[i].other[3] - 1);
      byte id = npc[i].other[2];
      byte action_id = npc[i].other[7];
      byte now_action = npc[i].now_action;
      if (action_id < 0) {
         npc[i].dir = 4;
         action_id = (byte)(-action_id);
      }

      if (npc[i].now_action >= npcSp[type][id].aLength(action_id)) {
         now_action = npc[i].now_action = 0;
         if (npc[i].other[8] == -4) {
            npc[i].other[8] = 0;
            npc[i].other[4] = 0;
            return;
         }

         if (!npc[i].bdir && this.setlastA(npc, i)) {
            this.drawNpcAniOne(npc, i);
            return;
         }
      }

      Ui.i().drawFrameOne(npcSp[type][id], npcSp[type][id].action(action_id, now_action, 0), this.map_x + npc[i].x + 10, this.map_y + npc[i].y + 19, npc[i].dir == 4 ? 1 : 0);
   }

   private void runNpcAniOne(Npc[] npc, int i) {
      byte type = (byte)(npc[i].other[3] - 1);
      byte id = npc[i].other[2];
      Ms.i();
      byte action_id = (byte)Ms.abs(npc[i].other[7]);
      if (npc[i].now_action >= npcSp[type][id].aLength(action_id)) {
         npc[i].now_action = 0;
         this.setlastA(npc, i);
      }

      if (++npc[i].now_time >= npcSp[type][id].action(action_id, npc[i].now_action, 1)) {
         ++npc[i].now_action;
         npc[i].now_time = 0;
      }

   }

   private boolean setlastA(Npc[] npc, int i) {
      if (npc[i].lastAction != 127) {
         npc[i].other[7] = npc[i].lastAction;
         npc[i].lastAction = 127;
         return true;
      } else {
         return false;
      }
   }

   public void drawMyAni(Npc my, int my_id, int x, int y, int action_id) {
      byte id = (byte)my_id;
      if (my.now_action >= npcSp[1][id].aLength(action_id)) {
         my.now_action = 0;
      }

      if (this.anole_type != 2 && this.anole_type != 4) {
         Ui.i().drawImage(this.imgShadow, x, y, 33);
      }

      Ui.i().drawFrameOne(npcSp[1][id], npcSp[1][id].action(action_id, my.now_action, 0), x, y, my.dir == 4 ? 1 : 0);
      if (++my.now_time > npcSp[1][id].action(action_id, my.now_action, 1)) {
         ++my.now_action;
         my.now_time = 0;
      }

   }

   public void insertNpc() {
      if (null != this.npc) {
         byte temp = false;

         for(byte i = 1; i < this.npcList.length; ++i) {
            for(byte j = i; j > 0; --j) {
               short y0 = this.getNpcListY(j);
               short y1 = this.getNpcListY(j - 1);
               if (y0 > y1) {
                  break;
               }

               if (y0 != y1 || y0 == y1 && this.npcList[j - 1] == -1) {
                  byte temp = this.npcList[j];
                  this.npcList[j] = this.npcList[j - 1];
                  this.npcList[j - 1] = temp;
               }
            }
         }

      }
   }

   private short getNpcListY(int j) {
      short y;
      if (this.npcList[j] == -1) {
         y = (short)(this.my.y + this.dir_select[this.my.dir][1] * this.my.speed);
      } else if (this.npcList[j] < -1) {
         byte[][] var10000 = this.item;
         Ms.i();
         y = (short)(var10000[Ms.abs(this.npcList[j]) - 2][1] * 20);
      } else {
         y = this.npc[0][this.npcList[j]].y;
      }

      return y;
   }

   private void initMoveMy() {
      this.my.frame_c = this.my.frame_num;
      this.my.setActionNo(true);
      if (this.my.state == 0 && this.gr.monPro.length > 0) {
         this.meetGrass();
      }

   }

   private void runMove() {
      if (this.my.ix == -1 && this.my.iy == -1) {
         this.runBoat();
      } else {
         this.runSeek(this.my, false);
      }

      this.oneMove(this.my, -1);

      for(byte j = 0; j < 3; j = (byte)(j + 2)) {
         for(byte i = 0; i < this.npc[j].length; ++i) {
            if (this.npc[j][i].other[4] == 1 || this.npc[j][i].other[4] == 2) {
               if (this.npc[j][i].other[8] >= 5) {
                  this.runAutoMoveNpc(this.npc[j][i], i);
               } else if (this.npc[j][i].ix != -1 || this.npc[j][i].iy != -1) {
                  this.runSeek(this.npc[j][i], true);
               }

               this.oneMove(this.npc[j][i], i);
            }
         }
      }

      this.insertNpc();
   }

   private void runAutoMoveNpc(Npc npc, int i) {
      if (this.my.state == 1 && this.get_meet == i) {
         byte[] var10000 = npc.other;
         Ms.i();
         var10000[7] = (byte)(Ms.abs(npc.other[7]) / 3 * 3 + (this.my.dir < 4 ? this.my.dir - 1 : this.my.dir - 2));
         if (this.my.dir == 3) {
            npc.other[7] = (byte)(-npc.other[7]);
         } else {
            npc.dir = 3;
         }
      } else if (this.initAuto(npc, 10)) {
         if (npc.b_auto) {
            this.runAutoX(npc, this.gr.mapMove[(npc.other[8] - 5) * 2]);
         } else {
            this.runAutoY(npc, this.gr.mapMove[(npc.other[8] - 5) * 2 + 1]);
         }
      }

   }

   private boolean initAuto(Npc npc, int sleep) {
      if (npc.timeMove == 0) {
         if (npc.frame_c != -1) {
            return false;
         }

         Ms.i();
         npc.b_auto = Ms.getRandom(100) < 50;
         Ms.i();
         if (Ms.getRandom(100) < 50) {
            Ms.i();
            npc.timeMove = (byte)(-(Ms.getRandom(sleep) + 20));
            npc.setActionNo(false);
            return false;
         }
      } else if (npc.timeMove < 0) {
         ++npc.timeMove;
         return false;
      }

      return true;
   }

   private boolean isAuto_canMove(Npc _npc, int sx, int sy) {
      return this.checkWorld(_npc.x, _npc.y, sx, sy, false) != 1 && !Ms.i().isRect(_npc.x + sx, _npc.y + sy, 19, 19, this.my.x, this.my.y, 19, 19);
   }

   private void runAutoY(Npc _npc, int w) {
      if (_npc.timeMove == 0 && _npc.frame_c == -1) {
         Ms.i();
         _npc.dir = (byte)(Ms.getRandom(100) < 50 ? 1 : 2);
         short t;
         if (_npc.dir == 1) {
            t = (short)(_npc.y - _npc.other[1] * 20);
         } else {
            t = (short)((w + _npc.other[1]) * 20 - _npc.y);
         }

         if (t <= 20) {
            _npc.timeMove = (byte)(t / _npc.speed);
         } else {
            Ms.i();
            _npc.timeMove = (byte)(Ms.getRandom((t - 20) / _npc.speed + 1) + 20 / _npc.speed);
         }
      } else if (_npc.timeMove > 0 && _npc.frame_c < 1) {
         if (this.isAuto_canMove(_npc, 0, _npc.dir == 1 ? -_npc.speed : _npc.speed)) {
            _npc.setSpeedXY(0, _npc.dir == 1 ? -_npc.speed : _npc.speed);
            --_npc.timeMove;
            _npc.frame_c = 1;
            _npc.setActionNo(true);
         } else {
            _npc.timeMove = -20;
            _npc.frame_c = -1;
            _npc.setActionNo(false);
         }
      }

   }

   private void runAutoX(Npc _npc, int w) {
      if (_npc.timeMove == 0) {
         Ms.i();
         _npc.dir = (byte)(Ms.getRandom(100) < 50 ? 3 : 4);
         short t;
         if (_npc.dir == 3) {
            t = (short)(_npc.x - _npc.other[0] * 20);
         } else {
            t = (short)((w + _npc.other[0]) * 20 - _npc.x);
         }

         if (t <= 20) {
            _npc.timeMove = (byte)(t / _npc.speed);
         } else {
            Ms.i();
            _npc.timeMove = (byte)(Ms.getRandom((t - 20) / _npc.speed + 1) + 20 / _npc.speed);
         }
      } else if (_npc.timeMove > 0 && _npc.frame_c < 1) {
         if (this.isAuto_canMove(_npc, _npc.dir == 3 ? -_npc.speed : _npc.speed, 0)) {
            _npc.setSpeedXY(_npc.dir == 3 ? -_npc.speed : _npc.speed, 0);
            --_npc.timeMove;
            _npc.frame_c = 1;
            _npc.setActionNo(true);
         } else {
            _npc.timeMove = -20;
            _npc.frame_c = -1;
            _npc.setActionNo(false);
         }
      }

   }

   private void runSeek(Npc npc_, boolean b_who) {
      boolean bb = npc_.b_check;
      short x_ = (short)(npc_.ix * 20);
      short y_ = (short)(npc_.iy * 20);
      if (npc_.x == x_ && npc_.y == y_) {
         if (npc_.frame_c == -1) {
            npc_.ix = npc_.iy = -1;
            if (b_who) {
               npc_.setIxIy_npc();
            } else {
               npc_.speed = this.getMySpeed();
            }

            if (npc_.bdir) {
               npc_.other[7] = npc_.lastAction;
               npc_.dir = (byte)(npc_.lastAction < 0 ? 4 : 3);
               npc_.setLastAction(false, 127);
            }
         }
      } else if (npc_.frame_c < 1) {
         if (bb) {
            bb = npc_.x != x_;
         } else {
            bb = npc_.y == y_;
         }

         if (bb) {
            if (npc_.x > x_ && npc_.x - npc_.speed < x_ || npc_.x < x_ && npc_.x + npc_.speed > x_) {
               npc_.setSpeedXY(x_ - npc_.x, 0);
            } else {
               npc_.setSpeedXY(npc_.x > x_ ? -npc_.speed : npc_.speed, 0);
            }

            if (!npc_.bdir) {
               npc_.dir = (byte)(npc_.x > x_ ? 3 : 4);
            }
         } else {
            if ((npc_.y <= y_ || npc_.y - npc_.speed >= y_) && (npc_.y >= y_ || npc_.y + npc_.speed <= y_)) {
               npc_.setSpeedXY(0, npc_.y > y_ ? -npc_.speed : npc_.speed);
            } else {
               npc_.setSpeedXY(0, y_ - npc_.y);
            }

            if (!npc_.bdir) {
               npc_.dir = (byte)(npc_.y > y_ ? 1 : 2);
            }
         }

         npc_.frame_c = npc_.frame_num;
         npc_.setActionNo(true);
      }

   }

   private void drawSrcEffect() {
      if (this.srcFlash_c > 0) {
         byte var10002 = this.srcFlash_c;
         this.srcFlash_c = (byte)(var10002 - 1);
         if (var10002 % 2 == 0) {
            Ui.i().fillRect(16777215, 0, 0, 240, 320);
         }
      }

      if (this.bSrc) {
         g.setColor(0);
         g.fillRect(0, this.black_width - 176 - this.bSrc_c, 240, 176);
         g.fillRect(0, 320 - this.black_width + this.bSrc_c, 240, 176);
      }

      if (this.my.state == 22) {
         this.drawBlackSRC(g, this.eventGoing == 2 ? 320 - this.gr.about_d.length * 24 >> 1 : 45, this.eventGoing == 2);
      }

   }

   public boolean initSrcEffect(int mode) {
      this.my.state = 10;
      switch (mode) {
         case 0:
         case 1:
         case 2:
            this.bSrc_c = 0;
            this.black_width = (short)(mode == 1 ? 176 : 0);
            this.bSrc = mode == 1;
            return true;
         case 3:
         case 4:
         case 5:
         case 6:
            this.bSrc = true;
            if (this.bSrc_c == 0) {
               if (mode != 6 && mode != 5) {
                  this.black_width = this.bSrc_c = (short)(mode == 4 ? 176 : 40);
               } else {
                  this.bSrc_c = -40;
                  this.black_width = (short)(mode == 5 ? 40 : 0);
               }
            }

            this.bSrc_c = Ms.i().mathSpeedN(this.bSrc_c, 0, 12, false);
            if (this.bSrc_c == 0) {
               this.bSrc = mode != 6;
               return true;
            }
         default:
            return false;
      }
   }

   private void initCloud() {
      if (this.mapNo != 24 && this.mapNo != 25 && this.mapNo != 26 && this.mapNo != 49 && this.mapNo != 50) {
         this.cloud = (short[][])null;
         this.imgCloud = null;
      } else {
         if (null == this.imgCloud) {
            this.imgCloud = Ms.i().createImage("data/cloud");
            this.cloud = (short[][])null;
            this.cloud = new short[4][3];
         }

         for(byte i = 0; i < this.cloud.length; ++i) {
            this.addCloud(i);
         }
      }

   }

   private void drawCloud() {
      if (null != this.imgCloud) {
         byte i;
         for(i = 0; i < this.cloud.length; ++i) {
            Ui.i().drawImage(this.imgCloud, this.map_x + this.cloud[i][0], this.map_y + this.cloud[i][1], 0);
         }

         for(i = 0; i < this.cloud.length; ++i) {
            if (!this.isNpcSrc(this.cloud[i][0], this.cloud[i][1])) {
               this.addCloud(i);
            } else {
               short[] var10000 = this.cloud[i];
               var10000[0] -= this.cloud[i][2];
            }
         }

      }
   }

   private void addCloud(int i) {
      short[] var10000 = this.cloud[i];
      int var10002 = this.leftCol * 20 + 240;
      Ms.i();
      var10000[0] = (short)(var10002 + Ms.getRandom(120));
      var10000 = this.cloud[i];
      var10002 = this.topRow * 20 + 20 + 2;
      Ms.i();
      var10000[1] = (short)(var10002 + i * (Ms.getRandom(25) + 55));
      var10000 = this.cloud[i];
      Ms.i();
      var10000[2] = (short)(Ms.getRandom(3) + 1);
   }

   private void drawMapRect(Graphics g) {
      byte i = 0;
      g.setColor(0);

      for(; i < this.gr.mapRect.length; ++i) {
         if (!Ms.i().isRect(this.my.x, this.my.y, 19, 19, this.gr.mapRect[i][0] * 20, this.gr.mapRect[i][1] * 20, (this.gr.mapRect[i][2] - this.gr.mapRect[i][0] + 1) * 20, (this.gr.mapRect[i][3] - this.gr.mapRect[i][1] + 1) * 20)) {
            for(byte j = 4; j < this.gr.mapRect[i].length; j = (byte)(j + 4)) {
               g.fillRect(this.map_x + this.gr.mapRect[i][j] * 20, this.map_y + this.gr.mapRect[i][j + 1] * 20, (this.gr.mapRect[i][j + 2] - this.gr.mapRect[i][j] + 1) * 20, (this.gr.mapRect[i][j + 3] - this.gr.mapRect[i][j + 1] + 1) * 20);
            }
         }
      }

   }

   private void initBoat(int _select) {
      this.my.state = 9;
      this.anole_type = 5;
      this.setAnole();
      this.boatSelect = (byte)_select;
      this.gr.cur_a = 0;
      this.my.setIXY(this.boatCourse[this.boatSelect][this.gr.cur_a], this.boatCourse[this.boatSelect][this.gr.cur_a + 1]);
   }

   private void runBoat() {
      if (this.boatSelect != -1 && this.my.ix == -1 && this.my.iy == -1) {
         this.my.state = 9;
         GameRun var10000 = this.gr;
         if ((var10000.cur_a = (byte)(var10000.cur_a + 2)) >= this.boatCourse[this.boatSelect].length) {
            this.boatSelect = -1;
            this.my.state = 0;
            this.anole_type = -1;
            this.setAnole();
         } else {
            this.checkType = this.checkIfOther(this.door, this.my.x, this.my.y);
            if (this.checkType != -1) {
               this.my.state = 5;
               this.get_meet = this.checkType;
            }

            this.my.setIXY(this.boatCourse[this.boatSelect][this.gr.cur_a], this.boatCourse[this.boatSelect][this.gr.cur_a + 1]);
         }

      }
   }

   public void setMapMusic(boolean bb) {
      Sound.i().setMusicId(this.gr.musicNo[this.mapNo]);
      Sound.i().setMusic(bb);
   }
}
