package com.atos.android.p14;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Resources res = getResources();
		String cadena = res.getString(R.string.frase, "pepe", 1500);
		Log.i("MAinActivity", "Cadena leida = " + cadena);
		
		String[] valores = res.getStringArray(R.array.estaciones);
		
		try {
			InputStream in = res.openRawResource(R.raw.android);
			in.close();
			
			AssetManager assets = getAssets();
			InputStream in2 = assets.open("android.png");
			in2.close();
			
		} catch (NotFoundException e) {
			Log.e("MAinActivity", "Error. Recurso no encontrado.");
		} catch (IOException e) {
			Log.e("MAinActivity", "Error de E/S.");
		}
	}

}
