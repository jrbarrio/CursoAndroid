package com.atos.android.p17;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Lienzo extends View {

	private int colorCirculo = Color.rgb(255, 100, 40);

	public Lienzo(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public Lienzo(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Lienzo(Context context) {
		super(context);
	} 
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//canvas.drawARGB(255, 100, 50, 50);
		
		Paint brocha = new Paint();
		brocha.setColor(colorCirculo); 	
		//canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), brocha);
		//canvas.drawArc(new RectF(0, 0, getWidth(), getHeight()), 45, 30, true, brocha);
		//canvas.drawText("Hola", 100, 100, brocha);
		
//		brocha.setTypeface(Typeface.SERIF);
//		canvas.drawText("Hola Mundo", 0, 40, brocha);
//		brocha.setTypeface(Typeface.MONOSPACE);
//		canvas.drawText("Hola Mundo", 0, 80, brocha);
//		brocha.setTypeface(Typeface.SANS_SERIF);
//		canvas.drawText("Hola Mundo", 0, 120, brocha);
		
		AssetManager assets = getContext().getAssets();
//		Typeface fuente = Typeface.createFromAsset(assets, "fuentes/E111VIVA.TTF");
//		brocha.setTypeface(fuente);
//		brocha.setTextSize(40);
//		canvas.drawText("Hola Mundo", 0, 100, brocha);
		
		try {
			InputStream is = assets.open("images/android.png");
			Bitmap bmp = BitmapFactory.decodeStream(is);
			
			canvas.scale(0.5f, 0.5f);
			canvas.drawBitmap(bmp, 0, 0, brocha);
			canvas.drawBitmap(bmp, 50, 50, brocha);
			canvas.drawBitmap(bmp, 
					new Rect(0, 0, 60, 60),
					new RectF(0, 0, 30, 60),
					brocha);
		} catch (IOException e) {
			Log.e("Lienzo", "Error de lectura de android.png", e);
		}
	}
	
	public int getColorCirculo() {
		return colorCirculo;
	}
	
	public void setColor(int colorCirculo) {
		this.colorCirculo = colorCirculo;
		invalidate();
	}
}
