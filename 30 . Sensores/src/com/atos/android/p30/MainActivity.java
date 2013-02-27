package com.atos.android.p30;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements SensorEventListener{

	private SensorManager manager;
	private Sensor acelerometro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
			manager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_FASTEST);
		}
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] datos = event.values;
		Log.i("", "Aceleracion x : " + datos[0]);
		Log.i("", "Aceleracion y : " + datos[1]);
		Log.i("", "Aceleracion z : " + datos[2]);
	}
}
