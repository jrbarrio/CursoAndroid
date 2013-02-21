package com.planetalia.p09;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
	
	/* INICIO-CAMBIO */
	private List<String> operaciones = new ArrayList<String>();
	/* FIN-CAMBIO */
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pantalla = (TextView)findViewById(R.id.pantalla);
        pantalla.setText("");
        
        operaciones.add("2+3=5");
        operaciones.add("5+3=8");
        operaciones.add("8+3=11");
        operaciones.add("11+3=14");
        operaciones.add("14+3=17");
        operaciones.add("17+3=20");
        operaciones.add("20+3=23");
        operaciones.add("23+3=26");
        operaciones.add("26+3=29");
        operaciones.add("29+3=32");
        operaciones.add("32+3=35");
        operaciones.add("35+3=38");
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
    	/* INICIO-CAMBIO */ 
    	operaciones.add(op1+" "+operador+" "+op2+" = "+resultado);
    	/* FIN-CAMBIO */
    	
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
    
    /* INICIO CAMBIO */
    public void borrarHistorico(View v) {
    	operaciones.clear();
    }
    
    public void mostrarHistorico(View v) {
    	Intent intent = new Intent(this,Historico.class);
    	intent.putExtra("historico",operaciones.toArray(new String[0]));
    	startActivity(intent);
    }
    /* FIN CAMBIO */
}

