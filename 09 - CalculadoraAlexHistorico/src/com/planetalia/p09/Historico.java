package com.planetalia.p09;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Historico extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        String[] operaciones = getIntent().getStringArrayExtra("historico");
        LinearLayout vista = (LinearLayout)findViewById(R.id.vistaOperaciones);
        vista.removeAllViews();
        for (String operacion : operaciones ) {
        	TextView tv = new TextView(this);
        	tv.setText(operacion);
        	/*
        	tv.setTextColor(Color.YELLOW);
        	tv.setTypeface(Typeface.MONOSPACE);
        	tv.setTextSize(24);
        	tv.setShadowLayer(2, 6, 6, Color.RED);
        	*/
        	vista.addView(tv);
        }
    }

    public void cerrar(View v) {
    	ScrollView sv = (ScrollView)findViewById(R.id.scrollView1);
    	int centroX = sv.getWidth()/2;
    	int centroY = sv.getHeight()/2;
    	
    	/*
		<set>
			<rotate />
			<scale />
		</set>


    	 */
    	AnimationSet anim = new AnimationSet(true);
    	RotateAnimation rotate = new RotateAnimation(0, 720, centroX, centroY);
    	rotate.setDuration(1500);
    	ScaleAnimation scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, centroX, centroY);
    	scale.setDuration(1500);
    	
    	anim.addAnimation(rotate);
    	anim.addAnimation(scale);
    	anim.setFillAfter(true);
    	anim.setFillEnabled(true);
    	
    	anim.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				finish();
			}
			public void onAnimationRepeat(Animation animation) {}
			public void onAnimationStart(Animation animation) {	}
    	});
    	sv.startAnimation(anim);
    }
    
}
