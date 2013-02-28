package com.planetalia.android.gravedad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity implements Runnable,SensorEventListener{
	private SensorManager manager;
	private Sensor gravedad;
	private double ax,ay;
	private SurfaceView surfaceView;
	private SurfaceHolder holder;
	private Paint brocha;
	private int centroX,centroY;
	private Thread t;
	private int bichox=-1;
	private int bichoy=-1;
	private int vx = 0;
	private int vy = 0;
	private Bitmap bicho;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView)findViewById(R.id.lienzo);
        holder = surfaceView.getHolder();
        brocha = new Paint();
        brocha.setColor(Color.RED);
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        gravedad = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        t = new Thread(this);
        t.start();
        bicho = BitmapFactory.decodeResource(getResources(), R.drawable.bicho);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if (gravedad != null)
    		manager.registerListener(this, gravedad, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	if (gravedad != null)
    		manager.unregisterListener(this,gravedad);
    }
    
    public void run() {
    	while (true) {
    		try { Thread.sleep(50); } catch (InterruptedException e) {}
    		Canvas c = holder.lockCanvas();
    		if (c == null) continue;
    		
    		
    		if (bichox == -1) {
    	        bichox = surfaceView.getWidth()/2;
    	        bichoy= surfaceView.getHeight()/2;
    		}
            centroX = surfaceView.getWidth()/2;
            centroY = surfaceView.getHeight()/2;
    		c.drawRGB(255, 255, 255);
    		
    		//Pintar la pelota
    		c.drawBitmap(bicho,bichox,bichoy,brocha);
    		vx = -(int)Math.round(ax*5);
    		vy = (int)Math.round(ay*5);
    		bichox += vx;
    		bichoy += vy;
    		if (bichox < 0)
    			bichox = 0;
    		if (bichoy < 0)
    			bichoy = 0;
    		if (bichox +bicho.getWidth() > surfaceView.getWidth())
    			bichox = surfaceView.getWidth()-bicho.getWidth();
    		if (bichoy + bicho.getHeight() > surfaceView.getHeight())
    			bichoy = surfaceView.getHeight() - bicho.getHeight();
    		
    		
    		int longx = -(int)Math.round(ax*5);
    		int longy = (int)Math.round(ay*5);
    		c.drawLine(centroX, centroY, centroX+longx, centroY, brocha);
    		c.drawLine(centroX, centroY, centroX, centroY+longy, brocha);
    		c.drawLine(0,0,10,10,brocha);
    		
    		holder.unlockCanvasAndPost(c);
    	}
    }

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		ax = event.values[0];
		ay = event.values[1];
		
	}



    
}
