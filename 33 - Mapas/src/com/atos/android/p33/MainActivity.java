package com.atos.android.p33;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {

	private MapView mapa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		mapa = (MapView) findViewById(R.id.mapa);
		
		mapa.setBuiltInZoomControls(true);
		mapa.setClickable(true);
		
		mapa.setSatellite(true);
		
		try {
			Geocoder gc = new Geocoder(this);
			List<Address> direcciones = gc.getFromLocationName("Madrid, Spain", 1);
			if (direcciones.size() > 0) {
				int latitud = (int) (direcciones.get(0).getLatitude()*1E6);
				int longitud = (int) (direcciones.get(0).getLongitude()*1E6);
				
				// Centrar el mapa en el punto encontrado
				MapController controlador = mapa.getController();
				GeoPoint punto = new GeoPoint(latitud, longitud);
				controlador.animateTo(punto);
				
				// Colocar un marcador
				Marcador m = new Marcador(this, latitud, longitud);
				mapa.getOverlays().add(m);
			}
		} catch (IOException e) {
			Log.e("", "Error de red");
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
