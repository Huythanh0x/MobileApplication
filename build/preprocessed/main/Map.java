/*      */ package main;
/*      */ 
/*      */ import dm.Ms;
/*      */ import dm.Npc;
/*      */ import dm.Sound;
/*      */ import dm.Sprite;
/*      */ import dm.Ui;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import javax.microedition.lcdui.Graphics;
/*      */ import javax.microedition.lcdui.Image;
/*      */ import minigame.Mg;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Map
/*      */   implements Constants_H, Key_H
/*      */ {
/*      */   private static Graphics g;
/*      */   private int map_x;
/*      */   private int map_y;
/*      */   private short cols;
/*      */   private short rows;
/*      */   private short leftCol;
/*      */   private short topRow;
/*      */   private short mapLeft_NOmove;
/*      */   private short mapRight_NOmove;
/*      */   private short mapUp_NOmove;
/*      */   private short mapDown_NOmove;
/*   37 */   public Npc[][] npc = new Npc[3][];
/*      */ 
/*      */   
/*      */   private static Npc cur_npc;
/*      */   
/*      */   private byte map_key;
/*      */   
/*   44 */   private byte srcNpcNo = -1; private byte get_meet; private byte lastMap; private byte lastExit; private byte sleep_count = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private short meet_step;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   56 */   private short step_MEET = 4;
/*      */   
/*      */   private byte[] mapChange;
/*      */   private byte[][] door;
/*      */   private byte[][] item;
/*   61 */   private byte event_state = 0; private byte now_eV1; private byte now_eV2; private byte[][] mapTemp = new byte[70][];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[][] event;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int eventCount;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   76 */   public byte anole_temp = -1; public byte anole_type = -1; public byte mapNo = 0; public byte inShop = 0; public byte notMeet = 0; public byte dialog_no = -1;
/*      */ 
/*      */   
/*      */   public short[] mapInfo;
/*      */ 
/*      */   
/*      */   public short[][] event_now_list;
/*      */ 
/*      */   
/*      */   public short[] event_now;
/*      */   
/*      */   public StringBuffer[] dialog;
/*      */   
/*      */   public GameRun gr;
/*      */   
/*      */   private String[] npcStringData;
/*      */   
/*      */   private String[] npcNameData;
/*      */ 
/*      */   
/*      */   public void setTempNull() {
/*   97 */     this.mapTemp = (byte[][])null;
/*   98 */     this.mapTemp = new byte[70][];
/*      */   }
/*  100 */   private byte bStep = -1; private byte[] npcPos; private byte go; boolean bExitBoss; byte mapNotemp; public byte fmap; public String fString; public byte sIfElse; public byte sEvent_eV1; public byte sEvent_eV2; public byte eventGoing; private String npcName; public boolean gmErr; private byte xxx; public byte black_0; public byte black_1; private byte[][] dir_select; private byte anoleSel; public byte npcDirTalk; public boolean bDirTalk; byte checkType; byte checkNpcT; private byte roadType; private boolean miniMapMode; private byte[][] mapdataMap; private byte[][] mapdataArea; private byte showArea; private byte myMiniMap; private byte selectMap; private short mapoffx; private short mapoffy; private short littleMapClipY; private boolean bPause; private StringBuffer[][] sMission; public byte[] bMission; public byte[] mDirect; short[][] moduleData; Image[] mapImg; int bgColor; short[] bottomData; short[] frontData; short[][] worldData; public byte firstDrawMap; int map_left; int map_top; int map_right; int map_bottom; int rightCol; int bottomRow; private Image imgFace; private boolean faceDir; private byte faceLast; private short face_c; Sprite brow; private byte action_5; private byte time_5; public Image imgShadow;
/*      */   public void notMeet(int kind, int id) {
/*  102 */     if (kind == 0) {
/*  103 */       this.notMeet = 0;
/*  104 */       Ms.i(); this.step_MEET = (short)(Ms.getRandom(30) + 1);
/*  105 */       this.meet_step = (short)((this.step_MEET - 15) * (this.step_MEET - 15));
/*  106 */       Ms.i(); if (this.meet_step > Ms.getRandom(50)) {
/*  107 */         Ms.i(); Ms.i(); this.step_MEET = (short)(byte)(17 + Ms.getRandom(9) - Ms.getRandom(7));
/*  108 */       }  this.step_MEET = (short)(this.step_MEET << 2);
/*  109 */       this.meet_step = 0;
/*  110 */       this.bStep = -1;
/*  111 */     } else if (kind == 1) {
/*  112 */       this.notMeet = 1;
/*  113 */       this.step_MEET = (short)(200 * (id - 14 + 1));
/*  114 */       this.step_MEET = (short)(this.step_MEET << 2);
/*  115 */       this.meet_step = 0;
/*  116 */       this.bStep = 100;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void createNpcString() {
/*  125 */     this.npcNameData = Ms.i().loadText(initData(this.gr.createData(1), this.mapNo));
/*  126 */     this.npcStringData = Ms.i().loadText(initData(this.gr.createData(2), this.mapNo));
/*      */   }
/*      */   public void loadDate_Rms() {
/*  129 */     byte[] info = Ms.i().rmsOptions(2, null, 1);
/*      */     
/*  131 */     if (info[0] == -1)
/*      */     
/*  133 */     { byte[] one = { 58, 9, 16, 2, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, -1 };
/*      */ 
/*      */ 
/*      */       
/*  137 */       info = one;
/*  138 */       Ms.i().rmsOptions(2, one, 2);
/*  139 */       one = null;
/*  140 */       this.event_now_list = new short[70][];
/*  141 */       Ms.i().rmsOptions(11, Ms.i().getEventNowData(this.event_now_list), 2); }
/*  142 */     else { this.event_now_list = Ms.i().readEventNowData(); }
/*  143 */      this.mapNo = info[0];
/*  144 */     this.lastMap = info[5];
/*  145 */     this.lastExit = info[6];
/*  146 */     this.my.setXY(info[1], info[2], 0, 0);
/*  147 */     this.my.dir = info[3];
/*      */     
/*  149 */     this.my.setActionNo(false);
/*  150 */     this.gr.money = Ms.i().getInt(info, 7);
/*  151 */     this.gr.coin = Ms.i().getInt(info, 11);
/*  152 */     this.anole_type = info[15];
/*  153 */     info = null;
/*      */   }
/*      */   public void map_saveGame() {
/*  156 */     byte[] info = new byte[16];
/*  157 */     info[0] = this.mapNo;
/*  158 */     info[5] = this.lastMap;
/*  159 */     info[6] = this.lastExit;
/*  160 */     info[1] = (byte)(this.my.x / 20);
/*  161 */     info[2] = (byte)(this.my.y / 20);
/*  162 */     info[3] = this.my.dir;
/*      */     
/*  164 */     Ms.i().putInt(this.gr.money, info, 7);
/*  165 */     Ms.i().putInt(this.gr.coin, info, 11);
/*  166 */     info[15] = this.anole_type;
/*      */     
/*  168 */     Ms.i().rmsOptions(2, info, 2);
/*  169 */     info = null;
/*      */   }
/*      */   public void save() {
/*  172 */     map_saveGame();
/*  173 */     saveNpc();
/*  174 */     for (int i = 0; i < this.mapInfo.length / 2; i++) {
/*  175 */       if (this.mapChange[i] == 1) {
/*  176 */         this.mapChange[i] = 0;
/*  177 */         Ms.i().rmsOptions(13 + i, this.mapTemp[i], 2);
/*      */       } 
/*      */     } 
/*  180 */     Ms.i().rmsOptions(12, Ms.i().shortArrayToByteArray(this.mapInfo), 2);
/*  181 */     Ms.i().rmsOptions(11, Ms.i().getEventNowData(this.event_now_list), 2);
/*  182 */     Ms.i().rmsOptions(1, this.mapChange, 2);
/*  183 */     Ms.i().rmsOptions(7, this.bMission, 2);
/*  184 */     Ms.i().rmsOptions(5, this.gr.rmsSms, 2);
/*  185 */     Ms.i().rmsOptions(1, null, 4);
/*      */   }
/*      */   
/*      */   public byte[] initData(byte[] tempdata, int n) {
/*  189 */     int len = 1;
/*  190 */     for (int i = 0; i < n; i++) {
/*  191 */       int j = (tempdata[len++] & 0xFF) << 8 | tempdata[len++] & 0xFF;
/*  192 */       len += j;
/*      */     } 
/*  194 */     int temp = (tempdata[len++] & 0xFF) << 8 | tempdata[len++] & 0xFF;
/*  195 */     byte[] data = new byte[temp];
/*  196 */     System.arraycopy(tempdata, len, data, 0, data.length);
/*  197 */     return data;
/*      */   }
/*      */   
/*      */   public void createEvent() {
/*  201 */     byte[] data = Ms.i().getStream("event", this.mapNo);
/*  202 */     int start_pos = 0;
/*      */     
/*  204 */     if (data.length == 1) {
/*  205 */       this.eventCount = 0;
/*      */       return;
/*      */     } 
/*  208 */     this.eventCount = data[start_pos++];
/*  209 */     if (this.mapInfo[this.mapNo * 2] < 1) {
/*  210 */       this.event_now_list[this.mapNo] = null;
/*  211 */       this.event_now_list[this.mapNo] = new short[this.eventCount];
/*      */     } else {
/*  213 */       for (byte b = 0; b < this.eventCount; b = (byte)(b + 1)) {
/*  214 */         if (isMapEvent(this.mapNo, b)) this.event_now_list[this.mapNo][b] = 24224; 
/*      */       } 
/*  216 */     }  this.event_now = this.event_now_list[this.mapNo];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  224 */     this.event = new byte[this.eventCount][];
/*  225 */     short length_ = 0;
/*  226 */     for (byte i = 0; i < this.eventCount; i = (byte)(i + 1)) {
/*  227 */       length_ = (short)(data[start_pos++] << 8 | data[start_pos++] & 0xFF);
/*      */       
/*  229 */       this.event[i] = new byte[length_];
/*  230 */       System.arraycopy(data, start_pos, this.event[i], 0, (this.event[i]).length);
/*      */       
/*  232 */       start_pos += length_;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void init() {
/*  238 */     initBrowImage();
/*  239 */     configureNpc();
/*  240 */     if (null == npcSp[1][0]) {
/*  241 */       npcSp[1][0] = new Sprite();
/*      */       
/*  243 */       this.my = new Npc(8);
/*  244 */       this.my.other[4] = 1;
/*  245 */       setAnole();
/*      */     } 
/*      */     
/*  248 */     if (null == this.imgShadow) this.imgShadow = Ms.i().createImage("data/shadow"); 
/*      */   }
/*      */   
/*      */   private void configureNpc() {
/*  252 */     npcSp[0] = new Sprite[100];
/*  253 */     npcSp[1] = new Sprite[5];
/*  254 */     npcSp[2] = new Sprite[60];
/*      */   }
/*      */   
/*      */   public void initMap() {
/*  258 */     this.my.dir = 4;
/*  259 */     this.srcNpcNo = -1;
/*  260 */     this.gr.loadRmsSms();
/*      */     
/*  262 */     this.gr.loadRmsNidus();
/*      */     
/*  264 */     notMeet(0, 0);
/*  265 */     this.mapInfo = Ms.i().byteArrayToShortArray(Ms.i().rmsOptions(12, null, 1));
/*  266 */     this.mapChange = Ms.i().rmsOptions(1, null, 1);
/*      */ 
/*      */     
/*  269 */     for (short i = 0; i < this.mapChange.length; i = (short)(i + 1)) {
/*  270 */       if (this.mapChange[i] == 1)
/*      */       {
/*  272 */         this.mapTemp[i] = Ms.i().rmsOptions(13 + i, null, 1);
/*      */       }
/*      */     } 
/*  275 */     System.gc();
/*  276 */     this.gr.createOver = 33;
/*  277 */     createOther();
/*  278 */     this.gr.createOver = 35;
/*  279 */     createLayer();
/*  280 */     this.gr.createOver = 37;
/*      */     
/*  282 */     createEvent();
/*  283 */     this.gr.createOver = 39;
/*  284 */     createNpcString();
/*  285 */     this.gr.createOver = 40;
/*  286 */     Ms.i().rmsOptions(1, null, 4);
/*  287 */     this.gr.initMonPro(this.mapNo, true);
/*  288 */     initMissionList();
/*  289 */     initCloud();
/*      */   }
/*      */   
/*      */   public void createOther() {
/*  293 */     byte[] Datas = null;
/*  294 */     Ms.i(); Ms.skip = 0;
/*  295 */     Ms.i(); Ms.skip2 = 0;
/*  296 */     Datas = initData(this.gr.createData(0), this.mapNo);
/*  297 */     this.npcPos = createNpcPos(Datas, this.npcPos);
/*  298 */     if (this.mapInfo[this.mapNo * 2] > 0 && 
/*  299 */       this.mapTemp[this.mapNo] == null) {
/*  300 */       this.mapTemp[this.mapNo] = Ms.i().rmsOptions(13 + this.mapNo, null, 1);
/*      */     }
/*      */     
/*  303 */     this.npc[0] = createNpc(Datas, this.npc[0]);
/*  304 */     this.npc[1] = createNpc(Datas, this.npc[1]);
/*  305 */     this.npc[2] = createNpc(Datas, this.npc[2]);
/*  306 */     this.item = createItem(Datas, this.item);
/*  307 */     this.door = createItem(Datas, this.door);
/*  308 */     this.npcList = null;
/*  309 */     this.npcList = new byte[(this.npc[0]).length + 1 + this.item.length];
/*  310 */     Datas = null;
/*  311 */     initNpcAni();
/*      */   }
/*      */   
/*      */   Npc[] createNpc(byte[] data, Npc[] npc) {
/*  315 */     npc = null;
/*  316 */     Ms.i(); npc = new Npc[data[Ms.skip++]];
/*      */     int i;
/*  318 */     for (i = 0; i < npc.length; i++) {
/*  319 */       Ms.i(); npc[i] = new Npc(data[Ms.skip++]);
/*  320 */       for (int j = 0; j < (npc[i]).other.length; j++) {
/*  321 */         Ms.i(); (npc[i]).other[j] = data[Ms.skip++];
/*      */       } 
/*  323 */       npc[i].setXY_npc();
/*      */     } 
/*  325 */     if (this.mapInfo[this.mapNo * 2] > 0) {
/*  326 */       for (i = 0; i < npc.length; i++) {
/*  327 */         if ((npc[i]).other[9] == 1) {
/*  328 */           for (int j = 0; j < (npc[i]).other.length; j++) {
/*  329 */             Ms.i(); (npc[i]).other[j] = this.mapTemp[this.mapNo][Ms.skip2++];
/*      */           } 
/*  331 */           npc[i].setXY_npc();
/*      */         } 
/*      */       } 
/*      */     }
/*  335 */     return npc;
/*      */   }
/*      */   
/*      */   byte[][] createItem(byte[] data, byte[][] npc) {
/*  339 */     npc = (byte[][])null;
/*      */     
/*  341 */     if (this.mapInfo[this.mapNo * 2] > 0) {
/*  342 */       data = this.mapTemp[this.mapNo];
/*  343 */       Ms.i(); npc = new byte[data[Ms.skip2++]][];
/*  344 */       for (int i = 0; i < npc.length; i++) {
/*  345 */         Ms.i(); npc[i] = new byte[data[Ms.skip2++]];
/*  346 */         for (int j = 0; j < (npc[i]).length; j++) {
/*  347 */           Ms.i(); npc[i][j] = data[Ms.skip2++];
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  352 */       Ms.i(); npc = new byte[data[Ms.skip++]][];
/*  353 */       for (int i = 0; i < npc.length; i++) {
/*  354 */         Ms.i(); npc[i] = new byte[data[Ms.skip++]];
/*  355 */         for (int j = 0; j < (npc[i]).length; j++) {
/*  356 */           Ms.i(); npc[i][j] = data[Ms.skip++];
/*      */         } 
/*      */       } 
/*      */     } 
/*  360 */     return npc;
/*      */   }
/*      */   
/*      */   private byte getNpcLayer(int i) {
/*  364 */     return (byte)((this.npcPos[i] & 0xFF) >> 6); } private byte getNpcId(int i) {
/*  365 */     return (byte)(this.npcPos[i] & 0x3F);
/*      */   }
/*      */   
/*      */   public byte[] createNpcPos(byte[] data, byte[] npc) {
/*  369 */     npc = null;
/*  370 */     Ms.i(); npc = new byte[data[Ms.skip++]];
/*      */     
/*  372 */     for (int i = 0; i < npc.length; i++) {
/*  373 */       Ms.i(); npc[i] = data[Ms.skip++];
/*      */     } 
/*  375 */     return npc;
/*      */   }
/*      */   
/*      */   public void saveNpc() {
/*  379 */     this.mapTemp[this.mapNo] = null;
/*  380 */     this.mapTemp[this.mapNo] = getNpcData();
/*  381 */     this.mapInfo[this.mapNo * 2] = 1;
/*  382 */     this.mapChange[this.mapNo] = 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setMapData() {
/*  387 */     this.map_x = map_set(this.my.getIx(), this.cols, 12, 6, 240, 20);
/*  388 */     this.map_y = map_set(this.my.getIy(), this.rows, 16, 8, 320, 20);
/*      */     
/*  390 */     this.mapLeft_NOmove = 110;
/*  391 */     this.mapRight_NOmove = (short)(this.cols * 20 - 120 - 10);
/*      */     
/*  393 */     this.mapUp_NOmove = 150;
/*  394 */     this.mapDown_NOmove = (short)(this.rows * 20 - 160 - 10);
/*      */   }
/*      */   
/*      */   public void createLayer() {
/*  398 */     createMap();
/*      */     
/*  400 */     setMapData();
/*      */   }
/*      */ 
/*      */   
/*      */   public byte exitMap(byte[][] door) {
/*      */     byte go;
/*  406 */     if (door[this.get_meet][3] == -1) {
/*  407 */       go = this.mapNo;
/*  408 */       this.mapNo = this.lastMap;
/*  409 */       this.lastMap = go;
/*  410 */       this.lastExit = this.get_meet;
/*  411 */       go = -1;
/*      */     } else {
/*  413 */       go = this.mapNo;
/*  414 */       this.mapNo = door[this.get_meet][4];
/*  415 */       this.lastMap = go;
/*  416 */       this.lastExit = this.get_meet;
/*  417 */       go = door[this.get_meet][3];
/*      */     } 
/*  419 */     return go;
/*      */   }
/*      */ 
/*      */   
/*      */   public void saveMapBak() {
/*  424 */     saveNpc();
/*  425 */     this.mapChange[this.mapNo] = 1;
/*  426 */     Ms.i().rmsOptions(1, this.mapChange, 2); }
/*      */   public void goNextMap() { setfmap(); this.firstDrawMap = 0; this.gr.doPaint(2); saveMapBak(); byte exit_b = this.lastExit; if (this.go != -2) { this.lastMap = this.mapNo; this.mapNo = this.mapNotemp; this.mapNotemp = 0; this.lastExit = this.go; }  this.gr.doPaint(5); if (this.go == -2) this.go = exitMap(this.door);  this.gr.setNull(false); this.gr.doPaint(8); createOther(); this.gr.doPaint(10); if (this.go == -1) this.go = exit_b;  if (!this.bExitBoss) getInMap(this.go, this.door);  createEvent(); this.gr.doPaint(15); createNpcString(); createLayer(); this.gr.doPaint(20); if (!this.bExitBoss && (this.door[this.go][5] == 1 || this.door[this.go][5] == 2)) { this.my.frame_c = (byte)(20 / this.my.speed); this.my.speed_x = (byte)(this.dir_select[this.my.dir][0] * this.my.speed); this.my.speed_y = (byte)(this.dir_select[this.my.dir][1] * this.my.speed); this.my.setActionNo(true); } else { this.my.setActionNo(false); }  this.gr.b_c = 0; setMapMusic(false); this.go = -2; this.gr.initMonPro(this.mapNo, true); this.gr.cityName_c = 25; this.srcNpcNo = -1; Ms.i().rmsOptions(1, null, 4); initCloud(); this.bExitBoss = false; this.my.state = 9; if (this.boatSelect == -1) { this.my.state = 0; this.my.setIXY(-1, -1); this.my.speed = getMySpeed(); }  this.checkNpcT = -1; }
/*      */   private void exitBoss(int map_No, int zuobiao_x, int zuobiao_y, int mydir) { chuansong(map_No, 0); this.bExitBoss = true; this.my.setXY(zuobiao_x, zuobiao_y, 0, 0); this.my.dir = (byte)mydir; }
/*      */   public void chuansong(int mapno, int door) { this.my.state = 5; this.mapNotemp = (byte)mapno; this.go = (byte)door; setMoveStop_m(); }
/*  430 */   public void setfmap() { this.fmap = 0; } private void getInMap(byte exit_no, byte[][] door) { this.my.dir = (byte)((door[exit_no][2] - 1) / 2 * 2 + 1 + door[exit_no][2] % 2); this.my.setXY(door[exit_no][0], door[exit_no][1], 0, 0); } private int map_set(int _my_x, int cols, int xcells, int half_xcells, int show_width, int cell_width) { int map_x; if (cols <= xcells) { map_x = show_width - cell_width * cols >> 1; } else if (_my_x + 1 <= half_xcells) { map_x = 0; } else if (_my_x >= cols - half_xcells) { map_x = show_width - cols * cell_width; } else { map_x = show_width / 2 - _my_x * cell_width - cell_width / 2; }  return map_x; } private boolean setSIfElse(int _mode, boolean _bt, boolean _bmode) { if (_mode == 2) { this.sIfElse = (byte)(_bt ? 0 : 1); return true; }  if (_bmode && _bt) return true;  if (!_bmode && _mode == 1) return true;  return false; } private void ifEvent(int i, int next_id, int next_mode, int mode, boolean bt) { if (setSIfElse(mode, bt, true)) { this.event_now[i] = (short)(this.event_now[i] + next_id); nextEvent(next_mode); } else if (setSIfElse(mode, bt, false)) { setEventNow(i, false); }  } private boolean isMapEvent(int _mapNo, int _i) { return ((this.mapInfo[_mapNo * 2 + 1] & 1 << _i) != 0); } public void setRestEvent(int map, int id) { if (isMapEvent(map, id)) this.mapInfo[map * 2 + 1] = (short)(this.mapInfo[map * 2 + 1] ^ 1 << id);  if (map == this.mapNo) { this.event_now[id] = 0; } else if (null != this.event_now_list[map]) { this.event_now_list[map][id] = 0; }  } public void setOverEvent(int map, int id) { if (!isMapEvent(map, id)) this.mapInfo[map * 2 + 1] = (short)(this.mapInfo[map * 2 + 1] | 1 << id);  if (map == this.mapNo) { this.event_now[id] = 24224; } else if (null != this.event_now_list[map]) { this.event_now_list[map][id] = 24224; }  } private void setEventNow(int i, boolean bb) { if (bb) this.my.state = 0;  this.now_eV1 = 0; this.now_eV2 = 0; this.eventGoing = 0; this.event_now[i] = 0; } private void nextEvent(int mode) { if ((mode & 0x1000) != 0) { this.now_eV1 = 0; this.now_eV2 = 0; }  if ((mode & 0x100) != 0) this.eventGoing = 0;  if ((mode & 0x10) != 0) this.event_state = 1;  if ((mode & 0x1) != 0) this.my.state = 0;  } private void runEvent_getMon(int i) { this.gmErr = false; if (this.gr.getMonster(this.event[i][this.event_now[i] + 2], this.event[i][this.event_now[i] + 3], this.event[i][this.event_now[i] + 4], -1) == -1) { this.gr.say("宠物空间已满，无法获得新的宠物，请整理寄存所空出一个空间。", -1); this.gmErr = true; this.gr.mini_state = 6; this.gr.view_state = 1; this.gr.goVIEW_MONSTER(); } else { this.event_now[i] = (short)(this.event_now[i] + this.event[i][this.event_now[i] + 1] + 2); nextEvent(4368); }  } public void bkEvent_getMon() { GameRun.run_state = -10; } private void runEvent_brow(int i) { int event_temp = this.event[i][this.event_now[i] + 2] - 1; if (event_temp == -1) { cur_npc = this.my; } else { cur_npc = this.npc[getNpcLayer(event_temp)][getNpcId(event_temp)]; }  if (this.event[i][this.event_now[i] + 4] == 0) { cur_npc.brow_type = this.event[i][this.event_now[i] + 3]; cur_npc.brow_action = 0; cur_npc.brow_time = 0; } else if (this.event[i][this.event_now[i] + 4] == 1) { cur_npc.other[6] = this.event[i][this.event_now[i] + 3]; } else if (this.event[i][this.event_now[i] + 4] == 2) { cur_npc.brow_type = -1; cur_npc.other[6] = 0; }  this.event_now[i] = (short)(this.event_now[i] + 5); nextEvent(4112); } private void runEvent_srcMove(int i) { this.my.state = 10; int event_temp = this.event[i][this.event_now[i] + 2] - 1; int x = 0, y = 0; if (event_temp == -1) { x = this.my.getIx(); y = this.my.getIy(); this.srcNpcNo = -1; } else { this.now_eV1 = getNpcLayer(event_temp); this.now_eV2 = getNpcId(event_temp); this.srcNpcNo = (this.now_eV1 == 0) ? this.now_eV2 : 0; x = (this.npc[this.now_eV1][this.now_eV2]).other[0]; y = (this.npc[this.now_eV1][this.now_eV2]).other[1]; }  byte step = 10; x = map_set(x, this.cols, 12, 6, 240, 20) - this.map_x; y = map_set(y, this.rows, 16, 8, 320, 20) - this.map_y; Ms.i(); if (Ms.abs(y) < step) { Ms.i(); if (Ms.abs(x) < step) { this.event_now[i] = (short)(this.event_now[i] + 3); nextEvent(4112); return; }  }  Ms.i(); if (Ms.abs(x) >= step) { Ms.i(); this.map_x += x / Ms.abs(x) * step; }  Ms.i(); if (Ms.abs(y) >= step) { Ms.i(); this.map_y += y / Ms.abs(y) * step; }  } private void runEvent_goShop(int i) { int event_temp = this.event[i][this.event_now[i] + 3]; if (event_temp == 4) { if (this.inShop == 0 && this.my.state != 16) { this.my.state = 16; this.inShop = 1; this.gr.setAction_str(new String[] { "回复", "不回复" }); this.gr.popMenu = 0; this.gr.sell_money = 0; byte d = 0; for (; d < this.gr.myMon_length; d = (byte)(d + 1)) this.gr.sell_money += 40 + 20 * (this.gr.myMonsters[d]).monster[4];  } else if (this.my.state == 0) { if (this.inShop == 1 && this.gr.popMenu == 0) { this.inShop = 2; if (!this.gr.checkMonster() && this.gr.isMoney(this.gr.sell_money, true)) this.gr.healMonster(true);  } else if (this.gr.say_c == 0) { this.inShop = 0; this.event_now[i] = (short)(this.event_now[i] + 4); nextEvent(0); }  }  } else { if (event_temp < 3) { this.gr.goBUY_ITEM(this.event[i][this.event_now[i] + 2], event_temp); } else if (event_temp == 3) { this.gr.goVIEW_COMPUTER(0); } else if (event_temp == 5) { this.gr.goNidus(0); } else if (event_temp == 6) { this.gr.view_state = 4; this.gr.popMenu = -1; } else if (event_temp == 7) { this.gr.view_state = 1; }  this.event_now[i] = (short)(this.event_now[i] + 4); nextEvent(16); }  } private void runEvent_miniGame_myLevel(int i) { int event_temp = this.event[i][this.event_now[i] + 2]; if (event_temp > 1) { this.event_now[i] = (short)(this.event_now[i] + 3); nextEvent(4368); } else if (this.eventGoing < 2) { if (event_temp == 0) { if (this.gr.magic_id == -2) { if (this.gr.rmsOther[3] == 5) { this.gr.magic_id = -2; this.eventGoing = 2; this.gr.say("您当前的训练师等级已达到上限！", 0); } else { this.gr.sell_money = this.gr.isMyLevel(true); if (this.gr.sell_money == -1) { this.my.state = 10; this.gr.magic_id = 28; this.gr.magic_x = this.map_x + this.my.x + 10; this.gr.magic_y = this.map_y + this.my.y + 19; this.gr.setMagic(this.gr.magic_id / 5); this.gr.setMaxTakes(false); } else { this.eventGoing = 2; }  }  } else if (this.gr.magic_id == -1) { this.gr.magic_id = -2; this.eventGoing = 2; if (this.gr.rmsOther[3] > 3) { this.gr.say("恭喜您的训练师等级已提升为#7" + this.gr.rmsOther[3] + "#0，您商店能存放的宠物#7暴增到" + this.gr.max_monsters, -1); } else { this.gr.say("恭喜您的训练师等级已提升为#7" + this.gr.rmsOther[3] + "#0，您随身可携带宠物数量#7暴增到" + this.gr.max_takes + "#0，您商店能存放的宠物#7暴增到" + this.gr.max_monsters, -1); }  }  } else if (event_temp == 1) { if (this.eventGoing == 0 && this.gr.monInfoList[104] == 0) { this.gr.sell_money = 0; this.eventGoing = 3; this.gr.say("此次您没有捕获到全新的宠物，捕获并不是唯一途径，有些宠物需要通过进化才能获得哦。", -1); } else if (this.eventGoing == 0) { this.eventGoing = 3; this.gr.sell_money = this.gr.monInfoList[104]; this.gr.monInfoList[104] = 0; this.gr.say("此次您捕获了" + this.gr.sell_money + "个新宠物，这里是王国训练师机构奖励给您的" + this.gr.sell_money + "个徽章，希望再接再厉哦。", -1); this.gr.coin += this.gr.sell_money; }  }  } else if (this.gr.say_c == 0) { if (this.eventGoing == 2) { if (this.gr.rmsOther[3] == 5) { this.gr.say("您当前的训练师等级已达到上限！", 0); } else { this.gr.sell_money = (byte)(this.gr.isMyLevel(false) - this.gr.monInfoList[103]); if (this.gr.sell_money < 1) this.gr.sell_money = 0;  this.gr.say("当前训练师等级为#7" + this.gr.rmsOther[3] + "#0距离下次升级还需要捕捉#7" + this.gr.sell_money + "#0只不同的宠物，努力获得新宠吧。", -1); }  this.eventGoing = 4; } else if (this.eventGoing == 3 && this.gr.sell_money > 0) { this.gr.say("获得：徽章x" + this.gr.sell_money, 0); this.eventGoing = 4; } else { this.event_now[i] = (short)(this.event_now[i] + 3); nextEvent(4368); }  }  } private void runEvent_item_add_del(int i) { int event_temp = 0; if (this.eventGoing == 0) { this.now_eV1 = this.event[i][this.event_now[i] + 2]; this.now_eV2 = this.event[i][this.event_now[i] + 3]; if (this.event[i][this.event_now[i] + 3] >= 0) { event_temp = this.gr.addItem(this.now_eV1, this.now_eV2); } else { Ms.i(); if (this.gr.findItem(-2, this.now_eV1, true) >= Ms.abs(this.now_eV2)) { Ms.i(); this.gr.deleteItems(this.now_eV1, Ms.abs(this.now_eV2)); } else { event_temp = -1; this.gr.say("道具不足！", 0); }  }  if (event_temp != -1) { Ms.i(); this.gr.say(((this.now_eV2 >= 0) ? "获得：" : "失去：") + this.gr.getNameItem(this.now_eV1) + "x" + Ms.abs(this.now_eV2), 0); }  this.eventGoing = 1; } else if (this.gr.say_c == 0) { if (event_temp == -1) { setEventNow(i, true); } else { this.event_now[i] = (short)(this.event_now[i] + 4); nextEvent(4368); }  }  } private void runEvent_dialog(int i) { this.checkNpcT = -1; if (this.eventGoing == 0) { this.eventGoing = 1; if (this.anole_type == 1) setAnole();  int event_temp = this.event[i][this.event_now[i] + 2] - 1; if (event_temp == -1) { this.npcName = "塞其"; this.now_eV2 = 0; } else { this.now_eV1 = getNpcLayer(event_temp); this.now_eV2 = getNpcId(event_temp); this.npcName = this.npcNameData[this.now_eV2]; if (this.npcName.equals("路人")) this.npcName = null;  this.now_eV2 = (this.npc[this.now_eV1][this.now_eV2]).other[2]; }  initNpcFaceOne(this.npcName); this.dialog = Ms.i().groupString(Ms.i().getDialogs(this.event[i], this.event_now[i] + 5, this.event[i][this.event_now[i] + 4]), 220); this.dialog_no = 0; this.my.state = 1; this.bDirTalk = false; } else if (this.eventGoing == 2) { this.event_now[i] = (short)(this.event_now[i] + (this.event[i][this.event_now[i] + 4] << 1) + 5); nextEvent(4369); }  } private void runEvent_select(int i) { if (this.my.state != 12 && this.my.state != 13 && this.my.state != 14) { this.sEvent_eV2 = this.event[i][this.event_now[i] + 1]; this.sEvent_eV1 = 0; this.gr.action_str = null; this.gr.action_str = new String[this.sEvent_eV2]; for (byte j = 0; j < this.sEvent_eV2; j = (byte)(j + 1)) { this.gr.action_str[j] = Ms.i().getDialogs(this.event[i], this.event_now[i] + 2 + this.sEvent_eV1 + 1, this.event[i][this.event_now[i] + 2 + this.sEvent_eV1]); this.sEvent_eV1 = (byte)(this.sEvent_eV1 + (this.event[i][this.event_now[i] + 2 + this.sEvent_eV1] << 1) + 1); }  if (this.sIfElse != -1) { this.event_state = 1; this.gr.cur_a = this.sIfElse; this.my.state = 13; this.gr.action_str = null; this.sIfElse = -1; } else { this.gr.cur_a = 0; this.my.state = 12; }  } else if (this.my.state == 13) { this.sEvent_eV1 = (byte)(this.sEvent_eV1 + 2); byte a = this.event[i][this.event_now[i] + this.sEvent_eV1 + (this.gr.cur_a << 1)]; byte b = this.event[i][this.event_now[i] + this.sEvent_eV1 + (this.gr.cur_a << 1) + 1]; short d = (short)((a & 0xFF) << 8 | b & 0xFF); this.event_now[i] = (short)(this.event_now[i] + this.sEvent_eV1 + (this.sEvent_eV2 << 1) + d); nextEvent(17); } else if (this.my.state == 14) { setEventNow(i, true); }  } public void run_map() { if (this.my.state != 9 && this.my.state != 6) eventCheck();  if (this.my.state == 5 && this.my.frame_c < 1) { setMoveStop_m(); this.my.state = 6; GameRun.mc.initChangeMapS(); }  if (this.my.state == 6) { this.gr.createOver = 1; goNextMap(); return; }  if (this.my.state == 2 && this.eventGoing == 0) { if (((this.npc[0][this.get_meet]).other[4] == 1 || (this.npc[0][this.get_meet]).other[4] == 3) && (this.npc[0][this.get_meet]).other[5] > 0) { this.npcName = this.npcNameData[this.get_meet]; if (this.npcName.equals("路人")) this.npcName = null;  initNpcFaceOne(this.npcName); this.dialog = Ms.i().groupString(this.npcStringData[(this.npc[0][this.get_meet]).other[5] - 1], 220); this.dialog_no = 0; this.my.state = 1; } else { this.my.state = 0; }  } else if (this.my.state == 8 && this.my.frame_c == -1) { if (this.gr.immueEnemy == 0) { Ms.i(); this.gr.say(this.gr.battleSay[Ms.getRandom(3)], 0); GameRun.mc.repaint(); this.gr.battleType(4); this.gr.goBattlePVC(); } else { this.my.state = 0; }  }  runMove(); } public void paint_map(Graphics g) { this; Map.g = g; if (this.my.state == 6) { this.gr.drawChangeMap(false, this.gr.b_c, 30, 282, 180); return; }  if (this.my.state == 17) { drawMiniMap(); return; }  if (this.my.state == 18) { drawMission(); return; }  Ui.i().fillRect(this.bgColor, 0, 0, 240, 320); setMapOffer(); if (this.xxx > 0) { this.xxx = (byte)(this.xxx - 1); g.translate(this.xxx % 2, this.xxx % 2); }  drawMap(GameRun.offG); drawNpcAni(this.npc[2]); drawNMAni(this.npc[0]); bkgroundToPaint(this.frontData, this.mapImg, -this.map_x, -this.map_y, this.moduleData, g, 0, 0, 240, 320, false); drawNpcAni(this.npc[1]); drawMapRect(g); drawStep(g, 223, 0, 16); drawBrow(); drawCheck5(4); drawCloud(); if (this.gr.magic_id > -1) if (this.gr.drawMagicC((this.gr.magic_id / 5 > 5) ? 5 : (this.gr.magic_id / 5), (this.gr.magic_id / 5 > 5) ? 5 : (this.gr.magic_id / 5), (this.gr.magic_id / 5 >= 5) ? (this.gr.magic_id - 25) : (this.gr.magic_id % 5), this.gr.magic_x, this.gr.magic_y + 16, 0)) { if (this.my.state == 21) { this.gr.magic_id = -2; setAnole(); } else { this.gr.magic_id = -1; }  this.my.state = 0; }   g.translate(-g.getTranslateX(), -g.getTranslateY()); this.gr.drawPauseIco(this.my.state); if (this.my.state == 19) { drawMySave(); return; }  if (this.gr.rmsOther[2] > 0) Ui.i().drawString("9:骑乘", 120, 312, 0x1 | 0x20, 0, 2);  this.gr.drawCityName(); drawSrcEffect(); if (this.gr.view_state == 4) { byte[] id = { 4, 25, 56 }; Ui.i().drawKuang(0, 260, 240, 60); this.gr.showStringM(this.gr.getNameMonsterInfo(id[this.gr.cur_a]), 120, 272, 10, 3); id = null; } else if (this.my.state == 16) { Ui.i().drawKuang(0, 240, 240, 80); this.gr.showStringM("是否回复所有宠物？", 120, 244, 10, 3); this.gr.drawSelectMenu((Object[])this.gr.action_str, 82, 140, 76, -1, 0, this.gr.popMenu); this.gr.showStringM("所需金额：" + this.gr.sell_money, 120, 264, 10, 3); this.gr.drawMoney(120, 300, 3, false); }  if (this.my.state == 23) { Ui.i().drawKuang(0, 280, 240, 40); Ui.i().drawString("是否保存进度？", 120, 284, 17, 3, 0); Ui.i().drawYesNo(true, true); } else if (this.my.state == 12) { this.gr.drawSelectMenu((Object[])this.gr.action_str, 63, 160 - 20 * (this.gr.action_str.length >> 1), 114, 0, 0, this.gr.cur_a); }  if (this.dialog_no > -1) dialog(this.npcName);  this.gr.map_flashString(); if (this.my.state == 20) drawAnoleSel();  } private void setMapOffer() { if (this.map_y + this.rows * 20 < 320 && this.rows > 16) { this.map_y = 320 - this.rows * 20; } else if (this.map_y > 0 && this.rows > 16) { this.map_y = 0; } else if (this.map_x > 0 && this.cols > 12) { this.map_x = 0; } else if (this.map_x + this.cols * 20 < 240 && this.cols > 12) { this.map_x = 240 - this.cols * 20; }  } private void drawStep(Graphics g, int x, int y, int h) { if (this.bStep > 0) { g.setColor(3947323); g.drawRect(x + 1, y, h - 2, h); g.drawRect(x, y + 1, h, h - 2); g.setColor(13421772); g.drawRect(x + 2, y + 1, h - 4, h - 2); g.drawRect(x + 1, y + 2, h - 2, h - 4); Ui.i().fillRect(16777215, x + 2, y + 2, h - 3, h - 3); Ui.i().drawUi(67, x + (h >> 1), y + (h >> 1), 0x2 | 0x1, 0); if (this.bStep % 12 < 6) { Ui.i().drawLine(15597568, x + h - 1, y, x, y + h - 1); Ui.i().drawLine(0, x + h, y, x, y + h); Ui.i().drawLine(15597568, x + h, y + 1, x + 1, y + h); }  if ((this.bStep = (byte)(this.bStep - 1)) < 2) this.bStep = 100;  }  } public void drawBlackSRC(Graphics g, int y, boolean mode) { g.setColor(16777215); byte d = 0, offy = (byte)((this.black_0 - 5 > 0) ? (this.black_0 - 5) : 0); for (; mode ? (d < this.gr.about_d.length) : (d <= this.black_0 && d < this.gr.about_d.length); d = (byte)(d + 1)) { if (!mode) { if (this.black_0 == d) g.setClip(0, y + (d - offy) * 24, this.black_1 << 1, 24);  Ui.i().drawStringColor(this.gr.about_d[d].toString(), 19, y + (d - offy) * 24, 0, 0); g.setClip(0, 0, 240, 320); } else { short sw = (short)(Ms.i().getStringWidth(this.gr.about_d[d].toString()) - this.gr.getSIndexW(this.gr.about_d[d].toString())); Ui.i().drawStringColor(this.gr.about_d[d].toString(), 240 - sw >> 1, y + d * 24, 0, 0); }  }  if (!mode) { g.setColor(0); g.fillRect(0, 0, 240, 40); g.fillRect(0, 280, 240, 40); }  if (this.black_0 == this.gr.about_d.length) { this.gr.drawZero(120, 296, 0x1 | 0x20, 0); } else if (!mode && (this.black_1 = (byte)(this.black_1 + 4)) >= 120) { this.black_1 = 0; this.black_0 = (byte)(this.black_0 + 1); } else if (mode) { this.black_0 = (byte)this.gr.about_d.length; }  } public void key_map() { if (this.my.state == 17) { keyMiniMap(); return; }  if (this.my.state == 18) { keyMission(); return; }  if (this.my.state == 12) { keySelectMenu(); return; }  if (this.my.state == 19) { keyMySave(); return; }  if (this.my.state == 22 && this.black_0 > -1 && this.black_0 == this.gr.about_d.length && Ms.i().key_Num0()) { this.eventGoing = 3; this.black_0 = -1; Ms.i().keyRelease(); return; }  if (this.my.state == 16) { if (Ms.i().key_delay()) return;  if (Ms.i().key_Up_Down()) { this.gr.popMenu = (byte)(this.gr.popMenu ^ 0x1); } else if (Ms.i().key_S1_Num5() || Ms.i().key_S2()) { if (Ms.i().key_S2()) this.gr.popMenu = 1;  this.my.state = 0; return; }  } else { if (this.my.state == 10) { Ms.i().keyRelease(); return; }  if (this.my.state == 23) { if (Ms.i().key_S2()) { this.my.state = 25; } else if (Ms.i().key_S1_Num5()) { this.my.state = 24; }  Ms.i().keyRelease(); return; }  if (this.my.state == 11) { if (Ms.i().key_S1_Num5()) this.gr.say_c = 0;  if (this.gr.say_c == 0) this.my.state = 0;  return; }  if (this.my.state == 20) { keyAnoleSel(); return; }  }  if (this.my.state == 1 && Ms.i().key_S1_Num5()) { if (this.dialog_no + 2 >= this.dialog.length) { this.dialog_no = -1; this.dialog = null; if (this.eventGoing == 0) { this.my.state = 0; } else { this.eventGoing = 2; }  if (this.bDirTalk) { (this.npc[0][this.get_meet]).other[7] = this.npcDirTalk; if ((this.npc[0][this.get_meet]).other[7] > -1) (this.npc[0][this.get_meet]).dir = 3;  this.bDirTalk = false; }  return; }  this.dialog_no = (byte)(this.dialog_no + 2); Ms.i().keyRelease(); }  if (this.my.frame_c > 0) return;  if (this.my.state == 0) if (GameRun.run_state != 98) if (Ms.i().key_S1()) { this.gr.goYouPAUSE(0); } else if (Ms.i().key_S2()) { SMSSender.i(this.gr).go(0, true); }    if (this.my.state != 0) return;  Ms.i(); this.map_key = (byte)-Ms.key; doKey(); } private void drawAnoleSel() { byte j, h = 20; this.gr.cur_a = (byte)Ms.i().mathSpeedN(this.gr.cur_a, h * 2 + 12, 8, false); Ui.i().drawK1(0, 320 - this.gr.cur_a, 76, h + 4, 2); Ui.i().drawK2(0, 320 + h + 4 - this.gr.cur_a, 240, h + 8, 0); for (byte i = 0; i < 5; i = (byte)(i + 1)) { byte c; if (!isAnoleType(i)) { c = 6; j = (byte)(70 + i); } else { c = (byte)((i == this.anoleSel) ? 1 : 0); if (i == this.anole_type) { j = 69; } else { j = (byte)((i == this.anoleSel) ? (5 + i) : i); }  }  Ui.i().drawK0(32 + i * 240 / 6, 320 + h + 8 - this.gr.cur_a, 20, 20, c); Ui.i().drawUi(j, 32 + i * 240 / 6 + 10, 320 + h + 8 - this.gr.cur_a + 18, 33, 0); }  if (this.anoleSel == this.anole_type) { j = 0; } else { j = (byte)(isAnoleType(this.anoleSel) ? (this.anoleSel + 1) : 6); }  Ui.i().drawString(this.gr.about_a[j].toString(), 38, 320 - this.gr.cur_a, 17, 1, 2); Ui.i().drawK4(31 + this.anoleSel * 240 / 6 + this.gr.cur_b / 3, 320 + h + 7 - this.gr.cur_a + this.gr.cur_b / 3, 22 - (this.gr.cur_b / 3 << 1), 22 - (this.gr.cur_b / 3 << 1)); this.gr.cur_b = (byte)(this.gr.cur_b + 1); if (this.gr.cur_b > 4) this.gr.cur_b = 0;  Ui.i().drawYesNo(true, true); } private void keyAnoleSel() { if (Ms.i().key_delay()) return;  if (Ms.i().key_Left_Right()) { this.anoleSel = Ms.i().select(this.anoleSel, 0, 4); } else if (Ms.i().key_S1_Num5()) { if (!isAnoleType(this.anoleSel)) { this.gr.say("还没有获得该骑宠", 0); } else if (this.anoleSel == 4) { this.gr.say("只能在水里使用", 0); } else { if (this.anole_type == this.anoleSel) { this.anole_type = -1; } else { this.anole_type = this.anoleSel; }  if (this.anole_type == 3 && this.gr.rmsSms[0] == 0) { SMSSender.i(this.gr).go(1, true); this.anole_type = -1; } else { if (this.anole_type == 3 && this.gr.rmsSms[0] < 0) { this.gr.rmsSms[0] = (byte)(this.gr.rmsSms[0] + 1); this.gr.say("奇异兽不仅跑得快而且可以不遇敌，现提供3次试骑体验，先试后买！", -1); }  this.gr.magic_id = 13; this.gr.magic_x = this.map_x + this.my.x + 10; this.gr.magic_y = this.map_y + this.my.y + 19; this.gr.setMagic(this.gr.magic_id / 5); this.my.state = 21; }  }  Ms.i().keyRelease(); } else if (Ms.i().key_S2() || Ms.i().key_Num9()) { this.my.state = 0; Ms.i().keyRelease(); }  } public boolean initAnoleValue() { if (isAnole(this.my.x, this.my.y, this.anole_type)) { this.gr.say("不能更换坐骑", 0); return false; }  this.my.state = 20; this.gr.cur_a = 0; this.gr.cur_b = 0; this.gr.setStringB("下坐骑#n植物坐骑#n金属坐骑#n飞行坐骑#n野兽坐骑#n海洋坐骑#n无此坐骑", 240, 0); return true; } private boolean isAnoleType(int i) { return ((this.gr.rmsOther[2] & 1 << i) != 0); } public void addAnole(int i) { if (i != 13 && i != 34 && i != 49 && i != 66 && i != 82) return;  if (i == 13) { i = 0; } else if (i == 34) { i = 1; } else if (i == 49) { i = 2; } else if (i == 66) { i = 3; } else { i = 4; }  this.gr.rmsOther[2] = (byte)(this.gr.rmsOther[2] | 1 << i); } public void setAnole() { this.gr.immueEnemy = (byte)((this.anole_type == 3) ? 1 : 0); this.my.speed = getMySpeed(); Ms.i().setSprite(npcSp[1][0], "data/npc1/" + ((this.anole_type == -1) ? "0" : ("r" + this.anole_type)), (this.anole_type == -1)); this.my.setActionNo(false); } public byte getMySpeed() { if (this.anole_type == 0) return 6;  if (this.anole_type == 1) return 6;  if (this.anole_type == 2) return 10;  if (this.anole_type == 4) return 5;  if (this.anole_type == 3) return 10;  return 5; } private void doKey() { switch (this.map_key) { case 1: case 2: case 3: case 4: if (this.my.dir != this.map_key) turnAround(this.map_key);  canMove(this.dir_select[this.map_key][0] * this.my.speed, this.dir_select[this.map_key][1] * this.my.speed); break;case -53: case 5: checkSoftKey(this.my.x, this.my.y, this.dir_select[this.my.dir][0] * this.my.speed, this.dir_select[this.my.dir][1] * this.my.speed); break;case -57: Ms.i().keyRelease(); if (this.gr.rmsOther[2] > 0) initAnoleValue();  break;case -51: goMission(1, false); break;case -49: goMission(0, false); break;case -48: goMiniMap(); break; }  } private void turnAround(int dir) { this.my.dir = (byte)dir; this.my.setActionNo(true); } public void mapMove(int x, int y, int xs, int ys) { if ((xs > 0 && x >= this.mapLeft_NOmove && x < this.mapRight_NOmove) || (xs < 0 && x > this.mapLeft_NOmove && x <= this.mapRight_NOmove)) this.map_x -= xs;  if ((ys > 0 && y >= this.mapUp_NOmove && y < this.mapDown_NOmove) || (ys < 0 && y > this.mapUp_NOmove && y <= this.mapDown_NOmove)) this.map_y -= ys;  } private void oneMove(Npc npc_, int i) { if (npc_.frame_c > 0) { if (this.srcNpcNo == i) mapMove(npc_.x, npc_.y, npc_.speed_x, npc_.speed_y);  npc_.x = (short)(npc_.x + npc_.speed_x); npc_.y = (short)(npc_.y + npc_.speed_y); npc_.frame_c = (byte)(npc_.frame_c - 1); } else if (npc_.frame_c != -1) { npc_.frame_c = -1; npc_.setActionNo(false); }  } private byte checkIfNpc(Npc[] npc, int ix, int iy) { byte i; for (i = (byte)(npc.length - 1); i > -1; i = (byte)(i - 1)) { if (Ms.i().isRect(ix, iy, 19, 19, (npc[i]).x, (npc[i]).y, 20, 20) && ((npc[i]).other[4] == 1 || (npc[i]).other[4] == 3)) return i;  }  return -1; } private byte checkIfOther(byte[][] data, int ix, int iy) { byte i; for (i = (byte)(data.length - 1); i > -1; i = (byte)(i - 1)) { if (Ms.i().isRect(ix, iy, 19, 19, data[i][0] * 20, data[i][1] * 20, 20, 20)) return i;  }  return -1; } private byte checkSoftKey(int A_X, int A_Y, int _speed_x, int _speed_y) { if (A_X + _speed_x < 0 || A_X + _speed_x >= this.cols * 20 || A_Y + _speed_y < 0 || A_Y + _speed_y >= this.rows * 20) return 0;  byte type = 0; type = checkIfNpc(this.npc[0], A_X + _speed_x, A_Y + _speed_y); if (this.my.state == 0 && type != -1) { if (this.anole_type == 1 && (this.npc[0][type]).other[8] == 4) { (this.npc[0][type]).other[8] = -4; (this.npc[0][type]).other[7] = (byte)((this.npc[0][type]).other[7] + 1); (this.npc[0][type]).now_action = 0; (this.npc[0][type]).now_time = 0; } else if ((this.npc[0][type]).other[8] != -4) { this.my.state = 2; this.get_meet = type; if ((this.npc[0][type]).other[8] != 2 && (this.npc[0][type]).other[8] != 3 && !this.npcNameData[type].equals("路人")) { this.bDirTalk = true; this.npcDirTalk = (this.npc[0][type]).other[7]; Ms.i(); (this.npc[0][type]).other[7] = (byte)(Ms.abs(this.npcDirTalk) / 3 * 3 + ((this.my.dir < 4) ? (this.my.dir - 1) : (this.my.dir - 2))); if (this.my.dir == 3) { (this.npc[0][type]).other[7] = (byte)-(this.npc[0][type]).other[7]; } else { (this.npc[0][type]).dir = 3; }  } else { this.bDirTalk = false; }  }  return 9; }  type = checkIfOther(this.item, A_X + _speed_x, A_Y + _speed_y); if (type != -1 && this.item[type][2] != -1) { Ms.i().keyRelease(); this.get_meet = type; getItem(); return 0; }  return -1; } private void getItem() { if (this.item[this.get_meet][2] == 2) { this.gr.say("这个宝箱是空的。", 0); return; }  byte id = (byte)(this.item[this.get_meet][3] - 2); if (id == -2) { this.gr.money += this.item[this.get_meet][4] * 100; this.gr.say("获得金钱：" + (this.item[this.get_meet][4] * 100) + "金", 0); } else if (id == -1) { this.gr.coin += this.item[this.get_meet][4]; this.gr.say("获得徽章：" + this.item[this.get_meet][4] + "徽章", 0); } else { this.gr.getItem(id, this.item[this.get_meet][4]); }  this.item[this.get_meet][2] = 2; } private boolean isTrundleNpc(int ix, int iy) { byte type = checkIfNpc(this.npc[0], ix, iy); if (type != -1) return ((this.npc[0][type]).other[4] == 1 || (this.npc[0][type]).other[4] == 3);  if (ix >= 0 && ix < this.cols && iy >= 0 && iy < this.rows && this.worldData[ix][iy] != 1) return false;  return true; } private byte checkMap(int A_X, int A_Y, int _speed_x, int _speed_y) { this.checkNpcT = -1; this.checkType = checkIfOther(this.item, A_X + _speed_x, A_Y + _speed_y); if (this.checkType != -1) { this.checkNpcT = -2; return 1; }  if (A_X + _speed_x < 0 || A_X + _speed_x >= this.cols * 20 || A_Y + _speed_y < 0 || A_Y + _speed_y >= this.rows * 20) return 1;  this.checkType = checkIfNpc(this.npc[0], A_X + _speed_x, A_Y + _speed_y); if (this.checkType != -1) { if (!this.npcNameData[this.checkType].equals("路人") || (this.npc[0][this.checkType]).other[5] > 0) this.checkNpcT = this.checkType;  return 1; }  this.checkType = checkIfOther(this.door, A_X + _speed_x, A_Y + _speed_y); if (this.checkType != -1 && (this.door[this.checkType][5] == 2 || this.map_key == this.door[this.checkType][2])) { this.my.state = 5; this.get_meet = this.checkType; return 0; }  return checkWorld(A_X, A_Y, _speed_x, _speed_y, false); } private byte checkWorld(int A_X, int A_Y, int _speed_x, int _speed_y, boolean mode) { byte i = 0, check_n = 1; if ((_speed_x == 0 && A_X % 20 != 0) || (_speed_y == 0 && A_Y % 20 != 0)) check_n = 2;  for (; i < check_n; i = (byte)(i + 1)) { byte ix; byte iy; if (_speed_y == 0) { iy = (byte)((A_Y + 20 * i) / 20); ix = (byte)((_speed_x >= 0) ? ((A_X + _speed_x + 19) / 20) : ((A_X + _speed_x) / 20)); } else { ix = (byte)((A_X + 20 * i) / 20); iy = (byte)((_speed_y >= 0) ? ((A_Y + _speed_y + 19) / 20) : ((A_Y + _speed_y) / 20)); }  if (mode) { if (this.worldData[ix][iy] != 0) return (byte)this.worldData[ix][iy];  } else if (isCan_not_pass(ix, iy)) { return 1; }  }  return 0; } private boolean isAnole(int _x, int _y, int _anole_type) { if (_anole_type == 0) { _anole_type = 3; } else if (_anole_type == 2) { _anole_type = 4; } else if (_anole_type == 4) { _anole_type = 2; } else { return false; }  byte check_n = 0; if (_x % 20 != 0) check_n = (byte)(check_n + 1);  if (_y % 20 != 0) check_n = (byte)(check_n + 1);  check_n = (byte)(1 << check_n); if (check_n == 4 && this.worldData[(_x + 19) / 20][_y / 20] == _anole_type) return true;  if (check_n > 1) { if (_x % 20 != 0 && this.worldData[(_x + 19) / 20][(_y + 19) / 20] == _anole_type) return true;  if (_y % 20 != 0 && this.worldData[_x / 20][_y / 20] == _anole_type) return true;  }  return (this.worldData[_x / 20][_y / 20] == _anole_type); } private boolean isCan_not_pass(int ix, int iy) { if (this.worldData[ix][iy] == 1) return true;  if (this.anole_type == 0) return (this.worldData[ix][iy] == 2 || this.worldData[ix][iy] == 4);  if (this.anole_type == 2) return (this.worldData[ix][iy] == 3 || this.worldData[ix][iy] == 2);  if (this.anole_type == 4) return (this.worldData[ix][iy] != 2);  return (this.worldData[ix][iy] == 2 || this.worldData[ix][iy] == 3 || this.worldData[ix][iy] == 4); } private void meetGrass() { if (this.meet_step < this.step_MEET) { if (this.worldData[this.my.getIx()][this.my.getIy()] != 7) this.meet_step = (short)(this.meet_step + 1);  } else { if (this.gr.monPro.length > 1 && this.gr.immueEnemy == 0) { this.my.frame_c = 0; this.my.state = 8; }  notMeet(0, 0); }  } private void canMove(int temp_xspeed, int temp_yspeed) { this.my.speed_x = (byte)temp_xspeed; this.my.speed_y = (byte)temp_yspeed; if (this.my.x + this.my.speed_x >= 0 && this.my.x + this.my.speed_x + 19 < this.cols * 20 && this.my.y + this.my.speed_y >= 0 && this.my.y + this.my.speed_y + 19 < this.rows * 20) { this.roadType = checkMap(this.my.x, this.my.y, this.my.speed_x, this.my.speed_y); if (0 == this.roadType) { initMoveMy(); } else if (checkMoveOff(this.checkNpcT)) { initMoveMy(); } else if (this.checkNpcT == -1 || isCheckNpcOff()) { this.roadType = isMapChenk(this.roadType); if (this.roadType == -1) { moveOff((byte)3, (byte)1, -this.my.speed); } else if (this.roadType == -2) { moveOff((byte)4, (byte)2, this.my.speed); } else { checkAnole(); }  } else { Ms.i().keyRelease(); setMoveStop_m(); }  } else { setMoveStop_m(); this.checkType = checkIfOther(this.door, this.my.x, this.my.y); if (this.checkType != -1 && this.door[this.checkType][5] == 0 && this.map_key == this.door[this.checkType][2]) { this.my.state = 5; this.get_meet = this.checkType; }  }  } private boolean isCheckNpcOff() { if (this.checkNpcT < 0) return false;  if (this.my.speed_x != 0) { Ms.i(); byte w = (byte)(20 - Ms.abs(this.my.y - (this.npc[0][this.checkNpcT]).y)); if (w > 0 && w < 11) return true;  } else if (this.my.speed_y != 0) { Ms.i(); byte w = (byte)(20 - Ms.abs(this.my.x - (this.npc[0][this.checkNpcT]).x)); if (w > 0 && w < 11) return true;  }  return false; } private void moveOff(byte dirx, byte diry, int speed) { if (this.my.speed_x == 0) { this.my.dir = dirx; } else if (this.my.speed_y == 0) { this.my.dir = diry; }  this.my.speed_x = (byte)((this.my.speed_x != 0) ? 0 : speed); this.my.speed_y = (byte)((this.my.speed_y != 0) ? 0 : speed); checkMoveOff(this.checkNpcT); initMoveMy(); } private boolean checkMoveOff(int npcid) { if (npcid > -1) { byte oxl = (byte)((this.npc[0][npcid]).x + 20 - this.my.x); byte oxr = (byte)((this.npc[0][npcid]).x - this.my.x - 20); byte oyl = (byte)((this.npc[0][npcid]).y + 20 - this.my.y); byte oyr = (byte)((this.npc[0][npcid]).y - this.my.y - 20); if (this.my.speed_x < 0 && oxl != 0) { this.my.speed_x = (oxl < this.my.speed_x) ? this.my.speed_x : oxl; return true; }  if (this.my.speed_x > 0 && oxr != 0) { this.my.speed_x = (oxr < this.my.speed_x) ? oxr : this.my.speed_x; return true; }  if (this.my.speed_y < 0 && oyl != 0) { this.my.speed_y = (oyl < this.my.speed_y) ? this.my.speed_y : oyl; return true; }  if (this.my.speed_y > 0 && oyr != 0) { this.my.speed_y = (oyr < this.my.speed_y) ? oyr : this.my.speed_y; return true; }  } else { byte oxl = this.my.getIx_off(); byte oxr = (byte)(20 - this.my.getIx_off()); byte oyl = this.my.getIy_off(); byte oyr = (byte)(20 - this.my.getIy_off()); if (this.my.speed_x != 0 && oxl != 0) { if (this.my.speed_x < 0) { this.my.speed_x = (byte)((-oxl < this.my.speed_x) ? this.my.speed_x : -oxl); } else { this.my.speed_x = (oxr < this.my.speed_x) ? oxr : this.my.speed_x; }  return true; }  if (this.my.speed_y != 0 && oyl != 0) { if (this.my.speed_y < 0) { this.my.speed_y = (byte)((-oyl < this.my.speed_y) ? this.my.speed_y : -oyl); } else { this.my.speed_y = (oyr < this.my.speed_y) ? oyr : this.my.speed_y; }  return true; }  }  return false; } private void checkAnole() { this.checkType = checkWorld(this.my.x, this.my.y, this.my.speed_x, this.my.speed_y, true); if (this.checkType == 2) { if (isCheckAnole(4)) return;  } else if (this.checkType == 3) { if (isCheckAnole(0)) return;  } else if (this.checkType == 4) { if (isCheckAnole(2)) return;  } else if (this.checkType == 0 && this.anole_type == 4) { if (this.anole_type != 3 && isAnoleType(3)) { this.anole_type = 3; } else { this.anole_type = -1; }  setAnole(); initMoveMy(); return; }  Ms.i().keyRelease(); setMoveStop_m(); } private boolean isCheckAnole(int id) { if (this.anole_type != id && isAnoleType(id)) { this.anole_type = (byte)id; setAnole(); return true; }  if (!isAnoleType(id)) this.gr.say("没有" + this.gr.monsterT[id] + "坐骑" + "，不能通过该地形", -1);  return false; } private void setMoveStop_m() { this.my.speed_x = 0; this.my.speed_y = 0; this.my.frame_c = -1; this.my.setActionNo(false); } private byte isMapChenk(byte checkType) { byte checkLeft = 0, checkRight = 0, ix = (byte)(this.my.x / 20), iy = (byte)(this.my.y / 20); if (this.my.speed_x == 0) { checkLeft = checkMap((ix - ((this.my.x % 20 != 0) ? 0 : 1)) * 20, (iy + ((this.my.speed_y >= 0) ? 1 : -1)) * 20, 0, 0); checkRight = checkMap((ix + 1) * 20, (iy + ((this.my.speed_y >= 0) ? 1 : -1)) * 20, 0, 0); } else if (this.my.speed_y == 0) { checkLeft = checkMap((ix + ((this.my.speed_x >= 0) ? 1 : -1)) * 20, (iy - ((this.my.y % 20 != 0) ? 0 : 1)) * 20, 0, 0); checkRight = checkMap((ix + ((this.my.speed_x >= 0) ? 1 : -1)) * 20, (iy + 1) * 20, 0, 0); }  if (checkLeft == 1 && checkRight == 1) return checkType;  if (checkLeft == 0) if (checkMap(((this.my.speed_x == 0) ? (ix - ((this.my.x % 20 != 0) ? 0 : 1)) : ix) * 20, ((this.my.speed_y == 0) ? (iy - ((this.my.y % 20 != 0) ? 0 : 1)) : iy) * 20, 0, 0) == 0) return -1;   if (checkRight == 0) if (checkMap(((this.my.speed_x == 0) ? (ix + ((this.my.x % 20 != 0) ? 0 : 1)) : ix) * 20, ((this.my.speed_y == 0) ? (iy + ((this.my.y % 20 != 0) ? 0 : 1)) : iy) * 20, 0, 0) == 0) return -2;   return checkType; } private void dialog(String npcName) { byte x = 10; short y = 272; byte height = 51; byte line_c = 0; Ui.i().drawKuang(4, 320 - height - 4, 236, height + 4); if (null != npcName) { drawNpcFace(this.faceDir ? 190 : 50, 320 - height - 4, 0x1 | 0x20); Ui.i().drawKuang(0, y - height + 20, Ms.i().getStringWidth(npcName) + 16, 28); Ui.i().drawString(npcName, 8, y - height + 2 + 20, 0, 9, 1); }  while (line_c < 2 && this.dialog_no + line_c < this.dialog.length) { Ui.i().drawStringColor(this.dialog[this.dialog_no + line_c].toString(), x, y - 2 + line_c * 20, -1, 1); line_c = (byte)(line_c + 1); }  } public void setNull() { this.npc[0] = null; this.item = (byte[][])null; this.door = (byte[][])null; this.event = (byte[][])null; this.mapImg = null; this.moduleData = (short[][])null; } private byte[] getNpcData() { ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); getNpcData(byteArray, this.npc[0]); getNpcData(byteArray, this.npc[1]); getNpcData(byteArray, this.npc[2]); getItemData(byteArray, this.item); getItemData(byteArray, this.door); return byteArray.toByteArray(); } private void getNpcData(ByteArrayOutputStream byteArray, Npc[] npc) { try { for (int i = 0; i < npc.length; i++) { if ((npc[i]).other[9] == 1) byteArray.write((npc[i]).other);  }  } catch (Exception ex) { ex.printStackTrace(); }  } private void getItemData(ByteArrayOutputStream byteArray, byte[][] data) { try { byteArray.write(data.length); for (int i = 0; i < data.length; i++) { byteArray.write((data[i]).length); byteArray.write(data[i]); }  } catch (Exception ex) { ex.printStackTrace(); }  } public Map(GameRun gameRun) { this.go = -2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  495 */     this.bExitBoss = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  503 */     this.mapNotemp = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  512 */     this.fmap = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  549 */     this.sIfElse = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1084 */     this.gmErr = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     this.xxx = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1688 */     this.dir_select = new byte[][] { { 0, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1805 */     this.anoleSel = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1925 */     this.npcDirTalk = -1;
/* 1926 */     this.bDirTalk = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2014 */     this.checkNpcT = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2105 */     this.roadType = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2349 */     this.miniMapMode = false;
/* 2350 */     this.showArea = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2356 */     this.littleMapClipY = 300;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2497 */     this.bPause = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2689 */     this.moduleData = (short[][])null;
/* 2690 */     this.mapImg = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2730 */     this.bgColor = 0;
/* 2731 */     this.bottomData = null; this.frontData = null; this.worldData = (short[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2869 */     this.firstDrawMap = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2922 */     this.map_left = 0; this.map_top = 0; this.map_right = 0; this.map_bottom = 0; this.rightCol = 0; this.bottomRow = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3009 */     this.imgFace = null;
/* 3010 */     this.faceDir = false;
/* 3011 */     this.faceLast = -1;
/* 3012 */     this.face_c = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3459 */     this.bSrc_c = 0;
/*      */ 
/*      */     
/* 3462 */     this.bSrc = false;
/*      */     
/* 3464 */     this.srcFlash_c = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3594 */     this.boatSelect = -1; this.gr = gameRun; System.gc(); init(); }
/*      */   private void drawMiniMap() { short x = 12, fh = 24; Ui.i().fillRectB(); Ui.i().drawK2(1, 2, 238, 318, 0); Ui.i().drawK1(6, 28, 228, 320 - fh * 3 - 15, 1); Ui.i().drawK1(6, 314 - fh * 2, 228, fh * 2, 2); Ui.i().drawYesNo(false, true); Ui.i().drawString("游戏地图", 120, 4, 17, 1, 2); if (this.showArea == -1) { Ui.i().drawString("该区域没有地图！", 120, 316 - fh * 2, 0x10 | 0x1, 3, 0); return; }  this.gr.showStringM("人物当前位置：" + this.gr.getNameCity(this.myMiniMap), 120, 316 - fh * 2, 10, 3); g.setClip(6, 28, 228, 305 - fh * 3); byte ww = 13, hh = 8, j = 0; int t = 120 - this.mapdataMap[this.selectMap][0] * ww - (this.miniMapMode ? (this.mapdataMap[this.selectMap][6] * ww / 2) : 25); this.mapoffx = Ms.i().mathSpeedN(this.mapoffx, t, 10, false); t = this.littleMapClipY / 2 - this.mapdataMap[this.selectMap][1] * hh - (this.miniMapMode ? (this.mapdataMap[this.selectMap][7] * hh / 2) : 15); this.mapoffy = Ms.i().mathSpeedN(this.mapoffy, t, 10, false); for (; j < (this.mapdataArea[this.showArea]).length; j = (byte)(j + 1)) { byte tmap = this.mapdataArea[this.showArea][j]; if (tmap >= 0 && (this.mapdataMap[tmap]).length > 0 && (this.miniMapMode || tmap != this.myMiniMap)) drawMapRect(ww, hh, tmap, false);  }  if (!this.miniMapMode) drawMapRect(ww, hh, this.myMiniMap, false);  drawMapRect(ww, hh, this.selectMap, true); g.setClip(0, 0, 240, 320); }
/* 3596 */   private void drawMapRect(byte ww, byte hh, int tmap, boolean select) { int width = 6 * ww, height = 6 * hh; if (this.miniMapMode) { width = this.mapdataMap[tmap][6] * ww; height = this.mapdataMap[tmap][7] * hh; }  if (select) { Ui.i().drawRectZ(12352252, this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 5); } else { if (tmap == this.myMiniMap) { Ui.i().drawK1(this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 4); } else { Ui.i().drawK0(this.mapdataMap[tmap][0] * ww + this.mapoffx, this.mapdataMap[tmap][1] * hh + this.mapoffy, width, height, 2); }  if (this.miniMapMode) { this.gr.showStringM(this.gr.getNameCity(tmap), this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1) + 19, this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1) - 20, 4, (tmap == this.myMiniMap) ? 0 : 3); } else { this.gr.showStringM(this.gr.getNameCity(tmap), this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1), this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1) - 20, 4, (tmap == this.myMiniMap) ? 0 : 3); }  if (tmap == this.myMiniMap) Ui.i().drawUi(67, this.mapdataMap[tmap][0] * ww + this.mapoffx + (width >> 1), this.mapdataMap[tmap][1] * hh + this.mapoffy + (height >> 1), 3, 0);  }  } private void keyMiniMap() { Ms.i().keyRelease(); if (!Ms.i().key_S1_Num5()) if (Ms.i().key_S2() || Ms.i().key_Num0()) { this.my.state = 0; this.mapdataArea = (byte[][])null; this.mapdataMap = (byte[][])null; } else { Ms.i(); Ms.i(); if ((Ms.i().key_Left_Right() || Ms.i().key_Up_Down()) && (this.mapdataMap[this.selectMap]).length >= 6 && this.mapdataMap[this.selectMap][Ms.abs(Ms.key) + 1] != -1) { Ms.i(); Ms.i(); this.selectMap = this.mapdataMap[this.selectMap][Ms.abs(Ms.key) + 1]; }  }   } public void goMiniMap() { Ms.i().keyRelease(); byte[] data = Ms.i().getStream("data/map.d", -1); Ms.i(); Ms.skip = 0; this.mapdataArea = Ms.i().create2Array(data); this.mapdataMap = Ms.i().create2Array(data); data = null; this.my.state = 17; this.showArea = -1; this.myMiniMap = -1; this.selectMap = 0; byte i = 0; for (; i < this.mapdataArea.length; i = (byte)(i + 1)) { for (byte k = 0; k < (this.mapdataArea[i]).length; k = (byte)(k + 1)) { if (this.mapNo == this.mapdataArea[i][k]) { this.selectMap = this.mapNo; if ((this.mapdataMap[this.selectMap]).length > 0) { this.myMiniMap = this.selectMap; this.showArea = i; this.miniMapMode = (this.mapdataArea[this.showArea][0] > -1); return; }  }  }  }  } private void drawMySave() { this.gr.showString("是否存盘?", 260, 0); Ui.i().drawYesNo(true, true); } private void keyMySave() { if (Ms.i().key_S1()) { this.my.state = 0; this.eventGoing = 2; } else if (Ms.i().key_S2()) { this.my.state = 0; this.eventGoing = 3; }  } private void initMissionList() { byte[] info = Ms.i().rmsOptions(7, null, 1); if (info[0] == -1) { this.bMission = null; } else { this.bMission = info; }  info = null; if (null == this.bMission) this.bMission = new byte[20];  } public void goMission(int type, boolean b) { if (this.my.state != 18 && this.my.state != 0) return;  Ms.i().keyRelease(); this.bPause = b; GameRun.run_state = -10; this.my.state = 18; this.gr.cur_a = (byte)type; this.gr.select[0][1] = 0; this.gr.select[0][0] = 0; Ms.i(); Ms.skip = 0; byte[] data = Ms.i().getStream("data/mission" + type + ".t", -1); this.sMission = Ms.i().createString2Array(data); this.gr.line_max = 6; this.gr.about_a = null; if (type == 1) { byte i = 0, j = 0, tMis[] = new byte[50]; for (; i < this.sMission.length; i = (byte)(i + 1)) { if (isMission(i)) { j = (byte)(j + 1); tMis[j] = i; }  }  this.mDirect = null; this.mDirect = new byte[j]; System.arraycopy(tMis, 0, this.mDirect, 0, this.mDirect.length); if (this.mDirect.length > 0) this.gr.setStringB(this.sMission[this.mDirect[this.gr.select[0][0]]][1].toString(), 202, 0);  tMis = null; } else { this.gr.select[0][0] = this.bMission[14]; if (this.bMission[14] >= this.gr.line_max) { this.gr.select[0][1] = (byte)(this.gr.select[0][0] - this.gr.line_max + 1); } else { this.gr.select[0][1] = 0; }  this.gr.setStringB(this.sMission[this.gr.select[0][0]][1].toString(), 202, 0); }  } public void setMission(int id, boolean bb) { byte type = (byte)(id / 8); id = (byte)(id % 8); if (bb) { this.bMission[type] = (byte)(this.bMission[type] | 1 << id); } else if ((this.bMission[type] & 1 << id) != 0) { this.bMission[type] = (byte)(this.bMission[type] ^ 1 << id); this.bMission[15] = (byte)(this.bMission[15] + 1); }  } private boolean isMission(int id) { byte type = (byte)(id / 8); id = (byte)(id % 8); if ((this.bMission[type] & 1 << id) != 0) return true;  return false; } private void drawMission() { Ui.i().fillRectB(); Ui.i().drawK2(1, 6, 238, 314, 0); short ly = 35; Ui.i().drawK1(8, ly, 214, this.gr.line_max * 24 + 10, 1); Ui.i().drawK1(8, ly + 18 + this.gr.line_max * 24, 224, 100, 2); drawMisstionList(8, ly + 6, 214, 24, this.gr.line_max, this.gr.select[0], (this.gr.cur_a == 0) ? (this.bMission[14] + 1) : this.mDirect.length); byte d = 0; for (; null != this.gr.about_a && d < this.gr.about_a.length; d = (byte)(d + 1)) Ui.i().drawStringColor(this.gr.about_a[d].toString(), 19, ly + 20 + this.gr.line_max * 24 + d * 20, 3, 0);  Ui.i().drawK1((this.gr.cur_a == 0) ? 25 : 140, 9, 76, 24, 4); Ui.i().drawString("主线任务", 63, 9, 17, (this.gr.cur_a == 0) ? 0 : 1, 0); Ui.i().drawString("分支任务", 177, 9, 17, (this.gr.cur_a == 1) ? 0 : 1, 0); Ui.i().drawTriangle(120, 18, 114, true, true); Ui.i().drawString(((this.gr.cur_a == 0) ? "主线完成度：" : "分支完成度：") + Ms.i().getPrecision(this.bMission[(this.gr.cur_a == 0) ? 14 : 15] * 1000 / ((this.gr.cur_a == 0) ? (this.sMission.length - 1) : this.sMission.length)) + "%", 120, 317, 33, 0, 1); Ui.i().drawYesNo(false, true); } private void drawMisstionList(int x, int y, int w, int sh, int show_num, byte[] sel, int length) { byte i = sel[1]; Ui.i().drawListKY(show_num, x, y, w, 6, sh, -1, sel[0] - sel[1], 4, 2); while (i < sel[1] + show_num) { if (i < length) { String s; if (this.gr.cur_a == 0) { s = (i + 1) + "、" + this.sMission[i][0].toString(); } else { s = (i + 1) + "、" + this.sMission[this.mDirect[i]][0].toString(); }  Ui.i().drawString(s, x + 8, y + (i - sel[1]) * (sh - 1), 0, (i < this.bMission[14]) ? -1 : ((sel[0] == i) ? 0 : 3), 0); if (this.gr.cur_a == 0 && i != length - 1) Ui.i().drawString("完成", x + w - 8, y + (i - sel[1]) * (sh - 1), 24, (i < this.bMission[14]) ? -1 : ((sel[0] == i) ? 0 : 3), 0);  }  i = (byte)(i + 1); }  Ui.i().sliding(x + w + 4, y, show_num * sh, sel[0], length, true); } private void keyMission() { if (Ms.i().key_delay()) return;  if (Ms.i().key_Left_Right() || (Ms.i().key_Num1() && this.gr.cur_a == 1) || (Ms.i().key_Num3() && this.gr.cur_a == 0)) { this.gr.cur_a = (byte)(this.gr.cur_a ^ 0x1); goMission(this.gr.cur_a, this.bPause); } else if (Ms.i().key_Up_Down()) { byte length; if (this.gr.cur_a == 0) { length = (byte)((this.bMission[14] + 1 > this.sMission.length) ? this.sMission.length : (this.bMission[14] + 1)); } else { length = (byte)this.mDirect.length; }  Ms.i().selectS(this.gr.select[0], 0, length, this.gr.line_max); if (this.gr.cur_a == 0) { this.gr.setStringB(this.sMission[this.gr.select[0][0]][1].toString(), 202, 0); } else if (this.mDirect.length > 0) { this.gr.setStringB(this.sMission[this.mDirect[this.gr.select[0][0]]][1].toString(), 202, 0); } else { this.gr.about_a = null; }  } else if (Ms.i().key_S2() || Ms.i().key_Num1() || Ms.i().key_Num3()) { this.my.state = 0; if (this.bPause) { this.gr.doPaint(0); GameRun.run_state = 97; } else { GameRun.run_state = -10; }  this.mDirect = null; this.sMission = (StringBuffer[][])null; this.gr.about_a = null; Ms.i().keyRelease(); }  } private void createMap() { loadMapModuleAndImage(); loadMapData(this.mapNo); } private void loadMapModuleAndImage() { Ms.i(); Ms.skip = 0; byte[] data = Ms.i().getStream("data/map/area.dat", -1); byte[] areaMap = Ms.i().createArray(data); byte[][] areaPic = Ms.i().create2Array(data); data = null; if (null != this.mapImg && areaMap[this.lastMap] == areaMap[this.mapNo]) { areaMap = null; areaPic = (byte[][])null; return; }  Ms.i(); Ms.skip = 0; this.moduleData = Ms.i().createShort2Array(Ms.i().getStream("data/map/" + areaMap[this.mapNo] + ".data", -1), 1); this.mapImg = null; this.mapImg = new Image[(areaPic[areaMap[this.mapNo]]).length]; for (byte i = 0; i < (areaPic[areaMap[this.mapNo]]).length; i = (byte)(i + 1)) this.mapImg[i] = Ms.i().createImage("data/map/" + areaPic[areaMap[this.mapNo]][i]);  areaMap = null; areaPic = (byte[][])null; } private void loadMapData(int id) { int elemNum = 0; int dataType = 0; byte[] buffShort = new byte[2]; byte[] buffInt = new byte[4]; byte layerType = 0; this.bottomData = null; this.frontData = null; this.worldData = (short[][])null; short arrayIndex = 0; try { DataInputStream dis = new DataInputStream(getClass().getResourceAsStream("/data/map/" + id + ".mid")); dis.skip(2L); dis.read(buffInt); Ms.i(); this.bgColor = (int)Ms.getNum(buffInt); dataType = dis.readByte(); byte[] buff = new byte[dataType]; dis.read(buff); dis.read(buff); Ms.i(); this.cols = (short)(byte)(int)Ms.getNum(buff); dis.read(buff); Ms.i(); this.rows = (short)(byte)(int)Ms.getNum(buff); this.worldData = new short[this.cols][this.rows]; byte i; for (i = 0; i < 3; i = (byte)(i + 1)) { layerType = dis.readByte(); if (layerType != -1) { dis.read(buffInt); Ms.i(); elemNum = (int)Ms.getNum(buffInt); elemNum *= 3; switch (layerType) { case 1: this.bottomData = new short[elemNum]; break;case 3: this.frontData = new short[elemNum]; break; }  for (arrayIndex = 0; arrayIndex < elemNum; ) { dis.read(buff); Ms.i(); int x = (short)(int)Ms.getNum(buff); dis.read(buff); Ms.i(); int y = (short)(int)((this.rows - 1) - Ms.getNum(buff)); dis.read(buffShort); Ms.i(); int z = (short)(int)Ms.getNum(buffShort); switch (layerType) { case 1: arrayIndex = (short)(arrayIndex + 1); this.bottomData[arrayIndex] = (short)x; arrayIndex = (short)(arrayIndex + 1); this.bottomData[arrayIndex] = (short)y; arrayIndex = (short)(arrayIndex + 1); this.bottomData[arrayIndex] = (short)z;case 2: arrayIndex = (short)(arrayIndex + 3); this.worldData[x][y] = (short)(z >> 3);case 3: arrayIndex = (short)(arrayIndex + 1); this.frontData[arrayIndex] = (short)x; arrayIndex = (short)(arrayIndex + 1); this.frontData[arrayIndex] = (short)y; arrayIndex = (short)(arrayIndex + 1); this.frontData[arrayIndex] = (short)z; }  }  }  }  dis.close(); buff = null; buffShort = null; buffInt = null; dis = null; } catch (Exception e) {} } void drawMap(Graphics g) { int tempLeftCol = this.leftCol; int tempTopRow = this.topRow; if (this.map_x >= 0) { this.leftCol = 0; this.map_left = this.map_x; } else { this.leftCol = (short)(-this.map_x / 20); this.map_left = this.map_x % 20; }  if (this.map_y >= 0) { this.topRow = 0; this.map_top = this.map_y; } else { this.topRow = (short)(-this.map_y / 20); this.map_top = this.map_y % 20; }  int left = this.leftCol * 20; int top = this.topRow * 20; if (this.firstDrawMap == 0) { this.firstDrawMap = 1; bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, 260, 340, true); } else if (this.leftCol != tempLeftCol || this.topRow != tempTopRow) { g.setClip(0, 0, 260, 340); g.copyArea(0, 0, 260, 340, (tempLeftCol - this.leftCol) * 20, (tempTopRow - this.topRow) * 20, 20); if (this.leftCol < tempLeftCol) { bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, (tempLeftCol - this.leftCol) * 20, 340, true); } else if (this.leftCol > tempLeftCol) { bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 260 + (tempLeftCol - this.leftCol) * 20, 0, 260, 340, true); }  if (this.topRow < tempTopRow) { bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 0, 260, (tempTopRow - this.topRow) * 20, true); } else if (this.topRow > tempTopRow) { bkgroundToPaint(this.bottomData, this.mapImg, left, top, this.moduleData, g, 0, 340 + (tempTopRow - this.topRow) * 20, 260, 340, true); }  }  this; Map.g.drawImage(GameRun.scrT, this.map_left, this.map_top, 20); } public void bkgroundToPaint(short[] bkgroundData, Image[] img, int screen_x, int screen_y, short[][] moduleData, Graphics gtem, int x1, int y1, int x2, int y2, boolean fillBlack) { if (null == bkgroundData) return;  screen_y -= 20; if (fillBlack) { gtem.setClip(x1, y1, x2 - x1, y2 - y1); gtem.setColor(this.bgColor); gtem.fillRect(x1, y1, x2 - x1, y2 - y1); }  int bkdata_id = -1, bkdata_trans = 0; int bkPic_w = 0, bkPic_h = 0; for (int i = 0; i < bkgroundData.length; i += 3) { if (bkgroundData[i + 2] >= 0) { bkdata_id = bkgroundData[i + 2] >> 3; bkdata_trans = bkgroundData[i + 2] & 0x3; int realX = bkgroundData[i] * 20 - screen_x; int realY = bkgroundData[i + 1] * 20 - screen_y; if (bkdata_trans % 2 == 1) { bkPic_w = moduleData[bkdata_id][4]; bkPic_h = moduleData[bkdata_id][3]; } else { bkPic_w = moduleData[bkdata_id][3]; bkPic_h = moduleData[bkdata_id][4]; }  if (realX + bkPic_w >= x1 && realX <= x2 && realY >= y1 && realY - bkPic_h <= y2) Ui.i().drawRegion(img[moduleData[bkdata_id][0]], moduleData[bkdata_id][1], moduleData[bkdata_id][2], moduleData[bkdata_id][3], moduleData[bkdata_id][4], realX, realY, 0x4 | 0x20, bkgroundData[i + 2] & 0x7, gtem);  }  }  } private void keySelectMenu() { if (Ms.i().key_delay()) return;  if (Ms.i().key_S1_Num5()) { this.my.state = 13; } else if (Ms.i().key_Up_Down()) { this.gr.cur_a = Ms.i().select(this.gr.cur_a, 0, this.gr.action_str.length - 1); }  } private void drawNpcFace(int x, int y, int dir) { if (null != this.imgFace) Ui.i().drawImage(this.imgFace, x + this.face_c, y, dir);  this.face_c = Ms.i().mathSpeedN(this.face_c, 0, 20, false); } public void initNpcFaceOne(String name) { if (null == name) return;  byte id = -1; this.faceDir = true; if (name.equals("塞其")) { id = 0; this.faceDir = false; } else if (name.equals("安琪儿") || name.equals("蓝发女孩")) { id = 1; } else if (name.equals("幽暗鬼王")) { id = 2; } else if (name.equals("杰奇校长")) { id = 3; } else if (name.equals("普罗") || name.equals("普罗校长")) { id = 4; } else if (name.equals("神秘东方男子")) { id = 5; } else if (name.equals("南宫云")) { id = 6; }  if (id > -1) { if (null == this.imgFace || this.faceLast != id) { this.face_c = (short)(this.faceDir ? 50 : -50); this.faceLast = id; this.imgFace = null; this.imgFace = Ms.i().createImage("data/face/" + id); }  } else { nullNpcFace(); }  } public void nullNpcFace() { this.imgFace = null; } public void initBrowImage() { if (null == this.brow) this.brow = Ms.i().createSprite("data/face/brow", true);  } private void drawCheck5(int id) { if (this.checkNpcT < 0 || this.my.state != 0 || (this.npc[0][this.checkNpcT]).other[6] != 0) return;  Ui.i().drawFrameOne(this.brow, this.brow.action(id, this.action_5, 0), this.map_x + (this.npc[0][this.checkNpcT]).x + 10, this.map_y + (this.npc[0][this.checkNpcT]).y - 16 - 10, 0); if ((this.time_5 = (byte)(this.time_5 + 1)) > this.brow.action(id, this.action_5, 1)) { if ((this.action_5 = (byte)(this.action_5 + 1)) >= this.brow.aLength(id)) this.action_5 = 0;  this.time_5 = 0; }  } private void drawBrow() { byte i = 0; while (true) { if (i < this.npcList.length) { byte id = this.npcList[i]; if (id >= -1) { if (id == -1) { cur_npc = this.my; } else { cur_npc = this.npc[0][id]; }  if ((cur_npc.other[4] == 1 || cur_npc.other[4] == 2) && (cur_npc.brow_type > -1 || cur_npc.other[6] != 0)) if (id <= -1 || isNpcSrc(cur_npc.x, cur_npc.y)) { if (cur_npc.other[6] != 0) cur_npc.brow_type = cur_npc.other[6];  if (cur_npc.brow_action >= this.brow.aLength(cur_npc.brow_type)) if (cur_npc.other[6] != 0) { cur_npc.brow_action = 0; } else { cur_npc.brow_type = -1; i = (byte)(i + 1); }   Ui.i().drawFrameOne(this.brow, this.brow.action(cur_npc.brow_type, cur_npc.brow_action, 0), this.map_x + cur_npc.x + 14, this.map_y + cur_npc.y - 15 - 10, 0); if ((cur_npc.brow_time = (byte)(cur_npc.brow_time + 1)) > this.brow.action(cur_npc.brow_type, cur_npc.brow_action, 1)) { cur_npc.brow_action = (byte)(cur_npc.brow_action + 1); cur_npc.brow_time = 0; }  }   }  } else { break; }  i = (byte)(i + 1); }  } static Sprite[][] npcSp = new Sprite[3][]; private byte[] npcList; private short black_width; private short bSrc_c; private boolean bSrc; private byte srcFlash_c; Npc my; private Image imgCloud; private short[][] cloud; public byte boatSelect; public byte[][] boatCourse; public void initNpcAniOne(String sid, int id, int type) { npcSp[type][id] = Ms.i().createSprite("data/npc" + type + "/" + sid, (type == 2) ? this.gr.isNpc2ImageType(Integer.parseInt(sid)) : true); } public void initNpcAni() { byte k = 0; byte[][] em = new byte[3][]; em[0] = new byte[100]; em[1] = new byte[5]; em[2] = new byte[60]; this.gr.initNidusMap(0); byte i; for (i = (byte)(this.npc.length - 1); i > -1; i = (byte)(i - 1)) { for (byte b = (byte)((this.npc[i]).length - 1); b > -1; b = (byte)(b - 1)) { em[(this.npc[i][b]).other[3] - 1][(this.npc[i][b]).other[2]] = 1; if (i == 0) { this.npcList[b + 1] = b; if ((this.npc[i][b]).other[8] == 1) { k = (byte)(k + 1); this.gr.nidusMap[k] = b; }  }  }  }  this.gr.initNidusMap(1); this.npcList[0] = -1; for (i = (byte)(this.item.length + (this.npc[0]).length); i > (this.npc[0]).length; i = (byte)(i - 1)) this.npcList[i] = (byte)(-2 - i + (this.npc[0]).length + 1);  initNpcAni_1(100, 0, em[0]); initNpcAni_1(5, 1, em[1]); initNpcAni_1(60, 2, em[2]); em = (byte[][])null; for (byte j = (byte)((this.npc[0]).length - 1); j > -1; j = (byte)(j - 1)) this.npc[0][j].setNpcNum(npcSp[(this.npc[0][j]).other[3] - 1][(this.npc[0][j]).other[2]].aLength());  } private void initNpcAni_1(int length, int type, byte[] em) { for (int i = length - 1; i > 0; i--) { if (em[i] != 0) { if (null == npcSp[type][i]) initNpcAniOne("" + i, i, type);  } else { npcSp[type][i] = null; }  }  } private boolean isNpcSrc(int ax, int ay) { return (this.map_x + ax >= -80 && this.map_x + ax <= 300 && this.map_y + ay >= -80 && this.map_y + ay <= 380); } private void drawNpcAni(Npc[] npc) { if (null == npc) return;  for (byte i = 0; i < npc.length; i = (byte)(i + 1)) { if (((npc[i]).other[4] == 1 || (npc[i]).other[4] == 2) && isNpcSrc((npc[i]).x, (npc[i]).y)) drawNpcAniOne(npc, i);  runNpcAniOne(npc, i); }  } private void drawNMAni(Npc[] npc) { if (null == npc) return;  for (byte i = 0; i < this.npcList.length; i = (byte)(i + 1)) { byte id = this.npcList[i]; if (id == -1) { if (this.my.other[4] == 1 || this.my.other[4] == 2) drawMyAni(this.my, 0, this.map_x + this.my.x + 10, this.map_y + this.my.y + 19, this.my.other[7]);  } else if (id < -1) { Ms.i(); id = (byte)(Ms.abs(id) - 2); if (isNpcSrc(this.item[id][0] * 20, this.item[id][1] * 20)) Ui.i().drawUi((this.item[id][2] == 2) ? 55 : 54, this.map_x + this.item[id][0] * 20 + 10, this.map_y + this.item[id][1] * 20 + 20, 33, 0);  } else { if (((npc[id]).other[4] == 1 || (npc[id]).other[4] == 2) && isNpcSrc((npc[id]).x, (npc[id]).y)) { if ((npc[id]).other[8] != 3 && !this.npcNameData[id].equals("路人")) g.drawImage(this.imgShadow, this.map_x + (npc[id]).x + 10, this.map_y + (npc[id]).y + 20, 33);  drawNpcAniOne(npc, id); }  runNpcAniOne(npc, id); }  }  } private void drawNpcAniOne(Npc[] npc, int i) { byte type = (byte)((npc[i]).other[3] - 1); byte id = (npc[i]).other[2]; byte action_id = (npc[i]).other[7]; byte now_action = (npc[i]).now_action; if (action_id < 0) { (npc[i]).dir = 4; action_id = (byte)-action_id; }  if ((npc[i]).now_action >= npcSp[type][id].aLength(action_id)) { now_action = (npc[i]).now_action = 0; if ((npc[i]).other[8] == -4) { (npc[i]).other[8] = 0; (npc[i]).other[4] = 0; return; }  if (!(npc[i]).bdir && setlastA(npc, i)) { drawNpcAniOne(npc, i); return; }  }  Ui.i().drawFrameOne(npcSp[type][id], npcSp[type][id].action(action_id, now_action, 0), this.map_x + (npc[i]).x + 10, this.map_y + (npc[i]).y + 19, ((npc[i]).dir == 4) ? 1 : 0); } private void runNpcAniOne(Npc[] npc, int i) { byte type = (byte)((npc[i]).other[3] - 1); byte id = (npc[i]).other[2]; Ms.i(); byte action_id = (byte)Ms.abs((npc[i]).other[7]); if ((npc[i]).now_action >= npcSp[type][id].aLength(action_id)) { (npc[i]).now_action = 0; setlastA(npc, i); }  if (((npc[i]).now_time = (byte)((npc[i]).now_time + 1)) >= npcSp[type][id].action(action_id, (npc[i]).now_action, 1)) { (npc[i]).now_action = (byte)((npc[i]).now_action + 1); (npc[i]).now_time = 0; }  } private boolean setlastA(Npc[] npc, int i) { if ((npc[i]).lastAction != Byte.MAX_VALUE) { (npc[i]).other[7] = (npc[i]).lastAction; (npc[i]).lastAction = Byte.MAX_VALUE; return true; }  return false; } public void drawMyAni(Npc my, int my_id, int x, int y, int action_id) { byte id = (byte)my_id; if (my.now_action >= npcSp[1][id].aLength(action_id)) my.now_action = 0;  if (this.anole_type != 2 && this.anole_type != 4) Ui.i().drawImage(this.imgShadow, x, y, 33);  Ui.i().drawFrameOne(npcSp[1][id], npcSp[1][id].action(action_id, my.now_action, 0), x, y, (my.dir == 4) ? 1 : 0); if ((my.now_time = (byte)(my.now_time + 1)) > npcSp[1][id].action(action_id, my.now_action, 1)) { my.now_action = (byte)(my.now_action + 1); my.now_time = 0; }  } public void insertNpc() { if (null == this.npc) return;  byte temp = 0; for (byte i = 1; i < this.npcList.length; i = (byte)(i + 1)) { for (byte j = i; j > 0; ) { short y0 = getNpcListY(j); short y1 = getNpcListY(j - 1); if (y0 <= y1) { if (y0 != y1 || (y0 == y1 && this.npcList[j - 1] == -1)) { temp = this.npcList[j]; this.npcList[j] = this.npcList[j - 1]; this.npcList[j - 1] = temp; }  j = (byte)(j - 1); }  }  }  } private short getNpcListY(int j) { short y; if (this.npcList[j] == -1) { y = (short)(this.my.y + this.dir_select[this.my.dir][1] * this.my.speed); } else if (this.npcList[j] < -1) { Ms.i(); y = (short)(this.item[Ms.abs(this.npcList[j]) - 2][1] * 20); } else { y = (this.npc[0][this.npcList[j]]).y; }  return y; } private void initMoveMy() { this.my.frame_c = this.my.frame_num; this.my.setActionNo(true); if (this.my.state == 0 && this.gr.monPro.length > 0) meetGrass();  } private void runMove() { if (this.my.ix != -1 || this.my.iy != -1) { runSeek(this.my, false); } else { runBoat(); }  oneMove(this.my, -1); byte j = 0; for (; j < 3; j = (byte)(j + 2)) { for (byte i = 0; i < (this.npc[j]).length; i = (byte)(i + 1)) { if ((this.npc[j][i]).other[4] == 1 || (this.npc[j][i]).other[4] == 2) { if ((this.npc[j][i]).other[8] >= 5) { runAutoMoveNpc(this.npc[j][i], i); } else if ((this.npc[j][i]).ix != -1 || (this.npc[j][i]).iy != -1) { runSeek(this.npc[j][i], true); }  oneMove(this.npc[j][i], i); }  }  }  insertNpc(); } private void runAutoMoveNpc(Npc npc, int i) { if (this.my.state == 1 && this.get_meet == i) { Ms.i(); npc.other[7] = (byte)(Ms.abs(npc.other[7]) / 3 * 3 + ((this.my.dir < 4) ? (this.my.dir - 1) : (this.my.dir - 2))); if (this.my.dir == 3) { npc.other[7] = (byte)-npc.other[7]; } else { npc.dir = 3; }  } else if (initAuto(npc, 10)) { if (npc.b_auto) { runAutoX(npc, this.gr.mapMove[(npc.other[8] - 5) * 2]); } else { runAutoY(npc, this.gr.mapMove[(npc.other[8] - 5) * 2 + 1]); }  }  } private boolean initAuto(Npc npc, int sleep) { if (npc.timeMove == 0) { if (npc.frame_c == -1) { Ms.i(); npc.b_auto = (Ms.getRandom(100) < 50); Ms.i(); if (Ms.getRandom(100) < 50) { Ms.i(); npc.timeMove = (byte)-(Ms.getRandom(sleep) + 20); npc.setActionNo(false); return false; }  } else { return false; }  } else if (npc.timeMove < 0) { npc.timeMove = (byte)(npc.timeMove + 1); return false; }  return true; } private boolean isAuto_canMove(Npc _npc, int sx, int sy) { if (checkWorld(_npc.x, _npc.y, sx, sy, false) == 1 || Ms.i().isRect(_npc.x + sx, _npc.y + sy, 19, 19, this.my.x, this.my.y, 19, 19)) return false;  return true; } private void runAutoY(Npc _npc, int w) { if (_npc.timeMove == 0 && _npc.frame_c == -1) { short t; Ms.i(); _npc.dir = (Ms.getRandom(100) < 50) ? 1 : 2; if (_npc.dir == 1) { t = (short)(_npc.y - _npc.other[1] * 20); } else { t = (short)((w + _npc.other[1]) * 20 - _npc.y); }  if (t <= 20) { _npc.timeMove = (byte)(t / _npc.speed); } else { Ms.i(); _npc.timeMove = (byte)(Ms.getRandom((t - 20) / _npc.speed + 1) + 20 / _npc.speed); }  } else if (_npc.timeMove > 0 && _npc.frame_c < 1) { if (isAuto_canMove(_npc, 0, (_npc.dir == 1) ? -_npc.speed : _npc.speed)) { _npc.setSpeedXY(0, (_npc.dir == 1) ? -_npc.speed : _npc.speed); _npc.timeMove = (byte)(_npc.timeMove - 1); _npc.frame_c = 1; _npc.setActionNo(true); } else { _npc.timeMove = -20; _npc.frame_c = -1; _npc.setActionNo(false); }  }  } private void runAutoX(Npc _npc, int w) { if (_npc.timeMove == 0) { short t; Ms.i(); _npc.dir = (Ms.getRandom(100) < 50) ? 3 : 4; if (_npc.dir == 3) { t = (short)(_npc.x - _npc.other[0] * 20); } else { t = (short)((w + _npc.other[0]) * 20 - _npc.x); }  if (t <= 20) { _npc.timeMove = (byte)(t / _npc.speed); } else { Ms.i(); _npc.timeMove = (byte)(Ms.getRandom((t - 20) / _npc.speed + 1) + 20 / _npc.speed); }  } else if (_npc.timeMove > 0 && _npc.frame_c < 1) { if (isAuto_canMove(_npc, (_npc.dir == 3) ? -_npc.speed : _npc.speed, 0)) { _npc.setSpeedXY((_npc.dir == 3) ? -_npc.speed : _npc.speed, 0); _npc.timeMove = (byte)(_npc.timeMove - 1); _npc.frame_c = 1; _npc.setActionNo(true); } else { _npc.timeMove = -20; _npc.frame_c = -1; _npc.setActionNo(false); }  }  } private void runSeek(Npc npc_, boolean b_who) { boolean bb = npc_.b_check; short x_ = (short)(npc_.ix * 20); short y_ = (short)(npc_.iy * 20); if (npc_.x == x_ && npc_.y == y_) { if (npc_.frame_c == -1) { npc_.ix = npc_.iy = -1; if (b_who) { npc_.setIxIy_npc(); } else { npc_.speed = getMySpeed(); }  if (npc_.bdir) { npc_.other[7] = npc_.lastAction; npc_.dir = (npc_.lastAction < 0) ? 4 : 3; npc_.setLastAction(false, 127); }  }  } else if (npc_.frame_c < 1) { if (bb) { bb = (npc_.x != x_); } else { bb = (npc_.y == y_); }  if (bb) { if ((npc_.x > x_ && npc_.x - npc_.speed < x_) || (npc_.x < x_ && npc_.x + npc_.speed > x_)) { npc_.setSpeedXY(x_ - npc_.x, 0); } else { npc_.setSpeedXY((npc_.x > x_) ? -npc_.speed : npc_.speed, 0); }  if (!npc_.bdir) npc_.dir = (npc_.x > x_) ? 3 : 4;  } else { if ((npc_.y > y_ && npc_.y - npc_.speed < y_) || (npc_.y < y_ && npc_.y + npc_.speed > y_)) { npc_.setSpeedXY(0, y_ - npc_.y); } else { npc_.setSpeedXY(0, (npc_.y > y_) ? -npc_.speed : npc_.speed); }  if (!npc_.bdir) npc_.dir = (npc_.y > y_) ? 1 : 2;  }  npc_.frame_c = npc_.frame_num; npc_.setActionNo(true); }  } private void drawSrcEffect() { this.srcFlash_c = (byte)(this.srcFlash_c - 1); if (this.srcFlash_c > 0 && this.srcFlash_c % 2 == 0) Ui.i().fillRect(16777215, 0, 0, 240, 320);  if (this.bSrc) { g.setColor(0); g.fillRect(0, this.black_width - 176 - this.bSrc_c, 240, 176); g.fillRect(0, 320 - this.black_width + this.bSrc_c, 240, 176); }  if (this.my.state == 22) drawBlackSRC(g, (this.eventGoing == 2) ? (320 - this.gr.about_d.length * 24 >> 1) : 45, (this.eventGoing == 2));  } public boolean initSrcEffect(int mode) { this.my.state = 10; switch (mode) { case 0: case 1: case 2: this.bSrc_c = 0; this.black_width = (short)((mode == 1) ? 176 : 0); this.bSrc = (mode == 1); return true;case 3: case 4: case 5: case 6: this.bSrc = true; if (this.bSrc_c == 0) if (mode == 6 || mode == 5) { this.bSrc_c = -40; this.black_width = (short)((mode == 5) ? 40 : 0); } else { this.black_width = this.bSrc_c = (short)((mode == 4) ? 176 : 40); }   this.bSrc_c = Ms.i().mathSpeedN(this.bSrc_c, 0, 12, false); if (this.bSrc_c == 0) { this.bSrc = (mode != 6); return true; }  break; }  return false; } private void initCloud() { if (this.mapNo == 24 || this.mapNo == 25 || this.mapNo == 26 || this.mapNo == 49 || this.mapNo == 50) { if (null == this.imgCloud) { this.imgCloud = Ms.i().createImage("data/cloud"); this.cloud = (short[][])null; this.cloud = new short[4][3]; }  for (byte i = 0; i < this.cloud.length; ) { addCloud(i); i = (byte)(i + 1); }  } else { this.cloud = (short[][])null; this.imgCloud = null; }  } private void drawCloud() { if (null == this.imgCloud) return;  byte i; for (i = 0; i < this.cloud.length; i = (byte)(i + 1)) Ui.i().drawImage(this.imgCloud, this.map_x + this.cloud[i][0], this.map_y + this.cloud[i][1], 0);  for (i = 0; i < this.cloud.length; i = (byte)(i + 1)) { if (!isNpcSrc(this.cloud[i][0], this.cloud[i][1])) { addCloud(i); } else { this.cloud[i][0] = (short)(this.cloud[i][0] - this.cloud[i][2]); }  }  } private void addCloud(int i) { Ms.i(); this.cloud[i][0] = (short)(this.leftCol * 20 + 240 + Ms.getRandom(120)); Ms.i(); this.cloud[i][1] = (short)(this.topRow * 20 + 20 + 2 + i * (Ms.getRandom(25) + 55)); Ms.i(); this.cloud[i][2] = (short)(Ms.getRandom(3) + 1); } private void drawMapRect(Graphics g) { byte i = 0; g.setColor(0); for (; i < this.gr.mapRect.length; i = (byte)(i + 1)) { if (!Ms.i().isRect(this.my.x, this.my.y, 19, 19, this.gr.mapRect[i][0] * 20, this.gr.mapRect[i][1] * 20, (this.gr.mapRect[i][2] - this.gr.mapRect[i][0] + 1) * 20, (this.gr.mapRect[i][3] - this.gr.mapRect[i][1] + 1) * 20)) for (byte j = 4; j < (this.gr.mapRect[i]).length; j = (byte)(j + 4)) g.fillRect(this.map_x + this.gr.mapRect[i][j] * 20, this.map_y + this.gr.mapRect[i][j + 1] * 20, (this.gr.mapRect[i][j + 2] - this.gr.mapRect[i][j] + 1) * 20, (this.gr.mapRect[i][j + 3] - this.gr.mapRect[i][j + 1] + 1) * 20);   }  } private void initBoat(int _select) { this.my.state = 9;
/* 3597 */     this.anole_type = 5;
/* 3598 */     setAnole();
/* 3599 */     this.boatSelect = (byte)_select;
/* 3600 */     this.gr.cur_a = 0;
/* 3601 */     this.my.setIXY(this.boatCourse[this.boatSelect][this.gr.cur_a], this.boatCourse[this.boatSelect][this.gr.cur_a + 1]); }
/*      */   
/*      */   private void runBoat() {
/* 3604 */     if (this.boatSelect == -1 || this.my.ix != -1 || this.my.iy != -1)
/* 3605 */       return;  this.my.state = 9;
/* 3606 */     if ((this.gr.cur_a = (byte)(this.gr.cur_a + 2)) >= (this.boatCourse[this.boatSelect]).length) {
/* 3607 */       this.boatSelect = -1;
/* 3608 */       this.my.state = 0;
/* 3609 */       this.anole_type = -1;
/* 3610 */       setAnole();
/*      */     } else {
/* 3612 */       this.checkType = checkIfOther(this.door, this.my.x, this.my.y);
/* 3613 */       if (this.checkType != -1) {
/* 3614 */         this.my.state = 5;
/* 3615 */         this.get_meet = this.checkType;
/*      */       } 
/* 3617 */       this.my.setIXY(this.boatCourse[this.boatSelect][this.gr.cur_a], this.boatCourse[this.boatSelect][this.gr.cur_a + 1]);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setMapMusic(boolean bb) {
/* 3622 */     Sound.i().setMusicId(this.gr.musicNo[this.mapNo]);
/* 3623 */     Sound.i().setMusic(bb);
/*      */   }
/*      */   
/*      */   public void eventCheck() {
/*      */     int i = 0;
/*      */     for (byte b = 0; this.event != null && b < this.event.length; b++) {
/*      */       if (this.event[b] != null && this.event_now[b] < (this.event[b]).length)
/*      */         do {
/*      */           boolean bool;
/*      */           byte[] arrayOfByte;
/*      */           byte[][] arrayOfByte1;
/*      */           this.event_state = 0;
/*      */           if (this.event_now[b] >= (this.event[b]).length) {
/*      */             this.mapInfo[this.mapNo * 2 + 1] = (short)(this.mapInfo[this.mapNo * 2 + 1] | 1 << b);
/*      */             break;
/*      */           } 
/*      */           switch (this.event[b][this.event_now[b]]) {
/*      */             case 28:
/*      */               runEvent_dialog(b);
/*      */               break;
/*      */             case 41:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 this.my.setXY(this.event[b][this.event_now[b] + 3], this.event[b][this.event_now[b] + 4], 0, 0);
/*      */                 this.my.frame_c = -1;
/*      */                 if (this.srcNpcNo == -1) {
/*      */                   this.map_x = map_set(this.event[b][this.event_now[b] + 3], this.cols, 12, 6, 240, 20);
/*      */                   this.map_y = map_set(this.event[b][this.event_now[b] + 4], this.rows, 16, 8, 320, 20);
/*      */                 } 
/*      */               } else {
/*      */                 this.now_eV1 = getNpcLayer(i);
/*      */                 this.now_eV2 = getNpcId(i);
/*      */                 (this.npc[this.now_eV1][this.now_eV2]).other[0] = this.event[b][this.event_now[b] + 3];
/*      */                 (this.npc[this.now_eV1][this.now_eV2]).other[1] = this.event[b][this.event_now[b] + 4];
/*      */                 this.npc[this.now_eV1][this.now_eV2].setXY_npc();
/*      */               } 
/*      */               this.event_now[b] = (short)(this.event_now[b] + 5);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 15:
/*      */               setEventNow(b, (this.event[b][this.event_now[b] + 2] == 0));
/*      */               break;
/*      */             case 4:
/*      */               bool = (this.gr.monInfoList[103] >= this.event[b][this.event_now[b] + 2]);
/*      */               ifEvent(b, 4, 16, this.event[b][this.event_now[b] + 3], bool);
/*      */               break;
/*      */             case 24:
/*      */               initBoat(this.event[b][this.event_now[b] + 2]);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 25:
/*      */               this.gr.overMode = (byte)(this.event[b][this.event_now[b] + 2] + 1);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               break;
/*      */             case 50:
/*      */               if (this.my.state == 24 || this.my.state == 25) {
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */                 nextEvent(0);
/*      */                 if (this.my.state == 24) {
/*      */                   this.gr.say("保存进度完成", 1);
/*      */                   this.gr.saveGame();
/*      */                 } 
/*      */                 this.my.state = 0;
/*      */                 break;
/*      */               } 
/*      */               if (this.my.frame_c == -1 && this.my.state != 23)
/*      */                 this.my.state = 23; 
/*      */               break;
/*      */             case 29:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 cur_npc = this.my;
/*      */               } else {
/*      */                 cur_npc = this.npc[getNpcLayer(i)][getNpcId(i)];
/*      */               } 
/*      */               cur_npc.setLastAction(false, this.event[b][this.event_now[b] + 4]);
/*      */               cur_npc.other[7] = this.event[b][this.event_now[b] + 3];
/*      */               cur_npc.now_action = 0;
/*      */               this.event_now[b] = (short)(this.event_now[b] + 5);
/*      */               nextEvent(4096);
/*      */               break;
/*      */             case 39:
/*      */               this.now_eV1 = this.event[b][this.event_now[b] + 2];
/*      */               this.now_eV2 = this.event[b][this.event_now[b] + 3];
/*      */               if ((this.gr.rmsOther[4 + this.now_eV1 * 2] & 1 << this.now_eV2) != 0) {
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */                 nextEvent(4113);
/*      */               } 
/*      */               break;
/*      */             case 42:
/*      */               this.now_eV1 = this.event[b][this.event_now[b] + 2];
/*      */               this.now_eV2 = this.event[b][this.event_now[b] + 3];
/*      */               Mg.i().go(this.gr, this.now_eV1, -1, this.now_eV2);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(4113);
/*      */               break;
/*      */             case 18:
/*      */               runEvent_item_add_del(b);
/*      */               break;
/*      */             case 49:
/*      */               if (this.gr.magic_id == -2) {
/*      */                 this.my.state = 10;
/*      */                 this.gr.magic_id = this.event[b][this.event_now[b] + 2];
/*      */                 this.gr.magic_x = this.map_x + this.event[b][this.event_now[b] + 3] * 20 + 10;
/*      */                 this.gr.magic_y = this.map_y + this.event[b][this.event_now[b] + 4] * 20 + 20;
/*      */                 this.gr.setMagic(this.event[b][this.event_now[b] + 2] / 5);
/*      */                 break;
/*      */               } 
/*      */               if (this.gr.magic_id == -1) {
/*      */                 this.gr.magic_id = -2;
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 5);
/*      */                 nextEvent(16);
/*      */               } 
/*      */               break;
/*      */             case 26:
/*      */               runEvent_getMon(b);
/*      */               break;
/*      */             case 27:
/*      */               if (this.eventGoing == 0) {
/*      */                 this.now_eV2 = this.gr.findMonsterMinLv(this.event[b][this.event_now[b] + 2], this.event[b][this.event_now[b] + 3]);
/*      */                 if (this.now_eV2 != -1) {
/*      */                   this.now_eV1 = this.gr.delMonster(this.now_eV2);
/*      */                   this.eventGoing = 1;
/*      */                   break;
/*      */                 } 
/*      */                 setEventNow(b, false);
/*      */                 break;
/*      */               } 
/*      */               if (this.gr.say_c == 0) {
/*      */                 if (this.now_eV1 == -1) {
/*      */                   setEventNow(b, false);
/*      */                   break;
/*      */                 } 
/*      */                 this.event_now[b] = (short)(this.event_now[b] + this.event[b][this.event_now[b] + 1] + 2);
/*      */                 nextEvent(4368);
/*      */               } 
/*      */               break;
/*      */             case 35:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 this.my.other[4] = this.event[b][this.event_now[b] + 3];
/*      */                 switch (this.event[b][this.event_now[b] + 3]) {
/*      */                   case 0:
/*      */                   case 2:
/*      */                     this.my.state = 0;
/*      */                     break;
/*      */                   case 1:
/*      */                   case 3:
/*      */                     this.my.state = 10;
/*      */                     break;
/*      */                 } 
/*      */               } else {
/*      */                 this.now_eV1 = getNpcLayer(i);
/*      */                 this.now_eV2 = getNpcId(i);
/*      */                 (this.npc[this.now_eV1][this.now_eV2]).other[4] = this.event[b][this.event_now[b] + 3];
/*      */               } 
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 32:
/*      */               runEvent_miniGame_myLevel(b);
/*      */               break;
/*      */             case 46:
/*      */               Sound.i().setMusicId(this.event[b][this.event_now[b] + 2]);
/*      */               Sound.i().setMusic(false);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 47:
/*      */               this.my.state = 10;
/*      */               if ((this.sleep_count = (byte)(this.sleep_count + 1)) == this.event[b][this.event_now[b] + 2]) {
/*      */                 this.sleep_count = 0;
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */                 nextEvent(17);
/*      */               } 
/*      */               break;
/*      */             case 52:
/*      */               this.my.state = 10;
/*      */               this.xxx = this.event[b][this.event_now[b] + 2];
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 16:
/*      */               runEvent_goShop(b);
/*      */               break;
/*      */             case 53:
/*      */               if (initSrcEffect(this.event[b][this.event_now[b] + 2])) {
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */                 nextEvent(16);
/*      */               } 
/*      */               break;
/*      */             case 13:
/*      */               if (this.eventGoing == 0) {
/*      */                 this.eventGoing = 1;
/*      */                 if (this.event[b][this.event_now[b] + 3] == 0) {
/*      */                   this.bMission[14] = this.event[b][this.event_now[b] + 2];
/*      */                   if (this.bMission[14] != -1)
/*      */                     this.gr.say("#4主线任务#0已更新，按#71键#0可查看任务表。", -1); 
/*      */                   break;
/*      */                 } 
/*      */                 setMission(this.event[b][this.event_now[b] + 2], (this.event[b][this.event_now[b] + 3] == 2));
/*      */                 if (this.event[b][this.event_now[b] + 3] == 2)
/*      */                   this.gr.say("#4分支任务#0已更新，按#73键#0可查看任务表。", -1); 
/*      */                 break;
/*      */               } 
/*      */               if (this.gr.say_c == 0) {
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */                 nextEvent(272);
/*      */               } 
/*      */               break;
/*      */             case 30:
/*      */               this.my.state = 10;
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 cur_npc = this.my;
/*      */               } else {
/*      */                 cur_npc = this.npc[getNpcLayer(i)][getNpcId(i)];
/*      */               } 
/*      */               cur_npc.setIXY(this.event[b][this.event_now[b] + 3], this.event[b][this.event_now[b] + 4]);
/*      */               cur_npc.speed = this.event[b][this.event_now[b] + 5];
/*      */               cur_npc.b_check = (this.event[b][this.event_now[b] + 6] == 0);
/*      */               cur_npc.setLastAction(false, 127);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 7);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 37:
/*      */               this.my.state = 10;
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 cur_npc = this.my;
/*      */               } else {
/*      */                 cur_npc = this.npc[getNpcLayer(i)][getNpcId(i)];
/*      */               } 
/*      */               cur_npc.setLastAction(true, this.event[b][this.event_now[b] + 6]);
/*      */               cur_npc.setIXY(this.event[b][this.event_now[b] + 3], this.event[b][this.event_now[b] + 4]);
/*      */               cur_npc.other[7] = this.event[b][this.event_now[b] + 5];
/*      */               if (this.event[b][this.event_now[b] + 5] < 0)
/*      */                 cur_npc.dir = 4; 
/*      */               cur_npc.speed = this.event[b][this.event_now[b] + 7];
/*      */               this.event_now[b] = (short)(this.event_now[b] + 8);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 23:
/*      */               this.now_eV1 = this.event[b][this.event_now[b] + 2];
/*      */               this.now_eV2 = this.event[b][this.event_now[b] + 3];
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               setRestEvent(this.now_eV1, this.now_eV2);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 17:
/*      */               this.now_eV1 = this.event[b][this.event_now[b] + 2];
/*      */               this.now_eV2 = this.event[b][this.event_now[b] + 3];
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               setOverEvent(this.now_eV1, this.now_eV2);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 22:
/*      */               exitBoss(this.event[b][this.event_now[b] + 2], this.event[b][this.event_now[b] + 3], this.event[b][this.event_now[b] + 4], this.event[b][this.event_now[b] + 5]);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 6);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 36:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               this.now_eV1 = getNpcLayer(i);
/*      */               this.now_eV2 = getNpcId(i);
/*      */               (this.npc[this.now_eV1][this.now_eV2]).other[5] = (byte)(this.event[b][this.event_now[b] + 3] + 1);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 2:
/*      */               this.now_eV1 = this.event[b][this.event_now[b] + 2];
/*      */               this.now_eV2 = this.event[b][this.event_now[b] + 3];
/*      */               ifEvent(b, 5, 4112, this.event[b][this.event_now[b] + 4], isMapEvent(this.now_eV1, this.now_eV2));
/*      */               break;
/*      */             case 45:
/*      */               runEvent_srcMove(b);
/*      */               break;
/*      */             case 48:
/*      */               this.my.state = 10;
/*      */               this.srcFlash_c = (byte)(this.event[b][this.event_now[b] + 2] << 1);
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 38:
/*      */               runEvent_brow(b);
/*      */               break;
/*      */             case 19:
/*      */               Ms.i();
/*      */               Ms.skip = 0;
/*      */               arrayOfByte = Ms.i().getStream("data/battle/boss.d", -1);
/*      */               arrayOfByte1 = Ms.i().create2Array(arrayOfByte);
/*      */               arrayOfByte = null;
/*      */               i = this.event[b][this.event_now[b] + 1] >> 1;
/*      */               this.gr.enemyList = new byte[i][5];
/*      */               this.now_eV2 = 0;
/*      */               while (this.now_eV2 < i) {
/*      */                 this.now_eV1 = this.event[b][this.event_now[b] + 2 + this.now_eV2 * 2];
/*      */                 this.gr.enemyList[this.now_eV2][0] = arrayOfByte1[this.now_eV1][0];
/*      */                 this.gr.enemyList[this.now_eV2][1] = this.event[b][this.event_now[b] + 3 + this.now_eV2 * 2];
/*      */                 this.gr.enemyList[this.now_eV2][2] = arrayOfByte1[this.now_eV1][1];
/*      */                 this.gr.enemyList[this.now_eV2][3] = arrayOfByte1[this.now_eV1][2];
/*      */                 this.gr.enemyList[this.now_eV2][4] = arrayOfByte1[this.now_eV1][3];
/*      */                 this.now_eV2 = (byte)(this.now_eV2 + 1);
/*      */               } 
/*      */               arrayOfByte1 = (byte[][])null;
/*      */               Sound.i().setMusicId(6);
/*      */               Sound.i().setMusic(false);
/*      */               this.gr.battleType(3);
/*      */               this.gr.goBattle();
/*      */               this.event_now[b] = (short)(this.event_now[b] + 2 + this.event[b][this.event_now[b] + 1]);
/*      */               break;
/*      */             case 6:
/*      */               bool = Ms.i().isRect(this.my.x, this.my.y, 19, 19, this.event[b][this.event_now[b] + 2] * 20, this.event[b][this.event_now[b] + 3] * 20, (this.event[b][this.event_now[b] + 4] - this.event[b][this.event_now[b] + 2] + 1) * 20, (this.event[b][this.event_now[b] + 5] - this.event[b][this.event_now[b] + 3] + 1) * 20);
/*      */               ifEvent(b, 7, 16, this.event[b][this.event_now[b] + 6], bool);
/*      */               break;
/*      */             case 1:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i != -1) {
/*      */                 this.now_eV1 = getNpcLayer(i);
/*      */                 this.now_eV2 = getNpcId(i);
/*      */                 bool = (this.my.state == 2 && this.get_meet == this.now_eV2);
/*      */                 ifEvent(b, 4, 16, this.event[b][this.event_now[b] + 3], bool);
/*      */               } 
/*      */               break;
/*      */             case 3:
/*      */               bool = (this.gr.money >= this.event[b][this.event_now[b] + 2] * 100);
/*      */               ifEvent(b, 4, 16, this.event[b][this.event_now[b] + 3], bool);
/*      */               break;
/*      */             case 5:
/*      */               i = this.gr.findItem(-2, this.event[b][this.event_now[b] + 2], true);
/*      */               if (this.event[b][this.event_now[b] + 3] == 0) {
/*      */                 bool = (i == 0);
/*      */               } else {
/*      */                 bool = (i >= this.event[b][this.event_now[b] + 3]);
/*      */               } 
/*      */               ifEvent(b, 5, 16, this.event[b][this.event_now[b] + 4], bool);
/*      */               break;
/*      */             case 8:
/*      */               bool = (this.gr.findMonster(this.event[b][this.event_now[b] + 2], this.event[b][this.event_now[b] + 3]) != -1);
/*      */               ifEvent(b, 5, 16, this.event[b][this.event_now[b] + 4], bool);
/*      */               break;
/*      */             case 12:
/*      */               runEvent_select(b);
/*      */               break;
/*      */             case 11:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               this.now_eV1 = getNpcLayer(i);
/*      */               this.now_eV2 = getNpcId(i);
/*      */               if ((this.npc[this.now_eV1][this.now_eV2]).other[8] == this.event[b][this.event_now[b] + 3]) {
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */                 nextEvent(16);
/*      */                 break;
/*      */               } 
/*      */               setEventNow(b, false);
/*      */               break;
/*      */             case 40:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 Ms.i();
/*      */                 this.my.other[7] = (byte)Ms.abs(this.event[b][this.event_now[b] + 3]);
/*      */                 if (this.event[b][this.event_now[b] + 3] < 0)
/*      */                   this.my.dir = 4; 
/*      */               } else {
/*      */                 cur_npc = this.npc[getNpcLayer(i)][getNpcId(i)];
/*      */                 cur_npc.other[7] = this.event[b][this.event_now[b] + 3];
/*      */                 cur_npc.setNpcNum(npcSp[cur_npc.other[3] - 1][cur_npc.other[2]].aLength());
/*      */               } 
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 43:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               this.now_eV1 = getNpcLayer(i);
/*      */               this.now_eV2 = getNpcId(i);
/*      */               (this.npc[this.now_eV1][this.now_eV2]).other[8] = this.event[b][this.event_now[b] + 3];
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 21:
/*      */               if (this.eventGoing == 0) {
/*      */                 this.eventGoing = 1;
/*      */                 i = this.event[b][this.event_now[b] + 2] * 100;
/*      */                 Ms.i();
/*      */                 if (i < 0 && this.gr.money < Ms.abs(i) - 100000) {
/*      */                   this.gr.say("金钱不足！", 0);
/*      */                   this.eventGoing = 2;
/*      */                 } 
/*      */                 if (this.eventGoing == 1) {
/*      */                   this.gr.money += i;
/*      */                   Ms.i();
/*      */                   this.gr.say(((i < 0) ? "失去：" : "获得：") + Ms.abs(i) + "金", 0);
/*      */                 } 
/*      */                 break;
/*      */               } 
/*      */               if (this.gr.say_c == 0) {
/*      */                 if (this.eventGoing == 2) {
/*      */                   setEventNow(b, true);
/*      */                   break;
/*      */                 } 
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */                 nextEvent(272);
/*      */               } 
/*      */               break;
/*      */             case 33:
/*      */               this.anole_type = (byte)(this.event[b][this.event_now[b] + 2] - 1);
/*      */               setAnole();
/*      */               this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */               nextEvent(16);
/*      */               break;
/*      */             case 51:
/*      */               switch (this.event[b][this.event_now[b] + 2]) {
/*      */                 case 0:
/*      */                   if (this.eventGoing == 0) {
/*      */                     this.gr.say(Ms.i().getDialogs(this.event[b], this.event_now[b] + 4, this.event[b][this.event_now[b] + 3]), -1);
/*      */                     this.eventGoing = 1;
/*      */                     break;
/*      */                   } 
/*      */                   if (this.gr.say_c == 0) {
/*      */                     this.event_now[b] = (short)(this.event_now[b] + (this.event[b][this.event_now[b] + 3] << 1) + 4);
/*      */                     nextEvent(272);
/*      */                   } 
/*      */                   break;
/*      */                 case 1:
/*      */                 case 2:
/*      */                   if (this.eventGoing == 0) {
/*      */                     this.my.state = 22;
/*      */                     this.black_0 = this.black_1 = 0;
/*      */                     this.gr.setStringB(Ms.i().getDialogs(this.event[b], this.event_now[b] + 4, this.event[b][this.event_now[b] + 3]), 202, 3);
/*      */                     this.eventGoing = this.event[b][this.event_now[b] + 2];
/*      */                     break;
/*      */                   } 
/*      */                   if (this.eventGoing == 3) {
/*      */                     this.event_now[b] = (short)(this.event_now[b] + (this.event[b][this.event_now[b] + 3] << 1) + 4);
/*      */                     nextEvent(272);
/*      */                     this.gr.about_d = null;
/*      */                     this.black_0 = -1;
/*      */                   } 
/*      */                   break;
/*      */               } 
/*      */               break;
/*      */             case 31:
/*      */               i = this.event[b][this.event_now[b] + 2] - 1;
/*      */               if (i == -1) {
/*      */                 this.now_eV1 = this.event[b][this.event_now[b] + 3];
/*      */                 this.my.dir = this.now_eV1;
/*      */                 this.my.setActionNo(false);
/*      */               } else {
/*      */                 this.now_eV1 = getNpcLayer(i);
/*      */                 if (this.now_eV1 == 0) {
/*      */                   this.now_eV2 = getNpcId(i);
/*      */                   (this.npc[this.now_eV1][this.now_eV2]).dir = this.event[b][this.event_now[b] + 3];
/*      */                   this.npc[this.now_eV1][this.now_eV2].setActionNo(false);
/*      */                 } 
/*      */               } 
/*      */               this.event_now[b] = (short)(this.event_now[b] + 4);
/*      */               nextEvent(4112);
/*      */               break;
/*      */             case 44:
/*      */               i = this.event[b][this.event_now[b] + 2];
/*      */               if (i == 0) {
/*      */                 bool = (this.gr.rmsSms[6] == 10);
/*      */                 ifEvent(b, 3, 16, 2, bool);
/*      */                 break;
/*      */               } 
/*      */               if (i == 1) {
/*      */                 SMSSender.i(this.gr).go(2, true);
/*      */                 this.event_now[b] = (short)(this.event_now[b] + 3);
/*      */                 nextEvent(0);
/*      */                 break;
/*      */               } 
/*      */               if (i == 2)
/*      */                 ifEvent(b, 3, 16, 2, (SMSSender.i(this.gr)).sms_b); 
/*      */               break;
/*      */           } 
/*      */         } while (this.event_state == 1); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/main/Map.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */