package com.atos.android.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button boton = (Button)findViewById(R.id.boton);
        boton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View v) {
		EditText operando1 = (EditText) findViewById(R.id.operando1);
		EditText operando2 = (EditText) findViewById(R.id.operando2);
		
		int suma = Integer.parseInt(operando1.getText().toString()) + Integer.parseInt(operando2.getText().toString());
		
		
		TextView resultado = (TextView) findViewById(R.id.resultado);
		
		resultado.setText(Integer.toString(suma));
	}

    
}
