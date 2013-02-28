package com.atos.android;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	private MapView mapa;
	private MapController controlador;
	private Map<String,Prediccion> predicciones;
	private Button[] botones = new Button[3];
	private Button botonPulsado = null;
	private String poblacionSeleccionada = null;
	private String codigoSeleccionado = null;
	private int latitud;
	private int longitud;

	private void inicializarBotones() {
		Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
        botones[0] = (Button)findViewById(R.id.btn1);
        botones[0].setText(sdf.format(c.getTime()));
        botones[0].setTag(c.getTime());
        
        c.add(Calendar.DAY_OF_MONTH,1);
        botones[1] = (Button)findViewById(R.id.btn2);
        botones[1].setText(sdf.format(c.getTime()));
        botones[1].setTag(c.getTime());
        
        c.add(Calendar.DAY_OF_MONTH,1);
        botones[2] = (Button)findViewById(R.id.btn3);
        botones[2].setText(sdf.format(c.getTime()));
        botones[2].setTag(c.getTime());
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapa = (MapView)findViewById(R.id.mapa);
        mapa.setClickable(true);
        mapa.setBuiltInZoomControls(true);
        controlador = mapa.getController();
        controlador.setZoom(6);
        inicializarBotones();
    }
	
    public void seleccionarPoblacion(View v) {
    	Intent intent = new Intent(this,SeleccionPoblacion.class);
    	startActivityForResult(intent, 0);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
   		codigoSeleccionado = data.getStringExtra("codigo");
   		poblacionSeleccionada = data.getStringExtra("nombre");
   		Geocoder gc = new Geocoder(this,new Locale("es"));
   		try {
	   		List<Address> direcciones = gc.getFromLocationName(poblacionSeleccionada+",España", 1);
	   		if (direcciones.size() > 0) {
	   			Address dir =direcciones.get(0);
	   			latitud = (int)(dir.getLatitude()*1E6);
	   			longitud = (int)(dir.getLongitude()*1E6);
	   			GeoPoint punto = new GeoPoint(latitud, longitud);
	   			controlador.animateTo(punto);
	   		} else {
	   			Toast.makeText(this, "Población no encontrada", Toast.LENGTH_SHORT).show();
	   		}
   		} catch (IOException e) {
   			Toast.makeText(this, "Error de E/S "+e.getMessage(), Toast.LENGTH_SHORT).show();
   		}
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
    public void prediccionesRecuperadas(Map<String,Prediccion> predicciones) {
    	this.predicciones = predicciones;
    	mostrarPrediccion((Date)botonPulsado.getTag());
    }
    
    public void verPrediccion(View v) {
    	if (poblacionSeleccionada == null) {
    		Toast.makeText(this, "Seleccione una población primero", Toast.LENGTH_LONG).show();
    		return;
    	}
    	if (predicciones == null) {
    		 botonPulsado = (Button)v;
    		 LectorPredicciones lector = new LectorPredicciones();
    		 lector.codigoPoblacion = codigoSeleccionado;
    		 lector.padre = this;
    		 lector.start();
    		 return;
    	}
    	mostrarPrediccion((Date)v.getTag());
    }
    
    public void mostrarPrediccion(Date d) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String clave = sdf.format(d);
        Prediccion p = predicciones.get(clave);
        if (p == null) {
        	Toast t =Toast.makeText(this, "No hay prediccion para la fecha "+clave, Toast.LENGTH_SHORT);
        	t.show();
        	return;
        }
        mapa.getOverlays().clear();
        mapa.getOverlays().add(new MarcadorCielo(this,p.icono+".png",latitud,longitud));
        mapa.invalidate();
    }
    
}
