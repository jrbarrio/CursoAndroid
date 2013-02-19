package com.atos.android.p09;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Stack<String> pila = new Stack<String>();
	
	
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
				pila.push(pantalla.getText().toString());
				pila.push(operadorPulsado.getText().toString());
				pantalla.setText("");
			}			
		} else {
			if (operadorPulsado.getText().toString().equals("=")) {
				String operando2 = pantalla.getText().toString();
				String operadorAnterior = pila.pop();
				String operando1 = pila.pop();
				
				int acumulado = 0;
				if (operadorAnterior.equals("+")) {
					acumulado = suma(Integer.parseInt(operando1), Integer.parseInt(operando2));
				} else if (operadorAnterior.equals("-")) {
					acumulado = resta(Integer.parseInt(operando1), Integer.parseInt(operando2));
				} else if (operadorAnterior.equals("/")) {
					acumulado = division(Integer.parseInt(operando1), Integer.parseInt(operando2));
				} else if (operadorAnterior.equals("*")) {
					acumulado = producto(Integer.parseInt(operando1), Integer.parseInt(operando2));
				}
				
				pantalla.setText(Integer.toString(acumulado));
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

}
