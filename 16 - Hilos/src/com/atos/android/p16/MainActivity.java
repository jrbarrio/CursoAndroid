package com.atos.android.p16;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	public int i = 0;
	public ProgressBar progreso;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
		StrictMode.setThreadPolicy(policy);
		
		progreso = (ProgressBar) findViewById(R.id.progreso);
	}

	public void iniciarHilo(View v) {
		i = 0;
		progreso.setProgress(0);
		
		Runnable r = new Runnable() {			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					i++;
					if (i > 100) break;
					
					runOnUiThread(
							new Runnable() {
								
								@Override
								public void run() {
									progreso.setProgress(i);									
								}
							});
					
					
				}				
			}
		};
		
		Thread t = new Thread(r);
		t.start();
	}
}
