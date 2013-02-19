package com.atos.android.p08;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		LinearLayout ll = (LinearLayout) findViewById(R.id.layout);
		for (int i = 0; i < 20; i++) {
			Button boton = new Button(this);
			boton.setText("Boton numero " + i);
			
			LinearLayout.LayoutParams params = 
					new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
			
			ll.addView(boton, params);
		}
	}

}
