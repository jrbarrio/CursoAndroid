package com.atos.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeleccionPoblacion extends Activity implements TextWatcher,OnClickListener{
	private EditText poblacion;
	private SQLiteDatabase db;
	private LinearLayout listaPoblaciones;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_poblacion);
        poblacion = (EditText)findViewById(R.id.poblacion);
        poblacion.addTextChangedListener(this);
        MiOpenHelper helper = new MiOpenHelper(this, "poblaciones", null, 1);
        db = helper.getWritableDatabase();
        listaPoblaciones = (LinearLayout)findViewById(R.id.listaPoblaciones);
    }

    public void afterTextChanged(Editable s) {
    	Cursor cr = db.rawQuery("select * from poblaciones where nombre like ? limit 10", 
    		new String[] {poblacion.getText().toString()+"%"});
    	
    	listaPoblaciones.removeAllViews();
    	while (cr.moveToNext()) {
    		String nombre = cr.getString(cr.getColumnIndex("nombre"));
    		String codigo = 
    				cr.getString(cr.getColumnIndex("codprovincia"))+
    				cr.getString(cr.getColumnIndex("codpoblacion"));
    		TextView tv = new TextView(this);
    		tv.setText(nombre);
    		tv.setTag(codigo);
    		tv.setTextSize(20);
    		tv.setTextColor(Color.BLUE);
    		tv.setOnClickListener(this);
    		listaPoblaciones.addView(tv);
    	}
	}
    
    
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

	public void onClick(View v) {
		db.close();
		String nombre = ((TextView)v).getText().toString();
		String codigo = (String)v.getTag();
		Intent intent = new Intent();
		intent.putExtra("nombre", nombre);
		intent.putExtra("codigo",codigo);
		setResult(RESULT_OK, intent);
		finish();
	}
 

    
}
