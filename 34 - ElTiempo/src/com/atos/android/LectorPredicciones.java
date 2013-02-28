package com.atos.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;
import android.widget.Toast;

public class LectorPredicciones extends Thread {
	public MainActivity padre;
	public String codigoPoblacion="28079";
   
    public String leerFlujo(InputStream in) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String s = br.readLine();
			if (s == null) break;
			sb.append(s);
			sb.append('\n');
		}
		br.close();
		return sb.toString();
	}
	
	public Map<String,Prediccion> getPrediccion() throws IOException {
		Map<String,Prediccion> predicciones = new HashMap<String, Prediccion>();
		URL url = new URL("http://www.aemet.es/xml/municipios/localidad_"+codigoPoblacion+".xml");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.addRequestProperty("Host", "www.aemet.es");
		con.addRequestProperty("Content-Type", "text/xml");
		String resultado = leerFlujo(con.getInputStream());

		Pattern dias = Pattern.compile("<dia fecha=\"(.*?)\">(.*?)</dia>",Pattern.DOTALL);
		Pattern datos = Pattern.compile(
				"<estado_cielo .*? descripcion=\"(.*?)\">(\\d\\d)</estado_cielo>.*?<temperatura>\\s*<maxima>(.*?)</maxima>.*?<minima>(.*?)</minima>.*?",Pattern.DOTALL
		);
		Matcher m = dias.matcher(resultado);
		while (m.find()) {
			String fecha = m.group(1);
			Prediccion prediccion = new Prediccion();
			prediccion.fecha = fecha;
			predicciones.put(fecha,prediccion);
			Matcher m1 = datos.matcher(m.group(2));
			if (m1.find()) {
				prediccion.cielo = m1.group(1);
				prediccion.icono = m1.group(2);
				prediccion.temperaturaMaxima = m1.group(3);
				prediccion.temperaturaMinima = m1.group(4);
			}
		}
		return predicciones;
	}
	

	public void run() {
		try {
			final Map<String,Prediccion> predicciones = getPrediccion();
			padre.runOnUiThread(
					new Runnable() {
						public void run() {
							padre.prediccionesRecuperadas(predicciones);	
						}
					}
			);
		} catch(IOException e) {
			Log.e("Meteo","Error de red",e);
			padre.runOnUiThread(new Runnable() {
				public void run() {
					Toast t =Toast.makeText(padre, "Error de Red", Toast.LENGTH_SHORT);
					t.show();
				}
			});
		}
	}

	
    
	
}