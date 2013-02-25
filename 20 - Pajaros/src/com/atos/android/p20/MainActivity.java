package com.atos.android.p20;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity implements Runnable {

	private Bitmap escenario;;
	private Bitmap pajaroDerecha;
	private Bitmap pajaroIzquierda;
	private Bitmap pajaro;
	private SurfaceView pantalla;
	int x = 0;
	int vx = 5;
	
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
			c.drawBitmap(escenario, 0, 0, new Paint());
			
			
			// Actualizar la posicion del pajaro
			x += vx;
			if (x <= 0) {
				girarDerecha();
				x = 0;
			}
			if (x > (pantalla.getWidth() - 40)) {
				girarIzquierda();
				x = pantalla.getWidth() - 40;
			}
			
			// Pintar nueva situacion
			Bitmap frame = Bitmap.createBitmap(pajaro, 40*(x%14), 0, 40, pajaro.getHeight());
			
			c.drawBitmap(frame, x, 0, new Paint());
			holder.unlockCanvasAndPost(c);
		}
	}

	private void girarIzquierda() {
		vx = -vx;	
		pajaro = pajaroIzquierda;
	}

	private void girarDerecha() {
		vx = -vx;	
		pajaro = pajaroDerecha;
	}
	
	public void girarIzquierda(View v) {
		girarIzquierda();
	}

	public void girarDerecha(View v) {
		girarDerecha();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		try {
			AssetManager assets = getAssets();
			InputStream is = assets.open("imagenes/pajaros/birdr.png");
			pajaroDerecha = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("MainActivity", "Error de E/S", e);
			pajaroDerecha = Bitmap.createBitmap(64, 64, Config.ARGB_8888);
		}
		
		try {
			AssetManager assets = getAssets();
			InputStream is = assets.open("imagenes/pajaros/birdl.png");
			pajaroIzquierda = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("MainActivity", "Error de E/S", e);
			pajaroIzquierda = Bitmap.createBitmap(64, 64, Config.ARGB_8888);
		}
		
		try {
			AssetManager assets = getAssets();
			InputStream is = assets.open("imagenes/pajaros/escenario.jpg");
			escenario = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			Log.e("MainActivity", "Error de E/S", e);
			escenario = Bitmap.createBitmap(64, 64, Config.ARGB_8888);
		}
		
		pajaro = pajaroDerecha;
	
		Thread t = new Thread(this);
		t.start();
		
		pantalla = (SurfaceView) findViewById(R.id.pantalla); 
	}

}
