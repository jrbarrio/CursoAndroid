package com.atos.android.p13;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.util.Linkify;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//TextView etiqueta = (TextView) findViewById(R.id.etiqueta);
		//etiqueta.setAutoLinkMask(Linkify.ALL);
		
//		etiqueta.setAutoLinkMask(Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES);
//		
//		etiqueta.setLinksClickable(true);
//		etiqueta.setText(
//				"Pepe Gomez, tel 677556886, email \n" +
//				"pepegomez@gmail.com, pagina web personal \n" +
//				"http://www.pepegomez.com");
//		
//		etiqueta.setText(
//				Html.fromHtml("Hola <b>Mundo</b> " +
//						"<u><font color='red' size='14'>¿que tal?</u>"));
		
		SeekBar barra = (SeekBar) findViewById(R.id.barra);
		barra.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		EditText texto = (EditText) findViewById(R.id.texto);
		texto.setText(Integer.toString(progress));
		
		ProgressBar progreso = (ProgressBar) findViewById(R.id.progreso);
		progreso.setProgress(progress);		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
