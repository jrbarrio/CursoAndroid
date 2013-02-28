package com.atos.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MiOpenHelper extends SQLiteOpenHelper{
	private Context ctx;

	public MiOpenHelper(
			Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		this.ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("","Creando base de datos v."+db.getVersion());
		db.execSQL("create table poblaciones ( "+
				 "codprovincia varchar(2),"+
				 "codpoblacion varchar(3),"+
				 "control varchar(1),"+
				 "nombre varchar(50)"+
				")"
		);
		db.execSQL("CREATE INDEX poblaciones_idx ON poblaciones(nombre)");
		BufferedReader br = null;
		int filas = 0;
		try {
			InputStream in = ctx.getAssets().open("poblaciones_ine.sql");
			br = new BufferedReader(new InputStreamReader(in));
			while (true) {
				filas++;
				String s =br.readLine();
				if (s == null) break;
				if (s.length() > 5) {
					if (s.endsWith(";")) s = s.substring(0,s.length()-1);
					db.execSQL(s);
				}
			}
		} catch (IOException e) {
			Log.e("","Error de E/S ",e);
		} finally {
			if (br != null) try { br.close(); } catch (IOException e) {}
		}
		Log.i("","Base de datos creada : "+filas+" poblaciones");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	

}
