package com.atos.android.p32;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MiOpenHelper extends SQLiteOpenHelper {

	public MiOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("", "Creando base de datos v." + db.getVersion());
		db.execSQL("create table clientes (" +
				"id numeric(5, 0) primary key, " +
				"nombre varchar(30), " +
				"saldo numeric(14,2), " +
				"fechaalta date)");
		db.execSQL("insert into clientes values (3, 'Pepe', 100, '2013-02-28')");
		db.execSQL("insert into clientes values (4, 'Antonio', 140, '2013-01-28')");
		db.execSQL("insert into clientes values (5, 'Gonzalo', 190, '2013-06-28')");
		db.execSQL("insert into clientes values (6, 'Marta', 200, '2013-11-28')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
}
