package com.atos.android.p17;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void cambiarColorCirculo(View v) {
		Lienzo lienzo = (Lienzo) findViewById(R.id.lienzo1);
		lienzo.setColor(Color.rgb(255, 2, 40));
	}

}
