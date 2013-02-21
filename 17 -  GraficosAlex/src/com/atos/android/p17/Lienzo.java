package com.atos.android.p17;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
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
		canvas.drawARGB(255, 100, 50, 50);

		Paint brocha = new Paint();
		brocha.setColor(colorCirculo);
		
		canvas.drawOval(
				new RectF(0, 0, getWidth(), getHeight()), 
				brocha
		);
	}

	public int getColorCirculo() {
		return colorCirculo;
	}

	public void setColorCirculo(int colorCirculo) {
		this.colorCirculo = colorCirculo;
		invalidate();
	}
	
	
}
