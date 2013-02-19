package com.planetalia.p09;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraActivity extends Activity {
    private String operandoAnterior = "0";
    private char operador='\0';
	private static final String DIGITOS ="0123456789";
	private TextView pantalla;
	private boolean mostrandoResultado = false;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pantalla = (TextView)findViewById(R.id.pantalla);
        pantalla.setText("");
        /*
        int[] ids = { R.id.btn0,R.id.btn1 };
        for (int id: ids) {
        	((Button)findViewById(id)).setOnClickListener(this);
        }
        */
    }
    
    public void botonPulsado(View v) {
    	Button btn = (Button)v;
    	char txt = btn.getText().charAt(0);
    	if (DIGITOS.indexOf(txt)!= -1 ) {
    		if (mostrandoResultado)
    			pantalla.setText("");
    		pantalla.append(String.valueOf(txt));
    		mostrandoResultado = false;
    		return;
    	}
    	int op1 = Integer.parseInt(operandoAnterior);
    	int op2 = Integer.parseInt(pantalla.getText().toString());
    	int resultado;
    	switch (operador) {
    		case '-': resultado = op1-op2;break;
    		case '*': resultado = op1*op2;break;
    		case '/': resultado = op1/op2;break;
    		default : resultado = op1+op2;break;
    	}
    	operandoAnterior = String.valueOf(resultado);
    	if (txt == '=') {
    		pantalla.setText(operandoAnterior);
    		operandoAnterior = "0";
    		operador = '+';
    		return;
    	} else {
    		mostrandoResultado = true;
    		pantalla.setText(String.valueOf(resultado));
    		operador = txt;
    	}
    }
}

