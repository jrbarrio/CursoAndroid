package com.atos.android.p15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SegundaActividad extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.segunda_actividad);
		
		Intent msg = getIntent();
		String nombre = msg.getExtras().getString("nombre");
		
		TextView texto = (TextView) findViewById(R.id.texto);
		texto.setText("Hola " + nombre);
		
	}

	public void cerrarSegundaActividad(View v) {
		Intent msg = new Intent();
		msg.putExtra("res", "ejemplo de resultado");
		setResult(RESULT_OK, msg);
		finish();
	}

}
