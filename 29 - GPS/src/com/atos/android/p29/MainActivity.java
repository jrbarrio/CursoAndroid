package com.atos.android.p29;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity implements Listener, LocationListener {

	private EditText latitud;
	private EditText longitud;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		latitud = (EditText) findViewById(R.id.latitud);
		longitud = (EditText) findViewById(R.id.longitud);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		List<String> sensores = locationManager.getAllProviders();
		for (String sensor: sensores) {
			Log.i("", "Sensor: " + sensor);
			LocationProvider proveedor = locationManager.getProvider(sensor);
			Log.i("", "Cuesta dinero? " + proveedor.hasMonetaryCost());
			Log.i("", "Precision: " + proveedor.getAccuracy());
			Log.i("", "Detecta altitud? " + proveedor.supportsAltitude());
			Log.i("", "Detecta velocidad? " + proveedor.supportsSpeed());
			Log.i("", "Detecta orientacion? " + proveedor.supportsBearing());
		}
		
		locationManager.addGpsStatusListener(this);
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				500,
				1,
				this);
	
		try {
			Geocoder gc = new Geocoder(this);
			List<Address> direcciones = gc.getFromLocationName("calle de la Tortola 10, Spain", 1);
			if (direcciones.size() > 0) {
				Address addr = direcciones.get(0);
				Log.i("", "Latitud: " + addr.getLatitude());
				Log.i("", "Longitud: " + addr.getLongitude());
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	public void onGpsStatusChanged(int event) {
//		switch (event) {
//		case GpsStatus.GPS_EVENT_STARTED:
//			Log.i("", "GPS iniciado");
//			break;
//		case GpsStatus.GPS_EVENT_STOPPED:
//			Log.i("", "GPS detenido");
//			break;
//		case GpsStatus.GPS_EVENT_FIRST_FIX:
//			Log.i("", "Primer satelite encontrado");
//			break;
//		case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//			Log.i("", "Cambios en la info de satelites");
//			break;
//		default:
//			break;
//		}
//		
//		GpsStatus status = locationManager.getGpsStatus(null);
//		Log.i("", "Satélites conocidos: " + status.getMaxSatellites());
//		for (GpsSatellite satelite : status.getSatellites()) {
//			Log.i("", "Azimuth: " + satelite.getAzimuth());
//		}
	}

	@Override
	public void onLocationChanged(Location location) {
		latitud.setText(String.valueOf(location.getLatitude()));
		longitud.setText(String.valueOf(location.getLongitude()));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	public void actualizarDireccion(View v) {
		Geocoder gc = new Geocoder(this);
		try {
			List<Address> direcciones = gc.getFromLocation(
					Float.parseFloat(latitud.getText().toString()), 
					Float.parseFloat(longitud.getText().toString()), 
					1);
			
			Address address = direcciones.get(0);
			EditText direccion = (EditText) findViewById(R.id.direccion);
			direccion.setText(address.getAddressLine(0));
		} catch (Exception e) {
			Log.e("", "Se ha producido un error al actualizar la direccion: " + e.getMessage());
		} 
	}
}
