package main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import dm.Translator;

public final class XConnection extends MIDlet {
   private MainCanvas gamecanvas = new MainCanvas(this);

   public XConnection() {
      Display.getDisplay(this).setCurrent(this.gamecanvas);
      this.gamecanvas.game_start();
   }

   public void startApp() {
   }

   public void pauseApp() {
   }

   public void destroyApp(boolean parm) {
      this.gamecanvas.game_stop();
      this.notifyDestroyed();
   }
}
