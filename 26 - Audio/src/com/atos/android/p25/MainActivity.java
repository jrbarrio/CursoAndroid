package com.atos.android.p25;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable {

	private MediaPlayer player;
	private ProgressBar progreso;
	private TextView etiquetaProgreso;
	private Thread t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		progreso = (ProgressBar) findViewById(R.id.progreso);
		etiquetaProgreso = (TextView) findViewById(R.id.etiquetaProgreso);
	}

	@Override
	public void run() {
		while (t != null) {
			Thread.yield();
			synchronized (this) {
				if (player != null && player.isPlaying()) {
					final int posicion = player.getCurrentPosition();
					final int pc = 100 * (posicion / player.getDuration());
					runOnUiThread(new Runnable() {				
						@Override
						public void run() {
							progreso.setProgress(posicion);
							etiquetaProgreso.setText(pc + "%");
						}
					});
				}
			}
		}		
	}
	
	public void iniciar(View v) {
		if (player != null) {
			return;
		}
		
		player = MediaPlayer.create(this, R.raw.el_choclo);
		player.start();
		progreso.setMax(player.getDuration());
		progreso.setProgress(0);
		
		t = new Thread(this);
		t.start();
	}
	
	public void detener(View v) {
		if (player != null && player.isPlaying()) {
			player.stop();
			synchronized (this) {
				player = null;
				t = null;
			}
		}
	}
}
