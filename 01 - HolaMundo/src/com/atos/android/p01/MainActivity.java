package com.atos.android.p01;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView etiqueta;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        etiqueta = (TextView) findViewById(R.id.etiqueta);
        etiqueta.setText("Hola Mundo!");
        
        Button boton = (Button)findViewById(R.id.boton);
        boton.setOnClickListener(this);
    }

	public void onClick(View v) {
		Log.d("MainActivity", "Se ha pulsdo el boton");
		Log.i("MainActivity", "Mensaje informativo");
		Log.w("MainActivity", "Mensaje de aviso");
		Log.e("MainActivity", "Mensaje de error");
		
		Log.println(Log.INFO, "MainActivity", "Mensaje informativo 2");
		
		etiqueta.setText("Me has pulsado");
		etiqueta.setBackgroundColor(Color.GREEN);		
	}   
	
	public void botonPulsado(View v) {
		etiqueta.setText("Has pulsado otro boton");
		etiqueta.setBackgroundColor(Color.MAGENTA);	
	}
}
