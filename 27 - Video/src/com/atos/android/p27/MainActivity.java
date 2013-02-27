package com.atos.android.p27;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements OnVideoSizeChangedListener, OnPreparedListener{

	private MediaPlayer player;
	private SurfaceView lienzo;
	private SurfaceHolder surfaceHolder;
	private boolean tamanioConocido;
	private boolean informacionRecibida;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lienzo = (SurfaceView) findViewById(R.id.lienzo);
		surfaceHolder = lienzo.getHolder();		
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void iniciar(View v) {
		if (player != null) {
			return;
		}
		
		try {
			player = new MediaPlayer();
			player.setDataSource("/mnt/sdcard/familyguy.3gp");
			player.setDisplay(surfaceHolder);
			
			tamanioConocido = false;
			informacionRecibida = false;
			
			player.setOnVideoSizeChangedListener(this);
			player.setOnPreparedListener(this);
			
			player.prepare();
		} catch (Exception e) {
			Log.e("", e.getMessage());
			player = null;
			Toast t = Toast.makeText(this, "Error de E/S", Toast.LENGTH_SHORT);
			t.show();
		} 
	}
	
	public void detener(View v) {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		informacionRecibida = true;
		if (tamanioConocido) {
			iniciar();
		}
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		tamanioConocido = true;
		surfaceHolder.setFixedSize(width, height);
		
		if (informacionRecibida) {
			iniciar();
		}		
	}
	
	private void iniciar() {
		player.start();
	}
}
