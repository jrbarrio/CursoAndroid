package com.atos.android.p09;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Historial extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historial);
	
		Intent msg = getIntent();
		String historial = msg.getExtras().getString("historial");
		
		EditText texto = (EditText) findViewById(R.id.texto);
		texto.setText(historial);
	}

	public void cerrarHistorial(View v) {		
		finish();
	}

}
