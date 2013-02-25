package com.atos.android.p19;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Runnable {

	private Bitmap bicho;
	private SurfaceView pantalla;
	int x = 0;
	int y = 0;
	int vx = 10;
	int vy = 10;
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(40);
			} catch (Exception e) {
				// No hace nada
			}
			
			SurfaceHolder holder = pantalla.getHolder();
			Canvas c = holder.lockCanvas();
			if (c == null) {
				continue;
			}
			
			// Borrar la situacion anterior
			c.drawRGB(0, 0, 0);
			// Actualizar la posicion del bicho
			x += vx;
			y += vy;
			if (x < 0 || x > pantalla.getWidth()) {
				vx = -vx;
			}
			if (y < 0 || y > pantalla.getHeight()) {
				vy = -vy;
			}
			
			// Pintar nueva situacion
			c.drawBitmap(bicho, x, y, new Paint());
			holder.unlockCanvasAndPost(c);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		try {
			AssetManager assets = getAssets();
			InputStream is = assets.open("imagenes/bicho.png");
			bicho = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("MainActivity", "Error de E/S", e);
			bicho = Bitmap.createBitmap(64, 64, Config.ARGB_8888);
		}
	
		Thread t = new Thread(this);
		t.start();
		
		pantalla = (SurfaceView) findViewById(R.id.pantalla); 
	}
}
