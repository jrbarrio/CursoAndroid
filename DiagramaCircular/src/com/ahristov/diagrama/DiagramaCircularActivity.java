package com.ahristov.diagrama;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;

import com.planetalia.diagrama.R;

public class DiagramaCircularActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Lienzo lienzo = (Lienzo)findViewById(R.id.lienzo1);
        Map<String,Integer> mapa = new HashMap<String, Integer>();
        mapa.put("Merkel",25);
        mapa.put("Chavez",25);
        mapa.put("Sarkozy",50);
        mapa.put("Putin",100);
        mapa.put("Gargamel",250);
        lienzo.setDatos(mapa);
  
    }
}
