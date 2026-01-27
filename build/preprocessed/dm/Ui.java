/*     */ package dm;
/*     */ 
/*     */ import com.nokia.mid.ui.DirectGraphics;
/*     */ import com.nokia.mid.ui.DirectUtils;
/*     */ import javax.microedition.lcdui.Graphics;
/*     */ import javax.microedition.lcdui.Image;
/*     */ import main.Constants_H;
/*     */ 
/*     */ public class Ui implements Constants_H
/*     */ {
/*     */   private static Ui uiListener; public static Graphics g; public static DirectGraphics dg; private byte[][] MIRROR2; private byte[] MIRROR3; private int[] MIRROR; public static Ui i() { if (null == uiListener)
/*     */       uiListener = new Ui();  return uiListener; } public void initGraphics(Graphics _g) { g = _g; dg = DirectUtils.getDirectGraphics(_g); } private static final short[] transforms = new short[] { 0, 5, 3, 6, 2, 4, 1, 7 }; private Image ui_img; private short[][] ui_modules; private byte rz; public byte cTriangle; public void drawRegion(Image image, int mx, int my, int mw, int mh, int realX, int realY, int anchor, int manipulation, Graphics g) { g.drawRegion(image, mx, my, mw, mh, transforms[manipulation], realX, realY, anchor); } public void drawModuleOne(Sprite sp, int m_id, int x, int y, int dir, int manipulation) { drawRegion(sp.img, sp.module(m_id, 0), sp.module(m_id, 1), sp.module(m_id, 2), sp.module(m_id, 3), x, y, this.MIRROR[dir], this.MIRROR2[dir][manipulation], g); } public void drawFrameOne(Sprite sp, int f_id, int x, int y, int dir) { short now_frame = 0; while (now_frame < sp.fLength(f_id)) {
/*     */       drawModuleOne(sp, sp.frame(f_id, now_frame, 0), x + sp.frame(f_id, now_frame, 1) * this.MIRROR3[dir], y + sp.frame(f_id, now_frame, 2), dir, sp.frame(f_id, now_frame, 3)); now_frame = (short)(now_frame + 1);
/*     */     }  } public boolean drawActionOne(Sprite sp, int a_id, int x, int y, byte[] now_a, int dir, boolean mode) { drawFrameOne(sp, sp.action(a_id, now_a[0], 0), x, y, dir); now_a[1] = (byte)(now_a[1] + 1); now_a[1] = 0;
/*     */     now_a[0] = (byte)(now_a[0] + 1);
/*     */     if ((byte)(now_a[1] + 1) > sp.action(a_id, now_a[0], 1) && (byte)(now_a[0] + 1) >= sp.aLength(a_id)) {
/*     */       if (!mode) {
/*     */         now_a[0] = (byte)(sp.aLength(a_id) - 1);
/*     */         return true;
/*     */       } 
/*     */       now_a[0] = 0;
/*     */     } 
/*     */     return false; } public void drawImage(Image image, int x, int y, int archor) { g.drawImage(image, x, y, archor); } public void drawImage(Image image, int x, int y, int archor, int trans) { if (trans == 0) {
/*     */       g.drawImage(image, x, y, archor);
/*     */     } else {
/*     */       dg.drawImage(image, x, y, archor, trans);
/*     */     }  } public void fillRect(int c, int x, int y, int w, int h) { g.setColor(c);
/*     */     g.fillRect(x, y, w, h); } public void drawLine(int c, int x0, int y0, int x1, int y1) { g.setColor(c);
/*  29 */     g.drawLine(x0, y0, x1, y1); } public Ui() { this.MIRROR2 = new byte[][] { { 0, 1, 2, 3, 4, 5, 6, 7 }, { 4, 5, 6, 7, 0, 1, 2, 3 } }; this.MIRROR3 = new byte[] { 1, -1 };
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.MIRROR = new int[] { 0x4 | 0x10, 0x8 | 0x10 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 578 */     this.cTriangle = 0; uiListener = this; }
/*     */   public void drawRect(int c, int x0, int y0, int w, int h) { g.setColor(c); g.drawRect(x0, y0, w, h); }
/*     */   public void fillArc(int c, int x, int y, int w, int h, int a0, int a1) { g.setColor(c); g.fillArc(x, y, w, h, a0, a1); }
/*     */   public void drawString(String str_hit, int x, int y, int anchor, int color, int mode) { int c0 = 13281366; int c1 = 3676168; y++; switch (color) { case 0: c0 = 0; c1 = 16777215; break;case 1: c0 = 5461075; c1 = 7727871; break;case 2: c0 = 2707928; c1 = 43016; break;case 3: c0 = 1908277; c1 = 24295; break;case 4: c0 = 1314307; c1 = 5228543; break;case 5: c0 = 2233603; c1 = 16739328; break;case 6: c0 = 8075577; c1 = 16189448; break;case 7: c0 = 4467457; c1 = 16757760; break;case 8: c0 = 10122756; c1 = 12583167; break;case 9: c0 = 11563064; c1 = 3676168; break;
/*     */       case 10: c0 = 1311269; c1 = 8816262; break; }  if (mode == 2) { g.setColor(c0); g.drawString(str_hit, x - 1, y, anchor); g.drawString(str_hit, x + 1, y, anchor); g.drawString(str_hit, x, y - 1, anchor); g.drawString(str_hit, x, y + 1, anchor); g.drawString(str_hit, x + 1, y + 1, anchor); g.drawString(str_hit, x - 1, y + 1, anchor); g.drawString(str_hit, x - 1, y - 1, anchor); g.drawString(str_hit, x + 1, y - 1, anchor); }  g.setColor(c1); if (mode == 1)
/*     */       g.drawString(str_hit, x + 1, y, anchor);  g.drawString(str_hit, x, y, anchor); }
/*     */   public void drawStringColor(String str, int x, int y, int color, int mode) { byte end, c = (byte)color, start = 0; short tw = 0; do { end = (byte)str.indexOf("#", start); if (end != -1) { String ssub = str.substring(start, end); drawString(ssub, x + tw, y, 0, c, mode); tw = (short)(tw + Ms.i().getStringWidth(ssub)); c = (byte)(Integer.parseInt(str.substring(end + 1, end + 2)) - 1); start = (byte)(end + 2); if (c == -1)
/*     */           c = (byte)color;  } else { drawString(str.substring(start, str.length()), x + tw, y, 0, c, mode); }  } while (end != -1); }
/*     */   public void drawStringY(StringBuffer[] showS, int x, int y, int fh, int color, int mode) { byte d; for (d = 0; d < showS.length; d = (byte)(d + 1))
/* 587 */       drawStringColor(showS[d].toString(), x, y + 4 + d * fh, color, mode);  } public void drawTriangle(int x, int y, int w, boolean dir, boolean ct) { if (dir) {
/* 588 */       Ms.i(); drawUi(30, x - w - Ms.abs(this.cTriangle), y, 0x4 | 0x2, 4);
/* 589 */       Ms.i(); drawUi(14, x + 6 - w - Ms.abs(this.cTriangle), y, 0x4 | 0x2, 0);
/* 590 */       Ms.i(); drawUi(30, x + w + Ms.abs(this.cTriangle), y, 0x8 | 0x2, 0);
/* 591 */       Ms.i(); drawUi(16, x - 6 + w + Ms.abs(this.cTriangle), y, 0x8 | 0x2, 0);
/*     */     } else {
/* 593 */       Ms.i(); drawUi(30, x, y - w - Ms.abs(this.cTriangle), 17, 3);
/* 594 */       Ms.i(); drawUi(12, x, y + 4 - w - Ms.abs(this.cTriangle), 17, 0);
/* 595 */       Ms.i(); drawUi(30, x, y + w + Ms.abs(this.cTriangle), 0x1 | 0x20, 1);
/* 596 */       Ms.i(); drawUi(18, x, y - 4 + w + Ms.abs(this.cTriangle), 0x1 | 0x20, 0);
/*     */     } 
/* 598 */     if (ct && (this.cTriangle = (byte)(this.cTriangle + 1)) > 4) this.cTriangle = -3;  } public void drawStringMid(StringBuffer[] showS, int x, int y, int fh, int color, int mode, int anchor) { byte d; for (d = 0; d < showS.length; d = (byte)(d + 1)) drawString(showS[d].toString(), x, y + d * fh, 17, color, mode);  } public void initUiModules() { this.ui_img = Ms.i().createImage("data/ui"); Ms.i(); Ms.skip = 0; this.ui_modules = Ms.i().createShort2Array(Ms.i().getStream("data/ui_m.d", -1), 0); } public void drawUi(int action, int x, int y, int anchor, int manipulation) { drawRegion(this.ui_img, this.ui_modules[action][0], this.ui_modules[action][1], this.ui_modules[action][2], this.ui_modules[action][3], x, y, anchor, manipulation, g); } public void drawBDir(int x, int y, int w, int h) { g.setColor(8310516); g.drawRect(x, y, w * 3, h << 1); g.drawRect(x + w, y, w, h << 1); g.drawLine(x, y + h, x + w * 3, y + h); }
/*     */   public void drawVolume(int x, int y, int v, boolean bb) { g.setColor(bb ? 458827 : 11563064); g.drawRect(x - 1, y + 5, 5, 8); g.drawRect(x + 5, y + 2, 5, 11); g.drawRect(x + 11, y - 1, 6, 14); g.setColor(bb ? 16048400 : 3676168); g.drawRect(x, y + 6, 4, 6); g.drawRect(x + 6, y + 3, 4, 9); g.drawRect(x + 12, y, 4, 12); if (v > 0) g.fillRect(x + 1, y + 7, 3, 5);  if (v > 30) g.fillRect(x + 7, y + 4, 3, 8);  if (v > 60) g.fillRect(x + 13, y + 1, 3, 11);  }
/*     */   public void drawK(int x, int y, int w, int h, int c) { if (c == 0) { c = 15400191; } else if (c == 1) { c = 12352252; } else if (c == 2) { c = 12313343; } else if (c == 3) { c = 5228543; } else if (c == 4) { c = 30975; } else { c = 11121884; }  g.setColor(c); g.fillRect(x + 1, y, w - 2, h); g.fillRect(x, y + 1, w, h - 2); }
/*     */   public void drawK0(int x, int y, int w, int h, int c) { byte c0 = (byte)c; if (c == 2) { c = 15400191; } else { c = 1908277; }  g.setColor(c); g.drawRect(x + 1, y, w - 3, h - 1); g.drawRect(x, y + 1, w - 1, h - 3); drawK(x + 1, y + 1, w - 2, h - 2, c0); }
/*     */   public void drawK1(int x, int y, int w, int h, int c) { g.setColor((c == 0) ? 5461075 : 1908277); g.drawRect(x, y + 2, w, h - 3); g.drawLine(x + 1, y + h, x + w - 1, y + h); if (c == 0 || c == 1) { c = 15400191; } else if (c == 2) { c = 13892607; } else if (c == 3) { c = 7727871; } else { c = 12352252; }  g.setColor(c); g.fillRect(x + 1, y, w - 2, h); g.fillRect(x, y + 1, w, h - 2); }
/*     */   public void drawK2(int x, int y, int w, int h, int arc) { if (arc == 0) { arc = 5; } else { arc = 20; }  g.setColor(0); g.fillRect(x, y, w, h); g.setColor(30975); g.fillRect(x + 2, y + 2, w - 4, h - 4); }
/*     */   public void drawK3(int x, int y, int w, int h) { drawK0(x, y, w - 1, h - 1, 0); g.setColor(1908277); g.fillRect(x + 2, y + 2, w - 2, h - 2); }
/* 605 */   public void drawYesNo(boolean a, boolean b) { if (a) drawUi(27, 0, 320, 36, 0); 
/* 606 */     if (b) drawUi(28, 240, 320, 40, 0);  } public void drawK4(int x, int y, int w, int h) { g.setColor(16771373); g.drawLine(x + 1, y, x + w - 2, y); g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1); g.drawLine(x, y + 1, x, y + h - 2); g.drawLine(x + w - 1, y + 1, x + w - 1, y + h - 2); g.drawLine(x, y + 1, x + 1, y + 1); g.drawLine(x + w - 2, y + 1, x + w - 1, y + 1); g.drawLine(x, y + h - 2, x + 1, y + h - 2); g.drawLine(x + w - 2, y + h - 2, x + w - 2, y + h - 2); }
/*     */   public void drawKuang(int x, int y, int w, int h) { g.setColor(1908277); g.drawRect(x + 1, y, w - 4, h - 2); g.drawRect(x + 2, y + 1, w - 4, h - 2); g.drawLine(x, y + 1, x, y + h - 3); g.drawLine(x + w - 1, y + 2, x + w - 1, y + h - 2); fillRect(15400191, x + 1, y + 1, w - 3, h - 3); }
/*     */   public void drawKuangSelect(int x, int y, int w, int h, int mode) { int color = 13281366; if (mode == 1) { color = 11370036; }
/*     */     else if (mode == 2) { color = 2233088; }
/*     */     else if (mode == 3) { color = 15129744; }
/*     */     else if (mode == 4) { color = 16762112; }
/*     */     else if (mode == 5) { color = 6168835; }
/*     */      g.setColor(color); g.fillRect(x + 1, y, w - 2, h); g.drawRect(x, y + 1, w - 1, h - 3); }
/*     */   public void drawKuangMap(int x, int y, int w, int h, boolean mode) { fillRect(mode ? 14961 : 16762112, x, y, w, h); fillRect(mode ? 6724095 : 11370036, x + 1, y + 1, w - 2, h - 2); }
/*     */   public void drawKuangAdorn(int x, int y, int w, int h, int mode) { if (mode == 0) { g.setColor(1842484); g.drawRect(x + 20, y - 22, w - 21, 20); g.drawRect(x + 20, y - 22, w - 22, 21); fillRect(15129744, x + 20, y - 22, w - 22, 20); drawUi(33, x, y, 36, 0); drawUi(31, x + w - 4, y, 36, 0); }
/*     */     else if (mode == 1) { drawUi(32, x, y - 7, 0, 0); drawUi(32, x + w + 5, y - 7, 0x8 | 0x10, 0); fillRect(13550445, x + 15, y - 5, w - 25, 13); fillRect(16116383, x + 15, y - 3, w - 25, 10); fillRect(13078326, x + 15, y + 10, w - 25, 5); drawLine(1842484, x + 15, y - 7, x + w - 11, y - 7); drawLine(16446403, x + 15, y - 6, x + w - 11, y - 6); drawLine(6168835, x + 15, y + 12, x + w - 11, y + 12); g.setColor(13281366); g.drawRect(x + 15, y + 8, w - 26, 1); }
/*     */     else if (mode == 2) { drawUi(32, x, y + h - 1, 36, 6); drawUi(32, x + w + 5, y + h - 1, 40, 6); fillRect(13550445, x + 15, y + h - 16, w - 25, 13); fillRect(16116383, x + 15, y + h - 15, w - 25, 10); fillRect(13078326, x + 15, y + h - 23, w - 25, 5); drawLine(1842484, x + 15, y + h - 2, x + w - 11, y + h - 2); drawLine(16446403, x + 15, y + h - 3, x + w - 11, y + h - 3); drawLine(6168835, x + 15, y + h - 21, x + w - 11, y + h - 21); g.setColor(13281366); g.drawRect(x + 15, y + h - 18, w - 26, 1); }
/*     */      }
/*     */   public void drawBarOne(int x, int y, int w, int cMin, int cMax, int mode) { int c0 = 16125468, c1 = 16752385, h = 5; if (mode == 1) { c0 = 55551; }
/*     */     else if (mode == 2) { c0 = 60177; }
/*     */      fillRect(4067649, x + 1, y, w - 1, h); g.drawRect(x, y + 1, w, h - 3); h -= 2; fillRect(c1, x + 1, y + 1, cMin, h); fillRect(c0, x + 1, y + 1, (cMin > cMax) ? cMax : cMin, h); }
/*     */   public void sliding(int x, int y, int h, int now, int all, boolean type) { byte w = 10; if (!type)
/*     */       drawK(x - 2, y - 2, w + 4, h + 4, 0);  drawK(x, y, w, h, type ? 0 : 3); drawK(x, y, w, 11, type ? 2 : 4); drawK(x, y + h - 11, w, 11, type ? 2 : 4); drawUi(12, x + 1, y + 1, 0, 0); drawUi(18, x + 1, y + h - 10, 0, 0); h -= 22; all--; if (all < 1) { drawK(x + 1, y + 12, w - 2, h - 2, type ? 3 : 0); }
/*     */     else { byte kh = (byte)(h / (all + 1)); if (kh < 15)
/*     */         kh = 15;  short k2 = (short)((h - 2 - kh) * now / all); drawK(x + 1, y + 12 + k2, w - 2, kh, type ? 3 : 0); }
/*     */      }
/*     */   public void drawNum(String str, int x, int y, int anchor, int type) { if (str.length() == 0)
/*     */       return;  byte i = (byte)((anchor == 0) ? 0 : (str.length() - 1)); short sw = 0; while (true) {
/*     */       byte b; if (type == 0) {
/*     */         b = 8;
/*     */       } else if (type == 1) {
/*     */         b = 10;
/*     */       } else {
/*     */         b = 14;
/*     */       }  byte bid = -1; char cr = str.charAt(i); if (cr >= '0' && cr <= '9') {
/*     */         bid = (byte)(cr - 48); if (type == 0) {
/*     */           bid = (byte)(bid + 10);
/*     */         } else if (type == 1) {
/*     */           bid = (byte)(bid + 35);
/*     */         } else {
/*     */           bid = (byte)(bid + 57);
/*     */         } 
/*     */       } else if (cr == '/') {
/*     */         bid = 20;
/*     */       } else if (cr == '-') {
/*     */         bid = (byte)((type == 2) ? 56 : 46);
/*     */       } else if (cr == '+') {
/*     */         bid = 47;
/*     */       } else if (cr == '%') {
/*     */         bid = 45; b = 14;
/*     */       } else if (cr == '级') {
/*     */         bid = 29; b = 12;
/*     */       }  if (bid != -1) {
/*     */         if (anchor == 0) {
/*     */           drawUi(bid, x + sw, y, 36, 0);
/*     */         } else {
/*     */           drawUi(bid, x - sw, y, 40, 0);
/*     */         }  sw = (short)(sw + b);
/*     */       } else {
/*     */         if (anchor == 0) {
/*     */           drawString("" + cr, x + sw, y + 5, 36, 3, 0);
/*     */         } else {
/*     */           drawString("" + cr, x - sw, y + 5, 40, 3, 0);
/*     */         }  sw = (short)(sw + 19);
/*     */       }  if (anchor == 0) {
/*     */         i = (byte)(i + 1);
/*     */       } else {
/*     */         i = (byte)(i - 1);
/*     */       }  if ((anchor == 0) ? (i < str.length()) : (i > -1))
/*     */         continue;  break;
/*     */     }  }
/*     */   public void drawRectZ(int color, int x, int y, int w, int h, int s) { g.setColor(color); Ms.i(); Ms.i(); Ms.i(); Ms.i(); g.drawRect(x - Ms.abs(this.rz), y - Ms.abs(this.rz), w + (Ms.abs(this.rz) << 1), h + (Ms.abs(this.rz) << 1)); if ((this.rz = (byte)(this.rz + 1)) > s)
/*     */       this.rz = (byte)-s;  }
/*     */   public void fillRectB() { fillRect(5422575, 0, 0, 240, 320); }
/* 675 */   public void drawListSY(Object[] menu, int x, int y, int w, int sh, int sel, int c0, int c1, int strT) { byte d; for (d = 0; d < menu.length; d = (byte)(d + 1))
/* 676 */       drawString(menu[d].toString(), x + (w >> 1), y + 2 + d * sh, 17, (d == sel) ? c0 : c1, strT);  }
/*     */    public void drawListKY(int length, int x, int y, int w, int kw, int kh, int dis, int sel, int c0, int c1) {
/*     */     byte d;
/* 679 */     for (d = 0; d < length; d = (byte)(d + 1)) {
/* 680 */       if (d != sel) drawK0(x + kw, y + d * (kh + dis), w - (kw << 1), kh, c1); 
/* 681 */     }  if (sel != -1) drawK1(x, y + sel * (kh + dis), w, kh, c0); 
/*     */   } public void drawStringY(Object[] str, int x, int y, int start, int end, int sh, int c, int strT) {
/*     */     byte i;
/* 684 */     for (i = (byte)start; i < end && i < str.length; i = (byte)(i + 1))
/* 685 */       i().drawString(str[i].toString(), x, y + sh * (i - start), 0, c, strT); 
/*     */   }
/*     */ }


/* Location:              /home/kasm-user/Downloads/宠物王国5 彩虹 (BT版)_N73.jar!/dm/Ui.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */