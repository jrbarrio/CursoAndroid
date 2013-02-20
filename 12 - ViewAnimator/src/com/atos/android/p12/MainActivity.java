package com.atos.android.p12;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewAnimator;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void cambiarVista(View v) {
		ViewAnimator animador = (ViewAnimator) findViewById(R.id.animador);
		
		animador.showNext();
	}

}
