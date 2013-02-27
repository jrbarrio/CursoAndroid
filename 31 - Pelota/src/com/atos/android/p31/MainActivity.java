package com.atos.android.p31;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {
	
	private SensorManager manager;
	private Sensor acelerometro;
	
	private float vx = 0;
	private float vy = 0;
	private float vz = 0;
	
	private long tiempoAnterior = System.currentTimeMillis();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(
			WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		setContentView(R.layout.activity_main);
		
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensores = manager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : sensores) {
			Log.i("", "Sensor: " + sensor.getName());
		}
		
		acelerometro = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		if (acelerometro != null) {
			manager.unregisterListener(this, acelerometro);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (acelerometro != null) {
			manager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_UI);
		}
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		long tiempoActual = System.currentTimeMillis();
		float segundos = (tiempoActual - tiempoAnterior) / 1000;
		
		float[] datos = event.values;
		float ax = datos[0];
		float ay = datos[1];
		float az = datos[2];
		
		float dx = vx + ((1/2) * ax * segundos * segundos);
		float dy = vy + ((1/2) * ay * segundos * segundos);
		float dz = vz + ((1/2) * az * segundos * segundos);
		
		vx += ax * segundos;
		vy += ay * segundos;
		vz += az * segundos;
		
		Log.i("", "Mover x: " + dx);
		Log.i("", "Mover y: " + dy);
		Log.i("", "Mover z: " + dz);
		
		tiempoAnterior = tiempoActual;
	}

}
