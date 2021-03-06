package com.ahristov.diagrama;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class Lienzo extends View {
	
	private Map<String, Integer> datos =
		new HashMap<String, Integer>();
	private int[] colores = {
		Color.RED, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLUE,Color.WHITE
	};
	private static final int TAMA�O_TARTA = 100;
	private static final int TAMA�O_LEYENDA = 20;
	
	int total;
	float gradosPorUnidad;
	
	private Typeface font;
	public void setFont(Typeface font) {
		this.font = font;
	}
	

	public Lienzo(Context context) {
		super(context);
	}

	public Lienzo(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Lienzo(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setDatos(Map<String, Integer> datos)  {
		this.datos.clear();
		total = 0;
		for (String clave : datos.keySet() ) {
			int valor = datos.get(clave);
			total += valor;
			this.datos.put(clave,valor);
		}
		gradosPorUnidad = 360f/total;
	}
	

	@Override
	protected void onDraw(Canvas canvas) {

		// Borrar el dibujo 
		Paint p = new Paint();
		canvas.drawRGB(0,0,0);
		int anguloActual = 0;
		int i = 0;
		for (String clave : datos.keySet()) {
			int valor = datos.get(clave);
			int longitud = (int)(gradosPorUnidad*valor);
			p.setColor(colores[i]);
			canvas.drawArc(
					new RectF(0,0,TAMA�O_TARTA,TAMA�O_TARTA),
					anguloActual,
					longitud,
					true,
					p);
			anguloActual += longitud;
			i = (i+1) % colores.length;
			
			// Leyenda
			int y = TAMA�O_TARTA+TAMA�O_LEYENDA*i;
			canvas.drawRect(0,y,TAMA�O_LEYENDA,y+TAMA�O_LEYENDA-5,p);
			if (font != null) p.setTypeface(font);
			p.setTextAlign(Align.LEFT);
			p.setTextSize(TAMA�O_LEYENDA*3/4);
			p.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
			p.setARGB(255,255,255,255);
			canvas.drawText(clave, TAMA�O_LEYENDA+5, y+TAMA�O_LEYENDA-5, p);
			
		}
	}

}
