package com.atos.android.p28;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity implements PictureCallback {

	private Camera camara;
	private SurfaceView lienzo;
	private SurfaceHolder surfaceHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int numero = Camera.getNumberOfCameras();
		Log.i("", "Numero de camaras: " + numero);
		
		for (int i = 0; i < numero; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			Log.i("", "Camara " + i + " - Orientacion: " + info.orientation);
			if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
				Log.i("", "Camara " + i + " - Trasera");
			} else if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				Log.i("", "Camara " + i + " - Frontal");
			}				
		}
		
		lienzo  = (SurfaceView) findViewById(R.id.lienzo);
		surfaceHolder = lienzo.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		camara = Camera.open(0);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (camara != null) {
			camara.release();
			camara = null;
		}
	}
	
	public void previsualizar(View v) {
		try {
			camara.setPreviewDisplay(surfaceHolder);
			camara.startPreview();
			
			Display pantalla = getWindowManager().getDefaultDisplay();
			int orientacionPantalla = pantalla.getOrientation();
			
			switch (orientacionPantalla) {
			case Surface.ROTATION_0:
				Log.i("", "Orientacion 0º");
				break;
			case Surface.ROTATION_90:
				Log.i("", "Orientacion 90º");
				break;
			case Surface.ROTATION_180:
				Log.i("", "Orientacion 180º");
				break;
			case Surface.ROTATION_270:
				Log.i("", "Orientacion 270º");
				break;

			default:
				break;
			}
		} catch (IOException e) {
			Log.e("", "Error de E/S");
		}
	}
	
	public void hacerFoto(View v) {
		Camera.Parameters params = camara.getParameters();
		params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		camara.setParameters(params);
		
		camara.takePicture(null, null, this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		File imagen = new File(f, "foto.jpg");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(imagen);
			os.write(data);
		} catch (Exception e) {
			Log.e("", "Error de E/S");
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					// No hace nada
				}
			}
		}
		
		MediaScannerConnection.scanFile(
				this, 
				new String[] {imagen.getAbsolutePath()},
				null,
				null);
	}
}
