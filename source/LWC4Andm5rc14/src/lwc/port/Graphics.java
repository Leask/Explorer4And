package lwc.port;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Graphics
{
  private Canvas target;
  private AFont font;

  public Graphics(Canvas g)
  {
    this(g,new AFont());
  }
  
  public Graphics(Canvas g, AFont p)  {
    target = g;
    font = p;
	font.setAntiAlias(false);
  }

  public void setTarget(Canvas g) {
    target = g;
  }

  public final AFont getFont () {
    return font;
  }

  public final FontMetrics getFontMetrics () {
    return new FontMetrics(font);
  }

  public final FontMetrics getFontMetrics (AFont f) {
    return new FontMetrics(f);
  }

  public final Color getColor () {
    return new Color (font.getColor());
  }

  public final void setColor (Color c) {
	font.setARGB(255, c.getRed(), c.getGreen(), c.getBlue());
	//font.setColor(c.getRGB());
  }

  public final void setFont (AFont f) {
    font=f;
  }

  public final void drawLine (int x1, int y1, int x2, int y2) {
    target.drawLine(x1, y1, x2, y2, font);
  }

  public final void drawRect (int x, int y, int w, int h) {
    target.drawRect(x, y, x+w, y+h, font);
  }
  
  public final void drawImage (Object img, int x, int y, Object observer) {
    //target.drawBitmap((Bitmap)img, x, y, font);//javax.microedition.lcdui.Graphics.TOP | javax.microedition.lcdui.Graphics.LEFT);
	  drawImage(img, x, y, ((Bitmap) img).width(), ((Bitmap)img).height(), null);
  }

  public final void drawImage (Object img, int x, int y, int w, int h, Object observer) {
    //target.drawBitmap((Bitmap)img, new Rect(x, y, x+w, y+h), new Rect(x, y, x+w, y+h), font);//javax.microedition.lcdui.Graphics.TOP | javax.microedition.lcdui.Graphics.LEFT);
		int ww=((Bitmap)img).getWidth(),hh=((Bitmap)img).getHeight();
		if (ww>w) w=ww;
		if (hh>h) h=hh;
		drawImage(img, x, y, x+w, y+h, 0, 0, w, h, null);
  }

  public final void drawImage(Object img, int dx1, int dy1, int dx2, int dy2,
                                     int sx1, int sy1, int sx2, int sy2, Object observer)
  {
    target.drawBitmap((Bitmap)img, new Rect(sx1, sy1, sx2, sy2), //javax.microedition.lcdui.game.Sprite.TRANS_NONE,
                           new Rect(dx1, dy1, dx2, dy2), font); //javax.microedition.lcdui.Graphics.TOP | javax.microedition.lcdui.Graphics.LEFT);
  }

  public final void drawArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
    target.drawArc(new RectF(x, y, x+w, y+h), startAngle, arcAngle, font);
  }

  public final void drawString(String str, int x, int y) {
    target.drawText(str, x, y, font);//javax.microedition.lcdui.Graphics.BASELINE | javax.microedition.lcdui.Graphics.LEFT);
  }

  public final void fillRect(int x, int y, int w, int h) {
	drawRect(x, y, w-1, h-1);
  }

  public final void clearRect(int x, int y, int w, int h) {
	setClip(x, y, w, h);
  }

  public final void fillArc(int x, int y, int w, int h, int startAngle, int arcAngle) {
    drawArc(x, y, w, h, startAngle, arcAngle);
  }
  
  public final void save()
  {
	target.save();
  };
  
  public final void restore()
  {
	target.restore();
  };
  
  public final void setClip(int x, int y, int w, int h) {
	target.clipRect(x, y, x+w, y+h);
  }

  public final void setClip(Rectangle r) {
	setClip(r.x, r.y, r.width, r.height);
  }

  public final void clipRect(int x, int y, int w, int h) {
    setClip(x, y, w, h);
  }

  public final Rectangle getClipBounds ()  {
	Rect r=target.getClipBounds();
    return new Rectangle(r.left, r.top, r.width(), r.height());
  }

  public final void translate (int dx, int dy) {
    target.translate (dx, dy);
  }
  
  public final void translate_ (int dx, int dy){
	  ;
  }

  public final int getClipX() {
    return target.getClipBounds().left;
  }

  public final int getClipY() {
    return target.getClipBounds().top;
  }

  public final int getClipWidth() {
    return target.getClipBounds().width();
  }

  public final int getClipHeight() {
    return target.getClipBounds().height();
  }
  
  public static final Bitmap createImage(int w, int h)
  {
	  return Bitmap.createBitmap(w, h, true);
  }

  public static final int getImageWidth(Object img)
  {
	  return ((Bitmap)img).width();
  }

  public static final int getImageHeight(Object img)
  {
	  return ((Bitmap)img).height();
  }
  
	public static final Bitmap alfImage(Object image,int alf) {
	    if(image == null)
	    	return null;
	    Bitmap src=(Bitmap)image;  
	    if (alf < 0)
	    	alf = 0;
	    else if(alf > 10)
	    	alf = 10;
	    int srcW = src.width();
	    int srcH = src.height();
	    int[] srcData = new int[srcW*srcH];
	    src.getPixels(srcData, 0, srcW, 0, 0, srcW, srcH);
	    int tmp = ((alf * 255 / 10) << 24) | 0x00ffffff;
	    for(int i=0;i<srcData.length;i++)
	    	srcData[i] &= tmp;
	    return Bitmap.createBitmap(srcData,srcW,srcH,Bitmap.Config.ARGB_8888);
	}

	public static final Bitmap grayImage(Object image) {
	    if(image == null)
	    	return null;
	    Bitmap src=(Bitmap)image;
	    int srcW = src.width();
	    int srcH = src.height();
	    int[] srcData = new int[srcW*srcH];
	    src.getPixels(srcData, 0, srcW, 0, 0, srcW, srcH);
	    int alf = 0;
	    int r = 0;
	    int g = 0;
	    int b = 0;
	    int gray = 0;
	    for(int i=0; i<srcData.length; i++) {
	    	alf = (srcData[i] >> 24) & 0xFF;
	    	r = (srcData[i] >> 16) & 0xFF;
	    	g = (srcData[i] >> 8) & 0xFF;
	    	b = srcData[i] & 0xFF;
	    	gray = (r*77+g*151+b*28 + 128)>>8;
	    	srcData[i] = (alf<<24)|(gray<<16)|(gray<<8)|gray;
	    }
	    return Bitmap.createBitmap(srcData, srcW, srcH, Bitmap.Config.ARGB_8888);
	}

	public static final Bitmap scaleImage(Object image, int dstW, int dstH) {
	    if(image == null)
	    	return null;
	    Bitmap src=(Bitmap)image;
	    int srcW = src.width();
	    int srcH = src.height();
	    if (srcH == dstH && srcW == dstW) return (Bitmap)image;
	    Bitmap buf1 = Bitmap.createBitmap(dstW, srcH, true);
	    Canvas bg = new Canvas(buf1);
	    int srcX = 0;
	    for(int x=0; x<dstW; x++) {//先横向扩展
	    	srcX = x*srcW/dstW;//求扩展后的某点X所对应的原图上的X
	    	bg.save();
	    	bg.clipRect(x, 0, 1, srcH);
	    	bg.drawBitmap(src, x-srcX, 0, null);
	    	bg.restore();
	    }
	    Bitmap buf2 = Bitmap.createBitmap(dstW,dstH,false);
	    bg = new Canvas(buf2);
	    int srcY = 0;
	    for(int y=0; y<dstH; y++) {
	    	srcY = y*srcH/dstH;
	    	bg.save();
	    	bg.clipRect(0, y, dstW, 1);
	    	bg.drawBitmap(buf1, 0, y-srcY, null);
	    	bg.restore();
	    }
	    return buf2;
	}   

	public static final Bitmap scaleImage4True(Object image, int dstW, int dstH, Rectangle rect) {
	    if(image == null)
	    	return null;
	    Bitmap src=(Bitmap)image;
	    int srcW = src.getWidth();
	    int srcH = src.getHeight();
	    if (srcH == dstH && srcW == dstW) return (Bitmap)image;
	    if (srcW > dstW) dstW = srcW;
	    if (srcH > dstH) dstH = srcH;
	    int[] srcData = new int[srcW*srcH];
	    src.getPixels(srcData, 0, srcW, 0, 0, srcW, srcH);
	    int[] dstData = new int[dstW*dstH];
	    int r,c,d,t;
	    for (r=0; r<srcH; r++) {
	    	t = r;
	    	if (r > srcH/2) t += dstH - srcH;
	    	for (c=0; c<srcW; c++)
	    		if (c<srcW/2)
	    			dstData[t*dstW+c] = srcData[r*srcW+c];
	    		else if (c==srcW/2) 
	    			for (d=0; d<dstW-srcW+1; d++)
	    				dstData[t*dstW+srcW/2+d] = srcData[r*srcW+srcW/2];
	    		else if (c>srcW/2 && c<srcW)
	    			dstData[dstW-srcW+t*dstW+c] = srcData[r*srcW+c];
	    	if (r==srcH/2)
	    		for (c=0; c<dstW; c++)
	    			for (t=0; t<dstH-srcH+1; t++)
	    				dstData[(r+t)*dstW+c] = dstData[r*dstW+c]; 
	    }
	    if (rect != null && rect.width != -1)
	    {
	    	for (r=rect.y; r<rect.y+rect.height; r++)
	    		for (c=rect.x; c<rect.x+rect.width; c++)
	    			dstData[r*dstW+c] = 0;
	    }
	    return Bitmap.createBitmap(dstData, dstW, dstH, Bitmap.Config.ARGB_8888);
	}
}

