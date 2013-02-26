package com.atos.android.p24;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void convertir(View v) {
		try {
			EditText resultado = (EditText) findViewById(R.id.resultado);
			resultado.setText(Divisas.convertir("EUR", "USD"));
		} catch (IOException e) {

		}	
	}
}
