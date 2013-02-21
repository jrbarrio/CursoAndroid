package com.atos.android.p18;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Lienzo extends View {

	private int datos[] = {3, 5, 6, 7};
	private int colores[] = new int[4];
	
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
		
		int left = 120, top = 20, right = 140, bottom = 40;
		
		int suma = 0;
		for (int i = 0; i < datos.length; i++) {
			suma += datos[i];
		}
		
		float ocupado = 0;
		for (int i = 0; i < datos.length; i++) {
			Paint brocha = new Paint();
			int color = Color.rgb((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
			brocha.setColor(color); 
			colores[0] = color;
			
			float angulo;
			if ((i + 1) == datos.length) {
				angulo = 360 - ocupado;
			} else {
				angulo = (datos[i]*360)/suma;
			}
			
			canvas.drawArc(new RectF(0, 0, 100, 100), ocupado, angulo, true, brocha);
			ocupado += angulo;
			
			canvas.drawRect(new RectF(left, top + 40*i, right, bottom + 40*i), brocha);
			canvas.drawText(Integer.toString(datos[i]), left + 30, top + 10 + 40*i, brocha);
		}
	}
}
