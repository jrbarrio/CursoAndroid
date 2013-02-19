package com.atos.android.p10;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class MainActivity extends Activity implements AnimationListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void iniciarAnimacion(View v) {
		View img = findViewById(R.id.imagen);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.ejemplo1);
		anim.setAnimationListener(this);
		img.startAnimation(anim);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.i("MainActivity", "Animacion terminada");		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Log.i("MainActivity", "Animacion repetida");		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.i("MainActivity", "Animacion iniciada");		
	}

}
