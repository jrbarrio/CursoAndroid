package com.atos.android.p09;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> pila = new ArrayList<String>();
	private List<String> history = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void numeroPulsado(View v) {
		TextView pantalla = (TextView) findViewById(R.id.pantalla);
		Button botonPulsado = (Button) findViewById(v.getId());
		
		String actual = pantalla.getText().toString();
		String introducido = botonPulsado.getText().toString();
		
		pantalla.setText(actual + introducido);
	}
	
	public void operadorPulsado(View v) {
		TextView pantalla = (TextView) findViewById(R.id.pantalla);
		Button operadorPulsado = (Button) findViewById(v.getId());
		
		if (pila.isEmpty()) {
			if (!operadorPulsado.getText().toString().equals("=")) {
				pila.add(pantalla.getText().toString());
				pila.add(operadorPulsado.getText().toString());
				pantalla.setText("");
			}			
		} else {
			String operando1 = pila.get(0);
			String operadorAnterior = pila.get(1);			
			String operando2 = pantalla.getText().toString();
				
			int resultado = 0;
			if (operadorAnterior.equals("+")) {
				resultado = suma(Integer.parseInt(operando1), Integer.parseInt(operando2));
			} else if (operadorAnterior.equals("-")) {
				resultado = resta(Integer.parseInt(operando1), Integer.parseInt(operando2));
			} else if (operadorAnterior.equals("/")) {
				resultado = division(Integer.parseInt(operando1), Integer.parseInt(operando2));
			} else if (operadorAnterior.equals("*")) {
				resultado = producto(Integer.parseInt(operando1), Integer.parseInt(operando2));
			}
			
			String linea = operando1 + operadorAnterior + operando2 + "=" + resultado; 
			history.add(linea);			
			
			if (operadorPulsado.getText().toString().equals("=")) {			
				pantalla.setText(Integer.toString(resultado));
			} else {
				pila = new ArrayList<String>();
				pila.add(Integer.toString(resultado));
				pila.add(operadorPulsado.getText().toString());
				pantalla.setText("");
			}
		}
	}
	
	public int suma(int operado1, int operando2) {
		return operado1 + operando2;
	}
	
	public int resta(int operado1, int operando2) {
		return operado1 - operando2;
	}
	
	public int division(int operado1, int operando2) {
		return operado1 / operando2;
	}
	
	public int producto(int operado1, int operando2) {
		return operado1 * operando2;
	}

	public void borrarHistorial(View v) {
		history = new ArrayList<String>();
	}
	
	public void mostrarHistorial(View v) {
		Intent msg = new Intent(this, Historial.class);
		StringBuffer historial = new StringBuffer();
		
		for (String linea : history) {
			historial.append(linea);
			historial.append("\n");
		}		
		
		msg.putExtra("historial", historial.toString());
		
	
		startActivity(msg);
	}
}
