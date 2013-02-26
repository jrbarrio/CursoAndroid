package com.atos.android.p25;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void escribirInterno(View v) {
		File directorioInterno = getFilesDir();
		Log.i("", "Directorio interno: " + directorioInterno.getAbsolutePath());
		FileOutputStream out = null;
		try {
			out = openFileOutput("datos.txt", Context.MODE_APPEND);
			out.write("Hola mundo!\n".getBytes());
		} catch (IOException e) {
			Toast t = Toast.makeText(this, "Error de E/S", Toast.LENGTH_SHORT);
			t.show();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// No hace nada
				}
			}
		}
	}
	
	public void leerInterno(View v) {
		FileInputStream in = null;
		try {
			in = openFileInput("datos.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			Log.i("", "Inicio de archivo");
			while (true) {
				String s = br.readLine();
				if (s == null) {
					break;
				}
				Log.i("", "Linea leida " + s); 
			}
			Log.i("", "Fin de archivo");
		} catch (IOException e) {
			Toast t = Toast.makeText(this, "Error de E/S", Toast.LENGTH_SHORT);
			t.show();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// No hace nada
				}
			}
		}
	}
	
	public void escribirExterno(View v) {
		String estado = Environment.getExternalStorageState();
		if (!estado.equals(Environment.MEDIA_MOUNTED)) {
			Toast t = Toast.makeText(this, "Tarjeta SD no disponible", Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		
		File directorioImagenes = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if (!directorioImagenes.exists()) {
			directorioImagenes.mkdirs();
		}
		
		Bitmap bmp = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		
		Canvas c = new Canvas(bmp);
		c.drawRGB(0, 0, 0);
		Paint p = new Paint();
		p.setColor(Color.RED);
		c.drawOval(new RectF(0, 0, 100, 100), p);
		
		File archivo = new File(directorioImagenes, "imagen.png");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(archivo);
			bmp.compress(CompressFormat.PNG, 100, out);
			Toast t = Toast.makeText(this, "Archivo generado", Toast.LENGTH_SHORT);
			t.show();
		} catch (IOException e) {
			Toast t = Toast.makeText(this, "Error de E/S " + e.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// No hace nada
				}
			}
		}
		
		MediaScannerConnection.scanFile(this, 
				new String[] { archivo.getAbsolutePath() }, 
				new String[] {"image/png"}, 
				new OnScanCompletedListener() {					
					@Override
					public void onScanCompleted(String path, Uri uri) {						
					}
				});
	}
	
	public void leerExterno(View v) {
		File directorioImagenes = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		FileInputStream in = null;
		Bitmap imagen = null;
		try {
			File archivo = new File(directorioImagenes, "imagen.png");
			in = new FileInputStream(archivo);
			imagen = BitmapFactory.decodeStream(in);
			Log.i("", "Imagen leida: " + imagen.getWidth() + "X" + imagen.getHeight());
		} catch (IOException e) {
			Log.e("", "Error de E/S");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// No hace nada
				}
			}
		}
	}
}
