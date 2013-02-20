package com.atos.android.p15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void abrirActividad(View v) {
		Intent msg = new Intent(this, SegundaActividad.class);
		
		EditText textoOrigen = (EditText) findViewById(R.id.origen);		
		msg.putExtra("nombre", textoOrigen.getText().toString());
		//startActivity(msg);
	
		startActivityForResult(msg, 100);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			String resultado = data.getStringExtra("res");
			Log.i("MainActivity", resultado);
		}
	}
}
