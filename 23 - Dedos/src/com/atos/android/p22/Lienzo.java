package com.atos.android.p22;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class Lienzo extends SurfaceView {

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
	
	private int color = Color.rgb(255, 100, 40);
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawARGB(255, 0, 0, 0);
		
		int accion = event.getActionMasked();
		if (accion == MotionEvent.ACTION_DOWN 
				|| accion == MotionEvent.ACTION_MOVE) {
			int punteros = event.getPointerCount();
			for (int i = 0; i < punteros; i++) {
				int id = event.getPointerId(i); 
				int indice = event.findPointerIndex(id);				
				float x = event.getX(indice);
				float y = event.getY(indice);
				
				Paint brocha = new Paint();
				brocha.setColor(color); 	
				canvas.drawRect(new RectF(x, y, x + 40, y + 40), brocha);
			}
		}
		
		
		getHolder().unlockCanvasAndPost(canvas);
		
		return true;
	}	
}
