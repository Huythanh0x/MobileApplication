package dm;

import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public final class Sound {
   private static Sound soundListener;
   private Player snd_music;
   private VolumeControl vc_snd;
   private boolean sound_play = false;
   private boolean sound_on = false;
   private byte play_music;
   private byte musicId = -1;
   private byte musicId_temp = -1;
   private byte loop_s;
   private byte volume = 30;
   public byte[] loop = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

   public Sound() {
      soundListener = this;
   }

   public static Sound i() {
      if (null == soundListener) {
         soundListener = new Sound();
      }

      return soundListener;
   }

   public void setSoundON(boolean sound_on_) {
      this.sound_on = sound_on_;
   }

   public boolean getSoundON() {
      return this.sound_on;
   }

   public void setMusicId(int musicId_) {
      this.musicId = (byte)musicId_;
   }

   public byte getMusicId() {
      return this.musicId;
   }

   public void setVolume(int volume_) {
      this.volume = (byte)volume_;
   }

   public byte getVolume() {
      return this.volume;
   }

   private Player createMusic(int id, int flag) {
      String[] tt_str = new String[]{"audio/midi", "audio/x-wav"};

      try {
         if (flag >= 0) {
            if (this.snd_music != null) {
               this.snd_music.close();
            }

            this.snd_music = null;
            InputStream input = this.getClass().getResourceAsStream("/music/" + id + ".mid");
            this.snd_music = Manager.createPlayer(input, tt_str[flag]);
            this.snd_music.prefetch();
            this.vc_snd = null;
            this.createVolume(this.volume);
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return this.snd_music;
   }

   public void setMusic(boolean b) {
      if (this.sound_on && this.musicId >= 0) {
         try {
            if (this.musicId_temp != this.musicId || b) {
               this.loop_s = this.loop[this.musicId];
               this.play_music = this.musicId;
               this.musicId_temp = this.play_music;
               if (this.snd_music != null) {
                  this.snd_music.close();
               }

               this.snd_music = null;
               this.createMusic(this.play_music, 0);
            }

            this.sound_play = true;
         } catch (Exception var3) {
            this.sound_play = false;
         }

      }
   }

   public void soundStart() {
      try {
         this.snd_music.start();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void soundPlay() {
      if (this.sound_on && this.play_music >= 0) {
         if (this.sound_play) {
            if (this.loop_s == -1 && this.snd_music != null) {
               this.snd_music.setLoopCount(-1);
               this.soundStart();
               this.play_music = -1;
            } else if (this.loop_s > 0 && this.snd_music != null && this.snd_music.getState() != 400) {
               this.snd_music.setLoopCount(1);
               this.soundStart();
               --this.loop_s;
            } else if (this.loop_s == 0) {
               this.play_music = -1;
            }
         }

      }
   }

   void playTone() {
   }

   public void soundStop() {
      try {
         if (this.snd_music != null) {
            this.snd_music.close();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         this.sound_play = false;
      }

   }

   private void createVolume(int volume) {
      if (null == this.vc_snd) {
         this.vc_snd = (VolumeControl)this.snd_music.getControl("VolumeControl");
      }

      this.vc_snd.setLevel(volume);
   }

   public void keyVolume(int mode) {
      if (mode == 0) {
         if ((this.volume = (byte)(this.volume + 30)) > 90) {
            this.volume = 0;
         }
      } else if (mode == 1 && Ms.i().key_Right()) {
         if ((this.volume = (byte)(this.volume + 30)) > 90) {
            this.volume = 0;
         } else if (Ms.i().key_Left() && (this.volume = (byte)(this.volume - 30)) < 0) {
            this.volume = 90;
         }
      }

      if (this.volume == 0) {
         this.sound_on = false;
         this.soundStop();
      } else {
         this.sound_on = true;
      }

   }
}
