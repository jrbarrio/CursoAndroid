package com.atos.android.p22;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

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
	public boolean onTouchEvent(MotionEvent event) {
		int accion = event.getActionMasked();
		if (accion == MotionEvent.ACTION_DOWN) {
			// ...
		}
		int punteros = event.getPointerCount();
		int id = event.getPointerId(3); // id del cuarto puntero
		int indice = event.findPointerIndex(28);
		
		//int tipo = event.getToolType(indice);
			
		event.getX(indice);
		event.getY(indice);
		event.getPressure(indice);
		
		//event.getAxisValue(int eje, indice);
		//MotionEvent.AXIS_xxxxx
		
		//event.getAxisValue(MotionEvent.AXIX_X, 1); <==> event.getX(1);
		//event.getAxisValue(MotionEvent.AXIX_Y, 1); <==> event.getY(1);
		//event.getAxisValue(MotionEvent.AXIX_PRESSURE, 1); <==> event.getPressure(1);
		
		int numeroDatosHistoricos = event.getHistorySize();
		float x = event.getHistoricalX(indice, 1);
		float y = event.getHistoricalY(indice, 1);
		float p = event.getHistoricalPressure(indice, 1);
		
		return true;
	}
}
