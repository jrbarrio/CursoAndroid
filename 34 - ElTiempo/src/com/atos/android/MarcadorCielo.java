package com.atos.android;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MarcadorCielo extends Overlay{
	private Bitmap iconoCielo;
	private Paint brocha;
	private Context contexto;
	
	private GeoPoint puntoGeografico;
	private Point puntoVisual;
										
	public MarcadorCielo(Context contexto, String icono, int latitud, int longitud) {
		this.contexto = contexto;
		puntoGeografico = new GeoPoint(latitud, longitud);
		brocha = new Paint();
		InputStream in = null;
		try{
			Log.i("","Intentando cargar "+icono);
			in = contexto.getAssets().open(icono);
			iconoCielo = BitmapFactory.decodeStream(in);
		} catch (IOException e) {
			Log.e("","Error de E/S",e);
		} finally {
			if (in != null) try { in.close(); } catch (IOException e) {}
		}
	
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		if (iconoCielo == null) return;
		puntoVisual = new Point();
		mapView.getProjection().toPixels(puntoGeografico, puntoVisual);
		canvas.drawBitmap(
				iconoCielo, 
				puntoVisual.x-iconoCielo.getWidth()/2, 
				puntoVisual.y-iconoCielo.getHeight()/2, 
				brocha
		);
	}
}
