/*    */ package main;
/*    */ 
/*    */ import javax.microedition.lcdui.Display;
/*    */ import javax.microedition.lcdui.Displayable;
/*    */ import javax.microedition.midlet.MIDlet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class XConnection
/*    */   extends MIDlet
/*    */ {
/*    */   private MainCanvas gamecanvas;
/*    */   
/*    */   public XConnection() {
/* 17 */     this.gamecanvas = new MainCanvas(this);
/* 18 */     Display.getDisplay(this).setCurrent((Displayable)this.gamecanvas);
/* 19 */     this.gamecanvas.game_start();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startApp() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void pauseApp() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void destroyApp(boolean parm) {
/* 47 */     this.gamecanvas.game_stop();
/* 48 */     notifyDestroyed();
/*    */   }
/*    */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/main/XConnection.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */