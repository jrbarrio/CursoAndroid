package com.atos.android.p34;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class ActividadSeleccionarMunicipio extends Activity implements OnClickListener {
	
	SQLiteDatabase db;
	
	String nombre;
	String codigo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seleccionar_municipio);	
		
		File f = getDatabasePath("poblaciones");
		Log.i("", "Ruta de la base de datos de poblaciones: " + f.getAbsolutePath());
		
		MiOpenHelper helper = new MiOpenHelper(this, "poblaciones", null, 1);
		SQLiteDatabase db = helper.getWritableDatabase();
		Log.i("", "Base de datos abierta, v." + db.getVersion());
		
		Cursor c = db.rawQuery("select * from poblaciones where nombre like ? ", new String[] {"M%"});
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.resultados);
		
		while (c.moveToNext()) {
			nombre = c.getString(c.getColumnIndex("nombre"));
			codigo = c.getString(c.getColumnIndex("codpoblacion"));
			
			RadioButton radio = new RadioButton(this);
			radio.setText(nombre);
			radio.setOnClickListener(this);
			
			layout.addView(radio);
		}
	}

	@Override
	public void onClick(View v) {
		Intent msg = new Intent();
		msg.putExtra("nombre", nombre);
		msg.putExtra("codigo", codigo);
		setResult(RESULT_OK, msg);
		finish();		
	}

}
