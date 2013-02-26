package com.atos.android.p23;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.atos.divisas.Divisas;

public class MainActivity extends Activity implements Runnable{
	private Spinner origen;
	private Spinner destino;
	private TextView resultado;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        origen = (Spinner)findViewById(R.id.origen);
        destino = (Spinner)findViewById(R.id.destino);
        resultado = (TextView)findViewById(R.id.resultado);
    }
    
    public void convertir(View v) {
    	Thread t = new Thread(this);
    	t.start();
    }
    
    public void run() {
    	String divisaOrigen = origen.getSelectedItem().toString();
    	String divisaDestino = destino.getSelectedItem().toString();
    	try {
    		final String factor = Divisas.convertir(divisaOrigen, divisaDestino);
    		runOnUiThread(new Runnable() {
    			public void run() {
    				resultado.setText(factor);
    			}
    		});
    	} catch (final IOException e) {
    		runOnUiThread(new Runnable() {
    			public void run() {
    				Toast.makeText(MainActivity.this, "Error de E/S : "+e.getMessage(), Toast.LENGTH_LONG).show();
    			}
    		});
    	}
    	
    }

}
