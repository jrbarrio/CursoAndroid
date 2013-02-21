package com.atos.android.p17;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class Lienzo extends View {

	private int colorCirculo = Color.rgb(255, 100, 40);

	public Lienzo(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public Lienzo(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public Lienzo(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
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
		Typeface fuente = Typeface.createFromAsset(assets, "fuentes/E111VIVA.TTF");
		brocha.setTypeface(fuente);
		brocha.setTextSize(40);
		canvas.drawText("Hola Mundo", 0, 100, brocha);
	}
	
	public int getColorCirculo() {
		return colorCirculo;
	}
	
	public void setColor(int colorCirculo) {
		this.colorCirculo = colorCirculo;
		invalidate();
	}
}
