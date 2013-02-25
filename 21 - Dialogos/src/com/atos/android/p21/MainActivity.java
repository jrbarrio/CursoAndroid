package com.atos.android.p21;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void mostrarAlerta(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Cuadro de diálogo");
		
		builder.setIcon(R.drawable.ic_launcher);
		//builder.setMessage("Mensaje del dialogo");
//		builder.setMultiChoiceItems(
//			new String[] {"Opcion 1", "Opcion 2", "Opcion 3"},
//			new boolean[] {true, false, true}, 
//			new OnMultiChoiceClickListener() {	
//				@Override
//				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//					Log.i("MAinActivity", "Boton " + which + " " + (isChecked ? "seleccionado" : "no seleccionado"));					
//				}
//			}
//		);
		
//		builder.setSingleChoiceItems(
//				new String[] {"Opcion 1", "Opcion 2", "Opcion 3"}, 
//				1, 
//				new OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//				});
		
		builder.setItems(
				new String[] {"Madrid", "Barcelona", "Valencia"},
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
					
				});
		
		
		builder.setPositiveButton("Aceptar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
		});
		
		builder.setNegativeButton("Cancelar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();				
			}
		});
		
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	public void mostrarFecha(View v) {
		DatePickerDialog dialogo = new DatePickerDialog(this, 
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						Log.i("", dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
					}
				},
				2013, 1, 25);
		dialogo.show();
	}
	
	public void mostrarHora(View v) {
		TimePickerDialog dialogo = new TimePickerDialog(
				this,
				new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						Log.i("", hourOfDay + ":" + minute);
					}
				},
				23, 50, true);
		dialogo.show();
	}
	
	public void dialogoPersonalizado(View v) {
		final Dialog dialogo = new Dialog(this);
		dialogo.setContentView(R.layout.dialogo);
		Button cerrar = (Button) dialogo.findViewById(R.id.cerrar);
		cerrar.setOnClickListener(
				new android.view.View.OnClickListener() {					
					@Override
					public void onClick(View v) {
						dialogo.hide();						
					}
				});
		dialogo.show();
	}
	
	public void mostrarNotificacion(View v) {
		Toast t = Toast.makeText(this, "Error de E/S", Toast.LENGTH_SHORT);
		t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 20);
		t.show();
	}

}
