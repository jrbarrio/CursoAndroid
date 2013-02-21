package com.atos.android.p17;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener{
	private SeekBar barraRoja;
	private SeekBar barraVerde;
	private SeekBar barraAzul;
	private Lienzo lienzo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        barraRoja = (SeekBar)findViewById(R.id.barraRoja);
        barraVerde = (SeekBar)findViewById(R.id.barraVerde);
        barraAzul = (SeekBar)findViewById(R.id.barraAzul);
        lienzo = (Lienzo)findViewById(R.id.lienzo);
        
        barraRoja.setOnSeekBarChangeListener(this);
        barraVerde.setOnSeekBarChangeListener(this);
        barraAzul.setOnSeekBarChangeListener(this);
    }

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		int r = barraRoja.getProgress();
		int g = barraVerde.getProgress();
		int b = barraAzul.getProgress();
		lienzo.setColorCirculo(Color.rgb(r, g, b));
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}

  
    
}
