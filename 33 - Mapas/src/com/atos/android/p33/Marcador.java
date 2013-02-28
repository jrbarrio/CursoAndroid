package com.atos.android.p33;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Marcador extends Overlay {
	private Bitmap bicho;
	private Paint brocha;
	private Context contexto;
	
	private GeoPoint puntoGeografico;
	private Point puntoVisual;
	
	public Marcador(Context contexto, int latitud, int longitud) {
		this.contexto = contexto;
		puntoGeografico = new GeoPoint(latitud, longitud);
		brocha = new Paint();
		bicho = BitmapFactory.decodeResource(contexto.getResources(), R.drawable.bicho);
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		puntoVisual = new Point();
		mapView.getProjection().toPixels(puntoGeografico, puntoVisual);
		canvas.drawBitmap(
				bicho, 
				puntoVisual.x - bicho.getWidth()/2, 
				puntoVisual.y - bicho.getHeight()/2,
				brocha);
	}

}
