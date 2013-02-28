package com.atos.android.p32;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		File f = getDatabasePath("clientes");
		Log.i("", "Ruta de la base de datos de clientes: " + f.getAbsolutePath());
		
		MiOpenHelper helper = new MiOpenHelper(this, "clientes", null, 3);
		SQLiteDatabase db = helper.getWritableDatabase();
		Log.i("", "Base de datos abierta, v." + db.getVersion());
		
		Cursor c = db.rawQuery("select * from clientes where saldo > ? ", new String[] {"100"});
	
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c.getColumnCount(); i++) {
			sb.append(c.getColumnName(i) + " \t");
		}
		Log.i("", sb.toString());
		
		while (c.moveToNext()) {
			sb = new StringBuilder();
			for (int i = 0; i < c.getColumnCount(); i++) {
				sb.append(c.getString(i) + " \t");
			}
			Log.i("", sb.toString());
			
			int indice = c.getColumnIndex("saldo");
			double saldo = c.getDouble(indice);
			Log.i("", Double.toString(saldo));
			
			double saldo2 = c.getDouble(c.getColumnIndex("saldo"));
			Log.i("", Double.toString(saldo2));
		}
		
		db.beginTransaction();
		try {
			// operaciones transaccionales
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction(); // si no ha habido un commit con setTransactionSuccessful() aqui se haría un rollback
		}
	}
}