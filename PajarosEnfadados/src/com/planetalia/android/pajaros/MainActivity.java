package com.planetalia.android.pajaros;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements Runnable {
	private static final int ANCHURA_PAJARO = 40;
	private static final int ALTURA_PAJARO = 38;
	private static final int VELOCIDAD_PAJARO = 5;
	private static final int NUM_FOTOGRAMAS = 12;
	
	private Bitmap escenario;
	private Bitmap pajaro_izquierda;
	private Bitmap pajaro_derecha;
	private Bitmap pajaro_actual;
	private Paint brocha = new Paint();
	private SurfaceView view;
	private int x = 0;
	private int y = 0;
	private int vx = VELOCIDAD_PAJARO;
	private int fotograma = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        escenario = cargarImagen("imagenes/escenario.jpg");
        pajaro_izquierda = cargarImagen("imagenes/birdl.png");
        pajaro_derecha = cargarImagen("imagenes/birdr.png");
        pajaro_actual = pajaro_derecha;
        view = (SurfaceView)findViewById(R.id.lienzo);
        Thread t = new Thread(this);
        t.start();        
    }

    private Bitmap cargarImagen(String asset) {
		InputStream in = null;
		try {
			in = getAssets().open(asset);
			Bitmap imagen = BitmapFactory.decodeStream(in);
			return imagen;
		} catch (IOException e) {
			Log.e("Graficos", "Error de E/S",e);
			return Bitmap.createBitmap(32, 32, Config.ARGB_8888);
		} finally {
			if (in != null) try { in.close(); } catch (IOException e) {}
		}
	}
    
    public void run() {
    	while (true) {
    		try { Thread.sleep(25); } catch (InterruptedException e) {}
    		SurfaceHolder sh = view.getHolder();
    		Canvas c = sh.lockCanvas();
    		if (c == null) continue;
    		
    		// Pintar el escenario
    		Rect origen = new Rect(0,0,escenario.getWidth(),escenario.getHeight());
    		RectF destino = new RectF(0,0,view.getWidth(),view.getHeight());
    		c.drawBitmap(escenario,origen,destino,brocha);
    		
    		// Pintar el bicho
    		origen = new Rect(
    			fotograma*ANCHURA_PAJARO,0,
    			(fotograma+1)*ANCHURA_PAJARO-1,ALTURA_PAJARO);
    		destino = new RectF(x,y,x+ANCHURA_PAJARO-1,y+ALTURA_PAJARO-1);
    		c.drawBitmap(pajaro_actual,origen,destino,brocha);
    		
    		sh.unlockCanvasAndPost(c);
    		fotograma = (fotograma + 1) % NUM_FOTOGRAMAS;
    		x+=vx;
    		if (x > view.getWidth())
    			irAIzquierda(null);
    		if (x < 0)
    			irADerecha(null);
    			
    	}
    }
    
    public void irADerecha(View v) {
    	vx = VELOCIDAD_PAJARO;
    	pajaro_actual = pajaro_derecha;
    }
    
    public void irAIzquierda(View v) {
    	vx = -VELOCIDAD_PAJARO;
    	pajaro_actual = pajaro_izquierda;
    }


    
}

/*



/data/data/<paquete>/files



*/