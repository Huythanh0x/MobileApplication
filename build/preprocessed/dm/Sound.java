/*     */ package dm;
/*     */ import java.io.InputStream;
/*     */ import javax.microedition.media.Manager;
/*     */ import javax.microedition.media.Player;
/*     */ import javax.microedition.media.control.VolumeControl;
/*     */ 
/*     */ public final class Sound {
/*     */   private static Sound soundListener;
/*     */   private Player snd_music;
/*     */   private VolumeControl vc_snd;
/*     */   private boolean sound_play = false;
/*     */   private boolean sound_on = false;
/*     */   private byte play_music;
/*  14 */   private byte musicId = -1; private byte musicId_temp = -1; private byte loop_s; private byte volume = 30;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   public byte[] loop = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }; public Sound() {
/*  22 */     soundListener = this;
/*     */   } public static Sound i() {
/*  24 */     if (null == soundListener) soundListener = new Sound(); 
/*  25 */     return soundListener;
/*     */   }
/*     */   
/*  28 */   public void setSoundON(boolean sound_on_) { this.sound_on = sound_on_; }
/*  29 */   public boolean getSoundON() { return this.sound_on; }
/*  30 */   public void setMusicId(int musicId_) { this.musicId = (byte)musicId_; }
/*  31 */   public byte getMusicId() { return this.musicId; }
/*  32 */   public void setVolume(int volume_) { this.volume = (byte)volume_; } public byte getVolume() {
/*  33 */     return this.volume;
/*     */   }
/*     */   private Player createMusic(int id, int flag) {
/*  36 */     String[] tt_str = { "audio/midi", "audio/x-wav" };
/*     */     try {
/*  38 */       if (flag >= 0) {
/*  39 */         if (this.snd_music != null)
/*  40 */           this.snd_music.close(); 
/*  41 */         this.snd_music = null;
/*  42 */         InputStream input = getClass().getResourceAsStream("/music/" + id + ".mid");
/*  43 */         this.snd_music = Manager.createPlayer(input, tt_str[flag]);
/*     */         
/*  45 */         this.snd_music.prefetch();
/*  46 */         this.vc_snd = null;
/*  47 */         createVolume(this.volume);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  52 */     catch (Exception e) {
/*  53 */       e.printStackTrace();
/*     */     } 
/*  55 */     return this.snd_music;
/*     */   }
/*     */   
/*     */   public void setMusic(boolean b) {
/*  59 */     if (!this.sound_on || this.musicId < 0)
/*     */       return;  
/*  61 */     try { if (this.musicId_temp != this.musicId || b) {
/*  62 */         this.loop_s = this.loop[this.musicId];
/*  63 */         this.play_music = this.musicId;
/*  64 */         this.musicId_temp = this.play_music;
/*  65 */         if (this.snd_music != null)
/*  66 */           this.snd_music.close(); 
/*  67 */         this.snd_music = null;
/*  68 */         createMusic(this.play_music, 0);
/*     */       } 
/*  70 */       this.sound_play = true; }
/*  71 */     catch (Exception ex) { this.sound_play = false; }
/*     */   
/*     */   }
/*     */   public void soundStart() {
/*     */     try {
/*  76 */       this.snd_music.start();
/*  77 */     } catch (Exception ex) {
/*  78 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void soundPlay() {
/*  84 */     if (!this.sound_on || this.play_music < 0)
/*  85 */       return;  if (this.sound_play)
/*  86 */       if (this.loop_s == -1 && this.snd_music != null)
/*  87 */       { this.snd_music.setLoopCount(-1);
/*  88 */         soundStart();
/*  89 */         this.play_music = -1; }
/*  90 */       else if (this.loop_s > 0 && this.snd_music != null && this.snd_music.getState() != 400)
/*  91 */       { this.snd_music.setLoopCount(1);
/*  92 */         soundStart();
/*  93 */         this.loop_s = (byte)(this.loop_s - 1); }
/*  94 */       else if (this.loop_s == 0) { this.play_music = -1; }
/*     */        
/*     */   }
/*     */   
/*     */   void playTone() {}
/*     */   
/*     */   public void soundStop() {
/*     */     
/* 102 */     try { if (this.snd_music != null) this.snd_music.close();  }
/* 103 */     catch (Exception e)
/* 104 */     { e.printStackTrace(); }
/* 105 */     finally { this.sound_play = false; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void createVolume(int volume) {
/* 111 */     if (null == this.vc_snd)
/* 112 */       this.vc_snd = (VolumeControl)this.snd_music.getControl("VolumeControl"); 
/* 113 */     this.vc_snd.setLevel(volume);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyVolume(int mode) {
/* 121 */     if (mode == 0) {
/* 122 */       if ((this.volume = (byte)(this.volume + 30)) > 90) this.volume = 0; 
/* 123 */     } else if (mode == 1 && 
/* 124 */       Ms.i().key_Right()) {
/* 125 */       if ((this.volume = (byte)(this.volume + 30)) > 90) { this.volume = 0; }
/* 126 */       else if (Ms.i().key_Left() && (
/* 127 */         this.volume = (byte)(this.volume - 30)) < 0) { this.volume = 90; }
/*     */     
/* 129 */     }  if (this.volume == 0)
/* 130 */     { this.sound_on = false;
/* 131 */       soundStop(); }
/* 132 */     else { this.sound_on = true; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Sound.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */