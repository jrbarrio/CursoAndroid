package com.planetalia.multitouch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LienzoMultiTouch extends View {
	private Map<Integer, Puntero> punteros = new HashMap<Integer,Puntero>();
	private Stack<Integer> coloresDisponibles = new Stack<Integer>();
	Paint brocha = new Paint();
	
	{
		coloresDisponibles.push(Color.GREEN);
		coloresDisponibles.push(Color.BLUE);
		coloresDisponibles.push(Color.YELLOW);
		coloresDisponibles.push(Color.CYAN);
		coloresDisponibles.push(Color.MAGENTA);
	}

	public LienzoMultiTouch(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LienzoMultiTouch(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LienzoMultiTouch(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (Puntero p : punteros.values()) {
			if (p.x == 0) continue;
			brocha.setColor(p.color);
			canvas.drawCircle(p.x,p.y,p.radio, brocha);
		}
		
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_MOVE) return true;
		
		// Recorrer todos los punteros que hay activos
		for (int i = 0; i < event.getPointerCount(); i++) {
			
			// Recuperar el id del puntero correspondiente
			int id = event.getPointerId(i);
			
			// Recuperar la información de ese puntero en base a su id
			Puntero p = punteros.get(id);
			
			// Si no recuperamos nada, se trata de un nuevo puntero
			if (p == null) {
				// Quedarse con su id, asignarle un color y añadirlo al mapa
				p = new Puntero();
				p.color = coloresDisponibles.pop();
				punteros.put(id,p);
			}
			
			// Guardar sus datos
			p.x = (int)event.getX(i);
			p.y = (int)event.getY(i);
			p.radio = (int)(event.getSize(i)*10)+30;
		}
		
		// Borrar todos los punteros que hayan desaparecido
		
		Iterator<Integer> it = punteros.keySet().iterator();
		while (it.hasNext()) {
			int id = it.next();
			if (event.findPointerIndex(id) == -1) {
				// Este puntero ha desaparecido, borrarlo de la lista
				// y recuperar su color para uso futuro
				Puntero p = punteros.get(id);
				coloresDisponibles.push(p.color);
				it.remove();
			}
		}
		
		
		// Notificar que queremos un refresco del componente
		invalidate();
		return true;
	}

}
